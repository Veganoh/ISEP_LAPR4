package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.common.domain.AddressType;
import eapli.base.customermanagement.application.AddCustomerController;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.infrastructure.bootstrapers.TestDataConstants;
import eapli.base.usermanagement.application.AddUserController;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.HashSet;
import java.util.Set;

public class CustomerBootstrapper implements Action {
    private AddCustomerController addCustomerController = new AddCustomerController();

    private final AddUserController addUserController = new AddUserController();

    @Override
    public boolean execute() {
        addCustomer("C0001", "alicealberd@email.com", "Alice Alberd", "+351", "946824682",
                "644462955", 1990, 9, 19, "Female", "aliceA", TestDataConstants.PASSWORD1);
        addCustomerController.addCustomerAddress("Home", "Boavista Road", AddressType.SHIPPING);
        addCustomerController.addCustomerAddress("Home", "Boavista Road", AddressType.BILLING);
        addCustomerController.addCustomerAddress("Work", "Republica Square", AddressType.SHIPPING);
        addCustomerController.saveCustomer();

        addCustomer("C0002", "beatrixpotter@email.com", "Beatrix Potter", "+351", "946835285",
                "365925957", 1987, 8, 7, "Female", "beatrixP", TestDataConstants.PASSWORD1);
        addCustomerController.addCustomerAddress("Home", "St Catarina Road", AddressType.SHIPPING);
        addCustomerController.addCustomerAddress("Home", "St Catarina Road", AddressType.BILLING);
        addCustomerController.addCustomerAddress("Work", "Republica Square", AddressType.BILLING);
        addCustomerController.saveCustomer();

        addCustomer("C0003", "charlesdavidson@email.com", "Charles Davidson", "+351", "965477362",
                "879256195", 2001, 1, 1, "Male", "charlesD", TestDataConstants.PASSWORD1);
        addCustomerController.addCustomerAddress("Event", "Aliados Sq", AddressType.BILLING);
        addCustomerController.addCustomerAddress("Event", "Aliados Sq", AddressType.SHIPPING);
        addCustomerController.saveCustomer();

        return true;
    }

    public void addCustomer(final String identifier, final String email, final String name,
                            final String dialingCode, final String phoneNumber, final String vatNumber,
                            final int year, final int month, final int day, final String gender,
                            final String username, final String password) {

        addCustomerController = new AddCustomerController();
        Customer customer = null;

        try {
            Set<Role> roles = new HashSet<>();
            roles.add(BaseRoles.CLIENT_USER);
            SystemUser customerSU = addUserController.addUser(username, password, name.split(" ")[0], name.split(" ")[name.split(" ").length - 1], email, roles);

            addCustomerController.addCustomer(identifier, email, name, dialingCode, phoneNumber, vatNumber, customerSU, year, month, day, gender);
        } catch (final Exception e) {
            //LOGGER.warn("Assuming something is wrong with the Product Data (activate trace log for details)");
            System.out.println(e.getMessage());
            //LOGGER.trace("Assuming wrong data", e);
        }

    }
}
