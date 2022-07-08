package eapli.base.ordermanagement.application;

import eapli.base.common.domain.AddressType;
import eapli.base.common.domain.Quantity;
import eapli.base.common.domain.RegisterTime;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.CustomerAddress;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.*;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.ordermanagement.repositories.SalesClerkRepository;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.shipmentmanagement.domain.ShippingMethod;
import eapli.base.shipmentmanagement.repositories.ShippingMethodRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class RegisterOrderController {
    /**
     * Authorization service that is used to make sure only authorized users can use the controller
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * The object repository
     */
    private final OrderRepository repo = PersistenceContext.repositories().Orders();

    /**
     * The customers repository
     */
    private final CustomerRepository customerRepo = PersistenceContext.repositories().Customer();

    /**
     * The sales clerks repository
     */
    private final SalesClerkRepository clerkRepo = PersistenceContext.repositories().SalesClerk();

    /**
     * The product repository
     */
    private final ProductRepository productRepo = PersistenceContext.repositories().Products();
    /**
     * The shipping methods repository
     */
    private final ShippingMethodRepository shippingRepo = PersistenceContext.repositories().ShippingMethods();

    /**
     * The order builder
     */
    private final OrderBuilder builder = new OrderBuilder();

    public RegisterOrderController() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER, BaseRoles.ADMIN);
    }

    /**
     * Returns a list of all customers registered on the platform,
     * for the purpose of selecting and associating a customer to the order
     *
     * @return a list of customers
     */
    public List<Customer> getCustomers() {
        return (List<Customer>) customerRepo.findAll();
    }

    /**
     * Returns a list of the customer's billing addresses,
     * for the purpose of selecting and associating one to the order
     *
     * @return a list of CustomerAddress
     */
    public List<CustomerAddress> getCustomerBillingAddresses(Customer customer) {
        return customer.addresses(AddressType.BILLING);
    }

    /**
     * Returns a list of the customer's shipping addresses
     * for the purpose of selecting and associating one to the order
     *
     * @return a list of CustomerAddress
     */
    public List<CustomerAddress> getCustomerShippingAddresses(Customer customer) {
        return customer.addresses(AddressType.SHIPPING);
    }

    /**
     * Returns a list of the available shipping methods
     */
    public List<ShippingMethod> getShippingMethods() {
        return (List<ShippingMethod>) shippingRepo.findAll();
    }

    /**
     * Returns a list of the available payment methods
     */
    public List<PaymentMethod> getPaymentMethods() {
        return List.of(PaymentMethod.values());
    }

    /**
     * Registers a new Order in the system
     *
     * @param customer        the selected customer
     * @param billingAddress  the selected billing address
     * @param shippingAddress the selected shipping address
     * @return an empty Order
     */
    public void register(Customer customer, CustomerAddress billingAddress, CustomerAddress shippingAddress, ShippingMethod shipping, PaymentMethod payment) {
        SalesClerk salesClerk = checkSalesClerk();

        builder.withSalesClerk(salesClerk);
        builder.ofCustomer(customer);
        builder.ofBillingAddress(billingAddress);
        builder.ofShippingAddress(shippingAddress);
        builder.ofShippingMethod(shipping);
        builder.ofPaymentMethod(payment);
    }

    /**
     * Checks the SalesClerk who is logged in, to associate to the order
     *
     * @return the SalesClerk
     * @throws NoSuchElementException, if sales clerk is not logged in
     */
    private SalesClerk checkSalesClerk() {
        Username salesClerkUser = authz.session().orElseThrow().authenticatedUser().username();

        Optional<SalesClerk> salesClerk = clerkRepo.findByUsername(salesClerkUser);

        return salesClerk.orElseThrow();
    }

    /**
     * Checks a given product code, to associate a Product to the order
     *
     * @param productCode the Product Code
     * @return the Product
     * @throws NoSuchElementException, if product doesn't exist
     */
    private Product checkProductCode(String productCode) {
        return productRepo.find(productCode).orElseThrow();
    }

    public void registerOrderLine(String selectedProduct, int quantity) {
        builder.ofItem(checkProductCode(selectedProduct), Quantity.valueOf(quantity));
    }

    public Order save() throws InterruptedException {
        Order order = builder.build();
        System.out.println("Registering order...");
        if (repo.save(order) != null) {
            System.out.print("Order registered successfully!\n\nAwaiting payment confirmation...\n");

            Thread.sleep(2000);
            order.addPaymentConfirmation(PaymentConfirmation.valueOf(true, RegisterTime.now(), "Payment success"));
            repo.save(order);
            System.out.println("Payment concluded! Order is ready to be processed!");
        }
        return order;
    }


}
