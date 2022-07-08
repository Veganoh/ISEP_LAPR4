#include "config.h"
#include "communication_config.h"
#include "Packet_Utils.h"

shared_data *data;
sem_t *sem_calculate_route;
sem_t *sem_assign_task;
sem_t *sem_shared_memory;


void send_finnish_task_code(SSL *sslConn) {
    connection_packet packet;

    packet.version = 1;
    packet.code = TASK_FINISH;
    packet.d_length1 = 0;
    packet.d_length2 = 0;

    printf("\nSent packet with code %d!\n", TASK_FINISH);
    SSL_write(sslConn, &packet, sizeof(packet));
}

void receive_task(SSL *sslConn) {
    unsigned char buffer[8], *ptr;
    int x;
    int y;

    strcpy(data->status.status, SERVING_ORDER);

    SSL_read(sslConn, buffer, 8);

    ptr = deserialize_int(buffer, &y);

    deserialize_int(ptr, &x);

    printf("\nTask Received: %d(x) - %d(y)\n", x, y);

    pthread_mutex_lock(&shared_data_access);
    sem_wait(sem_shared_memory);

    data->task.x = x;
    data->task.y = y;

    sem_post(sem_shared_memory);
    pthread_mutex_unlock(&shared_data_access);    
    sem_post(sem_calculate_route);
    
    sem_wait(sem_assign_task);
    send_finnish_task_code(sslConn);
    
}

void send_acknowledge_response(int newSock, SSL *sslConn) {
    connection_packet packet;

    packet.version = 1;
    packet.code = ACK;
    packet.d_length1 = 0;
    packet.d_length2 = 0;

    SSL_write(sslConn, &packet, sizeof(packet));
    printf("\nSent packet with code %d!\n", ACK);
}

