package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.AGVId;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.ordermanagement.domain.Order;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaAGVRepository extends JpaAutoTxRepository<AGV, AGVId, AGVId> implements AGVRepository {
    /**
     * creates a new AGV repository
     * @param autoTx the transactional context to enroll
     */
    public JpaAGVRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    /**
     * creates a new AGV repository
     */
    public JpaAGVRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    /**
     * @returns AGVs available for tasks (with status "FREE")
     */
    public Iterable<AGV> available() {
        final TypedQuery<AGV> query = entityManager().createQuery("SELECT a FROM AGV a WHERE a.status = 'Free'", AGV.class);
        return query.getResultList();
    }
}
