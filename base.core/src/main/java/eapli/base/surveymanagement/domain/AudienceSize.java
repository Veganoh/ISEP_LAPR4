package eapli.base.surveymanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;

import javax.persistence.Embeddable;

@Embeddable
public class AudienceSize implements ValueObject, Comparable<AudienceSize> {

    /**
     * Audiences Size
     */
    private int audienceSize;

    /**
     * Constructor for Audiences Size object
     * @param audienceSize the Audiences Size
     */
    protected AudienceSize(final int audienceSize){
        if (audienceSize < 0)
            throw new IllegalArgumentException("Target audience cannot be negative");
        this.audienceSize = audienceSize;
    }

    /**
     * Mandatory empty constructor for ORM
     */
    protected AudienceSize(){
    }

    /**
     * Creates an  Audiences Size
     * @param audienceSize the  Audiences Size
     * @return a new  Audiences Size
     */
    public static AudienceSize valueOf(final int audienceSize){ return new AudienceSize(audienceSize);}

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

        if(!(o instanceof  AudienceSize)){
            return false;
        }

        final AudienceSize other = (AudienceSize) o;
        return audienceSize == other.audienceSize;
    }

    /**
     * @return transforms the object to string form
     */
    @Override
    public String toString(){ return Integer.toString(audienceSize);}

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() { return new HashCoder().with(audienceSize).code();}

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(AudienceSize o){ return Integer.compare(audienceSize, o.audienceSize);}

    public int value() { return audienceSize;}
}
