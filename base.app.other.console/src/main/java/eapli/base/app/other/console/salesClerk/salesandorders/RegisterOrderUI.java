package eapli.base.app.other.console.salesClerk.salesandorders;

import eapli.base.app.other.console.salesClerk.manageUsers.AddCustomerUI;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.CustomerAddress;
import eapli.base.ordermanagement.application.RegisterOrderController;
import eapli.base.ordermanagement.domain.PaymentMethod;
import eapli.base.shipmentmanagement.domain.ShippingMethod;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class RegisterOrderUI extends AbstractUI {

    /**
     * The UI's controller
     */
    private final RegisterOrderController theController = new RegisterOrderController();

    /**
     * Main method of the UI
     *
     * @return shows the steps to add a new product
     */
    @Override
    protected boolean doShow() {
        try {
            Customer customer = customerSelect();
            CustomerAddress billing = billingAddressSelect(customer);
            CustomerAddress shipping = shippingAddressSelect(customer);
            ShippingMethod method = shippingMethodSelect();
            PaymentMethod payment = paymentMethodSelect();

            theController.register(customer, billing, shipping, method, payment);

            productLoop();

            theController.save();

            System.out.println("\nOrder successfully added\n");
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    private CustomerAddress billingAddressSelect(Customer customer) {
        if (theController.getCustomerBillingAddresses(customer).isEmpty()) return addressAdd();

        SelectWidget<CustomerAddress> billingAddressWidget = new SelectWidget<>("Select a Billing Address (Exit to create a new one)", theController.getCustomerBillingAddresses(customer));
        billingAddressWidget.show();
        final CustomerAddress billing = billingAddressWidget.selectedElement();

        if (billing == null) return addressAdd();
        return billing;
    }

    private CustomerAddress shippingAddressSelect(Customer customer) {
        if (theController.getCustomerShippingAddresses(customer).isEmpty()) return addressAdd();

        SelectWidget<CustomerAddress> shippingAddressWidget = new SelectWidget<>("Select a Shipping Address", theController.getCustomerShippingAddresses(customer));
        shippingAddressWidget.show();
        final CustomerAddress shipping = shippingAddressWidget.selectedElement();

        if (shipping == null) return addressAdd();
        return shipping;
    }

    private CustomerAddress addressAdd() {
        throw new UnsupportedOperationException("not implemented yet");
    }

    private Customer customerSelect() {
        if (theController.getCustomers().isEmpty()) customerAdd();

        SelectWidget<Customer> customerWidget = new SelectWidget<>("Select a Customer (Exit to create a new one)", theController.getCustomers());
        customerWidget.show();
        final Customer customer = customerWidget.selectedElement();

        if (customer == null) {
            customerAdd();
            return customerSelect();
        }

        return customer;
    }

    private ShippingMethod shippingMethodSelect() {
        if (theController.getShippingMethods().isEmpty()) throw new UnsupportedOperationException("There are no valid shipping methods registered in the system. Please run bootstrap.");

        SelectWidget<ShippingMethod> shippingWidget = new SelectWidget<>("Select a shipping method", theController.getShippingMethods());
        shippingWidget.show();
        final ShippingMethod shipping = shippingWidget.selectedElement();

        return shipping;
    }

    private PaymentMethod paymentMethodSelect() {
        if (theController.getPaymentMethods().isEmpty()) throw new UnsupportedOperationException("There are no valid payment methods registered in the system. Please run bootstrap.");

        SelectWidget<PaymentMethod> paymentWidget = new SelectWidget<>("Select a payment method", theController.getPaymentMethods());
        paymentWidget.show();
        final PaymentMethod shipping = paymentWidget.selectedElement();

        return shipping;
    }

    private void customerAdd() {
        new AddCustomerUI().show();
    }

    /**
     * Main loop to add new order items
     */
    private void productLoop() {
        do {
            new ShowCatalogUI().show();

            final String product = Console.readLine("Select a Product (type the desired Product Code)");

            final int quantity = Console.readInteger("Select desired Quantity");

            theController.registerOrderLine(product, quantity);
        } while (Console.readBoolean("Do you wish to add more products? Y/N"));
    }

    /**
     * The UI headline
     *
     * @return The UI headline
     */
    @Override
    public String headline() {
        return "Add a new Order - Sales Clerk";
    }
}
