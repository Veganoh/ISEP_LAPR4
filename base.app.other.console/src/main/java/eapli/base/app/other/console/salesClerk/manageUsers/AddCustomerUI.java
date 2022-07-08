package eapli.base.app.other.console.salesClerk.manageUsers;

import eapli.base.common.domain.AddressType;
import eapli.base.customermanagement.application.AddCustomerController;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class AddCustomerUI extends AbstractUI {
    /**
     * The UI's controller
     */
    AddCustomerController controller = new AddCustomerController();

    /**
     * Main method of the UI
     * @return shows the steps to add a new product
     */
    @Override
    protected boolean doShow() {
        try {
            final String identifier = Console.readLine("Customer's Identifier:");
            final String name = Console.readLine("First Name");
            final String lastName = Console.readLine("Last Name");
            final String email = Console.readLine("Customer's Email:");
            final String dialingCode = Console.readLine("Customer's Dialing Code");
            final String phoneNumber = Console.readLine("Customer's Phone Number");
            final String vatNumber = Console.readLine("Customer's VAT Number");

            final String birthDate = "Would you like to add a birth date? (Y/N)";
            int month = 1;
            int year = 1960;
            int day = 1;
            if (Console.readBoolean(birthDate)) {
                year = Console.readInteger("Year of birth:");
                month = Console.readInteger("Month of birth:");
                day = Console.readInteger("Day of birth:");
            }

            final String gender = "Would you like to add a gender? (Y/N)";
            String genderChose = null;
            if (Console.readBoolean(gender)) {
                genderChose = Console.readLine("Customer's Gender");
            }


            final String username = Console.readLine("Customer's username");
            final String password = Console.readLine("Customer's password");
            Set<Role> role = new HashSet<>();
            role.add(BaseRoles.CLIENT_USER);
            final String createdOn = "Would you like to add date of customer account creation?(Y/N)";
            Calendar date = null;
            if (Console.readBoolean(createdOn)) {
                date = Calendar.getInstance();
            }
            SystemUser systemUser = controller.addUser(username, password, name, lastName, email, role, date);
            controller.addCustomer(identifier, email, name, dialingCode, phoneNumber, vatNumber, systemUser, year, month, day, genderChose);
            addCustomerAddressesLoop();
            controller.saveCustomer();
            System.out.println("\nCustomer Successfully added\n");
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("That alpha code is already in use.");
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private void addCustomerAddressesLoop(){
        String add = "Add customer address? (Y/N)";
        while(Console.readBoolean(add)){
            final String addressName = Console.readLine("Customer's Address Name:");
            final String postalAddress = Console.readLine("Customer's Postal Address:");
            SelectWidget<AddressType> standardWidget = new SelectWidget<>("Select a type of address", controller.getAddressType());
            standardWidget.show();
            final AddressType standard = standardWidget.selectedElement();
            controller.addCustomerAddress(addressName,postalAddress,standard);
            add ="Continue adding addresses? (Y/N)";
        }
    }

    /**
     * The UI headline
     * @return The UI headline
     */
    @Override
    public String headline() {
        return "Add a new Customer";
    }
}
