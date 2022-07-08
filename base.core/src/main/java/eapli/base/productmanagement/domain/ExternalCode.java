package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
public class ExternalCode implements ValueObject, Comparable<ExternalCode> {

    /**
     * The Production Code
     */
    private String productionCode;

    /**
     * Constructor for Production Code object
     * @param productionCode the External Code
     */
    public ExternalCode(final String productionCode) {
        Preconditions.nonEmpty(productionCode, "Product must include an external code");

        if (!Pattern.matches("[a-zA-Z]{4}.[0-9]{5}", productionCode))
            throw new IllegalArgumentException("Invalid production code ");

        this.productionCode = productionCode;
    }

    /**
     * empty constructor for ORM
     */
    protected ExternalCode() {
        // for ORM
    }

    /**
     * Creates a External Code object
     * @param referenceCode the External Code
     * @return a new External Code object
     */
    public static ExternalCode valueOf(final String referenceCode) {
        return new ExternalCode(referenceCode);
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
        if (!(o instanceof ExternalCode)) {
            return false;
        }

        final ExternalCode other = (ExternalCode) o;
        return productionCode.equals(other.productionCode);
    }

    /**
     * @return transforms this object to string form
     */
    @Override
    public String toString() {
        return productionCode;
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(productionCode).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(ExternalCode o) {
        return productionCode.compareTo(o.productionCode);
    }
}
