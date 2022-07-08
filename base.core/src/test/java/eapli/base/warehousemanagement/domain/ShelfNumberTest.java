package eapli.base.warehousemanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShelfNumberTest {


    ShelfNumber shelfNumber = new ShelfNumber("1");
    ShelfNumber otherShelfNumber = new ShelfNumber("2");
    ShelfNumber shelfNumberCopy =  ShelfNumber.valueOf("1");

    @Test
    public void validConstructorTest(){
        new ShelfNumber();
        assertNotNull(shelfNumber);
        assertEquals(shelfNumber,shelfNumberCopy);
    }

    @Test
    public void emptyConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ShelfNumber(null));
        String expectedMessage = ("ID cannot be empty");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void invalidConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ShelfNumber("abcdef"));
        String expectedMessage = ("Shelf number must be a positive numeric");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void equalsTest(){
        assertEquals(shelfNumber, shelfNumberCopy);
        assertEquals(shelfNumber, shelfNumber);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();

        assertNotEquals(shelfNumber, otherShelfNumber);
        assertNotEquals(shelfNumber, otherObject);
    }

    @Test
    public void toStringTest(){
        assertTrue("1".contains(shelfNumber.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(shelfNumber.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(1,otherShelfNumber.compareTo(shelfNumber));
        assertEquals(-1,shelfNumber.compareTo(otherShelfNumber));
        assertEquals(0,shelfNumber.compareTo(shelfNumberCopy));
    }
}