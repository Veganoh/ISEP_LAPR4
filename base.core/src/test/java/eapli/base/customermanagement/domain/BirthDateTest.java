package eapli.base.customermanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BirthDateTest {
    BirthDate birthDate1 = new BirthDate(30,11,2020);
    BirthDate birthDate2 = new BirthDate(31,12,2000);
    BirthDate birthDate3 = new BirthDate(30,11,2020);

    BirthDate birthDate4 = new BirthDate(12, 4, 2021);

    @Test
    public void validConstructorTest(){
        new BirthDate();
        assertNotNull(birthDate1);
        assertEquals(birthDate1, birthDate3);
    }

    @Test
    public void emptyConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new BirthDate(0,0,0));
        String expectedMessage = ("Invalid birth date");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void invalidConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new BirthDate(-1,3,-4));
        String expectedMessage = ("Invalid birth date");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void equalsTest(){
        assertEquals(birthDate1, birthDate3);
        assertEquals(birthDate1, birthDate1);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();

        assertNotEquals(birthDate1, birthDate2);
        assertNotEquals(birthDate1, otherObject);
    }

    @Test
    public void toStringTest(){
        assertEquals("Birth Date: 30-11-2020 (DD-MM-YY)", birthDate1.toString());
        assertEquals("Birth Date: 12-04-2021 (DD-MM-YY)", birthDate4.toString());
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(birthDate1.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(-1,birthDate2.compareTo(birthDate1));
        assertEquals(1,birthDate1.compareTo(birthDate2));
        assertEquals(0,birthDate1.compareTo(birthDate3));
    }
}