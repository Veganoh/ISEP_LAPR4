package eapli.base.customermanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerIdentifierTest {
    CustomerIdentifier customer1 = new CustomerIdentifier("1");
    CustomerIdentifier customer2 = new CustomerIdentifier("2");
    CustomerIdentifier customer3 = new CustomerIdentifier("1");

    @Test
    public void validConstructorTest(){
        new CustomerIdentifier();
        assertNotNull(customer1);
        assertEquals(customer1, customer3);
    }

    @Test
    public void emptyConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new CustomerIdentifier(null));
        String expectedMessage = ("Customer must have an identifier");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void invalidConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new CustomerIdentifier("@#$%"));
        String expectedMessage = ("Invalid identifier");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void equalsTest(){
        assertEquals(customer1, customer3);
        assertEquals(customer1, customer1);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();

        assertNotEquals(customer1, customer2);
        assertNotEquals(customer1, otherObject);
    }

    @Test
    public void toStringTest(){
        assertTrue("1".contains(customer1.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(customer1.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(1,customer2.compareTo(customer1));
        assertEquals(-1,customer1.compareTo(customer2));
        assertEquals(0,customer1.compareTo(customer3));
    }
}