package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagermanagement.domain.AGVManager;
import eapli.base.agvmanagermanagement.domain.Coordinates;
import eapli.base.agvmanagermanagement.domain.Square;
import eapli.base.agvmanagermanagement.repositories.AGVManagerRepository;
import eapli.base.warehousemanagement.domain.AisleId;
import eapli.base.warehousemanagement.domain.RowId;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class JpaAGVManagerRepository extends JpaAutoTxRepository<AGVManager, Long, Long> implements AGVManagerRepository {
    /**
     * creates a new category repository
     * @param autoTx the transactional context to enroll
     */
    public JpaAGVManagerRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    /**
     * creates a new category repository
     */
    public JpaAGVManagerRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    @Override
    public Iterable<Square> squaresWithRowAisle(RowId rowId, AisleId aisleId) {
        final TypedQuery<Square> query = entityManager().createQuery("SELECT s FROM Square s WHERE s.rowId = :row AND s.aisleId = :aisle", Square.class)
                .setParameter("row", rowId)
                .setParameter("aisle", aisleId);
        return query.getResultList();
    }

    @Override
    public Optional<Square> squareWithCoords(Coordinates coords) {
        final TypedQuery<Square> query = entityManager().createQuery(
                "SELECT s FROM Square s " +
                        "WHERE s.coordinates.wSquare = :wSquare " +
                        "AND s.coordinates.lSquare = :lSquare", Square.class)
                .setParameter("wSquare", coords.widthSquare())
                .setParameter("lSquare", coords.lengthSquare());

        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
