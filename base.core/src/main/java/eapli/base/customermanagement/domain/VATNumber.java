package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
public class VATNumber implements ValueObject, Comparable<VATNumber> {

    private static final long serialVersionUID = 1L;

    /**
     * The Customer VATNumber
     */
    private String vatNumber;

    /**
     * Constructor for VATNumber object
     * @param vatNumber the Customer VATNumber
     */
    protected VATNumber(String vatNumber){
        Preconditions.nonEmpty(vatNumber, "Customer must have a VAT number");

        if(!Pattern.matches("[a-zA-Z0-9]+", vatNumber)){
            throw new IllegalArgumentException("Invalid VAT number");
        }

        this.vatNumber = vatNumber;
    }

    /**
     * empty constructor for ORM
     */
    protected VATNumber(){
        //for ORM
    }

    /**
     * Creates a VATNumber object
     * @param vatNumber the Customer Vat Number
     * @return a new VATNumber object
     */
    public static VATNumber valueOf(String vatNumber) {
        return new VATNumber(vatNumber);
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
        if (!(o instanceof VATNumber)) {
            return false;
        }

        final VATNumber other = (VATNumber) o;
        return vatNumber.equals(other.vatNumber);
    }

    /**
     * @return transforms this object into a string form
     */
    @Override
    public String toString() {
        return vatNumber;
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(vatNumber).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(VATNumber o) {
        return vatNumber.compareTo(o.vatNumber);
    }
}
