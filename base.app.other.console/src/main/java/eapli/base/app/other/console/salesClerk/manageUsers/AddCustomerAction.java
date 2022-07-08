package eapli.base.app.other.console.salesClerk.manageUsers;

import eapli.framework.actions.Action;

public class AddCustomerAction implements Action {

    @Override
    public boolean execute() {
        return new AddCustomerUI().show();
    }
}
