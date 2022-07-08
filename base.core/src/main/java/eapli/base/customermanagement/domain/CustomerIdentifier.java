package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
public class CustomerIdentifier implements ValueObject, Comparable<CustomerIdentifier> {

    private static final long serialVersionUID = 1L;

    /**
     * The Customer Identifier
     */
    private String identifier;

    /**
     * Constructor for CustomerIdentifier
     * @param identifier the customer identifier
     */
    public CustomerIdentifier(String identifier){
        Preconditions.nonEmpty(identifier, "Customer must have an identifier");

        if(!Pattern.matches("[a-zA-Z0-9]+",identifier)){
            throw new IllegalArgumentException("Invalid identifier");
        }

        this.identifier = identifier;
    }

    /**
     * empty constructor for ORM
     */
    protected CustomerIdentifier() {
        // for ORM
    }

    /**
     * Creates an CustomerIdentifier object
     * @param identifier the Customer identifier
     * @return a new CustomerIdentifier object
     */
    public static CustomerIdentifier valueOf(String identifier) {
        return new CustomerIdentifier(identifier);
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
        if (!(o instanceof CustomerIdentifier)) {
            return false;
        }

        final CustomerIdentifier other = (CustomerIdentifier) o;
        return identifier.equals(other.identifier);
    }

    /**
     * @return transforms this object to string form
     */
    @Override
    public String toString() {
        return identifier;
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
    public int compareTo(CustomerIdentifier o) {
        return identifier.compareTo(o.identifier);
    }
}
