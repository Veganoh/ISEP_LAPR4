package eapli.base.surveymanagement.repositories;

import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.domain.QuestionnaireID;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.List;

/**
 * A class that is to be updated depending on future needs of the project
 */
public interface QuestionnaireRepository extends DomainRepository<QuestionnaireID, Questionnaire> {

    List<Questionnaire> findClientSurveys(SystemUser systemUser);
}
