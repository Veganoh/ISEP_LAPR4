package eapli.base.app.other.console.salesClerk.salesandorders;

import eapli.base.ordermanagement.presentation.OrderPrinter;
import eapli.base.ordermanagement.domain.ShowOrdersService;
import eapli.base.ordermanagement.domain.Order;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class ShowDispatchedOrdersUI extends AbstractUI {
    /**
     * The UI's controller
     */
    ShowOrdersService controller = new ShowOrdersService();

    /**
     * Main method of the UI
     * @return shows the steps to add a new product
     */
    @Override
    protected boolean doShow() {
        try{
            final String accept2 = "Select an order to update dispatch status";
            SelectWidget<Order> categoryWidget = new SelectWidget<>("Select an Order:", controller.listToBeDeliveredOrders(), new OrderPrinter());
            categoryWidget.show();
            final Order order = categoryWidget.selectedElement();
            controller.updateToDeliveredByCarrier(order);
            System.out.println("Order state updated successfully ");
        }catch(final IntegrityViolationException | ConcurrencyException e) {
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
        return "See orders with current dispatch status";
    }
}
