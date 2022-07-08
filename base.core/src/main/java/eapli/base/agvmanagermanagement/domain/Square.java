package eapli.base.agvmanagermanagement.domain;

import eapli.base.warehousemanagement.domain.AisleId;
import eapli.base.warehousemanagement.domain.RowId;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

/**
 * This Class has the Entity Annotation but that is only because of JPA limitations with element collections
 * it is still treated as a value object.
 */
@Entity
public class Square implements ValueObject, Comparable<Square> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Embedded
    private Coordinates coordinates;

    @Embedded
    @AttributeOverride(name = "identifier", column = @Column(name = "row_id"))
    private RowId rowId;

    @Embedded
    @AttributeOverride(name = "identifier", column = @Column(name = "aisle_id"))
    private AisleId aisleId;

    @Enumerated(EnumType.STRING)
    private Accessibility accessibility;

    /**
     Mandatory empty constructor for ORM
     */
    protected Square() {}

    /**
     * Constructor to create a Square Object.
     * @param coordinates The Square's coordinates
     * @param rowId The RowId contained in the square (can be null)
     * @param aisleId The AisleId contained in the square (can be null)
     * @param accessibility The Accessibility direction for the square (can be null)
     */
    protected Square(final Coordinates coordinates, final RowId rowId, final AisleId aisleId, final Accessibility accessibility) {
        Preconditions.nonNull(coordinates, "Coordinates must be defined when creating a Square!");

        if (rowId == null ^ aisleId == null)
            throw new IllegalArgumentException("A Square can't have only the RowId or the AisleID defined! Both should be set to null or initialized.");

        this.coordinates = coordinates;
        this.rowId = rowId;
        this.aisleId = aisleId;
        this.accessibility = accessibility;
    }

    /**
     * Constructor to create a Square Object.
     * @param coordinates The Square's coordinates
     * @param rowId The RowId contained in the square (can be null)
     * @param aisleId The AisleId contained in the square (can be null)
     * @param accessibility The Accessibility direction for the square (can be null)
     */
    public static Square valueOf(final Coordinates coordinates, final RowId rowId, final AisleId aisleId, final Accessibility accessibility) {
        return new Square(coordinates, rowId, aisleId, accessibility);
    }

    public static Square valueOf(final Coordinates coordinates, final Accessibility accessibility) {
        return new Square(coordinates, null, null, accessibility);
    }

    public static Square valueOf(final Coordinates coordinates) {
        return new Square(coordinates, null, null, null);
    }

    public static Square valueOf(final Coordinates coordinates, final RowId rowId, final AisleId aisleId) {
        return new Square(coordinates, rowId, aisleId, null);
    }

    /**
     * Returns the Square accessibilityDirection if it has any
     * @return Accessibility enum
     */
    public Accessibility accessibilityDirection() {
        if (hasAccessibilityDirection())
            return accessibility;

        throw new NoResultException("This Square doesn't have an access direction");
    }

    /**
     * Checks if the square has a mandatory accessibility direction
     * @return True if it has a direction, False if it can be accessed from anywhere
     */
    public boolean hasAccessibilityDirection() {
        return accessibility != null;
    }

    /**
     * Returns the aisle contained in the square, if there is any
     * @return the aisle id
     */
    protected AisleId containedAisle() {
        if (hasAisle())
            return aisleId;

        throw new NoResultException("This Square doesn't contain an aisle");
    }

    /**
     * Checks if the Square contains any Aisle
     * @return True if there is an aisle in the square False if not
     */
    public boolean hasAisle() {
        return aisleId != null;
    }

    /**
     * Returns the row contained in the square, if there is any
     * @return the row id
     */
    protected RowId containedRow() {
        if (hasRow())
            return rowId;

        throw new NoResultException("This Square doesn't contain a row");
    }

    /**
     * Checks if the Square contains any row
     * @return True if there is a row in the square False if not
     */
    public boolean hasRow() {
        return rowId != null;
    }

    /**
     * Returns the Square's coordinates
     * @return Square's Coordinates
     */
    public Coordinates squareCoordinates() {
        return coordinates;
    }

    /**
     * Return true if both object are equal or false otherwise
     * @param o the other object
     * @return true if both object are equal or false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Square))
            return false;

        final Square other = (Square) o;
        return coordinates.equals(other.coordinates)
                && rowId.equals(other.rowId)
                && aisleId.equals(other.aisleId)
                && accessibility.equals(other.accessibility);
    }

    /**
     * @return transforms this object to string form
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Coordinates: %s", coordinates.toString()));

        if (hasRow() && hasAisle())
            sb.append(String.format(", RowId: %s, AisleId: %s", rowId.toString(), aisleId.toString()));

        if (hasAccessibilityDirection())
            sb.append(String.format(", Accessibility: %s", accessibility.toString()));

        return sb.toString();
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(coordinates.hashCode() * aisleId.hashCode() * rowId.hashCode() * accessibility.hashCode()).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(Square o) {
        return coordinates.compareTo(o.coordinates);
    }
}
