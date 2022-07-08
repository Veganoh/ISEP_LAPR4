package eapli.base.shipmentmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class Carrier implements ValueObject, Comparable<Carrier> {

    private static final long serialVersionUID = 1L;

    private String name;

    /**
     * Constructor for Carrier object
     * @param name the Carrier name
     */
    protected Carrier(final String name) {
        setName(name);
    }

    /**
     * empty constructor for ORM
     */
    protected Carrier() {
        // for ORM
    }

    private void setName(String name) {
        Preconditions.nonEmpty(name, "Carrier must include a name");
        this.name = name;
    }

    /**
     * Creates a Carrier object
     * @param name the Carrier name
     * @return a new Carrier object
     */
    public static Carrier valueOf(final String name) {
        return new Carrier(name);
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
        if (!(o instanceof Carrier)) {
            return false;
        }

        final Carrier other = (Carrier) o;
        return name.equals(other.name);
    }

    /**
     * @return transforms this object to string form
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(Carrier o) {
        return this.name.compareTo(o.name);
    }
}
