package eapli.base.customermanagement.domain;

import eapli.base.common.domain.AddressType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostalAddressTest {
    PostalAddress postalAddress1 = new PostalAddress("rua", AddressType.SHIPPING);
    PostalAddress postalAddress2 = new PostalAddress("avenida", AddressType.BILLING);
    PostalAddress postalAddress3 = new PostalAddress("rua", AddressType.SHIPPING);

    @Test
    public void validConstructorTest(){
        new PostalAddress();
        assertNotNull(postalAddress1);
        assertEquals(postalAddress1, postalAddress3);
    }

    @Test
    public void emptyConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new PostalAddress(null,null));
        String expectedMessage = ("At least one of the required method parameters is null");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void invalidConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new PostalAddress("%&#$",AddressType.SHIPPING));
        String expectedMessage = ("Invalid address!");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void equalsTest(){
        assertEquals(postalAddress1, postalAddress3);
        assertEquals(postalAddress1, postalAddress1);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();

        assertNotEquals(postalAddress1, postalAddress2);
        assertNotEquals(postalAddress1, otherObject);
    }

    @Test
    public void toStringTest(){
        assertTrue("Address: rua\nAddress Type: SHIPPING".contains(postalAddress1.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(postalAddress1.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(-17,postalAddress2.compareTo(postalAddress1));
        assertEquals(17,postalAddress1.compareTo(postalAddress2));
        assertEquals(0,postalAddress1.compareTo(postalAddress3));
    }
}