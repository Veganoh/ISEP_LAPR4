package eapli.base.agvmanagement.repositories;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.AGVId;
import eapli.framework.domain.repositories.DomainRepository;

public interface AGVRepository extends DomainRepository<AGVId, AGV> {
    Iterable<AGV> available();
}
