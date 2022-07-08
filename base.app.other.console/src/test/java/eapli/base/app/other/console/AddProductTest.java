package eapli.base.app.other.console;

import eapli.base.common.domain.Description;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productcategorymanagement.domain.AlphaCode;
import eapli.base.productcategorymanagement.domain.ProductCategory;
import eapli.base.productcategorymanagement.domain.Tax;
import eapli.base.productmanagement.application.AddProductController;
import eapli.base.productmanagement.domain.CodingStandard;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.junit.jupiter.api.Test;

public class AddProductTest {

    @Test
    void registerProductTest() {
        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(), new PlainTextEncoder());

        AuthenticationService authz = AuthzRegistry.authenticationService()  ;
        authz.authenticate("poweruser", "poweruserA1", BaseRoles.POWER_USER);

        AddProductController controller = new AddProductController();

        String internalCode ="code.12345";
        String shortDescription = "A short Description";
        String longDescription = "A longer sort of Description";
        ProductCategory category = new ProductCategory(new AlphaCode("alpha-code"), new Description("This is a description"), Tax.valueOf(10));
        String brand = "Brand";
        String referenceCode = "refer";
        double price = 12;
        double volume = 1.5 ;
        double weight = 2.5 ;
        String barcode = "012345678912";

        boolean availability = false;
        String technicalDescription = "A more technical Description";
        String productionCode = "prod.12345";

        controller.addProduct(internalCode, shortDescription, longDescription, category, brand, referenceCode, price, volume, weight, barcode, CodingStandard.UPC, technicalDescription, productionCode, availability);

        controller.getShelves();
        PersistenceContext.repositories().Categories().save(category);
        PersistenceContext.repositories().Products().remove(controller.saveProduct());
        PersistenceContext.repositories().Categories().remove(category);
    }
}
