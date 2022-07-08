package eapli.base.app.server.agvmanager.requestHandlers.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import eapli.base.agvdockmanagement.domain.AGVDock;

import java.io.IOException;

public class AGVDockJSONAdapter extends TypeAdapter<AGVDock> {

    @Override
    public void write(JsonWriter jsonWriter, AGVDock agvDock) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("id");
        jsonWriter.value(agvDock.identity().toString());
        jsonWriter.endObject();
    }

    @Override
    public AGVDock read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
