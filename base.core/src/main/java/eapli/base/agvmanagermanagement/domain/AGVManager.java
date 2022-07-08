package eapli.base.agvmanagermanagement.domain;

import eapli.base.agvdockmanagement.domain.AGVDock;
import eapli.base.agvmanagement.domain.AGV;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AGVManager implements AggregateRoot<Long> {
    /**
     * The AGVManager Identity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    /**
     * A list of all the Squares
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<Square> squareList;

    /**
     * A list with all the AGVs
     */
    @OneToMany
    private List<AGV> agvList;

    /**
     * A list with the AGVDocks
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<AGVDock> agvDockList;

    /**
     * Mandatory empty constructor for ORM
     */
    protected AGVManager() {
    }

    /**
     * Constructor for the AGVManager
     *
     * @param squareList  A List object for Squares
     * @param agvList     A List object for AGV
     * @param agvDockList A List object for AGV Docks
     */
    public AGVManager(final List<Square> squareList, final List<AGV> agvList, final List<AGVDock> agvDockList) {
        Preconditions.noneNull(squareList, agvList, agvDockList);

        this.squareList = squareList;
        this.agvList = agvList;
        this.agvDockList = agvDockList;
    }


    public static AGVManager valueOf() {
        List<Square> squareList = new ArrayList<>();
        List<AGV> agvList = new ArrayList<>();
        List<AGVDock> dockList = new ArrayList<>();

        return new AGVManager(squareList, agvList, dockList);
    }

    /**
     * Adds a new square to the Square list stored by the AGV Manager
     *
     * @param square the Square to be added
     * @return True if successful, False if it couldn't add the Square
     */
    public boolean addSquare(Square square) {
        if (squareList.contains(square))
            throw new IllegalArgumentException("The AGV Manager already contains that square!");

        return this.squareList.add(square);
    }

    /**
     * Returns the number of Squares the AGV Manager knows
     *
     * @return the number of Squares the AGV Manager knows
     */
    public int numberOfSquares() {
        return this.squareList.size();
    }

    public boolean removeSquare(Square square) {
        return this.squareList.remove(square);
    }

    /**
     * Adds a new square to the Square list stored by the AGV Manager
     *
     * @param agv the AGV to be added
     * @return True if successful, False if it couldn't add the AGV
     */
    public boolean addAGV(AGV agv) {
        if (agvList.contains(agv))
            throw new IllegalArgumentException("The AGV Manager already contains that AGV!");

        return this.agvList.add(agv);
    }

    /**
     * Returns the number of AGV the AGV Manager knows
     *
     * @return the number of AGV the AGV Manager knows
     */
    public int numberOfAGV() {
        return this.agvList.size();
    }

    public boolean removeAGV(AGV agv) {
        return this.agvList.remove(agv);
    }

    /**
     * Adds a new square to the AGVDock list stored by the AGV Manager
     *
     * @param dock the AGVDock to be added
     * @return True if successful, False if it couldn't add the AGVDock
     */
    public boolean addAGVDock(AGVDock dock) {
        if (agvDockList.contains(dock))
            throw new IllegalArgumentException("The AGV Manager already contains that AGV Dock!");

        return this.agvDockList.add(dock);
    }

    /**
     * Returns the number of AGV Docks the AGV Manager knows
     *
     * @return the number of AGV Docks the AGV Manager knows
     */
    public int numberOfAGVDock() {
        return this.agvDockList.size();
    }

    public boolean removeAGVDock(AGVDock dock) {
        return this.agvDockList.remove(dock);
    }

    /**
     * Checks if the 2 objects are the same
     *
     * @param other an Object
     * @return True if the objects are the same, False if not
     */
    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    /**
     * Returns the Identity of the AGVManager
     *
     * @return Identity of the AGVManager
     */
    @Override
    public Long identity() {
        return id;
    }
}
