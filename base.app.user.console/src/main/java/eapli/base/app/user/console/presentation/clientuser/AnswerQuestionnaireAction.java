package eapli.base.app.user.console.presentation.clientuser;

import eapli.framework.actions.Action;

public class AnswerQuestionnaireAction implements Action {
    @Override
    public boolean execute(){ return new AnswerQuestionnaireUI().show();}
}