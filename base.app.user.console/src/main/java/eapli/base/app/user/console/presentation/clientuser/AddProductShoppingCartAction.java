package eapli.base.app.user.console.presentation.clientuser;

import eapli.framework.actions.Action;

public class AddProductShoppingCartAction implements Action {
    @Override
    public boolean execute(){ return new AddProductShoppingCartUI().show();}
}
