package eapli.base.warehousemanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AisleIdTest {

    AisleId aisleId = new AisleId("1");
    AisleId otherAisleId = new AisleId("2");
    AisleId aisleIdCopy =  AisleId.valueOf("1");


    @Test
    public void validConstructorTest(){
        new AisleId();
        assertNotNull(aisleId);
        assertEquals(aisleId,aisleIdCopy);
    }

    @Test
    public void emptyConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AisleId(null));
        String expectedMessage = ("ID cannot be empty");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void invalidConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AisleId("abcdef"));
        String expectedMessage = ("Aisle ID must be a positive numeric");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void equalsTest(){
        assertEquals(aisleId, aisleIdCopy);
        assertEquals(aisleId, aisleId);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();

        assertNotEquals(aisleId, otherAisleId);
        assertNotEquals(aisleId, otherObject);
    }

    @Test
    public void toStringTest(){
        assertTrue("1".contains(aisleId.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(aisleId.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(1,otherAisleId.compareTo(aisleId));
        assertEquals(-1,aisleId.compareTo(otherAisleId));
        assertEquals(0,aisleId.compareTo(aisleIdCopy));
    }



}