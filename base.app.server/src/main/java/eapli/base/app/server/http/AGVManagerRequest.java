package eapli.base.app.server.http;

import eapli.base.Application;
import eapli.base.common.domain.ConnectionEstablisher;
import eapli.base.common.domain.PacketUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class AGVManagerRequest {
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
     * connection to server
     */
    private ConnectionEstablisher conn;

    public static final int AGV_STATUS = 4;

    public AGVManagerRequest() throws Exception {
        this.conn = new ConnectionEstablisher(Application.settings().getAgvManagerServer(), Application.settings().getAgvManagerPort(), Application.settings().getHttpServerKeyPath());
        this.sock = conn.establishConnection();

        try {
            sIn = new DataInputStream(sock.getInputStream());
            sOut = new DataOutputStream(sock.getOutputStream());
        } catch (IOException e) {
            System.out.println("Could not open data stream.");
            System.exit(1);
        }

    }

    public String getAGVStatus() {
        try {
            sOut.write(PacketUtils.toCode(AGV_STATUS));
            System.out.println("Sent packet with code " + AGV_STATUS);
            String reply = PacketUtils.read(sIn, AGV_STATUS);
            System.out.println("Translating data: " + reply);
            return reply;
        } catch (IOException e) {
            System.out.println("Could not communicate with AGV Manager server.");
            System.exit(1);
        }
        return null;
    }

    public void closeConnection() {
        conn.closeConnection();
    }
}
