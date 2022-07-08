package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.shipmentmanagement.application.AddShippingMethodController;
import eapli.base.shipmentmanagement.domain.ShippingMethod;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShippingMethodBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(
            ShippingMethodBootstrapper.class);

    private final AddShippingMethodController controller = new AddShippingMethodController();

    @Override
    public boolean execute() {
        registerShippingMethod("STANDARD", "CTT", "0.45 EUR", "G", 0.23);
        registerShippingMethod("BLUE", "CTT", "1.30 EUR", "G", 0.23);
        registerShippingMethod("GREEN", "CTT", "1.45 EUR", "KG", 0.23);

        return true;
    }

    private ShippingMethod registerShippingMethod(final String designation, final String carrier, final String price, final String unit, final double tax) {
        ShippingMethod shipping = null;

        try {
            shipping = controller.register(designation, carrier, price, unit, tax);
        } catch (final IllegalArgumentException e) {
            LOGGER.warn("Assuming something is wrong with the Category Data (activate trace log for details)");
            LOGGER.trace("Assuming wrong data", e);
        }

        return shipping;
    }

}
