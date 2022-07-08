package eapli.base.agvmanagermanagement.repositories;

import eapli.base.agvmanagermanagement.domain.AGVManager;
import eapli.base.agvmanagermanagement.domain.Coordinates;
import eapli.base.agvmanagermanagement.domain.Square;
import eapli.base.warehousemanagement.domain.AisleId;
import eapli.base.warehousemanagement.domain.RowId;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface AGVManagerRepository extends DomainRepository<Long, AGVManager> {
    Iterable<Square> squaresWithRowAisle(RowId rowId, AisleId aisleId);

    Optional<Square> squareWithCoords(Coordinates coords);
}
