package eapli.base.app.other.console.warehouseEmployee.agvDashboard;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoadAGVDashboardUI extends AbstractUI {
    /**
     * Main method of the UI
     *
     * @return shows the steps to add a new category
     */
    @Override
    protected boolean doShow() {
        try {
            File htmlFile = new File("base.app.other.console/src/main/java/eapli/base/app/other/console/warehouseEmployee/agvDashboard/www/index.html");
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (final IOException e) {
            System.out.println(e.getMessage());
            //System.out.println("Dashboard html file not found.");
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    /**
     * The UI headline
     *
     * @return The UI headline
     */
    @Override
    public String headline() {
        return "Load AGV status dashboard";
    }
}
