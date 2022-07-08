package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.shoppingcartmanagement.domain.ShoppingCart;
import eapli.base.shoppingcartmanagement.repositories.ShoppingCartRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.Optional;

public class JpaShoppingCartRepository extends JpaAutoTxRepository<ShoppingCart,Long,Long> implements ShoppingCartRepository {

    public JpaShoppingCartRepository(final TransactionalContext autoTx){ super(autoTx,"id");}


    /**
     * creates a new shopping cart repository
     */
    public JpaShoppingCartRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    @Override
    public Optional<ShoppingCart> findBySystemUser(SystemUser systemUser) {
        final TypedQuery<ShoppingCart> query= entityManager().createQuery("SELECT sc FROM ShoppingCart sc " +
                "WHERE sc.customer.systemUser = :systemUser",ShoppingCart.class).setParameter("systemUser",systemUser);
        return Optional.ofNullable(query.getSingleResult());
    }




}
