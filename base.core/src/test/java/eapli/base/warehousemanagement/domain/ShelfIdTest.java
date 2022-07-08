package eapli.base.warehousemanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShelfIdTest {

    ShelfId shelfId = new ShelfId(1L);
    ShelfId otherShelfId = new ShelfId(2L);
    ShelfId shelfIdCopy =  ShelfId.valueOf(1L);

    @Test
    public void validConstructorTest(){
        new ShelfId();
        assertNotNull(shelfId);
        assertEquals(shelfId,shelfIdCopy);
    }

    @Test
    public void invalidConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ShelfId(-1L));
        String expectedMessage = ("Shelf ID must be a positive numeric");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void equalsTest(){
        assertEquals(shelfId, shelfIdCopy);
        assertEquals(shelfId, shelfId);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();

        assertNotEquals(shelfId, otherShelfId);
        assertNotEquals(shelfId, otherObject);
    }

    @Test
    public void toStringTest(){
        assertTrue("1".contains(shelfId.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(shelfId.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(1,otherShelfId.compareTo(shelfId));
        assertEquals(-1,shelfId.compareTo(otherShelfId));
        assertEquals(0,shelfId.compareTo(shelfIdCopy));
    }

}