// Generated from C:/Users/marti/OneDrive/Ambiente de Trabalho/lapr/lapr4/base.core/src/main/java/eapli/base/surveymanagement/grammars\questionnaires.g4 by ANTLR 4.10.1
package eapli.base.surveymanagement.application;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link questionnairesParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface questionnairesVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#questionnaires}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionnaires(questionnairesParser.QuestionnairesContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#questionnaires_unanswered}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionnaires_unanswered(questionnairesParser.Questionnaires_unansweredContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#questionnaires_answered}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionnaires_answered(questionnairesParser.Questionnaires_answeredContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#questionnaires_document}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionnaires_document(questionnairesParser.Questionnaires_documentContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#questionnaires_document_answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionnaires_document_answer(questionnairesParser.Questionnaires_document_answerContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#questionnaires_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionnaires_id(questionnairesParser.Questionnaires_idContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#questionnaires_target}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionnaires_target(questionnairesParser.Questionnaires_targetContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#target_rule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTarget_rule(questionnairesParser.Target_ruleContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#rule_condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRule_condition(questionnairesParser.Rule_conditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#questionnaires_title}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionnaires_title(questionnairesParser.Questionnaires_titleContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#questionnaires_welcome_message}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionnaires_welcome_message(questionnairesParser.Questionnaires_welcome_messageContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#questionnaires_sections}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionnaires_sections(questionnairesParser.Questionnaires_sectionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#questionnaires_ending}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionnaires_ending(questionnairesParser.Questionnaires_endingContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#sections}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSections(questionnairesParser.SectionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#sections_answered}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSections_answered(questionnairesParser.Sections_answeredContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(questionnairesParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#section_answered}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection_answered(questionnairesParser.Section_answeredContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#section_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection_id(questionnairesParser.Section_idContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#section_title}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection_title(questionnairesParser.Section_titleContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#section_description}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection_description(questionnairesParser.Section_descriptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#section_obligatoriness}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection_obligatoriness(questionnairesParser.Section_obligatorinessContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(questionnairesParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#section_repeatability}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection_repeatability(questionnairesParser.Section_repeatabilityContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#section_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection_content(questionnairesParser.Section_contentContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#section_content_answered}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection_content_answered(questionnairesParser.Section_content_answeredContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#questions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestions(questionnairesParser.QuestionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#questions_answered}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestions_answered(questionnairesParser.Questions_answeredContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(questionnairesParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#question_answered}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion_answered(questionnairesParser.Question_answeredContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#question_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion_id(questionnairesParser.Question_idContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#question_text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion_text(questionnairesParser.Question_textContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#question_options}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion_options(questionnairesParser.Question_optionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption(questionnairesParser.OptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#question_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion_type(questionnairesParser.Question_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#question_instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion_instruction(questionnairesParser.Question_instructionContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#obligatoriness}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObligatoriness(questionnairesParser.ObligatorinessContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#obligatoriness_answered}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObligatoriness_answered(questionnairesParser.Obligatoriness_answeredContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswer(questionnairesParser.AnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#chars}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChars(questionnairesParser.CharsContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnairesParser#text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitText(questionnairesParser.TextContext ctx);
}