package eapli.base.app.user.console.presentation.clientuser;

import eapli.base.surveymanagement.controller.AnswerQuestionnaireController;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.antlr.v4.runtime.misc.Pair;

import java.util.HashMap;
import java.util.List;

public class AnswerQuestionnaireUI extends AbstractUI {

    /**
     * The UI's controller
     */
    private final AnswerQuestionnaireController theController = new AnswerQuestionnaireController();

    @Override
    protected boolean doShow() {

        SelectWidget<Questionnaire> questionnaireSelectWidget = new SelectWidget<Questionnaire>("Select which survey you wish to answer", theController.getQuestionnaires());
        questionnaireSelectWidget.show();
        Questionnaire questionnaire = questionnaireSelectWidget.selectedElement();
        if(questionnaire != null) {
            HashMap<String, HashMap<String, Pair<String, List<String>>>> answers = theController.answerQuestionnaire(questionnaire);
            System.out.println("Your answer was successfully saved");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Answer survey - Customer \n";
    }
}

