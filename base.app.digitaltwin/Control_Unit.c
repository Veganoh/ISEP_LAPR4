#include "config.h"
#include "communication_config.h"
#include "simulation_engine_config.h"
#include "threads.h"

#define SENSORS 8
#define THREADS_NUM 4

//used to generate sensors
pthread_t sensors[SENSORS];
int args[SENSORS];
//used to startup positioning module
pthread_t positioning[1];

//used to access shared memory
pthread_mutex_t shared_data_access;

//used to enter a state where it is reading a new task
pthread_mutex_t new_task;

//used to start the positioning changing
pthread_mutex_t change_position;

//used to reroute the agv
pthread_mutex_t calculate_route;

//used to stop the program untill the AGV Info is received
pthread_mutex_t loading_info;


void handle_sigusr1(int sig) {
    printf("Stopping...\n");
}

void handle_sigusr2(int sig) {
    printf("Slowing down...\n");
}

void create_positioning_thread() {
    //Create thread for positioning module
    if(pthread_create(&positioning[0], NULL, thread_positioning, NULL)) {
        perror("Error creating thread");
    }
}

void close_positioning_thread() {
    if (pthread_join(positioning[0], NULL)) {
            perror("Failed to join thread"); }
}


void create_sensor_threads() {
    //Create threads to act as sensors
    for (int i = 0; i < SENSORS; i++){
        args[i] = i;

        if(pthread_create(&sensors[i], NULL, thread_sensor, (void*)&args[i]))
             perror("Error creating thread.");
    }
}


void close_sensor_threads() {
    //Wait for all threads to be finished
    for (int i = 0; i < SENSORS; i++)
        if (pthread_join(sensors[i], NULL))
            perror("Failed to join thread");
}

int main() {
    shm_unlink("/digital_twin_shared_mem");
    sem_unlink("sem_calculate_route");
    sem_unlink("sem_assign_task");
    sem_unlink("sem_shared_memory");

    //signal handling
    signal(SIGUSR1, handle_sigusr1);
    signal(SIGUSR2, handle_sigusr2);

    pthread_t threads[THREADS_NUM];

	//shared memory
    shared_data *data;
    int size = sizeof(shared_data);

    int fd = shm_open("/digital_twin_shared_mem", O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);
    ftruncate(fd, size);
    data = (shared_data *) mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);

    sem_t *sem_calculate_route;
    if((sem_calculate_route = sem_open("sem_calculate_route", O_CREAT|O_EXCL, 0644, 0)) == SEM_FAILED){
        perror("Error in sem_open()");
        exit(EXIT_FAILURE);
    }

    sem_t *sem_assign_task;
    if((sem_assign_task = sem_open("sem_assign_task", O_CREAT|O_EXCL, 0644, 0)) == SEM_FAILED){
        perror("Error in sem_open()");
        exit(EXIT_FAILURE);
    }

    sem_t *sem_shared_memory;
    if((sem_shared_memory = sem_open("sem_shared_memory", O_CREAT|O_EXCL, 0644, 1)) == SEM_FAILED){
        perror("Error in sem_open()");
        exit(EXIT_FAILURE);
    }
    
    int plant[18][20] = {{0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
                        {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
                        {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
                        {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0}};

	data->warehouse.plant = &plant[0][0];
    data->warehouse.x_size = 20;
    data->warehouse.y_size = 18;
	data->task.x = -1;
	data->task.y = -1;
	strcpy(data->status.status, FREE);
	data->route = NULL;

	//initialize all mutex variables and block those that should be blocked
	pthread_mutex_init(&shared_data_access, NULL);
	pthread_mutex_init(&new_task, NULL);
	pthread_mutex_init(&change_position, NULL);
	pthread_mutex_init(&calculate_route, NULL);
	pthread_mutex_init(&loading_info, NULL);

	pthread_mutex_lock(&calculate_route);
	pthread_mutex_lock(&change_position);
	pthread_mutex_lock(&new_task);
	pthread_mutex_lock(&loading_info);

    pthread_create(&threads[0], NULL, thread_client, NULL);
    pthread_mutex_lock(&loading_info);

    pthread_create(&threads[1], NULL, thread_server, NULL);
    pthread_create(&threads[2], NULL, route_thread, NULL);
    pthread_create(&threads[3], NULL, bms_thread, NULL);

    //threads
    create_positioning_thread();
    create_sensor_threads();
    
    for(;;) {
        update_simulation_engine(data->curr_position, data->properties.id);
        char c = getchar();

        pthread_mutex_lock(&shared_data_access);
        sem_wait(sem_shared_memory);

        switch(c) {
            case 'a':
                data->curr_position.coordinate.x -= 1;
                break;
            case 'd':
                data->curr_position.coordinate.x += 1;
                break;
            case 's':
                data->curr_position.coordinate.y += 1;
                break;
            case 'w':
                data->curr_position.coordinate.y -= 1;
                break;
            case '\n': break;
            default:
                printf("Invalid input\n");
                break;
        }

        sem_post(sem_shared_memory);
        pthread_mutex_unlock(&shared_data_access);
    }

    close_sensor_threads();
    close_positioning_thread();

    return 0;
}