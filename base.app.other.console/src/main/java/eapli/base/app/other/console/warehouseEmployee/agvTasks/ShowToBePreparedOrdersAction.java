package eapli.base.app.other.console.warehouseEmployee.agvTasks;

import eapli.base.app.other.console.warehouseEmployee.salesAndOrders.ShowPreparedOrdersUI;
import eapli.framework.actions.Action;

public class ShowToBePreparedOrdersAction implements Action {

    @Override
    public boolean execute() {
        return new ShowToBePreparedOrdersUI().show();
    }
}
