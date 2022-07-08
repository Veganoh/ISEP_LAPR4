package eapli.base.productmanagement.domain;

import eapli.base.common.domain.Volume;
import eapli.base.common.domain.VolumeUnit;
import eapli.base.common.domain.Weight;
import eapli.base.common.domain.WeightUnit;
import eapli.base.productcategorymanagement.domain.ProductCategory;
import eapli.base.warehousemanagement.domain.Shelf;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.Money;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductBuilder implements DomainFactory<Product> {

    /**
     * Mandatory fields
     */
    private ProductCode internalCode;
    private String shortDescription;
    private String longDescription;
    private ProductCategory category;
    private Brand brand;
    private BrandReference referenceCode;
    private Money price;
    private Volume volume;
    private Weight weight;
    private Barcode barcode;
    private ProductAvailability availability;

    /**
     * Non-mandatory fields
     */
    private String technicalDescription;
    private ExternalCode productionCode;
    private List<Photo> photos = new ArrayList<>();
    private List<Shelf> shelves = new ArrayList<>();

    /**
     * the least amount of data necessary to create a product object
     */
    public ProductBuilder withData(final String productCode, final String shortDescription, final String longDescription,
                                   final ProductCategory category, final String brand, final String referenceCode,
                                   final double price, final double volume, final double weight, final String barcode,
                                   final CodingStandard standard, final boolean availability){

        withProductCode(productCode);
        withShortDescription(shortDescription);
        withLongDescription(longDescription);
        withCategory(category);
        withBrand(brand);
        withReferenceCode(referenceCode);
        withPrice(price);
        withVolume(volume);
        withWeight(weight);
        withBarcode(barcode, standard);
        withAvailability(availability);
        return this;
    }

    /**
     * Sets the internal code of the product
     */
    public ProductBuilder withProductCode(final String productCode){
        this.internalCode = ProductCode.valueOf(productCode);
        return this;
    }

    /**
     * Sets the short description of the product
     */
    public ProductBuilder withShortDescription(final String shortDescription){
        this.shortDescription = shortDescription;
        return this;
    }

    /**
     * Sets the long description of the product
     */
    public ProductBuilder withLongDescription(final String longDescription){
        this.longDescription = longDescription;
        return this;
    }

    /**
     * Sets the technical description of the product
     */
    public ProductBuilder withTechnicalDescription(final String technicalDescription){
        this.technicalDescription = technicalDescription;
        return this;
    }

    /**
     * Sets the category of the product
     */
    public ProductBuilder withCategory(final ProductCategory category){
        this.category = category;
        return this;
    }

    /**
     * Sets the brand of the product
     */
    public ProductBuilder withBrand(final String brand){
        this.brand = Brand.valueOf(brand);
        return this;
    }

    /**
     * Sets the reference code of the product
     */
    public ProductBuilder withReferenceCode(final String referenceCode){
        this.referenceCode = BrandReference.valueOf(referenceCode);
        return this;
    }

    /**
     * Sets the availability of the product
     */
    public ProductBuilder withAvailability(final Boolean availability){
        this.availability = ProductAvailability.valueOf(availability);
        return this;
    }

    /**
     * Sets the price of the product in EUR
     */
    public ProductBuilder withPrice(final double price){
        this.price = Money.euros(price);
        return this;
    }

    /**
     * Sets the volume of the product with a specified unit
     */
    public ProductBuilder withVolume(final double volume, VolumeUnit unit){
        this.volume = Volume.valueOf(volume, unit);
        return this;
    }

    /**
     * Sets the volume of the product with a base unit of CM
     */
    public ProductBuilder withVolume(final double volume){
        this.volume = Volume.valueOf(volume, VolumeUnit.MM);
        return this;
    }

    /**
     * Sets the volume of the product but receives the information from a string
     * Ex: 12.43 cm; 35.98 Dm
     */
    public ProductBuilder withVolume(final String volume){
        this.volume = Volume.valueOf(volume);
        return this;
    }

    /**
     * Sets the weight of the product with a specified unit
     */
    public ProductBuilder withWeight(final double weight, WeightUnit unit){
        this.weight = Weight.valueOf(weight, unit);
        return this;
    }

    /**
     * Sets the weight of the product with a base unit of G
     */
    public ProductBuilder withWeight(final double weight){
        this.weight = Weight.valueOf(weight, WeightUnit.G);
        return this;
    }

    /**
     * Sets the weight of the product but receives the information from a string
     * Ex: 12.43 kg; 35.98 G
     */
    public ProductBuilder withWeight(final String weight){
        this.weight = Weight.valueOf(weight);
        return this;
    }

    /**
     * Sets the barcode with the specified coding standard
     */
    public ProductBuilder withBarcode(final String barcode, CodingStandard standard){
        this.barcode = Barcode.valueOf(barcode, standard);
        return this;
    }

    /**
     * Sets the barcode with an implied coding standard
     */
    public ProductBuilder withBarcode(final String barcode){
        this.barcode = Barcode.valueOf(barcode);
        return this;
    }

    /**
     * Sets the production code of the product
     */
    public ProductBuilder withExternalCode(final String productionCode){
        this.productionCode = ExternalCode.valueOf(productionCode);
        return this;
    }

    /**
     * Adds a photo to the set of photos of this builder
     */
    public ProductBuilder withPhoto(final String url, final String name, final String description){
        photos.add(Photo.valueOf(url, name, description)) ;
        return this;
    }

    /**
     * Adds a new shelf to the list of locations of a product
     */
    public ProductBuilder withShelf(Shelf shelf) {
        shelves.add(shelf);
        return this;
    }

    /**
     * Builds the final product
     * @return a new product with all the provided information
     */
    @Override
    public Product build() {

        Product product = new Product(internalCode, ProductDescriptions.valueOf(shortDescription, longDescription,
                technicalDescription), category, brand, referenceCode, availability, price, volume, weight, barcode, productionCode);

        for (Photo photo : photos)
            product.addPhoto(photo);

        for (Shelf shelf : shelves)
            product.addShelf(shelf);

        return product;
    }

}
