package eapli.base.agvmanagement.domain;

import eapli.base.agvdockmanagement.domain.AGVDock;
import eapli.base.common.domain.Volume;
import eapli.base.common.domain.VolumeUnit;
import eapli.base.common.domain.Weight;
import eapli.base.common.domain.WeightUnit;
import eapli.framework.domain.model.DomainFactory;

public class AGVBuilder implements DomainFactory<AGV> {
    /**
     * The AGV instance being builded
     */
    private AGV thisAGV;

    /**
     * The AGV's Identifier
     */
    private AGVId id;

    /**
     * The AGV's Model
     */
    private AGVModel model;

    /**
     * The AGV's Short Description
     */
    private AGVShortDescription desc;

    /**
     * The AGV's Battery Status (Battery Autonomy)
     */
    private AGVBatteryStatus batteryStatus;

    /**
     * The AGV's current Status (Default is 'Free')
     */
    private AGVStatus status = AGVStatus.Free;

    /**
     * The AGV's maximum Weight Capacity (By default the unit is G)
     */
    private Weight weightCapacity;

    /**
     * The AGV's maximum Volume Capacity (By default the unit is MM)
     */
    private Volume volumeCapacity;

    /**
     * The AGV's Dock location
     */
    private AGVDock baseLocation;

    /**
     * Creates and Adds an AGVId with the given data to the AGV currently being built.
     *
     * @param id The String used to create the AGVId
     * @return The AGVBuilder with the current AGV being built
     */
    public AGVBuilder withId(String id) {
        this.id = new AGVId(id);
        return this;
    }

    /**
     * Creates and Adds an AGVModel with the given data to the current AGV being built
     *
     * @param model The String used to create the AGVModel
     * @return The AGVBuilder with the current AGV being built
     */
    public AGVBuilder ofModel(final String model) {
        this.model = AGVModel.valueOf(model);
        return this;
    }

    /**
     * Creates and Adds an AGVShortDescription with the given data to the current AGV being built
     *
     * @param description The String used to create the AGVShortDescription
     * @return The AGVBuilder with the current AGV being built
     */
    public AGVBuilder ofDescription(final String description) {
        this.desc = AGVShortDescription.valueOf(description);
        return this;
    }

    /**
     * Creates and Adds a Volume Capacity with the given data to the current AGV being built.
     * By Default the Volume Capacity is created using the Unit MM.
     *
     * @param capacity The AGV's Volume Capacity
     * @return The AGVBuilder with the current AGV being built
     */
    public AGVBuilder ofVolumeCapacity(final double capacity) {
        return ofVolumeCapacity(capacity, VolumeUnit.MM);
    }

    /**
     * Creates and Adds a Volume Capacity with the given data to the current AGV being built.
     *
     * @param capacity The AGV's Volume Capacity
     * @param unit The Volume's Unit
     * @return The AGVBuilder with the current AGV being built
     */
    public AGVBuilder ofVolumeCapacity(final double capacity, final VolumeUnit unit) {
        this.volumeCapacity = Volume.valueOf(capacity, unit);
        return this;
    }

    /**
     * Creates and Adds a Weight Capacity with the given data to the current AGV being built.
     * By Default the Weight Capacity is created using the Unit G.
     *
     * @param capacity The AGV's Weight Capacity
     * @return The AGVBuilder with the current AGV being built
     */
    public AGVBuilder ofWeightCapacity(final double capacity) {
        return ofWeightCapacity(capacity, WeightUnit.G);
    }

    /**
     * Creates and Adds a Weight Capacity with the given data to the current AGV being built.
     *
     * @param capacity The AGV's Weight Capacity
     * @param unit The Weight's Unit
     * @return The AGVBuilder with the current AGV being built
     */
    public AGVBuilder ofWeightCapacity(final double capacity, final WeightUnit unit) {
        this.weightCapacity = Weight.valueOf(capacity, unit);
        return this;
    }

    /**
     * Creates and adds Battery Status (Battery Autonomy) with the given data to the
     * current AGV being built.
     *
     * @param autonomy the Battery autonomy in minutes
     * @return The AGVBuilder with the current AGV being built
     */
    public AGVBuilder ofBatteryStatus(final int autonomy) {
        this.batteryStatus = AGVBatteryStatus.valueOf(autonomy);
        return this;
    }

    /**
     * Adds the given AGVDock (Base Location) to the AGV being built.
     *
     * @param baseLocation The AGV's Dock
     * @return The AGVBuilder with the current AGV being built
     */
    public AGVBuilder ofBaseLocation(final AGVDock baseLocation) {
        this.baseLocation = baseLocation;
        return this;
    }

    /**
     * Tries to build an AGV instance with the given data. If mandatory data is missing throws and IllegalStateException
     * and doesn't build the AGV.
     *
     * @return And instance of AGV with the given data.
     */
    private AGV buildOrThrow() {
        if (thisAGV != null)
            return thisAGV;

        if (id == null ||
                model == null ||
                desc == null ||
                batteryStatus == null ||
                weightCapacity == null ||
                volumeCapacity == null ||
                baseLocation == null) {

            id = null;
            model = null;
            desc = null;
            batteryStatus = null;
            weightCapacity = null;
            volumeCapacity = null;
            baseLocation = null;
            throw new IllegalStateException("AGV can only be built if all mandatory data is defined!");
        }

        thisAGV = new AGV(id, model, desc,weightCapacity, volumeCapacity, baseLocation, batteryStatus, status);
        return thisAGV;
    }

    /**
     * Creates and adds an AGV Status with the given data to the AGV being built.
     *
     * @param status A String to create the Status with.
     * @return The AGVBuilder with the current AGV being built
     */
    public AGVBuilder withStatus(String status) {
        AGVStatus tmp = AGVStatus.valueOfStatus(status);

        if (tmp == null)
            try {
                tmp = AGVStatus.valueOf(status);
            } catch (Exception e) {
                throw new IllegalArgumentException("No such AGV Status " + status + "!");
            }

        return withStatus(tmp);
    }

    /**
     * Adds the given AGV Status to the AGV being built.
     *
     * @param status The AGV's Status
     * @return The AGVBuilder with the current AGV being built
     */
    public AGVBuilder withStatus(AGVStatus status) {
        buildOrThrow();
        this.status = status;
        return this;
    }

    /**
     * Builds an AGV with the specified data
     *
     * @return An instance of AGV
     */
    @Override
    public AGV build() {
        final AGV ret = buildOrThrow();

        thisAGV = null;

        return ret;
    }
}
