package eapli.base.agvmanagermanagement.domain;

import eapli.base.agvdockmanagement.domain.AGVDock;
import eapli.base.agvdockmanagement.domain.AGVDockId;
import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.AGVBuilder;
import eapli.base.util.domain.RandomString;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AGVManagerTest {
    AGVDockId dockId = AGVDockId.valueOf("D1");
    AGVDockId dockIdDiff = AGVDockId.valueOf("D2");

    Coordinates coordinates1 = Coordinates.valueOf(10, 15);
    Coordinates coordinates2 = Coordinates.valueOf(11, 15);
    Coordinates coordinates3 = Coordinates.valueOf(10, 16);

    Square accessSquare = Square.valueOf(coordinates1, Accessibility.Up);
    Square square1 = Square.valueOf(coordinates2);
    Square square2 = Square.valueOf(coordinates3);

    AGVDock dock = new AGVDock(dockId, accessSquare, square1, square2);
    AGVDock dockDiff = new AGVDock(dockIdDiff, accessSquare, square1, square2);

    AGVBuilder agvBuilder = new AGVBuilder();

    AGV agv = agvBuilder.withId("123")
            .ofModel("AB123")
            .ofDescription(RandomString.generateRandomString(20))
            .ofWeightCapacity(20000)
            .ofVolumeCapacity(2000)
            .ofBaseLocation(dock)
            .ofBatteryStatus(120)
            .build();

    AGV agvDiff = agvBuilder.withId("456")
            .ofModel("AB123")
            .ofDescription(RandomString.generateRandomString(20))
            .ofWeightCapacity(20000)
            .ofVolumeCapacity(2000)
            .ofBaseLocation(dock)
            .ofBatteryStatus(120)
            .build();

    AGVManager manager = AGVManager.valueOf();
    AGVManager managerDiff = AGVManager.valueOf();

    @Test
    void valueOf() {
        AGVManager manager = AGVManager.valueOf();
        assertNotNull(manager);
    }

    @Test
    void constructorInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> new AGVManager(null, null, null));
        assertThrows(IllegalArgumentException.class, () -> new AGVManager(null, new ArrayList<>(), new ArrayList<>()));
        assertThrows(IllegalArgumentException.class, () -> new AGVManager(new ArrayList<>(), new ArrayList<>(), null));
        assertThrows(IllegalArgumentException.class, () -> new AGVManager(new ArrayList<>(), new ArrayList<>(), null));
    }

    @Test
    void addSquare() {
        assertEquals(0, manager.numberOfSquares());
        assertTrue(manager.addSquare(square1));
        assertEquals(1, manager.numberOfSquares());
        assertTrue(manager.addSquare(square2));
        assertEquals(2, manager.numberOfSquares());

        manager.removeSquare(square1);
        manager.removeSquare(square2);
    }

    @Test
    void addSquareAlreadyExists() {
        assertTrue(managerDiff.addSquare(square1));
        assertThrows(IllegalArgumentException.class, () -> managerDiff.addSquare(square1));
    }

    @Test
    void numberOfSquares() {
        assertEquals(0, manager.numberOfSquares());

        manager.addSquare(square1);
        assertEquals(1, manager.numberOfSquares());

        manager.addSquare(square2);
        assertEquals(2, manager.numberOfSquares());

        manager.removeSquare(square1);
        assertEquals(1, manager.numberOfSquares());

        manager.removeSquare(square2);
        assertEquals(0, manager.numberOfSquares());
    }

    @Test
    void removeSquare() {
        manager.addSquare(square1);
        manager.addSquare(square2);

        assertTrue(manager.removeSquare(square1));
        assertTrue(manager.removeSquare(square2));
        assertEquals(0, manager.numberOfSquares());
        assertFalse(manager.removeSquare(square1));
    }

    @Test
    void addAGV() {
        assertEquals(0, manager.numberOfAGV());
        assertTrue(manager.addAGV(agv));
        assertEquals(1, manager.numberOfAGV());
        assertTrue(manager.addAGV(agvDiff));
        assertEquals(2, manager.numberOfAGV());

        manager.removeAGV(agv);
        manager.removeAGV(agvDiff);
    }

    @Test
    void addAGVAlreadyExists() {
        assertTrue(managerDiff.addAGV(agv));
        assertThrows(IllegalArgumentException.class, () -> managerDiff.addAGV(agv));
    }

    @Test
    void numberOfAGV() {
        assertEquals(0, manager.numberOfAGV());
        manager.addAGV(agv);
        assertEquals(1, manager.numberOfAGV());

        manager.addAGV(agvDiff);
        assertEquals(2, manager.numberOfAGV());

        manager.removeAGV(agv);
        assertEquals(1, manager.numberOfAGV());

        manager.removeAGV(agvDiff);
        assertEquals(0, manager.numberOfAGV());
    }

    @Test
    void removeAGV() {
        manager.addAGV(agv);
        manager.addAGV(agvDiff);
        assertEquals(2, manager.numberOfAGV());

        assertTrue(manager.removeAGV(agv));
        assertEquals(1, manager.numberOfAGV());

        assertTrue(manager.removeAGV(agvDiff));
        assertEquals(0, manager.numberOfAGV());

        assertFalse(manager.removeAGV(agv));
    }

    @Test
    void addAGVDock() {
        assertEquals(0, manager.numberOfAGVDock());
        assertTrue(manager.addAGVDock(dock));
        assertEquals(1, manager.numberOfAGVDock());
        assertTrue(manager.addAGVDock(dockDiff));
        assertEquals(2, manager.numberOfAGVDock());

        manager.removeAGVDock(dock);
        manager.removeAGVDock(dockDiff);
    }

    @Test
    void addAGVDockAlreadyExists() {
        assertTrue(managerDiff.addAGVDock(dock));
        assertThrows(IllegalArgumentException.class, () -> managerDiff.addAGVDock(dock));
    }

    @Test
    void numberOfAGVDock() {
        assertEquals(0, manager.numberOfAGVDock());
        manager.addAGVDock(dock);
        assertEquals(1, manager.numberOfAGVDock());

        manager.addAGVDock(dockDiff);
        assertEquals(2, manager.numberOfAGVDock());

        manager.removeAGVDock(dock);
        assertEquals(1, manager.numberOfAGVDock());

        manager.removeAGVDock(dockDiff);
        assertEquals(0, manager.numberOfAGVDock());
    }

    @Test
    void removeAGVDock() {
        manager.addAGVDock(dock);
        manager.addAGVDock(dockDiff);
        assertEquals(2, manager.numberOfAGVDock());

        assertTrue(manager.removeAGVDock(dock));
        assertEquals(1, manager.numberOfAGVDock());

        assertTrue(manager.removeAGVDock(dockDiff));
        assertEquals(0, manager.numberOfAGVDock());

        assertFalse(manager.removeAGVDock(dock));
    }

//    @Test
//    void sameAs() {
//        assertTrue(manager.sameAs(managerCopy));
//        assertTrue(managerCopy.sameAs(manager));
//        assertTrue(manager.sameAs(manager));
//        assertFalse(manager.sameAs(managerDiff));
//        assertFalse(managerDiff.sameAs(manager));
//    }

//    @Test
//    void identity() {
//        assertEquals(id, manager.identity());
//        assertEquals(id, managerCopy.identity());
//        assertEquals(idDiff, managerDiff.identity());
//    }

    @Test
    void emptyConstructor() {
        AGVManager empty = new AGVManager();
        assertNotNull(empty);
    }
}