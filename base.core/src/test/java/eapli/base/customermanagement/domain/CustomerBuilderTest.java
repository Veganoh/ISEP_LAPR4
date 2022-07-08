package eapli.base.customermanagement.domain;

import eapli.base.common.domain.AddressType;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerBuilderTest {

    private String identifier = "1";
    private String email = "abc@gmail.com";
    private String name = "Name";
    private String dialingCode = "+351";
    private String phoneNumber = "123456789";
    private String vatNumber = "1";

    private String addressName1 = "Casa";
    private String addressName2 = "Trabalho";

    private String postalAddressName1 = "Rua";
    private String postalAddressName2 = "Avenida";

    private AddressType ship = AddressType.SHIPPING;
    private AddressType bill = AddressType.BILLING;

    private SystemUser systemUser = null;
    CustomerBuilderTest() {
    }

    @Test
    public void constructorTest(){
        CustomerBuilder builder = new CustomerBuilder();
        builder.withData(identifier,email,name,dialingCode,phoneNumber,vatNumber,systemUser);
        assertNotNull(builder.build());
        builder.withBirthDate(30,11,2020).withGender("Male");
        assertNotNull(builder.build());
        builder.withCustomerAddress(addressName1,postalAddressName1,ship).withCustomerAddress(addressName2,postalAddressName2,bill);
        assertNotNull(builder.build());
    }

    @Test
    public void invalidConstructorTest(){
        assertThrows(IllegalArgumentException.class, ()-> {
            CustomerBuilder builder = new CustomerBuilder();
            builder.withBirthDate(30,11,2020).withGender("Male").withCustomerAddress(addressName1,postalAddressName1,ship);
            builder.build();
        });
    }
}