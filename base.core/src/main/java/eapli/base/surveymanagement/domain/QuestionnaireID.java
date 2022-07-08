package eapli.base.surveymanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class QuestionnaireID implements ValueObject, Comparable<QuestionnaireID> {

    /**
     * Questionnaire's ID
     */
    @Column(nullable = false)
    private String identifier;

    /**
     * Constructor for QuestionnaireID object
     * @param identifier the Questionnaire ID
     */
    protected QuestionnaireID(final String identifier){
        this.identifier = identifier;
    }

    /**
     * Mandatory empty constructor for ORM
     */
    protected QuestionnaireID(){
    }

    /**
     * Creates an QuestionnaireID object
     * @param identifier the Questionnaire identifier
     * @return a new QuestionnaireID object
     */
    public static QuestionnaireID valueOf(final String identifier){ return new QuestionnaireID(identifier);}

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

        if(!(o instanceof  QuestionnaireID)){
            return false;
        }

        final QuestionnaireID other = (QuestionnaireID) o;
        return Objects.equals(identifier, other.identifier);
    }

    /**
     * @return transforms the object to string form
     */
    @Override
    public String toString(){ return identifier;}

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
    public int compareTo(QuestionnaireID o){ return identifier.compareTo(o.identifier);}
}
