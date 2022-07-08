package eapli.base.customermanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {
    Name name1 = new Name("Name");
    Name name2 = new Name("a");
    Name name3 = new Name("Name");

    @Test
    public void validConstructorTest(){
        new Name();
        assertNotNull(name1);
        assertEquals(name1, name3);
    }

    @Test
    public void emptyConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Name(null));
        String expectedMessage = ("Customer must have a name");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void invalidConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Name("123456789"));
        String expectedMessage = ("Invalid name");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void equalsTest(){
        assertEquals(name1, name3);
        assertEquals(name1, name1);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();

        assertNotEquals(name1, name2);
        assertNotEquals(name1, otherObject);
    }

    @Test
    public void toStringTest(){
        assertTrue("Name".contains(name1.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(name1.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(19,name2.compareTo(name1));
        assertEquals(-19,name1.compareTo(name2));
        assertEquals(0,name1.compareTo(name3));
    }
}