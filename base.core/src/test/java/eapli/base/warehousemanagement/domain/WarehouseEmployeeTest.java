package eapli.base.warehousemanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WarehouseEmployeeTest {

    @Test
    public void validConstructorTest(){
       new WarehouseEmployee();
    }

    @Test
    public void emptyConstructorTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new WarehouseEmployee(null,null));
        String expectedMessage = ("At least one argument is null");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

}