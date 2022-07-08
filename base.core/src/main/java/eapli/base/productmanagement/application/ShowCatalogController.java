package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;

public class ShowCatalogController {

    /**
     * Authorization service that is used to make sure only authorized users can use the controller
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * The product repository
     */
    private final ProductRepository productRepo = PersistenceContext.repositories().Products();

    public ShowCatalogController(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.ADMIN, BaseRoles.POWER_USER);
    }

    /**
     * Gets the list of products sorted by the option inserted
     * @param option the sorting method
     * @return a list of products sorted by the option inserted
     */
    public Iterable<Product> getProductsSorted(OptionSort option){

        Iterable<Product> result = new ArrayList<>();

        switch(option){
            case CATEGORY:
                result = sortByCategory();
                break;
            case BRAND:
                result = sortByBrand();
                break;
            case SHORT_DESCRIPTION:
                result = sortByShortDescription();
                break;
            case LONG_DESCRIPTION:
                result = sortByLongDescription();
                break;
        }
        return result;
    }

    /**
     * Returns the list of products sorted by long description
     * @return the list of products sorted by long   description
     */
    private Iterable<Product> sortByLongDescription(){
        return productRepo.sortByLongDescription();
    }

    /**
     * Returns the list of products sorted by short description
     * @return the list of products sorted by short description
     */
    private Iterable<Product> sortByShortDescription(){
        return productRepo.sortByShortDescription();
    }

    /**
     * Returns the list of products sorted by category
     * @return the list of products sorted by category
     */
    private Iterable<Product> sortByCategory(){
        return productRepo.sortByCategory();
    }

    /**
     * Returns the list of products sorted by brand
     * @return the list of products sorted by brand
     */
    private Iterable<Product> sortByBrand(){
        return productRepo.sortByBrand();
    }

}
