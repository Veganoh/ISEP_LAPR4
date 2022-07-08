package eapli.base.customermanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {
    Email email1 = new Email("abc@abc.com");
    Email email2 = new Email("email2@gmail.com");
    Email email3 = new Email("abc@abc.com");

    @Test
    public void validConstructorTest(){
        new Email();
        assertNotNull(email1);
        assertEquals(email1, email3);
    }

    @Test
    public void emptyConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Email(null));
        String expectedMessage = ("Customer must have an email");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void invalidConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Email("abc"));
        String expectedMessage = ("Invalid email");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void equalsTest(){
        assertEquals(email1, email3);
        assertEquals(email1, email1);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();

        assertNotEquals(email1, email2);
        assertNotEquals(email1, otherObject);
    }

    @Test
    public void toStringTest(){
        assertTrue("abc@abc.com".contains(email1.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(email1.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(4,email2.compareTo(email1));
        assertEquals(-4,email1.compareTo(email2));
        assertEquals(0,email1.compareTo(email3));
    }
}