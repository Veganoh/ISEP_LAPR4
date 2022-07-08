package eapli.base.warehousemanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareSizeTest {

    SquareSize squareSize = new SquareSize(1L);
    SquareSize otherSquareSize = new SquareSize(2L);
    SquareSize squareSizeCopy =  SquareSize.valueOf(1L);


    @Test
    public void validConstructorTest(){
        new SquareSize();
        assertNotNull(squareSize);
        assertEquals(squareSize,squareSizeCopy);
    }


    @Test
    public void invalidConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SquareSize(-1L));
        String expectedMessage = ("Invalid Square Size, must not be negative");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void equalsTest(){
        assertEquals(squareSize, squareSizeCopy);
        assertEquals(squareSize, squareSize);
    }

    @Test
    public void valueTest(){
        assertEquals(1,squareSize.value());
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();

        assertNotEquals(squareSize, otherSquareSize);
        assertNotEquals(squareSize, otherObject);
    }

    @Test
    public void toStringTest(){
        assertTrue("1".contains(squareSize.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(squareSize.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(1,otherSquareSize.compareTo(squareSize));
        assertEquals(-1,squareSize.compareTo(otherSquareSize));
        assertEquals(0,squareSize.compareTo(squareSizeCopy));
    }


}