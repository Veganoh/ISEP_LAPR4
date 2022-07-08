package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
public class Gender implements ValueObject, Comparable<Gender>{

    private static final long serialVersionUID = 1L;

    /**
     * The Customer Gender
     */
    private String gender;

    /**
     * Constructor for Gender object
     * @param gender the Customer Gender
     */
    protected Gender(String gender){
        Preconditions.nonEmpty(gender, "Customer must specify a gender");

        if (!Pattern.matches("Male|Female", gender)) {
            throw new IllegalArgumentException("Gender isn't on the system");
        }

        this.gender=gender;
    }

    /**
     * empty constructor for ORM
     */
    protected Gender(){
        //for ORM
    }

    /**
     * Creates a Gender object
     * @param gender the Customer's gender
     * @return a new Gender object
     */
    public static Gender valueOf(String gender) {
        return new Gender(gender);
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
        if (!(o instanceof Gender)) {
            return false;
        }

        final Gender other = (Gender) o;
        return gender.equals(other.gender);
    }

    /**
     * @return transforms this object into a string form
     */
    @Override
    public String toString() {
        return gender;
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(gender).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(Gender o) {
        return gender.compareTo(o.gender);
    }
}
