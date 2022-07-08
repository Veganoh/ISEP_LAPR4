package eapli.base.agvdockmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AGVDockAvailability implements ValueObject, Comparable<AGVDockAvailability>{

    /**
     * Represents the availability of an AGV Dock
     */
    @Column(nullable = false)
    private boolean availability;

    /**
     * Creates the availability class
     * @param availability the availability of an AGV Dock
     */
    public AGVDockAvailability(boolean availability){
        this.availability = availability;
    }

    /**
     * empty constructor for ORM
     */
    protected AGVDockAvailability() {
        //empty for ORM
    }

    /**
     * Creates a new availability object from a bool
     * @param availability the availability of an AGV Dock
     * @return a new availability object from a bool
     */
    public static AGVDockAvailability valueOf(boolean availability) {
        return new AGVDockAvailability(availability);
    }

    /**
     * Confirms the availability of the current object
     * @return a boolean
     */
    public boolean isAvailable(){
        return availability;
    }

    /**
     * checks if 2 availability object are equal
     * @param o the other object
     * @return true if they are equal and false if they aren't
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AGVDockAvailability)) {
            return false;
        }

        final AGVDockAvailability other = (AGVDockAvailability) o;
        return availability == other.availability;
    }

    /**
     * @return transforms this objet to string form
     */
    @Override
    public String toString() {
        return availability ? "AGV Dock is available." : "AGV Dock is not available.";
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(availability).code();
    }


    /**
     * Compares a boolean to another
     * @param o the other object to be compared to
     * @return 0 if they are equal 1 if this one is true and the other false -1 if this one is false and the other true
     */
    @Override
    public int compareTo(AGVDockAvailability o) {
        return Boolean.compare(availability, o.availability);
    }
}
