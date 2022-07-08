package eapli.base.app.other.console.salesManager.questionnaires;

import eapli.framework.actions.Action;

public class CreateSurveyAction implements Action {

    @Override
    public boolean execute() {
        return new CreateSurveyUI().show();
    }
}
