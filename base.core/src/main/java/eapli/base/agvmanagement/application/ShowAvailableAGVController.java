package eapli.base.agvmanagement.application;

import eapli.base.Application;
import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.agvtaskmanagement.domain.AGVTask;
import eapli.base.common.domain.ConnectionEstablisher;
import eapli.base.common.domain.PacketUtils;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class    ShowAvailableAGVController {

    public static final int TASK_ASSIGN = 5;
    public static final int AGV_ASSIGN = 6;
    /**
     * Authorization service that is used to make sure only authorized users can use the controller
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * The AGV Repository
     */
    private final AGVRepository agvRepo = PersistenceContext.repositories().AGV();
    /**
     * Constructs a new controller
     */
    public ShowAvailableAGVController() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE, BaseRoles.ADMIN, BaseRoles.POWER_USER);
    }
    /**
     * Returns an Iterable with all the configured AGVs
     * @return an Iterable with all the configured AGVs
     */
    public Iterable<AGV> listAvailableAGV() {
        return agvRepo.available();
    }

    /**
     * @returns an Iterable with all the available AGVs which have carrying capacity for a given Order
     */
    public Iterable<AGV> listCapableAGV(Order order) {
        return ((ArrayList<AGV>) listAvailableAGV()).stream()
                .filter(agv -> agv.maxVolumeCapacity().compareTo(order.volume()) >= 0
                        && agv.maxWeightCapacity().compareTo(order.weight()) >= 0)
                .collect(Collectors.toList());
    }

    /**
     * Assigns a task to an AGV and updates de order state
     * @param order the order to be converted to a task and updated
     * @param agv the agv that will carry out the task
     */
    public void assignTask(Order order, AGV agv) throws Exception {
        AGVTask task = new AGVTask(order);

        ConnectionEstablisher conn = new ConnectionEstablisher(Application.settings().getAgvManagerServer(), Application.settings().getAgvManagerPort(),Application.settings().getRequestsKeyPath());
        Socket sock = conn.establishConnection();
        DataInputStream sIn; DataOutputStream sOut;
        try {
            sIn = new DataInputStream(sock.getInputStream());
            sOut = new DataOutputStream(sock.getOutputStream());

            sOut.write(PacketUtils.toData(TASK_ASSIGN, task));
            PacketUtils.assertACK(sIn);

            sOut.write(PacketUtils.toData(AGV_ASSIGN, agv));
            PacketUtils.assertEXIT(sIn);

            conn.closeConnection();
        } catch (IOException e) {
            System.out.println("Could not communicate with AGV Manager server.");
            System.exit(1);
        }
    }
}
