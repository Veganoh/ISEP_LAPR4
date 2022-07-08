package eapli.base.agvmanagement.domain;

import eapli.base.util.domain.RandomString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AGVIdTest {
    String validId = "12AB3CDE";
    String validIdDiff = "56FG7HIJ";
    String invalidIdLength = RandomString.generateRandomString(9);
    String invalidIdChar = "AC2$123";

    AGVId id = AGVId.valueOf(validId);
    AGVId idCopy = AGVId.valueOf(validId);
    AGVId idDiff = AGVId.valueOf(validIdDiff);

    @Test
    void valueOfValidData() {
        assertNotNull(AGVId.valueOf(validId));
    }

    @Test
    void valueOfInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> AGVId.valueOf(invalidIdLength));
        assertThrows(IllegalArgumentException.class, () -> AGVId.valueOf(invalidIdChar));
        assertThrows(IllegalArgumentException.class, () -> AGVId.valueOf(null));
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
        AGVId empty = new AGVId();
        assertNotNull(empty);
    }
}