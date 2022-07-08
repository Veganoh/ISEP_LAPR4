package eapli.base.agvmanagermanagement.domain;

import eapli.base.warehousemanagement.domain.AisleId;
import eapli.base.warehousemanagement.domain.RowId;
import org.junit.jupiter.api.Test;

import javax.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {
    Coordinates coordinates = Coordinates.valueOf(10, 15);
    Coordinates coordinatesDiff = Coordinates.valueOf(15, 10);
    RowId rowId = RowId.valueOf("123");
    RowId rowIdDiff = RowId.valueOf("456");
    AisleId aisleId = AisleId.valueOf("321");
    AisleId aisleIdDiff = AisleId.valueOf("789");
    Accessibility accessibility = Accessibility.Up;
    Accessibility accessibilityDiff = Accessibility.Down;
    Square fullSquare = Square.valueOf(coordinates, rowId, aisleId, accessibility);
    Square fullSquareCopy = Square.valueOf(coordinates, rowId, aisleId, accessibility);
    Square fullSquareDiff = Square.valueOf(coordinatesDiff, rowIdDiff, aisleIdDiff, accessibilityDiff);
    Square nullSquare = Square.valueOf(coordinates, null, null, null);

    @Test
    void valueOfValidSquare() {
        assertNotNull(Square.valueOf(coordinates, rowId, aisleId, accessibility));
        assertNotNull(Square.valueOf(coordinates, rowId, aisleId));
        assertNotNull(Square.valueOf(coordinates, accessibility));
        assertNotNull(Square.valueOf(coordinates, null, null, null));
    }

    @Test
    void valueOfInvalidSquare() {
        assertThrows(IllegalArgumentException.class, ()-> Square.valueOf(coordinates, rowId, null, accessibility));
        assertThrows(IllegalArgumentException.class, ()-> Square.valueOf(coordinates, null, aisleId, accessibility));
        assertThrows(IllegalArgumentException.class, ()-> Square.valueOf(null, rowId, aisleId, accessibility));
        assertThrows(IllegalArgumentException.class, ()-> Square.valueOf(null, null, null, accessibility));
    }

    @Test
    void accessibilityDirectionExists() {
        assertEquals(accessibility, fullSquare.accessibilityDirection());
    }

    @Test
    void accessibilityDirectionDoesntExist() {
        assertThrows(NoResultException.class, ()->nullSquare.accessibilityDirection());
    }

    @Test
    void hasAccessibilityDirection() {
        assertTrue(fullSquare.hasAccessibilityDirection());
        assertFalse(nullSquare.hasAccessibilityDirection());
    }

    @Test
    void containedAisleExists() {
        assertEquals(aisleId, fullSquare.containedAisle());
    }

    @Test
    void containedAisleDoesntExist() {
        assertThrows(NoResultException.class, ()->nullSquare.containedAisle());
    }

    @Test
    void hasAisle() {
        assertTrue(fullSquare.hasAisle());
        assertFalse(nullSquare.hasAisle());
    }

    @Test
    void containedRowExists() {
        assertEquals(rowId, fullSquare.containedRow());
    }

    @Test
    void containedRowDoesntExist() {
        assertThrows(NoResultException.class, ()->nullSquare.containedRow());
    }

    @Test
    void hasRow() {
        assertTrue(fullSquare.hasRow());
        assertFalse(nullSquare.hasRow());
    }

    @Test
    void squareCoordinates() {
        assertEquals(coordinates, fullSquare.squareCoordinates());
    }

    @Test
    void testEqualsTrue() {
        assertEquals(fullSquareCopy, fullSquare);
        assertEquals(fullSquare, fullSquareCopy);
        assertEquals(fullSquare, fullSquare);
    }

    @Test
    void testEqualsFalse() {
        String randomObject = "random object";

        assertNotEquals(fullSquareCopy, nullSquare);
        assertNotEquals(fullSquareCopy, randomObject);
    }

    @Test
    void testToString() {
        String expected = String.format("Coordinates: %s, RowId: %s, AisleId: %s, Accessibility: %s", coordinates, rowId, aisleId, accessibility);

        assertEquals(expected, fullSquare.toString());
    }

    @Test
    void testHashCode() {
        assertEquals(fullSquare.hashCode(), fullSquareCopy.hashCode());
        assertEquals(fullSquareCopy.hashCode(), fullSquare.hashCode());
    }

    @Test
    void compareTo() {
        assertEquals(0, fullSquare.compareTo(fullSquareCopy));
        assertEquals(-1, fullSquare.compareTo(fullSquareDiff));
        assertEquals(1, fullSquareDiff.compareTo(fullSquare));
    }

    @Test
    void emptyConstructor() {
        Square empty = new Square();

        assertNotNull(empty);
    }
}