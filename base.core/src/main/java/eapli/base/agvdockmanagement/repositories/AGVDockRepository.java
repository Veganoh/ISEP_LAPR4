package eapli.base.agvdockmanagement.repositories;

import eapli.base.agvdockmanagement.domain.AGVDock;
import eapli.base.agvdockmanagement.domain.AGVDockId;
import eapli.framework.domain.repositories.DomainRepository;

public interface AGVDockRepository extends DomainRepository<AGVDockId, AGVDock> {

    public Iterable<AGVDock> listAvailableDocks();
}
