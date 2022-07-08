package eapli.base.customermanagement.domain;

import eapli.base.common.domain.AddressType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static eapli.base.util.domain.TestUtils.generateDummyCustomer;
import static org.junit.jupiter.api.Assertions.*;

class CustomerAddressTest {

    private String postalAddress1 = "Rua";
    private String postalAddress2 = "Avenida";

    private AddressType ship = AddressType.SHIPPING;
    private AddressType bill = AddressType.BILLING;

    CustomerAddress customerAddress1 = new CustomerAddress("Casa", postalAddress1,ship);
    CustomerAddress customerAddress2 = new CustomerAddress("Trabalho", postalAddress2,bill);
    CustomerAddress customerAddress3 = new CustomerAddress("Casa", postalAddress1, ship);

    CustomerAddress customerAddress4 = new CustomerAddress("Trabalho", postalAddress2,ship);

    @Test
    public void validConstructorTest(){
        new CustomerAddress();
        assertNotNull(customerAddress1);
        assertEquals(customerAddress1, customerAddress3);
    }

    @Test
    public void emptyConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new CustomerAddress(null,postalAddress1, ship));
        String expectedMessage = ("At least one of the required method parameters is null");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void invalidConstructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new CustomerAddress("!#$%@", postalAddress1, bill));
        String expectedMessage = ("Invalid Address Name");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void invalidConstructorTest2(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new CustomerAddress("Rua", "!#$%&", bill));
        String expectedMessage = ("Invalid Postal Address");
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void converterTest(){
        PostalAddress postalAddress = new PostalAddress(postalAddress1,ship);
        PostalAddress expected = customerAddress1.convertAddress();
        assertEquals(expected,postalAddress);
    }

    @Test
    public void equalsTest(){
        assertEquals(customerAddress1, customerAddress3);
        assertEquals(customerAddress1, customerAddress1);
    }

    @Test
    public void notEqualsTest(){
        Object otherObject = new Object();

        assertNotEquals(customerAddress1, customerAddress2);
        assertNotEquals(customerAddress1, otherObject);
    }

    @Test
    public void toStringTest(){
        assertTrue("Address Name: Casa\nPostal Address: Rua\nAddress Type: SHIPPING".contains(customerAddress1.toString()));
    }

    @Test
    public void hashCodeTest(){
        assertNotNull(customerAddress1.hashCode());
    }

    @Test
    public void compareToTest(){
        assertEquals(17,customerAddress2.compareTo(customerAddress1));
        assertEquals(-17,customerAddress1.compareTo(customerAddress2));
        assertEquals(0,customerAddress1.compareTo(customerAddress3));
    }

    @Test
    public void addressesByTypeTest() {
        Customer testCustomer = generateDummyCustomer();
        testCustomer.addCustomerAddress(customerAddress1);
        testCustomer.addCustomerAddress(customerAddress2);
        testCustomer.addCustomerAddress(customerAddress4);

        List<CustomerAddress> resultBilling = testCustomer.addresses(AddressType.BILLING);
        List<CustomerAddress> resultShipping = testCustomer.addresses(AddressType.SHIPPING);

        List<CustomerAddress> expectedBilling = Arrays.asList(customerAddress2);
        List<CustomerAddress> expectedShipping = Arrays.asList(customerAddress1, customerAddress4);

        assertArrayEquals(expectedBilling.toArray(), resultBilling.toArray());
        assertArrayEquals(expectedShipping.toArray(), resultShipping.toArray());
    }
}