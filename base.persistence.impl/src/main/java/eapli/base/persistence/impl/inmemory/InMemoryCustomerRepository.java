package eapli.base.persistence.impl.inmemory;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.CustomerIdentifier;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;
import java.util.Optional;

public class InMemoryCustomerRepository extends InMemoryDomainRepository<Customer, CustomerIdentifier> implements CustomerRepository {
    /*
     * Initializes the inmemory repository
     */
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<Customer> find(String id) {
        CustomerIdentifier identifier = CustomerIdentifier.valueOf(id);
        return matchOne(e -> e.identity().equals(identifier));
    }

    @Override
    public Optional<Customer> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public List<Customer> findByAge(int minAge, int maxAge) {
        return null;
    }

    @Override
    public List<Customer> findByGender(String gender) {
        return null;
    }

    @Override
    public List<Customer> findByBrand(String brand) {
        return null;
    }

    @Override
    public List<Customer> findByProduct(String productId) {
        return null;
    }

    @Override
    public List<Customer> findByCategory(String categoryId) {
        return null;
    }
}
