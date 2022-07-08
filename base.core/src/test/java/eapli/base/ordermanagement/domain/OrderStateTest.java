package eapli.base.ordermanagement.domain;

import eapli.base.common.domain.RegisterTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderStateTest {
    OrderState state1 = new OrderState(OrderState.State.REGISTERED, RegisterTime.now());
    OrderState state2 = new OrderState(OrderState.State.PAYMENT_PENDING, RegisterTime.valueOf("2022-04-20 15:57:26"));

    @Test
    void testOrderState() {
        assertNotNull(state1);
        assertNotNull(state2);

        assertTrue(state1.compareTo(state2) > 0);

        assertThrows(IllegalArgumentException.class, () -> {
           new OrderState(null, RegisterTime.now());
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new OrderState(OrderState.State.REGISTERED, null);
        });
    }

    @Test
    void testCheckStateSequence() {
        assertTrue(state1.checkStateSequence(state2.getState()));
        assertFalse(state2.checkStateSequence(state1.getState()));
    }
}