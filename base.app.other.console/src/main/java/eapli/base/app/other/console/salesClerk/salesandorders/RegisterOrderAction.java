package eapli.base.app.other.console.salesClerk.salesandorders;

import eapli.framework.actions.Action;

public class RegisterOrderAction implements Action {

    @Override
    public boolean execute() {
        return new RegisterOrderUI().show();
    }
}
