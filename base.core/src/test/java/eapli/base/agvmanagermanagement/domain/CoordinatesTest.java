package eapli.base.agvmanagermanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {
    int validWSquare = 10;
    int invalidWSquare = -10;
    int validLSquare = 20;
    int invalidLSquare = -20;
    Coordinates coordinates = Coordinates.valueOf(validWSquare, validLSquare);
    Coordinates coordinatesCopy = Coordinates.valueOf(validWSquare, validLSquare);
    Coordinates coordinatesDiffL = Coordinates.valueOf(validWSquare, validWSquare);
    Coordinates coordinatesDiffW = Coordinates.valueOf(validLSquare, validLSquare);

    @Test
    void valueOfValidCoordinate() {
        assertNotNull(Coordinates.valueOf(validWSquare, validLSquare));
    }

    @Test
    void valueOfInvalidLSquareValue() {
        assertThrows(IllegalArgumentException.class, ()-> Coordinates.valueOf(validWSquare, invalidLSquare));
        assertThrows(IllegalArgumentException.class, ()-> Coordinates.valueOf(validWSquare, 0));
    }

    @Test
    void valueOfInvalidWSquareValue() {
        assertThrows(IllegalArgumentException.class, ()-> Coordinates.valueOf(invalidWSquare, validLSquare));
        assertThrows(IllegalArgumentException.class, ()-> Coordinates.valueOf(0, validLSquare));
    }

    @Test
    void testEqualsTrue() {
        assertEquals(coordinates, coordinatesCopy);
        assertEquals(coordinatesCopy, coordinates);
        assertEquals(coordinates, coordinates);
    }

    @Test
    void testEqualsFalse() {
        String randomObj = "random object";

        assertNotEquals(coordinates, coordinatesDiffW);
        assertNotEquals(coordinates, coordinatesDiffL);
        assertNotEquals(coordinates, randomObj);
    }

    @Test
    void testToString() {
        String expected = String.format("wSquare: %d | lSquare: %d", validWSquare, validLSquare);
        assertEquals(expected, coordinates.toString());
    }

    @Test
    void testHashCode() {
        assertEquals(coordinates.hashCode(), coordinatesCopy.hashCode());
        assertEquals(coordinatesCopy.hashCode(), coordinates.hashCode());
    }

    @Test
    void compareTo() {
        assertEquals(0, coordinates.compareTo(coordinatesCopy));
        assertEquals(-1, coordinates.compareTo(coordinatesDiffW));
        assertEquals(1, coordinatesDiffW.compareTo(coordinates));
        assertEquals(1, coordinates.compareTo(coordinatesDiffL));
        assertEquals(-1, coordinatesDiffL.compareTo(coordinates));
    }

    @Test
    void emptyConstructor() {
        Coordinates empty = new Coordinates();
        assertNotNull(empty);
    }
}