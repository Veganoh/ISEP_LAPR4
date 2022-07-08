package eapli.base.warehousemanagement.domain;

import eapli.base.agvmanagermanagement.domain.AGVManager;
import eapli.base.common.domain.Description;
import eapli.base.common.domain.Length;
import eapli.base.common.domain.VolumeUnit;
import eapli.base.common.domain.Width;
import eapli.base.warehousemanagement.application.JsonFileParser;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Warehouse implements AggregateRoot<Long> {

    /**
     * The warehouse's ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    /**
     * The warehouse's measurements
     */
    @Embedded
    private WarehouseMeasurements warehouseMeasurements;

    /**
     * The warehouse's description
     */
    @Embedded
    private Description warehouseDescription;

    /**
     * The warehouse's plant
     */
    @Embedded
    private WareHousePlant wareHousePlant;

    /**
     * The warehouse's shelves
     */
    @Transient
    private List<Shelf> shelves = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private AGVManager agvManager;

    /**
     * Constructor for the Warehouse object
     *
     * @param warehouseMeasurements the warehouse's measurements
     * @param warehouseDescription  the warehouse's description
     */
    public Warehouse(WarehouseMeasurements warehouseMeasurements, Description warehouseDescription, AGVManager agvManager) {

        Preconditions.noneNull(warehouseMeasurements, warehouseDescription, agvManager);

        this.warehouseDescription = warehouseDescription;
        this.warehouseMeasurements = warehouseMeasurements;
        this.agvManager = agvManager;
    }

    /**
     * Mandatory empty constructor for ORM
     */
    protected Warehouse() {
    }

    /**
     * Adds a shelf to the list, by default the list is empty
     *
     * @param shelf a new photo to be added to the product
     * @return true if it is added and false if it is not
     */
    public boolean addShelf(Shelf shelf) {
        if (!shelves.contains(shelf)) return shelves.add(shelf);
        throw new IllegalArgumentException("This shelf is already registered");
    }

    /**
     * updates the warehouse plant
     * @param newPlant the new plant
     */
    public void updatePlant(WareHousePlant newPlant){
        this.wareHousePlant = newPlant;
    }

    public void loadShelves(){
        if (wareHousePlant == null)
            throw new IllegalArgumentException("There is no plant that the shelves can be loaded from");
        JsonFileParser jsonFileParser =  new JsonFileParser();
        shelves.clear();
        jsonFileParser.shelves(this);
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    /**
     * returns true if this object has the same identity as another product
     *
     * @param o the other object
     * @return true if they are equal
     */
    @Override
    public boolean sameAs(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    /**
     * returns the warehouse identity
     *
     * @return the warehouse identity
     */
    @Override
    public Long identity() {
        return id;
    }

    public SquareSize squareSize() {
        return warehouseMeasurements.squareSize();
    }

    public Length length() {
        return warehouseMeasurements.length();
    }

    public Width width() {
        return warehouseMeasurements.width();
    }

    public VolumeUnit unit() {
        return warehouseMeasurements.unit();
    }

    public Description description() {
        return warehouseDescription;
    }

    public List<Shelf> shelves() {
        return shelves;
    }

    public AGVManager agvManager() {return agvManager;}

    public WareHousePlant plant() {
        return wareHousePlant;
    }


}
