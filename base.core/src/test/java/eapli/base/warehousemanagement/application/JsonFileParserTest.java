package eapli.base.warehousemanagement.application;

import eapli.base.common.domain.Description;
import eapli.base.common.domain.Length;
import eapli.base.common.domain.VolumeUnit;
import eapli.base.common.domain.Width;
import eapli.base.warehousemanagement.domain.Shelf;
import eapli.base.warehousemanagement.domain.SquareSize;
import eapli.base.warehousemanagement.domain.Warehouse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonFileParserTest {

    @Test
    void testFileNotFound() {
        JsonFileParser fileParser = new JsonFileParser("none");
        assertThrows(IllegalArgumentException.class, fileParser::parse);
    }

    @Test
    void testFileInvalidFormat() {
        JsonFileParser fileParser = new JsonFileParser("src/test/java/eapli/base/warehousemanagement/application/JsonFileParserTestFiles/test_warehouse_invalid.json");
        assertThrows(IllegalArgumentException.class, fileParser::parse);
    }

    @Test
    void testFileIncomplete() {
        JsonFileParser fileParser1 = new JsonFileParser("src/test/java/eapli/base/warehousemanagement/application/JsonFileParserTestFiles/test_warehouse_incomplete_noRows.json");
        assertThrows(IllegalArgumentException.class, fileParser1::parse);

        JsonFileParser fileParser2 = new JsonFileParser("src/test/java/eapli/base/warehousemanagement/application/JsonFileParserTestFiles/test_warehouse_incomplete_noShelves.json");
        assertThrows(IllegalArgumentException.class, fileParser2::parse);

        JsonFileParser fileParser3 = new JsonFileParser("src/test/java/eapli/base/warehousemanagement/application/JsonFileParserTestFiles/test_warehouse_incomplete_noAisles.json");
        assertThrows(IllegalArgumentException.class, fileParser3::parse);
    }

    @Test
    void testFileComplete() throws IOException {
        JsonFileParser fileParser1 = new JsonFileParser("src/test/java/eapli/base/warehousemanagement/application/JsonFileParserTestFiles/test_warehouse_complete.json");
        Warehouse parsedWarehouse1 = fileParser1.parse();

        assertNotNull(parsedWarehouse1);

        assertEquals(parsedWarehouse1.description(), Description.valueOf("Warehouse Example 1"));
        assertEquals(parsedWarehouse1.length(), Length.valueOf(800));
        assertEquals(parsedWarehouse1.width(), Width.valueOf(720));
        assertEquals(parsedWarehouse1.squareSize(), SquareSize.valueOf(40));
        assertEquals(parsedWarehouse1.unit(), VolumeUnit.CM);
        assertEquals(parsedWarehouse1.shelves().size(), 61);

        assertNotNull(parsedWarehouse1.agvManager());
        assertEquals(parsedWarehouse1.agvManager().numberOfAGVDock(), 6);
    }

    @Test
    void testFileCompleteReverseWidthLength() throws IOException {
        JsonFileParser fileParser = new JsonFileParser("src/test/java/eapli/base/warehousemanagement/application/JsonFileParserTestFiles/test_warehouse_complete_reverse.json");
        Warehouse parsedWarehouse1 = fileParser.parse();

        assertNotNull(parsedWarehouse1);

        assertNotNull(parsedWarehouse1.agvManager());
        assertEquals(parsedWarehouse1.agvManager().numberOfAGVDock(), 2);
        assertEquals(parsedWarehouse1.agvManager().numberOfSquares(), 22);
    }

    @Test
    void testShelfReload() throws IOException {
        JsonFileParser fileParser1 = new JsonFileParser("src/test/java/eapli/base/warehousemanagement/application/JsonFileParserTestFiles/test_warehouse_complete.json");
        Warehouse parsedWarehouse1 = fileParser1.parse();
        List<Shelf> shelfList = new ArrayList<>(parsedWarehouse1.shelves());
        parsedWarehouse1.loadShelves();
        List<Shelf> reloadedShelfList = new ArrayList<>(parsedWarehouse1.shelves());

        assertEquals(reloadedShelfList, shelfList);
    }
}