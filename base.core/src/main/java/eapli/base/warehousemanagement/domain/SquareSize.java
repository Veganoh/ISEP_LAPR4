package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class SquareSize implements ValueObject, Comparable<SquareSize> {

    /**
     * SquareSize
     */
    private long squareSize;

    /**
     * Constructor for SquareSize object
     * @param squareSize the squareSize
     */
    public SquareSize(final long squareSize) {
        Preconditions.nonNegative(squareSize,"Invalid Square Size, must not be negative");
        this.squareSize = squareSize;
    }

    /**
     * Mandatory empty constructor for ORM
     */
    protected SquareSize() {
    }

    /**
     * Gets the squareSize
     * @return the squareSize
     */
    public long value() {
        return squareSize;
    }

    /**
     * Creates a SquareSize object
     * @param squareSize the squareSize
     * @return a new SquareSize object
     */
    public static SquareSize valueOf(final long squareSize) {
        return new SquareSize(squareSize);
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
        if (!(o instanceof SquareSize)) {
            return false;
        }
        final SquareSize that = (SquareSize) o;
        return this.squareSize == that.squareSize;
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return Long.hashCode(squareSize);
    }

    /**
     * @return transforms this object to string form
     */
    @Override
    public String toString() {
        return String.format("%d", squareSize);
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(SquareSize o) {
        return Long.compare(this.squareSize, o.squareSize);
    }
}
