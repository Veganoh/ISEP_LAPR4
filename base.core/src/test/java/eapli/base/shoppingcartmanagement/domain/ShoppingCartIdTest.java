package eapli.base.shoppingcartmanagement.domain;

import eapli.base.warehousemanagement.domain.WarehouseId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartIdTest {

    ShoppingCartId shoppingCartId = new ShoppingCartId();

    @Test
    public void validConstructorTest(){
        new ShoppingCartId();
        assertNotNull(shoppingCartId);
    }

    @Test
    public void equalsTest(){
        assertEquals(shoppingCartId, shoppingCartId);
        assertEquals(shoppingCartId, new ShoppingCartId());
        assertNotEquals(shoppingCartId, new String());
    }
}