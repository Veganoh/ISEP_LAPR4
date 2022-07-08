package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class WareHousePlant implements ValueObject, Comparable<WareHousePlant> {

    /**
     * A Plant
     */
    @Lob
    private String plant;

    /**
     * Constructor for plant object
     * @param plant the plant
     */
    public WareHousePlant(final String plant) {
        Preconditions.nonEmpty(plant, "plant cannot be empty");

        this.plant = plant;
    }

    /**
     * empty constructor for ORM
     */
    protected WareHousePlant() {
        // for ORM
    }

    /**
     * Creates a plant object
     * @param plant the plant
     * @return a new plant object
     */
    public static WareHousePlant valueOf(final String plant) {
        return new WareHousePlant(plant);
    }

    /**
     * Return true if both object are equal or false otherwise
     * @param o the other object
     * @return true if both object are equal or false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WareHousePlant)) {
            return false;
        }

        final WareHousePlant other = (WareHousePlant) o;
        return plant.equals(other.plant);
    }

    /**
     * @return transforms this objet to string form
     */
    @Override
    public String toString() {
        return plant;
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(plant).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(WareHousePlant o) {
        return plant.compareTo(o.plant);
    }
}
