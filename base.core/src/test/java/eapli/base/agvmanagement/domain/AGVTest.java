package eapli.base.agvmanagement.domain;

import eapli.base.agvdockmanagement.domain.AGVDock;
import eapli.base.agvmanagermanagement.domain.Accessibility;
import eapli.base.agvmanagermanagement.domain.Coordinates;
import eapli.base.agvmanagermanagement.domain.Square;
import eapli.base.agvtaskmanagement.domain.AGVTask;
import eapli.base.common.domain.Volume;
import eapli.base.common.domain.VolumeUnit;
import eapli.base.common.domain.Weight;
import eapli.base.common.domain.WeightUnit;
import eapli.base.util.domain.RandomString;
import eapli.base.util.domain.TestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AGVTest {
    AGVId id = new AGVId("123");

    Weight weightCap = Weight.valueOf(200.0, WeightUnit.KG);
    Volume volumeCap = Volume.valueOf(2, VolumeUnit.M);
    AGVModel model = AGVModel.valueOf("AB123");
    AGVShortDescription description = AGVShortDescription.valueOf(RandomString.generateRandomString(20));

    Coordinates coordinates1 = Coordinates.valueOf(10, 15);
    Coordinates coordinates2 = Coordinates.valueOf(11, 15);
    Coordinates coordinates3 = Coordinates.valueOf(10, 16);

    Square accessSquare = Square.valueOf(coordinates1, null, null, Accessibility.Up);
    Square square1 = Square.valueOf(coordinates2, null, null, null);
    Square square2 = Square.valueOf(coordinates3, null, null, null);

    AGVDock dock = new AGVDock(accessSquare, square1, square2);

    AGVBatteryStatus batteryStatus = AGVBatteryStatus.valueOf(120);

    AGVBuilder agvBuilder = new AGVBuilder();

    AGV agv = agvBuilder.withId("123")
            .ofModel("AB123")
            .ofDescription(RandomString.generateRandomString(20))
            .ofWeightCapacity(200000)
            .ofVolumeCapacity(2000)
            .ofBaseLocation(dock)
            .ofBatteryStatus(120)
            .build();

    AGV agvCopy = agvBuilder.withId("123")
            .ofModel("AB123")
            .ofDescription(RandomString.generateRandomString(20))
            .ofWeightCapacity(200000)
            .ofVolumeCapacity(2000)
            .ofBaseLocation(dock)
            .ofBatteryStatus(120)
            .build();

    AGV agvDiff = agvBuilder.withId("456")
            .ofModel("AB123")
            .ofDescription(RandomString.generateRandomString(20))
            .ofWeightCapacity(200000)
            .ofVolumeCapacity(2000)
            .ofBaseLocation(dock)
            .ofBatteryStatus(120)
            .build();

    @Test
    void constructorValidData() {
        AGV agv = new AGV(id, model, description, weightCap, volumeCap, dock, batteryStatus, AGVStatus.Free);
        assertNotNull(agv);
    }

    @Test
    void constructorInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> new AGV(id, null, description, weightCap, volumeCap, dock, batteryStatus, AGVStatus.Free));
        assertThrows(IllegalArgumentException.class, () -> new AGV(id, model, null, weightCap, volumeCap, dock, batteryStatus, AGVStatus.Free));
        assertThrows(IllegalArgumentException.class, () -> new AGV(id, model, description, null, volumeCap, dock, batteryStatus, AGVStatus.Free));
        assertThrows(IllegalArgumentException.class, () -> new AGV(id, model, description, weightCap, null, dock, batteryStatus, AGVStatus.Free));
        assertThrows(IllegalArgumentException.class, () -> new AGV(id, model, description, weightCap, volumeCap, null, batteryStatus, AGVStatus.Free));
        assertThrows(IllegalArgumentException.class, () -> new AGV(id, model, description, weightCap, volumeCap, dock, null, AGVStatus.Free));
        assertThrows(IllegalArgumentException.class, () -> new AGV(id, null, null, null, null, null, null, AGVStatus.Free));
        assertThrows(IllegalArgumentException.class, () -> new AGV(null, null, null, null, null, null, null, null));
    }

    @Test
    void ensureTransitionFreeToServingGivenOrder() {
        //AGV with status FREE
        AGV agv = new AGV(id, model, description, weightCap, volumeCap, dock, batteryStatus, AGVStatus.Free);

        AGVTask task = new AGVTask(TestUtils.generateDummyOrder());
        agv.assignTask(task);

        assertTrue(agv.isServingOrder());
    }

    @Test
    void ensureTransitionServingGivenOrderToFree() {
        //AGV with status FREE
        AGV agv = new AGV(id, model, description, weightCap, volumeCap, dock, batteryStatus, AGVStatus.Free);

        AGVTask task = new AGVTask(TestUtils.generateDummyOrder());
        //AGV transitions to SERVING_GIVEN_ORDER
        agv.assignTask(task);
        //AGV transitions to FREE
        agv.finishTask();

        assertTrue(agv.isFree());
    }

    @Test
    void ensureTransitionServingOrderToServingOrderInvalid() {
        //AGV with status FREE
        AGV agv = new AGV(id, model, description, weightCap, volumeCap, dock, batteryStatus, AGVStatus.Free);

        AGVTask task = new AGVTask(TestUtils.generateDummyOrder());
        //AGV transitions to SERVING_GIVEN_ORDER
        agv.assignTask(task);

        assertThrows(IllegalArgumentException.class, () -> {agv.assignTask(task);});
    }

    @Test
    void sameAs() {
        assertTrue(agv.sameAs(agv));
        assertTrue(agv.sameAs(agvCopy));
        assertTrue(agvCopy.sameAs(agv));
        assertFalse(agvDiff.sameAs(agv));
        assertFalse(agv.sameAs(agvDiff));
    }

    @Test
    void identity() {
        assertEquals(id, agv.identity());
    }

    @Test
    void emptyConstructor() {
        AGV empty = new AGV();
        assertNotNull(empty);
    }

    @Test
    void testModel(){
        assertEquals(model, agv.model());
    }

    @Test
    void testShortDescription(){
        assertNotNull(agv.shortDescription());
    }

    @Test
    void testBaseLocation(){
        assertEquals(agv.baseLocation(), dock);
    }

    @Test
    void testMaxWheight(){
        assertEquals(agv.maxWeightCapacity(), weightCap);
    }

    @Test
    void testMaxVolume(){
        assertEquals(agv.maxVolumeCapacity(), Volume.valueOf("2000 MM"));
    }

    @Test
    void testBatteryStatus(){
        assertEquals(agv.batteryStatus(), AGVBatteryStatus.valueOf(120));
    }

    @Test
    void testCurrentStatus(){
        assertEquals(agv.currentStatus(), AGVStatus.Free);
    }

    @Test
    void testCurrentPosition(){
        assertEquals(agv.currentPosition(), dock.beginSquare());
    }

    @Test
    void testTask(){
        assertNull(agv.task());
    }
}