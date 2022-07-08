package eapli.base.app.server.agvmanager.requestHandlers.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import eapli.base.agvdockmanagement.domain.AGVDock;
import eapli.base.agvtaskmanagement.domain.AGVTask;

import java.io.IOException;

public class AGVTaskJSONAdapter extends TypeAdapter<AGVTask> {

    @Override
    public void write(JsonWriter jsonWriter, AGVTask agvTask) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("status");
        jsonWriter.value("task assigned");
        jsonWriter.endObject();
    }

    @Override
    public AGVTask read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
