package eapli.base.app.server.http;

import eapli.base.Application;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.IOException;

public class HTTPServer {

    static final String TRUSTED_STORE = Application.settings().getHttpServerKeyPath();
    static final String KEYSTORE_PASS = "spomsp";
    static final int SERVER_PORT = Application.settings().getHttpServerPort();

    static private final String BASE_FOLDER = "src/main/java/eapli/base/app/other/console/warehouseEmployee/agvDashboard/www/";
    //static private ServerSocket sock;

    static private SSLServerSocket sock;

    public static void main(String args[]) throws Exception {

        SSLSocket cliSock;

        // Trust these certificates provided by authorized clients
        System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword",KEYSTORE_PASS);

        System.setProperty("javax.net.ssl.keyStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);

        SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        //Server Port
        try {
            sock = (SSLServerSocket) sslF.createServerSocket(SERVER_PORT);
            System.out.println(sock.getInetAddress());
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Local port number not available.");
            System.exit(1);
        }

        System.out.println("Server has started");

        //While cycle is maintained and awaits connections which will be handled by a separate thread
        while (true) {
            cliSock = (SSLSocket) sock.accept(); // wait for a new client connection request
            System.out.println("Connected to ip " + cliSock.getInetAddress().getHostAddress());
            Thread requestHandler = new Thread(new HTTPRequestHandler(cliSock, BASE_FOLDER));
            requestHandler.start();
        }
    }
}
