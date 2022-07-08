package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productcategorymanagement.domain.ProductCategory;
import eapli.base.productcategorymanagement.repositories.ProductCategoryRepository;
import eapli.base.productmanagement.domain.CodingStandard;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.ProductBuilder;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.Shelf;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.apache.commons.lang3.EnumUtils;

import java.util.Iterator;
import java.util.List;

public class AddProductController {

    /**
     * Authorization service that is used to make sure only authorized users can use the controller
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * The category repository
     */
    private final ProductCategoryRepository categoryRepo = PersistenceContext.repositories().Categories();

    /**
     * The product repository
     */
    private final ProductRepository productRepo = PersistenceContext.repositories().Products();

    /**
     * The product repository
     */
    private final WarehouseRepository warehouseRepository = PersistenceContext.repositories().Warehouses();

    /**
     * The builder used by this controller
     */
    private ProductBuilder builder = new ProductBuilder();

    public AddProductController(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.ADMIN, BaseRoles.POWER_USER);
    }

    /**
     * Gets a list of product categories
     * @return a list of product categories
     */
    public Iterable<ProductCategory> getCategories(){
        return  categoryRepo.findAll();
    }

    /**
     * Gets a list of barcode standards
     * @return a list of barcode standards
     */
    public List<CodingStandard> getBarcodeStandards(){
        return EnumUtils.getEnumList(CodingStandard.class);
    }

    /**
     * Gets a list of empty shelves
     * @return a list of empty shelves
     */
    public List<Shelf> getShelves(){
        Iterator<Warehouse> warehouseIterator = warehouseRepository.findAll().iterator();
        Warehouse warehouse = warehouseIterator.next();
        warehouse.loadShelves();

        List<Shelf> shelfList = warehouse.shelves();
        List<Shelf> unAvailableShelves = (List<Shelf>) productRepo.findOccupiedShelves();

        shelfList.removeAll(unAvailableShelves);

        return shelfList;
    }

    /**
     * Adds the main information of the product
     * @param productCode the product's code
     * @param shortDescription the product's short description
     * @param longDescription the product's long description
     * @param category the product's category
     * @param brand the product's brand
     * @param referenceCode the product's reference code
     * @param price the product's price
     * @param volume the product's volume
     * @param weight the product's wight
     * @param barcode the product's barcode
     * @param standard the product's barcode standard
     * @param technicalDescription the product's technical description
     * @param productionCode the product's production code
     * @param availability the product's availability
     */
    public void addProduct(final String productCode, final String shortDescription, final String longDescription,
                                      final ProductCategory category, final String brand, final String referenceCode,
                                      final double price, final double volume, final double weight, final String barcode,
                                      CodingStandard standard, final String technicalDescription, final String productionCode
                                      ,final boolean availability) {

        builder.withData(productCode, shortDescription, longDescription, category, brand, referenceCode, price, volume, weight, barcode, standard, availability);

        if (technicalDescription != null && technicalDescription.length() != 0 )
            builder.withTechnicalDescription(technicalDescription);
        if ( productionCode != null && productionCode.length() != 0)
            builder.withExternalCode(productionCode);

    }

    /**
     * Adds more photos to the builder
     * @param url the images url
     * @param name the image's name
     * @param description the images description
     */
    public void addPhoto(final String url, final String name, final String description) {

        builder.withPhoto(url, name, description);
    }

    /**
     * Adds more shelves to the builder
     * @param shelf the new location of the product
     */
    public void addShelf(final Shelf shelf){

        builder.withShelf(shelf);
    }

    /**
     * Builds and saves the product
     * @return the saved product
     */
    public Product saveProduct(){

        Product product = builder.build();

        return productRepo.save(product);
    }
}
