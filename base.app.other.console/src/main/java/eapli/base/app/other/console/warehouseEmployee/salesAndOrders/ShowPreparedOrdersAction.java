package eapli.base.app.other.console.warehouseEmployee.salesAndOrders;

import eapli.framework.actions.Action;

public class ShowPreparedOrdersAction implements Action {

    @Override
    public boolean execute() {
        return new ShowPreparedOrdersUI().show();
    }
}
