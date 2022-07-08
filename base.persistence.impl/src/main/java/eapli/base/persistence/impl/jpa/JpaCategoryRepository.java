package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.productcategorymanagement.domain.AlphaCode;
import eapli.base.productcategorymanagement.domain.ProductCategory;
import eapli.base.productcategorymanagement.repositories.ProductCategoryRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaCategoryRepository extends JpaAutoTxRepository<ProductCategory, AlphaCode, AlphaCode>  implements ProductCategoryRepository {

    /**
     * creates a new product repository
     * @param autoTx the transactional context to enroll
     */
    public JpaCategoryRepository(final TransactionalContext autoTx) {
        super(autoTx, "alphaCode");
    }

    /**
     * creates a new product repository
     */
    public JpaCategoryRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "alphaCode");
    }
}
