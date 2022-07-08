package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.agvdockmanagement.domain.AGVDock;
import eapli.base.agvmanagement.application.ConfigureAGVController;
import eapli.base.agvmanagement.domain.AGV;
import eapli.framework.actions.Action;

import java.util.Iterator;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

public class AGVBaseBootstrapper implements Action {
    ConfigureAGVController controller = new ConfigureAGVController();

    @Override
    public boolean execute() {
        if (controller.listAvailableDocks() == null) {
            LOGGER.warn("Assuming there are no created docks");
            return false;
        }

        Iterator<AGVDock> listDocks = controller.listAvailableDocks().iterator();

        AGVDock agvDock1 = listDocks.next();
        AGVDock agvDock2 = listDocks.next();
        AGVDock agvDock3 = listDocks.next();



        configureAGV("1", "AGV01", "A fast and effective AGV",  120, 200000, 2000, agvDock1);

        configureAGV("2", "AGV02", "A fast and effective AGV",  120, 200000, 2000, agvDock2);

        configureAGV("3", "AGV03", "A fast and effective AGV",  120, 200000, 2000, agvDock3);

        return true;
    }

    public void configureAGV(final String agvId,final String model,final String description,
                                final int autonomy,final double maxWeight,final double maxVolume,
                                final AGVDock baseLocation) {
        AGV agv = null;

        try {
            controller.configureAGV(agvId, model, description, maxWeight, maxVolume, baseLocation, autonomy);
        } catch (final IllegalArgumentException e) {
            LOGGER.warn("Assuming something is wrong with the Product Data (activate trace log for details)");
            LOGGER.trace("Assuming wrong data", e);
        }
    }
}
