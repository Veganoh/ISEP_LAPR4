package eapli.base.productmanagement.domain;

import eapli.base.common.domain.Description;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class ProductDescriptions implements ValueObject, Comparable<ProductDescriptions>{

    /**
     * The product's short description
     */
    @Embedded
    private Description shortDescription;

    /**
     * The product's long description
     */
    @Embedded
    private Description longDescription;

    /**
     * The product's technical description
     */
    @Embedded
    private Description technicalDescription;

    /**
     * Creates a set od description for a given product
     * @param shortDescription The product's short description
     * @param longDescription The product's long description
     * @param technicalDescription The product's technical description
     */
    public ProductDescriptions(final Description shortDescription, final Description longDescription, final Description technicalDescription) {
        Preconditions.noneNull(shortDescription, longDescription);
        if (shortDescription.length() > 30)
            throw new IllegalArgumentException("Short description has to be smaller than 30 characters");
        if (longDescription.length() < 20 || longDescription.length() > 100)
            throw new IllegalArgumentException("Long description has to be bigger than 20 character and smaller than 100");

        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.technicalDescription = technicalDescription;
    }

    /**
     * empty constructor for ORM
     */
    protected ProductDescriptions() {
        // for ORM
    }

    /**
     * Creates a product description from 3 strings
     * @param shortDescription The product's short description
     * @param longDescription The product's long description
     * @param technicalDescription The product's technical description
     * @return a product description from 3 strings
     */
    public static ProductDescriptions valueOf(final String shortDescription, final String longDescription, final String technicalDescription) {
        if(technicalDescription != null)
            return new ProductDescriptions(Description.valueOf(shortDescription), Description.valueOf(longDescription), Description.valueOf(technicalDescription));
        else
            return new ProductDescriptions(Description.valueOf(shortDescription), Description.valueOf(longDescription), null);
    }

    /**
     * Return true if both object are equal or false otherwise
     * @param o the other object
     * @return true if both object are equal or false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDescriptions)) {
            return false;
        }

        final ProductDescriptions other = (ProductDescriptions) o;
        return shortDescription.equals(other.shortDescription) && longDescription.equals(other.longDescription);
    }

    public Description shortestDescription() {
        return shortDescription;
    }

    public Description longestDescription() {
        return longDescription;
    }


    /**
     * @return transforms this objet to string form
     */
    @Override
    public String toString() {
        return String.format("- %s\n- %s\n- %s",shortDescription, longDescription, technicalDescription);
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(shortDescription.toString()).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(ProductDescriptions o) {
        return shortDescription.compareTo(o.shortDescription);
    }
}
