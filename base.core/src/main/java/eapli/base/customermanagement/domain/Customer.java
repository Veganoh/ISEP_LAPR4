package eapli.base.customermanagement.domain;

import eapli.base.common.domain.AddressType;
import eapli.base.shoppingcartmanagement.domain.ShoppingCart;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Customer implements AggregateRoot<CustomerIdentifier> {

    @EmbeddedId
    private CustomerIdentifier id;

    @Embedded
    private Email email;

    @Embedded
    private Name name;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    private VATNumber vatNumber;

    @Embedded
    private Gender gender;

    @Embedded
    private BirthDate birthDate;

    @ElementCollection
    private List<CustomerAddress> customerAddresses;

    @OneToOne(cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;

    @OneToMany
    private List<Questionnaire> questionnaireList = new ArrayList<>();

    @OneToOne()
    private SystemUser systemUser;

    public Customer(CustomerIdentifier id, Email email, Name name, PhoneNumber phoneNumber, VATNumber vatNumber, Gender gender,
                    BirthDate birthDate, List<CustomerAddress> addresses, SystemUser systemUser){
        Preconditions.noneNull(id,email,name,phoneNumber,vatNumber);

        //Mandatory
        this.id = id;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.vatNumber = vatNumber;
        this.systemUser = systemUser;

        //Optional
        this.customerAddresses = addresses;
        this.gender = gender;
        this.birthDate = birthDate;

        //Generated
        this.shoppingCart = new ShoppingCart(this);
    }


    public Customer() {
        //For ORM only
    }

    public boolean addCustomerAddress(CustomerAddress customerAddress){
        if(!customerAddresses.contains(customerAddress)){
            return customerAddresses.add(customerAddress);
        }else{
            throw new IllegalArgumentException("Address is already associated with current costumer");
        }
    }

    public boolean addQuestionnaires(Questionnaire questionnaire){
        if(!questionnaireList.contains(questionnaire)){
            return questionnaireList.add(questionnaire);
        }else{
            throw new IllegalArgumentException("Questionnaire is already associated with current costumer");
        }
    }

    /**
     * the objects hashcode
     *
     * @return the hashcode of the object
     */
    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer that = (Customer) o;
        return sameAs(that);
    }

    @Override
    public CustomerIdentifier identity() {
        return this.id;
    }

    public CustomerIdentifier id() {
        return id;
    }

    public Email email() {
        return email;
    }

    public Name name() {
        return name;
    }

    public int age(){
        return birthDate.age();
    }

    public SystemUser systemUser(){
        return systemUser;
    }

    @Override
    public String toString(){
        return id.toString();
    }

    public List<CustomerAddress> addresses(AddressType type) {
        return customerAddresses.stream()
                .filter(a -> a.isOf(type))
                .collect(Collectors.toList());
    }
}
