/**
 * This file serves to check weather or not a path has been attributed to the agv yet and if not creates a new path using dijkstra's algorithm
 * It uses 2 semaphores to control when to be run and when it can access the shared memory
 */

#include "config.h"

//visits the warehouse in the x and y coordinate
int visit_warehouse(Warehouse warehouse, int x, int y){
    return warehouse.plant[y * warehouse.x_size + x];
}

//fill the adj pointer with all the adjacent coordinates and return an int with the number of adjacent coordinates
int get_adj(Warehouse warehouse, Coordinate initial, Coordinate *adj){
    int number = 0;
    if(initial.y + 1 < warehouse.y_size && visit_warehouse(warehouse, initial.x, initial.y + 1) != 1){
        Coordinate new_coord;
        new_coord.y = initial.y + 1;
        new_coord.x = initial.x;
        *(adj+number) = new_coord;
        number++;
    }
    if(initial.y - 1 >= 0 && visit_warehouse(warehouse, initial.x, initial.y - 1) != 1){
        Coordinate new_coord;
        new_coord.y = initial.y - 1;
        new_coord.x = initial.x;
        *(adj+number) = new_coord;
        number++;
    }
    if(initial.x + 1 < warehouse.x_size && visit_warehouse(warehouse, initial.x+1, initial.y) != 1){
        Coordinate new_coord;
        new_coord.y = initial.y;
        new_coord.x = initial.x + 1;
        *(adj+number) = new_coord;
        number++;
    }
    if(initial.x - 1 >= 0 && visit_warehouse(warehouse, initial.x-1, initial.y) != 1){
        Coordinate new_coord;
        new_coord.y = initial.y;
        new_coord.x = initial.x - 1;
        *(adj+number) = new_coord;
        number++;
    }
    return number;
}

// uses the dijkstra's algorithm to find the closest path to every point in the warehouse filling the dist pointer
// with the minimum distances and the path array with the last vertice used to get to said path
void dijkstra(Warehouse warehouse, Coordinate initial, Coordinate path[], int *dist){
    int visited[warehouse.x_size * warehouse.y_size];

    for (int i = 0; i < warehouse.x_size * warehouse.y_size; ++i) {
        visited[i] = 0;
        dist[i] = 0;
    }

    dist[visit_warehouse(warehouse, initial.x, initial.y)] = 0;

    while (initial.x != -1 && initial.y != -1){

        int initial_index = initial.y * warehouse.x_size + initial.x;
        visited[initial_index] = 1;

        Coordinate adj[4];
        for (int i = 0; i < get_adj(warehouse, initial, adj); ++i) {
            int adj_index = adj[i].y * warehouse.x_size + adj[i].x;
            if(!visited[adj_index] && (dist[adj_index] == 0 || dist[adj_index] > dist[initial_index] + 1 )){
                dist[adj_index] = dist[initial_index] + 1;
                Coordinate temp;
                temp.y = initial.y;
                temp.x = initial.x;
                path[adj_index] = temp;

            }
        }

        initial.x = -1;
        initial.y = -1;

        int min_dist = 0;

        for (int i = 0; i < warehouse.x_size * warehouse.y_size; ++i) {
            if(!visited[i] && dist[i] != 0 && (min_dist == 0 || min_dist > dist[i])){
                initial.x = i % warehouse.x_size;
                initial.y = i / warehouse.x_size;
                min_dist = dist[i];
            }
        }

    }
}

//Uses the path array to calculate the array of squares that need to be used for the minimum path possible
void get_path(Warehouse warehouse, Coordinate initial, Coordinate target, Coordinate paths[], Coordinate *path, int size){
    if(initial.x == target.x && initial.y == target.y){
        *(path+size) = initial;
    } else {
        *(path+size) = target;
        target = paths[target.y * warehouse.x_size + target.x];

        size--;
        get_path(warehouse, initial, target, paths, path, size);
    }
}

//Calls the other functions to reproduce the best possible path to the destiny
int shortest_path(Warehouse warehouse, Coordinate initial, Coordinate target, Coordinate *path) {
    Coordinate paths[warehouse.x_size * warehouse.y_size];
    int dist[warehouse.x_size * warehouse.y_size];
    dijkstra(warehouse, initial, paths, dist);
    if (dist[target.y * warehouse.x_size + target.x]){
        int size = dist[target.y * warehouse.x_size + target.x];
        get_path(warehouse, initial, target, paths, path, size);
        return size + 1;
    }

    return -1;
}

// Thread function that repopulates the shared memory when needed
// Wait for a semaphores telling it to compute a new route and opens the semaphore that make the position module work
void* route_thread(void *arg){
    sem_t *sem_calculate_route;

    if((sem_calculate_route = sem_open("sem_calculate_route", O_CREAT, 0644, 0)) == SEM_FAILED){
        perror("Error in sem_open()");
        exit(EXIT_FAILURE);
    }

    shared_data *data;
    int size = sizeof(shared_data);

    int fd = shm_open("/digital_twin_shared_mem", O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);
    ftruncate(fd, size);
    data = (shared_data *) mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);

    sem_t *sem_shared_memory;

    if((sem_shared_memory = sem_open("sem_shared_memory", O_CREAT, 0644, 1)) == SEM_FAILED){
        perror("Error in sem_open()");
        exit(EXIT_FAILURE);
    }


    while(1){
        sem_wait(sem_calculate_route);
        pthread_mutex_lock(&shared_data_access);
        sem_wait(sem_shared_memory);


        if(data->route == NULL){
            //copies the data to not damage the integrity of the shared data type
            Coordinate target;
            target.x = data->task.x - 1;
            target.y = data->task.y - 1;

            Coordinate initial;
            initial.x = data->curr_position.coordinate.x - 1;
            initial.y = data->curr_position.coordinate.y - 1;

            Coordinate path[data->warehouse.x_size * data->warehouse.y_size];

            shortest_path(data->warehouse, initial, target, path);
            
            data->route = path;
        }
        
        sem_post(sem_shared_memory);
        pthread_mutex_unlock(&shared_data_access);
        pthread_mutex_unlock(&change_position);
    }

    pthread_exit(NULL);
}
