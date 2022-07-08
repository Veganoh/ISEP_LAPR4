/**
 *  Lê a next route coordinate, calcula a direção a partir disso (por exemplo se a next square tiver um x, por exemplo -1, diferente 
 * da current square então a direção é LEFT). Calcula também qual a square em que se encontra dependendo da velocidade, por exemplo, se
 * a velocidade for 1 square por segundo, demora 1 segundo a passar para a próxima square.
 * 
 * read from shared memory: route (check if null --> don't move), properties.speed, curr_position
 * write to shared memory: curr_position
 * 
 * note: curr_position.direction is UP, RIGHT, DOWN, LEFT as defined in config.h
 */

#include "config.h"
#include "simulation_engine_config.h"
#include "communication_config.h"
#include "Socket_Utils.h"

Coordinate get_next_coordinate(Coordinate* route, Coordinate coordinate);

int equals_coordinate(Coordinate c1, Coordinate c2);

int miliseconds_travel_square(int speed);

int x_changed(Coordinate c1, Coordinate c2);

int y_changed(Coordinate c1, Coordinate c2);

/**
 * Sends current AGV position to Simulation Engine server
 */
void update_simulation_engine(Position p, char* agv_id) {
    int sock = establishConnection(SIMULATION_ENGINE_IP);

	write_byte(sock, POSITIONING_REQUEST);
    write_str(sock, 6, agv_id);
    write_int(sock, p.coordinate.x);
    write_int(sock, p.coordinate.y);
    write_int(sock, p.direction);
    
    close_connection(sock);
}

void* thread_positioning(void *arg){
	shared_data *data;
    int size = sizeof(shared_data);

    int fd = shm_open("/digital_twin_shared_mem", O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);
    ftruncate(fd, size);
    data = (shared_data *) mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
	
	sem_t *sem_assign_task;

	if((sem_assign_task = sem_open("sem_assign_task", O_CREAT, 0644, 0)) == SEM_FAILED){
		perror("Error in sem_open()");
		exit(EXIT_FAILURE);
	}

	sem_t *sem_shared_memory;

    if((sem_shared_memory = sem_open("sem_shared_memory", O_CREAT, 0644, 1)) == SEM_FAILED){
        perror("Error in sem_open()");
        exit(EXIT_FAILURE);
    }
	
	Position curr_position;
	Position next_position;
	
	
	int speed,miliseconds_passed = 0;
	Coordinate* route;
	Coordinate curr_coordinate;
	Coordinate next_coordinate;
	Coordinate final_coordinate;
	
	
	while(1){
		pthread_mutex_lock(&change_position);
        pthread_mutex_lock(&shared_data_access);
		sem_wait(sem_shared_memory);
		
		speed = data->properties.speed;
		route = data->route;
		curr_position = data->curr_position;
		final_coordinate = data->task;

		if(route != NULL){
			curr_coordinate = curr_position.coordinate;
			if((!equals_coordinate(curr_coordinate, final_coordinate))){
				if(miliseconds_passed > miliseconds_travel_square(speed)){
					next_coordinate = get_next_coordinate(route,curr_coordinate);

					next_coordinate.x++;
					next_coordinate.y++;

					next_position.coordinate = next_coordinate;

					if(x_changed(curr_coordinate,next_coordinate)){
						if(curr_coordinate.x > next_coordinate.x){
							next_position.direction = 3;
						}else{
							next_position.direction = 1;
						}
					}else if(y_changed(curr_coordinate,next_coordinate)){
						if(curr_coordinate.y > next_coordinate.y){
							next_position.direction = 2;
						}else{
							next_position.direction = 0;
						}
					}		

					// escrever na memória partilhada
					update_simulation_engine(next_position, data->properties.id);
					miliseconds_passed = 0;
					data->curr_position = next_position;
				}
			} else {
				data->route = NULL;
				sem_post(sem_assign_task);
			}
		}

		sem_post(sem_shared_memory);
		pthread_mutex_unlock(&shared_data_access);
		pthread_mutex_unlock(&change_position);
		miliseconds_passed += 1000;
		sleep(1);
	}
	pthread_exit(NULL);
}

Coordinate get_next_coordinate(Coordinate* route, Coordinate coordinate){
	int i = 0;
	Coordinate actualCoordinate;
	
	while(1){
		actualCoordinate = *(route+i);
		
		if(((actualCoordinate.x + 1) == coordinate.x) && ((actualCoordinate.y + 1) == coordinate.y)){
			return *(route+i+1);
		}
		i++;
	}
}

int equals_coordinate(Coordinate c1, Coordinate c2){
	
	if(c1.x != c2.x) return 0;
	
	else if (c1.y != c2.y) return 0;
	
	return 1;
}

int miliseconds_travel_square(int speed){
	return (1/speed)*1000;
}

int x_changed(Coordinate c1, Coordinate c2){
	if(c1.x != c2.x) return 1;

	return 0;
}

int y_changed(Coordinate c1, Coordinate c2){
	if(c1.y != c2.y) return 1;

	return 0;
}


