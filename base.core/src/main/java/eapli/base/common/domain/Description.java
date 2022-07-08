package eapli.base.common.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class Description implements ValueObject, Comparable<Description> {


    /**
     * A description
     */
    private String description;

    /**
     * Constructor for description object
     * @param description the description
     */
    public Description(final String description) {
        Preconditions.nonEmpty(description, "Description cannot be empty");

        this.description = description;
    }

    /**
     * empty constructor for ORM
     */
    protected Description() {
        // for ORM
    }

    /**
     * Creates a Description object
     * @param description the description
     * @return a new Description object
     */
    public static Description valueOf(final String description) {
        return new Description(description);
    }

    /**
     * Returns the length of the description
     * @return length of the description
     */
    public int length(){
        return description.length();
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
        if (!(o instanceof Description)) {
            return false;
        }

        final Description other = (Description) o;
        return description.equals(other.description);
    }

    /**
     * @return transforms this objet to string form
     */
    @Override
    public String toString() {
        return description;
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(description).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(Description o) {
        return description.compareTo(o.description);
    }
}

