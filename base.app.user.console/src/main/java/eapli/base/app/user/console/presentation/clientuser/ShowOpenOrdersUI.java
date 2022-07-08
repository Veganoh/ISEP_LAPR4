package eapli.base.app.user.console.presentation.clientuser;

import eapli.base.ordermanagement.application.ShowOpenOrdersController;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.presentation.OrderPrinter;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Set;

public class ShowOpenOrdersUI extends AbstractUI {

    private final ShowOpenOrdersController theController = new ShowOpenOrdersController();

    private final OrderPrinter printer = new OrderPrinter();

    @Override
    protected boolean doShow() {
        Set<Order> openOrderList = theController.listOpenOrders();
        for (Order o : openOrderList)
            printer.visit(o);

        return true;
    }

    @Override
    public String headline() {
        return "Show Open Orders";
    }
}
