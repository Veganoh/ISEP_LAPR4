package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
public class ProductCode implements ValueObject, Comparable<ProductCode> {

    /**
     * The Product Code
     */
    private String internalCode;

    /**
     * Constructor for Product Code object
     * @param internalCode the Product Code
     */
    public ProductCode(final String internalCode) {
        Preconditions.nonEmpty(internalCode, "Product must include an internal code");

        if (!Pattern.matches("[a-zA-Z]{4}.[0-9]{5}", internalCode))
            throw new IllegalArgumentException("Invalid internal code ");

        this.internalCode = internalCode;
    }

    /**
     * empty constructor for ORM
     */
    protected ProductCode() {
        // for ORM
    }

    /**
     * Creates a Product Code object
     * @param referenceCode the Product Code
     * @return a new Product Code object
     */
    public static ProductCode valueOf(final String referenceCode) {
        return new ProductCode(referenceCode);
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
        if (!(o instanceof ProductCode)) {
            return false;
        }

        final ProductCode other = (ProductCode) o;
        return internalCode.equals(other.internalCode);
    }

    /**
     * @return transforms this object to string form
     */
    @Override
    public String toString() {
        return internalCode;
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(internalCode).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(ProductCode o) {
        return internalCode.compareTo(o.internalCode);
    }
}
