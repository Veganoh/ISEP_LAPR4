package eapli.base.persistence.impl.inmemory;

import eapli.base.shoppingcartmanagement.domain.ShoppingCart;
import eapli.base.shoppingcartmanagement.repositories.ShoppingCartRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryShoppingCart extends InMemoryDomainRepository<ShoppingCart,Long> implements ShoppingCartRepository {

    /*
     * Initializes the inmemory repository
     */
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<ShoppingCart> findBySystemUser(SystemUser systemUser)  {
        return Optional.empty();
    }
}
