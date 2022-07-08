package eapli.base.app.other.console.warehouseEmployee.warehouseplant;

import eapli.framework.actions.Action;

public class LoadWarehousePlantAction implements Action {

    @Override
    public boolean execute() {
        return new LoadWarehousePlantUI().show();
    }
}
