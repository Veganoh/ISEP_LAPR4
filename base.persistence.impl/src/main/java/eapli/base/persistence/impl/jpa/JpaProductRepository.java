package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.ProductCode;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.warehousemanagement.domain.Shelf;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.Optional;

public class JpaProductRepository  extends JpaAutoTxRepository<Product, ProductCode, ProductCode> implements ProductRepository {

    /**
     * creates a new category repository
     * @param autoTx the transactional context to enroll
     */
    public JpaProductRepository(final TransactionalContext autoTx) {
        super(autoTx, "internalCode");
    }

    /**
     * creates a new category repository
     */
    public JpaProductRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "internalCode");
    }

    /**
     * Returns the list of products sorted by category
     * @return the list of products sorted by category
     */
    @Override
    public Iterable<Product> sortByCategory() {
        final TypedQuery<Product> query = entityManager().createQuery("SELECT p FROM Product p " +
                " WHERE p.availability.availability = TRUE" +
                " ORDER BY UPPER(p.category.alphaCode)",Product.class);
        return query.getResultList();
    }

    /**
     * Returns the list of products sorted by brand
     * @return the list of products sorted by brand
     */
    @Override
    public Iterable<Product> sortByBrand() {
        final TypedQuery<Product> query = entityManager().createQuery("SELECT p FROM Product p " +
                " WHERE p.availability.availability = TRUE" +
                " ORDER BY UPPER(p.brand.brandName)",Product.class);
        return query.getResultList();
    }

    /**
     * Returns the list of products sorted by short description
     * @return the list of products sorted by short description
     */
    @Override
    public Iterable<Product> sortByShortDescription() {
        final TypedQuery<Product> query = entityManager().createQuery("SELECT p FROM Product p " +
                " WHERE p.availability.availability = TRUE" +
                " ORDER BY UPPER(p.productDescriptions.shortDescription)",Product.class);
        return query.getResultList();
    }

    /**
     * Returns the list of products sorted by long description
     * @return the list of products sorted by long description
     */
    @Override
    public Iterable<Product> sortByLongDescription() {
        final TypedQuery<Product> query = entityManager().createQuery("SELECT p FROM Product p " +
                " WHERE p.availability.availability = TRUE" +
                " ORDER BY UPPER(p.productDescriptions.longDescription)",Product.class);
        return query.getResultList();
    }

    @Override
    public Iterable<Shelf> findOccupiedShelves() {
        final TypedQuery<Shelf> query = entityManager().createQuery(
                "SELECT s FROM Product p " +
                            "INNER JOIN p.availableShelves s " +
                            "WHERE p.availability.availability = TRUE " +
                        "GROUP BY s.id ", Shelf.class);
        return query.getResultList();
    }

    @Override
    public Optional<Product> find(String id) {
        final TypedQuery<Product> query = entityManager().createQuery("SELECT p FROM Product p" +
                " WHERE p.id.internalCode LIKE :id",Product.class).setParameter("id", id);
        return Optional.ofNullable(query.getSingleResult());
    }
}
