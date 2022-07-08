package eapli.base.app.server.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eapli.base.agvdockmanagement.domain.AGVDock;
import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.agvtaskmanagement.domain.AGVTask;
import eapli.base.app.server.agvmanager.requestHandlers.utils.AGVDockJSONAdapter;
import eapli.base.app.server.agvmanager.requestHandlers.utils.AGVListJSON;
import eapli.base.app.server.agvmanager.requestHandlers.utils.AGVTaskJSONAdapter;
import eapli.base.infrastructure.persistence.PersistenceContext;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HTTPRequestHandler extends Thread {
    private String baseFolder;
    private Socket sock;
    private DataInputStream inS;
    private DataOutputStream outS;

    public HTTPRequestHandler(Socket s, String f) {
        this.baseFolder = f;
        this.sock = s;
    }

    public void run() {
        //Open sockets output and input
        try {
            outS = new DataOutputStream(sock.getOutputStream());
            inS = new DataInputStream(sock.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Thread error on data streams creation");
        }
        //Read incoming request and reply to it
        try {
            HTTPMessage request = new HTTPMessage(inS);
            HTTPMessage response = new HTTPMessage();
            System.out.printf("Receiving request: %s %s\n", request.getMethod(), request.getURI());

            //GET request
            if (request.getMethod().equals("GET")) {
                if (request.getURI().equals("/")) {
                    response.setContentFromString(
                            getAGVStatus(), "application/json");
                    response.setResponseStatus("200 Ok");
                } else {
                    String fullname = baseFolder + "/";
                    if (request.getURI().equals("/")) fullname = fullname + "index.html";
                    else fullname = fullname + request.getURI();
                    if (response.setContentFromFile(fullname)) {
                        response.setResponseStatus("200 Ok");
                    } else {
                        response.setContentFromString(
                                "<html><body><h1>404 File not found</h1></body></html>",
                                "text/html");
                        response.setResponseStatus("404 Not Found");
                    }
                }
                response.send(outS);
            } else { // other requests
                response.setContentFromString(
                        "<html><body><h1>ERROR: 405 Method Not Allowed</h1></body></html>",
                        "text/html");
                response.setResponseStatus("405 Method Not Allowed");
            }
            response.send(outS);
        } catch (Exception ex) {
//            ex.printStackTrace();
//            System.out.println("Thread error when reading request");
        }
        try {sock.close();} catch (IOException ex) {System.out.println("CLOSE IOException");}
    }

    private String getAGVStatus() throws Exception {
        System.out.println("Aquiring AGV status...");
        AGVManagerRequest request = new AGVManagerRequest();
        String response = request.getAGVStatus();
        request.closeConnection();
        return response;
    }
}

