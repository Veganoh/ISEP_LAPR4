package eapli.base.customermanagement.application;

import eapli.base.common.domain.AddressType;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.CustomerBuilder;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.apache.commons.lang3.EnumUtils;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class AddCustomerController {

    /**
     * Authorization service that is used to make sure only authorized users can use the controller
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * The category repository
     */
    private final CustomerRepository repo = PersistenceContext.repositories().Customer();

    /**
     * Service needed in order to register new system user
     */
    private final UserManagementService userSvc = AuthzRegistry.userService();

    /**
     * The builder used by this controller
     */
    private CustomerBuilder builder = new CustomerBuilder();

    public AddCustomerController(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.ADMIN, BaseRoles.POWER_USER);
    }

    /**
     * Adds main information of the customer
     * @param identifier customer identifier
     * @param email customer email
     * @param name customer name
     * @param dialingCode customer dialing code
     * @param phoneNumber customer phone number
     * @param vatNumber customer vat number
     * @param systemUser customer system user information
     * @param year year of birth
     * @param month month of birth
     * @param day day of birth
     * @param gender customer gender
     */
    public void addCustomer(final String identifier,
                            final String email,
                            final String name,
                            final String dialingCode,
                            final String phoneNumber,
                            final String vatNumber,
                            final SystemUser systemUser,
                            final int year, final int month, final int day,
                            final String gender){
        builder.withData(identifier,email,name,dialingCode,phoneNumber,vatNumber,systemUser);

        if(!(day<1 || month<1 || year<1 || day>31 || month>12)){
            builder.withBirthDate(day,month,year);
        }

        if(gender != null){
            builder.withGender(gender);
        }
    }

    /**
     * adds more addresses to customer
     * @param addressName address name
     * @param postalAddress postal address
     * @param addressType address type
     */
    public void addCustomerAddress(final String addressName,
                                   final String postalAddress,
                                   final AddressType addressType){
        builder.withCustomerAddress(addressName,postalAddress,addressType);
    }

    public List<AddressType> getAddressType(){
        return EnumUtils.getEnumList(AddressType.class);
    }

    public SystemUser addUser(final String username, final String password, final String firstName,
                              final String lastName,
                              final String email, final Set<Role> roles, final Calendar createdOn) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.SALES_CLERK);

        return userSvc.registerNewUser(username, password, firstName, lastName, email, roles,
                createdOn);
    }

    public Customer saveCustomer(){
        Customer customer = builder.build();
        return repo.save(customer);
    }
}
