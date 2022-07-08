package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.agvdockmanagement.domain.AGVDock;
import eapli.base.agvdockmanagement.domain.AGVDockId;
import eapli.base.agvdockmanagement.repositories.AGVDockRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaAGVDockRepository extends JpaAutoTxRepository<AGVDock, AGVDockId,AGVDockId> implements AGVDockRepository {

    /**
     * creates a new AGVDock repository
     * @param autoTx the transactional context to enroll
     */
    public JpaAGVDockRepository(final TransactionalContext autoTx) {
        super(autoTx, "agv_dock_id");
    }

    /**
     * creates a new AGVDock repository
     */
    public JpaAGVDockRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "agv_dock_id");
    }

    @Override
    public Iterable<AGVDock> listAvailableDocks() {
        final TypedQuery<AGVDock> query = entityManager().createQuery("SELECT d FROM AGVDock d WHERE d.availability.availability = TRUE",AGVDock.class);
        return query.getResultList();
    }
}
