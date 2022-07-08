package eapli.base.surveymanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TargetConditionTest {

    TargetCondition targetCondition = TargetCondition.valueOf("Product", "PROD.00001");
    TargetCondition targetConditionCpy = TargetCondition.valueOf("Product", "PROD.00001");
    TargetCondition targetConditionDiff = TargetCondition.valueOf("Product", "PROD.00020");

    @Test
    void testValueOfEmptyType() {
        assertThrows(IllegalArgumentException.class, () -> TargetCondition.valueOf("", "PROD.0001"));
    }

    @Test
    void testValueOfEmptyTarget() {
        assertThrows(IllegalArgumentException.class, () -> TargetCondition.valueOf("Product", ""));
    }

    @Test
    void testValueOfProductTargetAudience() {
        TargetCondition targetConditionTmp = TargetCondition.valueOf("Product", "PROD.00001");
        assertNotNull(targetConditionTmp);
    }

    @Test
    void testValueOfCategoryTargetAudience() {
        TargetCondition targetConditionTmp = TargetCondition.valueOf("Category", "Beauty");
        assertNotNull(targetConditionTmp);
    }

    @Test
    void testValueOfAgeGroupTargetAudience() {
        TargetCondition targetConditionTmp = TargetCondition.valueOf("Age Group", "18-25");
        assertNotNull(targetConditionTmp);
    }

    @Test
    void compareToEquals() {
        assertEquals(0, targetCondition.compareTo(targetConditionCpy));
        assertEquals(0, targetConditionCpy.compareTo(targetCondition));
    }

    @Test
    void compareToBigger() {
        assertTrue(targetCondition.compareTo(targetConditionDiff) < 0);
    }

    @Test
    void compareToSmaller() {
        assertTrue(targetConditionDiff.compareTo(targetCondition) > 0);
    }

    @Test
    void testEqualsTrue() {
        assertEquals(targetCondition, targetConditionCpy);
    }

    @Test
    void testEqualsFalse() {
        assertNotEquals(targetCondition, targetConditionDiff);
    }

    @Test
    void testEqualsSameInstance() {
        assertEquals(targetCondition, targetCondition);
    }

    @Test
    void testEqualsDifferentObject() {
        String tmp = "Bla";
        assertNotEquals(targetCondition, tmp);
    }

    @Test
    void testToString() {
        assertEquals("Product : PROD.00001", targetCondition.toString());
    }

    @Test
    void testHashCodeSameObject() {
        assertEquals(targetCondition.hashCode(), targetConditionCpy.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        assertNotEquals(targetCondition.hashCode(), targetConditionDiff.hashCode());
    }

    @Test
    void testEmptyConstructor() {
        TargetCondition targetConditionTmp = new TargetCondition();
        assertNotNull(targetConditionTmp);
    }
}