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
public class PostalAddress implements ValueObject, Comparable<PostalAddress> {

    private static final long serialVersionUID = 1L;

    /**
     * The Postal Address
     */
    private String address;

    /**
     * The Type of Address (Billing/Shipping)
     */
    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    /**
     * Constructor for PostalAddress object
     * @param address the postal address
     * @param addressType the type of address
     */
    public PostalAddress(String address, AddressType addressType){
        Preconditions.noneNull(address, addressType);

        if(!Pattern.matches("[a-zA-Z0-9 ]+",address)){
            throw new IllegalArgumentException("Invalid address!");
        }

        this.address=address;
        this.addressType=addressType;
    }

    /**
     * empty constructor for ORM
     */
    protected PostalAddress(){
        //for ORM
    }

    /**
     * Creates a PostalAddress object
     * @param address the postal address
     * @param addressType the type of address
     * @return a new PostalAddress object
     */
    public static PostalAddress valueOf(String address, AddressType addressType){
        return new PostalAddress(address,addressType);
    }

    /**
     * checks if 2 weights are the same taking into account the measuring unit
     * @param o the other object
     * @return true if they are equal and false if they arent
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PostalAddress)) {
            return false;
        }

        final PostalAddress other = (PostalAddress) o;
        return address.equals(other.address);
    }

    /**
     *
     * @return transforms this object to string form
     */
    @Override
    public String toString(){
        return String.format("Address: %s\nAddress Type: %s", address, addressType);
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(address).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(PostalAddress o) {
        return address.compareTo(o.address);
    }
}