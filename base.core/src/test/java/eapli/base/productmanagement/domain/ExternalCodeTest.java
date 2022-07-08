package eapli.base.productmanagement.domain;

import eapli.base.util.domain.RandomString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExternalCodeTest {

    ExternalCode externalCode1 = new ExternalCode("code.11111");
    ExternalCode externalCode2 = new ExternalCode("code.22222");
    ExternalCode externalCode11 =  ExternalCode.valueOf("code.11111");

    @Test
    public void validDataTest(){
        new ExternalCode();
        assertNotNull(externalCode1);
        assertEquals(externalCode1, externalCode11);
    }

    @Test
    public void invalidDataTest(){
        assertThrows(IllegalArgumentException.class, ()->{ new ExternalCode(RandomString.generateRandomString(10));});
    }

    @Test
    public void equalsTest(){
        assertEquals(externalCode1, externalCode11);
        assertEquals(externalCode1, externalCode1);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();
        assertNotEquals(externalCode1, externalCode2);
        assertNotEquals(externalCode1, otherObject);
    }

    @Test
    public void toStringTest(){
        assertTrue("code.11111".contains(externalCode1.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(externalCode1.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(1, externalCode2.compareTo(externalCode1));
        assertEquals(-1, externalCode1.compareTo(externalCode2));
        assertEquals(0, externalCode1.compareTo(externalCode11));
    }

}