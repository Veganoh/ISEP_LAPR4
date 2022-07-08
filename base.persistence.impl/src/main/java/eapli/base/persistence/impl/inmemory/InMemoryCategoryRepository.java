package eapli.base.persistence.impl.inmemory;

import eapli.base.productcategorymanagement.domain.AlphaCode;
import eapli.base.productcategorymanagement.domain.ProductCategory;
import eapli.base.productcategorymanagement.repositories.ProductCategoryRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryCategoryRepository extends InMemoryDomainRepository<ProductCategory, AlphaCode> implements ProductCategoryRepository {

    /*
     * Initializes the inmemory repository
     */
    static {
        InMemoryInitializer.init();
    }

}
