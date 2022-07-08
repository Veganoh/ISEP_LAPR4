package eapli.base.productcategorymanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;

import javax.persistence.Embeddable;


@Embeddable
public class Tax implements ValueObject, Comparable<Tax> {


    /**
     * Tax rate used to calculate interest values
     */
    private double tax;

    /**
     * Class constructor, ensures that tax cannot be negative
     * @param tax the Tax rate
     */
    public Tax(final double tax) {
        if (tax < 0) {
            throw new IllegalArgumentException(
                    "Tax rates cannot be bellow 0");
        }

        this.tax = tax;
    }

    /**
     * empty constructor for ORM
     */
    protected Tax() {
        // for ORM
    }

    /**
     * Creates a Tax object
     * @param tax the Tax rate
     * @return a new Tax object
     */
    public static Tax valueOf(final double tax) {
        return new Tax(tax);
    }

    /**
     * @return the tax value as a decimal floating point number
     */
    public double value() {
        return tax/100;
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
        if (!(o instanceof Tax)) {
            return false;
        }

        final Tax other = (Tax) o;
        return tax == other.tax;
    }

    /**
     * @return transforms this objet to string form
     */
    @Override
    public String toString() {
        return String.format("%.4f" , tax)+ "%";
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(tax).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(final Tax o) {
        return Double.compare(tax, o.tax);
    }
}
