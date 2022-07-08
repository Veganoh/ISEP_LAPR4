package eapli.base.persistence.impl.inmemory;

import eapli.base.agvdockmanagement.domain.AGVDock;
import eapli.base.agvdockmanagement.domain.AGVDockId;
import eapli.base.agvdockmanagement.repositories.AGVDockRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryAGVDockRepository extends InMemoryDomainRepository<AGVDock, AGVDockId> implements AGVDockRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<AGVDock> listAvailableDocks() {
        return  match(AGVDock::isValid);
    }
}
