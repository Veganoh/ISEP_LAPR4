#include "config.h"
#include "simulation_engine_config.h"

int agv_shm_fd, agv_shm_size = sizeof(AGV_shm), sensors_shm_fd, sensors_shm_size = sizeof(Sensors_shm);
AGV_shm* agv_shm;
Sensors_shm* sensors_shm;
sem_t* agv_lst_sem;
sem_t* sensors_sem;

void setup_agv_lst() {
    shm_unlink("/agv_shm");
    //Creates shared memory area
    if ((agv_shm_fd = shm_open("/agv_shm", O_CREAT|O_EXCL|O_RDWR, S_IRUSR|S_IWUSR)) < 0)
        perror("Failed to create shared memory");
    //Defines memory size
    if (ftruncate (agv_shm_fd, agv_shm_size) < 0)
        perror("Failed to adjust memory size");
    //Maps the area in an address
    agv_shm = (AGV_shm *) mmap(NULL, agv_shm_size, PROT_READ|PROT_WRITE, MAP_SHARED, agv_shm_fd, 0);
    if (agv_shm == NULL) {
        perror("Failed to map the area");
        exit(0);
    }
    //Initializes the shared memory
    agv_shm->size = 0;

    //Creates a semaphore to synchronize read/write
    sem_unlink("agv_lst_sem");
    if ((agv_lst_sem = sem_open("agv_lst_sem", O_CREAT | O_EXCL, 0644, 1)) == SEM_FAILED) {
        perror("Error in opening agv_lst semaphore.");
        exit(1);
    }
}

void setup_sensors() {
     shm_unlink("/sensors_shm");
    //Creates shared memory area
    if ((sensors_shm_fd = shm_open("/sensors_shm", O_CREAT|O_EXCL|O_RDWR, S_IRUSR|S_IWUSR)) < 0)
        perror("Failed to create shared memory");
    //Defines memory size
    if (ftruncate (sensors_shm_fd, sensors_shm_size) < 0)
        perror("Failed to adjust memory size");
    //Maps the area in an address
    sensors_shm = (Sensors_shm *) mmap(NULL, sensors_shm_size, PROT_READ|PROT_WRITE, MAP_SHARED, sensors_shm_fd, 0);
    if (sensors_shm == NULL) {
        perror("Failed to map the area");
        exit(0);
    }
    //Initializes the shared memory
    char sensors_init[MAX_AGV][MAX_SENSORS] = {{0,0,0,0,0,0,0,0},
                                     {0,0,0,0,0,0,0,0},
                                     {0,0,0,0,0,0,0,0},
                                     {0,0,0,0,0,0,0,0},
                                     {0,0,0,0,0,0,0,0},
                                     {0,0,0,0,0,0,0,0}};
    memcpy(sensors_shm->sensors, sensors_init, MAX_AGV*MAX_SENSORS*sizeof(char));
    //Creates a semaphore to synchronize read/write
    sem_unlink("sensors_sem");
    if ((sensors_sem = sem_open("sensors_sem", O_CREAT | O_EXCL, 0644, 1)) == SEM_FAILED) {
        perror("Error in opening sensors semaphore.");
        exit(1);
    }
}


int main() {
    setup_agv_lst();
    setup_sensors();
    
    pid_t p = fork();
    if (p == 0) { // Filho
        startup_collision_detection();
    } else { // Pai
        startup_server();
    }
    
    wait(NULL);

    sem_unlink("agv_lst_sem");
    sem_unlink("sensors_sem");
    close(agv_shm_fd);
    close(sensors_shm_fd);
    return 0;
}

void setup_shared_memory() {
	int agv_shm_fd, agv_shm_size = sizeof(AGV_shm), sensors_shm_fd, sensors_shm_size = sizeof(Sensors_shm);
	
	//Creates shared memory area
    if ((agv_shm_fd = shm_open("/agv_shm", O_RDWR, S_IRUSR|S_IWUSR)) < 0) {
        perror("Failed to create shared memory");}
	if ((sensors_shm_fd = shm_open("/sensors_shm", O_CREAT|O_RDWR, S_IRUSR|S_IWUSR)) < 0){
	perror("Failed to create shared memory");}
    //Defines memory size
    if (ftruncate (agv_shm_fd, agv_shm_size) < 0){
        perror("Failed to adjust memory size");}
	if (ftruncate (sensors_shm_fd, sensors_shm_size) < 0){
        perror("Failed to adjust memory size");}
    //Maps the area in an address
    agv_shm = (AGV_shm *) mmap(NULL, agv_shm_size, PROT_READ|PROT_WRITE, MAP_SHARED, agv_shm_fd, 0);
    if (agv_shm == NULL) {
        perror("Failed to map the area");
        exit(0);
    }
	sensors_shm = (Sensors_shm *) mmap(NULL, sensors_shm_size, PROT_READ|PROT_WRITE, MAP_SHARED, sensors_shm_fd, 0);
    if (sensors_shm == NULL) {
        perror("Failed to map the area");
        exit(0);
    }
}

void setup_synchronization() {
	//Creates a semaphore to synchronize read/write
    if ((agv_lst_sem = sem_open("agv_lst_sem", O_EXCL, 0644, 1)) == SEM_FAILED) {
        perror("Error in opening agv_lst semaphore.");
        exit(1);
    }
	//Creates a semaphore to synchronize read/write
    if ((sensors_sem = sem_open("sensors_sem", O_EXCL, 0644, 1)) == SEM_FAILED) {
        perror("Error in opening sensors semaphore.");
        exit(1);
    }
}