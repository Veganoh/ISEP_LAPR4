#include "config.h"
#include "simulation_engine_config.h" 
#include "Socket_Utils.h"

#define BUF_SIZE 300

//Shared memory variables
sem_t* agv_lst_sem;
sem_t* sensors_sem;
AGV_shm* agv_shm;
Sensors_shm* sensors_shm;

char process_sensor_request(char* agv_id, char sensor_id) {
	//read from shared memory
	sem_wait(agv_lst_sem);

	int agv_idx;
	for (int i=0;i<agv_shm->size;i++) {
		if(strcmp(agv_id, agv_shm->agv_lst[i].id) == 0) agv_idx = agv_shm->agv_lst[i].index;
	}

	//unblock shared memory
	sem_post(agv_lst_sem);
	
	//read from shared memory
	sem_wait(sensors_sem);

	char status = sensors_shm->sensors[agv_idx][(int) sensor_id];

	//unblock shared memory
	sem_post(sensors_sem);

	return status;
}

void write_sensor_reply(int sock, char status) {
    write(sock, &status, 1);
}

/**
 * Reads sensor request and replies with obstacle status.
 * 0 - no obstacles
 * 1 - obstacle at 1 square distance
 * 2 - obstacle at 2 squares distance
 */
void read_sensor_request(int sock) {
	char agv_id[6];	read_str(sock, 6, agv_id);

	char sensor_id = read_byte(sock);

	printf("RECEIVING REQUEST FROM SENSOR (%s, %d)...\n", agv_id, sensor_id);

	char status = process_sensor_request(agv_id, sensor_id);
	write_sensor_reply(sock, status);
}

/**
* Updates the information in global variable <agv_lst>
*
* for i = 0, i < MAX_AGV, i++
*  --doesn't exist --> create 
*     if agv_lst[i] == null
*          agv a;
*          a.id = id;
*          a.position = position;
*          a.index = i;
*          return;
*  --check if exists --> update
*      if agv_lst[i].id == id
*          agv_lst[i].position = position;
*          return; 
*/
void update_agv_position(char* agv_id, int x, int y, int direction) {
	//lock agv_lst access to write on it
	sem_wait(agv_lst_sem);

	int i;
	for(i=0;i<agv_shm->size;i++) {
		//exists --> update
		if(strcmp(agv_shm->agv_lst[i].id,agv_id) == 0) {
			printf("Rewriting position of AGV (%s)\n", agv_shm->agv_lst[i].id);
			agv_shm->agv_lst[i].position.coordinate.x = x;
			agv_shm->agv_lst[i].position.coordinate.y = y;
			agv_shm->agv_lst[i].position.direction = direction;
			printf("%d: {agv_id:%s; position:(%d,%d); direction:%d}\n", agv_shm->agv_lst[i].index, agv_shm->agv_lst[i].id, agv_shm->agv_lst[i].position.coordinate.x, agv_shm->agv_lst[i].position.coordinate.y, agv_shm->agv_lst[i].position.direction);
			break;
		}
	}
	//doesn't exist --> create
	if(i == agv_shm->size){
		printf("Creating AGV %s in index %d\n", agv_id, i);
		AGV a;
		strcpy(a.id,agv_id);
		a.position.coordinate.x = x;
		a.position.coordinate.y = y;
		a.position.direction = direction;
		a.index = i;

		agv_shm->agv_lst[i] = a;
		agv_shm->size++;

		printf("%d: {agv_id:%s; position:(%d,%d); direction:%d}\n", agv_shm->agv_lst[i].index, agv_shm->agv_lst[i].id, agv_shm->agv_lst[i].position.coordinate.x, agv_shm->agv_lst[i].position.coordinate.y, agv_shm->agv_lst[i].position.direction);
		} 			

	//unlock agv_lst access
	sem_post(agv_lst_sem);
}

/**
 * Reads AGV position from Positioning module
 * Updates/creates the information in variable <agv_lst>
 */
void read_agv_position(int sock) {
	char agv_id[6];	read_str(sock, 6, agv_id);
	int x = read_int(sock);
	int y = read_int(sock);
	int direction = read_int(sock);

	printf("RECEIVING POSITION OF %s (%d, %d) --> DIRECTION: %d\n", agv_id, x, y, direction);

	update_agv_position(agv_id, x, y, direction);
}

void startup_server() {
	setup_shared_memory();
	setup_synchronization();

    struct sockaddr_storage from;
	int err, newSock, sock;
	unsigned int adl;
	char cliIPtext[BUF_SIZE], cliPortText[BUF_SIZE];
	struct addrinfo  req, *list;

	bzero((char *)&req,sizeof(req));
	// requesting a IPv6 local address will allow both IPv4 and IPv6 clients to use it
	req.ai_family = AF_INET6;
	req.ai_socktype = SOCK_STREAM;
	req.ai_flags = AI_PASSIVE;      // local address

	err=getaddrinfo(NULL, SIMULATION_ENGINE_PORT , &req, &list);

	if(err) {
        	printf("Failed to get local address, error: %s\n",gai_strerror(err)); exit(1); }

	sock=socket(list->ai_family,list->ai_socktype,list->ai_protocol);
	if(sock==-1) {
        	perror("Failed to open socket"); freeaddrinfo(list); exit(1);}

	if(bind(sock,(struct sockaddr *)list->ai_addr, list->ai_addrlen)==-1) {
        	perror("Bind failed");close(sock);freeaddrinfo(list);exit(1);}

	freeaddrinfo(list);

	listen(sock,SOMAXCONN);

	puts("Accepting TCP connections (both over IPv6 or IPv4). Use CTRL+C to terminate the server");

	adl=sizeof(from);
	for(;;) {
        	newSock=accept(sock,(struct sockaddr *)&from,&adl);
        	if(!fork()) {
                	close(sock);
			getnameinfo((struct sockaddr *)&from,adl,cliIPtext,BUF_SIZE,cliPortText,BUF_SIZE,
				NI_NUMERICHOST|NI_NUMERICSERV);
                	printf("New connection from node %s, port number %s\n", cliIPtext, cliPortText);
					//-------------------------------------------------------------
					char request_code;
					read(newSock, &request_code, 1);

					if(request_code == POSITIONING_REQUEST) {read_agv_position(newSock);}
					if(request_code == SENSOR_REQUEST) {read_sensor_request(newSock);}
					//-------------------------------------------------------------
                	close(newSock);
                	printf("Connection from node %s, port number %s closed\n", cliIPtext, cliPortText);
                	exit(0);
                	}
        	close(newSock);
        	}
	close(sock);
    }



