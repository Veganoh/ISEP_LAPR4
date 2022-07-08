package eapli.base.app.other.console.salesClerk.salesandorders;

import eapli.base.productcategorymanagement.domain.ProductCategory;
import eapli.base.productmanagement.application.AddProductController;
import eapli.base.productmanagement.domain.CodingStandard;
import eapli.base.warehousemanagement.domain.Shelf;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.List;

public class AddProductUI  extends AbstractUI {

    /**
     * The UI's controller
     */
    private final AddProductController theController = new AddProductController();

    /**
     * Main method of the UI
     * @return shows the steps to add a new product
     */
    @Override
    protected boolean doShow() {
        try {
            SelectWidget<ProductCategory> categoryWidget = new SelectWidget<>("Select a Category", theController.getCategories());
            categoryWidget.show();
            final ProductCategory category = categoryWidget.selectedElement();

            final String productCode = Console.readLine("Internal Code");
            final String shortDescription = Console.readLine("Short Description (No more than 30 characters)");
            final String longDescription = Console.readLine("Long Description (No less than 20 or more than 100 characters)");
            final String technicalDescription = Console.readLine("Technical Description (Optional)");
            final double price = Console.readDouble("Price");
            final String brand = Console.readLine("Product Brand");
            final String referenceCode = Console.readLine("Brand's Reference Code");
            final String productionCode = Console.readLine("Production Code (Optional)");

            SelectWidget<CodingStandard> standardWidget = new SelectWidget<>("Select a barcode standard", theController.getBarcodeStandards());
            standardWidget.show();
            final CodingStandard standard = standardWidget.selectedElement();

            final String barcode = Console.readLine("Barcode (Must be in accordance with previously chosen standard)");
            final double volume = Console.readDouble("Volume (in millimetres)");
            final double weight = Console.readDouble("Weight (in grams)");
            final boolean availability = true;

            this.theController.addProduct(productCode, shortDescription, longDescription, category, brand, referenceCode,
                                        price, volume, weight, barcode, standard, technicalDescription, productionCode, availability);


            photoLoop();

            shelfLoop();

            theController.saveProduct();

            System.out.println("\nProduct Successfully added\n");
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("That alpha code is already in use.");
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }


        return false;
    }

    /**
     * Main loop to add new photos
     */
    private void photoLoop(){

        String image = "Add images to the product entry? (Y/N)";
        while (Console.readBoolean(image)){
            image = "Keep adding images?(Y/N)";
            final String url = Console.readLine("Image's url");
            final String name = Console.readLine("Image's name");
            final String description = Console.readLine("Image's description");

            theController.addPhoto(url, name, description);
        }
    }

    /**
     * Lets the user add location to the product
     */
    private void shelfLoop(){
        List<Shelf> shelfList = theController.getShelves();

        if (shelfList.isEmpty()) {
            System.out.println("There are no available shelves at the moment");
            return;
        }

        if (Console.readBoolean("Is the product already on shelves? (Y/N)")){
            boolean flag = true;

            while(flag) {

                if (shelfList.isEmpty())
                    return;

                SelectWidget<Shelf> shelfWidget = new SelectWidget<>("Product Location", shelfList);
                shelfWidget.show();
                Shelf shelf = shelfWidget.selectedElement();

                if (shelf == null)
                    flag = false;
                else {
                    theController.addShelf(shelf);
                    shelfList.remove(shelf);
                }

            }
        }
    }

    /**
     * The UI headline
     * @return The UI headline
     */
    @Override
    public String headline() {
        return "Add a new Product";
    }
}
