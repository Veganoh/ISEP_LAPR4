package eapli.base.app.user.console.presentation.clientuser;

import eapli.framework.actions.Action;

public class ShowShoppingCartAction implements Action {
    @Override
    public boolean execute(){ return new ShowShoppingCartUI().show();}
}
