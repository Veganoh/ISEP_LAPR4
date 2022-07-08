package eapli.base.agvmanagement.domain;

import eapli.base.agvdockmanagement.domain.AGVDock;
import eapli.base.agvdockmanagement.domain.AGVDockBuilder;
import eapli.base.common.domain.VolumeUnit;
import eapli.base.common.domain.WeightUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AGVBuilderTest {
    String validId = "AGV1";
    String validModel = "Some AGV Model here";
    String validShortDesc = "Some random Short Desc here";
    int validBatteryStatus = 120;
    String validStatus = "In Maintenance";
    double validVolumeCap = 2000.00;
    double validWeightCap = 5000.00;
    AGVDockBuilder dockBuilder = new AGVDockBuilder();
    AGVDock dock = dockBuilder.ofId("D1")
            .ofBeginningSquare(10, 15, "w+")
            .ofEndingSquare(10, 17)
            .ofDepthSquare(9, 15)
            .build();

    AGVBuilder defaultBuilder = new AGVBuilder();

    @Test
    void buildValidData() {
        AGV agv = defaultBuilder.withId(validId)
                .ofModel(validModel)
                .ofDescription(validShortDesc)
                .ofBatteryStatus(validBatteryStatus)
                .ofVolumeCapacity(validVolumeCap)
                .ofWeightCapacity(validWeightCap)
                .ofBaseLocation(dock)
                .withStatus(validStatus)
                .build();

        assertNotNull(agv);

        agv = defaultBuilder.withId(validId)
                .ofModel(validModel)
                .ofDescription(validShortDesc)
                .ofBatteryStatus(validBatteryStatus)
                .ofVolumeCapacity(validVolumeCap)
                .ofWeightCapacity(validWeightCap)
                .ofBaseLocation(dock)
                .build();

        assertNotNull(agv);
    }

    @Test
    void buildWithDiffStatus() {
        AGV agv = defaultBuilder.withId(validId)
                .ofModel(validModel)
                .ofDescription(validShortDesc)
                .ofBatteryStatus(validBatteryStatus)
                .ofVolumeCapacity(validVolumeCap)
                .ofWeightCapacity(validWeightCap)
                .ofBaseLocation(dock)
                .withStatus("InMaintenance")
                .build();

        assertNotNull(agv);

        agv = defaultBuilder.withId(validId)
                .ofModel(validModel)
                .ofDescription(validShortDesc)
                .ofBatteryStatus(validBatteryStatus)
                .ofVolumeCapacity(validVolumeCap)
                .ofWeightCapacity(validWeightCap)
                .ofBaseLocation(dock)
                .withStatus("Serving Given Order")
                .build();

        assertNotNull(agv);

        agv = defaultBuilder.withId(validId)
                .ofModel(validModel)
                .ofDescription(validShortDesc)
                .ofBatteryStatus(validBatteryStatus)
                .ofVolumeCapacity(validVolumeCap)
                .ofWeightCapacity(validWeightCap)
                .ofBaseLocation(dock)
                .withStatus(AGVStatus.ServingGivenOrder)
                .build();

        assertNotNull(agv);
    }

    @Test
    void buildWithInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> defaultBuilder.withId(validId)
                .ofModel(validModel)
                .ofDescription(validShortDesc)
                .ofBatteryStatus(validBatteryStatus)
                .ofVolumeCapacity(validVolumeCap)
                .ofWeightCapacity(validWeightCap)
                .ofBaseLocation(dock)
                .withStatus("InMain")
                .build());
    }

    @Test
    void buildDefiningUnits() {
        AGV agv = defaultBuilder.withId(validId)
                .ofModel(validModel)
                .ofDescription(validShortDesc)
                .ofBatteryStatus(validBatteryStatus)
                .ofVolumeCapacity(validVolumeCap, VolumeUnit.M)
                .ofWeightCapacity(validWeightCap, WeightUnit.KG)
                .ofBaseLocation(dock)
                .build();

        assertNotNull(agv);

        agv = defaultBuilder.withId(validId)
                .ofModel(validModel)
                .ofDescription(validShortDesc)
                .ofBatteryStatus(validBatteryStatus)
                .ofVolumeCapacity(validVolumeCap)
                .ofWeightCapacity(validWeightCap, WeightUnit.MG)
                .ofBaseLocation(dock)
                .build();

        assertNotNull(agv);

        agv = defaultBuilder.withId(validId)
                .ofModel(validModel)
                .ofDescription(validShortDesc)
                .ofBatteryStatus(validBatteryStatus)
                .ofVolumeCapacity(validVolumeCap, VolumeUnit.CM)
                .ofWeightCapacity(validWeightCap)
                .ofBaseLocation(dock)
                .build();

        assertNotNull(agv);
    }

    @Test
    void buildWithoutEveryData() {
        assertThrows(IllegalStateException.class, () -> defaultBuilder.withId(validId).build());

        assertThrows(IllegalStateException.class, () -> defaultBuilder.ofModel(validModel).build());

        assertThrows(IllegalStateException.class, () -> defaultBuilder.ofDescription(validShortDesc).build());

        assertThrows(IllegalStateException.class, () -> defaultBuilder.ofBatteryStatus(validBatteryStatus).build());

        assertThrows(IllegalStateException.class, () -> defaultBuilder.ofVolumeCapacity(validVolumeCap).build());

        assertThrows(IllegalStateException.class, () -> defaultBuilder.ofWeightCapacity(validWeightCap).build());

        assertThrows(IllegalStateException.class, () -> defaultBuilder.ofBaseLocation(dock).build());

        assertThrows(IllegalStateException.class, () -> defaultBuilder.ofModel(validModel)
                .ofDescription(validShortDesc)
                .ofBatteryStatus(validBatteryStatus)
                .ofVolumeCapacity(validVolumeCap)
                .ofWeightCapacity(validWeightCap)
                .ofBaseLocation(dock)
                .build());

        assertThrows(IllegalStateException.class, () -> defaultBuilder.withId(validId)
                .ofDescription(validShortDesc)
                .ofBatteryStatus(validBatteryStatus)
                .ofVolumeCapacity(validVolumeCap)
                .ofWeightCapacity(validWeightCap)
                .ofBaseLocation(dock)
                .build());

        assertThrows(IllegalStateException.class, () -> defaultBuilder.withId(validId)
                .ofModel(validModel)
                .ofBatteryStatus(validBatteryStatus)
                .ofVolumeCapacity(validVolumeCap)
                .ofWeightCapacity(validWeightCap)
                .ofBaseLocation(dock)
                .build());

        assertThrows(IllegalStateException.class, () -> defaultBuilder.withId(validId)
                .ofModel(validModel)
                .ofDescription(validShortDesc)
                .ofVolumeCapacity(validVolumeCap)
                .ofWeightCapacity(validWeightCap)
                .ofBaseLocation(dock)
                .build());

        assertThrows(IllegalStateException.class, () -> defaultBuilder.withId(validId)
                .ofModel(validModel)
                .ofDescription(validShortDesc)
                .ofBatteryStatus(validBatteryStatus)
                .ofWeightCapacity(validWeightCap)
                .ofBaseLocation(dock)
                .build());

        assertThrows(IllegalStateException.class, () -> defaultBuilder.withId(validId)
                .ofModel(validModel)
                .ofDescription(validShortDesc)
                .ofBatteryStatus(validBatteryStatus)
                .ofVolumeCapacity(validVolumeCap)
                .ofBaseLocation(dock)
                .build());

        assertThrows(IllegalStateException.class, () -> defaultBuilder.withId(validId)
                .ofModel(validModel)
                .ofDescription(validShortDesc)
                .ofBatteryStatus(validBatteryStatus)
                .ofVolumeCapacity(validVolumeCap)
                .ofWeightCapacity(validWeightCap)
                .build());
    }
}