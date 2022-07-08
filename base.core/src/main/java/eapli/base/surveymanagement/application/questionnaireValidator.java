package eapli.base.surveymanagement.application;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.misc.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class questionnaireValidator extends questionnairesBaseVisitor<String> {
    /**
     * The questionnaire's ID
     */
    private String questionnaireId;

    /**
     * The Target Audience Rules
     */
    private final List<List<Pair<String, String>>> targetRules = new ArrayList<>();

    /**
     * The questionnaire's content:
     *      Hashmap -> Hashmap -> Pair
     *      Section ID -> Question ID -> (Question Type // Answer)
     */
    private final HashMap<String, HashMap<String, Pair<String, List<String>>>> sectionsMap = new HashMap<>();

    /**
     * Flag that determines if the given questioner is valid or not
     */
    private boolean validFlag = true;

    //Temporary variables to save values needed to validate the current place the visitor is in
    private final List<String> questionScale = new ArrayList<>();
    private final List<String> questionOptions = new ArrayList<>();
    private String currentSection;
    private String sectionObligatoriness;
    private String currentQuestion;
    private String questionType;
    private Integer questionMaxOptions;
    private Integer questionMaxChars;
    List<Pair<String, String>> ruleConditions;

    //Counters for the repeatability validation
    private int contentCounter;
    private int neededRepeatabiltyCount;

    //Flags used for current validation
    private boolean answeredQuestionaire = false;
    private boolean repeatability = false;
    private boolean repeatabilityValid = true;

    @Override
    public String visitQuestionnaires_id(questionnairesParser.Questionnaires_idContext ctx) {
        return questionnaireId = ctx.id.getText();
    }

    @Override
    public String visitTarget_rule(questionnairesParser.Target_ruleContext ctx) {
        if (ruleConditions != null && !ruleConditions.isEmpty())
            targetRules.add(ruleConditions);

        ruleConditions = new ArrayList<>();

        return super.visitTarget_rule(ctx);
    }

    @Override
    public String visitRule_condition(questionnairesParser.Rule_conditionContext ctx) {
        String targetType = ctx.type.getText().replace("\"", "");

        String rule = "";

        if (targetType.equals("Age Group")) {
            if (ctx.minimum != null) {
                rule = String.join("-", ctx.minimum.getText(), ctx.maximum.getText());
            } else if (ctx.older != null){
                rule = String.join("-", ctx.older.getText(), "100");
            } else {
                rule = String.join("-", "0", ctx.younger.getText());
            }
        } else {
            rule = ctx.target.getText().replace("\"", "");
        }

        Pair<String, String> targetPair = new Pair<>(targetType, rule);

        ruleConditions.add(targetPair);

        return super.visitRule_condition(ctx);
    }

    @Override
    public String visitQuestionnaires_title(questionnairesParser.Questionnaires_titleContext ctx) {
        if (ruleConditions != null && !ruleConditions.isEmpty())
            targetRules.add(ruleConditions);

        return super.visitQuestionnaires_title(ctx);
    }

    @Override
    public String visitQuestion_type(questionnairesParser.Question_typeContext ctx) {
        if(ctx.question_list != null) visitOption(ctx.question_list.list, this.questionOptions);
        if(ctx.question_scale != null) visitOption(ctx.question_scale.list, this.questionScale);
        this.questionMaxOptions = ctx.max_options == null ? null : Integer.parseInt(ctx.max_options.getText());
        this.questionMaxChars = ctx.max_chars == null ? null : Integer.parseInt(ctx.max_chars.getText());

        return (this.questionType = ctx.type.getText());
    }

    public void visitOption(questionnairesParser.OptionContext ctx, List<String> list) {
        list.clear();
        list.addAll(ctx.text().stream().map(RuleContext::getText).collect(Collectors.toList()));
    }

    @Override
    public String visitAnswer(questionnairesParser.AnswerContext ctx) {
        switch (questionType) {
            case "\"Numeric\"":
                return visitNumericQuestion(ctx);
            case "\"Free Text\"":
                return visitFreeTextQuestion(ctx);
            case "\"Single Choice\"":
                return visitSingleChoiceQuestion(ctx);
            case "\"Single Choice With Input Value\"":
                return visitSingleChoiceQuestionInput(ctx);
            case "\"Multiple Choice\"":
                return visitMultipleChoiceQuestion(ctx);
            case "\"Multiple Choice With Input Value\"":
                return visitMultipleChoiceQuestionInput(ctx);
            case "\"Sorting Options\"":
                return visitSortingOptionsQuestion(ctx);
            case "\"Scaling Options\"":
                return visitScalingOptionsQuestion(ctx);
            default: {
                this.validFlag = false;
                return null;
            }
        }
    }

    @Override
    public String visitQuestionnaires_document_answer(questionnairesParser.Questionnaires_document_answerContext ctx) {
        this.answeredQuestionaire = true;
        return super.visitQuestionnaires_document_answer(ctx);
    }

    @Override
    public String visitSection(questionnairesParser.SectionContext ctx) {
        //If questionnaire is invalid, doesn't visit children
        if (!repeatabilityValid || !validFlag) {
            validFlag = false;
            return null;
        }

        return super.visitSection(ctx);
    }

    @Override
    public String visitSection_answered(questionnairesParser.Section_answeredContext ctx) {
        //If questionnaire is invalid, doesn't visit children
        if (!this.validFlag) {
            return null;
        }

        if (!repeatabilityValid) {
            System.out.printf("The Section %s's content doesn't repeat as many times or repeats more times than the Repeatability establishes! (Repeated %d times, should repeat %d times)\n", this.currentSection, this.contentCounter, this.neededRepeatabiltyCount);
            this.validFlag = false;
            return null;
        }

        return super.visitSection_answered(ctx);
    }

    @Override
    public String visitSection_id(questionnairesParser.Section_idContext ctx) {
        currentSection = ctx.sect_id.getText();

        if (!sectionsMap.containsKey(currentSection)) {
            HashMap<String, Pair<String, List<String>>> questionMap = new HashMap<>();
            sectionsMap.put(currentSection, questionMap);

            //Reset values from last section
            this.repeatability = false;

            if (answeredQuestionaire) {
                this.neededRepeatabiltyCount = 0;
                this.contentCounter = 0;
            }

            if (!this.validFlag)
                return null;
        }

        return super.visitSection_id(ctx);
    }

    @Override
    public String visitQuestion(questionnairesParser.QuestionContext ctx) {
        currentQuestion = ctx.question_id().quest_id.getText();

        Pair<String, List<String>> questionPair = new Pair<>(ctx.question_type().type.getText(), null);
        sectionsMap.get(currentSection).put(currentQuestion, questionPair);

        return super.visitQuestion(ctx);
    }

    @Override
    public String visitQuestion_answered(questionnairesParser.Question_answeredContext ctx) {
        currentQuestion = ctx.question_id().quest_id.getText();

        return super.visitQuestion_answered(ctx);
    }

    @Override
    public String visitSection_repeatability(questionnairesParser.Section_repeatabilityContext ctx) {
        if (ctx.TRUE() != null) {
            this.repeatability = true;
            visitRepeatabilityCondition(ctx.condition());
            return "true";
        }

        return "false";
    }

    public void visitRepeatabilityCondition(questionnairesParser.ConditionContext ctx) {
        String sect_id = ctx.section_id().sect_id.getText();
        String quest_id = ctx.question_id().quest_id.getText();

        if (!this.sectionsMap.containsKey(sect_id)) {
            System.out.printf("Couldn't find Section %s needed for the Repeatability condition from Section %s!\n", sect_id, this.currentSection);
            this.validFlag = false;
            return;
        } else if (!this.sectionsMap.get(sect_id).containsKey(quest_id)){
            System.out.printf("Couldn't find the Question %s from Section %s needed for the Repeatability condition from Section %s!\n", quest_id, sect_id, this.currentSection);
            this.validFlag = false;
            return;
        }

        String questionType = this.sectionsMap.get(sect_id).get(quest_id).a;

        if (!(questionType.equals("\"Numeric\"") || questionType.contains("Multiple"))) {
            System.out.printf("The Question %s can't be used to define the Repeatability condition from Section %s due to its Question Type!\n", quest_id, this.currentSection);
            this.validFlag = false;
            return;
        }

        if (this.answeredQuestionaire) {
            if (questionType.equals("\"Numeric\"")) {
                this.neededRepeatabiltyCount = Integer.parseInt(this.sectionsMap.get(sect_id).get(quest_id).b.get(0));
            } else if (questionType.contains("Multiple")) {
                this.neededRepeatabiltyCount = this.sectionsMap.get(sect_id).get(quest_id).b.size();
            }
        }
    }

    @Override
    public String visitSection_content_answered(questionnairesParser.Section_content_answeredContext ctx) {
        if (this.repeatability && this.answeredQuestionaire) {
            contentCounter++;
            repeatabilityValid = neededRepeatabiltyCount == contentCounter;
        }

        return super.visitSection_content_answered(ctx);
    }

    public String visitFreeTextQuestion(questionnairesParser.AnswerContext ctx) {
        if (ctx.text_answer == null || questionMaxChars == null || ctx.text_answer.getText().length() > questionMaxChars) {
            this.validFlag = false;
            return null;
        }

        addSingleAnswer(ctx.getText());
        return ctx.text_answer.getText();
    }

    public String visitNumericQuestion(questionnairesParser.AnswerContext ctx) {
        if (ctx.numeric_answer == null) {
            this.validFlag = false;
            return null;
        }

        addSingleAnswer(ctx.getText());
        return ctx.numeric_answer.getText();
    }

    public String visitSingleChoiceQuestion(questionnairesParser.AnswerContext ctx) {
        if (ctx.text_answer == null || !questionOptions.contains(ctx.text_answer.getText())) {
            this.validFlag = false;
            return null;
        }

        addSingleAnswer(ctx.getText());
        return ctx.text_answer.getText();
    }

    public String visitSingleChoiceQuestionInput(questionnairesParser.AnswerContext ctx) {
        if (ctx.text_answer == null) {
            this.validFlag = false;
            return null;
        }

        addSingleAnswer(ctx.getText());
        return ctx.text_answer.getText();
    }

    public String visitMultipleChoiceQuestion(questionnairesParser.AnswerContext ctx) {
        if (ctx.list_answer == null || questionMaxOptions == null || ctx.list_answer.text().size() > questionMaxOptions) {
            this.validFlag = false;
            return null;
        }

        List<String> answer = ctx.list_answer.text().stream().map(RuleContext::getText).collect(Collectors.toList());

        addAnswerList(answer);

        if (!new HashSet<>(questionOptions).containsAll(answer)) {
            this.validFlag = false;
            return null;
        }

        return String.join(",", answer);
    }

    public String visitMultipleChoiceQuestionInput(questionnairesParser.AnswerContext ctx) {
        if (ctx.list_answer == null || questionMaxOptions == null || ctx.list_answer.text().size() > questionMaxOptions) {
            this.validFlag = false;
            return null;
        }

        List<String> answer = ctx.list_answer.text().stream().map(RuleContext::getText).collect(Collectors.toList());

        addAnswerList(answer);

        //Check if there is only one input answer and the remaining are given options
        Set<String> difference = new HashSet<>(answer);
        difference.removeAll(new HashSet<>(questionOptions));

        if (difference.size() > 1) {
            this.validFlag = false;
            return null;
        }

        return String.join(",", answer);
    }

    public String visitSortingOptionsQuestion(questionnairesParser.AnswerContext ctx) {
        if (ctx.list_answer == null || ctx.list_answer.text().size() != questionOptions.size()) {
            this.validFlag = false;
            return null;
        }

        List<String> answer = ctx.list_answer.text().stream().map(RuleContext::getText).collect(Collectors.toList());

        addAnswerList(answer);

        if (!new HashSet<>(questionOptions).containsAll(answer)) {
            this.validFlag = false;
            return null;
        }

        return String.join(",", answer);
    }

    public String visitScalingOptionsQuestion(questionnairesParser.AnswerContext ctx) {
        if (ctx.list_answer == null || ctx.list_answer.text().size() != questionOptions.size()) {
            this.validFlag = false;
            return null;
        }

        List<String> answer = ctx.list_answer.text().stream().map(RuleContext::getText).collect(Collectors.toList());

        addAnswerList(answer);

        if (!new HashSet<>(questionScale).containsAll(answer)) {
            this.validFlag = false;
            return null;
        }

        return String.join(",", answer);
    }

    /**
     * Adds a single answer to the sectionMap
     * @param answer the answer
     */
    public void addSingleAnswer(String answer) {
        if (this.sectionsMap.get(currentSection).containsKey(currentQuestion)) {
            this.sectionsMap.get(currentSection).get(currentQuestion).b.add("|");
            this.sectionsMap.get(currentSection).get(currentQuestion).b.add(answer);
        } else {
            ArrayList<String> answerList = new ArrayList<>();
            answerList.add(answer);
            this.sectionsMap.get(currentSection).put(currentQuestion, new Pair<>(questionType, answerList));
        }
    }

    /**
     * Adds a list of answers to the sectionMap
     * @param answers the answers
     */
    public void addAnswerList(List<String> answers) {
        if (this.sectionsMap.get(currentSection).containsKey(currentQuestion)) {
            this.sectionsMap.get(currentSection).get(currentQuestion).b.add("|");
            this.sectionsMap.get(currentSection).get(currentQuestion).b.addAll(answers);
        } else {
            this.sectionsMap.get(currentSection).put(currentQuestion, new Pair<>(questionType, answers));
        }
    }

    @Override
    public String visitObligatoriness(questionnairesParser.ObligatorinessContext ctx) {
        if (this.sectionObligatoriness.equals("\"optional\"") && ctx.type.getText().equals("\"mandatory\"")) {
            System.out.printf("The Question %s can't be Mandatory since the Section %s is Optional!\n", this.currentQuestion, this.currentSection);
            this.validFlag = false;
            return null;
        }

        return ctx.type.getText();
    }

    /**
     * SECTION OBLIGATORINESS
     */

    @Override
    public String visitSection_obligatoriness(questionnairesParser.Section_obligatorinessContext ctx) {
        this.sectionObligatoriness = ctx.type.getText();
        visitObligatorinessCondition(ctx.condition());

        return this.sectionObligatoriness;
    }

    public void visitObligatorinessCondition(questionnairesParser.ConditionContext ctx) {
        if(sectionObligatoriness.equals("\"condition dependent\"")){
            String sect_id = ctx.section_id().sect_id.getText();
            String quest_id = ctx.question_id().quest_id.getText();

                if (!this.sectionsMap.containsKey(sect_id)) {                                   // Checks if the section exists
                     this.validFlag = false;
                    System.out.printf("Couldn't find the Section %s needed for the Obligatory Condition from Section %s\n", sect_id, this.currentSection);
                } else if (!this.sectionsMap.get(sect_id).containsKey(quest_id)) {              // Checks if the question exists
                    this.validFlag = false;
                    System.out.printf("Couldn't find the Question %s from Section %s needed for the Obligatory Condition from Section %s\n", quest_id, sect_id, this.currentSection);
                } else if(answeredQuestionaire) {                                                // Only if the questionnaire is answered
                    String answer = ctx.text().getText();

                    if (!this.sectionsMap.get(sect_id).get(quest_id).b.contains(answer)) {      // Checks if the answer exists
                        this.validFlag = false;
                        System.out.printf("Couldn't find the answer %s on question %s from section %s to answer the question %s from section %s \n", answer, quest_id, sect_id, this.currentQuestion, this.currentSection);
                    }
                }
        } else if(sectionObligatoriness.equals("\"mandatory\"")){
            if(!this.sectionsMap.containsKey(this.currentSection) && answeredQuestionaire){
                this.validFlag = false;
                System.out.printf("The section %s is mandatory but it wasn't found \n",this.currentSection);
            }
        }
    }

    public boolean isAnsweredQuestionaire(){
        return this.answeredQuestionaire;
    }

    public String getQuestionnaireId() {
        return this.questionnaireId.substring(1, this.questionnaireId.length()-1);
    }

    public boolean isQuestionnaireValid() {
        return this.validFlag;
    }

    /**
     * Returns the target's Rules
     * @return the target's Rules
     */
    public List<List<Pair<String, String>>> targetRules() {
        return targetRules;
    }
}
