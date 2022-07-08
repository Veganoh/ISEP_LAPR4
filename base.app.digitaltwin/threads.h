#ifndef THREADS
#define THREADS

    void* thread_client(void *arg);

    void* thread_server(void *arg);

    void* thread_sensor(void *arg);

    void* thread_positioning(void *arg);

    void* route_thread(void *arg);

    void* bms_thread(void *arg);
#endif