package eapli.base.surveymanagement.repositories;

import eapli.base.surveymanagement.domain.AnsweredQuestionnaire;
import eapli.base.surveymanagement.domain.AnsweredQuestionnaireID;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.framework.domain.repositories.DomainRepository;

/**
 * A class that is to be updated depending on future needs of the project
 */
public interface AnsweredQuestionnaireRepository extends DomainRepository<Long, AnsweredQuestionnaire> {
    public Iterable<AnsweredQuestionnaire> findAnswers(Questionnaire questionnaire);

}
