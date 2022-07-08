package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.bootstrapers.TestDataConstants;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.SalesClerk;
import eapli.base.ordermanagement.repositories.SalesClerkRepository;
import eapli.base.usermanagement.application.AddUserController;
import eapli.base.usermanagement.domain.BaseRoles;
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

public class SalesClerkUserBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(
            ClientUserBootstrapper.class);

    private final AddUserController addUserController = new AddUserController();
    private final SalesClerkRepository repo = PersistenceContext.repositories().SalesClerk();

    @Override
    public boolean execute() {
        addUser(TestDataConstants.USER_CLERK1, TestDataConstants.PASSWORD1, "Jack", "Met", "jackmet@clerk.com");
        addUser("clerk2", "Password1", "Adam", "Met", "adammet@clerk.com");
        return true;
    }

    private SalesClerk addUser(final String username, final String password, final String firstName, final String lastName, final String email) {
        SalesClerk clerk = null;

        try {
            Set<Role> roles = new HashSet<>();
            roles.add(BaseRoles.SALES_CLERK);

            SystemUser clerkSU = addUserController.addUser(username, password, firstName, lastName, email, roles);

            clerk = new SalesClerk(clerkSU, Name.valueOf(firstName, lastName));
            repo.save(clerk);
        } catch (final ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", username);
            LOGGER.trace("Assuming existing record", e);
        }
        return clerk;
    }
}
