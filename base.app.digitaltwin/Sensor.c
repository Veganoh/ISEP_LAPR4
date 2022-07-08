#include "config.h"
#include "simulation_engine_config.h"
#include "communication_config.h"
#include "Socket_Utils.h"

char id;
shared_data *data;

/**
 * Sends a request to Simulation Engine in order to obtain sensor status.
 * 0 - no obstacles
 * 1 - obstacle 1 square away
 * 2 - obstacle 2 squares away
 * 
 * write(agv_id, id)
 * 
 * read() --> status
 * 
 * return status;
 * 
 * @return char 
 */
char update() {
    int sock = establishConnection(SIMULATION_ENGINE_IP);

    write_byte(sock, SENSOR_REQUEST);

    write_str(sock, 6, data->properties.id);

    write_byte(sock, id);

    char status;
    read(sock, &status, 1);
    printf("RECEIVING STATUS %d FROM SERVER\n", status);
    
    close_connection(sock);

    return status;
}

void send_stop_signal() {
    printf("Signaling AGV to stop\n");
    raise(SIGUSR1);
}

void send_slow_signal() {
    printf("Signaling AGV to slow down\n");
    raise(SIGUSR2);
}

void* thread_sensor(void *arg) {
    //Open shared data
    int size = sizeof(shared_data);

    int fd = shm_open("/digital_twin_shared_mem", O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);
    ftruncate(fd, size);
    data = (shared_data *) mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);

    //Initialise sensor
    id = *(int*)arg;
    printf("Initiating sensor %d...\n", id);
    
    while(1) {
        int status = update();
        if (status == 1) send_stop_signal();
        else if (status == 2) send_slow_signal();
        
        sleep(1);
    }
}



