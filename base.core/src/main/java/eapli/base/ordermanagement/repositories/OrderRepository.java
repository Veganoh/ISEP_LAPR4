package eapli.base.ordermanagement.repositories;

import eapli.base.ordermanagement.domain.Order;
import eapli.framework.domain.repositories.DomainRepository;

/**
 * A class that is to be updated depending on future needs of the project
 */
public interface OrderRepository extends DomainRepository<Long, Order> {
    Iterable<Order> prepared();

    Iterable<Order> toBePrepared();

    Iterable<Order> toBePreparedSortedByDate();

    Iterable<Order> toBeReadyForDeliver();

    Iterable<Order> findOpenOrders(String customerId);
}
