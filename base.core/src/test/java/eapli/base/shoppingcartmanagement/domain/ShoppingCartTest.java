package eapli.base.shoppingcartmanagement.domain;

import eapli.base.common.domain.Quantity;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.general.domain.model.Money;
import org.junit.jupiter.api.Test;

import static eapli.base.util.domain.TestUtils.generateDummyCustomer;
import static eapli.base.util.domain.TestUtils.generateDummyProduct;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {
    Customer testCustomer = generateDummyCustomer();

    @Test
    void testShoppingCartCustomerNonNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ShoppingCart(null);
        });
    }

    @Test
    void testShoppingCartSuccessful() {
        ShoppingCart shoppingCart = new ShoppingCart(testCustomer);

        assertNotNull(shoppingCart);
    }

    @Test
    void testAddShoppingCartLineItemProductNonNull() {
        ShoppingCart shoppingCart = new ShoppingCart(testCustomer);
        assertThrows(IllegalArgumentException.class, () -> {
            shoppingCart.addShoppingCartLineItem(null, Quantity.valueOf(1));
        });
    }

    @Test
    void testAddShoppingCartLineItemQuantityNonNull() {
        ShoppingCart shoppingCart = new ShoppingCart(testCustomer);
        assertThrows(IllegalArgumentException.class, () -> {
            shoppingCart.addShoppingCartLineItem(generateDummyProduct(1), null);
        });
    }

    @Test
    void testAddShoppingCartLineItemSuccessful() {
        ShoppingCart shoppingCart = new ShoppingCart(testCustomer);

        assertEquals(0, shoppingCart.numberOfLines());
        shoppingCart.addShoppingCartLineItem(generateDummyProduct(1), Quantity.valueOf(1));
    }

    @Test
    void testRemoveShoppingCartLineItemNonNull() {
        ShoppingCart shoppingCart = new ShoppingCart(testCustomer);

        assertThrows(IllegalArgumentException.class, () -> {
            shoppingCart.removeShoppingCartLineItem(null);
        });
    }

    @Test
    void testRemoveShoppingCartLineItemSuccessful() {
        ShoppingCart shoppingCart = new ShoppingCart(testCustomer);

        ShoppingCartLineItem item = shoppingCart.addShoppingCartLineItem(generateDummyProduct(1), Quantity.valueOf(1));
        assertEquals(1, shoppingCart.numberOfLines());

        shoppingCart.removeShoppingCartLineItem(item);
        assertEquals(0, shoppingCart.numberOfLines());
    }

    @Test
    void testRemoveShoppingCartLineItemNonExistent() {
        ShoppingCart shoppingCart = new ShoppingCart(testCustomer);
        ShoppingCartLineItem item = shoppingCart.addShoppingCartLineItem(generateDummyProduct(1), Quantity.valueOf(1));

        //remove once
        assertTrue(shoppingCart.removeShoppingCartLineItem(item));
        assertEquals(0, shoppingCart.numberOfLines());

        //remove twice
        assertFalse(shoppingCart.removeShoppingCartLineItem(item));
        assertEquals(0, shoppingCart.numberOfLines());

    }

    @Test
    void testShoppingCartIncreaseQuantityNonNull() {
        ShoppingCart shoppingCart = new ShoppingCart(testCustomer);

        assertThrows(IllegalArgumentException.class, () -> {
            shoppingCart.increaseQuantity(null);
        });
    }

    @Test
    void testShoppingCartIncreaseQuantitySuccessful() {
        ShoppingCart shoppingCart = new ShoppingCart(testCustomer);

        ShoppingCartLineItem item = shoppingCart.addShoppingCartLineItem(generateDummyProduct(1), Quantity.valueOf(1)); //1.50 € * 1

        assertEquals(Money.euros(1.50), shoppingCart.total());

        shoppingCart.increaseQuantity(item);
        assertEquals(Money.euros(3.00), shoppingCart.total());
    }

    @Test
    void testShoppingCartTotal() {
        ShoppingCart shoppingCart = new ShoppingCart(testCustomer);

        Product product1 = generateDummyProduct(1);
        Product product2 = generateDummyProduct(2);

        Quantity quantity1 = Quantity.valueOf(2);
        Quantity quantity2 = Quantity.valueOf(4);

        ShoppingCartLineItem item1 = shoppingCart.addShoppingCartLineItem(product1, quantity1);
        assertEquals(shoppingCart.total(), Money.euros(3.00));
        assertEquals(shoppingCart.totalWithTax(), Money.euros(3.70));

        ShoppingCartLineItem item2 = shoppingCart.addShoppingCartLineItem(product2, quantity2);
        assertEquals(shoppingCart.total(), Money.euros(13.00));
        assertEquals(shoppingCart.totalWithTax(), Money.euros(16.02));

        shoppingCart.removeShoppingCartLineItem(item1);
        assertEquals(shoppingCart.total(), Money.euros(10.00));

        shoppingCart.increaseQuantity(item2);
        assertEquals(shoppingCart.total(), Money.euros(12.50));
    }
}