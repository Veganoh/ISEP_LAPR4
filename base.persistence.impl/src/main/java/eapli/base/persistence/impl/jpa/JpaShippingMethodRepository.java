package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.shipmentmanagement.domain.ShippingMethod;
import eapli.base.shipmentmanagement.repositories.ShippingMethodRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaShippingMethodRepository extends JpaAutoTxRepository<ShippingMethod, Long, Long>  implements ShippingMethodRepository {

    /**
     * creates a new product repository
     * @param autoTx the transactional context to enroll
     */
    public JpaShippingMethodRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    /**
     * creates a new product repository
     */
    public JpaShippingMethodRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }
}
