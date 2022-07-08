package eapli.base.agvmanagement.domain;


public enum AGVStatus {
    Free("Free"),
    Charging("Charging"),
    ServingGivenOrder("Serving Given Order"),
    InMaintenance("In Maintenance");

    private final String status;

    AGVStatus(String status) {
        this.status = status;
    }

    public static AGVStatus valueOfStatus(String status) {
        for (AGVStatus e : values())
            if (e.status.equals(status))
                return e;

        return null;
    }
}
