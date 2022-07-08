#include "config.h"
#include "communication_config.h"
#include "Packet_Utils.h"

SSL *sslConn;

shared_data *data;

sem_t *sem_shared_memory;

int establish_connection() {
    unsigned char socket_buffer[sizeof(connection_packet)]; 
    int err, sock;
    connection_packet packet;

    struct addrinfo req, *list;

    bzero((char *)&req, sizeof(req));
    
    // let getaddrinfo set the family depending on the supplied server address
    req.ai_family = AF_UNSPEC;
    req.ai_socktype = SOCK_STREAM;
    err = getaddrinfo(MANAGER_IP, MANAGER_PORT, &req, &list);
    
    if(err) {
        printf("Failed to get server address, error: %s\n", gai_strerror(err)); 
        exit(1); 
    }
        
    sock = socket(list->ai_family, list->ai_socktype, list->ai_protocol);
    
    if(sock == -1) {
        perror("Failed to open socket"); 
        freeaddrinfo(list); 
        exit(1);
    }

    if(connect(sock, (struct sockaddr *)list->ai_addr, list->ai_addrlen) == -1) {
        perror("Failed connect"); 
        freeaddrinfo(list); 
        close(sock); 
        exit(1);
    }

    puts("Connected");

    const SSL_METHOD *method = SSLv23_client_method();
    SSL_CTX *ctx = SSL_CTX_new(method);

    // Load client's certificate and key
	SSL_CTX_use_certificate_file(ctx, DIGITAL_SSL_CERT_FILE, SSL_FILETYPE_PEM);
    SSL_CTX_use_PrivateKey_file(ctx, DIGITAL_SSL_KEY_FILE, SSL_FILETYPE_PEM);

    if (!SSL_CTX_check_private_key(ctx)) {
        puts("Error loading client's certificate/key");
        close(sock);
        exit(1);
    }

    SSL_CTX_set_verify(ctx, SSL_VERIFY_PEER, NULL);

    // THE SERVER'S CERTIFICATE IS TRUSTED
    SSL_CTX_load_verify_locations(ctx, MANAGER_SSL_CERT_FILE, NULL);

	// Restrict TLS version and cypher suites
    SSL_CTX_set_min_proto_version(ctx, TLS1_2_VERSION);
	SSL_CTX_set_cipher_list(ctx, "HIGH:!aNULL:!kRSA:!PSK:!SRP:!MD5:!RC4");

	sslConn = SSL_new(ctx);
    SSL_set_fd(sslConn, sock);

    if(SSL_connect(sslConn) != 1) {
        puts("TLS handshake error");
        SSL_free(sslConn);
        close(sock);
        exit(1);
	}
 
	printf("TLS version: %s\nCypher suite: %s\n", SSL_get_cipher_version(sslConn), SSL_get_cipher(sslConn));

	if(SSL_get_verify_result(sslConn) != X509_V_OK) {
		puts("Sorry: invalid server certificate");
        SSL_free(sslConn);
        close(sock);
        exit(1);
    }

	X509* cert = SSL_get_peer_certificate(sslConn);
    X509_free(cert);

    if(cert == NULL) {
        puts("Sorry: no certificate provided by the server");
        SSL_free(sslConn);
        close(sock);
        exit(1);
    }

    packet.version = 1;
    packet.code = COMMTEST;
    packet.d_length1 = 0;
    packet.d_length2 = 0;

    serialize_connection_packet(socket_buffer, &packet);

    SSL_write(sslConn, socket_buffer, sizeof(packet));
    printf("\nSent packet with code %d!\nWaiting for a response...\n", COMMTEST);

    SSL_read(sslConn, socket_buffer, sizeof(packet));
    deserialize_connection_packet(socket_buffer, &packet);

    printf("\nPacket received info:\n"); 
    printf("\tVersion: %d\n\tCode: %d\n\tD_Length1: %d\n\tD_Lenght2: %d\n", (int)packet.version, (int)packet.code, (int)packet.d_length1, (int)packet.d_length2);

    if (packet.code != ACK) {
        printf("Response Packet is Faulty (expected code %d, received %d)\n", ACK, (int)packet.code);
        close(sock);
        exit(-1);
    }

    return sock;
}

int send_disconnect_request(int sock) {
    unsigned char socket_buffer[sizeof(connection_packet)]; 
    connection_packet packet;

    packet.version = 1;
    packet.code = DISCONN;
    packet.d_length1 = 0;
    packet.d_length2 = 0;

    serialize_connection_packet(socket_buffer, &packet);

    SSL_write(sslConn, socket_buffer, sizeof(packet));
    printf("\nSent packet with code %d!\nWaiting for a response...\n", DISCONN);

    SSL_read(sslConn, socket_buffer, sizeof(packet));

    deserialize_connection_packet(socket_buffer, &packet);

    printf("\nPacket received info:\n"); 
    printf("\tVersion: %d\n\tCode: %d\n\tD_Length1: %d\n\tD_Lenght2: %d\n", (int)packet.version, (int)packet.code, (int)packet.d_length1, (int)packet.d_length2); 

    if (packet.code != ACK) {
        printf("Response Packet is Faulty (expected code %d, received %d)\n", ACK, (int)packet.code);
        close(sock);
        exit(-1);
    }

    printf("\nClosing connection with server!\n");

    SSL_free(sslConn);
    close(sock);
    exit(0);
}

