package eapli.base.agvdockmanagement.domain;

import eapli.base.agvmanagermanagement.domain.Accessibility;
import eapli.base.agvmanagermanagement.domain.Coordinates;
import eapli.base.agvmanagermanagement.domain.Square;
import eapli.base.warehousemanagement.domain.AisleId;
import eapli.base.warehousemanagement.domain.RowId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AGVDockTest {
    AGVDockId id1 = AGVDockId.valueOf("1");
    AGVDockId id2 = AGVDockId.valueOf("2");
    Coordinates coordinates1 = Coordinates.valueOf(10, 15);
    Coordinates coordinates2 = Coordinates.valueOf(11, 15);
    Coordinates coordinates3 = Coordinates.valueOf(10, 16);

    Square accessSquare = Square.valueOf(coordinates1, null, null, Accessibility.Up);
    Square rowAisleSquare = Square.valueOf(coordinates1, RowId.valueOf("1"), AisleId.valueOf("1"), Accessibility.Up);
    Square square1 = Square.valueOf(coordinates2, null, null, null);
    Square square2 = Square.valueOf(coordinates3, null, null, null);

    AGVDock dock = new AGVDock(id1, accessSquare, square1, square2);
    AGVDock dockCopy = new AGVDock(id1, accessSquare, square1, square2);
    AGVDock dockDiff = new AGVDock(id2, accessSquare, square1, square2);

    @Test
    void constructorTestValidData() {
        AGVDock dock = new AGVDock(id1, accessSquare, square1, square2);
        assertNotNull(dock);

        dock = new AGVDock(accessSquare, square1, square2);
        assertNotNull(dock);
    }

    @Test
    void constructorTestInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> new AGVDock(id1, square1, square1, square1));
        assertThrows(IllegalArgumentException.class, () -> new AGVDock(id1, rowAisleSquare, accessSquare, square1));
        assertThrows(IllegalArgumentException.class, () -> new AGVDock(id1, accessSquare, rowAisleSquare, square1));
        assertThrows(IllegalArgumentException.class, () -> new AGVDock(id1, accessSquare, square1, rowAisleSquare));
        assertThrows(IllegalArgumentException.class, () -> new AGVDock(null, null, null, null));
        assertThrows(IllegalArgumentException.class, () -> new AGVDock(null, accessSquare, square1, square2));
        assertThrows(IllegalArgumentException.class, () -> new AGVDock(id1, null, square1, square2));
        assertThrows(IllegalArgumentException.class, () -> new AGVDock(id1, accessSquare, null, square2));
        assertThrows(IllegalArgumentException.class, () -> new AGVDock(id1, accessSquare, square1, null));
    }

    @Test
    void sameAs() {
        assertTrue(dock.sameAs(dockCopy));
        assertTrue(dockCopy.sameAs(dock));
        assertTrue(dock.sameAs(dock));
        assertFalse(dock.sameAs(dockDiff));
        assertFalse(dockDiff.sameAs(dock));
    }

    @Test
    void identity() {
        assertEquals(id1, dock.identity());
        assertEquals(id2, dockDiff.identity());
    }

    @Test
    void beginSquare() {
        assertEquals(accessSquare, dock.beginSquare());
    }

    @Test
    void emptyConstructor() {
        AGVDock empty = new AGVDock();
        assertNotNull(empty);
    }
}