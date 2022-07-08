package eapli.base.surveymanagement.controller;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import eapli.base.surveymanagement.domain.Answer;

import java.io.IOException;

public class AnsweredQuestionnaireJSONAdapter extends TypeAdapter<Answer> {

    @Override
    public void write(JsonWriter jsonWriter, Answer/*answer*/ answer) throws IOException {
        jsonWriter.beginObject();
        if(answer.values().size() == 1){
            jsonWriter.name("Answer"); //answer
        }else{
            jsonWriter.beginArray();
            for(int i=0; i<answer.values().size(); i++){
                jsonWriter.value(answer.values().get(i));
            }
            jsonWriter.endArray();
        }

        //if para se tiver mais que uma resposta
        jsonWriter.endObject();
    }

    @Override
    public Answer read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
