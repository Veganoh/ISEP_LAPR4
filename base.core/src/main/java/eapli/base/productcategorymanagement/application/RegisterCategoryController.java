package eapli.base.productcategorymanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productcategorymanagement.domain.ProductCategory;
import eapli.base.productcategorymanagement.domain.ProductCategoryBuilder;
import eapli.base.productcategorymanagement.repositories.ProductCategoryRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class RegisterCategoryController {
    /**
     * Authorization service that is used to make sure only authorized users can use the controller
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * The object repository
     */
    private final ProductCategoryRepository repo = PersistenceContext.repositories().Categories();

    public RegisterCategoryController(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER, BaseRoles.ADMIN);
    }

    /**
     * Registers a new Category into the repository
     * @param alphaCode the category's alphaCode
     * @param description the category's description
     * @param tax the category's tax
     * @return the created Product Category
     */
    public ProductCategory register(String alphaCode,String description, float tax) {

        ProductCategoryBuilder builder = new ProductCategoryBuilder();
        builder.withData(alphaCode, description).withTax(tax);

        ProductCategory category = builder.build();

        return repo.save(category);
    }
}
