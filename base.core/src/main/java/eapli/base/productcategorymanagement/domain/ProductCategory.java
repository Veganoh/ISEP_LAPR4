package eapli.base.productcategorymanagement.domain;

import eapli.base.common.domain.Description;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ProductCategory implements AggregateRoot<AlphaCode> {


    /**
     * The aggregate's ID which is also it's alpha code
     */
    @EmbeddedId
    private AlphaCode alphaCode;

    /**
     *  The category's description
     */
    @Embedded
    private Description description;

    /**
     *  The category's tax
     */
    @Embedded
    private Tax tax;

    /**
     * Constructs a product category
     * @param alphaCode the category's alpha code
     * @param description the category's description
     * @param tax The category's tax
     */
    public ProductCategory(AlphaCode alphaCode, Description description, Tax tax){
        Preconditions.noneNull(alphaCode, description, tax);

        if (description.length() < 20 || description.length() > 50)
            throw new IllegalArgumentException("Description must be between 20 and 50 characters");

        this.alphaCode = alphaCode;
        this.description = description;
        this.tax = tax;
    }

    /**
     * empty constructor for ORM
     */
    protected   ProductCategory(){
        // for ORM only
    }

    public Tax getTax() {
        return tax;
    }

    public AlphaCode getAlphaCode() {
        return alphaCode;
    }

    /**
     * String version of a product category
     * @return the string version of a product category
     */
    @Override
    public String toString() {
        return String.format("Category alpha code: %s\nCategory description: %s", alphaCode, description);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    /**
     * @param other the other object
     * @return true if they are the same or false if they aren't
     */
    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    /**
     * The object ID which in this case is it's AlphaCode
     * @return The objects ID
     */
    @Override
    public AlphaCode identity() {
        return this.alphaCode;
    }


}
