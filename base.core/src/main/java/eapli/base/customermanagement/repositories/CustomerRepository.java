package eapli.base.customermanagement.repositories;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.CustomerIdentifier;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends DomainRepository<CustomerIdentifier, Customer> {
    Optional<Customer> find(String id);

    Optional<Customer> findByUsername(String username);

    List<Customer> findByAge(int minAge, int maxAge);
    List<Customer> findByGender(String gender);
    List<Customer> findByBrand(String brand);
    List<Customer> findByProduct(String productId);
    List<Customer> findByCategory(String categoryId);
}
