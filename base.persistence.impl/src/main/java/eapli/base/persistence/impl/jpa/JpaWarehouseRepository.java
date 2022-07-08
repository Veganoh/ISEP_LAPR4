package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.domain.WarehouseId;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaWarehouseRepository extends JpaAutoTxRepository<Warehouse, WarehouseId, Long> implements WarehouseRepository {

    /**
     * creates a new product repository
     *
     * @param autoTx the transactional context to enroll
     */
    public JpaWarehouseRepository(final TransactionalContext autoTx) {
        super(autoTx, "identifier");
    }

    /**
     * creates a new product repository
     */
    public JpaWarehouseRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "identifier");
    }
}
