//package eapli.base.agvmanagermanagement.domain;
//
//import eapli.framework.domain.model.ValueObject;
//import eapli.framework.util.HashCoder;
//import eapli.framework.validations.Preconditions;
//
//import javax.persistence.Embeddable;
//import javax.persistence.GeneratedValue;
//import java.util.regex.Pattern;
//
//@Embeddable
//public class AGVManagerId implements ValueObject, Comparable<AGVManagerId> {
//    /**
//     * An AGV Manager Identifier
//     */
//    @GeneratedValue
//    private Long id;
//
//    /**
//     * Mandatory empty constructor for ORM
//     */
//    protected AGVManagerId() {
//    }
//
//    /**
//     * A Constructor to create an AGV Manager Identifier
//     *
//     * @param id an AGV Manager Identifier
//     */
//    protected AGVManagerId(final long id) {
//        if (id < 0)
//            throw new IllegalArgumentException("AGV Manager Identifier must be positive!");
//
//        this.id = id;
//    }
//
//    /**
//     * Creates an AGV Manager Identifier
//     *
//     * @param identifier an AGV Manager Identifier
//     */
//    public static AGVManagerId valueOf(final long identifier) {
//        return new AGVManagerId(identifier);
//    }
//
//    /**
//     * Return true if both object are equal or false otherwise
//     *
//     * @param o the other object
//     * @return true if both object are equal or false otherwise
//     */
//    @Override
//    public boolean equals(final Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (!(o instanceof AGVManagerId)) {
//            return false;
//        }
//
//        final AGVManagerId other = (AGVManagerId) o;
//        return id == other.id;
//    }
//
//    /**
//     * @return transforms this object to string form
//     */
//    @Override
//    public String toString() {
//        return Long.toString(id);
//    }
//
//    /**
//     * @return the object's hashcode
//     */
//    @Override
//    public int hashCode() {
//        return new HashCoder().with(id).code();
//    }
//
//    /**
//     * @param o the other object
//     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
//     */
//    @Override
//    public int compareTo(AGVManagerId o) {
//        return Long.compare(id, o.id);
//    }
//}
