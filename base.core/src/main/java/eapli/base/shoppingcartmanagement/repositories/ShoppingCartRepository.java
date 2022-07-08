package eapli.base.shoppingcartmanagement.repositories;

import eapli.base.shoppingcartmanagement.domain.ShoppingCart;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Optional;

public interface ShoppingCartRepository extends DomainRepository<Long,ShoppingCart> {

    public Optional<ShoppingCart> findBySystemUser(SystemUser systemUser);
}
