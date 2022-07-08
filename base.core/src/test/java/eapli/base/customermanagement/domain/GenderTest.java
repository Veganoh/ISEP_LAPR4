package eapli.base.customermanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenderTest {
    Gender gender1 = new Gender("Male");
    Gender gender2 = new Gender("Female");
    Gender gender3 = new Gender("Male");

    @Test
    public void validConstructorTest(){
        new Gender();
        assertNotNull(gender1);
        assertEquals(gender1, gender3);
    }

    @Test
    public void emptyConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Gender(null));
        String expectedMessage = ("Customer must specify a gender");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void invalidConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Gender("abc"));
        String expectedMessage = ("Gender isn't on the system");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void equalsTest(){
        assertEquals(gender1, gender3);
        assertEquals(gender1, gender1);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();

        assertNotEquals(gender1, gender2);
        assertNotEquals(gender1, otherObject);
    }

    @Test
    public void toStringTest(){
        assertTrue("Male".contains(gender1.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(gender1.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(-7,gender2.compareTo(gender1));
        assertEquals(7,gender1.compareTo(gender2));
        assertEquals(0,gender1.compareTo(gender3));
    }
}