package eapli.base.surveymanagement.controller;


import eapli.base.surveymanagement.application.answerQuestionnaire;
import eapli.base.surveymanagement.application.questionnairesLexer;
import eapli.base.surveymanagement.application.questionnairesParser;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.List;

public class AnswerQuestionnaireController {

    /**
     * Input stream to the remote server
     */
    private DataInputStream sIn;

    /**
     * Output stream to the remote server
     */
    private DataOutputStream sOut;

    /**
     * Authorization service that is used to make sure only authorized users can use the controller
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    AnswerQuestionaireRequest answerQuestionaireRequest = new AnswerQuestionaireRequest(authz.session().get().authenticatedUser());

    /**
     * returns a list of al product categories
     * @return a list of al product categories
     */
    public List<Questionnaire> getQuestionnaires() {
        return answerQuestionaireRequest.getQuestionnaires();
    }

    public HashMap<String, HashMap<String, Pair<String, List<String>>>> answerQuestionnaire(Questionnaire questionnaire){

        CharStream in = CharStreams.fromString(questionnaire.text());
        questionnairesLexer lexer = new questionnairesLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        questionnairesParser parser = new questionnairesParser(tokens);

        answerQuestionnaire validator = new answerQuestionnaire();
        validator.visit(parser.questionnaires());

        return validator.answers;
    }

    /**
     * Constructs a new ShowPreparedOrdersController
     */
    public AnswerQuestionnaireController() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CLIENT_USER, BaseRoles.ADMIN, BaseRoles.POWER_USER);
    }
}
