package eapli.base.surveymanagement.application;

import eapli.base.surveymanagement.domain.Question;
import org.antlr.v4.runtime.RuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class questionsParser extends questionnairesBaseVisitor<Question>{
    private List<Question> questions = new ArrayList<>();
    private String currentSection;
    private String currentQuestion;

    @Override
    public Question visitSection(questionnairesParser.SectionContext ctx) {
        this.currentSection = ctx.section_id().getText();
        return super.visitSection(ctx);
    }

    @Override
    public Question visitQuestion_id(questionnairesParser.Question_idContext ctx) {
        this.currentQuestion = ctx.quest_id.getText();
        return super.visitQuestion_id(ctx);
    }

    @Override
    public Question visitQuestion_type(questionnairesParser.Question_typeContext ctx) {
        String currentQuestionType = ctx.type.getText();

        if(ctx.question_list==null)
            questions.add(Question.valueOf(currentSection, currentQuestion, currentQuestionType));
        else {
            List<String> currentOptions = ctx.question_list.list.text()
                    .stream()
                    .map(RuleContext::getText)
                    .collect(Collectors.toList());
            questions.add(Question.valueOf(currentSection, currentQuestion, currentQuestionType, currentOptions));
        }
        return super.visitQuestion_type(ctx);
    }

    public List<Question> parseQuestions() {
        return questions;
    }

}
