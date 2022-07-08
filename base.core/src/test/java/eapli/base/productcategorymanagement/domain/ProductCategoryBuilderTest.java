package eapli.base.productcategorymanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductCategoryBuilderTest {

    private String alphaCode = "alphacode";
    private String description = "this is a description";
    private float tax = 20.0F;


    @Test
    public void ValidDataTest(){
        ProductCategoryBuilder builder = new ProductCategoryBuilder();
        ProductCategory productCategory1 = builder.withData(alphaCode, description).build();
        ProductCategory productCategory2 = builder.withTax(tax).build();
        assertNotNull(productCategory1);
        assertNotNull(productCategory2);
    }

    @Test
    public void invalidDataTest(){
        assertThrows(IllegalArgumentException.class,
                ()->{
                    ProductCategoryBuilder builder = new ProductCategoryBuilder();
                    ProductCategory productCategory = builder.withAlphaCode(alphaCode).build();
                });

        assertThrows(IllegalArgumentException.class,
                ()->{
                    ProductCategoryBuilder builder = new ProductCategoryBuilder();
                    ProductCategory productCategory = builder.withDescription(description).build();
                });

        assertThrows(IllegalArgumentException.class,
                ()->{
                    ProductCategoryBuilder builder = new ProductCategoryBuilder();
                    ProductCategory productCategory = builder.withTax(tax).build();
                });
    }

}