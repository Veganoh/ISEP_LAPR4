package eapli.base.app.user.console.presentation.clientuser;

import eapli.base.productmanagement.application.AddProductShoppingCartController;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;

public class AddProductShoppingCartUI extends AbstractUI {

    /**
     * The UI's controller
     */
    private final AddProductShoppingCartController theController = new AddProductShoppingCartController();

    /**
     * The product printer
     */
    private final ProductPrinter printer = new ProductPrinter();

    /**
     * Main method of the UI
     *
     * @return shows the steps to add a new product
     */
    @Override
    protected boolean doShow() {
        try{
            theController.openConnection();
            ArrayList<Product> list = theController.getProductList();
            showProducts(list);
            theController.addSystemUser();
            productLoop();
            theController.closeConnection();

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Main loop to add new order items
     */
    private void productLoop() {
        do {
            final String product = Console.readLine("Select a Product (type the desired Product Code)");

            final int quantity = Console.readInteger("Select desired Quantity");

            theController.addProductShoppingCart(product,quantity);
        } while (Console.readBoolean("Do you wish to add more products? Y/N"));
    }

    @Override
    public String headline() {
        return "Add product to shopping cart - Customer \n";
    }

    /**
     * Shows the product catalog
     * @param list the list of products to be shown
     */
    private void showProducts(ArrayList<Product> list){
        System.out.println(String.format(" %s %3s %-30s %20s %-15s %20s %-15s %20s %s \n" +
                        "==============================================================================================================================================================================="
                ,"PRODUCT CODE","|","SHORT DESCRIPTION","|","BRAND","|","CATEGORY","|","PRICE"));

        for(Product p : list) printer.visit(p);

        System.out.println("===============================================================================================================================================================================");
    }
}
