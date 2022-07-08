package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.warehousemanagement.domain.WarehouseEmployee;
import eapli.base.warehousemanagement.repositories.WarehouseEmployeeRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaWarehouseEmployeeRepository extends JpaAutoTxRepository<WarehouseEmployee, Long, Long> implements WarehouseEmployeeRepository {
    /**
     * creates a new warehouse employee repository
     * @param autoTx the transactional context to enroll
     */
    public JpaWarehouseEmployeeRepository(final TransactionalContext autoTx) {
        super(autoTx, "identifier");
    }

    /**
     * creates a new warehouse employee repository
     */
    public JpaWarehouseEmployeeRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "identifier");
    }
}
