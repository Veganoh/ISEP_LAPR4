package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.bootstrapers.TestDataConstants;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.application.AddUserController;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.WarehouseEmployee;
import eapli.base.warehousemanagement.repositories.WarehouseEmployeeRepository;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Name;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class WarehouseEmployeeUserBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(
            ClientUserBootstrapper.class);

    private final AddUserController addUserController = new AddUserController();
    private final WarehouseEmployeeRepository repo = PersistenceContext.repositories().WarehouseEmployee();

    @Override
    public boolean execute() {
        addUser(TestDataConstants.USER_WAREHOUSE_EMP1, TestDataConstants.PASSWORD1, "Oliver", "Tree", "olivertree@warehouse.com");
        addUser("warehouseEmp2", "Password1", "Joshua", "Weissman", "joshuaweiss@warehouse.com");
        return true;
    }

    private WarehouseEmployee addUser(final String username, final String password,
                                        final String firstName, final String lastName, final String email) {
        WarehouseEmployee warehouseEmployee = null;

        try {
            Set<Role> roles = new HashSet<>();
            roles.add(BaseRoles.WAREHOUSE_EMPLOYEE);

            SystemUser warehouseEmployeeSU = addUserController.addUser(username, password, firstName, lastName, email, roles);

            warehouseEmployee = new WarehouseEmployee(warehouseEmployeeSU, Name.valueOf(firstName, lastName));
            repo.save(warehouseEmployee);
        } catch (final ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", username);
            LOGGER.trace("Assuming existing record", e);
        }
        return warehouseEmployee;
    }
}
