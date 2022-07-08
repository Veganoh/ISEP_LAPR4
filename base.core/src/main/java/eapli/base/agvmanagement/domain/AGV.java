package eapli.base.agvmanagement.domain;

import eapli.base.agvdockmanagement.domain.AGVDock;
import eapli.base.agvmanagermanagement.domain.Square;
import eapli.base.agvtaskmanagement.domain.AGVTask;
import eapli.base.common.domain.Volume;
import eapli.base.common.domain.Weight;
import eapli.base.ordermanagement.domain.Order;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
public class AGV implements AggregateRoot<AGVId> {
    @EmbeddedId
    @AttributeOverride(name="id", column=@Column(name="AGVId"))
    private AGVId id;

    @Embedded
    private Weight maxWeightCapacity;

    @Embedded
    private Volume maxVolumeCapacity;

    @Embedded
    private AGVModel model;

    @Embedded
    private AGVShortDescription shortDescription;

    @Enumerated(EnumType.STRING)
    private AGVStatus status;

    @Embedded
    private AGVBatteryStatus batteryStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_position_square_id")
    private Square currentPosition;

    @OneToOne
    private AGVDock baseLocation;

    @OneToOne(cascade = CascadeType.ALL)
    private AGVTask task;

    /**
     Mandatory empty constructor for ORM
     */
    protected AGV() {}

    protected AGV(AGVId id, AGVModel model, AGVShortDescription shortDescription,Weight maxWeightCapacity, Volume maxVolumeCapacity, AGVDock baseLocation, AGVBatteryStatus batteryStatus, AGVStatus status) {
        Preconditions.noneNull(id, model, shortDescription, maxWeightCapacity, maxVolumeCapacity, baseLocation, batteryStatus);

        this.id = id;
        this.model = model;
        this.shortDescription = shortDescription;
        this.maxWeightCapacity = maxWeightCapacity;
        this.maxVolumeCapacity = maxVolumeCapacity;
        this.baseLocation = baseLocation;
        this.batteryStatus = batteryStatus;
        this.status = status;
        this.currentPosition = baseLocation.beginSquare();
    }
    public boolean isFree() {
        return status.equals(AGVStatus.Free);
    }

    public boolean isServingOrder() {
        return status.equals(AGVStatus.ServingGivenOrder);
    }

    /**
     * Assigns a given task to an AGV.
     * Method should not be called unless through @OrderNotificationService.assignTask()
     * @param task
     */
    public void assignTask(AGVTask task) {
        if (!isFree())  throw new IllegalArgumentException("Cannot assign task to unavailable agv");
        this.task = task;
        this.status = AGVStatus.ServingGivenOrder;
    }

    /**
     * Marks the assigned task as finished.
     * Method should not be called unless through @OrderNotificationService.finishTask()
     */
    public void finishTask(){
        if (!isServingOrder()) throw new IllegalArgumentException("Cannot finish an order of an agv that had none");

        this.task = null;
        this.status = AGVStatus.Free;
    }

    public void updateStatus(AGVStatus status) {
        this.status = status;
    }

    public AGVModel model() {
        return model;
    }

    public AGVShortDescription shortDescription() {
        return shortDescription;
    }

    public AGVDock baseLocation() {
        return baseLocation;
    }

    public Weight maxWeightCapacity() {
        return maxWeightCapacity;
    }

    public Volume maxVolumeCapacity() {
        return maxVolumeCapacity;
    }

    public AGVBatteryStatus batteryStatus() {
        return batteryStatus;
    }

    public AGVStatus currentStatus() {
        return status;
    }

    public Square currentPosition() {
        return currentPosition;
    }

    public void updatePosition(Square currentPosition) {
        this.currentPosition = currentPosition;
    }

    public AGVTask task() {return task;}

    /**
     * Verifies if the AGV can carry out the given order
     *
     * @param order the Order
     * @return True if able to carry out the order, False if otherwise
     */
    public boolean isAGVCapable(Order order) {
        return this.maxVolumeCapacity().compareTo(order.volume()) >= 0
                && this.maxWeightCapacity().compareTo(order.weight()) >= 0;
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public AGVId identity() {
        return id;
    }
}
