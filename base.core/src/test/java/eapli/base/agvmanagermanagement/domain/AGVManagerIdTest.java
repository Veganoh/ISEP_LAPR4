//package eapli.base.agvmanagermanagement.domain;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class AGVManagerIdTest {
//    long validId = 1234;
//    long invalidId = -7890;
//    long validIdDiff = 5678;
//    AGVManagerId id = AGVManagerId.valueOf(validId);
//    AGVManagerId idCopy = AGVManagerId.valueOf(validId);
//    AGVManagerId idDiff = AGVManagerId.valueOf(validIdDiff);
//
//    @Test
//    void valueOfValidData() {
//        assertNotNull(AGVManagerId.valueOf(validId));
//    }
//
//    @Test
//    void valueOfInvalidData() {
//        assertThrows(IllegalArgumentException.class, () -> AGVManagerId.valueOf(invalidId));
//    }
//
//    @Test
//    void testEqualsTrue() {
//        assertEquals(id, idCopy);
//        assertEquals(idCopy, id);
//        assertEquals(id, id);
//    }
//
//    @Test
//    void testEqualsFalse() {
//        String randomObj = "Random Object";
//
//        assertNotEquals(id, idDiff);
//        assertNotEquals(idDiff, id);
//        assertNotEquals(id, randomObj);
//    }
//
//    @Test
//    void testToString() {
//        String expected = "1234";
//
//        assertEquals(expected, id.toString());
//
//        expected = "5678";
//        assertEquals(expected, idDiff.toString());
//    }
//
//    @Test
//    void testHashCode() {
//        assertEquals(id.hashCode(), idCopy.hashCode());
//        assertEquals(idCopy.hashCode(), id.hashCode());
//        assertNotEquals(id.hashCode(), idDiff.hashCode());
//    }
//
//    @Test
//    void compareTo() {
//        assertEquals(0, id.compareTo(idCopy));
//        assertEquals(0, idCopy.compareTo(id));
//        assertTrue(0 < idDiff.compareTo(id));
//        assertTrue(0 > id.compareTo(idDiff));
//    }
//
//    @Test
//    void emptyConstructor() {
//        AGVManagerId empty = new AGVManagerId();
//        assertNotNull(empty);
//    }
//}