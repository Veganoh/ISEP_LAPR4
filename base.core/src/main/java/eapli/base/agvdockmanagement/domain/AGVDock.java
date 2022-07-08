package eapli.base.agvdockmanagement.domain;

import eapli.base.agvmanagermanagement.domain.Square;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
public class AGVDock implements AggregateRoot<AGVDockId> {
    /**
     * The AGVDock Identifier
     */
    @EmbeddedId
    @AttributeOverride(name = "id", column = @Column(name = "agv_dock_id"))
    private AGVDockId id;

    /**
     * product's availability
     */
    @Embedded
    @AttributeOverride(name = "availability",column = @Column(name = "agv_dock_availability"))
    private AGVDockAvailability availability;

    /**
     * The Square where the AGVDock Begins.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "begin_square_id")
    private Square beginSquare;

    /**
     * The Square where the AGVDock Ends.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "end_square_id")
    private Square endSquare;

    /**
     * The Square that marks the Depth of the AGVDock
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "depth_square_id")
    private Square depthSquare;

    /**
     * empty constructor for ORM
     */
    protected AGVDock() {
        // for ORM
    }

    public AGVDock(final Square beginSquare, final Square endSquare, final Square depthSquare){
        this(new AGVDockId(), beginSquare, endSquare, depthSquare);
    }

    /**
     * Constructor for AGVDocks
     * @param dockId AGVDock's identifier
     * @param beginSquare AGVDock's beginning square
     * @param endSquare AGVDock's ending square
     * @param depthSquare AGVDock's depth square
     */
    public AGVDock(final AGVDockId dockId, final Square beginSquare, final Square endSquare, final Square depthSquare) {
        Preconditions.noneNull(dockId, beginSquare, endSquare, depthSquare);

        if (beginSquare.hasRow() || beginSquare.hasAisle())
            throw new IllegalArgumentException("The AGV Dock's begin square can't contain a row or an aisle!");

        if (endSquare.hasRow() || endSquare.hasAisle())
            throw new IllegalArgumentException("The AGV Dock's end square can't contain a row or an aisle!");

        if (depthSquare.hasRow() || depthSquare.hasAisle())
            throw new IllegalArgumentException("The AGV Dock's depth square can't contain a row or an aisle!");

        if (!beginSquare.hasAccessibilityDirection() && !endSquare.hasAccessibilityDirection() && !depthSquare.hasAccessibilityDirection())
            throw new IllegalArgumentException("At least one of the AGV Dock's squares must have an accessibilityDirection");



        this.id = dockId;
        this.beginSquare = beginSquare;
        this.endSquare = endSquare;
        this.depthSquare = depthSquare;
        this.availability = AGVDockAvailability.valueOf(true);
    }

    public Square beginSquare() {
        return this.beginSquare;
    }

    public void turnUnavailable(){
        this.availability = AGVDockAvailability.valueOf(false);
    }

    public boolean isValid(){ return  this.availability.isAvailable();}

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public String toString() {
        return String.format("AGV Dock: %s", identity().toString());
    }

    @Override
    public AGVDockId identity() {
        return id;
    }
}
