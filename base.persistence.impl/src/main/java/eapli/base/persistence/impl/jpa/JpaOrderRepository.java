package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.domain.OrderState;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaOrderRepository extends JpaAutoTxRepository<Order, Long, Long> implements OrderRepository {

    /**
     * creates a new product repository
     *
     * @param autoTx the transactional context to enroll
     */
    public JpaOrderRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    /**
     * creates a new product repository
     */
    public JpaOrderRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    /**
     * Query to obtain all unfinished orders of a given customer.
     * Please note that because of the need to fetch lazy elements, it is necessary to use the clause "JOIN FETCH".
     * <p>
     * According to Hibernate <a href="http://docs.jboss.org/hibernate/orm/3.6/reference/en-US/html/objectstate.html#objectstate-querying-executing">documentation</a>
     * <i>Queries that make use of eager fetching of collections usually return duplicates of the root objects,
     * but with their collections initialized. You can filter these duplicates through a Set.</i>
     *
     * @param customerId
     * @return an iterable with duplicates of the root object (as stated above)
     */
    @Override
    public Iterable<Order> findOpenOrders(String customerId) {
        final TypedQuery<Order> query = entityManager().createQuery(
                        "SELECT o FROM Order o " +
                                "INNER JOIN FETCH o.orderLineItems " +
                                "WHERE o.customer.id.identifier LIKE :id " +
                                "AND o.orderStates.size < :closed", Order.class)
                .setParameter("id", customerId).setParameter("closed", OrderState.State.values().length);
        return query.getResultList();
    }

    /**
     * query to obtain all orders in state "READY FOR PACKAGING"
     *
     * @return all orders in state "READY FOR PACKAGING"
     */
    @Override
    public Iterable<Order> prepared() {
        final TypedQuery<Order> query = entityManager().createQuery("SELECT o FROM Order o WHERE SIZE(o.orderStates) = 5", Order.class);
        return query.getResultList();
    }

    /**
     * query to obtain all orders in state "DISPATCHED"
     *
     * @return all orders in state "DISPATCHED"
     */
    @Override
    public Iterable<Order> toBeReadyForDeliver() {
        final TypedQuery<Order> query = entityManager().createQuery("SELECT o FROM Order o WHERE SIZE(o.orderStates) = 7", Order.class);
        return query.getResultList();
    }

    /**
     * query to obtain all orders in state "TO BE PREPARED"
     *
     * @return all orders in state "TO BE PREPARED"
     */
    @Override
    public Iterable<Order> toBePrepared() {
        final TypedQuery<Order> query = entityManager().createQuery("SELECT o FROM Order o WHERE SIZE(o.orderStates) = 3", Order.class);
        return query.getResultList();
    }

    /**
     * query to obtain all orders in state "TO BE PREPARED" ordered by registered time
     *
     * @return all orders in state "TO BE PREPARED" ordered by registered time
     */
    @Override
    public Iterable<Order> toBePreparedSortedByDate() {
        final TypedQuery<Order> query = entityManager().createQuery(
                "SELECT o FROM Order o " +
                        "INNER JOIN o.orderStates s " +
                        "WHERE SIZE(s) = 3 " +
                        "AND s.registerTime = (SELECT MAX(s2.registerTime) FROM o.orderStates s2)" +
                        "ORDER BY s.registerTime"
                , Order.class);

        return query.getResultList();
    }


}
