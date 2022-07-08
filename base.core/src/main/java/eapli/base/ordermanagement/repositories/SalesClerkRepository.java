package eapli.base.ordermanagement.repositories;

import eapli.base.ordermanagement.domain.SalesClerk;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.Optional;

public interface SalesClerkRepository extends DomainRepository<Long, SalesClerk> {
    public Optional<SalesClerk> findByUsername(Username username);
}
