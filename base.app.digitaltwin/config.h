#ifndef CONFIG
#define CONFIG
    #include <errno.h>
    #include <fcntl.h>
    #include <semaphore.h>
    #include <stdio.h>
    #include <stdlib.h>
    #include <string.h>
    #include <sys/mman.h>
    #include <sys/stat.h>
    #include <sys/types.h>
    #include <sys/wait.h>
    #include <sys/socket.h>
    #include <netinet/in.h>
    #include <arpa/inet.h>
    #include <netdb.h>
    #include <time.h>
    #include <unistd.h>
    #include <pthread.h>
    #include <signal.h>

    #define FREE "Free"
    #define SERVING_ORDER "Serving Given Order"
    #define CHARGING "Charging"

    #define UP 0
    #define RIGHT 1
    #define DOWN 2
    #define LEFT 3

    #define SIMULATION_ENGINE_PORT "9999"
    #define SIMULATION_ENGINE_IP "127.0.0.1"
    #define SENSOR_REQUEST 11
    #define POSITIONING_REQUEST 22
    
    //used to access shared memory
    extern pthread_mutex_t shared_data_access;
    
    //used to enter a state where it is reading a new task
    extern pthread_mutex_t new_task;
    
    //used to start the positioning changing
    extern pthread_mutex_t change_position;
    
    //used to reroute the agv
    extern pthread_mutex_t calculate_route;

    //used to make the program wait to get the AGV Info
    extern pthread_mutex_t loading_info;

    typedef struct {
        int x;
        int y;
    } Coordinate;

    typedef struct {
        char status[20];
        Coordinate position;
    } Status;

    typedef struct {
        Coordinate coordinate;
        int direction;
    } Position;

    typedef struct {
        Coordinate dock;
        char id[6];
        int speed;//Square/second
        int max_battery; //Autonomy
    } AGV_Info;

    typedef struct {
        int currBat;
        int minBat; //Default = 15%
    } BMS;

    typedef struct {
        int* plant;
        int x_size;
        int y_size;
    } Warehouse;

    typedef struct {
        AGV_Info properties;
        Status status;
        Position curr_position;
        Coordinate* route;
        Coordinate task;
        Warehouse warehouse;
    } shared_data;

    typedef struct {
        char agv_id[6];
        char index;
    } sensor_id;

#endif
