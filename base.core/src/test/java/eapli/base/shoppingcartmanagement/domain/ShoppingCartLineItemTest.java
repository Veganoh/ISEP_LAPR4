package eapli.base.shoppingcartmanagement.domain;

import eapli.base.common.domain.Quantity;
import eapli.base.ordermanagement.domain.OrderLineItem;
import eapli.framework.general.domain.model.Money;
import org.junit.jupiter.api.Test;

import static eapli.base.util.domain.TestUtils.generateDummyProduct;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartLineItemTest {

    @Test
    void testShoppingCartLineItem() {
        ShoppingCartLineItem item1 = new ShoppingCartLineItem(generateDummyProduct(1));
        ShoppingCartLineItem item2 = new ShoppingCartLineItem(generateDummyProduct(2), Quantity.valueOf(2));

        assertNotNull(item1);
        assertNotNull(item2);

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
    void testGetTotal() {
        ShoppingCartLineItem item1 = new ShoppingCartLineItem(generateDummyProduct(1), Quantity.valueOf(5)); //1.50 €
        ShoppingCartLineItem item2 = new ShoppingCartLineItem(generateDummyProduct(500), Quantity.valueOf(2)); //500.50 €

        assertEquals(item1.total(), Money.euros(7.50));
        assertEquals(item1.totalWithTax(), Money.euros(9.25));

        assertEquals(item2.total(), Money.euros(1001.00));
        assertEquals(item2.totalWithTax(), Money.euros(1231.24));

        item2.decreaseQuantity();
        assertEquals(item2.total(), Money.euros(500.50));
        assertEquals(item2.totalWithTax(), Money.euros(615.62));
    }
}