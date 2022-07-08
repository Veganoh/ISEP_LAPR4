package eapli.base.productcategorymanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlphaCodeTest {

    @Test
    public void ValidConstructorTest(){
        assertNotNull(new AlphaCode().hashCode());
        assertEquals(new AlphaCode("alphaCode"), AlphaCode.valueOf("alphaCode"));
    }

    @Test
    public void equalsTest(){
        AlphaCode alphaCode = new AlphaCode("alphaCode");
        AlphaCode alphaCode1 = alphaCode;
        assertNotEquals(alphaCode,"alphaCode");
        assertEquals(alphaCode, alphaCode);
        alphaCode1 = new AlphaCode("alphaCode");
        assertEquals(alphaCode, alphaCode1);
    }

    @Test
    public void toStringTest(){
        AlphaCode alphaCode = new AlphaCode("alphaCode");
        assertEquals(alphaCode.toString(), "alphaCode");
    }

    @Test
    public void compareTest(){
        AlphaCode alphaCode1 = new AlphaCode("alphaCode1");
        AlphaCode alphaCode2 = new AlphaCode("alphaCode2");
        AlphaCode alphaCode3 = new AlphaCode("alphaCode1");
        assertTrue(alphaCode1.compareTo(alphaCode2) < 0);
        assertTrue(alphaCode2.compareTo(alphaCode1) > 0);
        assertTrue(alphaCode1.compareTo(alphaCode3) == 0);
    }

    @Test
    public void invalidAlphaCode(){
        assertThrows(IllegalArgumentException.class, ()->{
            AlphaCode alphacode = new AlphaCode("1234567891234");});
    }

}