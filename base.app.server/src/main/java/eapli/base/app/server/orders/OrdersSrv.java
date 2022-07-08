package eapli.base.app.server.orders;

import eapli.base.Application;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class OrdersSrv {

    static final String TRUSTED_STORE=Application.settings().getOrdersServerKeyPath();;
    static final String KEYSTORE_PASS="spomsp";
    static final int SERVER_PORT = Application.settings().getOrderServerPort();

    public static void main(String[] args) throws Exception {
        // The server's socket
        SSLServerSocket sock = null;

        // The client's socket
        Socket s;

        // Trust these certificates provided by authorized clients
        System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword",KEYSTORE_PASS);

        // Use this certificate and private key as server certificate
        System.setProperty("javax.net.ssl.keyStore",TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword",KEYSTORE_PASS);

        SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        //Server Port
        try {
            sock = (SSLServerSocket) sslF.createServerSocket(SERVER_PORT);
            sock.setNeedClientAuth(true);
            System.out.println(sock.getInetAddress());
        } catch(IOException ex) {
            System.out.println("Local port number not available.");
            System.exit(1);
        }

        System.out.println("Server has started");
        while(true){
            s = sock.accept();
            System.out.println("Connected to ip " + s.getInetAddress().getHostAddress());
            new Thread(new OrdersRequestHandler(s)).start();
        }
    }

}
