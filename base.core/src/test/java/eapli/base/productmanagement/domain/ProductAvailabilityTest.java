package eapli.base.productmanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductAvailabilityTest {

    ProductAvailability productCode1 = new ProductAvailability(true);
    ProductAvailability productCode2 = new ProductAvailability(false);
    ProductAvailability productCode11 =  ProductAvailability.valueOf(true);

    @Test
    public void validDataTest(){
        new ProductAvailability();
        assertNotNull(productCode1);
        assertEquals(productCode1, productCode11);
        assertTrue(productCode1.isAvailable());
        assertFalse(productCode2.isAvailable());
    }

    @Test
    public void equalsTest(){
        assertEquals(productCode1, productCode11);
        assertEquals(productCode1, productCode1);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();
        assertNotEquals(productCode1, productCode2);
        assertNotEquals(productCode1, otherObject);
    }

    @Test
    public void toStringTest(){
        assertTrue("Product is available for retail.".contains(productCode1.toString()));
        assertTrue("Product is not available for retail.".contains(productCode2.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(productCode1.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(-1, productCode2.compareTo(productCode1));
        assertEquals(1, productCode1.compareTo(productCode2));
        assertEquals(0, productCode1.compareTo(productCode11));
    }
}