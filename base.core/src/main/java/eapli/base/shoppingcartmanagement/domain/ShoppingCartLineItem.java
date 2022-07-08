package eapli.base.shoppingcartmanagement.domain;

import eapli.base.common.domain.Quantity;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.general.domain.model.Money;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ShoppingCartLineItem implements DomainEntity<Long> {
    /**
     * Database Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    /**
     * Product contained in the shopping cart line
     * Any product attributes are directly obtained from the object, in order to assure data is up to date
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    /**
     * The shopping cart line quantity, relating to the @product
     */
    @Embedded
    private Quantity quantity;
    /**
     * The shopping cart line total
     * Computed as the @product price multiplied by the @quantity
     */
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "total_amount")), @AttributeOverride(name = "currency", column = @Column(name = "total_currency"))})
    private Money total;

    /**
     * Constructor for a ShoppingCartLineItem, with standard quantity (1)
     *
     * @param product the product to be ordered
     */
    public ShoppingCartLineItem(final Product product) {
        setProduct(product);
        setQuantity(Quantity.valueOf(1));
        setTotal();
    }

    /**
     * Constructor for a ShoppingCartLineItem
     *
     * @param product  the product to be ordered
     * @param quantity the product's quantity
     */
    public ShoppingCartLineItem(final Product product, final Quantity quantity) {
        setProduct(product);
        setQuantity(quantity);
        setTotal();
    }

    /**
     * Constructor for a ShoppingCart
     * FOR ORM ONLY
     */
    protected ShoppingCartLineItem() {
        //for ORM only
    }

    private void setProduct(Product product) {
        if (product == null) throw new IllegalArgumentException("Invalid product reference, must not be null.");
        else this.product = product;
    }

    private void setQuantity(Quantity quantity) {
        if (quantity == null) throw new IllegalArgumentException("Invalid quantity reference, must not be null.");
        else this.quantity = quantity;
    }

    protected Quantity increaseQuantity() {
        this.quantity = Quantity.valueOf(quantity.value() + 1);

        setTotal();

        return this.quantity;
    }

    protected Quantity decreaseQuantity() {
        this.quantity = Quantity.valueOf(quantity.value() - 1);

        setTotal();

        return this.quantity;
    }

    private void setTotal() {
        if (product == null || quantity == null)
            throw new IllegalArgumentException("Cannot compute line total, product and/or quantity must not be null");

        this.total = product.getPrice().multiply(quantity.value());
    }
    public Quantity quantity(){return quantity; }

    public Product product(){return  product; }

    public Money total() {
        return total;
    }

    public Money totalWithTax() {
        return product.getPriceWithTax().multiply(quantity.value());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShoppingCartLineItem)) {
            return false;
        }

        final ShoppingCartLineItem that = (ShoppingCartLineItem) o;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public String toString() {
        return String.format("PRODUCT: %s  |  QTY: %s  |  TOTAL: %s", product, quantity, total);
    }

    @Override
    public Long identity() {
        return this.id;
    }
}
