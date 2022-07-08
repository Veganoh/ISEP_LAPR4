package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
public class Shelf implements DomainEntity<Long> {

    /**
     * The shelf's ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    /**
     * The aisle's ID
     */
    @Embedded
    @AttributeOverride(name = "identifier", column = @Column(name = "aisle_id"))
    private AisleId aisleId;

    /**
     * The row's ID
     */
    @Embedded
    @AttributeOverride(name = "identifier", column = @Column(name = "row_id"))
    private RowId rowId;

    /**
     * Shelf's number
     */
    @Embedded
    @AttributeOverride(name = "identifier", column = @Column(name = "shelf_no"))
    private ShelfNumber shelfNumber;

    /**
     * Constructor for the Shelf object
     * @param aisleId the aisle's ID
     * @param rowId the row's ID
     * @param shelfNumber the shelf's number
     */
    public Shelf(final AisleId aisleId,final RowId rowId,
                 final ShelfNumber shelfNumber){

        Preconditions.noneNull(aisleId,rowId,shelfNumber);
        this.aisleId = aisleId;
        this.rowId = rowId;
        this.shelfNumber = shelfNumber;
    }

    public Shelf(final String aisleId, final String rowId, final String shelfNumber) {
        this.aisleId = AisleId.valueOf(aisleId);
        this.rowId = RowId.valueOf(rowId);
        this.shelfNumber = ShelfNumber.valueOf(shelfNumber);
    }

    public void assignShelfNumber(final String shelfNumber){
        this.shelfNumber = ShelfNumber.valueOf(shelfNumber);
    }

    /**
     * Mandatory empty constructor for ORM
     */
    protected Shelf(){
    }

    public RowId rowId() {
        return this.rowId;
    }

    public AisleId aisleId() {
        return this.aisleId;
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
        if (!(o instanceof Shelf)) {
            return false;
        }
        final Shelf other = (Shelf) o;
        return this.shelfNumber.equals(other.shelfNumber) && this.aisleId.equals(other.aisleId) && this.rowId.equals(other.rowId);
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode(){ return id.hashCode();}

    /**
     * returns the shelf identity
     * @return the shelf identity
     */
    @Override
    public Long identity() {return id;}

    /**
     * returns true if this object has the same identity as another product
     * @param o the other object
     * @return true if they are equal
     */
    @Override
    public boolean sameAs(final Object o){
        return DomainEntities.areEqual(this,o);
    }

    /**
     * @return transforms the object to string form
     */
    @Override
    public String toString() {
        return String.format("AISLE ID: %s | ROW ID: %s | SHELF NUMBER: %s",
                aisleId,rowId,shelfNumber);
    }


}