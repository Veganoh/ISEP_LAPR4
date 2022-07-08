package eapli.base.surveymanagement.controller;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.repositories.QuestionnaireRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;


import java.io.IOException;

public class CreateQuestionnaireController {

    /**
     * Authorization service that is used to make sure only authorized users can use the controller
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * The Order Repository
     */
    private final QuestionnaireRepository questionnaireRepository = PersistenceContext.repositories().Questionnaire();

    /**
     * Constructs a new ShowPreparedOrdersController
     */
    public CreateQuestionnaireController() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_MANAGER, BaseRoles.ADMIN, BaseRoles.POWER_USER);
    }

    /**
     * loads a new questionnaire or answered questionnaire
     * @param path the path to the file
     * @return true if saves, false if don't
     * @throws IOException in case path file is not accepted
     */
    public boolean load(String path) throws IOException {

        Questionnaire questionnaire = new Questionnaire(path);
        questionnaireRepository.save(questionnaire);

        TargetAudienceHelper helper = new TargetAudienceHelper(questionnaire);
        helper.referCostumers();
        return true;
    }
}
