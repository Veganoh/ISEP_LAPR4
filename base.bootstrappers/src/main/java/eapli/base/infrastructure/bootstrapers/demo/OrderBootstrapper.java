package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.common.domain.AddressType;
import eapli.base.common.domain.Quantity;
import eapli.base.common.domain.RegisterTime;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.domain.OrderBuilder;
import eapli.base.ordermanagement.domain.PaymentConfirmation;
import eapli.base.ordermanagement.domain.PaymentMethod;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.ordermanagement.domain.OrderNotificationService;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.shipmentmanagement.domain.ShippingMethod;
import eapli.base.shipmentmanagement.repositories.ShippingMethodRepository;
import eapli.framework.actions.Action;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.List;

public class OrderBootstrapper implements Action {

    OrderRepository orderRepository = PersistenceContext.repositories().Orders();

    @Override
    public boolean execute() {
        CustomerRepository customerRepository = PersistenceContext.repositories().Customer();
        ProductRepository productRepository = PersistenceContext.repositories().Products();
        ShippingMethodRepository shippingRepo = PersistenceContext.repositories().ShippingMethods();
        List<Customer> costumerList = (List<Customer>) customerRepository.findAll();
        List<Product> productList = (List<Product>) productRepository.findAll();
        List<ShippingMethod> shippingMethods = (List<ShippingMethod>) shippingRepo.findAll();

        List<Pair<Product, Integer>> prod1 = new ArrayList<>();
        prod1.add(new Pair<>(productList.get(0), 2));
        prod1.add(new Pair<>(productList.get(4), 1));
        registerOrder(prod1, costumerList.get(0), shippingMethods.get(0), false, false);

        List<Pair<Product, Integer>> prod2 = new ArrayList<>();
        prod2.add(new Pair<>(productList.get(5), 1));
        registerOrder(prod2, costumerList.get(1), shippingMethods.get(1), false, false);

        List<Pair<Product, Integer>> prod3 = new ArrayList<>();
        prod3.add(new Pair<>(productList.get(2), 1));
        prod3.add(new Pair<>(productList.get(1), 2));
        prod3.add(new Pair<>(productList.get(3), 1));
        registerOrder(prod3, costumerList.get(2), shippingMethods.get(2), true, false);

        List<Pair<Product, Integer>> prod4 = new ArrayList<>();
        prod4.add(new Pair<>(productList.get(0), 1));
        registerOrder(prod4, costumerList.get(2), shippingMethods.get(1), false, false);

        List<Pair<Product, Integer>> prod5 = new ArrayList<>();
        prod5.add(new Pair<>(productList.get(1), 1));
        prod5.add(new Pair<>(productList.get(2), 1));
        registerOrder(prod5, costumerList.get(1), shippingMethods.get(2), true, true);
        return true;

    }

    private void registerOrder(List<Pair<Product, Integer>> list, Customer customer, ShippingMethod shipping, boolean packaging, boolean sending){

        OrderBuilder builder = new OrderBuilder();
        builder.ofCustomer(customer);
        builder.ofBillingAddress(customer.addresses(AddressType.BILLING).get(0));
        builder.ofShippingAddress(customer.addresses(AddressType.SHIPPING).get(0));
        builder.ofShippingMethod(shipping);
        builder.ofPaymentMethod(PaymentMethod.PAYPAL);

        for (Pair<Product, Integer> pair : list)
            builder.ofItem(pair.a, Quantity.valueOf(pair.b));

        Order order = builder.build();
        order.addPaymentConfirmation(PaymentConfirmation.valueOf(true, RegisterTime.now(), "Payment success"));
        if (packaging) {
            OrderNotificationService.beingPrepared(order);
            OrderNotificationService.readyForPackaging(order);
            if (sending){
                OrderNotificationService.readyForDispatch(order);
                OrderNotificationService.dispatched(order);
            }
        } else {
            OrderNotificationService.save(order);
        }
    }
}
