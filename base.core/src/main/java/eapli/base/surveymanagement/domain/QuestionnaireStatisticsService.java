package eapli.base.surveymanagement.domain;

import eapli.base.surveymanagement.application.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.*;

public class QuestionnaireStatisticsService {

    private final int totalSize;
    private final int answeredSize;
    private final String report;


    public QuestionnaireStatisticsService(Questionnaire questionnaire, List<AnsweredQuestionnaire> answeredQuestionnaires) {
        //process universe data
        totalSize = questionnaire.audienceSize();
        answeredSize = answeredQuestionnaires.size();

        //process questions for statistic computing
        List<Question> questions = parseQuestions(questionnaire);


        //process answers for statistic computing
        Map<QuestionID, List<Answer>> answersByID = new HashMap<>();

        for(AnsweredQuestionnaire a : answeredQuestionnaires) {
           List<Answer> answers = parseAnswers(a);
           for(Answer answer :answers)
               if (answersByID.containsKey(answer.questionID())) answersByID.get(answer.questionID()).add(answer);
                       else answersByID.put(answer.questionID(), new ArrayList<Answer>(List.of(answer)));
        }
        //generate and print statistics
        StringBuilder sb = new StringBuilder();

        for (Question q : questions) {
            QuestionStatisticsService stats = new QuestionStatisticsService(q, answersByID.get(q.id())==null ? new ArrayList<>() : answersByID.get(q.id()));
            print(q.id(), stats, sb);
        }

        this.report = sb.toString();
    }

    private List<Question> parseQuestions(Questionnaire questionnaire) {
        CharStream in = CharStreams.fromString(questionnaire.text());
        questionnairesLexer lexer = new questionnairesLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        questionnairesParser questionnairesParser = new questionnairesParser(tokens);

        questionsParser parser = new questionsParser();

        parser.visit(questionnairesParser.questionnaires());

       return parser.parseQuestions();
    }

    private List<Answer> parseAnswers(AnsweredQuestionnaire questionnaire) {
        CharStream in = CharStreams.fromString(questionnaire.text());
        questionnairesLexer lexer = new questionnairesLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        questionnairesParser questionnairesParser = new questionnairesParser(tokens);

        answersParser parser = new answersParser();

        parser.visit(questionnairesParser.questionnaires());

        return parser.parseAnswers();
    }

    private void print(QuestionID id, QuestionStatisticsService questionStatistics, StringBuilder sb) {
        sb.append(id);
        sb.append(questionStatistics.print());
        sb.append("\n");
    }
    public String print() {
        return String.format("Universe Size: %d\n" +
                "Number of Responses Obtained: %d\n" +
                "%% of responses obtained: %.0f%%\n\n" +
                "%s", totalSize, answeredSize, answeredSize/(double) totalSize*100, report);
    }
}
