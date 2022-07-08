package eapli.base.persistence.impl.inmemory;

import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.domain.QuestionnaireID;
import eapli.base.surveymanagement.repositories.QuestionnaireRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;

public class InMemoryQuestionnaireRepository extends InMemoryDomainRepository<Questionnaire, QuestionnaireID> implements QuestionnaireRepository {
    /*
     * Initializes the inmemory repository
     */
    static {
        InMemoryInitializer.init();
    }

    @Override
    public List<Questionnaire> findClientSurveys(SystemUser systemUser) {
        return null;
    }
}
