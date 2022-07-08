package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.warehousemanagement.application.LoadWarehouseMapController;
import eapli.framework.actions.Action;

import java.io.IOException;

public class WarehouseBootstrapper implements Action {
    private final LoadWarehouseMapController loadWarehouseMapController = new LoadWarehouseMapController();

    @Override
    public boolean execute() {
        try {
            loadWarehouseMapController.load();
        } catch (IOException e) {
            System.out.println("Warehouse map loading unsuccessful, please load from menu.");
            return false;
        }
        return true;
    }
}
