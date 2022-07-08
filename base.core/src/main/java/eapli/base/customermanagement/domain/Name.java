package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import java.util.regex.Pattern;

public class Name implements ValueObject, Comparable<Name>{

    private static final long serialVersionUID = 1L;

    /**
     * The Customer name
     */
    private String name;

    /**
     * Constructor for Name
     * @param name the customer's name
     */
    protected Name(String name){
        Preconditions.nonEmpty(name, "Customer must have a name");

        if(!Pattern.matches("[a-zA-Z]+( [a-zA-Z]+)*",name)){
            throw new IllegalArgumentException("Invalid name");
        }

        this.name = name;
    }

    /**
     * empty constructor for ORM
     */
    protected Name(){
        // for ORM
    }

    /**
     * Creates a Name object
     * @param name the Customer's name
     * @return a new Name object
     */
    public static Name valueOf(String name) {
        return new Name(name);
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
        if (!(o instanceof Name)) {
            return false;
        }

        final Name other = (Name) o;
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
        return new HashCoder().with(name).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(Name o) {
        return name.compareTo(o.name);
    }
}
