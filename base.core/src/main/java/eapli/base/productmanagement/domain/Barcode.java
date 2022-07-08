package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import org.apache.commons.lang3.EnumUtils;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Barcode implements ValueObject, Comparable<Barcode>{


    /**
     * The weight amount
     */
    private String barcode;

    /**
     * Units in which the weight was measured
     */
    @Enumerated(EnumType.STRING)
    private CodingStandard standard;

    /**
     * Constructor for the barcode Class
     * @param barcode The barcode
     * @param standard The coding standard utilized by the  barcode
     */
    public Barcode(String barcode, CodingStandard standard){

        if(standard == null)
            throw new IllegalArgumentException("A coding standard must be present.");
        if (!CodingStandard.validate(barcode, standard))
            throw new IllegalArgumentException("The barcode does not match the barcode standard stated.");

        this.barcode = barcode;
        this.standard = standard;
    }

    /**
     * empty constructor for ORM
     */
    protected Barcode() {
        //empty for ORM
    }

    /**
     * creates a barcode object
     * @param barcode the barcode
     * @param standard the barcode standard
     * @return a new barcode with the new information
     */
    public static Barcode valueOf(final String barcode, CodingStandard standard) {
        return new Barcode(barcode, standard);
    }

    /**
     * creates a barcode object by implying which coding standard is being used
     * @param barcode the barcode
     * @return a new barcode with the new information or throws an exception if the barcode does not match any standard
     * in the project
     */
    public static Barcode valueOf(final String barcode) {
        for (CodingStandard standard : EnumUtils.getEnumList(CodingStandard.class) )
            if (CodingStandard.validate(barcode, standard))
                return new Barcode(barcode, standard);
        throw new IllegalArgumentException("The barcode does not match any barcode standard present in this application.");
    }

    /**
     * checks if 2 weights are the same taking into account the measuring unit
     * @param o the other object
     * @return true if they are equal and false if they arent
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Barcode)) {
            return false;
        }

        final Barcode other = (Barcode) o;
        return barcode.equals(other.barcode);
    }

    /**
     * @return transforms this objet to string form
     */
    @Override
    public String toString() {
        return String.format("%s - %s", barcode, standard);
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(barcode).code();
    }

    /**
     * @param o the other object
     * @return 1 if this object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(Barcode o) {
        return barcode.compareTo(o.barcode);
    }
}
