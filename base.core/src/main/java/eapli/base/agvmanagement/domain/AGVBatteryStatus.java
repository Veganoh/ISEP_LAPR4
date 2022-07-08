package eapli.base.agvmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class AGVBatteryStatus implements ValueObject, Comparable<AGVBatteryStatus> {
    /**
     * The AGV max battery life
     */
    private int batteryAutonomy;

    /**
     * The AGV current battery life
     */
    private int currentBattery;

    /**
     * Mandatory empty constructor for ORM
     */
    protected AGVBatteryStatus() {}

    /**
     * A Constructor to create an AGV Battery Status
     *
     * @param batteryAutonomy an AGV maximum Battery life in minutes
     * @param currentBattery an AGV current Battery Life in minutes
     */
    protected AGVBatteryStatus(final int batteryAutonomy, final int currentBattery) {
        Preconditions.isPositive(batteryAutonomy, "The Battery autonomy must be positive!");
        Preconditions.nonNegative(currentBattery, "The current Battery can't be negative!");

        this.batteryAutonomy = batteryAutonomy;
        this.currentBattery = currentBattery;
    }

    /**
     * Creates a new AGVBatteryStatus instance with the given AGV's maximum battery life in minutes.
     * Current battery life is defaulted to 0.
     *
     * @param batteryAutonomy the AGV's maximum battery life in minutes
     * @return an AGVBatteryStatus
     */
    public static AGVBatteryStatus valueOf(int batteryAutonomy) {
        return new AGVBatteryStatus(batteryAutonomy, 0);
    }

    public int batteryAutonomy() {
        return batteryAutonomy;
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
        if (!(o instanceof AGVBatteryStatus)) {
            return false;
        }

        final AGVBatteryStatus other = (AGVBatteryStatus) o;
        return batteryAutonomy == other.batteryAutonomy && currentBattery == other.currentBattery;
    }

    /**
     * @return transforms this object to string form
     */
    @Override
    public String toString() {
        return String.format("Battery autonomy: %d min. - Current Battery Life: %d min.", batteryAutonomy, currentBattery);
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(batteryAutonomy * (currentBattery + 1)).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(AGVBatteryStatus o) {
        return currentBattery - o.currentBattery > 0 ? 1 :
                (currentBattery - o.currentBattery < 0 ? -1 :
                        batteryAutonomy - o.batteryAutonomy);
    }
}
