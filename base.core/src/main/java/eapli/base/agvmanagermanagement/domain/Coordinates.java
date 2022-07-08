package eapli.base.agvmanagermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class Coordinates implements ValueObject, Comparable<Coordinates> {
    private int wSquare;

    private int lSquare;

    /**
     Mandatory empty constructor for ORM
     */
    protected Coordinates() {}

    /**
     * Constructor for a Coordinates Object
     * @param wSquare the width axis (y)
     * @param lSquare the length axis (x)
     */
    protected Coordinates(final int wSquare, final int lSquare) {
        if (wSquare == 0 || lSquare == 0)
            throw new IllegalArgumentException("The Coordinates can't be 0 (starts at 1)");

        Preconditions.isPositive(wSquare, "The Coordinates must be positive!");
        Preconditions.isPositive(lSquare, "The Coordinates must be positive!");

        this.wSquare = wSquare;
        this.lSquare = lSquare;
    }

    /**
     * Creates a new Coordinates Object.
     * The Coordinates must be positive and can't be 0.
     *
     * @param wSquare the width axis (y)
     * @param lSquare the length axis (x)
     * @return A Coordinates Object
     */
    public static Coordinates valueOf(final int wSquare, final int lSquare) {
        return new Coordinates(wSquare, lSquare);
    }

    public int widthSquare() {
        return wSquare;
    }

    public int lengthSquare() {
        return lSquare;
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
        if (!(o instanceof Coordinates)) {
            return false;
        }

        final Coordinates other = (Coordinates) o;
        return wSquare == other.wSquare && lSquare == other.lSquare;
    }

    /**
     * @return transforms this objet to string form
     */
    @Override
    public String toString() {
        return String.format("wSquare: %d | lSquare: %d", wSquare, lSquare);
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(wSquare - lSquare).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(Coordinates o) {
        return (wSquare < o.wSquare) ? -1 :
                ((wSquare > o.wSquare) ? 1 :
                        Integer.compare(lSquare, o.lSquare));
    }
}
