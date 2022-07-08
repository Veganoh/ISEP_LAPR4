package eapli.base.app.other.console.warehouseEmployee.warehouseplant;

import eapli.base.Application;
import eapli.base.warehousemanagement.application.LoadWarehouseMapController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;

public class LoadWarehousePlantUI extends AbstractUI {

    /**
     * The UI's controller
     */
    private final LoadWarehouseMapController theController = new LoadWarehouseMapController();

    /**
     * Main method of the UI
     * @return shows the steps to add a new category
     */
    @Override
    protected boolean doShow() {
        try {
            String jsonFileName = Application.settings().getWarehousePlantFilePath();

            final String json = "Would you like to load plant from file " + jsonFileName + "? (Y/N)\n" +
                    "WARNING: Any current warehouse configurations will be deleted and AGV's must be assigned to the new Dock locations.";
           if(Console.readBoolean(json)){
                if(this.theController.load()){
                    System.out.println("\nWarehouse Plant successfully loaded!\n");
                }else{
                    System.out.println("\nUnable to load warehouse\n");
                }
           }
        } catch (final IOException e) {
            System.out.println("Invalid warehouse plant file.");
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    /**
     * The UI headline
     * @return The UI headline
     */
    @Override
    public String headline() {
        return "Load a Warehouse Plant from JSON file";
    }
}
