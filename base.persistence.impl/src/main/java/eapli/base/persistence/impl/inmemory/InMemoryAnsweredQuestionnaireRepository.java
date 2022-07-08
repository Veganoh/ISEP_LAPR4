package eapli.base.persistence.impl.inmemory;

import eapli.base.surveymanagement.domain.AnsweredQuestionnaire;
import eapli.base.surveymanagement.domain.AnsweredQuestionnaireID;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.repositories.AnsweredQuestionnaireRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryAnsweredQuestionnaireRepository extends InMemoryDomainRepository<AnsweredQuestionnaire, Long> implements AnsweredQuestionnaireRepository {
    /*
     * Initializes the inmemory repository
     */
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<AnsweredQuestionnaire> findAnswers(Questionnaire questionnaire) {
        return null;
    }
}
