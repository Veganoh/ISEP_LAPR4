package eapli.base.agvmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class AGVModel implements ValueObject, Comparable<AGVModel> {
    private String model;

    /**
     * A Constructor for creating AGVModel objects
     *
     * @param model the AGV Model
     */
    protected AGVModel(final String model) {
        Preconditions.nonEmpty(model, "Model cannot be empty");

        if (model.length() > 50)
            throw new IllegalArgumentException("The AGV Model mustn't have more than 50 characters!");

        this.model = model;
    }

    /**
     * empty constructor for ORM
     */
    protected AGVModel() {
        // for ORM
    }

    /**
     * Creates an AGVModel object
     *
     * @param model the AGV Model
     * @return An AGVModel Object
     */
    public static AGVModel valueOf(final String model) {
        return new AGVModel(model);
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
        if (!(o instanceof AGVModel)) {
            return false;
        }

        final AGVModel other = (AGVModel) o;
        return model.equals(other.model);
    }

    /**
     * @return transforms this objet to string form
     */
    @Override
    public String toString() {
        return model;
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(model).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(AGVModel o) {
        return model.compareTo(o.model);
    }
}
