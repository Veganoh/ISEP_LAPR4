package eapli.base.app.other.console.warehouseEmployee.agvConfiguration;

import eapli.base.agvdockmanagement.domain.AGVDock;
import eapli.base.agvmanagement.application.ConfigureAGVController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import javax.persistence.NoResultException;

public class ConfigureAGVUI extends AbstractUI {

    ConfigureAGVController theController = new ConfigureAGVController();

    @Override
    protected boolean doShow() {
        try {
            SelectWidget<AGVDock> categoryWidget = new SelectWidget<>("Select an AGV Dock", theController.listAvailableDocks());
            categoryWidget.show();
            final AGVDock baseLocation = categoryWidget.selectedElement();

            final String agvId = Console.readLine("AGV Identifier: ");
            final String model = Console.readLine("Model (no more than 50 characters): ");
            final String description = Console.readLine("Description (no more than 30 characters): ");
            final int autonomy = Console.readInteger("Battery Autonomy (in minutes): ");
            final double maxWeight = Console.readDouble("Maximum Weight Capacity (in grams): ");
            final double maxVolume = Console.readDouble("Maximum Volume Capacity (in millimeters): ");

            if (Console.readBoolean(String.format("\nAre you sure you want to configure this AGV (%s)? (Y/N)\n", agvId)))
                if (theController.configureAGV(agvId, model, description, maxWeight, maxVolume, baseLocation, autonomy)) {
                    System.out.println("\nAGV Configured with success!\n");
                    return true;
                }

            System.out.println("\nERROR! Something went wrong while configuring the AGV...\n");
        } catch (NoResultException | IllegalArgumentException e) {
            System.out.printf("\n%s\n", e.getMessage());
        }

        return false;
    }

    @Override
    public String headline() {
        return "Configure new AGV";
    }
}
