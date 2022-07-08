package eapli.base.persistence.impl.inmemory;

import eapli.base.agvmanagermanagement.domain.AGVManager;
import eapli.base.agvmanagermanagement.domain.Coordinates;
import eapli.base.agvmanagermanagement.domain.Square;
import eapli.base.agvmanagermanagement.repositories.AGVManagerRepository;
import eapli.base.warehousemanagement.domain.AisleId;
import eapli.base.warehousemanagement.domain.RowId;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryAGVManagerRepository extends InMemoryDomainRepository<AGVManager, Long> implements AGVManagerRepository {

    /*
     * Initializes the inmemory repository
     */
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Square> squaresWithRowAisle(RowId rowId, AisleId aisleId) {
        return null;
    }

    @Override
    public Optional<Square> squareWithCoords(Coordinates coords) {
        return Optional.empty();
    }
}
