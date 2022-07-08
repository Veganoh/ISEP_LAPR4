package eapli.base.app.other.console.warehouseEmployee.agvTasks;

import eapli.base.agvmanagement.application.ShowAvailableAGVController;
import eapli.base.agvmanagement.domain.AGV;
import eapli.base.ordermanagement.presentation.OrderPrinter;
import eapli.base.app.other.console.warehouseEmployee.agvConfiguration.AGVPrinter;
import eapli.base.ordermanagement.domain.ShowOrdersService;
import eapli.base.ordermanagement.domain.Order;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class ShowToBePreparedOrdersUI extends AbstractUI{
    /**
     * The UI's controller
     */
    ShowOrdersService ordersController = new ShowOrdersService();
    ShowAvailableAGVController agvController = new ShowAvailableAGVController();

    /**
     * Main method of the UI
     * @return shows the steps to add a new product
     */
    @Override
    protected boolean doShow() {
        try{
            SelectWidget<Order> ordersWidget = new SelectWidget<>
                    ("Select an order to be prepared:",
                            ordersController.listToBePreparedOrders(), new OrderPrinter());
            ordersWidget.show();
            final Order order = ordersWidget.selectedElement();

            SelectWidget<AGV> agvWidget = new SelectWidget<>
                    ("Select an available AGV:\n Please note only AGV with carrying capacity are displayed",
                            agvController.listCapableAGV(order), new AGVPrinter());
            agvWidget.show();
            final AGV agv = agvWidget.selectedElement();

            agvController.assignTask(order, agv);

            System.out.println("Order state updated successfully ");
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
        return "See orders to be prepared";
    }
}
