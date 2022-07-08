package eapli.base.persistence.impl.inmemory;

import eapli.base.ordermanagement.domain.SalesClerk;
import eapli.base.ordermanagement.repositories.SalesClerkRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemorySalesClerkRepository extends InMemoryDomainRepository<SalesClerk, Long> implements SalesClerkRepository {
    /*
     * Initializes the inmemory repository
     */
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<SalesClerk> findByUsername(final Username username) {
        return matchOne(e -> e.user().username().equals(username));
    }
}
