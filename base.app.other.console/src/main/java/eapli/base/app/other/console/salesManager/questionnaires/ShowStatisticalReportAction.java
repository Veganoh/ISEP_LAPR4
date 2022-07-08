package eapli.base.app.other.console.salesManager.questionnaires;

import eapli.framework.actions.Action;

public class ShowStatisticalReportAction implements Action {

    @Override
    public boolean execute() {
        return new ShowStatisticalReportUI().show();
    }
}
