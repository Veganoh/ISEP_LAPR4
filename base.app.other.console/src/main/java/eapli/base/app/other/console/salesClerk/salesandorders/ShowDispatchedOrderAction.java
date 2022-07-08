package eapli.base.app.other.console.salesClerk.salesandorders;

import eapli.framework.actions.Action;

public class ShowDispatchedOrderAction implements Action {

    @Override
    public boolean execute() {
        return new ShowDispatchedOrdersUI().show();
    }
}
