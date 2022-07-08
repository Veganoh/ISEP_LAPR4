package eapli.base.surveymanagement.application;

import eapli.base.surveymanagement.controller.JsonFileParserAnsweredQuestionnaire;
import eapli.framework.io.util.Console;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.misc.Pair;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class answerQuestionnaire extends questionnairesBaseVisitor<String> {

    /**
     * The questionnaire's content:
     *      Hashmap -> Hashmap -> Pair
     *      Section ID -> Question ID -> (Question Type // Answer)
     */
    public final HashMap<String, HashMap<String, Pair<String, List<String>>>> answers = new HashMap<>();

    /**
     * Hashmap needed variables
     */
    private String currentSection;
    private String currentQuestion;
    private String questionType;

    /**
     * Current Question Mandatoriness
     */
    private String questionMandatoriness;

    /**
     * Current Section Mandatoriness
     */
    private String sectionMandatoriness;

    /**
     * Current Section Repeatability
     */
    private boolean sectionRepeatability;

    /**
     * Current Questions Options, if exists
     */
    private final List<String> questionOptions = new ArrayList<>();

    /**
     * Current Questions Scale, if exists
     */
    private final List<String> questionScale = new ArrayList<>();

    /**
     * Current Max Options, if exists
     */
    private int questionMaxOptions;

    /**
     * Current Max Characters, if exists
     */
    private int questionMaxChars;

    /**
     * Section ID needed if the question type is conditional dependent
     */
    private String questionConditionSectionID;

    /**
     * Question ID needed if the question type is conditional dependent
     */
    private String questionConditionQuestionID;

    /**
     * Answers needed if the question type is conditional dependent
     */
    private List<String> questionConditionAnswers = new ArrayList<>();

    /**
     * Section ID needed if the section obligatoriness is conditional dependent
     */
    private String sectionObligatorinessConditionSectionID;

    /**
     * Question ID needed if the section obligatoriness is conditional dependent
     */
    private String sectionObligatorinessConditionQuestionID;

    /**
     * Answer needed if the obligatoriness is conditional dependent
     */
    private String sectionObligatorinessConditionAnswer;

    /**
     * Section ID needed if the repeatability is conditional dependent
     */
    private String sectionRepeatabilityConditionSectionID;

    /**
     * Question ID needed if the repeatability is conditional dependent
     */
    private String sectionRepeatabilityConditionQuestionID;

    /**
     * This flag is used to know if the repeatability is about a numeric question or other
     */
    private boolean sectionRepeatabilityFlag;

    /**
     * The answer provided by the user
     */
    private String givenAnswer;

    /**
     * The answers provided by the user
     */
    private List<String> givenAnswers = new ArrayList<>();

    /**
     * The options but with spaces just for printing purposes
     */
    private final List<String> options = new ArrayList<>();

    /**
     * The scale but with spaces just for printing purposes
     */
    private final List<String> scales = new ArrayList<>();

    /**
     * The string builder
     */
    private StringBuilder sb;

    /**
     * This variable represents if the visitor is on a section or in a question
     */
    private boolean inSection = false;


    File file = new File("configfiles/answeredQuestionnaire.json");
    JsonFileParserAnsweredQuestionnaire jsonParser = new JsonFileParserAnsweredQuestionnaire();

    private String document;
    private String id;
    private String title;
    private String welcomeMessage;
    private List<String> sections;

    @Override
    public String visitQuestionnaires_unanswered(questionnairesParser.Questionnaires_unansweredContext ctx) {
        document = ctx.questionnaires_document().getText();
        jsonParser.jsonQuestionnaire().document(document);
        id = ctx.questionnaires_id().getText();
        jsonParser.jsonQuestionnaire().ID(id);
        title = ctx.questionnaires_title().getText();
        jsonParser.jsonQuestionnaire().title(title);
        welcomeMessage = ctx.questionnaires_welcome_message().getText();
        jsonParser.jsonQuestionnaire().welcomeMessage(welcomeMessage);
        /*
        need help
        need to add "Sections": [
         {
         */

//        sections = ctx.questionnaires_sections().getText();
//        jsonParser.jsonQuestionnaire().sections(sections);
        return super.visitQuestionnaires_unanswered(ctx);
    }

    @Override
    public String visitSection(questionnairesParser.SectionContext ctx) {
        sb = new StringBuilder();
        inSection = true;

        currentSection = ctx.section_id().sect_id.getText();
        jsonParser.jsonQuestionnaire().jsonSections().sectionID(Integer.parseInt(currentSection));
        sb.append("=================================================================================================================\n| Section ").append(currentSection).append(" - ");


        String sectionTitle = visitSection_title(ctx.section_title());
        jsonParser.jsonQuestionnaire().jsonSections().sectionTitle(sectionTitle);

        if(ctx.section_description() != null){
            String sectionDescription = visitSection_description(ctx.section_description());
            jsonParser.jsonQuestionnaire().jsonSections().sectionDescription(sectionDescription);
        }

        String sectionObligatoriness = visitSection_obligatoriness(ctx.section_obligatoriness());
        jsonParser.jsonQuestionnaire().jsonSections().obligatoriness(sectionObligatoriness);

        String sectionRepeatability = visitSection_repeatability(ctx.section_repeatability());
        jsonParser.jsonQuestionnaire().jsonSections().repeatability(sectionRepeatability);

        if(sectionObligatorinessConditionalRequisites() && sectionRepeatabilityConditionalRequisites() != 0) {
            System.out.println(sb);
        }

        if (!answers.containsKey(currentSection)) {
            HashMap<String, Pair<String, List<String>>> questionMap = new HashMap<>();
            answers.put(currentSection, questionMap);
        }

        /*
        need help
        need to add "Content": [
        {
         */

//       content = ctx.section_().getText();
//       jsonParser.jsonQuestionnaire().sections(sections);
        inSection = false;

        return super.visitSection(ctx);
    }

    @Override
    public String visitSection_title(questionnairesParser.Section_titleContext ctx) {

        for (questionnairesParser.CharsContext string : ctx.text().chars())
            sb.append(string.getText()).append(" ");
        sb.delete(sb.length()-1, sb.length());

        return super.visitSection_title(ctx);
    }

    @Override
    public String visitSection_description(questionnairesParser.Section_descriptionContext ctx) {
        sb.append(": ");

        for (questionnairesParser.CharsContext string : ctx.text().chars())
            sb.append(string.getText()).append(" ");
        sb.delete(sb.length()-1, sb.length());

        return super.visitSection_description(ctx);
    }

    @Override
    public String visitSection_obligatoriness(questionnairesParser.Section_obligatorinessContext ctx) {
        this.sectionMandatoriness = ctx.type.getText();

        sb.append(" - ").append(this.sectionMandatoriness.substring(1, 2).toUpperCase()).append(this.sectionMandatoriness, 2, this.sectionMandatoriness.length() - 1);

        if(this.sectionMandatoriness.equals("\"condition dependent\"")){
            this.sectionObligatorinessConditionSectionID = ctx.condition().section_id().sect_id.getText();
            this.sectionObligatorinessConditionQuestionID = ctx.condition().question_id().quest_id.getText();
            this.sectionObligatorinessConditionAnswer = ctx.condition().text().getText();
        }
        return super.visitSection_obligatoriness(ctx);
    }

    @Override
    public String visitSection_repeatability(questionnairesParser.Section_repeatabilityContext ctx) {

        this.sectionRepeatability = ctx.TRUE() != null;

        if(this.sectionRepeatability){
            this.sectionRepeatabilityConditionSectionID = ctx.condition().section_id().sect_id.getText();
            this.sectionRepeatabilityConditionQuestionID = ctx.condition().question_id().quest_id.getText();
        }
        return super.visitSection_repeatability(ctx);
    }

    /**
     * Main method that will get the information from the question currently in
     */
    @Override
    public String visitQuestion(questionnairesParser.QuestionContext ctx) {
       if(sectionObligatorinessConditionalRequisites()) {
           for(int i = 0; i < sectionRepeatabilityConditionalRequisites();i++) {
               sb = new StringBuilder();
               if(this.sectionRepeatabilityFlag){
                   List<String> strings = this.answers.get(sectionRepeatabilityConditionSectionID).get(sectionRepeatabilityConditionQuestionID).b;
                   removeTraces(strings);
                   sb.append("| About the answer: ").append(strings.get(i)).append(" | \n");
               }

                   sb.append("=================================================================================================================\n");
               visitQuestion_id(ctx.question_id());
               visitQuestion_text(ctx.question_text());
               if (ctx.question_instruction() != null) {
                   visitQuestion_instruction(ctx.question_instruction());
               }
               visitObligatoriness(ctx.obligatoriness());

               if (this.questionMandatoriness.equals("\"condition dependent\"")) {
                   if (!questionConditionalRequisites()) return super.visitQuestion(ctx);
               }
               visitQuestion_type(ctx.question_type());

               System.out.println(sb);
               validateAnswer();
           }
       }
        return super.visitQuestion(ctx);
    }

    /**
     * Visits the question ID and saves it.
     */
    @Override
    public String visitQuestion_id(questionnairesParser.Question_idContext ctx) {

        if(!inSection){
            this.currentQuestion = ctx.quest_id.getText();
            sb.append("Question ").append(currentQuestion).append(" - ");
        }
        return super.visitQuestion_id(ctx);
    }

    /**
     * Visits the question text and saves it.
     */
    @Override
    public String visitQuestion_text(questionnairesParser.Question_textContext ctx) {
        for (questionnairesParser.CharsContext asd : ctx.text().chars())
            sb.append(asd.getText()).append(" ");
        sb.append("\n");
        return super.visitQuestion_text(ctx);
    }

    /**
     * Visits the instructions and saves it.
     */
    @Override
    public String visitQuestion_instruction(questionnairesParser.Question_instructionContext ctx) {
        sb.append("(");
        for (questionnairesParser.CharsContext asd : ctx.text().chars())
            sb.append(asd.getText()).append(" ");
        sb.delete(sb.length()-1, sb.length());
        sb.append(")\n");
        return super.visitQuestion_instruction(ctx);
    }

    /**
     * Visits the obligatoriness and saves its value
     */
    @Override
    public String visitObligatoriness(questionnairesParser.ObligatorinessContext ctx) {
        this.questionMandatoriness = ctx.type.getText();
        sb.append("Obligatoriness: ").append(this.questionMandatoriness.substring(1, 2).toUpperCase()).append(this.questionMandatoriness, 2, this.questionMandatoriness.length() - 1).append("\n");

        if(this.questionMandatoriness.equals("\"condition dependent\"")){
            this.questionConditionAnswers = new ArrayList<>();
            this.questionConditionSectionID = ctx.answer_section_id.getText();
            this.questionConditionQuestionID = ctx.answer_question_id.getText();
            if(ctx.answer_option != null){
                this.questionConditionAnswers.add(ctx.answer_option.getText());
            }else{
                visitOption(ctx.answer_list,this.questionConditionAnswers);
            }
        }
        return super.visitObligatoriness(ctx);
    }

    /**
     * Visits the question type and saves it. It also records data, in case it exists, such as:
     * Max Characters
     * Max Options
     * List of Options and Question Scale
     */
    @Override
    public String visitQuestion_type(questionnairesParser.Question_typeContext ctx) {
        this.questionType = ctx.type.getText();
        sb.append("Question Type: ").append(questionType, 1, this.questionType.length()-1).append("\n");

        visitQuestionInfo(ctx);
        visitMaxChars(ctx);
        visitMaxOptions(ctx);
        visitOptions(ctx);
        visitQuestionScale(ctx);

        return super.visitQuestion_type(ctx);
    }

    /**
     * This method checks if the question has a max number of characters and saves it
     */
    public void visitMaxChars(questionnairesParser.Question_typeContext ctx){
        if(ctx.max_chars != null){
            this.questionMaxChars = Integer.parseInt(ctx.max_chars.getText());
            sb.append("\tMax Characters: ").append(this.questionMaxChars).append("\n");
        }
    }

    /**
     * This method checks if the question has a max number of options and saves it
     */
    public void visitMaxOptions(questionnairesParser.Question_typeContext ctx){
        if(ctx.max_options != null){
            this.questionMaxOptions = Integer.parseInt(ctx.max_options.getText());
            sb.append("\tMax Options: ").append(this.questionMaxOptions).append("\n");
        }
    }

    /**
     * This method checks if the question has options  and saves them
     */
    public void visitOptions(questionnairesParser.Question_typeContext ctx){
      if(ctx.question_list != null) {



          visitOption(ctx.question_list.list, this.questionOptions);
          withSpaces(ctx.question_list.list,this.options);

          if(questionType.equals( "\"Scaling Options\"")) return;
          sb.append("\tOptions: \n");

          if(this.questionType.equals("\"Single Choice With Input Value\"") || this.questionType.equals("\"Multiple Choice With Input Value\"")) {
              this.questionOptions.add("\"Other\"");
              options.add("\"Other\"");
          }
              for (String option : options)
                  sb.append("\t\t").append(option).append("\n");
      }
    }

    /**
     * This method gets the list of options or scale
     */
    private void visitOption(questionnairesParser.OptionContext ctx, List<String> list) {
        list.clear();
        list.addAll(ctx.text().stream().map(RuleContext::getText).collect(Collectors.toList()));
    }

    /**
     * This method gets the list of options or scale with spaces just for print purposes
     */
    private void withSpaces(questionnairesParser.OptionContext ctx, List<String> list) {
        list.clear();
        for (questionnairesParser.TextContext optionText : ctx.text()) {
            StringBuilder st = new StringBuilder();
            st.append("\"");
            for (questionnairesParser.CharsContext asd : optionText.chars()) {
                st.append(asd.getText()).append(" ");
            }
            st.delete(st.length() - 1, st.length());
            st.append("\"");
            list.add(st.toString());
        }
    }


    /**
     * This method checks if the question has a scale and saves them
     */
    public void visitQuestionScale(questionnairesParser.Question_typeContext ctx){
        if(ctx.question_scale != null) {
            visitOption(ctx.question_scale.list,this.questionScale);
            withSpaces(ctx.question_scale.list,this.scales);
        }
    }

    /**
     * This method checks if the question has info and saves it
     */
    public void visitQuestionInfo(questionnairesParser.Question_typeContext ctx){

        if(questionType.equals( "\"Scaling Options\"")) return;

        if(ctx.info != null) sb.append("Extra Info:\n");
    }

    private boolean validateAnswer() {
        switch (questionType) {
            case "\"Numeric\"":
                return validateNumeric();
            case "\"Free Text\"":
                return validateFreeText();
            case "\"Single Choice\"":
                return validateSingleChoice();
            case "\"Single Choice With Input Value\"":
                return validateSingleChoiceInput();
            case "\"Multiple Choice\"":
                return validateMultipleChoice();
            case "\"Multiple Choice With Input Value\"":
                return validateMultipleChoiceInput();
            case "\"Sorting Options\"":
                return validateSortingOptions();
            case "\"Scaling Options\"":
                return validateScalingOptions();
            default: {
                return false;
            }
        }
    }

    /**
     * Validates the numeric answer
     * @return true if valid and false if invalid
     */
    private boolean validateNumeric(){

        do {
            givenAnswer = Console.readLine("Answer:");

            if (givenAnswer.isEmpty()){
                if(this.questionMandatoriness.equals("\"optional\"")) return true;
                else System.out.println("This question cannot be skipped");
            }else {
                try {
                    Double.parseDouble(givenAnswer);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("You must enter a valid number!");
                }
            }
        } while(true);
        addSingleAnswer("\"" + givenAnswer + "\"");
        return true;
    }

    /**
     * Validates the free text answer
     * @return true if valid and false if invalid
     */
    private boolean validateFreeText(){
        while(true) {
            givenAnswer = readAnswer();

            if (givenAnswer.isEmpty())
                if(this.questionMandatoriness.equals("\"optional\"")) return true;
                else System.out.println("This question cannot be skipped");
            else if (givenAnswer.length() > questionMaxChars)
                System.out.println("The answer cannot extend more than " + questionMaxChars + " characters!");
            else break;
        }
        addSingleAnswer(givenAnswer);
        return true;
    }

    /**
     * Validates the single choice answer
     * @return true if valid and false if invalid
     */
    private boolean validateSingleChoice() {

        while(true) {
            givenAnswer = readAnswer();

            if (givenAnswer.isEmpty())
                if(this.questionMandatoriness.equals("\"optional\"")) return true;
                else System.out.println("This question cannot be skipped");
            else if (!questionOptions.contains(givenAnswer))
                System.out.println("The answer must belong to the options list: ");
            else break;
        }
        addSingleAnswer(givenAnswer);
        return true;
    }

    /**
     * Validates the single choice input answer
     * @return true if valid and false if invalid
     */
    private boolean validateSingleChoiceInput() {

        while(true) {
            givenAnswer = readAnswer();

            if (givenAnswer.isEmpty())
                if(this.questionMandatoriness.equals("\"optional\"")) return true;
                else System.out.println("This question cannot be skipped");
            else if (!questionOptions.contains(givenAnswer))
                System.out.println("The answer must belong to the options list: ");
            else if (givenAnswer.equals("\"Other\"")){
                System.out.println("Input your own answer:");
                givenAnswer = readAnswer();
                break;
            }
            else break;
        }
        addSingleAnswer(givenAnswer);
        return true;
    }

    /**
     * Validates the multiple choice answer
     * @return true if valid and false if invalid
     */
    private boolean validateMultipleChoiceInput() {

        boolean flag = true;
        givenAnswers = new ArrayList<>();

        for(int i = 1; i <= questionMaxOptions;i++){

            givenAnswer = readAnswer();

            if (givenAnswer.isEmpty()){
                if(this.questionMandatoriness.equals("\"optional\"")) return true;
                else {
                    System.out.println("This question cannot be skipped");
                    i--;
                }
            }else if (!questionOptions.contains(givenAnswer)){
                System.out.println("The answer must belong to the options list: ");
                i--;
            } else if (givenAnswers.contains(givenAnswer)){
                System.out.println("You can't pick the same answer again. Select another one: ");
                i--;
            }
            else if (givenAnswer.equals("\"Other\"")){
                if(!flag){
                    System.out.println("You can't choose other again");
                    i--;
                }
                else {
                    System.out.println("Input your own answer:");
                    givenAnswer = readAnswer();
                    flag = false;
                    givenAnswers.add(givenAnswer);
                    if(i != questionMaxOptions) {
                        if (!readConfirmation()) break;
                    }
                }
            }
            else{
                givenAnswers.add(givenAnswer);
                if(i != questionMaxOptions) {
                    if (!readConfirmation()) break;
                }
            }
        }
        addAnswerList(givenAnswers);
        return true;
    }

    /**
     * Validates the multiple choice answer
     * @return true if valid and false if invalid
     */
    private boolean validateMultipleChoice() {

        givenAnswers = new ArrayList<>();
        for(int i = 1; i <= questionMaxOptions;i++){
            givenAnswer = readAnswer();

            if (givenAnswer.isEmpty()){
                if(this.questionMandatoriness.equals("\"optional\"")) return true;
                else {
                    System.out.println("This question cannot be skipped");
                    i--;
                }
            }else if (!questionOptions.contains(givenAnswer)){
                System.out.println("The answer must belong to the options list: ");
                i--;
            } else if (givenAnswers.contains(givenAnswer)){
                System.out.println("You can't pick the same answer again. Select another one: ");
                i--;
            }
            else{
                givenAnswers.add(givenAnswer);
                if(i != questionMaxOptions) {
                    if (!readConfirmation()) break;
                }
            }
        }
        addAnswerList(givenAnswers);
        return true;
    }

    /**
     * Validates the scaling options answer
     * @return true if valid and false if invalid
     */
    private boolean validateScalingOptions(){

        while (true){
            this.givenAnswers = new ArrayList<>();
            for(String option : this.options) {
                System.out.println("\t\t\t\t\t" + option);
                sb = new StringBuilder();
                for (String scale : this.scales) {
                 sb.append(scale).append("\t\t");
                }
                sb.append("\n");
                System.out.println(sb);

                 while (true) {
                    givenAnswer = readAnswer();

                    if(givenAnswer.isEmpty()){
                        break;
                    }

                    if (!questionScale.contains(givenAnswer))
                        System.out.println("The answer must belong to the question scale list: ");
                    else {
                        givenAnswers.add(givenAnswer);
                        break;
                    }
                }
            }

            if(givenAnswers.isEmpty()){
                if(this.questionMandatoriness.equals("\"optional\"")) return true;
                else System.out.println("This question cannot be skipped");
            }else if(givenAnswers.size() != this.questionOptions.size()){
                System.out.println("WARNING - All options need to be answered, if this question is optional you can't answer just some. All or nothing!");
            }else{
                break;
            }
        }
        addAnswerList(givenAnswers);
        return true;
    }

    /**
     * Validates the sorting options answer
     * @return true if valid and false if invalid
     */
    private boolean validateSortingOptions(){

        givenAnswers = new ArrayList<>();

        while (true) {
            for (int i = 0; i < this.questionOptions.size(); i++) {
                while (true) {
                    givenAnswer = readAnswer();

                    if (givenAnswer.isEmpty()) {
                        break;
                    }

                    if (!questionOptions.contains(givenAnswer)) {
                        System.out.println("The answer must belong to the options list: ");
                        i--;
                    } else if (!givenAnswers.contains(givenAnswer)) {
                        System.out.println("You can't pick the same option twice! ");
                        i--;
                    } else {
                        givenAnswers.add(givenAnswer);
                        break;
                    }
                }
            }
            if(givenAnswers.isEmpty()){
                if(this.questionMandatoriness.equals("\"optional\"")) return true;
                else System.out.println("This question cannot be skipped");
            }else if(givenAnswers.size() != this.questionOptions.size()){
                System.out.println("WARNING - All options need to be in the sequence, if this question is optional you can't put just some in the sequence. All or nothing!");
            }else{
                break;
            }
        }
        addAnswerList(givenAnswers);
        return true;
    }

    /**
     * Checks if the question conditional requisites are fulfilled
     * @return true if they are and false if they aren't
     */
    private boolean questionConditionalRequisites(){
        if (!this.answers.containsKey(this.questionConditionSectionID)) {
            return false;
        } else if ((!this.answers.get(this.questionConditionSectionID).containsKey(this.questionConditionQuestionID))) {
            return false;
        } else {
            for (String answer : this.questionConditionAnswers) {
                if (this.answers.get(this.questionConditionSectionID).get(this.questionConditionQuestionID).b.contains(answer))
                    return true;
            }
        }
        return false;
    }

    /**
     * Checks if the section obligatoriness conditional requisites are fulfilled
     * @return true if they are and false if they aren't
     */
    private boolean sectionObligatorinessConditionalRequisites(){

        if(this.sectionMandatoriness.equals("\"condition dependent\"")) {
            if (!this.answers.containsKey(this.sectionObligatorinessConditionSectionID)) {
                return false;
            } else if ((!this.answers.get(this.sectionObligatorinessConditionSectionID).containsKey(this.sectionObligatorinessConditionQuestionID))) {
                return false;
            } else return this.answers.get(this.sectionObligatorinessConditionSectionID).get(this.sectionObligatorinessConditionQuestionID).b.contains(sectionObligatorinessConditionAnswer);
        }
        return true;
    }

    /**
     * Checks if the section repeatability conditional requisites ands returns how many times the section will repeat
     * @return the number of times a section will repeat
     */
    private int sectionRepeatabilityConditionalRequisites() {

        String string;
        this.sectionRepeatabilityFlag = false;

            if (this.sectionRepeatability) {
                if (!this.answers.containsKey(this.sectionRepeatabilityConditionSectionID)) {
                    return 0;
                } else if ((!this.answers.get(this.sectionRepeatabilityConditionSectionID).containsKey(this.sectionRepeatabilityConditionQuestionID))) {
                    return 0;
                } else if ((this.answers.get(this.sectionRepeatabilityConditionSectionID).get(this.sectionRepeatabilityConditionQuestionID).a.equals("\"Numeric\""))){
                    string = this.answers.get(this.sectionRepeatabilityConditionSectionID).get(this.sectionRepeatabilityConditionQuestionID).b.get(0);
                    return Integer.parseInt(string.substring(1, string.length() - 1));
                } else {
                this.sectionRepeatabilityFlag = true;
                return this.answers.get(this.sectionRepeatabilityConditionSectionID).get(this.sectionRepeatabilityConditionQuestionID).b.size() - countTraces(this.answers.get(this.sectionRepeatabilityConditionSectionID).get(this.sectionRepeatabilityConditionQuestionID).b);
            }
        }
        return 1;
    }

    /**
     * This method checks how many traces are splitting an answer from another one
     * @param list the list to be checks
     * @return the number of traces splitting an answer from another one
     */
    private int countTraces(List<String> list){
        int result = 0;
        for(String string : list){
            if(string.equals("|")) result++;
        }
        return result;
    }

    /**
     * This method checks how many traces are splitting an answer from another one
     * @param list the list to be checks
     */
    private void removeTraces(List<String> list){
        for(int i = 0; i < list.size();i++){
            if(list.get(i).equals("|")){
                list.remove(i);
                i--;
            }
        }
    }

    /**
     * Reads a confirmation from the input
     * @return true if yes and false if no
     */
    private boolean readConfirmation(){
        do {
            String string = Console.readLine("Add more answers? (Y|N)");
            if(string.equalsIgnoreCase("Y")){
                System.out.println("Select another option: ");
                return true;
            }
            if(string.equalsIgnoreCase("N")) return false;

        }while(true);
    }

    /**
     * Reads an input from the user
     * @return the answer typed
     */
    private String readAnswer(){

        String string = Console.readLine("Answer:");

        if(string.isEmpty()) return "";
        return "\"" + string.replaceAll("\\s+","") + "\"";
    }

    /**
     * Adds a single answer to the sectionMap
     * @param answer the answer
     */
    public void addSingleAnswer(String answer) {
        if (this.answers.get(currentSection).containsKey(currentQuestion)) {
            this.answers.get(currentSection).get(currentQuestion).b.add("|");
            this.answers.get(currentSection).get(currentQuestion).b.add(answer);
        } else {
            ArrayList<String> answerList = new ArrayList<>();
            answerList.add(answer);
            this.answers.get(currentSection).put(currentQuestion, new Pair<>(questionType, answerList));
        }
    }

    /**
     * Adds a list of answers to the sectionMap
     * @param answers the answers
     */
    public void addAnswerList(List<String> answers) {
        if (this.answers.get(currentSection).containsKey(currentQuestion)) {
            this.answers.get(currentSection).get(currentQuestion).b.add("|");
            this.answers.get(currentSection).get(currentQuestion).b.addAll(answers);
        } else {
            this.answers.get(currentSection).put(currentQuestion, new Pair<>(questionType, answers));
        }
    }
}
