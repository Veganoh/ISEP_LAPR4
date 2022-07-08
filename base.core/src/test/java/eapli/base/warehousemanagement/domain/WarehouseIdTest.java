package eapli.base.warehousemanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseIdTest {

    WarehouseId warehouseId = new WarehouseId(1L);
    WarehouseId otherWarehouseId = new WarehouseId(2L);
    WarehouseId warehouseIdCopy =  WarehouseId.valueOf(1L);

    @Test
    public void validConstructorTest(){
       new WarehouseId();
        assertNotNull(warehouseId);
        assertEquals(warehouseId,warehouseIdCopy);
    }

    @Test
    public void invalidConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new WarehouseId(-1L));
        String expectedMessage = ("Warehouse ID must be a positive numeric");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void equalsTest(){
        assertEquals(warehouseId, warehouseIdCopy);
        assertEquals(warehouseId, warehouseId);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();

        assertNotEquals(warehouseId, otherWarehouseId);
        assertNotEquals(warehouseId, otherObject);
    }

    @Test
    public void toStringTest(){
        assertTrue("1".contains(warehouseId.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(warehouseId.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(1,otherWarehouseId.compareTo(warehouseId));
        assertEquals(-1,warehouseId.compareTo(otherWarehouseId));
        assertEquals(0,warehouseId.compareTo(warehouseIdCopy));
    }


}