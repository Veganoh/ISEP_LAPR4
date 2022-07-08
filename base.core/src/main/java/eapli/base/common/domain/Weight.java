package eapli.base.common.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;
import org.apache.commons.lang3.EnumUtils;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Weight implements ValueObject, Comparable<Weight>{

    /**
     * The weight amount
     */
    private double weight;

    /**
     * Units in which the weight was measured
     */
    @Enumerated(EnumType.STRING)
    private WeightUnit weightUnit;

    /**
     * Constructor for the weight Class
     * @param weight The weight amount
     * @param weightUnit  Units in which the weight was measured
     */
    public Weight(double weight, WeightUnit weightUnit){
        if (weight <= 0)
            throw new IllegalArgumentException("The weight of a product cannot be 0 or less.");
        if(weightUnit == null)
            throw new IllegalArgumentException("weight units must be be specified.");

        this.weight = weight;
        this.weightUnit = weightUnit;
    }

    /**
     * empty constructor for ORM
     */
    public Weight() {
        //empty for ORM
    }

    /**
     * creates a weight object by multiplying the value of the previous weight object
     * @param weight the previous weight
     * @param multiplier the amount by which the previous value will be multiplied
     * @param unit the new unit of measurement
     * @return  New weight object with new information based on the previous one
     */
    protected static Weight valueOf(Weight weight, double multiplier, WeightUnit unit) {
        return new Weight(weight.weight * multiplier, unit);
    }

    /**
     * Creates a new weight object with the provided information
     * @param amount The weight amount
     * @param unit Units in which the weight was measured
     * @return a new weight object with the provided information
     */
    public static Weight valueOf(final double amount,final WeightUnit unit) {
        return new Weight(amount, unit);
    }

    /**
     * Creates a new weight object based on a string input
     * Ex: 12.43 kg; 35.98 G
     * Throws an exception if more than one space exists in the string
     * @param amount A string detailing the weight amount and it's
     * @return a new weight object based on a string input
     */
    public static Weight valueOf(final String amount) {
        final String[] parts = amount.split(" ");
        Preconditions.areEqual(parts.length, 2);

        final double am = Double.parseDouble(parts[0]);
        return new Weight(am,  EnumUtils.getEnumIgnoreCase(WeightUnit.class, parts[1]));
    }

    /**
     * returns the measurement unit
     * @return the measurement unit
     */
    public WeightUnit Unit(){
        return weightUnit;
    }

    /**
     * returns the weight amount
     * @return the weight amount
     */
    public double value() {
        return weight;
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
        if (!(o instanceof Weight)) {
            return false;
        }

        final Weight other = (Weight) o;
        return WeightUnit.convert(this, WeightUnit.G).weight == WeightUnit.convert(other, WeightUnit.G).weight;
    }

    /**
     * @return transforms this objet to string form
     */
    @Override
    public String toString() {
        return String.format("%.2f %s", weight, weightUnit);
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(this).code();
    }

    /**
     * @param o the other object
     * @return 1 if this object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(Weight o) {
        return Double.compare(WeightUnit.convert(this, WeightUnit.G).weight, WeightUnit.convert(o, WeightUnit.G).weight);
    }

}
