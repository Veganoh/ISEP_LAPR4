package eapli.base.ordermanagement.domain;

import eapli.base.common.domain.*;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.CustomerAddress;
import eapli.base.shipmentmanagement.domain.ShippingMethod;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Money;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Class that represents a products Order
 */
@Entity
@Table(name = "Customer_Order")
public class Order implements AggregateRoot<Long> {
    /**
     * Database Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    /**
     * The sales clerk that registers the Order (optional)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "salesClerk_id", referencedColumnName = "id")})
    private SalesClerk salesClerk;
    /**
     * The customer who places the order
     */
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "customer_id", referencedColumnName = "identifier")})
    private Customer customer;
    /**
     * The @customer's billing address, related to payment
     */
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "postalAddress", column = @Column(name = "billing_address")), @AttributeOverride(name = "addressName", column = @Column(name = "billing_address_name")), @AttributeOverride(name = "addressType", column = @Column(name = "billing"))})
    private CustomerAddress billingAddress;
    /**
     * The @customer's shipping address, related to shipping
     */
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "postalAddress", column = @Column(name = "shipping_address")), @AttributeOverride(name = "addressName", column = @Column(name = "shipping_address_name")), @AttributeOverride(name = "addressType", column = @Column(name = "shipping"))})
    private CustomerAddress shippingAddress;
    /**
     * The shipping method for the order
     * e.g. STANDARD, EXPRESS
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn(name = "shipment_method_id", referencedColumnName = "id")})
    private ShippingMethod shipment;
    /**
     * The payment method for the order (e.g. PAYPAL)
     * Represented by @enum in order to mock the payment API
     */
    @Enumerated(EnumType.STRING)
    private PaymentMethod payment;
    /**
     * The payment confirmation for the selected @payment
     */
    @Embedded
    private PaymentConfirmation paymentConfirmation;
    /**
     * List of order line items, corresponding to a Product and the respective Quantity
     */
    @OneToMany(cascade = CascadeType.ALL)
    private final List<OrderLineItem> orderLineItems = new ArrayList<>();
    /**
     * List of order states, and their respective registration date/times
     */
    @ElementCollection(fetch = FetchType.EAGER)
    private final List<OrderState> orderStates = new ArrayList<>();
    /**
     * The shipping price, based on the selected @shipment price per weight unit and the order's total weight
     */
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "shipping_total_amount")), @AttributeOverride(name = "currency", column = @Column(name = "shipping_total_currency"))})
    private Money shippingTotal;
    /**
     * The @shippingTotal, including tax
     */
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "shipping_total_tax_amount")), @AttributeOverride(name = "currency", column = @Column(name = "shipping_total_tax_currency"))})
    private Money shippingTotalWithTax;
    /**
     * The order total price, based on the sum of the price of all @orderLineItems and the @shippingTotal
     */
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "total_amount")), @AttributeOverride(name = "currency", column = @Column(name = "total_currency"))})
    private Money total;
    /**
     * The @total, including tax
     */
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "total_tax_amount")), @AttributeOverride(name = "currency", column = @Column(name = "total_tax_currency"))})
    private Money totalWithTax;

    /**
     * Constructor for Order
     *
     * @param salesClerk      (optional / nullable)
     * @param customer
     * @param billingAddress
     * @param shippingAddress
     * @param shipment
     * @param payment
     * @param orderLineItems
     */
    protected Order(SalesClerk salesClerk, Customer customer, CustomerAddress billingAddress, CustomerAddress shippingAddress, ShippingMethod shipment, PaymentMethod payment, List<OrderLineItem> orderLineItems) {
        setSalesClerk(salesClerk);
        setCustomer(customer);
        setBillingAddress(billingAddress);
        setShippingAddress(shippingAddress);
        setShippingMethod(shipment);
        setPaymentMethod(payment);
        this.paymentConfirmation = new PaymentConfirmation();
        setOrderLineItems(orderLineItems);
        registered();
        paymentPending();
    }

    /**
     * Constructor for Order
     * FOR ORM ONLY
     */
    protected Order() {
        //for ORM only
    }

    /**
     * sets sales clerk in order
     * @param salesClerk the sales clerk
     */
    private void setSalesClerk(SalesClerk salesClerk) {
        this.salesClerk = salesClerk;
    }

    /**
     * sets the customer in order
     * @param customer the customer
     */
    private void setCustomer(Customer customer) {
        if (customer == null) throw new IllegalArgumentException("Order must be associated to a customer");
        this.customer = customer;
    }

    /**
     * sets the billing address in order
     * @param address the billing address
     */
    private void setBillingAddress(CustomerAddress address) {
        if (address == null) throw new IllegalArgumentException("Order must have a billing address");
        else if (!address.isOf(AddressType.BILLING))
            throw new IllegalArgumentException("Order billing address must be defined as \"billing\"");

        this.billingAddress = address;
    }

    /**
     * sets the shipping address in order
     * @param address the shipping address
     */
    private void setShippingAddress(CustomerAddress address) {
        if (address == null) throw new IllegalArgumentException("Order must have a shipping address");
        else if (!address.isOf(AddressType.SHIPPING))
            throw new IllegalArgumentException("Order shipping address must be defined as \"shipping\"");

        this.shippingAddress = address;
    }

    /**
     * sets the order line items in order
     * @param orderLineItems the order line items
     */
    private void setOrderLineItems(List<OrderLineItem> orderLineItems) {
        if (orderLineItems == null || orderLineItems.isEmpty())
            throw new IllegalArgumentException("Order must have at least one item.");

        for (OrderLineItem orderLineItem : orderLineItems)
            addOrderLineItem(orderLineItem);
    }

    /**
     * sets the payment method in order
     * @param payment the payment method
     */
    private void setPaymentMethod(PaymentMethod payment) {
        if (payment == null) throw new IllegalArgumentException("Order must have a valid payment method");
        this.payment = payment;
    }

    /**
     * sets the shipping method in order
     * @param shipment the shipping method
     */
    private void setShippingMethod(ShippingMethod shipment) {
        if (shipment == null) throw new IllegalArgumentException("Order must have a valid shipment method.");
        this.shipment = shipment;
    }

    /**
     * sets the shipping total
     */
    private void setShippingTotal() {
        //Calculate total order weight to compute shipping price
        Weight totalOrderWeight = weight();

        this.shippingTotal = shipment.getPricePerWeight().multiply(Objects.requireNonNull(totalOrderWeight).value());
        this.shippingTotalWithTax = shippingTotal.multiply(1 + shipment.getTax().value());
    }

    public Weight weight() {
        Weight totalOrderWeight = null;

        for (OrderLineItem line : orderLineItems) {
            Weight lineWeight = WeightUnit.convert(line.weight(), shipment.getWeightUnit());
            totalOrderWeight = (totalOrderWeight == null ? lineWeight : Weight.valueOf(totalOrderWeight.value() + lineWeight.value(), shipment.getWeightUnit()));
        }

        return totalOrderWeight;
    }

    public Volume volume() {
        Volume totalOrderVolume = null;

        for (OrderLineItem line : orderLineItems) {
            Volume lineVolume = VolumeUnit.convert(line.volume(), VolumeUnit.CM);
            totalOrderVolume = (totalOrderVolume == null ? lineVolume : Volume.valueOf(totalOrderVolume.value() + lineVolume.value(), VolumeUnit.CM));
        }

        return totalOrderVolume;
    }

    /**
     * sets the total
     */
    private void setTotal() {
        this.total = Money.euros(0);
        this.totalWithTax = Money.euros(0);

        for (OrderLineItem line : orderLineItems) {
            this.total = total.add(line.total());
            this.totalWithTax = totalWithTax.add(line.totalWithTax());
        }

        this.total = total.add(shippingTotal);
        this.totalWithTax = totalWithTax.add(shippingTotalWithTax);
    }

    /**
     * adds an order state to an order
     * @param state the order state
     */
    private void addOrderState(OrderState state) {
        orderStates.add(state);
        Collections.sort(orderStates);
    }

    /**
     * gets the last order state
     * @return last order state
     */
    private OrderState getLastOrderState() {
        return orderStates.get(orderStates.size() - 1);
    }

    /**
     * sets the order state as "READY FOR PACKAGING"
     */
    protected void readyForPackaging() {
        if (!getLastOrderState().checkStateSequence(OrderState.State.READY_FOR_PACKAGING))
            throw new IllegalStateException("Order is not being prepared on the warehouse and cannot be packaged yet.");

        OrderState state = new OrderState(OrderState.State.READY_FOR_PACKAGING, RegisterTime.now());
        addOrderState(state);
    }

    /**
     * sets the order state as "BEING PREPARED"
     */
    protected void beingPrepared() {
        if (!getLastOrderState().checkStateSequence(OrderState.State.BEING_PREPARED))
            throw new IllegalStateException("Order has not been payed for and can't be prepared yet.");

        OrderState state = new OrderState(OrderState.State.BEING_PREPARED, RegisterTime.now());
        addOrderState(state);
    }

    /**
     * sets the order state as "READY FOR DISPATCHING"
     */
    protected void readyForDispatch() {
        if (!getLastOrderState().checkStateSequence(OrderState.State.READY_FOR_DISPATCHING))
            throw new IllegalStateException("Order has not been packaged yet and cannot be dispatched.");

        OrderState state = new OrderState(OrderState.State.READY_FOR_DISPATCHING, RegisterTime.now());
        addOrderState(state);
    }

    /**
     * sets the order state as "DISPATCHED"
     */
    protected void dispatched() {
        if (!getLastOrderState().checkStateSequence(OrderState.State.DISPATCHED))
            throw new IllegalStateException("Order has not been dispatched.");

        OrderState state = new OrderState(OrderState.State.DISPATCHED, RegisterTime.now());
        addOrderState(state);
    }

    /**
     * sets the order state as "DELIVERED_BY_CARRIER"
     */
    protected void deliveredByCarrier() {
        if (!getLastOrderState().checkStateSequence(OrderState.State.DELIVERED_BY_CARRIER))
            throw new IllegalStateException("Order has not been delivered by carrier.");

        OrderState state = new OrderState(OrderState.State.DELIVERED_BY_CARRIER, RegisterTime.now());
        addOrderState(state);
    }

    /**
     * register time of order
     */
    private void registered() {
        if (!orderStates.isEmpty()) throw new IllegalStateException("Order has already been registered.");

        OrderState state = new OrderState(OrderState.State.REGISTERED, RegisterTime.now());
        addOrderState(state);
    }

    /**
     * sets the order state as "PAYMENT PENDING"
     */
    private void paymentPending() {
        if (!getLastOrderState().checkStateSequence(OrderState.State.PAYMENT_PENDING))
            throw new IllegalStateException("Order has not been registered yet.");

        OrderState state = new OrderState(OrderState.State.PAYMENT_PENDING, RegisterTime.now());
        addOrderState(state);
    }

    /**
     * sets the order state as "TO BE PREPARED"
     */
    private void toBePrepared() {
        if (!getLastOrderState().checkStateSequence(OrderState.State.TO_BE_PREPARED))
            throw new IllegalStateException("Order does not have valid payment confirmation yet.");

        OrderState state = new OrderState(OrderState.State.TO_BE_PREPARED, RegisterTime.now());
        addOrderState(state);
    }

    /**
     * adds an order item to an order
     * @param item the new item
     */
    protected void addOrderLineItem(OrderLineItem item) {
        if (item == null) throw new IllegalArgumentException("Cannot add a null line to an order");
        orderLineItems.add(item);

        //Refresh total
        setShippingTotal();
        setTotal();
    }

    /**
     * adds a payment confirmation to the order
     * @param confirmation the payment confirmation
     */
    public void addPaymentConfirmation(PaymentConfirmation confirmation) {
        if (confirmation == null) throw new IllegalArgumentException("Payment confirmation must be not null");

        if (confirmation.success()) toBePrepared();

        this.paymentConfirmation = confirmation;
    }

    /**
     * gets the number of lines in an order
     * @return the size of the order
     */
    public int numberOfLines() {
        return orderLineItems.size();
    }

    /**
     * the total amount of the order
     * @return how much the order costs
     */
    public Money total() {
        return total;
    }

    /**
     * the total amount of the order with tax
     * @return how much the order costs with tax
     */
    public Money totalWithTax() {
        return totalWithTax;
    }

    /**
     * the last state
     * @return how much the order costs with tax
     */
    public OrderState getOrderStates() {
        return getLastOrderState();
    }

    /**
     * the total amount to ship the order
     * @return how much the shipping the order costs
     */
    public Money shippingTotal() {
        return shippingTotal;
    }

    /**
     * the total amount to ship the order with tax
     * @return how much the shipping the order costs with tax
     */
    public Money shippingTotalWithTax() {
        return shippingTotalWithTax;
    }

    /**
     * gets the customer
     * @return the order's customer
     */
    public Customer proprietary() {
        return customer;
    }

    /**
     * gets the sales clerk
     * @return the order's sales clerk
     */
    public SalesClerk responsibleEmployee() {
        return salesClerk;
    }

    /**
     * all the items in the order
     * @return all order items
     */
    public List<OrderLineItem> items() {
        return orderLineItems;
    }

    /**
     * compares if two orders are equal
     * @param other the order used for comparation
     * @return true if they are equal
     */
    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    /**
     * gets the order's id
     * @return order id
     */
    @Override
    public Long identity() {
        return id;
    }
}
