package eapli.base.warehousemanagement.domain;

import eapli.base.common.domain.Length;
import eapli.base.common.domain.VolumeUnit;
import eapli.base.common.domain.Width;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class WarehouseMeasurements implements ValueObject, Comparable<WarehouseMeasurements> {

    /**
     * The warehouse's square size
     */
    @Embedded
    private SquareSize squareSize;

    /**
     * The warehouse's length
     */
    @Embedded

    private Length wlength;

    /**
     * The warehouse's width
     */
    @Embedded
    private Width width;

    /**
     * The warehouse's metric volume
     */
    @Enumerated(EnumType.STRING)
    private VolumeUnit unit;

    /**
     * Constructor for WarehouseMeasurements object
     * @param squareSize the size of a square
     * @param length a numeric value specifying the length of the warehouse
     * @param width  a numeric value specifying the width of the warehouse
     * @param unit the metric unit (e.g.: centimeters, millimeters) in which the values of the Length, Width and Square fields are expressed.
     */
    public WarehouseMeasurements(final SquareSize squareSize,final Length length,final Width width,final VolumeUnit unit){
        Preconditions.noneNull(squareSize,length,width,unit);

        this.squareSize = squareSize;
        this.wlength = length;
        this.width = width;
        this.unit = unit;
    }


    /**
     * Mandatory empty constructor for ORM
     */
    protected WarehouseMeasurements(){
    }

    /**
     * Creates a WarehouseMeasurements object
     * @param squareSize the size of a square
     * @param length a numeric value specifying the length of the warehouse
     * @param width  a numeric value specifying the width of the warehouse
     * @param unit the metric unit (e.g.: centimeters, millimeters) in which the values of the Length, Width and Square fields are expressed.
     * @return a new WarehouseMeasurements object
     */
    public static WarehouseMeasurements valueOf(final SquareSize squareSize,final Length length,final Width width,final VolumeUnit unit){
        return new WarehouseMeasurements(squareSize,length,width,unit);
    }

    /**
     * Creates a WarehouseMeasurements object
     * @param squareSize the size of a square
     * @param length a numeric value specifying the length of the warehouse
     * @param width  a numeric value specifying the width of the warehouse
     * @param unit the metric unit (e.g.: centimeters, millimeters) in which the values of the Length, Width and Square fields are expressed.
     * @return a new WarehouseMeasurements object
     */
    public static WarehouseMeasurements valueOf (final long squareSize, final long length, final long width, final String unit) {
        return new WarehouseMeasurements(
                SquareSize.valueOf(squareSize),
                Length.valueOf(length),
                Width.valueOf(width),
                VolumeUnit.valueOf(unit));
    }

    /**
     * Returns true if both objects are equal or false otherwise
     * @param o the other object
     * @return true if both objects are equal or false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WarehouseMeasurements)) {
            return false;
        }

        final WarehouseMeasurements other = (WarehouseMeasurements) o;
        return (squareSize == other.squareSize) && (wlength == other.wlength) && (width == other.width) && unit.equals(other.unit);
    }


    /**
     * @return transforms this object to string form
     */
    @Override
    public String toString() {
        return String.format("SQUARE SIZE: %s |  LENGTH: %s  |  WIDTH: %s  |  UNIT: %s",squareSize, wlength,width,unit);
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(squareSize).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(WarehouseMeasurements o) {return  Long.compare(squareSize.value(),o.squareSize.value());}

    protected SquareSize squareSize() {
        return squareSize;
    }

    protected Length length() {
        return wlength;
    }

    protected Width width() {
        return width;
    }

    protected VolumeUnit unit() {
        return unit;
    }
}
