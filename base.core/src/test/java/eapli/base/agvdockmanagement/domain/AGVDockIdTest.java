package eapli.base.agvdockmanagement.domain;

import eapli.base.util.domain.RandomString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AGVDockIdTest {
    String validId = "12AB";
    String invalidId = RandomString.generateRandomString(9);
    String validIdDiff = "34CD";
    AGVDockId id = AGVDockId.valueOf(validId);
    AGVDockId idCopy = AGVDockId.valueOf(validId);
    AGVDockId idDiff = AGVDockId.valueOf(validIdDiff);

    @Test
    void valueOfValidData() {
        assertNotNull(AGVDockId.valueOf(validId));
    }

    @Test
    void valueOfInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> AGVDockId.valueOf(invalidId));
        assertThrows(IllegalArgumentException.class, () -> AGVDockId.valueOf(""));
        assertThrows(IllegalArgumentException.class, () -> AGVDockId.valueOf("12AC-V"));
        assertThrows(IllegalArgumentException.class, () -> AGVDockId.valueOf(null));
    }

    @Test
    void testEqualsTrue() {
        assertEquals(id, idCopy);
        assertEquals(idCopy, id);
        assertEquals(id, id);
    }

    @Test
    void testEqualsFalse() {
        String randomObj = "Random Object";

        assertNotEquals(id, idDiff);
        assertNotEquals(idDiff, id);
        assertNotEquals(id, randomObj);
    }

    @Test
    void testToString() {
        String expected = validId;

        assertEquals(expected, id.toString());

        expected = validIdDiff;
        assertEquals(expected, idDiff.toString());
    }

    @Test
    void testHashCode() {
        assertEquals(id.hashCode(), idCopy.hashCode());
        assertEquals(idCopy.hashCode(), id.hashCode());
        assertNotEquals(id.hashCode(), idDiff.hashCode());
    }

    @Test
    void compareTo() {
        assertEquals(0, id.compareTo(idCopy));
        assertEquals(0, idCopy.compareTo(id));
        assertTrue(0 < idDiff.compareTo(id));
        assertTrue(0 > id.compareTo(idDiff));
    }

    @Test
    void emptyConstructor() {
        AGVDockId empty = new AGVDockId();
        assertNotNull(empty);
    }
}