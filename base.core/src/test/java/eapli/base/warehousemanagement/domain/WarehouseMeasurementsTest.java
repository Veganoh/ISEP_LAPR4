package eapli.base.warehousemanagement.domain;

import eapli.base.common.domain.Length;
import eapli.base.common.domain.VolumeUnit;
import eapli.base.common.domain.Width;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseMeasurementsTest {

    Length length = new Length(5L);
    Width width = new Width(3L);
    SquareSize squareSize = new SquareSize(2L);
    SquareSize otherSquareSize = new SquareSize(6L);
    VolumeUnit unit = VolumeUnit.MM;
    WarehouseMeasurements warehouseMeasurements = new WarehouseMeasurements(squareSize,length,width,unit);
    WarehouseMeasurements otherWarehouseMeasurements = new WarehouseMeasurements(otherSquareSize,length,width,unit);
    WarehouseMeasurements warehouseMeasurementsCopy = WarehouseMeasurements.valueOf(squareSize,length,width,unit);

    @Test
    public void validConstructorTest(){
        assertNotNull(warehouseMeasurements);
        assertNotNull(warehouseMeasurementsCopy);
        assertEquals(warehouseMeasurements,warehouseMeasurementsCopy);
        WarehouseMeasurements empty = new WarehouseMeasurements();
    }

    @Test
    public void invalidConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> WarehouseMeasurements.valueOf(null, null, null, null));
        String expectedMessage = ("At least one of the required method parameters is null");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void equalsTest(){
        assertEquals(warehouseMeasurements, warehouseMeasurementsCopy);
        assertEquals(warehouseMeasurements, warehouseMeasurements);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();
        assertNotEquals(warehouseMeasurements, otherWarehouseMeasurements);
        assertNotEquals(warehouseMeasurements, otherObject);
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(warehouseMeasurements.hashCode());
    }

    @Test
    public void toStringTest(){
        assertTrue("SQUARE SIZE: 2 |  LENGTH: 5  |  WIDTH: 3  |  UNIT: MM".contains(warehouseMeasurements.toString()));
    }

    @Test
    public void compareToTest(){
        assertEquals(1,otherWarehouseMeasurements.compareTo(warehouseMeasurements));
        assertEquals(-1,warehouseMeasurements.compareTo(otherWarehouseMeasurements));
        assertEquals(0,warehouseMeasurements.compareTo(warehouseMeasurementsCopy));
    }
}
