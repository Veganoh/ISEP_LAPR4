package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.domain.OrderState;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.domain.QuestionnaireID;
import eapli.base.surveymanagement.repositories.QuestionnaireRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.List;

public class JpaQuestionnaireRepository extends JpaAutoTxRepository<Questionnaire,QuestionnaireID, QuestionnaireID> implements QuestionnaireRepository {
    /**
     * creates a new sales clerk repository
     * @param autoTx the transactional context to enroll
     */
    public JpaQuestionnaireRepository(final TransactionalContext autoTx) {
        super(autoTx, "identifier");
    }

    /**
     * creates a new questionnaire repository
     */
    public JpaQuestionnaireRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "identifier");
    }

    @Override
    public List<Questionnaire> findClientSurveys(SystemUser systemUser) {
        final TypedQuery<Questionnaire> query = entityManager().createQuery(
                        "SELECT ql FROM Customer cu " +
                                "INNER JOIN cu.questionnaireList ql " +
                                "WHERE cu.systemUser = :systemuser ", Questionnaire.class)
                .setParameter("systemuser", systemUser);
        return query.getResultList();
    }
}
