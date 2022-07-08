package eapli.base.app.server.agvmanager.threads;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.agvtaskmanagement.domain.AGVTask;
import eapli.base.app.server.agvmanager.requestHandlers.AssignTaskHandler;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AutoAGVTaskAssigner implements Runnable {

    /**
     * The TaskHandler
     */
    AssignTaskHandler taskHandler = new AssignTaskHandler();

    Map<String, String> digitalTwins;

    public AutoAGVTaskAssigner(Map<String, String> digitalTwins) {
        this.digitalTwins = digitalTwins;
    }

    /**
     * AutoAGVTaskAssigner's main function.
     * Every 1 minute it will search for new orders and for the currently free AGV, converting the orders
     * into tasks and assigning them to a capable agv following a FIFO queue.
     *
     * May be interrupted using interrupt()
     */
    @Override
    public void run() {
        Iterable<Order> orderList;
        Iterable<AGV> freeAGVList;
        LinkedList<Order> orderQueue = new LinkedList<>();

        System.out.println("Auto AGV Task Assigner is ON");

        while(!Thread.interrupted()) {
            try {
                orderList = taskHandler.getOrdersToBePrepared();

                taskHandler.addNewOrdersToQueue(orderQueue, orderList);

                freeAGVList = taskHandler.getFreeAGV();

                taskHandler.assignTaskToFreeAGV(orderQueue, freeAGVList, digitalTwins);

                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("The Auto AGV Task Assignment functionally was interrupted!");
                break;
            }
        }
    }
}
