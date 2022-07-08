package eapli.base.app.server.agvmanager.requestHandlers.utils;

import eapli.base.agvmanagement.domain.AGV;

import java.util.List;

public class AGVListJSON {
    List<AGV> AGVList;

    public AGVListJSON(List<AGV> AGVList) {
        this.AGVList = AGVList;
    }
}
