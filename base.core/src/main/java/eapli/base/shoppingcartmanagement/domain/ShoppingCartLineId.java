package eapli.base.shoppingcartmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

@Embeddable
public class ShoppingCartLineId implements ValueObject, Comparable<ShoppingCartLineId> {

    private static final long serialVersionUID = 1L;

    /**
     * ShoppingCartItem identifier
     */
    @GeneratedValue
    private Long identifier;

    /**
     * empty constructor for ORM
     */
    protected ShoppingCartLineId() {
        // for ORM
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
        if (!(o instanceof ShoppingCartLineId)) {
            return false;
        }

        final ShoppingCartLineId other = (ShoppingCartLineId) o;
        return identifier == (other.identifier);
    }

    /**
     * @return transforms this object to string form
     */
    @Override
    public String toString() {
        return Long.toString(identifier);
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(identifier).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(ShoppingCartLineId o) {
        return Long.compare(identifier, o.identifier);
    }
}
