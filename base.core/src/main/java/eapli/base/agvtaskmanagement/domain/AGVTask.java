package eapli.base.agvtaskmanagement.domain;

import eapli.base.common.domain.Volume;
import eapli.base.common.domain.Weight;
import eapli.base.ordermanagement.domain.Order;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
public class AGVTask implements AggregateRoot<Long> {
    /**
     * Database Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @OneToOne
    private Order order;

    /**
     * Mandatory empty constructor for ORM
     */
    protected AGVTask() {
    }

    /**
     * Constructor for creating AGV Tasks Objects
     *
     * @param order The Order that the AGV Task is related to
     */
    public AGVTask(final Order order) {
        Preconditions.nonNull(order);
        this.order = order;
    }
    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return id;
    }

    public Weight weight() {
        return order.weight();
    }

    public Volume volume() {
        return order.volume();
    }

    public Order order() {return order;}
}
