package eapli.base.agvdockmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
public class AGVDockId implements ValueObject, Comparable<AGVDockId> {
    /**
     * AGV Dock Identifier
     */
    private String id;

    /**
     * Mandatory empty constructor for ORM
     */
    protected AGVDockId() {}

    /**
     * Constructor for the Class AGVDockId
     *
     * The Id can't be null and must be numeric
     * @param id the AGVDock identifier
     */
    protected AGVDockId(final String id) {
        Preconditions.nonEmpty(id, "AGV Dock must include an internal identifier");

        if (!Pattern.matches("[a-zA-Z0-9]+", id))
            throw new IllegalArgumentException("AGV Dock Id must be alphanumeric!");

        if (id.length() > 8)
            throw new IllegalArgumentException("AGV Dock Id can't have more than 8 characters!");

        this.id = id;
    }

    /**
     * Creates an AGV Dock Identifier
     * @param identifier of the AGV Dock
     * @return a new AGVDockId object
     */
    public static AGVDockId valueOf(final String identifier) {
        return new AGVDockId(identifier);
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
        if (!(o instanceof AGVDockId)) {
            return false;
        }

        final AGVDockId other = (AGVDockId) o;
        return id.equals(other.id);
    }

    /**
     * @return transforms this object to string form
     */
    @Override
    public String toString() {
        return id;
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(id).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(AGVDockId o) {
        return id.compareTo(o.id);
    }
}
