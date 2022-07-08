package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.domain.CustomerIdentifier;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class JpaCustomerRepository extends JpaAutoTxRepository<Customer, CustomerIdentifier, CustomerIdentifier> implements CustomerRepository {

    /**
     * creates a new category repository
     * @param autoTx the transactional context to enroll
     */
    public JpaCustomerRepository(final TransactionalContext autoTx) {
        super(autoTx, "identifier");
    }

    /**
     * creates a new category repository
     */
    public JpaCustomerRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "identifier");
    }

    @Override
    public Optional<Customer> find(String id) {
        final TypedQuery<Customer> query = entityManager().createQuery("SELECT c FROM Customer c" +
                " WHERE c.id.identifier LIKE :id",Customer.class).setParameter("id", id);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public Optional<Customer> findByUsername(String username) {
        final TypedQuery<Customer> query = entityManager().createQuery("SELECT c FROM Customer c " +
                "WHERE c.systemUser.username.value LIKE :username", Customer.class).setParameter("username", username);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<Customer> findByAge(int minAge, int maxAge) {
        Calendar minCalendar = Calendar.getInstance();
        minCalendar.add(Calendar.YEAR, -maxAge);
        Calendar maxCalendar = Calendar.getInstance();
        maxCalendar.add(Calendar.YEAR, -minAge);
        final TypedQuery<Customer> query = entityManager().createQuery("SELECT c FROM Customer c " +
                "WHERE c.birthDate.birthDate BETWEEN :mindate AND :maxdate", Customer.class).setParameter("mindate", minCalendar).setParameter("maxdate", maxCalendar);
        return query.getResultList();
    }

    @Override
    public List<Customer> findByGender(String gender) {
        final TypedQuery<Customer> query = entityManager().createQuery("SELECT c FROM Customer c " +
                "WHERE c.gender.gender LIKE :gender", Customer.class).setParameter("gender", gender);
        return query.getResultList();
    }

    @Override
    public List<Customer> findByBrand(String brand) {
        final TypedQuery<Customer> query = entityManager().createQuery("SELECT c FROM Customer c, Order o " +
                "WHERE o.customer = c AND :brand IN (SELECT ol.product.brand.brandName FROM o.orderLineItems ol GROUP BY ol.product.brand.brandName)", Customer.class).setParameter("brand", brand);
        return query.getResultList();
    }

    @Override
    public List<Customer> findByProduct(String productId) {
        final TypedQuery<Customer> query = entityManager().createQuery("SELECT c FROM Customer c, Order o " +
                "WHERE o.customer = c AND :product IN (SELECT ol.product.id FROM o.orderLineItems ol GROUP BY ol.product.id)", Customer.class).setParameter("product", productId);
        return query.getResultList();
    }

    @Override
    public List<Customer> findByCategory(String categoryId) {
        final TypedQuery<Customer> query = entityManager().createQuery("SELECT c FROM Customer c, Order o " +
                "WHERE o.customer = c AND :category IN (SELECT ol.product.category.id FROM o.orderLineItems ol GROUP BY ol.product.category.id)", Customer.class).setParameter("category", categoryId);
        return query.getResultList();
    }
}
