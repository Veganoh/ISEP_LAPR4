package eapli.base.app.other.console;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.application.LoadWarehouseMapController;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.junit.jupiter.api.Test;

public class LoadWarehouseMapTest {

    @Test
    void registerProductTest() {
        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(), new PlainTextEncoder());

        AuthenticationService authz = AuthzRegistry.authenticationService()  ;
        authz.authenticate("poweruser", "poweruserA1", BaseRoles.POWER_USER);

        LoadWarehouseMapController controller = new LoadWarehouseMapController();

        //controller.load();
    }
}
