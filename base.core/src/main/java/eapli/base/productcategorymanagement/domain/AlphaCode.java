package eapli.base.productcategorymanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class AlphaCode implements ValueObject, Comparable<AlphaCode> {


    /**
     * Alpha code used to identify a category
     */
    private String alphaCode;

    /**
     * Constructor for Alpha code object
     * @param alphaCode the alpha code
     */
    public AlphaCode(final String alphaCode) {
        Preconditions.nonEmpty(alphaCode, "Alpha code cannot be empty");

        if (alphaCode.length() > 10)
            throw new IllegalArgumentException("Alpha-code must have 10 or less characters.");

        this.alphaCode = alphaCode;
    }

    /**
     * empty constructor for ORM
     */
    protected AlphaCode() {
        // for ORM
    }

    /**
     * Creates a AlphaCode object
     * @param alphaCode the alpha code
     * @return a new AlphaCode object
     */
    public static AlphaCode valueOf(final String alphaCode) {
        return new AlphaCode(alphaCode);
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
        if (!(o instanceof AlphaCode)) {
            return false;
        }

        final AlphaCode other = (AlphaCode) o;
        return alphaCode.equals(other.alphaCode);
    }

    /**
     * @return transforms this objet to string form
     */
    @Override
    public String toString() {
        return alphaCode;
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(alphaCode).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(AlphaCode o) {
        return alphaCode.compareTo(o.alphaCode);
    }
}

