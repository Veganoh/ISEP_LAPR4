package eapli.base.app.other.console;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.application.OptionSort;
import eapli.base.productmanagement.application.ShowCatalogController;
import eapli.base.productmanagement.domain.Product;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShowCatalogTest {





    @Test
    void showCatalogTest(){
        AuthzRegistry.configure(PersistenceContext.repositories().users(),new BasePasswordPolicy(), new PlainTextEncoder());
        AuthenticationService auth = AuthzRegistry.authenticationService();
        auth.authenticate("poweruser", "poweruserA1", BaseRoles.POWER_USER);

        ShowCatalogController controller = new ShowCatalogController();

        List<Product> list = (List<Product>) controller.getProductsSorted(OptionSort.CATEGORY);
        Product firstElement = list.get(0);
        Product lastElement = list.get(list.size()-1);
        Assertions.assertNotNull(list);
        Assertions.assertTrue(firstElement.getCategory().compareTo(lastElement.getCategory().getAlphaCode()) < 0);

        list = (List<Product>) controller.getProductsSorted(OptionSort.BRAND);
        firstElement = list.get(0);
        lastElement = list.get(list.size()-1);
        Assertions.assertNotNull(list);
        Assertions.assertTrue(firstElement.getBrand().compareTo(lastElement.getBrand())< 0);

        list = (List<Product>) controller.getProductsSorted(OptionSort.SHORT_DESCRIPTION);
        firstElement = list.get(0);
        lastElement = list.get(list.size()-1);
        Assertions.assertNotNull(list);
        Assertions.assertTrue(firstElement.getProductDescriptions().shortestDescription().compareTo(lastElement.getProductDescriptions().shortestDescription())< 0);

        list = (List<Product>) controller.getProductsSorted(OptionSort.LONG_DESCRIPTION);
        firstElement = list.get(0);
        lastElement = list.get(list.size()-1);
        Assertions.assertNotNull(list);
        Assertions.assertTrue(firstElement.getProductDescriptions().longestDescription().compareTo(lastElement.getProductDescriptions().longestDescription())< 0);
    }



}
