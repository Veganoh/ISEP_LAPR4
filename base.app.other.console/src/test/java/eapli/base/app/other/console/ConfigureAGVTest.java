package eapli.base.app.other.console;

import eapli.base.agvdockmanagement.domain.AGVDock;
import eapli.base.agvmanagement.application.ConfigureAGVController;
import eapli.base.agvmanagermanagement.domain.Accessibility;
import eapli.base.agvmanagermanagement.domain.Coordinates;
import eapli.base.agvmanagermanagement.domain.Square;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.junit.jupiter.api.Test;

public class ConfigureAGVTest {


    @Test
    void configureAGVTest(){
        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(), new PlainTextEncoder());

        AuthenticationService authz = AuthzRegistry.authenticationService()  ;
        authz.authenticate("poweruser", "poweruserA1", BaseRoles.POWER_USER);

        ConfigureAGVController controller = new ConfigureAGVController();

        String id = "123";
        String model = "AB123";
        String description = "This is a random string";
        double weightCapacity = 200000;
        double volumeCapacity = 2000;
        int batteryStatus = 120;
        Coordinates coordinates1 = Coordinates.valueOf(10, 15);
        Coordinates coordinates2 = Coordinates.valueOf(11, 15);
        Coordinates coordinates3 = Coordinates.valueOf(10, 16);
        Square accessSquare = Square.valueOf(coordinates1, null, null, Accessibility.Up);
        Square square1 = Square.valueOf(coordinates2, null, null, null);
        Square square2 = Square.valueOf(coordinates3, null, null, null);

        AGVDock dock = new AGVDock(accessSquare, square1, square2);

        //controller.configureAGV(id,model,description,weightCapacity,volumeCapacity,dock,batteryStatus);
        controller.listAvailableDocks();

       // PersistenceContext.repositories().AGVS.remove(controller.saveAGV());
    }
}
