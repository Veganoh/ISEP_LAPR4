package eapli.base.productcategorymanagement.domain;

import eapli.base.common.domain.Description;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryTest {

    AlphaCode alphaCode = new AlphaCode("alphaCode");
    Tax tax = new Tax(23);
    Description description = new Description("This is a Product Category");
    ProductCategory category =  new ProductCategory(alphaCode, description, tax);


    @Test
    public void validConstructorTest(){
        assertNotNull(new ProductCategory().hashCode());
        assertNotNull(category);
        assertEquals(category.identity(), alphaCode);
        assertEquals(category.getTax(), tax);
        assertEquals(category.toString(), String.format("Category alpha code: %s\nCategory description: %s", alphaCode, description));
    }

    @Test
    public void  sameTest(){
        assertTrue(category.sameAs(category));
    }

    @Test
    public void invalidDescriptionTest(){
        assertThrows(IllegalArgumentException.class, ()->{
            ProductCategory barcode1 = new ProductCategory(alphaCode, new Description("short desc"), tax);});
    }
}