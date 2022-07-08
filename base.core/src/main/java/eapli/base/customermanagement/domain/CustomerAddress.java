package eapli.base.customermanagement.domain;

import eapli.base.common.domain.AddressType;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.regex.Pattern;

@Embeddable
public class CustomerAddress implements ValueObject, Comparable<CustomerAddress> {

    private static final long serialVersionUID = 1L;

    private String addressName;

    private String postalAddress;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    public CustomerAddress(String addressName, String postalAddress, AddressType addressType){
        Preconditions.noneNull(addressName,postalAddress, addressType);

        if(!Pattern.matches("[a-zA-Z0-9]+",addressName)){
            throw new IllegalArgumentException("Invalid Address Name");
        }else if(!Pattern.matches("[a-zA-Z0-9 ]+",postalAddress)){
            throw new IllegalArgumentException("Invalid Postal Address");
        }

        this.addressName=addressName;
        this.postalAddress=postalAddress;
        this.addressType=addressType;
    }

    protected CustomerAddress(){
        //for ORM
    }

    public static CustomerAddress valueOf(String addressName, String postalAddress, AddressType addressType){
        return new CustomerAddress(addressName,postalAddress,addressType);
    }

    public PostalAddress convertAddress(){
        return new PostalAddress(postalAddress,addressType);
    }

    /**
     * Return true if both object are equal or false otherwise
     * @param o the other object
     * @return true if both object are equal or false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomerAddress)) {
            return false;
        }

        final CustomerAddress other = (CustomerAddress) o;
        return postalAddress.equals(other.postalAddress) && addressName.equals(other.addressName) && addressType.equals(other.addressType);
    }

    @Override
    public String toString(){
        return String.format("Address Name: %s\nPostal Address: %s\nAddress Type: %s", addressName, postalAddress, addressType);
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(addressName).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(CustomerAddress o) {
        return addressName.compareTo(o.addressName);
    }

    public AddressType type() {
        return addressType;
    }

    public boolean isOf(AddressType type) {
        return this.addressType.equals(type);
    }

    public String name() {
        return addressName;
    }
}
