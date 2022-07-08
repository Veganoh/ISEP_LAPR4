package eapli.base.ordermanagement.domain;

import eapli.base.common.domain.Quantity;
import eapli.base.common.domain.Volume;
import eapli.base.common.domain.Weight;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.ProductDescriptions;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.general.domain.model.Money;

import javax.persistence.*;

/**
 * Class the represents an item/line of an Order, composed of a given Product and respective Quantity
 */
@Entity
public class OrderLineItem implements DomainEntity<Long> {
    /**
     * Database Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    /**
     * Line number in the Order
     */
    private int lineNo;
    /**
     * Product contained in the order line
     * It is persisted in the database for product location queries,
     * however any information saved for Order display purposes is saved separately in static value objects
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    /**
     * The order line product's description
     * Corresponds to the product description at the time of order registration (static)
     */
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "shortDescription.description", column = @Column(name = "product_shortDescription")), @AttributeOverride(name = "longDescription.description", column = @Column(name = "product_longDescription")), @AttributeOverride(name = "technicalDescription.description", column = @Column(name = "product_technicalDescription")),})
    private ProductDescriptions productDescriptions;
    /**
     * The order line product's price
     * Corresponds to the product price at the time of order registration (static)
     */
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "product_price_amount")), @AttributeOverride(name = "currency", column = @Column(name = "product_price_currency"))})
    private Money productPrice;
    /**
     * The order line product's price, including tax
     * Corresponds to the product price and tax at the time of order registration (static)
     */
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "product_tax_price_amount")), @AttributeOverride(name = "currency", column = @Column(name = "product_price_tax_currency"))})
    private Money productTaxPrice;
    /**
     * The order line quantity, relating to the @product
     */
    @Embedded
    private Quantity quantity;
    /**
     * The order line total
     * Computed as the @productPrice multiplied by the @quantity
     */
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "total_amount")), @AttributeOverride(name = "currency", column = @Column(name = "total_currency"))})
    private Money total;

    /**
     * Constructor for an OrderLineItem
     *
     * @param lineNo   the line number in the Order
     * @param product  the product to be ordered
     * @param quantity the product's quantity
     */
    public OrderLineItem(final int lineNo, final Product product, final Quantity quantity) {
        setId(lineNo);
        setProduct(product);
        setQuantity(quantity);
        setTotal();
    }

    /**
     * Constructor for an OrderLineItem, with standard quantity (1)
     *
     * @param lineNo  the line number in the Order
     * @param product the product to be ordered
     */
    public OrderLineItem(final int lineNo, final Product product) {
        setId(lineNo);
        setProduct(product);
        setQuantity(Quantity.valueOf(1));
        setTotal();
    }

    /**
     * Constructor for an OrderLineItem
     * FOR ORM ONLY
     */
    protected OrderLineItem() {
        //for ORM only
    }

    private void setId(int lineNo) {
        if (lineNo < 0) throw new IllegalArgumentException("Invalid line id, must not be negative.");
        else this.lineNo = lineNo;
    }

    private void setProduct(Product product) {
        if (product == null) throw new IllegalArgumentException("Invalid product reference, must not be null.");
        else {
            this.product = product;
            this.productDescriptions = product.getProductDescriptions();
            this.productPrice = product.getPrice();
            this.productTaxPrice = product.getPriceWithTax();
        }
    }

    private void setQuantity(Quantity quantity) {
        if (quantity == null) throw new IllegalArgumentException("Invalid quantity reference, must not be null.");
        else this.quantity = quantity;
    }

    public Product product() {
        return this.product;
    }

    public Weight weight() {
        return Weight.valueOf(product.getWeight().value() * quantity.value(), product.getWeight().Unit());
    }

    public Volume volume() {
        return Volume.valueOf(product.getVolume().value() * quantity.value(), product.getVolume().Unit());
    }

    private void setTotal() {
        if (product == null || quantity == null)
            throw new IllegalArgumentException("Cannot compute line total, product and/or quantity must not be null");

        this.total = productPrice.multiply(quantity.value());
    }

    public Money total() {
        return this.total;
    }

    public Money totalWithTax() {
        return productTaxPrice.multiply(quantity.value());
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderLineItem)) {
            return false;
        }

        final OrderLineItem that = (OrderLineItem) o;
        return this.lineNo == that.lineNo && this.product.equals(that.product);
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
        return String.format("LINE NO: %d |  PRODUCT: %s  |  QTY: %s  |  TOTAL: %s", lineNo, productDescriptions.shortestDescription(), quantity, total);
    }

    @Override
    public Long identity() {
        return id;
    }
}
