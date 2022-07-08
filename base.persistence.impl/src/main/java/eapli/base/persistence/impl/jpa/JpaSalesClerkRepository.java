package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordermanagement.domain.SalesClerk;
import eapli.base.ordermanagement.repositories.SalesClerkRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaSalesClerkRepository extends JpaAutoTxRepository<SalesClerk, Long, Long> implements SalesClerkRepository {
    /**
     * creates a new sales clerk repository
     * @param autoTx the transactional context to enroll
     */
    public JpaSalesClerkRepository(final TransactionalContext autoTx) {
        super(autoTx, "identifier");
    }

    /**
     * creates a new sales clerk repository
     */
    public JpaSalesClerkRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "identifier");
    }

    @Override
    public Optional<SalesClerk> findByUsername(final Username name) {
        final Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return matchOne("e.systemUser.username=:name", params);
    }
}
