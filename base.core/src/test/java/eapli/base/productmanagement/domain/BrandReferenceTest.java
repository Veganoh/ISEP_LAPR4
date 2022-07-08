package eapli.base.productmanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandReferenceTest {

    BrandReference reference1 = new BrandReference("reference1");
    BrandReference reference2 = new BrandReference("reference2");
    BrandReference reference11 =  BrandReference.valueOf("reference1");

    @Test
    public void validDataTest(){
        new BrandReference();
        assertNotNull(reference1);
        assertEquals(reference1, reference11);
    }

    @Test
    public void invalidDataTest(){
        assertThrows(IllegalArgumentException.class, ()->{ new BrandReference("123456789123123456879456");});

    }

    @Test
    public void equalsTest(){
        assertEquals(reference1, reference11);
        assertEquals(reference1, reference1);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();
        assertNotEquals(reference1, reference2);
        assertNotEquals(reference1, otherObject);
    }

    @Test
    public void toStringTest(){
        assertTrue("reference1".contains(reference1.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(reference1.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(1, reference2.compareTo(reference1));
        assertEquals(-1, reference1.compareTo(reference2));
        assertEquals(0, reference1.compareTo(reference11));
    }
}