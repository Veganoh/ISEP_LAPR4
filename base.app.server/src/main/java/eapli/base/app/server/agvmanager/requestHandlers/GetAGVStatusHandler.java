package eapli.base.app.server.agvmanager.requestHandlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eapli.base.agvdockmanagement.domain.AGVDock;
import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.agvtaskmanagement.domain.AGVTask;
import eapli.base.app.server.agvmanager.AGVManagerRequestHandler;
import eapli.base.app.server.agvmanager.requestHandlers.utils.AGVDockJSONAdapter;
import eapli.base.app.server.agvmanager.requestHandlers.utils.AGVListJSON;
import eapli.base.app.server.agvmanager.requestHandlers.utils.AGVTaskJSONAdapter;
import eapli.base.common.domain.PacketUtils;
import eapli.base.infrastructure.persistence.PersistenceContext;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GetAGVStatusHandler implements Runnable {

    /**
     * The socket that connects the server
     */
    private Socket myS;

    /**
     * input and output streams to the remote server
     */
    private DataInputStream sIn;
    private DataOutputStream sOut;

    public GetAGVStatusHandler(Socket myS, DataInputStream sIn, DataOutputStream sOut) {
        this.myS = myS;
        this.sIn = sIn;
        this.sOut = sOut;
    }

    /**
     * receives the agv and updates its task as completed
     */
    @Override
    public void run() {
        try {
            sOut.write(PacketUtils.toData(AGVManagerRequestHandler.AGV_STATUS, agvListJSON()));
        } catch (IOException e) {
            System.out.println("Could not open data stream.");
            System.exit(1);
        }
    }

    public String agvListJSON() {
        AGVRepository agvRepository = PersistenceContext.repositories().AGV();
        List<AGV> agvList = (ArrayList<AGV>) agvRepository.findAll();

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(AGVDock.class, new AGVDockJSONAdapter());
        builder.registerTypeAdapter(AGVTask.class, new AGVTaskJSONAdapter());
        Gson parser = builder.create();

        System.out.printf("Fetching list from database... Retrieved %d records.\n", agvList.size());
        String json = parser.toJson(new AGVListJSON(agvList));
        System.out.printf("Parsing list to json format...\n%s\n", json);
        return json;
    }
}
