package eapli.base.agvmanagermanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AccessibilityTest {

    @Test
    void valuesMatch() {
        assertEquals(Accessibility.Down, Accessibility.valueOf("Down"));
        assertEquals(Accessibility.Up, Accessibility.valueOf("Up"));
        assertEquals(Accessibility.Left, Accessibility.valueOf("Left"));
        assertEquals(Accessibility.Right, Accessibility.valueOf("Right"));
    }

    @Test
    void valueOfLabelValidLabels() {
        assertEquals(Accessibility.Down, Accessibility.valueOfLabel("w+"));
        assertEquals(Accessibility.Up, Accessibility.valueOfLabel("w-"));
        assertEquals(Accessibility.Left, Accessibility.valueOfLabel("l-"));
        assertEquals(Accessibility.Right, Accessibility.valueOfLabel("l+"));
    }

    @Test
    void valueOfLabelInvalidLabels() {
        assertNull(Accessibility.valueOfLabel("+w"));
        assertNull(Accessibility.valueOfLabel("-w"));
        assertNull(Accessibility.valueOfLabel("-l"));
        assertNull(Accessibility.valueOfLabel("+l"));
    }
}