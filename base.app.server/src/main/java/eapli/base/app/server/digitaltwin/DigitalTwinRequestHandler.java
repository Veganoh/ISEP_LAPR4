package eapli.base.app.server.digitaltwin;

import eapli.base.app.server.digitaltwin.taskhandler.TaskHandler;
import eapli.base.common.domain.PacketUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class DigitalTwinRequestHandler extends Thread{

    private final  int COMMTEST = 0;
    private final  int DISCONN = 1;
    private final  int ACK = 2;
    private final  int TASK = 3;

    /**
     * The socket that connects this class to another program
     */
    private Socket myS;

    /**
     * receives the socket that will control this method
     * @param socket the socket
     */
    public DigitalTwinRequestHandler(Socket socket){
        this.myS = socket;
    }

    /**
     * While loop that treats the packets receives from the socket, The second byte is equal to the code of said byt and
     * after that the switch case will perform the given instructors
     */
    @Override
    public void run(){
        try {

            DataInputStream sIn = new DataInputStream(myS.getInputStream());
            DataOutputStream sOut = new DataOutputStream(myS.getOutputStream());
            boolean flag = true;
            while(flag) {
                int version = sIn.readUnsignedByte();
                int code = sIn.readUnsignedByte();
                switch (code) {
                    case COMMTEST:
                        //Skips the rest of the bytes and writes back an acknowledgment that the packet was received
                        sIn.skipBytes(2);
                        sOut.write(PacketUtils.ACK_DATA);
                        break;
                    case DISCONN:
                        //Skips the rest of the bytes and writes back an acknowledgment that the packet was received and
                        // finally stops the loop by closing the socket
                        sIn.skipBytes(2);
                        sOut.write(PacketUtils.ACK_DATA);
                        System.out.println("Disconnecting user" + myS.getInetAddress().getHostAddress());
                        myS.close();
                        flag = false;
                        break;
                    case ACK:
                        //Ascertains if a connection was made
                        System.out.println("\nConnection test acknowledged");
                        break;
                    case TASK:
                        //if the code is a task allocation then the process of agv twin starts
                        TaskHandler handler = new TaskHandler(myS, sIn, sOut);
                        handler.run();
                        flag = false;
                        break;
                }
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
