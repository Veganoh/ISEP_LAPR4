package eapli.base.agvmanagement.domain.DTOs;

public class AGVDigitalTwinDTO {
    public String agvId;

    public int maxSpeed;

    public int batteryLife;

    public int currPositionX;

    public int currPositionY;

    public int dockX;

    public int dockY;

    public AGVDigitalTwinDTO() {}

    public AGVDigitalTwinDTO(String agvId, int maxSpeed, int batteryLife, int currPositionX, int currPositionY, int dockX, int dockY) {
        this.agvId = agvId;
        this.maxSpeed = maxSpeed;
        this.batteryLife = batteryLife;
        this.currPositionX = currPositionX;
        this.currPositionY = currPositionY;
        this.dockX = dockX;
        this.dockY = dockY;
    }
}
