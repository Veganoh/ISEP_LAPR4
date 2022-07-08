package eapli.base.customermanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VATNumberTest {
    VATNumber vatNumber1 = new VATNumber("1");
    VATNumber vatNumber2 = new VATNumber("2");
    VATNumber vatNumber3 = new VATNumber("1");

    @Test
    public void validConstructorTest(){
        new VATNumber();
        assertNotNull(vatNumber1);
        assertEquals(vatNumber1, vatNumber3);
    }

    @Test
    public void emptyConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new VATNumber(null));
        String expectedMessage = ("Customer must have a VAT number");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void invalidConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new VATNumber("!#$%@"));
        String expectedMessage = ("Invalid VAT number");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void equalsTest(){
        assertEquals(vatNumber1, vatNumber3);
        assertEquals(vatNumber1, vatNumber1);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();

        assertNotEquals(vatNumber1, vatNumber2);
        assertNotEquals(vatNumber1, otherObject);
    }

    @Test
    public void toStringTest(){
        assertTrue("1".contains(vatNumber1.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(vatNumber1.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(1,vatNumber2.compareTo(vatNumber1));
        assertEquals(-1,vatNumber1.compareTo(vatNumber2));
        assertEquals(0,vatNumber1.compareTo(vatNumber3));
    }
}