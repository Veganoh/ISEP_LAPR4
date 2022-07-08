package eapli.base.app.other.console.salesClerk.salesandorders;

import eapli.base.ordermanagement.presentation.OrderPrinter;
import eapli.base.ordermanagement.domain.ShowOrdersService;
import eapli.base.ordermanagement.domain.Order;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ShowSavedOrdersUI extends AbstractListUI<Order> {

    ShowOrdersService theController = new ShowOrdersService();

    @Override
    protected Iterable<Order> elements() {
        return theController.listSavedOrders();
    }

    @Override
    protected Visitor<Order> elementPrinter() {
        return new OrderPrinter();
    }

    @Override
    protected String elementName() {
        return "Order";
    }

    @Override
    protected String listHeader() {
        return "Currently Stored Orders";
    }

    @Override
    protected String emptyMessage() {
        return null;
    }

    @Override
    public String headline() {
        return "Show Currently Stored Orders";
    }
}
