package eapli.base.app.server.agvmanager.requestHandlers;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.AGVId;
import eapli.base.agvmanagement.domain.AGVStatus;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.agvmanagermanagement.domain.Coordinates;
import eapli.base.agvmanagermanagement.domain.Square;
import eapli.base.infrastructure.persistence.PersistenceContext;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.rmi.UnexpectedException;
import java.util.Map;
import java.util.Optional;

public class UpdateAGVStatusHandler implements Runnable {
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

    public UpdateAGVStatusHandler(Socket myS, DataInputStream sIn, DataOutputStream sOut, Map<String, String> digitalTwins) {
        this.myS = myS;
        this.sIn = sIn;
        this.sOut = sOut;
        this.digitalTwins = digitalTwins;
    }

    @Override
    public void run() {
        try {
            if (!digitalTwins.containsValue(myS.getRemoteSocketAddress().toString()))
                throw new IllegalCallerException("The IP that tried to update an agv status does not belong to any of the known Digital Twins!");

            String identifier = null;

            for (String agvId : digitalTwins.keySet())
                if (digitalTwins.get(agvId).equals(myS.getRemoteSocketAddress().toString())) {
                    identifier = agvId;
                    break;
                }

            Optional<AGV> optionalAGV = agvRep.ofIdentity(AGVId.valueOf(identifier));

            if (optionalAGV.isEmpty())
                throw new UnexpectedException("Something unexpected happened!");


            AGV agv = optionalAGV.get();
            int dataSize = sIn.readUnsignedByte();
            sIn.skipBytes(1);
            int lengthSquare = sIn.readUnsignedByte();
            int widthSquare = sIn.readUnsignedByte();
            String status = "";

            for (int i = 0; i < dataSize - 2; i++) {
                status = status.concat(String.valueOf((char) sIn.readByte()));
            }

            System.out.printf("%d(x) - %d(y) - %s\n", widthSquare, lengthSquare, status);

            Square currPosition = Square.valueOf(Coordinates.valueOf(widthSquare, lengthSquare));
            agv.updatePosition(currPosition);
            agv.updateStatus(AGVStatus.valueOfStatus(status));
            
            agvRep.save(agv);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
