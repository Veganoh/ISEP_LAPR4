package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class BrandReference implements ValueObject, Comparable<BrandReference> {

    /**
     * The brand's reference code
     */
    private String referenceCode;

    /**
     * Constructor for brand reference Code object
     * @param referenceCode the Brand reference code
     */
    public BrandReference(final String referenceCode) {
        Preconditions.nonEmpty(referenceCode, "Product must include a reference code");

        if (referenceCode.length() > 23)
            throw new IllegalArgumentException("Reference code cannot be bigger than 23 characters");

        this.referenceCode = referenceCode;
    }

    /**
     * empty constructor for ORM
     */
    protected BrandReference() {
        // for ORM
    }

    /**
     * Creates a brand reference Code object
     * @param referenceCode the brand reference Code
     * @return a new brand reference Code object
     */
    public static BrandReference valueOf(final String referenceCode) {
        return new BrandReference(referenceCode);
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
        if (!(o instanceof BrandReference)) {
            return false;
        }

        final BrandReference other = (BrandReference) o;
        return referenceCode.equals(other.referenceCode);
    }

    /**
     * @return transforms this object to string form
     */
    @Override
    public String toString() {
        return referenceCode;
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(referenceCode).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(BrandReference o) {
        return referenceCode.compareTo(o.referenceCode);
    }
}
