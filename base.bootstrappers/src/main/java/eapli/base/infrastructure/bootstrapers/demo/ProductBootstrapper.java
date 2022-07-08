package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.productcategorymanagement.domain.ProductCategory;
import eapli.base.productmanagement.application.AddProductController;
import eapli.base.productmanagement.domain.CodingStandard;
import eapli.base.productmanagement.domain.Product;
import eapli.base.warehousemanagement.domain.Shelf;
import eapli.framework.actions.Action;

import java.util.Iterator;
import java.util.List;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

public class ProductBootstrapper implements Action {
    AddProductController addProductController = new AddProductController();

    @Override
    public boolean execute() {
        Iterator<ProductCategory> listCategories = addProductController.getCategories().iterator();

        ProductCategory productCategory1 = listCategories.next();
        ProductCategory productCategory2 = listCategories.next();
        ProductCategory productCategory3 = listCategories.next();

        if (addProductController.getCategories() == null) {
            LOGGER.warn("Assuming there are no created categories");
            return false;
        }

        addProduct("PROD.00001", "BB Facial Cream FPS50+", "Immediate protection against solar burns and UV caused damage",
                productCategory1, "NIVEA", "BBFC01", 8.39, 50, 75, "1578621035256", CodingStandard.EAN13,
                null, null, true, 0);

        addProduct("PROD.00002", "Serum Facial Revitalift Laser", "Anti-Aging serum with \"new skin\" effect with 3 actions.",
                productCategory1, "L'Oréal Paris", "SFRLX301", 29.99, 30, 50, "223698457421", CodingStandard.UPC,
                null, null, true, 10);

        addProduct("PROD.00003", "Gaming Monitor ASUS VG248QG", "The fastest monitor of its class with a response time of 0.5ms.",
                productCategory2, "ASUS", "VG248QG", 239.99, 270, 5200, "3214578545896", CodingStandard.EAN13,
                null, null, true, 30);

        addProduct("PROD.00004", "Laptop HP 15s-fq2032np", "A long lasting battery and a screen with a thin border in this elegant laptop.",
                productCategory2, "HP", "FQ2032NP", 549.99, 795, 1900, "457841548758", CodingStandard.UPC,
                null, null, true, 40);

        addProduct("PROD.00005", "Yämmi 2 XL", "A kitchen robot that allows you to make recipes in a fast manner.",
                productCategory3, "Yämmi", "YAM2XL01", 549.00, 350, 7900, "5487593258412", CodingStandard.EAN13,
                null, null, true, 20);

        addProduct("PROD.00006", "Coffee Machine Inissia", "Inissia counts with various colors and an elegant finish... a design impossible to resist.",
                productCategory3, "Nespresso", "EN80B", 90.99, 1900, 2300, "665845216325", CodingStandard.UPC,
                null, null, true, 50);

        return true;
    }

    public Product addProduct(final String productCode, final String shortDescription, final String longDescription,
                              final ProductCategory category, final String brand, final String referenceCode,
                              final double price, final double volume, final double weight, final String barcode,
                              CodingStandard standard, final String technicalDescription, final String productionCode,
                              final boolean availability, int shelf) {
        Product product = null;

        try {
            addProductController.addProduct(productCode, shortDescription, longDescription, category, brand, referenceCode, price,
                    volume, weight, barcode, standard, technicalDescription, productionCode, availability);

            List<Shelf> shelfList = addProductController.getShelves();

            addProductController.addShelf(shelfList.get(shelf));

            product = addProductController.saveProduct();
            addProductController = new AddProductController();
        } catch (final IllegalArgumentException e) {
            LOGGER.warn("Assuming something is wrong with the Product Data (activate trace log for details)");
            LOGGER.trace("Assuming wrong data", e);
        }

        return product;
    }
}
