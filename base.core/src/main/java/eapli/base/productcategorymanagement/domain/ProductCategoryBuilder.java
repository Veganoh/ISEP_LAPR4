package eapli.base.productcategorymanagement.domain;

import eapli.base.common.domain.Description;
import eapli.framework.domain.model.DomainFactory;

public class ProductCategoryBuilder implements DomainFactory<ProductCategory> {

    /**
     * The new category's alpha code
     */
    private String alphaCode;

    /**
     *  The new category's description
     */
    private String description;

    /**
     *  The new category's tax
     */
    private float tax = 0;


    /**
     * Product category builder with the minimum data necessary(in this case every field is mandatory)
     * @param alphaCode The new category's alpha code
     * @param description The new category's description
     * @return the builder filled with the new Data
     */
    public ProductCategoryBuilder withData(final String alphaCode, final String description) {
        withAlphaCode(alphaCode);
        withDescription(description);
        return this;
    }

    /**
     * fills the alpha code variable
     * @param alphaCode The new category's alpha code
     * @return the builder filled with the new Data
     */
    public ProductCategoryBuilder withAlphaCode(final String alphaCode) {
        this.alphaCode = alphaCode;
        return this;
    }

    /**
     * fills the description variable
     * @param description The new category's description
     * @return the builder filled with the new Data
     */
    public ProductCategoryBuilder withDescription(final String description) {
        this.description = description;
        return this;
    }

    /**
     * fills the tax variable
     * @param tax The new category's tax rate
     * @return the builder filled with the new Data
     */
    public ProductCategoryBuilder withTax(final float tax) {
        this.tax = tax;
        return this;
    }

    /**
     * Builds a new Product category with the information stored in this class
     * @return a new Product Category with the Data contained in this class
     */
    @Override
    public ProductCategory build() {
        return new ProductCategory(AlphaCode.valueOf(alphaCode), Description.valueOf(description), Tax.valueOf(tax));
    }
}
