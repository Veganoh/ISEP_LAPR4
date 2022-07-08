package eapli.base.persistence.impl.inmemory;

import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.ProductCode;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.warehousemanagement.domain.Shelf;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class InMemoryProductRepository extends InMemoryDomainRepository<Product, ProductCode> implements ProductRepository {

    /*
     * Initializes the inmemory repository
     */
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Product> sortByCategory() {
        List<Product> products = (List<Product>) match(Product::isValid);

        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getCategory().identity().compareTo(o2.getCategory().identity());
            }

        });
        return products;    }

    @Override
    public Iterable<Product> sortByBrand() {
        List<Product> products = (List<Product>) match(Product::isValid);

        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getBrand().compareTo(o2.getBrand());
            }

        });
        return products;
    }

    @Override
    public Iterable<Product> sortByShortDescription() {
        List<Product> products = (List<Product>) match(Product::isValid);

        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getProductDescriptions().shortestDescription().compareTo(o2.getProductDescriptions().shortestDescription());
            }
        });
        return products;
    }

    @Override
    public Iterable<Product> sortByLongDescription() {
        List<Product> products = (List<Product>) match(Product::isValid);

        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getProductDescriptions().longestDescription().compareTo(o2.getProductDescriptions().longestDescription());
            }
        });
        return products;
    }

    @Override
    public Iterable<Shelf> findOccupiedShelves() {
        Iterable<Product> products =  match(Product::isValid);
        List<Shelf> shelfList = new ArrayList<>();
        for (Product product : products)
            shelfList.addAll(product.availableShelves());
        return null;
    }

    @Override
    public Optional<Product> find(String id) {
        ProductCode identifier = ProductCode.valueOf(id);
        return matchOne(e -> e.identity().equals(identifier));
    }
}
