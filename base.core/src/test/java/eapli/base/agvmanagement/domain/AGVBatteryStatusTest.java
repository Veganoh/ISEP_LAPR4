package eapli.base.agvmanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AGVBatteryStatusTest {
    int validAutonomy = 120;
    int validAutonomyDiff = 90;

    AGVBatteryStatus batteryStatus = AGVBatteryStatus.valueOf(validAutonomy);
    AGVBatteryStatus batteryStatusCopy = AGVBatteryStatus.valueOf(validAutonomy);
    AGVBatteryStatus batteryStatusDiff = AGVBatteryStatus.valueOf(validAutonomyDiff);

    @Test
    void valueOfValidData() {
        assertNotNull(AGVBatteryStatus.valueOf(validAutonomy));
    }

    @Test
    void valueOfInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> AGVBatteryStatus.valueOf(-1));
        assertThrows(IllegalArgumentException.class, () -> AGVBatteryStatus.valueOf(0));
    }

    @Test
    void testEqualsTrue() {
        assertEquals(batteryStatus, batteryStatusCopy);
        assertEquals(batteryStatusCopy, batteryStatus);
        assertEquals(batteryStatus, batteryStatus);
    }

    @Test
    void testEqualsFalse() {
        String randomObj = "Random Object";

        assertNotEquals(batteryStatus, batteryStatusDiff);
        assertNotEquals(batteryStatusDiff, batteryStatus);
        assertNotEquals(batteryStatus, randomObj);
    }

    @Test
    void testToString() {
        String expected = String.format("Battery autonomy: %d min. - Current Battery Life: %d min.", validAutonomy, 0);

        assertEquals(expected, batteryStatus.toString());

        expected = String.format("Battery autonomy: %d min. - Current Battery Life: %d min.", validAutonomyDiff, 0);
        assertEquals(expected, batteryStatusDiff.toString());
    }

    @Test
    void testHashCode() {
        assertEquals(batteryStatus.hashCode(), batteryStatusCopy.hashCode());
        assertEquals(batteryStatusCopy.hashCode(), batteryStatus.hashCode());
        assertNotEquals(batteryStatus.hashCode(), batteryStatusDiff.hashCode());
    }

    @Test
    void compareTo() {
        assertEquals(0, batteryStatus.compareTo(batteryStatusCopy));
        assertEquals(0, batteryStatusCopy.compareTo(batteryStatus));
        assertTrue(0 > batteryStatusDiff.compareTo(batteryStatus));
        assertTrue(0 < batteryStatus.compareTo(batteryStatusDiff));
    }

    @Test
    void emptyConstructor() {
        AGVBatteryStatus empty = new AGVBatteryStatus();
        assertNotNull(empty);
    }
}