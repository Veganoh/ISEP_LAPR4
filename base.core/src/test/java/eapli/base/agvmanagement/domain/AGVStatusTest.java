package eapli.base.agvmanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AGVStatusTest {

    @Test
    void chargingValuesMatch() {
        assertEquals(AGVStatus.Charging, AGVStatus.valueOf("Charging"));
    }

    @Test
    void servingGivenOrderValuesMatch() {
        assertEquals(AGVStatus.ServingGivenOrder, AGVStatus.valueOf("ServingGivenOrder"));
    }

    @Test
    void freeValuesMatch() {
        assertEquals(AGVStatus.Free, AGVStatus.valueOf("Free"));
    }

    @Test
    void inMaintenanceValuesMatch() {
        assertEquals(AGVStatus.InMaintenance, AGVStatus.valueOf("InMaintenance"));
    }

    @Test
    void valueOfChargingValidData() {
        assertEquals(AGVStatus.Charging, AGVStatus.valueOfStatus("Charging"));
    }

    @Test
    void valueOfServingGivenOrderValidData() {
        assertEquals(AGVStatus.ServingGivenOrder, AGVStatus.valueOfStatus("Serving Given Order"));
    }

    @Test
    void valueOfFreeValidData() {
        assertEquals(AGVStatus.Free, AGVStatus.valueOfStatus("Free"));
    }

    @Test
    void valueOfInMaintenanceValidData() {
        assertEquals(AGVStatus.InMaintenance, AGVStatus.valueOfStatus("In Maintenance"));
    }

    @Test
    void valueOfStatusInValidData() {
        assertNotEquals(AGVStatus.Charging, AGVStatus.valueOfStatus("Charge"));
    }

    @Test
    void valueOfServingGivenOrderInValidData() {
        assertNotEquals(AGVStatus.ServingGivenOrder, AGVStatus.valueOfStatus("ServingGivenOrder"));
    }

    @Test
    void valueOfFreeInValidData() {
        assertNotEquals(AGVStatus.Free, AGVStatus.valueOfStatus("Fre"));
    }

    @Test
    void valueOfInMaintenanceInValidData() {
        assertNotEquals(AGVStatus.InMaintenance, AGVStatus.valueOfStatus("InMaintenance"));
    }
}