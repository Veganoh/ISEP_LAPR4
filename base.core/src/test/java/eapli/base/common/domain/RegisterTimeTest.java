package eapli.base.common.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTimeTest {

    @Test
    public void TestRegisterTime() {
        RegisterTime date1 = RegisterTime.valueOf("2000-01-01 12:00:00");
        RegisterTime date2 = RegisterTime.valueOf("2020-02-02 22:00:00");
        RegisterTime date3 = RegisterTime.valueOf("2000-01-01 12:00:00");

        assertNotNull(date1);
        assertNotNull(date2);
        assertEquals(date1, date3);

        assertThrows(IllegalArgumentException.class, () -> {
           RegisterTime date4 = RegisterTime.valueOf("2030-01-01 14:00:00");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            RegisterTime date5 = RegisterTime.valueOf("01/01/2020 10:00");
        });
    }
}