package eapli.base.app.user.console.presentation.clientuser;

import eapli.framework.actions.Action;

public class ShowOpenOrdersAction implements Action {
    @Override
    public boolean execute(){ return new ShowOpenOrdersUI().show();}
}
