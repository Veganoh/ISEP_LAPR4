package eapli.base.agvdockmanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AGVDockBuilderTest {
    AGVDockBuilder dockBuilder = new AGVDockBuilder();

    @Test
    void buildDockValidDataTest() {
        AGVDock dock = dockBuilder.ofId("D1")
                .ofBeginningSquare(10, 13, "w+")
                .ofEndingSquare(10, 15)
                .ofDepthSquare(11, 13)
                .build();

        assertNotNull(dock);

        dock = dockBuilder.ofId("D1")
                .ofBeginningSquare(10, 13, "Up")
                .ofEndingSquare(10, 15)
                .ofDepthSquare(11, 13)
                .build();

        assertNotNull(dock);
    }

    @Test
    void buildDockMissingDataTest() {
        assertThrows(IllegalStateException.class, ()->dockBuilder.ofId("D1")
                .ofBeginningSquare(10, 13, "w+")
                .ofEndingSquare(10, 15)
                .build());

        assertThrows(IllegalStateException.class, ()->dockBuilder.ofId("D1")
                .ofBeginningSquare(10, 13, "w+")
                .ofDepthSquare(11, 13)
                .build());

        assertThrows(IllegalStateException.class, ()->dockBuilder.ofId("D1")
                .ofEndingSquare(10, 15)
                .ofDepthSquare(11, 13)
                .build());

        assertThrows(IllegalStateException.class, ()->dockBuilder.ofBeginningSquare(10, 13, "w+")
                .ofEndingSquare(10, 15)
                .ofDepthSquare(11, 13)
                .build());

        assertThrows(IllegalStateException.class, ()->dockBuilder.ofId("D1").build());

        assertThrows(IllegalStateException.class, ()->dockBuilder.ofBeginningSquare(10, 13, "w+").build());

        assertThrows(IllegalStateException.class, ()->dockBuilder.ofEndingSquare(10, 15).build());

        assertThrows(IllegalStateException.class, ()->dockBuilder.ofDepthSquare(11, 13).build());
    }
}