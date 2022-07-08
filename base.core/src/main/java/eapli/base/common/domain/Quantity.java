package eapli.base.common.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Quantity implements ValueObject, Comparable<Quantity> {
    private int qty;

    public Quantity(final int qty) {
        setQuantity(qty);
    }

    protected Quantity() {
        //for ORM only
    }

    private void setQuantity(int qty) {
        if (qty <= 0)
            throw new IllegalArgumentException("Invalid quantity, must not be negative or zero.");
        else
            this.qty = qty;
    }

    public int value() {
        return qty;
    }

    public static Quantity valueOf(final int qty) {
        return new Quantity(qty);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Quantity)) {
            return false;
        }

        final Quantity that = (Quantity) o;
        return this.qty == that.qty;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(qty);
    }

    @Override
    public String toString() {
        return String.format("%d", qty);
    }

    @Override
    public int compareTo(Quantity o) {
        return Integer.compare(this.qty, o.qty);
    }
}
