package eapli.base.app.other.console.warehouseEmployee.agvConfiguration;

import eapli.base.agvmanagement.domain.AGV;
import eapli.framework.visitor.Visitor;

public class AGVPrinter implements Visitor<AGV> {
    @Override
    public void visit(final AGV visitedAgv) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("AGV ID: %s\n\t", visitedAgv.identity().toString()))
                .append(String.format("Base Location - %s", visitedAgv.baseLocation().toString()))
                .append("\n\tModel: ")
                .append(visitedAgv.model().toString())
                .append("\n\tShort Description: ")
                .append(visitedAgv.shortDescription().toString())
                .append("\n\tMaximum weight capacity: ")
                .append(visitedAgv.maxWeightCapacity().toString())
                .append("\n\tMaximum volume capacity: ")
                .append(visitedAgv.maxVolumeCapacity().toString())
                .append("\n\t")
                .append(visitedAgv.batteryStatus().toString())
                .append("\n\tCurrent AGV Status: ")
                .append(visitedAgv.currentStatus().toString())
                .append("\n\tCurrent Position: ")
                .append(visitedAgv.currentPosition().toString());

        System.out.println(sb);
    }
}
