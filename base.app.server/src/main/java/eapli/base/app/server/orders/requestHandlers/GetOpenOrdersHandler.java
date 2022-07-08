package eapli.base.app.server.orders.requestHandlers;

import eapli.base.app.server.agvmanager.AGVManagerRequestHandler;
import eapli.base.app.server.orders.OrdersRequestHandler;
import eapli.base.common.domain.PacketUtils;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetOpenOrdersHandler implements Runnable{
    /**
     * The socket that connects the server
     */
    private Socket myS;

    /**
     * input and output streams to the remote server
     */
    private DataInputStream sIn;
    private DataOutputStream sOut;

    public GetOpenOrdersHandler(Socket myS, DataInputStream sIn, DataOutputStream sOut) {
        this.myS = myS;
        this.sIn = sIn;
        this.sOut = sOut;
    }

    @Override
    public void run() {
        try {
            sOut.write(PacketUtils.toData(OrdersRequestHandler.OPEN_ORDERS, openOrders()));
        } catch (IOException e) {
            System.out.println("Could not open data stream.");
            System.exit(1);
        }
    }

    private HashSet<Order> openOrders() {
        SystemUser user = PacketUtils.readObject(sIn);

        CustomerRepository customerRepo = PersistenceContext.repositories().Customer();
        Customer customer = customerRepo.findByUsername(user.username().toString()).orElseThrow();

        OrderRepository orderRepo = PersistenceContext.repositories().Orders();
        return new HashSet<>((ArrayList<Order>) orderRepo.findOpenOrders(customer.id().toString()));
    }
}
