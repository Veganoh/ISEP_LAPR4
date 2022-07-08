package eapli.base.agvmanagement.application;

import eapli.base.agvdockmanagement.domain.AGVDock;
import eapli.base.agvdockmanagement.repositories.AGVDockRepository;
import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.AGVBuilder;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.agvmanagermanagement.domain.AGVManager;
import eapli.base.agvmanagermanagement.repositories.AGVManagerRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import javax.persistence.NoResultException;
import java.util.Iterator;

public class ConfigureAGVController {


    /**
     * Authorization service that is used to make sure only authorized users can use the controller
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * The AGVDock repository
     */
    private final AGVDockRepository agvDockRepo = PersistenceContext.repositories().Docks();

    /**
     * The AGVManager repository
     */
    private final AGVManagerRepository agvManagerRepo = PersistenceContext.repositories().AGVManager();

    /**
     * The AGV Repository
     */
    private final AGVRepository agvRepo = PersistenceContext.repositories().AGV();

    /**
     * Constructs a new ConfigureAGVController
     */
    public ConfigureAGVController() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE, BaseRoles.ADMIN, BaseRoles.POWER_USER);
    }

    /**
     * Returns an Iterable with all the available AGV Docks or throws if there are none
     * @return an Iterable with all the available AGVDocks
     */
    public Iterable<AGVDock> listAvailableDocks() {
        Iterable<AGVDock> availableDocksList =  agvDockRepo.listAvailableDocks();
        Iterator<AGVDock> it = availableDocksList.iterator();

        if (!it.hasNext())
            throw new NoResultException("There are currently no available AGV Docks in the system!");

        return availableDocksList;
    }

    /**
     * Builds a new AGV with the given data, if everything went successfully turns its dock unavailable and
     * persists the agv
     *
     * @param agvId The AGV Identifier
     * @param model The AGV Model
     * @param description The AGV Description
     * @param maxWeight The AGV Maximum Weight Capacity
     * @param maxVolume The AGV Maximum Volume Capacity
     * @param baseLocation The AGV Base Location (Dock)
     * @param autonomy The AGV Battery Autonomy
     * @return true if everything was successful, false if otherwise
     */
    public boolean configureAGV(final String agvId, final String model, final String description,
                             final double maxWeight, final double maxVolume, final AGVDock baseLocation,
                             final int autonomy){

        AGVBuilder builder = new AGVBuilder();

        builder.withId(agvId)
                .ofModel(model)
                .ofDescription(description)
                .ofBaseLocation(baseLocation)
                .ofBatteryStatus(autonomy)
                .ofWeightCapacity(maxWeight)
                .ofVolumeCapacity(maxVolume);

        AGV agv = builder.build();

        if (agvRepo.contains(agv))
            throw new IllegalArgumentException(String.format("The AGV - %s - already exists in the system!", agvId));

        agv.baseLocation().turnUnavailable();
        agvDockRepo.save(agv.baseLocation());

        AGVManager agvManager = agvManagerRepo.findAll().iterator().next();
        agvManager.addAGV(agv);
        agvRepo.save(agv);
        return agvManagerRepo.save(agvManager) != null;
    }
}
