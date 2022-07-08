package eapli.base.common.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Width implements ValueObject, Comparable<Width> {

    /**
     * width
     */
    private long width;

    /**
     * Constructor for width object
     * @param width the width
     */
    public Width(final long width) {
        if (width <= 0)
            throw new IllegalArgumentException("Invalid width, must not be negative or zero.");
        else
            this.width = width;
    }

    /**
     * Mandatory empty constructor for ORM
     */
    protected Width() {
    }

    /**
     * Gets the width
     * @return the width
     */
    public long value() {
        return width;
    }

    /**
     * Creates a Width object
     * @param width the length
     * @return a new Width object
     */
    public static Width valueOf(final long width) {
        return new Width(width);
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
        if (!(o instanceof Width)) {
            return false;
        }

        final Width that = (Width) o;
        return this.width == that.width;
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return Long.hashCode(width);
    }

    /**
     * @return transforms this object to string form
     */
    @Override
    public String toString() {
        return String.format("%d", width);
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(Width o) {
        return Long.compare(this.width, o.width);
    }
}
