package eapli.base.productmanagement.domain;

import eapli.base.common.domain.*;
import eapli.base.productcategorymanagement.domain.AlphaCode;
import eapli.base.productcategorymanagement.domain.ProductCategory;
import eapli.base.productcategorymanagement.domain.Tax;
import eapli.base.warehousemanagement.domain.Shelf;
import eapli.framework.general.domain.model.Money;
import org.junit.jupiter.api.Test;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    final ProductCode internalCode = new ProductCode("asdf.12345");
    private String shortDescription = "A short Description";
    private String longDescription = "A longer sort of Description";
    final ProductDescriptions productDescriptions = new ProductDescriptions(new Description(shortDescription), new Description(longDescription), null);
    final ProductCategory category = new ProductCategory(new AlphaCode("alpha"), new Description("This is a description"), new Tax(20));
    Brand brand = new Brand("brand");
    BrandReference referenceCode =  new BrandReference("reger");
    ProductAvailability availability = new ProductAvailability(true);
    Money price = new Money(20, Currency.getInstance("EUR"));
    Volume volume = new Volume(20, VolumeUnit.MM);
    Weight weight = new Weight(20, WeightUnit.G);
    Barcode barcode = new Barcode("013245678901", CodingStandard.UPC);
    ExternalCode productionCode = new ExternalCode("prod.12345");
    Photo photo = new Photo("src/test/java/eapli/base/productmanagement/domain/testassets/PhotoTest.jpg","Domain Model", "The domain model");
    Shelf shelf = new Shelf("1", "1", "1");

    @Test
    public void ValidDataTest(){
        assertNotNull(new Product());
        Product product = new Product(internalCode, productDescriptions, category, brand, referenceCode,availability, price, volume, weight, barcode, productionCode);
        assertEquals(product.identity(), internalCode);
        assertEquals(product.getBrand(), brand);
        assertEquals(product.getProductDescriptions(), productDescriptions);
        assertEquals(product.getCategory(), category);
        assertEquals(product.getPrice(), price);
        assertEquals(product.getWeight(), weight);
        assertEquals(product.getPriceWithTax(), new Money(24, Currency.getInstance("EUR")));
        assertNotNull(product.hashCode());
        assertNotNull(product.availableShelves());
        assertTrue(product.isValid());
    }

    @Test
    public void InvalidDataTest(){
        assertThrows(IllegalArgumentException.class,
                ()->{
                new Product(internalCode, productDescriptions, category, brand, referenceCode,availability, new Money(20, Currency.getInstance("USD")), volume, weight, barcode, productionCode);
                });

        assertThrows(IllegalArgumentException.class,
                ()->{
                    Product product = new Product(internalCode, productDescriptions, category, brand, referenceCode,availability, price, volume, weight, barcode, productionCode);
                    product.addPhoto(photo);
                    product.addPhoto(photo);
                });

        assertThrows(IllegalArgumentException.class,
                ()->{
                    Product product = new Product(internalCode, productDescriptions, category, brand, referenceCode,availability, price, volume, weight, barcode, productionCode);
                    product.addShelf(shelf);
                    product.addShelf(shelf);
                });

        assertThrows(IllegalArgumentException.class,
                ()->{
                    new Product(null, productDescriptions, category, brand, referenceCode,availability, new Money(20, Currency.getInstance("USD")), volume, weight, barcode, productionCode);
                });

    }

    @Test
    public void sameAsTest(){
        Product product = new Product(internalCode, productDescriptions, category, brand, referenceCode,availability, price, volume, weight, barcode, productionCode);

        assertTrue(product.sameAs(product));
    }
}