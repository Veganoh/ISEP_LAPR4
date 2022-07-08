package eapli.base.surveymanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;

import javax.persistence.*;

@Embeddable
public class AnsweredQuestionnaireID implements ValueObject, Comparable<AnsweredQuestionnaireID> {

    /**
     * AnsweredQuestionnaire's ID
     */
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long identifier;

    /**
     * Constructor for AnsweredQuestionnaireID object
     * @param identifier the AnsweredQuestionnaire ID
     */
    protected AnsweredQuestionnaireID(final Long identifier){
        this.identifier = identifier;
    }

    /**
     * Mandatory empty constructor for ORM
     */
    protected AnsweredQuestionnaireID(){
    }

    /**
     * Creates an AnsweredQuestionnaireID object
     * @param identifier the AnsweredQuestionnaire identifier
     * @return a new AnsweredQuestionnaireID object
     */
    public static AnsweredQuestionnaireID valueOf(final Long identifier){ return new AnsweredQuestionnaireID(identifier);}

    /**
     * Returns true if both objects are equal or false otherwise
     * @param o the other object
     * @return true if both objects are equal or false otherwise
     */
    @Override
    public boolean equals(final Object o){
        if(this == o){
            return true;
        }

        if(!(o instanceof  AnsweredQuestionnaireID)){
            return false;
        }

        final AnsweredQuestionnaireID other = (AnsweredQuestionnaireID) o;
        return identifier.equals((other.identifier));
    }

    /**
     * @return transforms the object to string form
     */
    @Override
    public String toString(){ return identifier.toString();}

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() { return new HashCoder().with(identifier).code();}

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(AnsweredQuestionnaireID o){ return identifier.compareTo(o.identifier);}
}
