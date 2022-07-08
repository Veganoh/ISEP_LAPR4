package eapli.base.util.domain;

import eapli.base.common.domain.AddressType;
import eapli.base.common.domain.Description;
import eapli.base.common.domain.Quantity;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.CustomerAddress;
import eapli.base.customermanagement.domain.CustomerBuilder;
import eapli.base.ordermanagement.domain.*;
import eapli.base.productcategorymanagement.domain.AlphaCode;
import eapli.base.productcategorymanagement.domain.ProductCategory;
import eapli.base.productcategorymanagement.domain.Tax;
import eapli.base.productmanagement.domain.CodingStandard;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.ProductBuilder;
import eapli.base.shipmentmanagement.domain.ShippingMethod;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Arrays;
import java.util.List;

public class TestUtils {
    /**
     * Returns a Customer object, for use in testing, with the following information.
     * Id: testCustomerId
     * E-mail: test@email.com
     * Name: testCustomerName
     * Phone: +351 987654321
     * VATNumber: 123456789
     * @return the Customer object
     */
    public static  Customer generateDummyCustomer() {
        String identifier = "testCustomerId";
        String email = "test@email.com";
        String name = "testCustomerName";
        String dialingCode = "+351";
        String phoneNumber = "987654321";
        String vatNumber = "123456789";

        SystemUser systemUser = null;
        CustomerBuilder testCustomerBuilder = new CustomerBuilder();

        return testCustomerBuilder.withData(identifier, email, name, dialingCode, phoneNumber, vatNumber,systemUser).build();
    }
    /**
     * Returns a Product object, for use in testing, with the following information.
     * ProductCode: ABCD.0000N
     * Description: testDescriptionN
     * ProductCategory (the same for all products generated this way):
     *     AlphaCode 1 | Description testDescription | Tax 23%
     * Brand: testBrandN
     * ReferenceCode: testReferenceCodeN
     * Price: N.50
     * Volume: N M
     * Weight: N KG
     * Barcode: testBarcodeN | CodingStandard: UPC
     * Where N is an integer received as
     * @param n
     * @return the Product object
     */
    public static Product generateDummyProduct(int n) {
        String productCode = String.format("ABCD.%05d", n);
        String shortDescription = "testShortDescription" + n;
        String longDescription = "testLongDescription" + n;
        ProductCategory category = new ProductCategory(AlphaCode.valueOf("1"), Description.valueOf("This is a test Description"), Tax.valueOf(23));
        String brand = "testBrand" + n;
        String referenceCode = "testReferenceCode" + n;
        double price = n + 0.50;
        double volume = n;
        double weight = n;
        String barcode = String.format("%012d", n);
        CodingStandard standard = CodingStandard.UPC;

        ProductBuilder testProductBuilder = new ProductBuilder();
        return testProductBuilder.withData(productCode, shortDescription, longDescription, category,
                brand, referenceCode, price, volume, weight, barcode, standard, true).build();
    }

    public static Order generateDummyOrder() {
        Customer testCustomer = generateDummyCustomer();
        CustomerAddress testBillingAddress = CustomerAddress.valueOf("test","testBillingAddress", AddressType.BILLING);
        CustomerAddress testShippingAddress = CustomerAddress.valueOf("test","testShippingAddress", AddressType.SHIPPING);
        ShippingMethod testshippingMethod = ShippingMethod.valueOf("test", "carrier1", "1.50 EUR", "KG", 0.23);
        Product testProduct = generateDummyProduct(1);
        return new OrderBuilder().ofCustomer(testCustomer)
                .ofBillingAddress(testBillingAddress)
                .ofShippingAddress(testShippingAddress)
                .ofShippingMethod(testshippingMethod)
                .ofPaymentMethod(PaymentMethod.PAYPAL)
                .ofItem(testProduct, Quantity.valueOf(1))
                .build();
    }
}
