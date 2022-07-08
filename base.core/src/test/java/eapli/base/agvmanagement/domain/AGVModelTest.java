package eapli.base.agvmanagement.domain;

import eapli.base.util.domain.RandomString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AGVModelTest {
    String validModel = "12AB2";
    String validModelDiff = "56CD7";
    String invalidModel = RandomString.generateRandomString(51);
    String validMaxModel = RandomString.generateRandomString(50);


    AGVModel agvModel = AGVModel.valueOf(validModel);
    AGVModel agvModelCopy = AGVModel.valueOf(validModel);
    AGVModel agvModelDiff = AGVModel.valueOf(validModelDiff);

    @Test
    void valueOfValidData() {
        assertNotNull(AGVModel.valueOf(validModel));
        assertNotNull(AGVModel.valueOf(validMaxModel));
    }

    @Test
    void valueOfInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> AGVModel.valueOf(" "));
        assertThrows(IllegalArgumentException.class, () -> AGVModel.valueOf(null));
        assertThrows(IllegalArgumentException.class, () -> AGVModel.valueOf(invalidModel));
    }

    @Test
    void testEqualsTrue() {
        assertEquals(agvModel, agvModelCopy);
        assertEquals(agvModelCopy, agvModel);
        assertEquals(agvModel, agvModel);
    }

    @Test
    void testEqualsFalse() {
        String randomObj = "Random Object";

        assertNotEquals(agvModel, agvModelDiff);
        assertNotEquals(agvModelDiff, agvModel);
        assertNotEquals(agvModel, randomObj);
    }

    @Test
    void testToString() {
        String expected = validModel;

        assertEquals(expected, agvModel.toString());

        expected = validModelDiff;
        assertEquals(expected, agvModelDiff.toString());
    }

    @Test
    void testHashCode() {
        assertEquals(agvModel.hashCode(), agvModelCopy.hashCode());
        assertEquals(agvModelCopy.hashCode(), agvModel.hashCode());
        assertNotEquals(agvModel.hashCode(), agvModelDiff.hashCode());
    }

    @Test
    void compareTo() {
        assertEquals(0, agvModel.compareTo(agvModelCopy));
        assertEquals(0, agvModelCopy.compareTo(agvModel));
        assertTrue(0 < agvModelDiff.compareTo(agvModel));
        assertTrue(0 > agvModel.compareTo(agvModelDiff));
    }

    @Test
    void emptyConstructor() {
        AGVModel empty = new AGVModel();
        assertNotNull(empty);
    }
}