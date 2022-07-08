package eapli.base.persistence.impl.inmemory;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.AGVId;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryAGVRepository extends InMemoryDomainRepository<AGV, AGVId> implements AGVRepository {
    /*
     * Initializes the inmemory repository
     */
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<AGV> available() {
        return null;
    }
}
