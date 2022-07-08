package eapli.base.app.server.agvmanager;

import eapli.base.Application;

import eapli.base.app.server.agvmanager.threads.AutoAGVTaskAssigner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public class AGVManagerSrv {
    static final String TRUSTED_STORE=Application.settings().getAgvManagerKeyPath();;
    static final String KEYSTORE_PASS="spomsp";
    static final int SERVER_PORT = Application.settings().getAgvManagerPort();

    public static void main(String[] args) throws Exception {

        // The server's socket
        SSLServerSocket sock = null;

        // The client's socket
        Socket cliSock;

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

        ConcurrentHashMap<String, String> digitalTwins = new ConcurrentHashMap<>();

        if (Application.settings().getAutoAGVTaskAssignerFlag().equals("true")) {
            AutoAGVTaskAssigner autoAGVTaskAssigner = new AutoAGVTaskAssigner(digitalTwins);
            Thread autoAGVTaskAssignerThread = new Thread(autoAGVTaskAssigner);
            autoAGVTaskAssignerThread.start();
        } else {
            System.out.println("Auto AGV Task Assigner is OFF");
        }

        //While cycle is maintained and awaits connections which will be handled by a separate thread
        while(true) {
            cliSock = sock.accept(); // wait for a new client connection request
            System.out.println("Connected to ip " + cliSock.getRemoteSocketAddress().toString());
            Thread requestHandler = new Thread(new AGVManagerRequestHandler(cliSock, digitalTwins));
            requestHandler.start();
        }
    }
}

