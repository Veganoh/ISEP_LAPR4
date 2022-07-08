package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class Brand implements ValueObject, Comparable<Brand> {


    /**
     * A Brand name
     */
    private String brandName;

    /**
     * Constructor for brand object
     * @param brandName the Brand name
     */
    public Brand(final String brandName) {
        Preconditions.nonEmpty(brandName, "Product must have a Brand");

        if (brandName.length() > 50)
            throw new IllegalArgumentException("Brand name cannot have more than 50 characters");

        this.brandName = brandName;
    }

    /**
     * empty constructor for ORM
     */
    protected Brand() {
        // for ORM
    }

    /**
     * Creates a brand object
     * @param brandName the brand name
     * @return a new brand object
     */
    public static Brand valueOf(final String brandName) {
        return new Brand(brandName);
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
        if (!(o instanceof Brand)) {
            return false;
        }

        final Brand other = (Brand) o;
        return brandName.equals(other.brandName);
    }

    /**
     * @return transforms this object to string form
     */
    @Override
    public String toString() {
        return brandName;
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(brandName).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(Brand o) {
        return brandName.compareTo(o.brandName);
    }
}
