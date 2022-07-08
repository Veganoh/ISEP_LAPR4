client:
	gcc -Wall Communications_Client.c cJson/cJson.c -o client -lssl -lcrypto

server:
	gcc -Wall Communications_Server.c -o server -lssl -lcrypto

sensor_client:
	gcc -Wall Sensor.c -o sensor_client

position_client:
	gcc -Wall Positioning.c -o position_client

simulation_server:
	gcc -Wall Simulation_Engine.c Simulation_Engine_Server.c Simulation_Engine_Collisions.c Socket_Utils.c -o simulation_server -lpthread -lrt

run_client: client
	./client $(ip)

run_server: server
	./server

run_sensor_client: sensor_client
	./sensor_client

run_position_client: position_client
	./position_client

run_simulation_server: simulation_server
	./simulation_server

digital_twin:
	gcc -Wall Control_Unit.c cJson/cJson.c BMS.c Packet_Utils.c Socket_Utils.c Communications_Client.c Communications_Server.c Positioning.c Route_Planner.c Sensor.c Simulation_Engine_Client.c -o digital_twin -lssl -lcrypto -lpthread -lrt

run_digital_twin: digital_twin
	./digital_twin

clean:
	rm server client sensor_client position_client simulation_server digital_twin