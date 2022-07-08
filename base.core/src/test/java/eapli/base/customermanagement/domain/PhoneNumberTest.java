package eapli.base.customermanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberTest {
    PhoneNumber phoneNumber1 = new PhoneNumber("+351","123456789");
    PhoneNumber phoneNumber2 = new PhoneNumber("+351","987654321");
    PhoneNumber phoneNumber3 = new PhoneNumber("+351","123456789");

    @Test
    public void validConstructorTest(){
        new PhoneNumber();
        assertNotNull(phoneNumber1);
        assertEquals(phoneNumber1, phoneNumber3);
    }

    @Test
    public void emptyConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(null,null));
        String expectedMessage = ("At least one of the required method parameters is null");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void invalidConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("+111","123456789"));
        String expectedMessage = ("Invalid dialing code");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void invalidConstructorTest2(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("+351","1234567890"));
        String expectedMessage = ("Invalid phone number");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void equalsTest(){
        assertEquals(phoneNumber1, phoneNumber3);
        assertEquals(phoneNumber1, phoneNumber1);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();

        assertNotEquals(phoneNumber1, phoneNumber2);
        assertNotEquals(phoneNumber1, otherObject);
    }

    @Test
    public void toStringTest(){
        assertTrue("Dialing Code: +351\n Phone Number: 123456789".contains(phoneNumber1.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(phoneNumber1.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(8,phoneNumber2.compareTo(phoneNumber1));
        assertEquals(-8,phoneNumber1.compareTo(phoneNumber2));
        assertEquals(0,phoneNumber1.compareTo(phoneNumber3));
    }
}