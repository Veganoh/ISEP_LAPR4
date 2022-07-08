package eapli.base.agvmanagement.application;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class ShowConfiguredAGVController {
    /**
     * Authorization service that is used to make sure only authorized users can use the controller
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * The AGV Repository
     */
    private final AGVRepository agvRepo = PersistenceContext.repositories().AGV();

    /**
     * Constructs a new ConfigureAGVController
     */
    public ShowConfiguredAGVController() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE, BaseRoles.ADMIN, BaseRoles.POWER_USER);
    }

    /**
     * Returns an Iterable with all the configured AGVs
     * @return an Iterable with all the configured AGVs
     */
    public Iterable<AGV> listConfiguredAGV() {
        return agvRepo.findAll();
    }
}
