package eapli.base.warehousemanagement.application;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import eapli.base.agvmanagermanagement.domain.Accessibility;
import eapli.base.warehousemanagement.domain.Shelf;
import eapli.base.warehousemanagement.domain.WareHousePlant;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.framework.io.util.Files;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class JsonFileParser {

    private class JSONWarehouse {
        private String Warehouse;
        private int Length;
        private int Width;
        private int Square;
        private String Unit;
        private List<JSONAisle> Aisles;
        private List<JSONAGVDock> AGVDocks;

        private class JSONAisle {
            private int Id;
            private JSONSquare begin;
            private JSONSquare end;
            private JSONSquare depth;
            private String accessibility;

            private Accessibility accessibilityLabel() {
                return Accessibility.valueOfLabel(accessibility);
            }

            private List<JSONRow> rows;
        }

        private class JSONSquare {
            private int lsquare;
            private int wsquare;
        }

        private class JSONRow {
            private int Id;
            private JSONSquare begin;
            private JSONSquare end;
            private int shelves;
        }

        private class JSONAGVDock {
            private String Id;
            private JSONSquare begin;
            private JSONSquare end;
            private JSONSquare depth;
            private String accessibility;

            private Accessibility accessibilityLabel() {
                return Accessibility.valueOfLabel(accessibility);
            }
        }
    }

    private final Gson parser = new Gson();
    private final File file;

    public JsonFileParser() {
        this.file = null;
    }

    public JsonFileParser(String filepath) {
        this.file = new File(filepath);
    }

    private JSONWarehouse extractRawData(String fileContent) {
        try {
            return parser.fromJson(fileContent, JSONWarehouse.class);
        } catch (JsonSyntaxException ex) {
            throw new IllegalArgumentException("Invalid format file!");
        }

    }

    private String processWarehouseRawData(JSONWarehouse raw) {
        try {
            StringBuilder preprocessedJsonBuilder = new StringBuilder();
            preprocessedJsonBuilder.append("{\"warehouseMeasurements\":{\"squareSize\":{\"squareSize\":").append(raw.Square).append("},");
            preprocessedJsonBuilder.append("\"wlength\":{\"wlength\":").append(raw.Length).append("},");
            preprocessedJsonBuilder.append("\"width\":{\"width\":").append(raw.Width).append("},");
            preprocessedJsonBuilder.append("\"unit\":\"").append(raw.Unit.toUpperCase()).append("\"},");
            preprocessedJsonBuilder.append("\"warehouseDescription\":{\"description\":\"").append(raw.Warehouse).append("\"},");
            preprocessedJsonBuilder.append("\"shelves\":[");
            if (raw.Aisles.isEmpty()) throw new NullPointerException("No aisles found.");
            for (JSONWarehouse.JSONAisle aisle : raw.Aisles) {
                if (aisle.rows.isEmpty()) throw new NullPointerException("No rows found");
                for (JSONWarehouse.JSONRow row : aisle.rows) {
                    if (row.shelves == 0) throw new NullPointerException("No shelves found");
                    for (int shelf = 1; shelf <= row.shelves; shelf++)
                        preprocessedJsonBuilder.append(
                                "{\"aisleId\":{\"identifier\":\"").append(aisle.Id).append("\"}," +
                                "\"rowId\":{\"identifier\":\"").append(row.Id).append("\"}," +
                                "\"binSize\":{\"binSize\":0}," +
                                "\"shelfNumber\":{\"identifier\":\"").append(shelf).append("\"}},");
                }
            }
            preprocessedJsonBuilder.deleteCharAt(preprocessedJsonBuilder.length() - 1);
            preprocessedJsonBuilder.append("]}");

            return preprocessedJsonBuilder.toString();
        } catch (NullPointerException | JsonSyntaxException ex) {
            throw new IllegalArgumentException("File does not contain full warehouse map.");
        }
    }

    private String processAGVRawData(JSONWarehouse raw) {
        try {
            StringBuilder preprocessedJsonBuilder = new StringBuilder();
            preprocessedJsonBuilder.append("{\"squareList\":[");
            for (JSONWarehouse.JSONAisle aisle : raw.Aisles)
                for (JSONWarehouse.JSONRow row : aisle.rows) {

                    int rowStart, rowEnd, depthStart, depthEnd;
                    if (row.begin.wsquare == row.end.wsquare) {
                        rowStart = Math.min(row.begin.lsquare, row.end.lsquare);
                        rowEnd = Math.max(row.begin.lsquare, row.end.lsquare);
                        depthStart = Math.min(row.begin.wsquare, aisle.depth.wsquare);
                        depthEnd = Math.max(row.begin.wsquare, aisle.depth.wsquare);
                    } else {
                        rowStart = Math.min(row.begin.wsquare, row.end.wsquare);
                        rowEnd = Math.max(row.begin.wsquare, row.end.wsquare);
                        depthStart = Math.min(row.begin.lsquare, aisle.depth.lsquare);
                        depthEnd = Math.max(row.begin.lsquare, aisle.depth.lsquare);
                    }

                    for (int lsquare = rowStart; lsquare <= rowEnd; lsquare++)
                        for (int wsquare = depthStart; wsquare <= depthEnd; wsquare++)
                            preprocessedJsonBuilder.append(
                                    "{\"coordinates\":{\"wSquare\":").append(wsquare).append("," +
                                    "\"lSquare\":").append(lsquare).append("}," +
                                    "\"rowId\":{\"identifier\":\"").append(row.Id).append("\"}," +
                                    "\"aisleId\":{\"identifier\":\"").append(aisle.Id).append("\"}," +
                                    "\"accessibility\":\"").append(aisle.accessibilityLabel()).append("\"},");
                }
            preprocessedJsonBuilder.deleteCharAt(preprocessedJsonBuilder.length() - 1);
            preprocessedJsonBuilder.append("],\"agvList\":[],\"agvDockList\":[");
            for (JSONWarehouse.JSONAGVDock dock : raw.AGVDocks)
                preprocessedJsonBuilder.append(
                        "{\"id\":{\"id\":\"").append(dock.Id).append("\"}," +
                        "\"availability\":{\"availability\":true}," +
                        "\"beginSquare\":{\"coordinates\":{\"wSquare\":").append(dock.begin.wsquare).append("," +
                        "\"lSquare\":").append(dock.begin.lsquare).append("}," +
                        "\"accessibility\":\"").append(dock.accessibilityLabel()).append("\"}," +
                        "\"endSquare\":{\"coordinates\":{\"wSquare\":").append(dock.end.wsquare).append("," +
                        "\"lSquare\":").append(dock.end.lsquare).append("}," +
                        "\"accessibility\":\"").append(dock.accessibilityLabel()).append("\"}," +
                        "\"depthSquare\":{\"coordinates\":{\"wSquare\":").append(dock.depth.wsquare).append("," +
                        "\"lSquare\":").append(dock.depth.lsquare).append("}," +
                        "\"accessibility\":\"").append(dock.accessibilityLabel()).append("\"}},");
            preprocessedJsonBuilder.deleteCharAt(preprocessedJsonBuilder.length() - 1);
            preprocessedJsonBuilder.append("]}");

            return preprocessedJsonBuilder.toString();
        } catch (NullPointerException | JsonSyntaxException ex) {
            throw new IllegalArgumentException("File does not contain full warehouse map.");
        }
    }

    private String mergeProcessedData(String warehouseProcessedData, String agvManagerProcessedData) {
        StringBuilder mergedData = new StringBuilder();
        mergedData.append(warehouseProcessedData);
        mergedData.deleteCharAt(mergedData.length() - 1);
        mergedData.append(",\"agvManager\":").append(agvManagerProcessedData).append("}");

        return mergedData.toString();
    }

    private Warehouse parseWarehouseData(String processedData) {
        return parser.fromJson(processedData, Warehouse.class);
    }

    public Warehouse parse()  {
        try {
            String fileContent = Files.textFrom(new FileInputStream(file));
            JSONWarehouse rawData = extractRawData(fileContent);
            String warehouseProcessedData = processWarehouseRawData(rawData);
            String agvManagerProcessedData = processAGVRawData(rawData);
            String processedData = mergeProcessedData(warehouseProcessedData, agvManagerProcessedData);
            Warehouse warehouse = parseWarehouseData(processedData);
            warehouse.updatePlant(WareHousePlant.valueOf(fileContent));
            return warehouse;
        } catch (IOException e) {
            throw new IllegalArgumentException("File was not found.");
        }
    }

    public void shelves(Warehouse warehouse)  {
        JSONWarehouse raw = extractRawData(warehouse.plant().toString());
        if (raw.Aisles.isEmpty()) throw new NullPointerException("No aisles found.");
        for (JSONWarehouse.JSONAisle aisle : raw.Aisles) {
            if (aisle.rows.isEmpty()) throw new NullPointerException("No rows found");
            for (JSONWarehouse.JSONRow row : aisle.rows) {
                if (row.shelves == 0) throw new NullPointerException("No shelves found");
                for (int shelf = 1; shelf <= row.shelves; shelf++)
                    warehouse.addShelf(new Shelf(String.valueOf(aisle.Id), String.valueOf(row.Id), String.valueOf(shelf)));
            }
        }
    }
}
