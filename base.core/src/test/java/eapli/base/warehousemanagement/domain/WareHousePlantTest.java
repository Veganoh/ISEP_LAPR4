package eapli.base.warehousemanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WareHousePlantTest {

    @Test
    public void TestDescription(){
        WareHousePlant description1 = new WareHousePlant("This is a description");
        WareHousePlant description2 = new WareHousePlant("This is another description");
        WareHousePlant description3 = new WareHousePlant("This is a description");

        assertNotNull(new WareHousePlant());
        assertNotNull(description1);

        assertNotNull(description1.hashCode());
        assertEquals(description1, description1);
        assertEquals(description1, description3);
        assertTrue(description1.compareTo(description3) == 0);
        assertNotEquals(description1, new String());
        assertNotEquals(description1, description2);
        assertEquals("This is a description", description1.toString());

    }
}