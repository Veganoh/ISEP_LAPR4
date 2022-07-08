package eapli.base.productmanagement.domain;

import eapli.base.common.domain.Description;
import eapli.base.common.domain.VolumeUnit;
import eapli.base.common.domain.WeightUnit;
import eapli.base.productcategorymanagement.domain.AlphaCode;
import eapli.base.productcategorymanagement.domain.ProductCategory;
import eapli.base.productcategorymanagement.domain.Tax;
import eapli.base.warehousemanagement.domain.Shelf;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductBuilderTest {

    private String internalCode ="code.12345";
    private String shortDescription = "A short Description";
    private String longDescription = "A longer sort of Description";
    private ProductCategory category = new ProductCategory(new AlphaCode("alpha-code"), new Description("This is a test description"),Tax.valueOf(10));
    private String brand = "Brand";
    private String referenceCode = "refer";
    private double price = 12;
    private double volume = 1.5 ;
    private double weight = 2.5 ;
    private String barcode = "012345678912";

    private boolean availability = false;
    private String technicalDescription = "A more technical Description";
    private String productionCode = "prod.12345";

    private List<Photo> photos = new ArrayList<>();

    @Test
    public void constructorTest(){
        ProductBuilder builder = new ProductBuilder();
        builder.withData(internalCode, shortDescription, longDescription, category, brand, referenceCode, price, volume,
                            weight, barcode, CodingStandard.UPC, true);
        assertNotNull(builder.build());
        builder.withAvailability(false).withTechnicalDescription(technicalDescription).withExternalCode("extr.12345")
                .withPhoto("src/test/java/eapli/base/productmanagement/domain/testassets/PhotoTest.jpg",
                        "Domain Model", "The domain model")
                .withShelf(new Shelf("1", "1", "1"));
        assertNotNull(builder.build());
        builder.withBarcode("123456789012");
        builder.withVolume("12 CM").withVolume(12, VolumeUnit.CM);
        builder.withWeight("12 KG").withWeight(12, WeightUnit.KG);
    }

    @Test
    public void invalidDataTest(){
        assertThrows(IllegalArgumentException.class,
                ()->{
        ProductBuilder builder = new ProductBuilder();
        builder.withAvailability(false).withTechnicalDescription(technicalDescription).withShortDescription(shortDescription)
                .withLongDescription(longDescription).withPhoto("src/test/java/eapli/base/productmanagement/domain/testassets/PhotoTest.jpg",
                        "Domain Model", "The domain model");
        builder.build(); });
    }
}