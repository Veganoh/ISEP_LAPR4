package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductAvailability implements ValueObject, Comparable<ProductAvailability>{

    /**
     * Represents the availability of a product
     */
    @Column(nullable = false)
    private boolean availability;

    /**
     * Creates the availability class
     * @param availability the availability of a product
     */
    public ProductAvailability(boolean availability){
        this.availability = availability;
    }

    /**
     * empty constructor for ORM
     */
    protected ProductAvailability() {
        //empty for ORM
    }

    /**
     * Creates a new availability object from a bool
     * @param availability the availability of a product
     * @return a new availability object from a bool
     */
    public static ProductAvailability valueOf(boolean availability) {
        return new ProductAvailability(availability);
    }

    /**
     * Confirms the availability of the current object
     * @return a boolean
     */
    public boolean isAvailable(){
        return availability;
    }

    /**
     * checks if 2 availability object are equal
     * @param o the other object
     * @return true if they are equal and false if they aren't
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductAvailability)) {
            return false;
        }

        final ProductAvailability other = (ProductAvailability) o;
        return availability == other.availability;
    }

    /**
     * @return transforms this objet to string form
     */
    @Override
    public String toString() {
        return availability ? "Product is available for retail." : "Product is not available for retail.";
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(availability).code();
    }


    /**
     * Compares a boolean to another
     * @param o the other object to be compared to
     * @return 0 if they are equal 1 if this one is true and the other false -1 if this one is false and the other true
     */
    @Override
    public int compareTo(ProductAvailability o) {
        return Boolean.compare(availability, o.availability);
    }
}
