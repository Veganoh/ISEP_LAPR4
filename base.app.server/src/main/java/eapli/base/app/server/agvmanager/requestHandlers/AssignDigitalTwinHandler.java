package eapli.base.app.server.agvmanager.requestHandlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.DTOs.AGVDigitalTwinDTO;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.app.server.agvmanager.AGVManagerRequestHandler;
import eapli.base.common.domain.PacketUtils;
import eapli.base.infrastructure.persistence.PersistenceContext;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

public class AssignDigitalTwinHandler implements Runnable{
    /**
     * The socket that connects the server
     */
    private Socket myS;

    /**
     * input and output streams to the remote server
     */
    private DataInputStream sIn;
    private final DataOutputStream sOut;

    private Map<String, String> digitalTwins;

    /**
     * The AGV Repository
     */
    AGVRepository agvRep = PersistenceContext.repositories().AGV();

    public AssignDigitalTwinHandler(Socket myS, DataInputStream sIn, DataOutputStream sOut, Map<String, String> digitalTwins) {
        this.myS = myS;
        this.sIn = sIn;
        this.sOut = sOut;
        this.digitalTwins = digitalTwins;
    }

    @Override
    public void run() {
        try {
            Iterable<AGV> agvIterable = agvRep.available();

            if (agvIterable == null || !agvIterable.iterator().hasNext()) {
                System.out.println("There are no available AGV in the system!");
                myS.close();
                System.exit(1);
            }

            AGV agv;
            boolean foundAGV = false;

            while (agvIterable.iterator().hasNext()) {
                agv = agvIterable.iterator().next();

                if (!digitalTwins.containsKey(agv.identity().toString())) {
                    digitalTwins.put(agv.identity().toString(), myS.getRemoteSocketAddress().toString());
                    System.out.println("Assigned the AGV " + agv.identity().toString() + "!");
                    sOut.write(PacketUtils.toData(AGVManagerRequestHandler.DIGITAL_TWIN_ASSIGN, digitalTwinJSON(agv)));
                    foundAGV = true;
                    break;
                }
            }

            if (!foundAGV) {
                System.out.println("Couldn't find an available AGV!");
                myS.close();
            }
        } catch (IOException e) {
            System.out.println("Could not open data stream.");
            System.exit(1);
        }
    }

    public String digitalTwinJSON(AGV agv) {
        AGVDigitalTwinDTO digitalTwinDTO = new AGVDigitalTwinDTO(
                agv.identity().toString(),
                1,
                agv.batteryStatus().batteryAutonomy(),
                agv.currentPosition().squareCoordinates().lengthSquare(),
                agv.currentPosition().squareCoordinates().widthSquare(),
                agv.baseLocation().beginSquare().squareCoordinates().lengthSquare(),
                agv.baseLocation().beginSquare().squareCoordinates().widthSquare());

        GsonBuilder builder = new GsonBuilder();
        Gson parser = builder.create();

        System.out.println("Fetching AGV info from database...");
        String json = parser.toJson(digitalTwinDTO);
        System.out.printf("Parsing info to json format...\n%s\n", json);
        return json;
    }
}
