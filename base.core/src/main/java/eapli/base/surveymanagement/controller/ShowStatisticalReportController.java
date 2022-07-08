package eapli.base.surveymanagement.controller;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.domain.AnsweredQuestionnaire;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.domain.QuestionnaireStatisticsService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;

public class ShowStatisticalReportController {
    /**
     * Authorization service that is used to make sure only authorized users can use the controller
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * Constructs a new ShowStatisticalReportController
     */
    public ShowStatisticalReportController() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_MANAGER, BaseRoles.ADMIN, BaseRoles.POWER_USER);
    }

    public List<Questionnaire> showSentQuestionnaires() {
        return (List<Questionnaire>) PersistenceContext.repositories().Questionnaire().findAll();
    }

    public String showStatisticalReport(Questionnaire questionnaire) {
        List<AnsweredQuestionnaire> answeredQuestionnaires =
                (List<AnsweredQuestionnaire>) PersistenceContext.repositories().AnsweredQuestionnaire().findAnswers(questionnaire);
        return new QuestionnaireStatisticsService(questionnaire, answeredQuestionnaires).print();
    }
}
