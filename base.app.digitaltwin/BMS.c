/**
 * Must have a function to charge and another to discharge battery (may be linear).
 * Must signal Control System when minBat is reached (to go into CHARGING mode).
 * Must signal Control System when maxBat is reached (to go into FREE/SERVINGORDER mode).
 *
 * read from shared memory: properties.max_battery
 * write to shared memory: nothing
 *
 * note: define signal codes and respective actions in config.h (as a comment).
 */

#include "config.h"

shared_data *data;

void chargeBattery(BMS *bms, char old_state){
	strcpy(data->status.status, CHARGING);

	while(bms->currBat < data->properties.max_battery){
		sleep(1);
		bms->currBat++;

		printf("Current Battery: %d%%\n", bms->currBat);
	}

	if (old_state) {
		strcpy(data->status.status, SERVING_ORDER);	
	} else {
		strcpy(data->status.status, FREE);	
	}
}


//verificar quando deve ir para dock
/*
 * speed -> battery
 *
 * 10 speed -> 1% battery
 * current speed -> x
 */


void* bms_thread(void *arg){
    int size = sizeof(shared_data);

	int fd = shm_open("/digital_twin_shared_mem", O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);
    ftruncate(fd, size);
    data = (shared_data *) mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
	
	BMS bms;

	bms.currBat = data->properties.max_battery;
	bms.minBat = 15;

	while(1) {
		while(bms.currBat > bms.minBat){
			bms.currBat--;
			sleep(1);
			printf("Current Battery: %d%%\n", bms.currBat);

		}

		if (!strcmp(data->status.status, FREE)) {
			chargeBattery(&bms, 0);
		} else {
			chargeBattery(&bms, 1);
		}

	}
}