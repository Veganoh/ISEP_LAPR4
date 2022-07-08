package eapli.base.productcategorymanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxTest {

    @Test
    public void validConstructorTest(){
        assertNotNull(new Tax().hashCode());
        Tax tax = new Tax(1);
        assertEquals(tax, Tax.valueOf(1));
        assertEquals(0.01, tax.value());
    }

    @Test
    public void invalidDataTest(){
        assertThrows(IllegalArgumentException.class, ()->{ new Tax(-1);});
        assertThrows(IllegalArgumentException.class, ()->{ Tax.valueOf(-1);});
    }

    @Test
    public void equalsTest(){
        Tax tax = new Tax(1);
        Tax tax1 = tax;
        assertNotEquals(tax, new String());
        assertEquals(tax, tax1);
        tax1 = new Tax(1);
        assertEquals(tax1, tax);
    }

    @Test
    public void toStringTest(){
        Tax tax = new Tax(1);
        assertEquals(tax.toString(), String.format("%.4f" , 1F)+ "%");
    }

    @Test
    public void compareTest(){
        Tax tax1 = new Tax(1);
        Tax tax2 = new Tax(2);
        Tax tax3 = new Tax(1);
        assertTrue(tax1.compareTo(tax2) < 0);
        assertTrue(tax2.compareTo(tax1) > 0);
        assertTrue(tax1.compareTo(tax3) == 0);
    }
}