package eapli.base.ordermanagement.domain;

import eapli.base.common.domain.Quantity;
import eapli.base.common.domain.Weight;
import eapli.base.common.domain.WeightUnit;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.general.domain.model.Money;
import org.junit.jupiter.api.Test;

import static eapli.base.util.domain.TestUtils.generateDummyProduct;
import static org.junit.jupiter.api.Assertions.*;

class OrderLineItemTest {

    @Test
    void testOrderLineItem() {
        Product dummyProduct= generateDummyProduct(1);
        OrderLineItem item1 = new OrderLineItem(1,dummyProduct );
        OrderLineItem item2 = new OrderLineItem(2, generateDummyProduct(2), Quantity.valueOf(2));
        OrderLineItem item3 = new OrderLineItem(1, dummyProduct);

        assertNotNull(item1);
        assertNotNull(item2);

        assertEquals(item1, item3);

        assertThrows(IllegalArgumentException.class, () -> {
            OrderLineItem item4 = new OrderLineItem(-1, generateDummyProduct(1));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            OrderLineItem item5 = new OrderLineItem(1, null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            OrderLineItem item6 = new OrderLineItem(1, generateDummyProduct(1), null);
        });
    }

    @Test
    void testGetWeight() {
        OrderLineItem item1 = new OrderLineItem(1, generateDummyProduct(1), Quantity.valueOf(5)); //1 KG
        OrderLineItem item2 = new OrderLineItem(2, generateDummyProduct(10), Quantity.valueOf(2)); //10 KG

        assertEquals(item1.weight(), Weight.valueOf("5 G"));
        assertEquals(item2.weight(), Weight.valueOf(20, WeightUnit.G));

        item2.increaseQuantity();
        item2.increaseQuantity();
        assertEquals(item2.weight(), Weight.valueOf(40, WeightUnit.G));
    }

    @Test
    void testGetTotal() {
        OrderLineItem item1 = new OrderLineItem(1, generateDummyProduct(1), Quantity.valueOf(5)); //1.50 € * 1
        OrderLineItem item2 = new OrderLineItem(2, generateDummyProduct(500), Quantity.valueOf(2)); //500.50 € * 2

        assertEquals(item1.total(), Money.euros(7.50));
        assertEquals(item1.totalWithTax(), Money.euros(9.25));

        assertEquals(item2.total(), Money.euros(1001.00));
        assertEquals(item2.totalWithTax(), Money.euros(1231.24));

        item2.decreaseQuantity();
        assertEquals(item2.total(), Money.euros(500.50));
        assertEquals(item2.totalWithTax(), Money.euros(615.62));
    }
}