package eapli.base.ordermanagement.domain;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.agvtaskmanagement.domain.AGVTask;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.repositories.OrderRepository;

public class OrderNotificationService {
    public static void beingPrepared(Order o) {
        o.beingPrepared();
        save(o);
    }

    public static void readyForPackaging(Order o) {
        o.readyForPackaging();
        save(o);
    }

    public static void readyForDispatch(Order o) {
        o.readyForDispatch();
        save(o);
    }

    public static void dispatched(Order o) {
        o.dispatched();
        save(o);
    }

    public static void deliveredByCarrier(Order o) {
        o.deliveredByCarrier();
        save(o);
    }

    public static void assignTask(AGV a, AGVTask t) {
        a.assignTask(t);
        beingPrepared(t.order());

        save(a);
        save(t.order());
    }

    public static void finishTask(AGV a) {
        AGVTask t = a.task();
        a.finishTask();
        readyForPackaging(t.order());

        save(a);
        save(t.order());
    }

    public static void save(Order o) {
        OrderRepository orderRepository = PersistenceContext.repositories().Orders();
        orderRepository.save(o);
    }

    private static void save(AGV a) {
        AGVRepository agvRepository = PersistenceContext.repositories().AGV();
        agvRepository.save(a);
    }
}
