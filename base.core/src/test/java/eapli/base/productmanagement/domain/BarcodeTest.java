package eapli.base.productmanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BarcodeTest {

    @Test
    public void validDataTest(){
        Barcode barcode1 = new Barcode("123456789123", CodingStandard.UPC);
        Barcode barcode2 = Barcode.valueOf("1234567891234", CodingStandard.EAN13);
        Barcode barcode3 = Barcode.valueOf("123456789123");

        assertNotNull(barcode1);
        assertNotNull(barcode2.hashCode());
        assertNotNull(barcode3);
        assertNotNull(new Barcode());
        assertEquals(barcode1, barcode3);
    }

    @Test
    public void invalidDataTest(){
        assertThrows(IllegalArgumentException.class, ()->{ Barcode barcode1 = new Barcode("123456789123", null);});
        assertThrows(IllegalArgumentException.class, ()->{Barcode barcode1 = new Barcode("1234567891234", CodingStandard.UPC);});
        assertThrows(IllegalArgumentException.class, ()->{Barcode.valueOf("1234123");});
    }

    @Test
    public void equalsTest(){
        Barcode barcode1 = new Barcode("123456789123", CodingStandard.UPC);
        Barcode barcode2 = new Barcode("123456789123", CodingStandard.UPC);
        assertNotEquals(barcode1, "this is different");
        assertEquals(barcode1, barcode1);
        assertEquals(barcode1, barcode2);
    }

    @Test
    public void compareTest(){
        Barcode barcode1 = new Barcode("123456789123", CodingStandard.UPC);
        Barcode barcode2 = new Barcode("123456789124", CodingStandard.UPC);
        Barcode barcode3 = new Barcode("123456789123", CodingStandard.UPC);

        assertTrue(barcode1.compareTo(barcode2) < 0);
        assertTrue(barcode2.compareTo(barcode1) > 0);
        assertTrue(barcode1.compareTo(barcode3) == 0);
    }

    @Test
    public void toStringTest(){
        Barcode barcode1 = new Barcode("123456789123", CodingStandard.UPC);
        assertEquals(barcode1.toString(), "123456789123 - UPC");

    }
}