int get_agv_info(int sock) {
    unsigned char socket_buffer[sizeof(connection_packet)]; 
    connection_packet packet;

    packet.version = 1;
    packet.code = DIGITAL_TWIN_REQUEST;
    packet.d_length1 = 0;
    packet.d_length2 = 0;

    serialize_connection_packet(socket_buffer, &packet);

    SSL_write(sslConn, socket_buffer, sizeof(packet));
    printf("\nSent packet with code %d!\nWaiting for a response...\n", DISCONN);

    SSL_read(sslConn, socket_buffer, sizeof(packet));
    deserialize_connection_packet(socket_buffer, &packet);

    printf("\nPacket received info:\n"); 
    printf("\tVersion: %d\n\tCode: %d\n\tD_Length1: %d\n\tD_Lenght2: %d\n", (int)packet.version, (int)packet.code, (int)packet.d_length1, (int)packet.d_length2); 

    if (packet.code != DIGITAL_TWIN_ASSIGN) {
        printf("Response Packet is Faulty (expected code %d, received %d)\n", DIGITAL_TWIN_ASSIGN, (int)packet.code);
        close(sock);
        exit(-1);
    }

    size_t json_size = (packet.d_length1 + packet.d_length2 * 256) - 7;
    char json_buffer[json_size];

    SSL_read(sslConn, json_buffer, 7); //Getting rid of overhead/gap in the data
    SSL_read(sslConn, json_buffer, json_size);

    cJSON *agv_info_json = cJSON_ParseWithLength(json_buffer, json_size);

    if (agv_info_json == NULL) {
        const char *error_ptr = cJSON_GetErrorPtr();

        if (error_ptr != NULL) {
            fprintf(stderr, "Error before: %s\n", error_ptr);
        }

        return -1;
    }

    const cJSON *attribute = NULL;

    printf("Received AGV Info: \n");
    
    attribute = cJSON_GetObjectItemCaseSensitive(agv_info_json, "agvId");
    if (cJSON_IsString(attribute) && (attribute->valuestring != NULL)) {
        printf("\tAGV Id: \"%s\"\n", attribute->valuestring);

        strcpy(data->properties.id, attribute->valuestring);
    }

    attribute = cJSON_GetObjectItemCaseSensitive(agv_info_json, "maxSpeed");
    if (cJSON_IsNumber(attribute)) {
        printf("\tMaximum Speed: %d\n", attribute->valueint);

        data->properties.speed = attribute->valueint;
    }

    attribute = cJSON_GetObjectItemCaseSensitive(agv_info_json, "batteryLife");
    if (cJSON_IsNumber(attribute)) {
        printf("\tBattery Life: %d\n", attribute->valueint);

        data->properties.max_battery = attribute->valueint;
    }
    
    pthread_mutex_lock(&shared_data_access);

    attribute = cJSON_GetObjectItemCaseSensitive(agv_info_json, "currPositionX");
    if (cJSON_IsNumber(attribute)) {
        printf("\tCurrent Position X: %d\n", attribute->valueint);

        data->curr_position.coordinate.x = attribute->valueint;
    }

    attribute = cJSON_GetObjectItemCaseSensitive(agv_info_json, "currPositionY");
    if (cJSON_IsNumber(attribute)) {
        printf("\tCurrent Position Y: %d\n", attribute->valueint);

        data->curr_position.coordinate.y = attribute->valueint;
    }

    pthread_mutex_unlock(&shared_data_access);

    attribute = cJSON_GetObjectItemCaseSensitive(agv_info_json, "dockX");
    if (cJSON_IsNumber(attribute)) {
        printf("\tDock X: %d\n", attribute->valueint);

        data->properties.dock.x = attribute->valueint;
    }

    attribute = cJSON_GetObjectItemCaseSensitive(agv_info_json, "dockY");
    if (cJSON_IsNumber(attribute)) {
        printf("\tDock Y: %d\n", attribute->valueint);

        data->properties.dock.y = attribute->valueint;
    }

    pthread_mutex_unlock(&loading_info);

    return 0;
}

int send_status() {
    Coordinate coords;

    pthread_mutex_lock(&shared_data_access);
    sem_wait(sem_shared_memory);

    coords.x = data->curr_position.coordinate.x;
    coords.y = data->curr_position.coordinate.y;

    Status status;

    status.position = coords;
    strcpy(status.status, data->status.status);

    sem_post(sem_shared_memory);
    pthread_mutex_unlock(&shared_data_access);

    unsigned char socket_buffer[30], *ptr; 
    connection_packet packet;

    packet.version = 1;
    packet.code = STATUS;
    packet.d_length1 = 2 + string_size(status.status);
    packet.d_length2 = 0;

    ptr = serialize_connection_packet(socket_buffer, &packet);
    ptr = serialize_status(ptr, &status);

    SSL_write(sslConn, socket_buffer, ptr - socket_buffer);

    printf("\nSent packet with code %d!\n", STATUS);

    return 0;
}

void* thread_client(void *arg) {
    int size = sizeof(shared_data);

    int fd = shm_open("/digital_twin_shared_mem", O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);
    ftruncate(fd, size);
    data = (shared_data *) mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);

    if((sem_shared_memory = sem_open("sem_shared_memory", O_CREAT, 0644, 1)) == SEM_FAILED){
        perror("Error in sem_open()");
        exit(EXIT_FAILURE);
    }

    int sock;

    if ((sock = establish_connection()) == -1) {
        exit(-1);
    }

    if (get_agv_info(sock) != 0) {
        send_disconnect_request(sock);
        exit(-1);
    }

    for (;;) {
        send_status();
        sleep(1);
    }

    send_disconnect_request(sock);

    pthread_exit((void*) NULL);
}