package eapli.base.productmanagement.domain;

import eapli.base.util.domain.RandomString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductCodeTest {

    ProductCode productCode1 = new ProductCode("code.11111");
    ProductCode productCode2 = new ProductCode("code.22222");
    ProductCode productCode11 =  ProductCode.valueOf("code.11111");

    @Test
    public void validDataTest(){
        new ProductCode();
        assertNotNull(productCode1);
        assertEquals(productCode1, productCode11);
    }

    @Test
    public void invalidDataTest(){
        assertThrows(IllegalArgumentException.class, ()->{ new ProductCode(RandomString.generateRandomString(10));});
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
        assertTrue("code.11111".contains(productCode1.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(productCode1.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(1, productCode2.compareTo(productCode1));
        assertEquals(-1, productCode1.compareTo(productCode2));
        assertEquals(0, productCode1.compareTo(productCode11));
    }
}