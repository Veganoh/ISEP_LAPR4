package eapli.base.app.other.console.salesManager.questionnaires;

import eapli.base.Application;
import eapli.base.common.domain.Width;
import eapli.base.surveymanagement.controller.CreateQuestionnaireController;
import eapli.base.surveymanagement.controller.ShowStatisticalReportController;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class ShowStatisticalReportUI extends AbstractUI {

    ShowStatisticalReportController controller = new ShowStatisticalReportController();

    @Override
    protected boolean doShow(){
        SelectWidget<Questionnaire> questionnaires = new SelectWidget<>("Select a Questionnaire:",controller.showSentQuestionnaires());
        questionnaires.show();
        final Questionnaire questionnaire = questionnaires.selectedElement();

        System.out.println(controller.showStatisticalReport(questionnaire));

        return true;
    }

    @Override
    public String headline() {
        return "View survey statistics report";
    }
}
