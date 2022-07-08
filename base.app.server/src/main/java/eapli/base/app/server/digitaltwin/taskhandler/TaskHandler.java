package eapli.base.app.server.digitaltwin.taskhandler;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.common.domain.PacketUtils;
import org.apache.commons.lang3.SerializationUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static eapli.base.common.domain.PacketUtils.CODE_OFFSET;
import static eapli.base.common.domain.PacketUtils.DISCONN;

public class TaskHandler implements Runnable {

    /**
     * The socket that connects the server
     */
    private Socket myS;

    /**
     * input and output streams to the remote server
     */
    private DataInputStream sIn;
    private DataOutputStream sOut;

    public TaskHandler(Socket myS, DataInputStream sIn, DataOutputStream sOut){
        this.myS = myS;
        this.sIn = sIn;
        this.sOut = sOut;
    }

    /**
     * receives the agv and sends it to the process responsible for taking care of tasks
     */
    @Override
    public void run() {
        try {
            AGV agv = PacketUtils.readObject(sIn);

            byte[] response = new byte[4];
            sIn.read(response);
            if (response[CODE_OFFSET] == DISCONN) {
                System.out.println("Disconnecting user " + myS.getInetAddress().getHostAddress());
                sOut.write(PacketUtils.ACK_DATA);
                myS.close();
            }

            DigitalTwin digitalTwin = new DigitalTwin(agv);
            digitalTwin.carryOutTask();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
