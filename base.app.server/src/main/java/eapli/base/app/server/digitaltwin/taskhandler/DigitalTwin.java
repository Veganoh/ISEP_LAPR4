package eapli.base.app.server.digitaltwin.taskhandler;

import eapli.base.Application;
import eapli.base.agvmanagement.domain.AGV;
import eapli.base.common.domain.ConnectionEstablisher;
import eapli.base.common.domain.PacketUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import static eapli.base.common.domain.PacketUtils.ACK;
import static eapli.base.common.domain.PacketUtils.CODE_OFFSET;

public class DigitalTwin {

    /**
     * The connected server's ip
     */
    private InetAddress serverIP;

    /**
     * The connected server's socket
     */
    private Socket sock;

    /**
     * input and output streams to the remote server
     */
    private DataInputStream sIn;
    private DataOutputStream sOut;

    /**
     * target agv
     */
    private AGV agv;

    /**
     * Set's the agv that will be updated
     * @param agv target agv
     */
    public DigitalTwin(AGV agv){
        this.agv = agv;
    }

    /**
     * Main method responsible for working out the order
     */
    public void carryOutTask(){
        establishManagerConnection();

        //TODO: for now a simple mocking of the operation is being user but latter a more complex solution is needed

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        alertTaskFinished();
    }

    /**
     * alerts the agv manager server that the task has been concluded
     */
    private void alertTaskFinished() {
        try {
            sOut.write(PacketUtils.toData(3, agv));

            sOut.write(PacketUtils.EXIT_DATA);

            byte[] response = new byte[4];
            sIn.read(response);
            if (response[CODE_OFFSET] == ACK) {
                System.out.println("Finished preparing the order");
                sock.close();
            }
        } catch (IOException e) {
            System.out.println("Could not open data stream.");
            System.exit(1);
        }
    }


    /**
     * establishes the connection to the agv manager server
     */
    private void establishManagerConnection() {
        ConnectionEstablisher connectionEstablisher = new ConnectionEstablisher(Application.settings().getAgvManagerServer(), Application.settings().getAgvManagerPort(), Application.settings().getDigitalTwinKeyPath());

        try {
            sock = connectionEstablisher.establishConnection();
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Failed to connect.");
            System.exit(1);
        }


        try {
            sIn = new DataInputStream(sock.getInputStream());
            sOut = new DataOutputStream(sock.getOutputStream());

            sOut.write(PacketUtils.COMM_DATA);

            byte[] response = new byte[4];
            sIn.read(response);

            if (response[CODE_OFFSET] != ACK)
                throw new IllegalArgumentException("Response packet is faulty");

        } catch (IOException e) {
            System.out.println("Could not open data stream.");
            System.exit(1);
        }
    }

}


