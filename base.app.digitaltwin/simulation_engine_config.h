
#ifndef SIMULATION_CONFIG
#define SIMULATION_CONFIG
    #define MAX_AGV 6
    #define MAX_SENSORS 8
    #define FREE_SPACE 0
    #define OBSTACLE 1


    typedef struct {
        char id[6];
        Position position;
        int index;
    }AGV;

    typedef struct {
    AGV agv_lst[MAX_AGV]; //protected-write-read
    int size;
    } AGV_shm;

    typedef struct {
        char sensors[MAX_AGV][MAX_SENSORS]; //protected-write-read
    } Sensors_shm;


    void setup_synchronization();
    void setup_shared_memory();
    void startup_server();
    void startup_collision_detection();

    int establishConnection(char* ip);
    void close_connection(int sock);
#endif