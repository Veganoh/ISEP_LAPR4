package eapli.base.app.other.console.warehouseEmployee.agvConfiguration;

import eapli.base.agvmanagement.application.ShowConfiguredAGVController;
import eapli.base.agvmanagement.domain.AGV;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ShowConfiguredAGVUI extends AbstractListUI<AGV> {

    ShowConfiguredAGVController theController = new ShowConfiguredAGVController();

    @Override
    protected Iterable<AGV> elements() {
        return theController.listConfiguredAGV();
    }

    @Override
    protected Visitor<AGV> elementPrinter() {
        return new AGVPrinter();
    }

    @Override
    protected String elementName() {
        return "AGV";
    }

    @Override
    protected String listHeader() {
        return "Currently Configured AGV";
    }

    @Override
    protected String emptyMessage() {
        return null;
    }
    @Override
    public String headline() {
        return "Show Currently Configured AGV";
    }
}
