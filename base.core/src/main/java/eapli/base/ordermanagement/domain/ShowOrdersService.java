package eapli.base.ordermanagement.domain;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.domain.OrderNotificationService;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class ShowOrdersService {
    /**
     * Authorization service that is used to make sure only authorized users can use the controller
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * The Order Repository
     */
    private final OrderRepository ordersRepo = PersistenceContext.repositories().Orders();

    /**
     * Constructs a new ConfigureAGVController
     */
    public ShowOrdersService() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.WAREHOUSE_EMPLOYEE, BaseRoles.ADMIN, BaseRoles.POWER_USER);
    }

    /**
     * Returns an Iterable with all the Saved orders
     * @return an Iterable with all the Saved orders
     */
    public Iterable<Order> listSavedOrders() {
        return ordersRepo.findAll();
    }

    /**
     * Returns an Iterable with all the Saved orders
     * @return an Iterable with all the Saved orders
     */
    public Iterable<Order> listPreparedOrders() {
        return ordersRepo.prepared();
    }

    /**
     * Returns an Iterable with all the Saved orders
     * @return an Iterable with all the Saved orders
     */
    public Iterable<Order> listToBePreparedOrders() {
        return ordersRepo.toBePrepared();
    }

    /**
     * Returns an Iterable with all the Saved orders
     * @return an Iterable with all the Saved orders
     */
    public Iterable<Order> listToBeDeliveredOrders() {
        return ordersRepo.toBeReadyForDeliver();
    }

    /**
     * Updates the state of an order to "READY FOR DISPATCHING"
     * @param order the order that will be updated
     */
    public void updateToReadyForDispatch(Order order){
        OrderNotificationService.readyForDispatch(order);
    }

    /**
     * Updates the state of an order to "DISPATCHED"
     * @param order the order that will be updated
     */
    public void updateToDispatched(Order order){
        OrderNotificationService.dispatched(order);
    }

    /**
     * Updates the state of an order to "DELIVERED BY CARRIER"
     * @param order the order that will be updated
     */
    public void updateToDeliveredByCarrier(Order order){
        OrderNotificationService.deliveredByCarrier(order);
    }
}
