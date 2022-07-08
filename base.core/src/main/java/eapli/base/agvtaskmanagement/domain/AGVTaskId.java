package eapli.base.agvtaskmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
public class AGVTaskId implements ValueObject, Comparable<AGVTaskId> {
    /**
     * An AGV Task Identifier
     */
    private String id;

    /**
     * Mandatory empty constructor for ORM
     */
    protected AGVTaskId() {}

    /**
     * A Constructor to create an AGV Task Identifier
     *
     * @param id an AGV Task Identifier
     */
    protected AGVTaskId(final String id) {
        Preconditions.nonEmpty(id, "AGV Task must include an internal identifier!");

        if (!Pattern.matches("[0-9]+", id))
            throw new IllegalArgumentException("AGV Task Identifier must be numeric!");

        this.id = id;
    }

    /**
     * Creates an AGV Task Identifier
     *
     * @param identifier an AGV Task Identifier
     */
    public static AGVTaskId valueOf(final String identifier) { return new AGVTaskId(identifier); }

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
        if (!(o instanceof AGVTaskId)) {
            return false;
        }

        final AGVTaskId other = (AGVTaskId) o;
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
    public int compareTo(AGVTaskId o) {
        return id.compareTo(o.id);
    }
}
