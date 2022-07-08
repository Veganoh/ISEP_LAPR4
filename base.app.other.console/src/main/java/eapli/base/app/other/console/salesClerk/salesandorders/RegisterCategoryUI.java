package eapli.base.app.other.console.salesClerk.salesandorders;

import eapli.base.productcategorymanagement.application.RegisterCategoryController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class RegisterCategoryUI extends AbstractUI {

    /**
     * The UI's controller
     */
    private final RegisterCategoryController theController = new RegisterCategoryController();

    /**
     * Main method of the UI
     * @return shows the steps to add a new category
     */
    @Override
    protected boolean doShow() {
        try {
            final String alphaCode = Console.readLine("Alpha Code");
            final String description = Console.readLine("Description");
            final float tax = (float) Console.readDouble("Tax rate (Optional)");


            this.theController.register(alphaCode, description, tax);
            System.out.println("Category was successfully registered");

        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("That alpha code is already in use.");
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    /**
     * The UI headline
     * @return The UI headline
     */
    @Override
    public String headline() {
        return "Add a new Product Category";
    }
}
