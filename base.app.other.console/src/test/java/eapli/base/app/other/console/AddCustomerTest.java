package eapli.base.app.other.console;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.junit.jupiter.api.Test;

public class AddCustomerTest {

    @Test
    public void addCustomerTest(){
        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(), new PlainTextEncoder());

        AuthenticationService authz = AuthzRegistry.authenticationService()  ;
        authz.authenticate("poweruser", "poweruserA1", BaseRoles.POWER_USER);

        //AddCustomerController controller = new AddCustomerController();

        String identifier = "1";
        String email = "abc@gmail.com";
        String name = "Name";
        String dialingCode = "+351";
        String phoneNumber = "123456789";
        String vatNumber = "1";
        //SystemUser systemUser = new SystemUser();
    }
}
