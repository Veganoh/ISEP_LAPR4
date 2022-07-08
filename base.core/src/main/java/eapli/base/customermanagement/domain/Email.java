package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
public class Email implements ValueObject, Comparable<Email> {

    private static final long serialVersionUID = 1L;

    /**
     * The Customer Email
     */
    private String email;

    /**
     * Constructor for Email object
     * @param email the Costumer email
     */
    public Email(final String email){
        Preconditions.nonEmpty(email, "Customer must have an email");

        if(!Pattern.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z0-9]+",email)){
            throw new IllegalArgumentException("Invalid email");
        }

        this.email=email;
    }

    /**
     * empty constructor for ORM
     */
    protected Email(){
        //for ORM
    }

    /**
     * Creates a Email object
     * @param email the Customer Email
     * @return a new Email object
     */
    public static Email valueOf(final String email) {
        return new Email(email);
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
        if (!(o instanceof Email)) {
            return false;
        }

        final Email other = (Email) o;
        return email.equals(other.email);
    }

    /**
     * @return transforms this object to string form
     */
    @Override
    public String toString() {
        return email;
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(email).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(Email o) {
        return email.compareTo(o.email);
    }
}
