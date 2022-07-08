package eapli.base.common.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;
import org.apache.commons.lang3.EnumUtils;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Volume implements ValueObject, Comparable<Volume>{


    /**
     * The volume amount
     */
    private double volume;

    /**
     * Units in which the volume was measured
     */
    @Enumerated(EnumType.STRING)
    private VolumeUnit volumeUnit;

    /**
     * Constructor for the Volume Class
     * @param volume The volume amount
     * @param volumeUnit  Units in which the volume was measured
     */
    public Volume(double volume, VolumeUnit volumeUnit){
        if (volume <= 0)
            throw new IllegalArgumentException("The volume of a product cannot be 0 or less.");
        if(volumeUnit == null)
            throw new IllegalArgumentException("Volume units must be be specified.");

        this.volume = volume;
        this.volumeUnit = volumeUnit;
    }

    /**
     * empty constructor for ORM
     */
    public Volume() {
        //empty for ORM
    }

    /**
     * creates a volume object by multiplying the value of the previous volume object
     * @param volume the previous volume
     * @param multiplier the amount by which the previous value will be multiplied
     * @param unit the new unit of measurement
     * @return  New volume object with new information based on the previous one
     */
    protected static Volume valueOf(Volume volume, double multiplier, VolumeUnit unit) {
        return new Volume(volume.volume * multiplier, unit);
    }

    /**
     * Creates a new volume object with the provided information
     * @param amount The volume amount
     * @param unit Units in which the volume was measured
     * @return a new volume object with the provided information
     */
    public static Volume valueOf(final double amount,final VolumeUnit unit) {
        return new Volume(amount, unit);
    }

    /**
     * Creates a new volume object based on a string input
     * Ex: 12.43 cm; 35.98 Dm
     * Throws an exception if more than one space exists in the string
     * @param amount A string detailing the volume amount and it's
     * @return a new volume object based on a string input
     */
    public static Volume valueOf(final String amount) {
        final String[] parts = amount.split(" ");
        Preconditions.areEqual(parts.length, 2);

        final double am = Double.parseDouble(parts[0]);
        return new Volume(am,  EnumUtils.getEnumIgnoreCase(VolumeUnit.class, parts[1]));
    }

    /**
     * returns the measurement unit
     * @return the measurement unit
     */
    public VolumeUnit Unit(){
        return volumeUnit;
    }

    /**
     * returns the volume amount
     * @return the volume amount
     */
    public double value() {return volume;}

    /**
     * checks if 2 volumes are the same taking into account the measuring unit
     * @param o the other object
     * @return true if they are equal and false if they arent
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Volume)) {
            return false;
        }

        final Volume other = (Volume) o;
        return VolumeUnit.convert(this, VolumeUnit.MM).volume == VolumeUnit.convert(other, VolumeUnit.MM).volume;
    }

    /**
     * @return transforms this objet to string form
     */
    @Override
    public String toString() {
        return String.format("%.2f %s", volume, volumeUnit);
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
    public int compareTo(Volume o) {
        return Double.compare(VolumeUnit.convert(this, VolumeUnit.MM).volume, VolumeUnit.convert(o, VolumeUnit.MM).volume);
    }


}
