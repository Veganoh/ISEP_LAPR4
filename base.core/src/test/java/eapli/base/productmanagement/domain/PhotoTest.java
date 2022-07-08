package eapli.base.productmanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhotoTest {

    Photo photo1 = new Photo("src/test/java/eapli/base/productmanagement/domain/testassets/PhotoTest.jpg",
            "Domain Model1", "The domain model");
    Photo photo2 = new Photo("src/test/java/eapli/base/productmanagement/domain/testassets/PhotoTest.jpg",
            "Domain Model2", "The domain model");
    Photo photo22 =  Photo.valueOf("src/test/java/eapli/base/productmanagement/domain/testassets/PhotoTest.jpg",
            "Domain Model1", "The domain model");

    @Test
    public void validDataTest(){
        new Photo();
        assertNotNull(photo1);
        assertEquals(photo1, photo22);
    }

    @Test
    public void invalidDataTest(){
        assertThrows(IllegalArgumentException.class, ()->{ new Photo("src/test/java/eapli/base/productmanagement/domain/testassets/PhotoTest.pg",
                "Domain Model1", "The domain model");});
        assertThrows(IllegalArgumentException.class, ()->{ new Photo("src/test/java/eapli/base/productmanagement/domain/testasset/PhotoTest.jpg",
                "Domain Model1", "The domain model");});
    }

    @Test
    public void equalsTest(){
        assertEquals(photo1, photo22);
        assertEquals(photo1, photo1);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();
        assertNotEquals(photo1, photo2);
        assertNotEquals(photo1, otherObject);
    }

    @Test
    public void toStringTest(){
        assertTrue("Image name: Domain Model1\nImage Description: The domain model".contains(photo1.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(photo1.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(1, photo2.compareTo(photo1));
        assertEquals(-1, photo1.compareTo(photo2));
        assertEquals(0, photo1.compareTo(photo22));
    }

}