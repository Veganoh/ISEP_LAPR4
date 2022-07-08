package eapli.base.ordermanagement.domain;

import eapli.base.common.domain.AddressType;
import eapli.base.common.domain.Quantity;
import eapli.base.common.domain.RegisterTime;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.CustomerAddress;
import eapli.base.shipmentmanagement.domain.ShippingMethod;
import eapli.framework.general.domain.model.Money;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static eapli.base.util.domain.TestUtils.generateDummyCustomer;
import static eapli.base.util.domain.TestUtils.generateDummyProduct;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    SalesClerk testSalesClerk = new SalesClerk();
    Customer testCustomer = generateDummyCustomer();
    CustomerAddress testBillingAddress = CustomerAddress.valueOf("test", "testBillingAddress", AddressType.BILLING);
    CustomerAddress testShippingAddress = CustomerAddress.valueOf("test", "testShippingAddress", AddressType.SHIPPING);
    ShippingMethod testShippingMethod = ShippingMethod.valueOf("test", "carrier1", "1.50 EUR", "G", 23); //1.50 € / G
    PaymentMethod testPaymentMethod = PaymentMethod.PAYPAL;
    List<OrderLineItem> testOrderLineItems = Arrays.asList(new OrderLineItem(1, generateDummyProduct(1), Quantity.valueOf(1)), //1.50 €    1 G     Qty 1
            new OrderLineItem(2, generateDummyProduct(2), Quantity.valueOf(2))); //2.50 €   2 G     Qty 2

    @Test
    void testOrderConstructorSalesClerkNullable() {
        Order orderWithoutSalesClerk = new Order(null, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        assertNotNull(orderWithoutSalesClerk);
    }

    @Test
    void testOrderConstructorCustomerNotNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Order orderCustomerNull = new Order(testSalesClerk, null, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        });
    }

    @Test
    void testOrderConstructorAddressNotNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Order orderBillingAddressNull = new Order(testSalesClerk, testCustomer, null, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Order orderShippingAddressNull = new Order(testSalesClerk, testCustomer, testBillingAddress, null, testShippingMethod, testPaymentMethod, testOrderLineItems);
        });
    }

    @Test
    void testOrderConstructorAddressTypeCheck() {
        assertThrows(IllegalArgumentException.class, () -> {
            Order orderAddressTypeSwitched = new Order(testSalesClerk, testCustomer, testShippingAddress, testBillingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        });
    }

    @Test
    void testOrderConstructorShippingMethodNotNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Order orderShippingMethodNull = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, null, testPaymentMethod, testOrderLineItems);
        });
    }

    @Test
    void testOrderConstructorPaymentMethodNotNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Order orderPaymentMethodNull = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, null, testOrderLineItems);
        });
    }

    @Test
    void testOrderConstructorLineItemsNotNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            Order orderLineItemsNull = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Order orderLineItemsEmpty = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, new ArrayList<>());
        });
    }

    @Test
    void testOrderConstructorSuccessful() {
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);

        assertNotNull(order);
    }

    @Test
    void testOrderNumberOfLines() {
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        assertEquals(2, order.numberOfLines());
    }

    @Test
    void testOrderShippingTotal() {
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        //Shipping Total = 1.50 € * (1 G * 1 + 2 G * 2)
        assertEquals(Money.euros(7.50), order.shippingTotal());
        //With Tax (23 %)
        assertEquals(Money.euros(7.50 * 1.23), order.shippingTotalWithTax());
    }

    @Test
    void testOrderShippingTotalWithConversion() {
        ShippingMethod shippingMethodKG = ShippingMethod.valueOf("methodWithKG", "carrier2", "100.00 EUR", "KG", 20); //0.10 € / G
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, shippingMethodKG, testPaymentMethod, testOrderLineItems);

        //Shipping Total = 0.10 € * (1 G * 1 + 2 G * 2)
        assertEquals(Money.euros(0.50), order.shippingTotal());
        //With Tax (20 %)
        assertEquals(Money.euros(0.50 * 1.2), order.shippingTotalWithTax());
    }

    @Test
    void testOrderTotal() {
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        //Total = Products Total (1.50 € * 1 + 2.50 € * 2) + Shipping Total (1.50 € * (1 G * 1 + 2 G * 2))
        assertEquals(Money.euros(14.00), order.total());
        //With Tax (23%) = Products Total (1.85 € * 1 + 3,07 € * 2) + Shipping Total (1.85 € * (1 G * 1 + 2 G * 2))
        assertEquals(Money.euros(17.24), order.totalWithTax());
    }

    @Test
    void testOrderTotalWithConversion() {
        ShippingMethod shippingMethodKG = ShippingMethod.valueOf("methodWithKG", "carrier2", "100.00 EUR", "KG", 20); //0.10 € / G
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, shippingMethodKG, testPaymentMethod, testOrderLineItems);

        //Total = Products Total (1.50 € * 1 + 2.50 € * 2) + Shipping Total (0.10 € * (1 G * 1 + 2 G * 2))
        assertEquals(Money.euros(7.00), order.total());
        //With Tax (23% - Products, 20% - Shipping)
        //Products Total (1.85 € * 1 + 3.07 € * 2) + Shipping Total (0.12 € * (1 G * 1 + 2 G * 2))
        assertEquals(Money.euros(8.61), order.totalWithTax());
    }

    @Test
    void testAddOrderLineItemNotNull() {
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);

        assertThrows(IllegalArgumentException.class, () -> {
            order.addOrderLineItem(null);
        });
    }

    @Test
    void testAddOrderLineItemSuccessful() {
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);

        order.addOrderLineItem(new OrderLineItem(order.numberOfLines() + 1, generateDummyProduct(3), Quantity.valueOf(3))); //3.50 €    3 G     Qty 3
        //Assert number of lines increases
        assertEquals(3, order.numberOfLines());
        //Assert total is updated
        assertEquals(Money.euros(38.00), order.total());
        // Total =
        // Products Total (1.50 € * 1 + 2.50 € * 2 + 3.50 € * 3)
        // +
        // Shipping Total (1.50 € * (1 G * 1 + 2 G * 2 + 3 G * 3))
    }

    @Test
    void testAddPaymentConfirmationNotNull() {
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);

        assertThrows(IllegalArgumentException.class, () -> {
            order.addPaymentConfirmation(null);
        });
    }

    @Test
    void testAddPaymentConfirmationRejected() {
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);

        order.addPaymentConfirmation(PaymentConfirmation.valueOf(false, RegisterTime.now(), "Payment failed"));
    }

    @Test
    void testAddPaymentConfirmationSuccessful() {
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);

        order.addPaymentConfirmation(PaymentConfirmation.valueOf(true, RegisterTime.now(), "Payment successful"));
    }

    @Test
    void testPaymentPendingToReadyForPackagingInvalid() {
        //Order in state PAYMENT_PENDING
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);

        assertThrows(IllegalStateException.class, order::readyForPackaging);
    }

    @Test
    void testPaymentPendingToBeingPreparedInvalid() {
        //Order in state PAYMENT_PENDING
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);

        assertThrows(IllegalStateException.class, order::beingPrepared);
    }

    @Test
    void testPaymentPendingToDeliveredByCarrierInvalid() {
        //Order in state PAYMENT_PENDING
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);

        assertThrows(IllegalStateException.class, order::deliveredByCarrier);
    }

    @Test
    void testPaymentPendingToDispatchedInvalid() {
        //Order in state PAYMENT_PENDING
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);

        assertThrows(IllegalStateException.class, order::dispatched);
    }

    @Test
    void testPaymentPendingToReadyForDispatchInvalid() {
        //Order in state PAYMENT_PENDING
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);

        assertThrows(IllegalStateException.class, order::readyForDispatch);
    }

    @Test
    void testToBePreparedToBeingPreparedValid() {
        //Order in state TO_BE_PREPARED
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        order.addPaymentConfirmation(PaymentConfirmation.valueOf(true, RegisterTime.now(), "Payment successful"));


        assertDoesNotThrow(order::beingPrepared);
    }

    @Test
    void testToBePreparedToReadyForDispatchInvalid() {
        //Order in state TO_BE_PREPARED
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        order.addPaymentConfirmation(PaymentConfirmation.valueOf(true, RegisterTime.now(), "Payment successful"));

        assertThrows(IllegalStateException.class, order::readyForDispatch);
    }

    @Test
    void testToBePreparedToDispatchedInvalid() {
        //Order in state TO_BE_PREPARED
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        order.addPaymentConfirmation(PaymentConfirmation.valueOf(true, RegisterTime.now(), "Payment successful"));

        assertThrows(IllegalStateException.class, order::dispatched);
    }

    @Test
    void testToBePreparedToDeliveredByCarrierInvalid() {
        //Order in state TO_BE_PREPARED
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        order.addPaymentConfirmation(PaymentConfirmation.valueOf(true, RegisterTime.now(), "Payment successful"));

        assertThrows(IllegalStateException.class, order::deliveredByCarrier);
    }

    @Test
    void testToBePreparedToReadyForPackagingInvalid() {
        //Order in state TO_BE_PREPARED
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        order.addPaymentConfirmation(PaymentConfirmation.valueOf(true, RegisterTime.now(), "Payment successful"));

        assertThrows(IllegalStateException.class, order::readyForPackaging);
    }

    @Test
    void testBeingPreparedToReadyForPackagingValid() {
        //Order in state BEING_PREPARED
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        order.addPaymentConfirmation(PaymentConfirmation.valueOf(true, RegisterTime.now(), "Payment successful"));
        order.beingPrepared();

        assertDoesNotThrow(order::readyForPackaging);
    }

    @Test
    void testBeingPreparedToReadyForDispatchInValid() {
        //Order in state BEING_PREPARED
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        order.addPaymentConfirmation(PaymentConfirmation.valueOf(true, RegisterTime.now(), "Payment successful"));
        order.beingPrepared();

        assertThrows(IllegalStateException.class, order::readyForDispatch);
    }

    @Test
    void testBeingPreparedToDispatchedInValid() {
        //Order in state BEING_PREPARED
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        order.addPaymentConfirmation(PaymentConfirmation.valueOf(true, RegisterTime.now(), "Payment successful"));
        order.beingPrepared();

        assertThrows(IllegalStateException.class, order::dispatched);
    }

    @Test
    void testBeingPreparedToDeliveredByCarrierInValid() {
        //Order in state BEING_PREPARED
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        order.addPaymentConfirmation(PaymentConfirmation.valueOf(true, RegisterTime.now(), "Payment successful"));
        order.beingPrepared();

        assertThrows(IllegalStateException.class, order::deliveredByCarrier);
    }

    @Test
    void testBeingPreparedToBeingPreparedInValid() {
        //Order in state BEING_PREPARED
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        order.addPaymentConfirmation(PaymentConfirmation.valueOf(true, RegisterTime.now(), "Payment successful"));
        order.beingPrepared();

        assertThrows(IllegalStateException.class, order::beingPrepared);
    }

    @Test
    void testReadyForPackagingToReadyForDispatchingValid() {
        //Order in state READY_FOR_PACKAGING
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        order.addPaymentConfirmation(PaymentConfirmation.valueOf(true, RegisterTime.now(), "Payment successful"));
        order.beingPrepared();
        order.readyForPackaging();

        assertDoesNotThrow(order::readyForDispatch);
    }

    @Test
    void testReadyForPackagingToDispatchedInvalid() {
        //Order in state READY_FOR_PACKAGING
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        order.addPaymentConfirmation(PaymentConfirmation.valueOf(true, RegisterTime.now(), "Payment successful"));
        order.beingPrepared();
        order.readyForPackaging();

        assertThrows(IllegalStateException.class, order::dispatched);
    }

    @Test
    void testReadyForPackagingToDeliveredByCarrierInvalid() {
        //Order in state READY_FOR_PACKAGING
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        order.addPaymentConfirmation(PaymentConfirmation.valueOf(true, RegisterTime.now(), "Payment successful"));
        order.beingPrepared();
        order.readyForPackaging();

        assertThrows(IllegalStateException.class, order::deliveredByCarrier);
    }

    @Test
    void testReadyForPackagingToReadyForPackagingInvalid() {
        //Order in state READY_FOR_PACKAGING
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        order.addPaymentConfirmation(PaymentConfirmation.valueOf(true, RegisterTime.now(), "Payment successful"));
        order.beingPrepared();
        order.readyForPackaging();

        assertThrows(IllegalStateException.class, order::readyForPackaging);
    }

    @Test
    void testReadyForPackagingToBeingPreparedInvalid() {
        //Order in state READY_FOR_PACKAGING
        Order order = new Order(testSalesClerk, testCustomer, testBillingAddress, testShippingAddress, testShippingMethod, testPaymentMethod, testOrderLineItems);
        order.addPaymentConfirmation(PaymentConfirmation.valueOf(true, RegisterTime.now(), "Payment successful"));
        order.beingPrepared();
        order.readyForPackaging();

        assertThrows(IllegalStateException.class, order::beingPrepared);
    }
}