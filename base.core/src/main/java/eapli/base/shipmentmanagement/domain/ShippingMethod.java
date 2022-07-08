package eapli.base.shipmentmanagement.domain;

import eapli.base.common.domain.WeightUnit;
import eapli.base.customermanagement.domain.Name;
import eapli.base.productcategorymanagement.domain.Tax;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Money;

import javax.persistence.*;

@Entity
public class ShippingMethod implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "designation"))
    })
    private Name designation;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "carrier"))
    })
    private Carrier carrier;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "pricePerWeight_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "pricePerWeight_currency"))
    })
    private Money pricePerWeight;

    @Enumerated(EnumType.STRING)
    private WeightUnit weightUnit;

    @Embedded
    private Tax taxRate;

    /**
     * Constructor for ShipmentMethod object
     *
     * @param designation the Shipment Method designation
     * @param price       the Price per weight unit
     */
    protected ShippingMethod(final Name designation, final Carrier carrier, final Money price, WeightUnit unit, final Tax tax) {
        setName(designation);
        setCarrier(carrier);
        setPrice(price);
        setWeightUnit(unit);
        setTax(tax);
    }

    /**
     * empty constructor for ORM
     */
    protected ShippingMethod() {
        // for ORM
    }

    private void setName(Name designation) {
        if (designation == null) throw new IllegalArgumentException("Shipment Method must include a designation");
        this.designation = designation;
    }

    private void setCarrier(Carrier carrier) {
        if (carrier == null)
            throw new IllegalArgumentException("Shipment Method must include a carrier");
        this.carrier = carrier;
    }

    private void setWeightUnit(WeightUnit unit) {
        if (unit == null)
            throw new IllegalArgumentException("Shipment Method price must be associated to a weight unit.");
        this.weightUnit = unit;
    }

    private void setPrice(Money price) {
        if (price == null) throw new IllegalArgumentException("Shipment Method must include a price");
        this.pricePerWeight = price;
    }

    public Money getPricePerWeight() {
        return pricePerWeight;
    }

    public WeightUnit getWeightUnit() {
        return weightUnit;
    }

    private void setTax(Tax tax) {
        if (tax == null) throw new IllegalArgumentException("Shipment Method must include a tax rate");
        this.taxRate = tax;
    }

    public Tax getTax() {
        return taxRate;
    }

    /**
     * Creates an ShipmentMethod object
     *
     * @param designation the Shipment Method designation
     * @param price       the Price per weight unit
     * @return a new ShipmentMethod object
     */
    public static ShippingMethod valueOf(final Name designation, final Carrier carrier, final Money price, WeightUnit unit, final Tax tax) {
        return new ShippingMethod(designation, carrier, price, unit, tax);
    }

    public static ShippingMethod valueOf(final String designation, final String carrier, final String price, final String unit, final double tax) {
        return new ShippingMethod(Name.valueOf(designation), Carrier.valueOf(carrier), Money.valueOf(price), WeightUnit.valueOf(unit), Tax.valueOf(tax));
    }

    /**
     * Return true if both object are equal or false otherwise
     *
     * @param o the other object
     * @return true if both object are equal or false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShippingMethod)) {
            return false;
        }

        final ShippingMethod other = (ShippingMethod) o;
        return designation.equals(other.designation) && carrier.equals(other.carrier);
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return designation.hashCode() * pricePerWeight.hashCode();
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.format("%s    %s    %s/%s\n", carrier, designation, pricePerWeight, weightUnit);
    }
}
