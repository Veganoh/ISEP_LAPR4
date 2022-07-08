package eapli.base.ordermanagement.domain;

import eapli.base.common.domain.RegisterTime;
import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class PaymentConfirmation implements ValueObject {

    private static final long serialVersionUID = 1L;

    private boolean operationSuccess;

    private String message;

    @Embedded
    private RegisterTime registerTime;

    /**
     * Constructor for PaymentConfimation object
     *
     * @param operationSuccess the confirmation success/insuccess
     * @param registerTime the date/time of the confirmation
     */
    protected PaymentConfirmation(final boolean operationSuccess, final RegisterTime registerTime, String message)
    {
        this.operationSuccess = operationSuccess;
        setRegisterTime(registerTime);
        setMessage(message);
    }

    /**
     * empty constructor for ORM
     */
    protected PaymentConfirmation() {
        // for ORM
    }

    private void setRegisterTime(RegisterTime date) {
        if (date == null)
            throw new IllegalArgumentException("Payment confirmation date must be not null.");
        this.registerTime = date;
    }

    private void setMessage(String message) {
        if (message == null || message.isEmpty() || message.length() > 512)
            throw new IllegalArgumentException("Payment confirmation text must be 512 chars max.");

        this.message = message;
    }

    public boolean success() {
        return operationSuccess;
    }

    /**
     * Creates an PaymentConfimation object
     *
     * @param operationSuccess the confirmation success/insuccess
     * @param registerTime the date/time of the confirmation
     * @return a new PaymentConfimation object
     */
    public static PaymentConfirmation valueOf(final boolean operationSuccess, final RegisterTime registerTime, final String message) {
        return new PaymentConfirmation(operationSuccess, registerTime, message);
    }

    /**
     * Return true if both object are equal or false otherwise
     *
     * @param o the other object
     * @return true if both object are equal or false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentConfirmation)) {
            return false;
        }

        final PaymentConfirmation other = (PaymentConfirmation) o;
        return registerTime.equals(other.registerTime);
    }

    /**
     * @return transforms this object to string form
     */
    @Override
    public String toString() {
        if (operationSuccess)
            return String.format("%s    Payment confirmed!    %s", registerTime, message);
        else
            return String.format("%s    Payment rejected!    %s", registerTime, message);
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return registerTime.hashCode();
    }

}
