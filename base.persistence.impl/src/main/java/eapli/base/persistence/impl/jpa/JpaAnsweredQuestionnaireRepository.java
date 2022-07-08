package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.surveymanagement.domain.AnsweredQuestionnaire;
import eapli.base.surveymanagement.domain.AnsweredQuestionnaireID;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.repositories.AnsweredQuestionnaireRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaAnsweredQuestionnaireRepository extends JpaAutoTxRepository<AnsweredQuestionnaire, Long,Long> implements AnsweredQuestionnaireRepository {
    /**
     * creates a new sales clerk repository
     * @param autoTx the transactional context to enroll
     */
    public JpaAnsweredQuestionnaireRepository(final TransactionalContext autoTx) {
        super(autoTx, "identifier");
    }

    /**
     * creates a new questionnaire repository
     */
    public JpaAnsweredQuestionnaireRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "identifier");
    }

    @Override
    public Iterable<AnsweredQuestionnaire> findAnswers(Questionnaire questionnaire) {
        final TypedQuery<AnsweredQuestionnaire> query = entityManager().createQuery(
                "SELECT a FROM AnsweredQuestionnaire a " +
                    "WHERE a.baseQuestionnaire = :questionnaire",
                AnsweredQuestionnaire.class).setParameter("questionnaire", questionnaire);
        return query.getResultList();
    }
}
