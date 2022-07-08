package eapli.base.surveymanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TargetConditionTypeTest {
    @Test
    void ageGroupValuesMatch() {
        assertEquals(TargetConditionType.AgeGroup, TargetConditionType.valueOf("AgeGroup"));
    }

    @Test
    void categoryValuesMatch() {
        assertEquals(TargetConditionType.Category, TargetConditionType.valueOf("Category"));
    }

    @Test
    void productValuesMatch() {
        assertEquals(TargetConditionType.Product, TargetConditionType.valueOf("Product"));
    }

    @Test
    void ageGroupValueOfType() {
        assertEquals(TargetConditionType.AgeGroup, TargetConditionType.valueOfType("Age Group"));
    }

    @Test
    void categoryValueOfType() {
        assertEquals(TargetConditionType.Category, TargetConditionType.valueOfType("Category"));
    }

    @Test
    void productValueOfType() {
        assertEquals(TargetConditionType.Product, TargetConditionType.valueOfType("Product"));
    }

    @Test
    void invalidDataValueOfType() {
        assertThrows(IllegalArgumentException.class, () -> TargetConditionType.valueOfType("Produc"));
    }
}