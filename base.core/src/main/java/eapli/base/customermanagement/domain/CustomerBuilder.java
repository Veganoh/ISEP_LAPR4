package eapli.base.customermanagement.domain;

import eapli.base.common.domain.AddressType;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CustomerBuilder implements DomainFactory<Customer> {

    /**
     * Mandatory Fields
     */
    private List<CustomerAddress> customerAddressList = new ArrayList<>();
    private CustomerIdentifier customerIdentifier;
    private Email email;
    private Name name;
    private PhoneNumber phoneNumber;
    private VATNumber vatNumber;
    private SystemUser systemUser;

    /**
     * Non-Mandatory Fields
     */
    private BirthDate birthDate;
    private Gender gender;

    public CustomerBuilder withData(final String identifier,
                                    final String email, final String name, final String dialingCode, final String phoneNumber,
                                    final String vatNumber, final SystemUser systemUser){
        withCustomerIdentifier(identifier);
        withEmail(email);
        withName(name);
        withPhoneNumber(dialingCode,phoneNumber);
        withVATNumber(vatNumber);
        withSystemUser(systemUser);
        return this;
    }

    public CustomerBuilder withSystemUser(final SystemUser systemUser){
        this.systemUser = systemUser;
        return this;
    }

    public CustomerBuilder withCustomerAddress(final String addressName, final String postalAddressName, final AddressType addressType){
        customerAddressList.add(CustomerAddress.valueOf(addressName, postalAddressName, addressType));
        return this;
    }

    public CustomerBuilder withCustomerIdentifier(final String identifier){
        this.customerIdentifier = CustomerIdentifier.valueOf(identifier);
        return this;
    }

    public CustomerBuilder withEmail(final String email){
        this.email = Email.valueOf(email);
        return this;
    }

    public CustomerBuilder withName(final String name){
        this.name = Name.valueOf(name);
        return this;
    }

    public CustomerBuilder withPhoneNumber(final String dialingCode, final String phoneNumber){
        this.phoneNumber = PhoneNumber.valueOf(dialingCode,phoneNumber);
        return this;
    }

    public CustomerBuilder withVATNumber(final String vatNumber){
        this.vatNumber = VATNumber.valueOf(vatNumber);
        return this;
    }

    public CustomerBuilder withBirthDate(final int day, final int month, final int year){
       this.birthDate = BirthDate.valueOf(day, month, year);
       return this;
    }

    public CustomerBuilder withGender(final String gender){
        this.gender = Gender.valueOf(gender);
        return this;
    }

    @Override
    public Customer build(){
        Customer customer = new Customer(customerIdentifier,email,name,phoneNumber,vatNumber, gender, birthDate, customerAddressList, systemUser);


        return customer;
    }
}
