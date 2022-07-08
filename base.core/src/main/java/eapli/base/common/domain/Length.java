package eapli.base.common.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Length implements ValueObject, Comparable<Length> {

    /**
     * Length
     */
    private long wlength;

    /**
     * Constructor for Length object
     * @param length the length
     */
    public Length(final long length) {
        if (length <= 0)
            throw new IllegalArgumentException("Invalid length, must not be negative or zero.");
        else
            this.wlength = length;
    }

    /**
     * Mandatory empty constructor for ORM
     */
    protected Length() {
    }

    /**
     * Gets the length
     * @return the length
     */
    public long value() {
        return wlength;
    }

    /**
     * Creates a Length object
     * @param length the length
     * @return a new Length object
     */
    public static Length valueOf(final long length) {
        return new Length(length);
    }

    /**
     * Returns true if both objects are equal or false otherwise
     * @param o the other object
     * @return true if both objects are equal or false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Length)) {
            return false;
        }

        final Length that = (Length) o;
        return this.wlength == that.wlength;
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return Long.hashCode(wlength);
    }

    /**
     * @return transforms this object to string form
     */
    @Override
    public String toString() {
        return String.format("%d", wlength);
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(Length o) {
        return Long.compare(this.wlength, o.wlength);
    }
}
