package eapli.base.agvdockmanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AGVDockAvailabilityTest {

    AGVDockAvailability agvDockAvailability1 = new AGVDockAvailability(true);
    AGVDockAvailability agvDockAvailability2 = new AGVDockAvailability(false);
    AGVDockAvailability agvDockAvailabilityCopy =  AGVDockAvailability.valueOf(true);

    @Test
    public void validDataTest(){
        new AGVDockAvailability();
        assertNotNull(agvDockAvailability1);
        assertEquals(agvDockAvailability1, agvDockAvailabilityCopy);
        assertTrue(agvDockAvailability1.isAvailable());
        assertFalse(agvDockAvailability2.isAvailable());
    }

    @Test
    public void equalsTest(){
        assertEquals(agvDockAvailability1, agvDockAvailabilityCopy);
        assertEquals(agvDockAvailability1, agvDockAvailability1);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();
        assertNotEquals(agvDockAvailability1, agvDockAvailability2);
        assertNotEquals(agvDockAvailability1, otherObject);
    }

    @Test
    public void toStringTest(){
        assertTrue("AGV Dock is available.".contains(agvDockAvailability1.toString()));
        assertTrue("AGV Dock is not available.".contains(agvDockAvailability2.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(agvDockAvailability1.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(-1, agvDockAvailability2.compareTo(agvDockAvailability1));
        assertEquals(1, agvDockAvailability1.compareTo(agvDockAvailability2));
        assertEquals(0, agvDockAvailability1.compareTo(agvDockAvailabilityCopy));
    }
}