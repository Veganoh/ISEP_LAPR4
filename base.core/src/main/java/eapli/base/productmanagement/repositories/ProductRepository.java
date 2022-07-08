package eapli.base.productmanagement.repositories;

import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.ProductCode;
import eapli.base.warehousemanagement.domain.Shelf;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

/**
 * A class that is to be updated depending on future needs of the project
 */
public interface ProductRepository extends DomainRepository<ProductCode, Product> {

    public Iterable<Product> sortByCategory();

    public Iterable<Product> sortByBrand();

    public Iterable<Product> sortByShortDescription();

    public Iterable<Product> sortByLongDescription();

    /**
     * Finds all the shelves that are empty
     * @return a list of all the empty shelves
     */
    Iterable<Shelf> findOccupiedShelves();

    Optional<Product> find(String id);
}
