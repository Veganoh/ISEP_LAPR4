package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;
@Embeddable
public class ShelfNumber implements ValueObject,Comparable<ShelfNumber> {


    /**
     * Shelf's number
     */
    private String identifier;

    /**
     * Constructor for ShelfNumber object
     * @param identifier the Shelf's number
     */
    protected ShelfNumber(final String identifier){
        Preconditions.nonEmpty(identifier,"ID cannot be empty");

        if(!Pattern.matches("[0-9]+",identifier))
            throw new IllegalArgumentException("Shelf number must be a positive numeric");

        this.identifier = identifier;
    }

    /**
     * Mandatory empty constructor for ORM
     */
    protected ShelfNumber(){
    }

    /**
     * Creates an ShelfNumber object
     * @param identifier the Shelf's number
     * @return a new ShelfNumber object
     */
    public static ShelfNumber valueOf(final String identifier){ return new ShelfNumber(identifier);}

    /**
     * Returns true if both objects are equal or false otherwise
     * @param o the other object
     * @return true if both objects are equal or false otherwise
     */
    @Override
    public boolean equals(final Object o){
        if(this == o){
            return true;
        }

        if(!(o instanceof  ShelfNumber)){
            return false;
        }

        final ShelfNumber other = (ShelfNumber) o;
        return identifier.equals((other.identifier));
    }

    /**
     * @return transforms the object to string form
     */
    @Override
    public String toString(){ return identifier;}

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() { return new HashCoder().with(identifier).code();}

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(ShelfNumber o){ return identifier.compareTo(o.identifier);}
}
