package eapli.base.agvtaskmanagement.domain;

import eapli.base.common.domain.AddressType;
import eapli.base.common.domain.Quantity;
import eapli.base.common.domain.Volume;
import eapli.base.common.domain.Weight;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.CustomerAddress;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.domain.OrderBuilder;
import eapli.base.ordermanagement.domain.PaymentMethod;
import eapli.base.productmanagement.domain.Product;
import eapli.base.shipmentmanagement.domain.ShippingMethod;
import org.junit.jupiter.api.Test;

import static eapli.base.util.domain.TestUtils.generateDummyCustomer;
import static eapli.base.util.domain.TestUtils.generateDummyProduct;
import static org.junit.jupiter.api.Assertions.*;

class AGVTaskTest {
    Customer testCustomer = generateDummyCustomer();
    CustomerAddress testBillingAddress = CustomerAddress.valueOf("test","testBillingAddress", AddressType.BILLING);
    CustomerAddress testShippingAddress = CustomerAddress.valueOf("test","testShippingAddress", AddressType.SHIPPING);
    ShippingMethod testshippingMethod = ShippingMethod.valueOf("test", "carrier1", "1.50 EUR", "KG", 0.23);
    Product testProduct = generateDummyProduct(1);
    Order order = new OrderBuilder().ofCustomer(testCustomer)
            .ofBillingAddress(testBillingAddress)
            .ofShippingAddress(testShippingAddress)
            .ofShippingMethod(testshippingMethod)
            .ofPaymentMethod(PaymentMethod.PAYPAL)
            .ofItem(testProduct, Quantity.valueOf(1))
            .build();

    @Test
    void validTaskCreation() {
        AGVTask task = new AGVTask(order);
        assertNotNull(task);
        assertTrue(task.order().sameAs(order));
    }

    @Test
    void invalidTaskCreation() {
        assertThrows(IllegalArgumentException.class, () -> new AGVTask(null));
    }

    @Test
    void emptyConstructor() {
        AGVTask empty = new AGVTask();
        assertNotNull(empty);
    }

    @Test
    void  testSameAs(){
        AGVTask task = new AGVTask(order);
        assertNull(task.identity());
        assertTrue(task.sameAs(task));
    }

    @Test
    void  testWeightAndVolume(){
        System.out.println(order.weight());
        AGVTask task = new AGVTask(order);
        assertEquals(task.weight(), Weight.valueOf("1 G"));
        assertEquals(task.volume(), Volume.valueOf("1 MM"));
    }
}