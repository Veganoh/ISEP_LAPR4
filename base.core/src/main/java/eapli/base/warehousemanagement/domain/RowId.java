package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
public class RowId implements ValueObject, Comparable<RowId> {

    /**
     * Row's ID
     */
    private String identifier;

    /**
     * Constructor for ShelfID object
     * @param identifier the Aisle ID
     */
    protected RowId(final String identifier){
        Preconditions.nonEmpty(identifier,"ID cannot be empty");

        if(!Pattern.matches("[0-9]+",identifier))
            throw new IllegalArgumentException("Row ID must be a positive numeric");

        this.identifier = identifier;
    }

    /**
     * Mandatory empty constructor for ORM
     */
    protected RowId(){
    }

    /**
     * Creates a RowId object
     * @param identifier the Row identifier
     * @return a new RowId object
     */
    public static RowId valueOf(final String identifier){ return new RowId(identifier);}

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

        if(!(o instanceof  RowId)){
            return false;
        }

        final RowId other = (RowId) o;
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
    public int compareTo(RowId o){ return identifier.compareTo(o.identifier);}
}