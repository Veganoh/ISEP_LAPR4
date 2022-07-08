package eapli.base.surveymanagement.application;

import eapli.base.surveymanagement.domain.Answer;
import org.antlr.v4.runtime.RuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class answersParser extends questionnairesBaseVisitor<Answer> {
    private final List<Answer> answers = new ArrayList<>();
    private String currentSection;
    private String currentQuestion;

    @Override
    public Answer visitSection_answered(questionnairesParser.Section_answeredContext ctx) {
        currentSection = ctx.section_id().getText();
        return super.visitSection_answered(ctx);
    }

    @Override
    public Answer visitQuestion_id(questionnairesParser.Question_idContext ctx) {
        this.currentQuestion = ctx.quest_id.getText();
        return super.visitQuestion_id(ctx);
    }

    @Override
    public Answer visitAnswer(questionnairesParser.AnswerContext ctx) {
        Answer a;

        if (ctx.numeric_answer != null) a = Answer.valueOf(currentSection, currentQuestion, ctx.numeric_answer.getText());
        else if (ctx.text_answer != null) a = Answer.valueOf(currentSection, currentQuestion, ctx.text_answer.getText());
        else a = Answer.valueOf(currentSection, currentQuestion,
                    ctx.list_answer.text()
                            .stream()
                            .map(RuleContext::getText)
                            .collect(Collectors.toList()));

        if(answers.contains(a)) answers.get(answers.indexOf(a)).add(a);
        else answers.add(a);

        return super.visitAnswer(ctx);
    }

    public List<Answer> parseAnswers() {
        return answers;
    }
}
