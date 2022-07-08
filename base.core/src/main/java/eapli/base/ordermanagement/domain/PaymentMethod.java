package eapli.base.ordermanagement.domain;

public enum PaymentMethod {
    PAYPAL("Paypal");

    final private String designation;

    PaymentMethod(String designation) {
        this.designation = designation;
    }

    PaymentMethod value(String designation) {
        for (PaymentMethod payment: values())
            if (payment.designation.equalsIgnoreCase(designation))
                return payment;
        return null;
    }
}