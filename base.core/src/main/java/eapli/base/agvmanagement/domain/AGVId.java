package eapli.base.agvmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
public class AGVId implements ValueObject, Comparable<AGVId> {
    /**
     * An AGV Identifier
     */
    private String id;

    /**
     * Mandatory empty constructor for ORM
     */
    protected AGVId() {}

    /**
     * A Constructor to create an AGV Identifier
     *
     * @param id an AGV Identifier
     */
    protected AGVId(final String id) {
        Preconditions.nonEmpty(id, "AGV must include an internal identifier!");

        if (!Pattern.matches("[0-9a-zA-Z]+", id))
            throw new IllegalArgumentException("AGV Identifier must be alphanumeric!");

        if (id.length() > 8)
            throw new IllegalArgumentException("AGV Identifier can't have more than 8 characters!");

        this.id = id;
    }

    /**
     * Creates an AGV Identifier
     *
     * @param identifier an AGV Identifier
     */
    public static AGVId valueOf(final String identifier) { return new AGVId(identifier); }

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
        if (!(o instanceof AGVId)) {
            return false;
        }

        final AGVId other = (AGVId) o;
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
    public int compareTo(AGVId o) {
        return id.compareTo(o.id);
    }
}
