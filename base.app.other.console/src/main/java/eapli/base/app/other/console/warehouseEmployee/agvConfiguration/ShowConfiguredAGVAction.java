package eapli.base.app.other.console.warehouseEmployee.agvConfiguration;

import eapli.framework.actions.Action;

public class ShowConfiguredAGVAction implements Action {
    @Override
    public boolean execute() {
        return new ShowConfiguredAGVUI().show();
    }
}
