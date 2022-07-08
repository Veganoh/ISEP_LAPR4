#include "config.h"
#include "simulation_engine_config.h"

/**
 * Matrix that stores the position of each sensor given any direction the AGV is facing.
 * Line indexes correspond to: 0-UP, 1-RIGHT, 2-DOWN, 3-LEFT
 * Column indexes correpond to variable <sensor_dir>
 * 
 * Sensor position for each direction:
 *    UP      RIGHT      DOWN     LEFT
 * 7  0  1   5  6  7   3  4  5   1  2  3
 *  \ | /     \ | /     \ | /     \ | /
 *6 -   - 2 4 -   - 0 2 -   - 6 0 -   - 4
 *  / | \     / | \     / | \     / | \
 * 5  4  3   3  2  1   1  0  7   7  6  5
 */
int dir_helper[4][8] = {
                        {5,6,7,0,1,2,3,4},
                        {3,4,5,6,7,0,1,2},
                        {1,2,3,4,5,6,7,0},
                        {7,0,1,2,3,4,5,6}
                        };
int curr_plant[18][20];

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
                        {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0}}; //read-only

//Shared memory variables
sem_t* agv_lst_sem;
sem_t* sensors_sem;
AGV_shm* agv_shm;
Sensors_shm* sensors_shm;

/**
 * Goes through every AGV position and detects any possible collisions with obstacles up to 2 squares.
 * 
 * for agv in agv_lst
 *      --go through layer 2 and 1 of adjacent squares
 *      for i=2, i >=1, i++
 *          int sensor_dir=0 --absolute direction in warehouse plant 0=N, 1=NE, 2=E, 3=SE, 4=S, 5=SW, 6=W, 7=NW
 *          if curr_plant[agv.position.x][agv.position.y + i] == OBSTACLE
 *              sensors[agv.id][dir_helper[agv.direction][sensor_dir]] = i else sensors[agv.id][dir_helper[agv.direction][sensor_dir]] = 0
 * 
 *          sensor_dir=1
 *          if curr_plant[agv.position.x + i][agv.position.y + i] == OBSTACLE
 *              sensors[agv.id][dir_helper[agv.direction][sensor_dir]] = i else sensors[agv.id][dir_helper[agv.direction][sensor_dir]] = 0
 *           
 *            (...)
 */
void detect_collision() {
    int i;
    for (i=0; i<agv_shm->size; i++) {
        printf("Detecting collisions of AGV %s\n", agv_shm->agv_lst[i].id);
        //go through layer 2 and 1 of adjacent squares
        for(int sq=2; sq>=1; sq--) {
            int sensor_dir=0; //absolute direction in warehouse plant 3=N, 4=NE, 5=E, 6=SE, 7=S, 0=SW, 1=W, 2=NW
            for(int x=-1;x<=1;x++) {
                for(int y=-1;y<=1;y++) {
                   if(x == 0 && y == 0) continue;
                   if (curr_plant[agv_shm->agv_lst[i].position.coordinate.x + x*sq][agv_shm->agv_lst[i].position.coordinate.y + y*sq] == OBSTACLE) {
                        printf("Collision detected at (%d,%d)\n", agv_shm->agv_lst[i].position.coordinate.x + x, agv_shm->agv_lst[i].position.coordinate.y + y);
                        sensors_shm->sensors[agv_shm->agv_lst[i].index][dir_helper[agv_shm->agv_lst[i].position.direction][sensor_dir]] = sq;
                   } else {
                        sensors_shm->sensors[agv_shm->agv_lst[i].index][dir_helper[agv_shm->agv_lst[i].position.direction][sensor_dir]] = 0;
                   }
                   sensor_dir++;
                }
            }
        }
    }
    
    //unlock sensors access
    sem_post(sensors_sem);
    //unlock agv_lst access
	sem_post(agv_lst_sem);
}

/**
 * Makes a copy of the warehouse plant (fixed obstacles) and adds all AGV positions as obstacles as well
 * 
 * curr_plant = copy_array(warehouse.plant)
 * 
 * for agv in agv_lst
 *  --add each agv position to plant
 *      curr_plant[agv.position.x][agv.position.y] = OBSTACLE
 */
void update_plant() {  
    printf("Updating plant...\n");
    memcpy(curr_plant, plant, 18*20*sizeof(int));
    //lock agv_lst access to read
	sem_wait(agv_lst_sem);
    //lock sensors to write
    sem_wait(sensors_sem);
    for (int i=0; i<agv_shm->size; i++) {
        //add each agv position to plant
        curr_plant[agv_shm->agv_lst[i].position.coordinate.x][agv_shm->agv_lst[i].position.coordinate.y] = OBSTACLE;
        printf("Added obstacle AGV at (%d,%d)\n", agv_shm->agv_lst[i].position.coordinate.x, agv_shm->agv_lst[i].position.coordinate.y);
    }
    detect_collision();
}

void startup_collision_detection() {
    setup_shared_memory();
    setup_synchronization();

    do {
        update_plant();
        sleep(5);
    } while(1);
}

