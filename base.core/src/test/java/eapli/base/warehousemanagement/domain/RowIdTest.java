package eapli.base.warehousemanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RowIdTest {

    RowId rowId = new RowId("1");
    RowId otherRowId = new RowId("2");
    RowId rowIdCopy =  RowId.valueOf("1");

    @Test
    public void validConstructorTest(){
        new RowId();
        assertNotNull(rowId);
        assertEquals(rowId,rowIdCopy);
    }

    @Test
    public void emptyConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new RowId(null));
        String expectedMessage = ("ID cannot be empty");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void invalidConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new RowId("abcdef"));
        String expectedMessage = ("Row ID must be a positive numeric");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void equalsTest(){
        assertEquals(rowId, rowIdCopy);
        assertEquals(rowId, rowId);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();

        assertNotEquals(rowId, otherRowId);
        assertNotEquals(rowId, otherObject);
    }

    @Test
    public void toStringTest(){
        assertTrue("1".contains(rowId.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(rowId.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(1,otherRowId.compareTo(rowId));
        assertEquals(-1,rowId.compareTo(otherRowId));
        assertEquals(0,rowId.compareTo(rowIdCopy));
    }

}