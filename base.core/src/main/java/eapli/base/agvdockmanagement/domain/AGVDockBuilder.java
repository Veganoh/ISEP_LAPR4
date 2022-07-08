package eapli.base.agvdockmanagement.domain;

import eapli.base.agvmanagermanagement.domain.Accessibility;
import eapli.base.agvmanagermanagement.domain.Coordinates;
import eapli.base.agvmanagermanagement.domain.Square;
import eapli.framework.domain.model.DomainFactory;

public class AGVDockBuilder implements DomainFactory<AGVDock> {
    /**
     * The AGV instance being built
     */
    private AGVDock theAGVDock;

    /**
     * The AGVDock's Identifier
     */
    private AGVDockId dockId;

    /**
     * The Square where the AGVDock Begins
     */
    private Square beginSquare;

    /**
     * The Square where the AGVDock Ends
     */
    private Square endSquare;

    /**
     * The Square that defines the AGV Dock's depth
     */
    private Square depthSquare;

    /**
     * Creates and Adds an AGVDockId with the given data to the AGV Dock currently being built.
     *
     * @param id The String used to create the AGVDockId
     * @return The AGVDockBuilder with the current AGV being built
     */
    public AGVDockBuilder ofId(String id) {
        this.dockId = new AGVDockId(id);
        return this;
    }

//    public AGVDockBuilder ofBeginningSquare(int wSquare, int lSquare, String accessibility) {
//
//    }

    /**
     * Creates and Adds a Square representing the beginning of the AGV Dock,
     * with the given data, to the AGV Dock currently being built.
     *
     * @param wSquare the width Coordinate of the warehouse
     * @param lSquare the length Coordinate of the warehouse
     * @param accessibility the Accessibility direction of the AGVDock
     * @return The AGVDockBuilder with the current AGV being built
     */
    public AGVDockBuilder ofBeginningSquare(int wSquare, int lSquare, String accessibility) {
        Coordinates coords = Coordinates.valueOf(wSquare, lSquare);
        Accessibility access = Accessibility.valueOfLabel(accessibility);

        if (access == null) {
            try {
                access = Accessibility.valueOf(accessibility);
            } catch (Exception e) {
                throw new IllegalArgumentException("No such Accessibility with label " + accessibility + "!");
            }
        }

        this.beginSquare = Square.valueOf(coords, access);

        return this;
    }

    /**
     * Creates and Adds a Square representing the end of the AGV Dock,
     * with the given data, to the AGV Dock currently being built.
     *
     * @param wSquare the width Coordinate of the warehouse
     * @param lSquare the length Coordinate of the warehouse
     * @return The AGVDockBuilder with the current AGV being built
     */
    public AGVDockBuilder ofEndingSquare(int wSquare, int lSquare) {
        Coordinates coords = Coordinates.valueOf(wSquare, lSquare);
        this.endSquare = Square.valueOf(coords);

        return this;
    }

    /**
     * Creates and Adds a Square representing the depth of the AGV Dock,
     * with the given data, to the AGV Dock currently being built.
     *
     * @param wSquare the width Coordinate of the warehouse
     * @param lSquare the length Coordinate of the warehouse
     * @return The AGVDockBuilder with the current AGV being built
     */
    public AGVDockBuilder ofDepthSquare(int wSquare, int lSquare) {
        Coordinates coords = Coordinates.valueOf(wSquare, lSquare);
        this.depthSquare = Square.valueOf(coords);

        return this;
    }

    /**
     * Tries to build an AGVDock instance with the given data. If mandatory data is missing throws and IllegalStateException
     * and doesn't build the AGVDock.
     *
     * @return And instance of AGVDock with the given data.
     */
    private AGVDock buildOrThrow() {
        if (theAGVDock != null)
            return theAGVDock;

        if (dockId == null ||
                beginSquare == null ||
                endSquare == null ||
                depthSquare == null) {
            dockId = null;
            beginSquare = null;
            endSquare = null;
            depthSquare = null;
            throw new IllegalStateException();
        }


        theAGVDock = new AGVDock(dockId, beginSquare, endSquare, depthSquare);
        return theAGVDock;
    }

    /**
     * Builds an AGVDock with the specified data
     *
     * @return An instance of AGVDock
     */
    @Override
    public AGVDock build() {
        final AGVDock ret = buildOrThrow();

        theAGVDock = null;

        return ret;
    }
}
