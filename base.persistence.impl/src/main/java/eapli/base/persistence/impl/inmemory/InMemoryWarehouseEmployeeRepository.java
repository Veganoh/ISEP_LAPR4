package eapli.base.persistence.impl.inmemory;

import eapli.base.warehousemanagement.domain.WarehouseEmployee;
import eapli.base.warehousemanagement.repositories.WarehouseEmployeeRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryWarehouseEmployeeRepository extends InMemoryDomainRepository<WarehouseEmployee, Long> implements WarehouseEmployeeRepository {
    /*
     * Initializes the inmemory repository
     */
    static {
        InMemoryInitializer.init();
    }
}
