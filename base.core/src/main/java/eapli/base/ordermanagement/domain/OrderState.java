package eapli.base.ordermanagement.domain;

import eapli.base.common.domain.RegisterTime;
import eapli.framework.domain.model.ValueObject;

import javax.persistence.*;
import java.util.Calendar;

@Embeddable
public class OrderState implements ValueObject, Comparable<OrderState> {
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar registerTime;

    @Enumerated(EnumType.STRING)
    private State state;

    public enum State {
        REGISTERED("Registered"),
        PAYMENT_PENDING("Payment pending"),
        TO_BE_PREPARED("To be prepared"),
        BEING_PREPARED("Being prepared on the warehouse"),
        READY_FOR_PACKAGING("Ready for packaging"),
        READY_FOR_DISPATCHING("Ready for carrier dispatching"),
        DISPATCHED("Dispatched"),
        DELIVERED_BY_CARRIER("Delivered by carrier"),
        RECEIVED_BY_CUSTOMER("Received by customer");

        private final String state;

        State(String state) {
            this.state = state;
        }

        @Override
        public String toString() {
            return this.state;
        }
    }

    public OrderState(State state, RegisterTime date) {
        setState(state);
        setRegisterTime(date);
    }

    protected OrderState() {
        //for ORM only
    }

    private void setState(State state) {
        if (state == null)
            throw new IllegalArgumentException("Order state must be not null.");
        this.state = state;
    }

    public State getState() {
        return this.state;
    }

    public RegisterTime getRegisterTime() {
        return RegisterTime.valueOf(registerTime);
    }

    private void setRegisterTime(Calendar date) {
        if (date == null)
            throw new IllegalArgumentException("Order state alteration date must be not null.");
        this.registerTime = date;
    }

    private void setRegisterTime(RegisterTime date) {
        if (date == null)
            throw new IllegalArgumentException("Order state alteration date must be not null.");
        this.registerTime = date.value();
    }

    /**
     * Checks if given state directly succeeds current state in the order defined by enum class State
     *
     * @param other a given State
     * @return true if given state is immediately after current one, false if otherwise
     */
    public boolean checkStateSequence(State other) {
        return other.ordinal() - this.state.ordinal() == 1;
    }

    @Override
    public int compareTo(OrderState o) {
        return registerTime.compareTo(o.registerTime);
    }
}
