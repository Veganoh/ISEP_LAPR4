package eapli.base.shoppingcartmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.shoppingcartmanagement.domain.ShoppingCart;
import eapli.base.shoppingcartmanagement.repositories.ShoppingCartRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class ShowShoppingCartController {

    /**
     * Authorization service that is used to make sure only authorized users can use the controller
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * The Shopping Cart Repository
     */
    private final ShoppingCartRepository repo = PersistenceContext.repositories().ShoppingCarts();

    /**
     * The system user authenticated
     */
    private final SystemUser usr = authz.session().get().authenticatedUser();

    /**
     * Constructs a new ShowShoppingCartController
     */
    public ShowShoppingCartController() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CLIENT_USER);
    }

    /**
     * Returns the shopping cart
     * @return the shopping cart
     */
    public ShoppingCart getShoppingCart() {
        return repo.findBySystemUser(usr).orElseThrow();
    }





}
