package eapli.base.productmanagement.domain;

import eapli.base.common.domain.Description;
import eapli.base.util.domain.RandomString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDescriptionsTest {

    Description shortDescription = new Description("A Description");
    Description longDescription = new Description("This is a description");
    Description technicalDescription = new Description(RandomString.generateRandomString(110));

    ProductDescriptions productDescription1 = new ProductDescriptions(shortDescription, longDescription, technicalDescription);
    ProductDescriptions productDescription2 = new ProductDescriptions( new Description("A Descriptiona"), longDescription, null);
    ProductDescriptions productDescription3 =  ProductDescriptions.valueOf(shortDescription.toString(), longDescription.toString(), technicalDescription.toString());

    @Test
    public void validDataTest(){
        new ProductDescriptions();
        assertNotNull(productDescription1);
        assertEquals(productDescription1, productDescription3);
    }

    @Test
    public void invalidDataTest(){
        assertThrows(IllegalArgumentException.class, ()->{ new ProductDescriptions(new Description(""), longDescription, technicalDescription);});
        assertThrows(IllegalArgumentException.class, ()->{ new ProductDescriptions(shortDescription, new Description(""), technicalDescription);});
        assertThrows(IllegalArgumentException.class, ()->{ new ProductDescriptions(technicalDescription, longDescription, technicalDescription);});
        assertThrows(IllegalArgumentException.class, ()->{ new ProductDescriptions(shortDescription, technicalDescription, technicalDescription);});
    }

    @Test
    public void equalsTest(){
        assertEquals(productDescription1, productDescription3);
        assertEquals(productDescription1, productDescription1);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();
        assertNotEquals(productDescription1, productDescription2);
        assertNotEquals(productDescription1, otherObject);
    }

    @Test
    public void toStringTest(){
        assertTrue(productDescription1.toString().contains(shortDescription.toString()));
        assertTrue(productDescription1.toString().contains(longDescription.toString()));
        assertTrue(productDescription1.toString().contains(technicalDescription.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(productDescription1.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(1, productDescription2.compareTo(productDescription1));
        assertEquals(-1, productDescription1.compareTo(productDescription2));
        assertEquals(0, productDescription1.compareTo(productDescription3));
    }
}