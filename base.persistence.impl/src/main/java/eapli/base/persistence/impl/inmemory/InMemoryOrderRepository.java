package eapli.base.persistence.impl.inmemory;

import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryOrderRepository extends InMemoryDomainRepository<Order, Long> implements OrderRepository {

    /*
     * Initializes the inmemory repository
     */
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Order> findOpenOrders(String customerId) {
        return null;
    }

    /**
     * In Memory method for sorting orders in state "READ FOR PACKAGING"
     * @return null
     */
    @Override
    public Iterable<Order> prepared() {
        return null;
    }

    /**
     * In Memory method for sorting orders in state "TO BE PREPARED"
     * @return null
     */
    @Override
    public Iterable<Order> toBePrepared() {
        return null;
    }

    /**
     * In Memory method for sorting orders in state "DISPATCHED"
     * @return null
     */
    @Override
    public Iterable<Order> toBeReadyForDeliver() {
        return null;
    }

    /**
     * In memory method for returning orders in state "TO BE PREPARED" ordered by registered time
     * @return null
     */
    @Override
    public Iterable<Order> toBePreparedSortedByDate() { return null;}
}
