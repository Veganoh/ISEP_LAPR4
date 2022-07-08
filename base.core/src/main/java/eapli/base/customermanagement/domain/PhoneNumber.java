package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
public class PhoneNumber implements ValueObject, Comparable<PhoneNumber> {

    private static final long serialVersionUID = 1L;

    /**
     * The phone number dialing code
     */
    private String dialingCode;

    /**
     * The phone number
     */
    private String phoneNumber;

    /**
     * Constructor for Phone Number object
     * @param dialingCode the phone number dialing code
     * @param number the phone number
     */
    public PhoneNumber(String dialingCode, String number){
        Preconditions.noneNull(dialingCode, number);

        if(!Pattern.matches("[+]351",dialingCode)){
            throw new IllegalArgumentException("Invalid dialing code");
        }
        if(!Pattern.matches("[0-9]{9}",number)){
            throw new IllegalArgumentException("Invalid phone number");
        }

        this.dialingCode = dialingCode;
        this.phoneNumber = number;
    }

    /**
     * empty constructor for ORM
     */
    protected PhoneNumber(){
        //for ORM
    }

    /**
     * creates a new phone number object
     * @param dialingCode the phone number dialing code
     * @param number the phone number
     * @return a new Phone Number object
     */
    public static PhoneNumber valueOf(String dialingCode, String number){
        return new PhoneNumber(dialingCode,number);
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
        if (!(o instanceof PhoneNumber)) {
            return false;
        }

        final PhoneNumber other = (PhoneNumber) o;
        return dialingCode.equals(other.dialingCode) && phoneNumber.equals(other.phoneNumber);
    }

    /**
     * @return transforms this object to string form
     */
    @Override
    public String toString() {
        return String.format("Dialing Code: %s\n Phone Number: %s", dialingCode, phoneNumber);
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(phoneNumber).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(PhoneNumber o) {
        return phoneNumber.compareTo(o.phoneNumber);
    }
}
