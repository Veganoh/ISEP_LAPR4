package eapli.base.agvmanagement.domain;

import eapli.base.util.domain.RandomString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AGVShortDescriptionTest {
    String validDesc = "12AB2";
    String validDescDiff = "56CD7";
    String invalidDesc = RandomString.generateRandomString(31);
    String validMaxDesc = RandomString.generateRandomString(30);
    AGVShortDescription agvShortDesc = AGVShortDescription.valueOf(validDesc);
    AGVShortDescription agvShortDescCopy = AGVShortDescription.valueOf(validDesc);
    AGVShortDescription agvShortDescDiff = AGVShortDescription.valueOf(validDescDiff);
    AGVShortDescription agvShortDescMax = AGVShortDescription.valueOf(validMaxDesc);

    @Test
    void valueOfValidData() {
        assertNotNull(AGVShortDescription.valueOf(validDesc));
        assertNotNull(AGVShortDescription.valueOf(validMaxDesc));
    }

    @Test
    void valueOfInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> AGVShortDescription.valueOf(" "));
        assertThrows(IllegalArgumentException.class, () -> AGVShortDescription.valueOf(null));
        assertThrows(IllegalArgumentException.class, () -> AGVShortDescription.valueOf(invalidDesc));
    }

    @Test
    void length() {
        assertEquals(validDesc.length(), agvShortDesc.length());
        assertEquals(validDescDiff.length(), agvShortDescDiff.length());
        assertEquals(validMaxDesc.length(), agvShortDescMax.length());
    }

    @Test
    void testEqualsTrue() {
        assertEquals(agvShortDesc, agvShortDescCopy);
        assertEquals(agvShortDescCopy, agvShortDesc);
        assertEquals(agvShortDesc, agvShortDesc);
    }

    @Test
    void testEqualsFalse() {
        String randomObj = "Random Object";

        assertNotEquals(agvShortDesc, agvShortDescDiff);
        assertNotEquals(agvShortDescDiff, agvShortDesc);
        assertNotEquals(agvShortDesc, randomObj);
    }

    @Test
    void testToString() {
        String expected = validDesc;

        assertEquals(expected, agvShortDesc.toString());

        expected = validDescDiff;
        assertEquals(expected, agvShortDescDiff.toString());
    }

    @Test
    void testHashCode() {
        assertEquals(agvShortDesc.hashCode(), agvShortDescCopy.hashCode());
        assertEquals(agvShortDescCopy.hashCode(), agvShortDesc.hashCode());
        assertNotEquals(agvShortDesc.hashCode(), agvShortDescDiff.hashCode());
    }

    @Test
    void compareTo() {
        assertEquals(0, agvShortDesc.compareTo(agvShortDescCopy));
        assertEquals(0, agvShortDescCopy.compareTo(agvShortDesc));
        assertTrue(0 < agvShortDescDiff.compareTo(agvShortDesc));
        assertTrue(0 > agvShortDesc.compareTo(agvShortDescDiff));
    }

    @Test
    void emptyConstructor() {
        AGVShortDescription empty = new AGVShortDescription();
        assertNotNull(empty);
    }
}