package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class WarehouseId implements ValueObject, Comparable<WarehouseId> {

    /**
     * Warehouse's ID
     */

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long identifier;


    /**
     * Mandatory empty constructor for ORM
     */
    protected WarehouseId(){
    }

    /**
     * Constructor for Warehouse object
     * @param identifier the Warehouse ID
     */
    public WarehouseId(final Long identifier){
        Preconditions.nonNull(identifier);

        if(identifier < 0)
            throw new IllegalArgumentException("Warehouse ID must be a positive numeric");

        this.identifier = identifier;
    }

    /**
     * Creates a Warehouse object
     * @param identifier the Warehouse identifier
     * @return a new WarehouseId object
     */
    public static WarehouseId valueOf(final Long identifier){ return new WarehouseId(identifier);}


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

        if(!(o instanceof  WarehouseId)){
            return false;
        }

        final WarehouseId other = (WarehouseId) o;
        return identifier.equals(other.identifier);
    }

    /**
     * @return transforms the object to string form
     */
    @Override
    public String toString(){ return Long.toString(identifier);}

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
    public int compareTo(WarehouseId o){ return Long.compare(identifier, o.identifier);}
}