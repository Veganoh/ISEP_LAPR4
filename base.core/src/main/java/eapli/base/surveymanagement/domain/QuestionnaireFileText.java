package eapli.base.surveymanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class QuestionnaireFileText implements ValueObject, Comparable<QuestionnaireFileText> {

    /**
     * A questionnaire
     */
    @Lob
    private String questionnaire;

    /**
     * Constructor for QuestionnaireFileText object
     * @param questionnaire the questionnaire
     */
    public QuestionnaireFileText(final String questionnaire) {
        Preconditions.nonEmpty(questionnaire, "Questionnaire cannot be empty");

        this.questionnaire = questionnaire;
    }

    /**
     * empty constructor for ORM
     */
    protected QuestionnaireFileText() {
        // for ORM
    }

    /**
     * Creates a QuestionnaireFileText object
     * @param questionnaire the QuestionnaireFileText
     * @return a new QuestionnaireFileText object
     */
    public static QuestionnaireFileText valueOf(final String questionnaire) {
        return new QuestionnaireFileText(questionnaire);
    }

    /**
     * Return true if both object are equal or false otherwise
     * @param o the other object
     * @return true if both object are equal or false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionnaireFileText)) {
            return false;
        }

        final QuestionnaireFileText other = (QuestionnaireFileText) o;
        return questionnaire.equals(other.questionnaire);
    }

    /**
     * @return transforms this objet to string form
     */
    @Override
    public String toString() {
        return questionnaire;
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(this).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(QuestionnaireFileText o) {
        return questionnaire.compareTo(o.questionnaire);
    }
}

