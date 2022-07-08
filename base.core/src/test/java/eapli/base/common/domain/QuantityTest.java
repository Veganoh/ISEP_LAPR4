package eapli.base.common.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

    @Test
    public void TestQuantity() {
        Quantity quantity1 = new Quantity(2);
        Quantity quantity2 = new Quantity(4);
        Quantity quantity3 = Quantity.valueOf(2);

        assertNotNull(quantity1);
        assertNotNull(quantity2);
        assertNotNull(quantity3);

        assertEquals(quantity1, quantity3);

        assertThrows(IllegalArgumentException.class, () -> {
            Quantity quantity4 = Quantity.valueOf(-1);
        });
    }
}