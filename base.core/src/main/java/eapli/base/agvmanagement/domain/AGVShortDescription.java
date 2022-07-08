package eapli.base.agvmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

public class AGVShortDescription implements ValueObject, Comparable<AGVShortDescription> {
    private String shortDescription;

    /**
     * A Constructor for creating AGVShortDescription objects
     *
     * @param shortDescription the AGV Short Description
     */
    protected AGVShortDescription(final String shortDescription) {
        Preconditions.nonEmpty(shortDescription, "The AGV Short Description cannot be empty!");

        if (shortDescription.length() > 30)
            throw new IllegalArgumentException("The AGV's Short Description mustn't have more than 30 characters!");

        this.shortDescription = shortDescription;
    }

    /**
     * empty constructor for ORM
     */
    protected AGVShortDescription() {
        // for ORM
    }

    /**
     * Creates an AGVShortDescription object
     *
     * @param model the AGV Short Description
     * @return An AGVShortDescription Object
     */
    public static AGVShortDescription valueOf(final String model) {
        return new AGVShortDescription(model);
    }

    /**
     * Returns the length of the description
     * @return length of the description
     */
    public int length(){
        return shortDescription.length();
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
        if (!(o instanceof AGVShortDescription)) {
            return false;
        }

        final AGVShortDescription other = (AGVShortDescription) o;
        return shortDescription.equals(other.shortDescription);
    }

    /**
     * @return transforms this objet to string form
     */
    @Override
    public String toString() {
        return shortDescription;
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(shortDescription).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(AGVShortDescription o) {
        return shortDescription.compareTo(o.shortDescription);
    }
}
