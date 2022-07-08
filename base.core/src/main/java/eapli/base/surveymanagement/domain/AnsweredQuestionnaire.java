package eapli.base.surveymanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;

@Entity
public class AnsweredQuestionnaire implements AggregateRoot<Long> {

    /**
     * Database Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Embedded
    private QuestionnaireFileText text;

    @OneToOne
    private Questionnaire baseQuestionnaire;

    public AnsweredQuestionnaire(QuestionnaireFileText text, Questionnaire baseQuestionnaire){
        this.baseQuestionnaire = baseQuestionnaire;
        setText(text);
    }

    protected AnsweredQuestionnaire() {
        //for ORM only
    }

    private void setText(QuestionnaireFileText text){
        this.text = text;
    }

    /**
     * compares if two questionnaires are equal
     * @param other the questionnaire used for comparation
     * @return true if they are equal
     */
    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    /**
     * gets the questionnaire's id
     * @return questionnaire id
     */
    @Override
    public Long identity() {
        return id;
    }

    public String text() {return text.toString();}
}