void* thread_server(void *arg) {
    struct sockaddr_storage from;
	int err, newSock, sock;
	unsigned int adl;
	char cliIPtext[BUF_SIZE], cliPortText[BUF_SIZE];
	struct addrinfo  req, *list;

	bzero((char *)&req, sizeof(req));
	// requesting a IPv6 local address will allow both IPv4 and IPv6 clients to use it
	req.ai_family = AF_INET6;
	req.ai_socktype = SOCK_STREAM;
	req.ai_flags = AI_PASSIVE;      // local address

	err = getaddrinfo(NULL, DIGITAL_PORT, &req, &list);

	if(err) {
        printf("Failed to get local address, error: %s\n", gai_strerror(err)); 
        exit(1); 
    }

	sock=socket(list->ai_family,list->ai_socktype,list->ai_protocol);

	if(sock == -1) {
        perror("Failed to open socket"); 
        freeaddrinfo(list); 
        exit(1);
    }

	if(bind(sock, (struct sockaddr *)list->ai_addr, list->ai_addrlen) == -1) {
        perror("Bind failed");
        close(sock);
        freeaddrinfo(list);
        exit(1);
    }

	freeaddrinfo(list);

	listen(sock,SOMAXCONN);

	const SSL_METHOD *method;
    SSL_CTX *ctx;

	method = SSLv23_server_method();
    ctx = SSL_CTX_new(method);

	// Load the server's certificate and key
    SSL_CTX_use_certificate_file(ctx, DIGITAL_SSL_CERT_FILE, SSL_FILETYPE_PEM);
    SSL_CTX_use_PrivateKey_file(ctx, DIGITAL_SSL_KEY_FILE, SSL_FILETYPE_PEM);

    if (!SSL_CTX_check_private_key(ctx)) {
        puts("Error loading server's certificate/key");
        close(sock);
        exit(1);
    }

	// THE CLIENTS' CERTIFICATES ARE TRUSTED
    SSL_CTX_load_verify_locations(ctx, DIGITAL_CLIENTS_SSL_CERTS_FILE, NULL);

	// Restrict TLS version and cypher suite
    SSL_CTX_set_min_proto_version(ctx, TLS1_2_VERSION);
    SSL_CTX_set_cipher_list(ctx, "HIGH:!aNULL:!kRSA:!PSK:!SRP:!MD5:!RC4");

	// The client must provide a certificate and it must be trusted, the handshake will fail otherwise
	SSL_CTX_set_verify(ctx, SSL_VERIFY_PEER|SSL_VERIFY_FAIL_IF_NO_PEER_CERT, NULL);

	puts("Accepting TCP connections (both over IPv6 or IPv4). Use CTRL+C to terminate the server");

	adl=sizeof(from);

    for(;;) {
        newSock = accept(sock, (struct sockaddr *)&from, &adl);

        puts("Accepted a connection!\n");

        if(!fork()) {
            close(sock);
            
            int size = sizeof(shared_data);

            int fd = shm_open("/digital_twin_shared_mem", O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);
            ftruncate(fd, size);
            data = (shared_data *) mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);

            
            if((sem_shared_memory = sem_open("sem_shared_memory", O_CREAT, 0644, 1)) == SEM_FAILED){
                perror("Error in sem_open()");
                exit(EXIT_FAILURE);
            }

            if((sem_calculate_route = sem_open("sem_calculate_route", O_CREAT, 0644, 0)) == SEM_FAILED){
                perror("Error in sem_open()");
                exit(EXIT_FAILURE);
            }

            if((sem_assign_task = sem_open("sem_assign_task", O_CREAT, 0644, 0)) == SEM_FAILED){
                perror("Error in sem_open()");
                exit(EXIT_FAILURE);
            }

            connection_packet packet;

            getnameinfo((struct sockaddr *)&from, adl, cliIPtext, BUF_SIZE, cliPortText, BUF_SIZE, NI_NUMERICHOST|NI_NUMERICSERV);
            printf("New connection from %s, port number %s\n", cliIPtext, cliPortText);

            SSL *sslConn = SSL_new(ctx);

        	SSL_set_fd(sslConn, newSock);

			if(SSL_accept(sslConn) != 1) {
				puts("TLS handshake error: client not authorized");
				SSL_free(sslConn);
                close(newSock);
				exit(1);
			}

        	printf("TLS version: %s\nCypher suite: %s\n", SSL_get_cipher_version(sslConn), SSL_get_cipher(sslConn));

			X509* cert=SSL_get_peer_certificate(sslConn);
        	X509_free(cert);

			X509_NAME_oneline(X509_get_subject_name(cert), cliIPtext, BUF_SIZE);
        	printf("Client's certificate subject: %s\n",cliIPtext);
            
            SSL_read(sslConn, &packet, sizeof(packet));
            printf("\nPacket received info:\n"); 
            printf("\tVersion: %c\n\tCode: %c\n\tD_Length1: %c\n\tD_Lenght2: %c\n", packet.version + '0', packet.code + '0', packet.d_length1 + '0', packet.d_length2 + '0'); 

            if (packet.code != COMMTEST) {
                printf("Unexpected code received. Expected code %d, received %c.\n", COMMTEST, packet.code);
                SSL_free(sslConn);
                close(newSock);
                exit(-1);
            }

            send_acknowledge_response(newSock, sslConn);

            while(1) {
                SSL_read(sslConn, &packet, sizeof(packet));
                printf("\nPacket received info:\n"); 
                printf("\tVersion: %c\n\tCode: %c\n\tD_Length1: %c\n\tD_Lenght2: %c\n", packet.version + '0', packet.code + '0', packet.d_length1 + '0', packet.d_length2 + '0'); 

                switch(packet.code) {
                    case TASK_ASSIGN:
                        receive_task(sslConn);
                        break;
                    case DISCONN:
                        send_acknowledge_response(newSock, sslConn);

                        strcpy(data->status.status, FREE);
            
                        SSL_free(sslConn);
                        close(newSock);
                        printf("\nConnection %s, port number %s closed\n", cliIPtext, cliPortText);
                        exit(0);
                        break;
                }            
            }
        }

        close(newSock);
    }
    close(sock);
}