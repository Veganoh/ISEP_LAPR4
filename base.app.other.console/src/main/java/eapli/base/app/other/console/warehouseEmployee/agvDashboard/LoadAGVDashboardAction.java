package eapli.base.app.other.console.warehouseEmployee.agvDashboard;

import eapli.base.app.other.console.warehouseEmployee.warehouseplant.LoadWarehousePlantUI;
import eapli.framework.actions.Action;

public class LoadAGVDashboardAction implements Action {

    @Override
    public boolean execute() {
        return new LoadAGVDashboardUI().show();
    }
}
