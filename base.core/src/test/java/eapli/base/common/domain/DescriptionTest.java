package eapli.base.common.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {


    @Test
    public void TestDescription(){
        Description description1 = new Description("This is a description");
        Description description2 = new Description("This is another description");
        Description description3 = new Description("This is a description");

        assertNotNull(new Description());
        assertNotNull(description1);
        assertNotNull(description1.hashCode());

        assertEquals(description1, description3);
        assertEquals(description1, description1);
        assertNotEquals(description1, new String());
        assertNotEquals(description1, description2);
        assertEquals("This is a description", description1.toString());

    }
}