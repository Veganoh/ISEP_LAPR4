package eapli.base.warehousemanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShelfTest {

    AisleId aisleId = new AisleId("1");
    RowId rowId = new RowId("2");
    ShelfNumber shelfNumber = new ShelfNumber("3");
    ShelfNumber otherShelfNumber = new ShelfNumber("4");

    Shelf shelf = new Shelf(aisleId,rowId,shelfNumber);
    Shelf otherShelf = new Shelf(aisleId,rowId,otherShelfNumber);

    @Test
    public void validConstructorTest(){
        new Shelf();
        assertNotNull(shelf);
    }

    @Test
    public void emptyConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Shelf((AisleId) null,null,null));
        String expectedMessage = ("At least one of the required method parameters is null");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void equalsTest(){
        assertEquals(shelf,shelf);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();
        assertNotEquals(shelf, otherObject);
    }

    @Test
    public void toStringTest(){ assertTrue("AISLE ID: 1 | ROW ID: 2 | SHELF NUMBER: 3\n".contains(shelf.toString()));}
}