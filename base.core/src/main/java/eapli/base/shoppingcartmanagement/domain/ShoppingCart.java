package eapli.base.shoppingcartmanagement.domain;

import eapli.base.common.domain.Quantity;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Money;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a shopping cart, used to select products and convert into an Order
 */
@Entity
public class ShoppingCart implements AggregateRoot<Long> {
    /**
     * Database Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    /**
     * The customer to which the shopping cart belongs
     * A customer may only have one associated shopping cart, and vice versa
     */
    @OneToOne
    private Customer customer;
    /**
     * List of shopping cart line items, corresponding to a Product and respective Quantity
     */
    @OneToMany(cascade = CascadeType.ALL)
    private final List<ShoppingCartLineItem> shoppingCartLineItems = new ArrayList<>();
    /**
     * The shopping cart total price, based on the sum of the price of all @shoppingCartLineItems
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "total_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "total_currency"))
    })
    private Money total;
    /**
     * The @total, including tax
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "total_tax_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "total_tax_currency"))
    })
    private Money totalWithTax;

    /**
     * Constructor for Shopping Cart
     *
     * @param customer the customer associated to the shopping cart
     */
    public ShoppingCart(Customer customer) {
        setCustomer(customer);
    }

    /**
     * Constructor for Shopping Cart
     * FOR ORM ONLY
     */
    protected ShoppingCart() {
        //for ORM only
    }

    private void setCustomer(Customer customer) {
        if (customer == null)
            throw new IllegalArgumentException("Shopping Cart must be associated to a customer");
        this.customer = customer;
    }

    public ShoppingCartLineItem addShoppingCartLineItem(Product product, Quantity quantity) {
        ShoppingCartLineItem item = new ShoppingCartLineItem(product, quantity);
        boolean flag = true;

        for(ShoppingCartLineItem line : shoppingCartLineItems){
            if(line.product().sameAs(product)){
                flag = false;
                for(int i = 0;i < quantity.value();i++){
                    line.increaseQuantity();
                }
            }
        }
        if(flag) shoppingCartLineItems.add(item);

        //Refresh shopping cart total
        update();
        return item;
    }

    public boolean removeShoppingCartLineItem(ShoppingCartLineItem shoppingCartLineItem) {
        if (shoppingCartLineItem == null) throw new IllegalArgumentException("Cannot remove null item from Shopping Cart.");

        if (shoppingCartLineItems.remove(shoppingCartLineItem)) {
            //Refresh shopping cart total
            update();

            return true;
        } else
            return false;
    }

    public Quantity increaseQuantity(ShoppingCartLineItem item) {
        if (item == null) throw new IllegalArgumentException("Cannot increase quantity of null item.");

        Quantity newQty = item.increaseQuantity();
        update();

        return newQty;
    }

    public List<ShoppingCartLineItem> lines(){ return shoppingCartLineItems;}

    public int numberOfLines() {
        return shoppingCartLineItems.size();
    }

    public Money total() {
        return total;
    }

    public Money totalWithTax() {
        return totalWithTax;
    }

    public void update() {
        this.total = Money.euros(0);
        this.totalWithTax = Money.euros(0);

        for (ShoppingCartLineItem line : shoppingCartLineItems) {
            this.total = total.add(line.total());
            this.totalWithTax = totalWithTax.add(line.totalWithTax());
        }
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return id;
    }
}
