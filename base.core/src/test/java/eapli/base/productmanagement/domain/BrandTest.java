package eapli.base.productmanagement.domain;

import eapli.base.util.domain.RandomString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandTest {
    Brand brand1 = new Brand("Brand1");
    Brand brand2 = new Brand("Brand2");
    Brand brand11 =  Brand.valueOf("Brand1");

    @Test
    public void validDataTest(){
        new Brand();
        assertNotNull(brand1);
        assertEquals(brand1, brand11);
    }

    @Test
    public void invalidDataTest(){
        assertThrows(IllegalArgumentException.class, ()->{ new Brand(RandomString.generateRandomString(51));});
    }

    @Test
    public void equalsTest(){
        assertEquals(brand1, brand11);
        assertEquals(brand1, brand1);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();
        assertNotEquals(brand1, brand2);
        assertNotEquals(brand1, otherObject);
    }

    @Test
    public void toStringTest(){
        assertTrue("Brand1".contains(brand1.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(brand1.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(1, brand2.compareTo(brand1));
        assertEquals(-1, brand1.compareTo(brand2));
        assertEquals(0, brand1.compareTo(brand11));
    }
}