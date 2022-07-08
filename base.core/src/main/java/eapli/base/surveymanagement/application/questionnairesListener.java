// Generated from C:/Users/marti/OneDrive/Ambiente de Trabalho/lapr/lapr4/base.core/src/main/java/eapli/base/surveymanagement/grammars\questionnaires.g4 by ANTLR 4.10.1
package eapli.base.surveymanagement.application;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link questionnairesParser}.
 */
public interface questionnairesListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#questionnaires}.
	 * @param ctx the parse tree
	 */
	void enterQuestionnaires(questionnairesParser.QuestionnairesContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#questionnaires}.
	 * @param ctx the parse tree
	 */
	void exitQuestionnaires(questionnairesParser.QuestionnairesContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#questionnaires_unanswered}.
	 * @param ctx the parse tree
	 */
	void enterQuestionnaires_unanswered(questionnairesParser.Questionnaires_unansweredContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#questionnaires_unanswered}.
	 * @param ctx the parse tree
	 */
	void exitQuestionnaires_unanswered(questionnairesParser.Questionnaires_unansweredContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#questionnaires_answered}.
	 * @param ctx the parse tree
	 */
	void enterQuestionnaires_answered(questionnairesParser.Questionnaires_answeredContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#questionnaires_answered}.
	 * @param ctx the parse tree
	 */
	void exitQuestionnaires_answered(questionnairesParser.Questionnaires_answeredContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#questionnaires_document}.
	 * @param ctx the parse tree
	 */
	void enterQuestionnaires_document(questionnairesParser.Questionnaires_documentContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#questionnaires_document}.
	 * @param ctx the parse tree
	 */
	void exitQuestionnaires_document(questionnairesParser.Questionnaires_documentContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#questionnaires_document_answer}.
	 * @param ctx the parse tree
	 */
	void enterQuestionnaires_document_answer(questionnairesParser.Questionnaires_document_answerContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#questionnaires_document_answer}.
	 * @param ctx the parse tree
	 */
	void exitQuestionnaires_document_answer(questionnairesParser.Questionnaires_document_answerContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#questionnaires_id}.
	 * @param ctx the parse tree
	 */
	void enterQuestionnaires_id(questionnairesParser.Questionnaires_idContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#questionnaires_id}.
	 * @param ctx the parse tree
	 */
	void exitQuestionnaires_id(questionnairesParser.Questionnaires_idContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#questionnaires_target}.
	 * @param ctx the parse tree
	 */
	void enterQuestionnaires_target(questionnairesParser.Questionnaires_targetContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#questionnaires_target}.
	 * @param ctx the parse tree
	 */
	void exitQuestionnaires_target(questionnairesParser.Questionnaires_targetContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#target_rule}.
	 * @param ctx the parse tree
	 */
	void enterTarget_rule(questionnairesParser.Target_ruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#target_rule}.
	 * @param ctx the parse tree
	 */
	void exitTarget_rule(questionnairesParser.Target_ruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#rule_condition}.
	 * @param ctx the parse tree
	 */
	void enterRule_condition(questionnairesParser.Rule_conditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#rule_condition}.
	 * @param ctx the parse tree
	 */
	void exitRule_condition(questionnairesParser.Rule_conditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#questionnaires_title}.
	 * @param ctx the parse tree
	 */
	void enterQuestionnaires_title(questionnairesParser.Questionnaires_titleContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#questionnaires_title}.
	 * @param ctx the parse tree
	 */
	void exitQuestionnaires_title(questionnairesParser.Questionnaires_titleContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#questionnaires_welcome_message}.
	 * @param ctx the parse tree
	 */
	void enterQuestionnaires_welcome_message(questionnairesParser.Questionnaires_welcome_messageContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#questionnaires_welcome_message}.
	 * @param ctx the parse tree
	 */
	void exitQuestionnaires_welcome_message(questionnairesParser.Questionnaires_welcome_messageContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#questionnaires_sections}.
	 * @param ctx the parse tree
	 */
	void enterQuestionnaires_sections(questionnairesParser.Questionnaires_sectionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#questionnaires_sections}.
	 * @param ctx the parse tree
	 */
	void exitQuestionnaires_sections(questionnairesParser.Questionnaires_sectionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#questionnaires_ending}.
	 * @param ctx the parse tree
	 */
	void enterQuestionnaires_ending(questionnairesParser.Questionnaires_endingContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#questionnaires_ending}.
	 * @param ctx the parse tree
	 */
	void exitQuestionnaires_ending(questionnairesParser.Questionnaires_endingContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#sections}.
	 * @param ctx the parse tree
	 */
	void enterSections(questionnairesParser.SectionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#sections}.
	 * @param ctx the parse tree
	 */
	void exitSections(questionnairesParser.SectionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#sections_answered}.
	 * @param ctx the parse tree
	 */
	void enterSections_answered(questionnairesParser.Sections_answeredContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#sections_answered}.
	 * @param ctx the parse tree
	 */
	void exitSections_answered(questionnairesParser.Sections_answeredContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(questionnairesParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(questionnairesParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#section_answered}.
	 * @param ctx the parse tree
	 */
	void enterSection_answered(questionnairesParser.Section_answeredContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#section_answered}.
	 * @param ctx the parse tree
	 */
	void exitSection_answered(questionnairesParser.Section_answeredContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#section_id}.
	 * @param ctx the parse tree
	 */
	void enterSection_id(questionnairesParser.Section_idContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#section_id}.
	 * @param ctx the parse tree
	 */
	void exitSection_id(questionnairesParser.Section_idContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#section_title}.
	 * @param ctx the parse tree
	 */
	void enterSection_title(questionnairesParser.Section_titleContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#section_title}.
	 * @param ctx the parse tree
	 */
	void exitSection_title(questionnairesParser.Section_titleContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#section_description}.
	 * @param ctx the parse tree
	 */
	void enterSection_description(questionnairesParser.Section_descriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#section_description}.
	 * @param ctx the parse tree
	 */
	void exitSection_description(questionnairesParser.Section_descriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#section_obligatoriness}.
	 * @param ctx the parse tree
	 */
	void enterSection_obligatoriness(questionnairesParser.Section_obligatorinessContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#section_obligatoriness}.
	 * @param ctx the parse tree
	 */
	void exitSection_obligatoriness(questionnairesParser.Section_obligatorinessContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(questionnairesParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(questionnairesParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#section_repeatability}.
	 * @param ctx the parse tree
	 */
	void enterSection_repeatability(questionnairesParser.Section_repeatabilityContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#section_repeatability}.
	 * @param ctx the parse tree
	 */
	void exitSection_repeatability(questionnairesParser.Section_repeatabilityContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#section_content}.
	 * @param ctx the parse tree
	 */
	void enterSection_content(questionnairesParser.Section_contentContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#section_content}.
	 * @param ctx the parse tree
	 */
	void exitSection_content(questionnairesParser.Section_contentContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#section_content_answered}.
	 * @param ctx the parse tree
	 */
	void enterSection_content_answered(questionnairesParser.Section_content_answeredContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#section_content_answered}.
	 * @param ctx the parse tree
	 */
	void exitSection_content_answered(questionnairesParser.Section_content_answeredContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#questions}.
	 * @param ctx the parse tree
	 */
	void enterQuestions(questionnairesParser.QuestionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#questions}.
	 * @param ctx the parse tree
	 */
	void exitQuestions(questionnairesParser.QuestionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#questions_answered}.
	 * @param ctx the parse tree
	 */
	void enterQuestions_answered(questionnairesParser.Questions_answeredContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#questions_answered}.
	 * @param ctx the parse tree
	 */
	void exitQuestions_answered(questionnairesParser.Questions_answeredContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(questionnairesParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(questionnairesParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#question_answered}.
	 * @param ctx the parse tree
	 */
	void enterQuestion_answered(questionnairesParser.Question_answeredContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#question_answered}.
	 * @param ctx the parse tree
	 */
	void exitQuestion_answered(questionnairesParser.Question_answeredContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#question_id}.
	 * @param ctx the parse tree
	 */
	void enterQuestion_id(questionnairesParser.Question_idContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#question_id}.
	 * @param ctx the parse tree
	 */
	void exitQuestion_id(questionnairesParser.Question_idContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#question_text}.
	 * @param ctx the parse tree
	 */
	void enterQuestion_text(questionnairesParser.Question_textContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#question_text}.
	 * @param ctx the parse tree
	 */
	void exitQuestion_text(questionnairesParser.Question_textContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#question_options}.
	 * @param ctx the parse tree
	 */
	void enterQuestion_options(questionnairesParser.Question_optionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#question_options}.
	 * @param ctx the parse tree
	 */
	void exitQuestion_options(questionnairesParser.Question_optionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(questionnairesParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(questionnairesParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#question_type}.
	 * @param ctx the parse tree
	 */
	void enterQuestion_type(questionnairesParser.Question_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#question_type}.
	 * @param ctx the parse tree
	 */
	void exitQuestion_type(questionnairesParser.Question_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#question_instruction}.
	 * @param ctx the parse tree
	 */
	void enterQuestion_instruction(questionnairesParser.Question_instructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#question_instruction}.
	 * @param ctx the parse tree
	 */
	void exitQuestion_instruction(questionnairesParser.Question_instructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#obligatoriness}.
	 * @param ctx the parse tree
	 */
	void enterObligatoriness(questionnairesParser.ObligatorinessContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#obligatoriness}.
	 * @param ctx the parse tree
	 */
	void exitObligatoriness(questionnairesParser.ObligatorinessContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#obligatoriness_answered}.
	 * @param ctx the parse tree
	 */
	void enterObligatoriness_answered(questionnairesParser.Obligatoriness_answeredContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#obligatoriness_answered}.
	 * @param ctx the parse tree
	 */
	void exitObligatoriness_answered(questionnairesParser.Obligatoriness_answeredContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#answer}.
	 * @param ctx the parse tree
	 */
	void enterAnswer(questionnairesParser.AnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#answer}.
	 * @param ctx the parse tree
	 */
	void exitAnswer(questionnairesParser.AnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#chars}.
	 * @param ctx the parse tree
	 */
	void enterChars(questionnairesParser.CharsContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#chars}.
	 * @param ctx the parse tree
	 */
	void exitChars(questionnairesParser.CharsContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnairesParser#text}.
	 * @param ctx the parse tree
	 */
	void enterText(questionnairesParser.TextContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnairesParser#text}.
	 * @param ctx the parse tree
	 */
	void exitText(questionnairesParser.TextContext ctx);
}