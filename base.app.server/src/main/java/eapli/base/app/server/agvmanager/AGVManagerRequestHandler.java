package eapli.base.app.server.agvmanager;

import eapli.base.app.server.agvmanager.requestHandlers.AssignDigitalTwinHandler;
import eapli.base.app.server.agvmanager.requestHandlers.AssignTaskHandler;
import eapli.base.app.server.agvmanager.requestHandlers.GetAGVStatusHandler;
import eapli.base.app.server.agvmanager.requestHandlers.UpdateAGVStatusHandler;
import eapli.base.common.domain.PacketUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static eapli.base.common.domain.PacketUtils.*;

public class AGVManagerRequestHandler extends Thread {
    public static final int TASK_FINISH = 3;
    public static final int AGV_STATUS = 4;
    public static final int TASK_ASSIGN = 5;
    public static final int AGV_ASSIGN = 6;
    public static final int DIGITAL_TWIN_REQUEST = 7;
    public static final int DIGITAL_TWIN_ASSIGN = 8;
    public static final int STATUS_UPDATE = 9;

    /**
     * The socket that connects this class to another program
     */
    private Socket myS;

    private Map<String, String> digitalTwins;

    /**
     * receives the socket that will control this method
     *
     * @param socket the socket
     */
    public AGVManagerRequestHandler(Socket socket) {
        this.myS = socket;
    }

    /**
     * receives the socket that will control this method and the currently assigned digitalTwins
     *
     * @param socket the socket
     * @param digitalTwins the hashmap of digitalTwins
     */
    public AGVManagerRequestHandler(Socket socket, Map<String, String> digitalTwins) {
        this.myS = socket;
        this.digitalTwins = digitalTwins;
    }

    /**
     * While loop that treats the packets receives from the socket, The second byte is equal to the code of said byt and
     * after that the switch case will perform the given instructors
     */
    @Override
    public void run() {
        try {
            DataInputStream sIn = new DataInputStream(myS.getInputStream());
            DataOutputStream sOut = new DataOutputStream(myS.getOutputStream());

            boolean flag = true;
            while (flag) {
                int version = sIn.readUnsignedByte();
                int code = sIn.readUnsignedByte();
                System.out.println("Received request with code " + code);
                switch (code) {
                    case COMMTEST:
                        //Skips the rest of the bytes and writes back an acknowledgment that the packet was received
                        sIn.skipBytes(2);
                        sOut.write(PacketUtils.ACK_DATA);
                        System.out.println("Reply sent with code " + PacketUtils.ACK_DATA[CODE_OFFSET]);
                        break;
                    case DISCONN:
                        //Skips the rest of the bytes and writes back an acknowledgment that the packet was received and
                        // finally stops the loop by closing the socket
                        sIn.skipBytes(2);
                        sOut.write(PacketUtils.ACK_DATA);
                        System.out.println("Disconnecting user " + myS.getRemoteSocketAddress().toString());

                        if (digitalTwins.containsValue(myS.getRemoteSocketAddress().toString()))
                            for (String key : digitalTwins.keySet())
                                if (digitalTwins.get(key).equals(myS.getRemoteSocketAddress().toString()))
                                    digitalTwins.remove(key);

                        myS.close();
                        flag = false;
                        break;
                    case ACK:
                        //Ascertains if a connection was made
                        System.out.println("\nConnection test acknowledged");
                        break;
                    case TASK_ASSIGN:
                        AssignTaskHandler assignHandler = new AssignTaskHandler(myS, sIn, sOut, digitalTwins);
                        assignHandler.run();
                        break;
                    case AGV_STATUS:
                        //if the code is a request of AGV status returns the status
                        sIn.skipBytes(2);
                        GetAGVStatusHandler agvHandler = new GetAGVStatusHandler(myS, sIn, sOut);
                        agvHandler.run();
                        break;
                    case DIGITAL_TWIN_REQUEST:
                        sIn.skipBytes(2);
                        AssignDigitalTwinHandler digitalTwinHandler = new AssignDigitalTwinHandler(myS, sIn, sOut, digitalTwins);
                        digitalTwinHandler.run();
                        break;
                    case STATUS_UPDATE:
                        UpdateAGVStatusHandler updateAGVStatusHandler = new UpdateAGVStatusHandler(myS, sIn, sOut, digitalTwins);
                        updateAGVStatusHandler.run();
                        break;
                    default:
                        sIn.skipBytes(2);
                        System.out.println("Received unknown code! (" + code + ")");
                        System.out.println("Disconnecting user " + myS.getInetAddress().getHostAddress());
                        myS.close();
                        break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
