package eapli.base.app.user.console.presentation.clientuser;
import eapli.base.shoppingcartmanagement.application.ShowShoppingCartController;
import eapli.base.shoppingcartmanagement.domain.ShoppingCart;
import eapli.framework.presentation.console.AbstractUI;

public class ShowShoppingCartUI extends AbstractUI {

    /**
     * The UI's controller
     */
    private final ShowShoppingCartController theController = new ShowShoppingCartController();

    private final ShoppingCartPrinter printer = new ShoppingCartPrinter();


    @Override
    protected boolean doShow() {

        ShoppingCart sp = theController.getShoppingCart();
        printer.visit(sp);

        return false;
    }

    @Override
    public String headline() {
        return "See shopping cart - Customer \n";
    }
}
