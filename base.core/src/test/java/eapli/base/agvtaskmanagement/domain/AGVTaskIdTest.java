package eapli.base.agvtaskmanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AGVTaskIdTest {
    String validId = "1234";
    String invalidId = "AB21C";
    String validIdDiff = "5678";
    AGVTaskId id = AGVTaskId.valueOf(validId);
    AGVTaskId idCopy = AGVTaskId.valueOf(validId);
    AGVTaskId idDiff = AGVTaskId.valueOf(validIdDiff);

    @Test
    void valueOfValidData() {
        assertNotNull(AGVTaskId.valueOf(validId));
    }

    @Test
    void valueOfInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> AGVTaskId.valueOf(invalidId));
        assertThrows(IllegalArgumentException.class, () -> AGVTaskId.valueOf(null));
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
        String expected = "1234";

        assertEquals(expected, id.toString());

        expected = "5678";
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
        AGVTaskId empty = new AGVTaskId();
        assertNotNull(empty);
    }
}