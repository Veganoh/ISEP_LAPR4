package eapli.base.ordermanagement.domain;

import eapli.base.common.domain.Quantity;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.CustomerAddress;
import eapli.base.productmanagement.domain.Product;
import eapli.base.shipmentmanagement.domain.ShippingMethod;
import eapli.framework.domain.model.DomainFactory;

import java.util.ArrayList;
import java.util.List;

public class OrderBuilder implements DomainFactory<Order> {
    /**
     * Mandatory fields
     */
    private Customer customer;
    private CustomerAddress billingAddress;
    private CustomerAddress shippingAddress;
    private ShippingMethod shipping;
    private PaymentMethod payment;
    private List<OrderLineItem> orderLineItems = new ArrayList<>();

    /**
     * Non-mandatory fields
     */
    private SalesClerk salesClerk;

    public OrderBuilder withSalesClerk(SalesClerk salesClerk) {
        this.salesClerk = salesClerk;
        return this;
    }

    public OrderBuilder ofCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public OrderBuilder ofBillingAddress(CustomerAddress address) {
        this.billingAddress = address;
        return this;
    }

    public OrderBuilder ofShippingAddress(CustomerAddress address) {
        this.shippingAddress = address;
        return this;
    }
    public OrderBuilder ofItem(Product product, Quantity quantity) {
        OrderLineItem orderLineItem = new OrderLineItem(orderLineItems.size(), product, quantity);

        this.orderLineItems.add(orderLineItem);
        return this;
    }
    public OrderBuilder ofShippingMethod(ShippingMethod shipment) {
        this.shipping = shipment;
        return this;
    }
    public OrderBuilder ofPaymentMethod(PaymentMethod payment) {
        this.payment = payment;
        return this;
    }

    @Override
    public Order build() {
        return new Order(salesClerk, customer, billingAddress, shippingAddress, shipping, payment, orderLineItems);
    }
}
