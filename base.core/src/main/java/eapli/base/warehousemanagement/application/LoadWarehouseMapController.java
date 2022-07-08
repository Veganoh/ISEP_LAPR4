package eapli.base.warehousemanagement.application;

import eapli.base.Application;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.IOException;
import java.util.Iterator;

public class LoadWarehouseMapController {
    /**
     * Authorization service that is used to make sure only authorized users can use the controller
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * The object repository
     */
    private final WarehouseRepository repo = PersistenceContext.repositories().Warehouses();

    public LoadWarehouseMapController(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE, BaseRoles.POWER_USER, BaseRoles.ADMIN);
    }

    public boolean load() throws IOException {
        checkWarehouseExists();

        Warehouse warehouse = new JsonFileParser(Application.settings().getWarehousePlantFilePath()).parse();
        return repo.save(warehouse) != null;
    }

    /**
     * Checks if Warehouse is already registered in the system and deletes it before loading
     */
    private void checkWarehouseExists() {
        Iterator<Warehouse> warehouseIterator = repo.findAll().iterator();

        if (warehouseIterator.hasNext())
            repo.delete(warehouseIterator.next());
    }
}
