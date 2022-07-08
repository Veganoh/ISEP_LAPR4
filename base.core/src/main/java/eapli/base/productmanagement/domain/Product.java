package eapli.base.productmanagement.domain;

import eapli.base.common.domain.Volume;
import eapli.base.common.domain.Weight;
import eapli.base.productcategorymanagement.domain.ProductCategory;
import eapli.base.warehousemanagement.domain.Shelf;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Money;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product implements AggregateRoot<ProductCode> {

    /**
     * the products internal code serving as it's id
     */
    @EmbeddedId
    private ProductCode internalCode;

    /**
     * The various product descriptions (technical description is not mandatory)
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "shortDescription.description", column = @Column(name = "shortDescription")),
            @AttributeOverride(name = "longDescription.description", column = @Column(name = "longDescription")),
            @AttributeOverride(name = "technicalDescription.description", column = @Column(name = "technicalDescription")),
    })
    private ProductDescriptions productDescriptions;

    /**
     * Product's category
     */
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "category_alphaCode", referencedColumnName = "alphaCode")
    })
    private ProductCategory category;

    /**
     * product's Brand
     */
    @Embedded
    private Brand brand;

    /**
     * product's referenceCode
     */
    @Embedded
    private BrandReference referenceCode;

    /**
     * product's availability
     */
    @Embedded
    private ProductAvailability availability;

    /**
     * product's price in EUR
     */
    @Embedded
    private Money price;

    /**
     * product's volume
     */
    @Embedded
    private Volume volume;

    /**
     * product's weight
     */
    @Embedded
    private Weight weight;

    /**
     * product's barcode
     */
    @Embedded
    private Barcode barcode;

    /**
     * product's production code
     */
    @Embedded
    private ExternalCode productionCode;

    /**
     * product's list of photos
     */
    @ElementCollection(fetch = FetchType.LAZY)
    private final List<Photo> photos = new ArrayList<>();

    /**
     * The shelves that contain the product
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<Shelf> availableShelves = new ArrayList<>();

    /**
     * Construct the product
     *
     * @param internalCode        the products internal code serving as it's id
     * @param productDescriptions The various product descriptions (technical description is not mandatory)
     * @param category            Product's category
     * @param brand               product's Brand
     * @param referenceCode       product's referenceCode
     * @param availability        product's availability
     * @param price               product's price in EUR
     * @param volume              product's volume
     * @param weight              product's weight
     * @param barcode             product's barcode
     * @param productionCode      product's production code
     */
    public Product(ProductCode internalCode, ProductDescriptions productDescriptions, ProductCategory category, Brand brand,
                   BrandReference referenceCode, ProductAvailability availability, Money price, Volume volume, Weight weight,
                   Barcode barcode, ExternalCode productionCode) {

        Preconditions.noneNull(internalCode, productDescriptions, category, brand, referenceCode, availability, barcode, price, volume, weight);

        if (!price.currency().getCurrencyCode().equals("EUR"))
            throw new IllegalArgumentException("Base currency must be stored in EUR instead of " + price.currency().getCurrencyCode());

        //Mandatory
        this.internalCode = internalCode;
        this.productDescriptions = productDescriptions;
        this.category = category;
        this.brand = brand;
        this.referenceCode = referenceCode;
        this.availability = availability;
        this.price = price;
        this.volume = volume;
        this.weight = weight;
        this.barcode = barcode;

        //Optional
        this.productionCode = productionCode;
    }

    /**
     * empty constructor for ORM
     */
    protected Product() {
        //empty for ORM
    }

    /**
     * Adds a photo to the list, by default the list is empty
     *
     * @param photo a new photo to be added to the product
     * @return true if it is added and false if it is not
     */
    public boolean addPhoto(Photo photo) {
        if (!photos.contains(photo))
            return photos.add(photo);
        throw new IllegalArgumentException("Photo is already registered to this product");
    }

    /**
     * Adds a new shelf to the list of shelves containing the product
     *
     * @param shelf a shelf that will now contain the product
     * @return true if it is added and false if it is not
     */
    public boolean addShelf(Shelf shelf) {
        if (!availability.isAvailable())
            availability = new ProductAvailability(true);
        if (!availableShelves.contains(shelf))
            return availableShelves.add(shelf);
        throw new IllegalArgumentException("product already is in that Shelf");
    }

    /**
     * the objects hashcode
     *
     * @return the hashcode of the object
     */
    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    /**
     * returns true if this object has the same identity as another product
     *
     * @param other the other object
     * @return true if they are equal
     */
    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    /**
     * returns the product identity
     *
     * @return the product identity
     */
    @Override
    public ProductCode identity() {
        return this.internalCode;
    }

    public ProductDescriptions getProductDescriptions() {
        return productDescriptions;
    }

    public Money getPrice() {
        return price;
    }

    public Brand getBrand() {
        return brand;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public Money getPriceWithTax() {
        return price.multiply(1 + category.getTax().value());
    }

    public Weight getWeight() {
        return weight;
    }
    public Volume getVolume() {
        return volume;
    }

    public List<Shelf> availableShelves() {
        return availableShelves;
    }

    public boolean isValid() {
        return this.availability.isAvailable();
    }

    @Override
    public String toString() {
        return String.format("%s - %s | %s\nRef.: %s / %s | PVP %s (%s w/Tax)", productDescriptions.shortestDescription(),
        productDescriptions.longestDescription(), brand, internalCode, referenceCode, price, getPriceWithTax());
    }
}
