package eapli.base.surveymanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

public class TargetCondition implements ValueObject, Comparable<TargetCondition> {
    private TargetConditionType targetType;

    private String target;

    private int minAge;

    private int maxAge;

    protected TargetCondition(String type, String target) {
        Preconditions.nonEmpty(type, "The Target Type must be defined!");
        Preconditions.nonEmpty(type, "The Target must be defined!");

        this.targetType = TargetConditionType.valueOfType(type);
        setTarget(target);
    }

    /**
     * empty constructor for ORM
     */
    protected TargetCondition() {
        // for ORM
    }

    /**
     * Returns a new TargetAudience object constructed with the given data
     * @param type a String of the TargetAudience Type
     * @param target a String with the target
     * @return a new TargetAudience Object
     */
    public static TargetCondition valueOf(String type, String target) {
        return new TargetCondition(type, target);
    }


    private void setTarget(String target) {
        Preconditions.nonEmpty(target, "The Target condition can't be empty!");

        if (targetType.equals(TargetConditionType.AgeGroup)) {
            String[] targetArr = target.split("-");
            Preconditions.nonNegative(Integer.parseInt(targetArr[0]), "The Age Group can't have negative values!");
            Preconditions.nonNegative(Integer.parseInt(targetArr[1]), "The Age Group can't have negative values!");

            int minAge = Integer.parseInt(targetArr[0]);
            int maxAge = Integer.parseInt(targetArr[1]);

            Preconditions.ensure(minAge < maxAge, "The Age Group minimum age must be lower than the maximum age!");
            Preconditions.nonNegative(minAge, "The Age Group can't contain negative values!");
            Preconditions.nonNegative(maxAge, "The Age Group can't contain negative values!");

            this.minAge = minAge;
            this.maxAge = maxAge;
        } else {
            this.target = target;
        }
    }
    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(TargetCondition o) {
        return target.compareTo(o.target);
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
        if (!(o instanceof TargetCondition)) {
            return false;
        }

        final TargetCondition other = (TargetCondition) o;
        return this.target.equals(other.target) && targetType.equals(other.targetType);
    }

    /**
     * @return transforms this objet to string form
     */
    @Override
    public String toString() {
        if (target != null) {
            return String.format("%s : %s", targetType, target);
        } else {
            return String.format("%s : %d-%d", targetType, minAge, maxAge);
        }

    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(target.hashCode() + targetType.hashCode()).code();
    }

    public int MaxAge() {
        return maxAge;
    }

    public int MinAge() {
        return minAge;
    }

    public String condition() {
        return target;
    }

    public TargetConditionType conditionType() {
        return targetType;
    }
}
