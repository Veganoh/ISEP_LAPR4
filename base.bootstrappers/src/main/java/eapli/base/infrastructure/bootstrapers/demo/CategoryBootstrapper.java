package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.productcategorymanagement.application.RegisterCategoryController;
import eapli.base.productcategorymanagement.domain.ProductCategory;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategoryBootstrapper implements Action  {
    private static final Logger LOGGER = LoggerFactory.getLogger(
            CategoryBootstrapper.class);

    private final RegisterCategoryController registerCategoryController = new RegisterCategoryController();

    @Override
    public boolean execute() {
        registerCategory("Beauty", "Beauty related products", (float) 1.30);
        registerCategory("Technology", "Technology related products", (float) 1.70);
        registerCategory("Household", "Household related products", (float) 1.50);

        return true;
    }

    private ProductCategory registerCategory(final String alphaCode, final String description, final float tax) {
        ProductCategory category = null;

        try {
            category = registerCategoryController.register(alphaCode, description, tax);
        } catch (final IllegalArgumentException e) {
            LOGGER.warn("Assuming something is wrong with the Category Data (activate trace log for details)");
            LOGGER.trace("Assuming wrong data", e);
        }

        return category;
    }
}
