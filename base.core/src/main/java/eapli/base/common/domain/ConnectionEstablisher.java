package eapli.base.common.domain;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.net.ssl.*;


import static eapli.base.common.domain.PacketUtils.*;

public class ConnectionEstablisher {

    /**
     * The connected server's ip
     */
    private  InetAddress serverIP;

    /**
     * The connected server's socket
     */
    private SSLSocket sock;

    /**
     * Input stream to the remote server
     */
    public DataInputStream sIn;

    /**
     * Output stream to the remote server
     */
    public DataOutputStream sOut;

    /**
     * Server IP
     */
    private String server;

    /**
     * The port number
     */
    private int port;

    /**
     * The application path
     */
    private String path;

    /**
     * The keystore password
     */
    static final String KEYSTORE_PASS = "spomsp";

    public ConnectionEstablisher(String server, int port, String path) {
        this.server = server;
        this.port = port;
        this.path = path;
    }

    public ConnectionEstablisher(String server, int port) {
        this.server = server;
        this.port = port;
    }

    /**
     * Establishes the connection to the server
     */
    public SSLSocket establishConnection() throws Exception{

        // Trust these certificates provided by servers
        System.setProperty("javax.net.ssl.trustStore", path);
        System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASS);

        // Use this certificate and private key for client certificate when requested by the server
        System.setProperty("javax.net.ssl.keyStore", path);
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);

        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try {
            serverIP = InetAddress.getByName(server);
        } catch (UnknownHostException ex) {
            System.out.println("Invalid server: " + server);
            System.exit(1);
        }

        try {
            System.out.println("Outgoing connection: " + serverIP);
            sock = (SSLSocket) sf.createSocket(serverIP,port);
        } catch (IOException ex) {
            System.out.println("Failed to connect. - " + ex.getMessage());
            System.exit(1);
        }

        sock.startHandshake();

        try {
            sIn = new DataInputStream(sock.getInputStream());
            sOut = new DataOutputStream(sock.getOutputStream());

            sOut.write(PacketUtils.COMM_DATA);
            System.out.println("Sent packet with code " + COMM_DATA[CODE_OFFSET]);

            byte[] response = new byte[4];
            sIn.read(response);
            System.out.println("Received reply with code " + response[CODE_OFFSET]);

            if (response[CODE_OFFSET] != ACK)
                throw new IllegalArgumentException("Response packet is faulty");

        } catch (IOException e) {
            System.out.println("Could not open data stream.");
            System.exit(1);
        }
        return sock;
    }

    /**
     * Closes the connection to the server
     */
    public void closeConnection(){
        try{
            sOut.write(EXIT_DATA);
            sIn.readUnsignedByte();

            if(sIn.readUnsignedByte() == ACK)
                sock.close();
        }catch (IOException e){
            System.out.println("Could not open data stream.");
            System.exit(1);
        }
    }
}
