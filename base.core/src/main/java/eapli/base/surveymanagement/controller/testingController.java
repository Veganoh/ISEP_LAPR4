package eapli.base.surveymanagement.controller;


import eapli.base.surveymanagement.application.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class testingController {

    public static void main (String args[]) throws IOException {
        System.out.println(new testingController().validate());
    }

    public boolean validate() throws IOException {
        CharStream in = CharStreams.fromFileName("configfiles/questionnaire.json");
        questionnairesLexer lexer = new questionnairesLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        questionnairesParser parser = new questionnairesParser(tokens);

        answerQuestionnaire validator = new answerQuestionnaire();
        validator.visit(parser.questionnaires());
        return true;
    }
}
