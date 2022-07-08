// Generated from C:/Users/marti/OneDrive/Ambiente de Trabalho/lapr/lapr4/base.core/src/main/java/eapli/base/surveymanagement/grammars\questionnaires.g4 by ANTLR 4.10.1
package eapli.base.surveymanagement.application;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class questionnairesParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, SURVEY_DOCUMENT=2, SURVEY_ID=3, SURVEY_TARGET=4, SURVEY_TITLE=5, 
		SURVEY_WELCOME=6, SURVEY_SECTIONS=7, SURVEY_END=8, SURVEY_UNANSWERED=9, 
		SURVEY_ANSWERED=10, CONDITION_CATEGORY=11, CONDITION_PRODUCT=12, CONDITION_BRAND=13, 
		CONDITION_GENDER=14, CONDITION_AGE=15, RULE_CONDITION=16, CONDITION_TYPE=17, 
		MINIMUM_AGE=18, MAXIMUM_AGE=19, OLDER=20, YOUNGER=21, SECTION_ID=22, SECTION_TITLE=23, 
		SECTION_DESCRIPTION=24, OBLIGATORINESS=25, REPEATABILITY=26, CONTENT=27, 
		QUESTION_ID=28, QUESTION=29, QUESTION_INSTRUCTION=30, QUESTION_TYPE=31, 
		QUESTION_INFO=32, QUESTION_OPTIONS=33, QUESTION_MAX_OPTIONS=34, QUESTION_MAX_CHARS=35, 
		QUESTION_SCALE=36, FREE_TEXT=37, NUMERIC=38, SINGLE_CHOICE=39, SINGLE_CHOICE_INPUT=40, 
		MULTIPLE_CHOICE=41, MULTIPLE_CHOICE_INPUT=42, SORTING_OPTION=43, SCALING_OPTION=44, 
		OPTIONAL=45, MANDATORY=46, CONDITIONAL=47, CONDITION=48, ANSWER=49, CHAR=50, 
		SPECIAL=51, INT=52, WORD=53, ID=54, FALSE=55, TRUE=56, LIST_START=57, 
		LIST_END=58, ELEM_START=59, ELEM_END=60, SEPARATOR=61, LINE=62, WS=63;
	public static final int
		RULE_questionnaires = 0, RULE_questionnaires_unanswered = 1, RULE_questionnaires_answered = 2, 
		RULE_questionnaires_document = 3, RULE_questionnaires_document_answer = 4, 
		RULE_questionnaires_id = 5, RULE_questionnaires_target = 6, RULE_target_rule = 7, 
		RULE_rule_condition = 8, RULE_questionnaires_title = 9, RULE_questionnaires_welcome_message = 10, 
		RULE_questionnaires_sections = 11, RULE_questionnaires_ending = 12, RULE_sections = 13, 
		RULE_sections_answered = 14, RULE_section = 15, RULE_section_answered = 16, 
		RULE_section_id = 17, RULE_section_title = 18, RULE_section_description = 19, 
		RULE_section_obligatoriness = 20, RULE_condition = 21, RULE_section_repeatability = 22, 
		RULE_section_content = 23, RULE_section_content_answered = 24, RULE_questions = 25, 
		RULE_questions_answered = 26, RULE_question = 27, RULE_question_answered = 28, 
		RULE_question_id = 29, RULE_question_text = 30, RULE_question_options = 31, 
		RULE_option = 32, RULE_question_type = 33, RULE_question_instruction = 34, 
		RULE_obligatoriness = 35, RULE_obligatoriness_answered = 36, RULE_answer = 37, 
		RULE_chars = 38, RULE_text = 39;
	private static String[] makeRuleNames() {
		return new String[] {
			"questionnaires", "questionnaires_unanswered", "questionnaires_answered", 
			"questionnaires_document", "questionnaires_document_answer", "questionnaires_id", 
			"questionnaires_target", "target_rule", "rule_condition", "questionnaires_title", 
			"questionnaires_welcome_message", "questionnaires_sections", "questionnaires_ending", 
			"sections", "sections_answered", "section", "section_answered", "section_id", 
			"section_title", "section_description", "section_obligatoriness", "condition", 
			"section_repeatability", "section_content", "section_content_answered", 
			"questions", "questions_answered", "question", "question_answered", "question_id", 
			"question_text", "question_options", "option", "question_type", "question_instruction", 
			"obligatoriness", "obligatoriness_answered", "answer", "chars", "text"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'\"'", "'\"Document\"'", "'\"ID\"'", "'\"Target Audience\"'", 
			"'\"Title\"'", "'\"WelcomeMessage\"'", "'\"Sections\"'", "'\"FinalMessage\"'", 
			"'\"Questionnaire\"'", "'\"Answered Questionnaire\"'", "'\"Category\"'", 
			"'\"Product\"'", "'\"Brand\"'", "'\"Gender\"'", "'\"Age Group\"'", "'\"Rule Conditions\"'", 
			"'\"Condition Type\"'", "'\"Minimum Age\"'", "'\"Maximum Age\"'", "'\"Older Than\"'", 
			"'\"Younger Than\"'", "'\"SectionID\"'", "'\"SectionTitle\"'", "'\"SectionDescription\"'", 
			"'\"Obligatoriness\"'", "'\"Repeatability\"'", "'\"Content\"'", "'\"QuestionID\"'", 
			"'\"Question\"'", "'\"Instruction\"'", "'\"Type\"'", "'\"ExtraInfo\"'", 
			"'\"Options\"'", "'\"MaximumOptions\"'", "'\"MaximumCharacters\"'", "'\"Scale\"'", 
			"'\"Free Text\"'", "'\"Numeric\"'", "'\"Single Choice\"'", "'\"Single Choice With Input Value\"'", 
			"'\"Multiple Choice\"'", "'\"Multiple Choice With Input Value\"'", "'\"Sorting Options\"'", 
			"'\"Scaling Options\"'", "'\"optional\"'", "'\"mandatory\"'", "'\"condition dependent\"'", 
			"'\"Condition\"'", "'\"Answer\"'", null, null, null, null, null, null, 
			null, "'['", "']'", "'{'", "'}'", "':'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "SURVEY_DOCUMENT", "SURVEY_ID", "SURVEY_TARGET", "SURVEY_TITLE", 
			"SURVEY_WELCOME", "SURVEY_SECTIONS", "SURVEY_END", "SURVEY_UNANSWERED", 
			"SURVEY_ANSWERED", "CONDITION_CATEGORY", "CONDITION_PRODUCT", "CONDITION_BRAND", 
			"CONDITION_GENDER", "CONDITION_AGE", "RULE_CONDITION", "CONDITION_TYPE", 
			"MINIMUM_AGE", "MAXIMUM_AGE", "OLDER", "YOUNGER", "SECTION_ID", "SECTION_TITLE", 
			"SECTION_DESCRIPTION", "OBLIGATORINESS", "REPEATABILITY", "CONTENT", 
			"QUESTION_ID", "QUESTION", "QUESTION_INSTRUCTION", "QUESTION_TYPE", "QUESTION_INFO", 
			"QUESTION_OPTIONS", "QUESTION_MAX_OPTIONS", "QUESTION_MAX_CHARS", "QUESTION_SCALE", 
			"FREE_TEXT", "NUMERIC", "SINGLE_CHOICE", "SINGLE_CHOICE_INPUT", "MULTIPLE_CHOICE", 
			"MULTIPLE_CHOICE_INPUT", "SORTING_OPTION", "SCALING_OPTION", "OPTIONAL", 
			"MANDATORY", "CONDITIONAL", "CONDITION", "ANSWER", "CHAR", "SPECIAL", 
			"INT", "WORD", "ID", "FALSE", "TRUE", "LIST_START", "LIST_END", "ELEM_START", 
			"ELEM_END", "SEPARATOR", "LINE", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "questionnaires.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public questionnairesParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class QuestionnairesContext extends ParserRuleContext {
		public Questionnaires_answeredContext questionnaires_answered() {
			return getRuleContext(Questionnaires_answeredContext.class,0);
		}
		public Questionnaires_unansweredContext questionnaires_unanswered() {
			return getRuleContext(Questionnaires_unansweredContext.class,0);
		}
		public QuestionnairesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionnaires; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterQuestionnaires(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitQuestionnaires(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitQuestionnaires(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionnairesContext questionnaires() throws RecognitionException {
		QuestionnairesContext _localctx = new QuestionnairesContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_questionnaires);
		try {
			setState(82);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(80);
				questionnaires_answered();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(81);
				questionnaires_unanswered();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Questionnaires_unansweredContext extends ParserRuleContext {
		public TerminalNode ELEM_START() { return getToken(questionnairesParser.ELEM_START, 0); }
		public Questionnaires_documentContext questionnaires_document() {
			return getRuleContext(Questionnaires_documentContext.class,0);
		}
		public List<TerminalNode> LINE() { return getTokens(questionnairesParser.LINE); }
		public TerminalNode LINE(int i) {
			return getToken(questionnairesParser.LINE, i);
		}
		public Questionnaires_idContext questionnaires_id() {
			return getRuleContext(Questionnaires_idContext.class,0);
		}
		public Questionnaires_titleContext questionnaires_title() {
			return getRuleContext(Questionnaires_titleContext.class,0);
		}
		public Questionnaires_welcome_messageContext questionnaires_welcome_message() {
			return getRuleContext(Questionnaires_welcome_messageContext.class,0);
		}
		public Questionnaires_sectionsContext questionnaires_sections() {
			return getRuleContext(Questionnaires_sectionsContext.class,0);
		}
		public TerminalNode SEPARATOR() { return getToken(questionnairesParser.SEPARATOR, 0); }
		public SectionsContext sections() {
			return getRuleContext(SectionsContext.class,0);
		}
		public Questionnaires_endingContext questionnaires_ending() {
			return getRuleContext(Questionnaires_endingContext.class,0);
		}
		public TerminalNode ELEM_END() { return getToken(questionnairesParser.ELEM_END, 0); }
		public Questionnaires_targetContext questionnaires_target() {
			return getRuleContext(Questionnaires_targetContext.class,0);
		}
		public Questionnaires_unansweredContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionnaires_unanswered; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterQuestionnaires_unanswered(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitQuestionnaires_unanswered(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitQuestionnaires_unanswered(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Questionnaires_unansweredContext questionnaires_unanswered() throws RecognitionException {
		Questionnaires_unansweredContext _localctx = new Questionnaires_unansweredContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_questionnaires_unanswered);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(ELEM_START);
			setState(85);
			questionnaires_document();
			setState(86);
			match(LINE);
			setState(87);
			questionnaires_id();
			setState(88);
			match(LINE);
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SURVEY_TARGET) {
				{
				setState(89);
				questionnaires_target();
				setState(90);
				match(LINE);
				}
			}

			setState(94);
			questionnaires_title();
			setState(95);
			match(LINE);
			setState(96);
			questionnaires_welcome_message();
			setState(97);
			match(LINE);
			setState(98);
			questionnaires_sections();
			setState(99);
			match(SEPARATOR);
			setState(100);
			sections();
			setState(101);
			match(LINE);
			setState(102);
			questionnaires_ending();
			setState(103);
			match(ELEM_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Questionnaires_answeredContext extends ParserRuleContext {
		public TerminalNode ELEM_START() { return getToken(questionnairesParser.ELEM_START, 0); }
		public Questionnaires_document_answerContext questionnaires_document_answer() {
			return getRuleContext(Questionnaires_document_answerContext.class,0);
		}
		public List<TerminalNode> LINE() { return getTokens(questionnairesParser.LINE); }
		public TerminalNode LINE(int i) {
			return getToken(questionnairesParser.LINE, i);
		}
		public Questionnaires_idContext questionnaires_id() {
			return getRuleContext(Questionnaires_idContext.class,0);
		}
		public Questionnaires_titleContext questionnaires_title() {
			return getRuleContext(Questionnaires_titleContext.class,0);
		}
		public Questionnaires_welcome_messageContext questionnaires_welcome_message() {
			return getRuleContext(Questionnaires_welcome_messageContext.class,0);
		}
		public Questionnaires_sectionsContext questionnaires_sections() {
			return getRuleContext(Questionnaires_sectionsContext.class,0);
		}
		public TerminalNode SEPARATOR() { return getToken(questionnairesParser.SEPARATOR, 0); }
		public Sections_answeredContext sections_answered() {
			return getRuleContext(Sections_answeredContext.class,0);
		}
		public Questionnaires_endingContext questionnaires_ending() {
			return getRuleContext(Questionnaires_endingContext.class,0);
		}
		public TerminalNode ELEM_END() { return getToken(questionnairesParser.ELEM_END, 0); }
		public Questionnaires_answeredContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionnaires_answered; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterQuestionnaires_answered(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitQuestionnaires_answered(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitQuestionnaires_answered(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Questionnaires_answeredContext questionnaires_answered() throws RecognitionException {
		Questionnaires_answeredContext _localctx = new Questionnaires_answeredContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_questionnaires_answered);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(ELEM_START);
			setState(106);
			questionnaires_document_answer();
			setState(107);
			match(LINE);
			setState(108);
			questionnaires_id();
			setState(109);
			match(LINE);
			setState(110);
			questionnaires_title();
			setState(111);
			match(LINE);
			setState(112);
			questionnaires_welcome_message();
			setState(113);
			match(LINE);
			setState(114);
			questionnaires_sections();
			setState(115);
			match(SEPARATOR);
			setState(116);
			sections_answered();
			setState(117);
			match(LINE);
			setState(118);
			questionnaires_ending();
			setState(119);
			match(ELEM_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Questionnaires_documentContext extends ParserRuleContext {
		public TerminalNode SURVEY_DOCUMENT() { return getToken(questionnairesParser.SURVEY_DOCUMENT, 0); }
		public TerminalNode SEPARATOR() { return getToken(questionnairesParser.SEPARATOR, 0); }
		public TerminalNode SURVEY_UNANSWERED() { return getToken(questionnairesParser.SURVEY_UNANSWERED, 0); }
		public Questionnaires_documentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionnaires_document; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterQuestionnaires_document(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitQuestionnaires_document(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitQuestionnaires_document(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Questionnaires_documentContext questionnaires_document() throws RecognitionException {
		Questionnaires_documentContext _localctx = new Questionnaires_documentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_questionnaires_document);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(SURVEY_DOCUMENT);
			setState(122);
			match(SEPARATOR);
			setState(123);
			match(SURVEY_UNANSWERED);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Questionnaires_document_answerContext extends ParserRuleContext {
		public TerminalNode SURVEY_DOCUMENT() { return getToken(questionnairesParser.SURVEY_DOCUMENT, 0); }
		public TerminalNode SEPARATOR() { return getToken(questionnairesParser.SEPARATOR, 0); }
		public TerminalNode SURVEY_ANSWERED() { return getToken(questionnairesParser.SURVEY_ANSWERED, 0); }
		public Questionnaires_document_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionnaires_document_answer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterQuestionnaires_document_answer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitQuestionnaires_document_answer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitQuestionnaires_document_answer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Questionnaires_document_answerContext questionnaires_document_answer() throws RecognitionException {
		Questionnaires_document_answerContext _localctx = new Questionnaires_document_answerContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_questionnaires_document_answer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(SURVEY_DOCUMENT);
			setState(126);
			match(SEPARATOR);
			setState(127);
			match(SURVEY_ANSWERED);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Questionnaires_idContext extends ParserRuleContext {
		public Token id;
		public TerminalNode SURVEY_ID() { return getToken(questionnairesParser.SURVEY_ID, 0); }
		public TerminalNode SEPARATOR() { return getToken(questionnairesParser.SEPARATOR, 0); }
		public TerminalNode ID() { return getToken(questionnairesParser.ID, 0); }
		public Questionnaires_idContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionnaires_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterQuestionnaires_id(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitQuestionnaires_id(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitQuestionnaires_id(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Questionnaires_idContext questionnaires_id() throws RecognitionException {
		Questionnaires_idContext _localctx = new Questionnaires_idContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_questionnaires_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			match(SURVEY_ID);
			setState(130);
			match(SEPARATOR);
			setState(131);
			((Questionnaires_idContext)_localctx).id = match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Questionnaires_targetContext extends ParserRuleContext {
		public TerminalNode SURVEY_TARGET() { return getToken(questionnairesParser.SURVEY_TARGET, 0); }
		public TerminalNode SEPARATOR() { return getToken(questionnairesParser.SEPARATOR, 0); }
		public TerminalNode LIST_START() { return getToken(questionnairesParser.LIST_START, 0); }
		public List<Target_ruleContext> target_rule() {
			return getRuleContexts(Target_ruleContext.class);
		}
		public Target_ruleContext target_rule(int i) {
			return getRuleContext(Target_ruleContext.class,i);
		}
		public TerminalNode LIST_END() { return getToken(questionnairesParser.LIST_END, 0); }
		public List<TerminalNode> LINE() { return getTokens(questionnairesParser.LINE); }
		public TerminalNode LINE(int i) {
			return getToken(questionnairesParser.LINE, i);
		}
		public Questionnaires_targetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionnaires_target; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterQuestionnaires_target(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitQuestionnaires_target(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitQuestionnaires_target(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Questionnaires_targetContext questionnaires_target() throws RecognitionException {
		Questionnaires_targetContext _localctx = new Questionnaires_targetContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_questionnaires_target);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(SURVEY_TARGET);
			setState(134);
			match(SEPARATOR);
			setState(135);
			match(LIST_START);
			setState(141);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(136);
					target_rule();
					setState(137);
					match(LINE);
					}
					} 
				}
				setState(143);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(144);
			target_rule();
			setState(145);
			match(LIST_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Target_ruleContext extends ParserRuleContext {
		public TerminalNode ELEM_START() { return getToken(questionnairesParser.ELEM_START, 0); }
		public TerminalNode RULE_CONDITION() { return getToken(questionnairesParser.RULE_CONDITION, 0); }
		public TerminalNode SEPARATOR() { return getToken(questionnairesParser.SEPARATOR, 0); }
		public TerminalNode LIST_START() { return getToken(questionnairesParser.LIST_START, 0); }
		public List<Rule_conditionContext> rule_condition() {
			return getRuleContexts(Rule_conditionContext.class);
		}
		public Rule_conditionContext rule_condition(int i) {
			return getRuleContext(Rule_conditionContext.class,i);
		}
		public TerminalNode LIST_END() { return getToken(questionnairesParser.LIST_END, 0); }
		public TerminalNode ELEM_END() { return getToken(questionnairesParser.ELEM_END, 0); }
		public List<TerminalNode> LINE() { return getTokens(questionnairesParser.LINE); }
		public TerminalNode LINE(int i) {
			return getToken(questionnairesParser.LINE, i);
		}
		public Target_ruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_target_rule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterTarget_rule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitTarget_rule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitTarget_rule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Target_ruleContext target_rule() throws RecognitionException {
		Target_ruleContext _localctx = new Target_ruleContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_target_rule);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(ELEM_START);
			setState(148);
			match(RULE_CONDITION);
			setState(149);
			match(SEPARATOR);
			setState(150);
			match(LIST_START);
			setState(156);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(151);
					rule_condition();
					setState(152);
					match(LINE);
					}
					} 
				}
				setState(158);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(159);
			rule_condition();
			setState(160);
			match(LIST_END);
			setState(161);
			match(ELEM_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rule_conditionContext extends ParserRuleContext {
		public Token type;
		public TextContext target;
		public Token minimum;
		public Token maximum;
		public Token older;
		public Token younger;
		public TerminalNode ELEM_START() { return getToken(questionnairesParser.ELEM_START, 0); }
		public TerminalNode ELEM_END() { return getToken(questionnairesParser.ELEM_END, 0); }
		public TerminalNode CONDITION_TYPE() { return getToken(questionnairesParser.CONDITION_TYPE, 0); }
		public List<TerminalNode> SEPARATOR() { return getTokens(questionnairesParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(questionnairesParser.SEPARATOR, i);
		}
		public List<TerminalNode> LINE() { return getTokens(questionnairesParser.LINE); }
		public TerminalNode LINE(int i) {
			return getToken(questionnairesParser.LINE, i);
		}
		public TerminalNode CONDITION() { return getToken(questionnairesParser.CONDITION, 0); }
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public TerminalNode CONDITION_AGE() { return getToken(questionnairesParser.CONDITION_AGE, 0); }
		public TerminalNode CONDITION_CATEGORY() { return getToken(questionnairesParser.CONDITION_CATEGORY, 0); }
		public TerminalNode CONDITION_PRODUCT() { return getToken(questionnairesParser.CONDITION_PRODUCT, 0); }
		public TerminalNode CONDITION_BRAND() { return getToken(questionnairesParser.CONDITION_BRAND, 0); }
		public TerminalNode CONDITION_GENDER() { return getToken(questionnairesParser.CONDITION_GENDER, 0); }
		public TerminalNode MINIMUM_AGE() { return getToken(questionnairesParser.MINIMUM_AGE, 0); }
		public TerminalNode MAXIMUM_AGE() { return getToken(questionnairesParser.MAXIMUM_AGE, 0); }
		public TerminalNode OLDER() { return getToken(questionnairesParser.OLDER, 0); }
		public TerminalNode YOUNGER() { return getToken(questionnairesParser.YOUNGER, 0); }
		public List<TerminalNode> INT() { return getTokens(questionnairesParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(questionnairesParser.INT, i);
		}
		public Rule_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rule_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterRule_condition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitRule_condition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitRule_condition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rule_conditionContext rule_condition() throws RecognitionException {
		Rule_conditionContext _localctx = new Rule_conditionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_rule_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(ELEM_START);
			setState(190);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				{
				setState(164);
				match(CONDITION_TYPE);
				setState(165);
				match(SEPARATOR);
				setState(166);
				((Rule_conditionContext)_localctx).type = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONDITION_CATEGORY) | (1L << CONDITION_PRODUCT) | (1L << CONDITION_BRAND) | (1L << CONDITION_GENDER))) != 0)) ) {
					((Rule_conditionContext)_localctx).type = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(167);
				match(LINE);
				setState(168);
				match(CONDITION);
				setState(169);
				match(SEPARATOR);
				setState(170);
				((Rule_conditionContext)_localctx).target = text();
				}
				}
				break;
			case 2:
				{
				{
				setState(171);
				match(CONDITION_TYPE);
				setState(172);
				match(SEPARATOR);
				setState(173);
				((Rule_conditionContext)_localctx).type = match(CONDITION_AGE);
				setState(174);
				match(LINE);
				setState(188);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case MINIMUM_AGE:
					{
					{
					setState(175);
					match(MINIMUM_AGE);
					setState(176);
					match(SEPARATOR);
					setState(177);
					((Rule_conditionContext)_localctx).minimum = match(INT);
					setState(178);
					match(LINE);
					setState(179);
					match(MAXIMUM_AGE);
					setState(180);
					match(SEPARATOR);
					setState(181);
					((Rule_conditionContext)_localctx).maximum = match(INT);
					}
					}
					break;
				case OLDER:
					{
					{
					setState(182);
					match(OLDER);
					setState(183);
					match(SEPARATOR);
					setState(184);
					((Rule_conditionContext)_localctx).older = match(INT);
					}
					}
					break;
				case YOUNGER:
					{
					{
					setState(185);
					match(YOUNGER);
					setState(186);
					match(SEPARATOR);
					setState(187);
					((Rule_conditionContext)_localctx).younger = match(INT);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				break;
			}
			setState(192);
			match(ELEM_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Questionnaires_titleContext extends ParserRuleContext {
		public TerminalNode SURVEY_TITLE() { return getToken(questionnairesParser.SURVEY_TITLE, 0); }
		public TerminalNode SEPARATOR() { return getToken(questionnairesParser.SEPARATOR, 0); }
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Questionnaires_titleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionnaires_title; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterQuestionnaires_title(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitQuestionnaires_title(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitQuestionnaires_title(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Questionnaires_titleContext questionnaires_title() throws RecognitionException {
		Questionnaires_titleContext _localctx = new Questionnaires_titleContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_questionnaires_title);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			match(SURVEY_TITLE);
			setState(195);
			match(SEPARATOR);
			setState(196);
			text();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Questionnaires_welcome_messageContext extends ParserRuleContext {
		public TerminalNode SURVEY_WELCOME() { return getToken(questionnairesParser.SURVEY_WELCOME, 0); }
		public TerminalNode SEPARATOR() { return getToken(questionnairesParser.SEPARATOR, 0); }
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Questionnaires_welcome_messageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionnaires_welcome_message; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterQuestionnaires_welcome_message(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitQuestionnaires_welcome_message(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitQuestionnaires_welcome_message(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Questionnaires_welcome_messageContext questionnaires_welcome_message() throws RecognitionException {
		Questionnaires_welcome_messageContext _localctx = new Questionnaires_welcome_messageContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_questionnaires_welcome_message);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			match(SURVEY_WELCOME);
			setState(199);
			match(SEPARATOR);
			setState(200);
			text();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Questionnaires_sectionsContext extends ParserRuleContext {
		public TerminalNode SURVEY_SECTIONS() { return getToken(questionnairesParser.SURVEY_SECTIONS, 0); }
		public Questionnaires_sectionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionnaires_sections; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterQuestionnaires_sections(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitQuestionnaires_sections(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitQuestionnaires_sections(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Questionnaires_sectionsContext questionnaires_sections() throws RecognitionException {
		Questionnaires_sectionsContext _localctx = new Questionnaires_sectionsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_questionnaires_sections);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			match(SURVEY_SECTIONS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Questionnaires_endingContext extends ParserRuleContext {
		public TerminalNode SURVEY_END() { return getToken(questionnairesParser.SURVEY_END, 0); }
		public TerminalNode SEPARATOR() { return getToken(questionnairesParser.SEPARATOR, 0); }
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Questionnaires_endingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionnaires_ending; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterQuestionnaires_ending(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitQuestionnaires_ending(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitQuestionnaires_ending(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Questionnaires_endingContext questionnaires_ending() throws RecognitionException {
		Questionnaires_endingContext _localctx = new Questionnaires_endingContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_questionnaires_ending);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			match(SURVEY_END);
			setState(205);
			match(SEPARATOR);
			setState(206);
			text();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SectionsContext extends ParserRuleContext {
		public TerminalNode LIST_START() { return getToken(questionnairesParser.LIST_START, 0); }
		public TerminalNode LIST_END() { return getToken(questionnairesParser.LIST_END, 0); }
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public List<TerminalNode> LINE() { return getTokens(questionnairesParser.LINE); }
		public TerminalNode LINE(int i) {
			return getToken(questionnairesParser.LINE, i);
		}
		public SectionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sections; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterSections(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitSections(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitSections(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionsContext sections() throws RecognitionException {
		SectionsContext _localctx = new SectionsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_sections);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(LIST_START);
			setState(213); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(209);
				section();
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LINE) {
					{
					setState(210);
					match(LINE);
					}
				}

				}
				}
				setState(215); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ELEM_START );
			setState(217);
			match(LIST_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sections_answeredContext extends ParserRuleContext {
		public TerminalNode LIST_START() { return getToken(questionnairesParser.LIST_START, 0); }
		public TerminalNode LIST_END() { return getToken(questionnairesParser.LIST_END, 0); }
		public List<Section_answeredContext> section_answered() {
			return getRuleContexts(Section_answeredContext.class);
		}
		public Section_answeredContext section_answered(int i) {
			return getRuleContext(Section_answeredContext.class,i);
		}
		public List<TerminalNode> LINE() { return getTokens(questionnairesParser.LINE); }
		public TerminalNode LINE(int i) {
			return getToken(questionnairesParser.LINE, i);
		}
		public Sections_answeredContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sections_answered; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterSections_answered(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitSections_answered(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitSections_answered(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Sections_answeredContext sections_answered() throws RecognitionException {
		Sections_answeredContext _localctx = new Sections_answeredContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_sections_answered);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(LIST_START);
			setState(224); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(220);
				section_answered();
				setState(222);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LINE) {
					{
					setState(221);
					match(LINE);
					}
				}

				}
				}
				setState(226); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ELEM_START );
			setState(228);
			match(LIST_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SectionContext extends ParserRuleContext {
		public TerminalNode ELEM_START() { return getToken(questionnairesParser.ELEM_START, 0); }
		public Section_idContext section_id() {
			return getRuleContext(Section_idContext.class,0);
		}
		public List<TerminalNode> LINE() { return getTokens(questionnairesParser.LINE); }
		public TerminalNode LINE(int i) {
			return getToken(questionnairesParser.LINE, i);
		}
		public Section_titleContext section_title() {
			return getRuleContext(Section_titleContext.class,0);
		}
		public Section_obligatorinessContext section_obligatoriness() {
			return getRuleContext(Section_obligatorinessContext.class,0);
		}
		public Section_repeatabilityContext section_repeatability() {
			return getRuleContext(Section_repeatabilityContext.class,0);
		}
		public Section_contentContext section_content() {
			return getRuleContext(Section_contentContext.class,0);
		}
		public TerminalNode ELEM_END() { return getToken(questionnairesParser.ELEM_END, 0); }
		public Section_descriptionContext section_description() {
			return getRuleContext(Section_descriptionContext.class,0);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_section);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			match(ELEM_START);
			setState(231);
			section_id();
			setState(232);
			match(LINE);
			setState(233);
			section_title();
			setState(234);
			match(LINE);
			setState(238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SECTION_DESCRIPTION) {
				{
				setState(235);
				section_description();
				setState(236);
				match(LINE);
				}
			}

			setState(240);
			section_obligatoriness();
			setState(241);
			match(LINE);
			setState(242);
			section_repeatability();
			setState(243);
			match(LINE);
			setState(244);
			section_content();
			setState(245);
			match(ELEM_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Section_answeredContext extends ParserRuleContext {
		public TerminalNode ELEM_START() { return getToken(questionnairesParser.ELEM_START, 0); }
		public Section_idContext section_id() {
			return getRuleContext(Section_idContext.class,0);
		}
		public List<TerminalNode> LINE() { return getTokens(questionnairesParser.LINE); }
		public TerminalNode LINE(int i) {
			return getToken(questionnairesParser.LINE, i);
		}
		public Section_titleContext section_title() {
			return getRuleContext(Section_titleContext.class,0);
		}
		public Section_obligatorinessContext section_obligatoriness() {
			return getRuleContext(Section_obligatorinessContext.class,0);
		}
		public Section_repeatabilityContext section_repeatability() {
			return getRuleContext(Section_repeatabilityContext.class,0);
		}
		public TerminalNode ELEM_END() { return getToken(questionnairesParser.ELEM_END, 0); }
		public List<Section_content_answeredContext> section_content_answered() {
			return getRuleContexts(Section_content_answeredContext.class);
		}
		public Section_content_answeredContext section_content_answered(int i) {
			return getRuleContext(Section_content_answeredContext.class,i);
		}
		public Section_descriptionContext section_description() {
			return getRuleContext(Section_descriptionContext.class,0);
		}
		public Section_answeredContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section_answered; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterSection_answered(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitSection_answered(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitSection_answered(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Section_answeredContext section_answered() throws RecognitionException {
		Section_answeredContext _localctx = new Section_answeredContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_section_answered);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(ELEM_START);
			setState(248);
			section_id();
			setState(249);
			match(LINE);
			setState(250);
			section_title();
			setState(251);
			match(LINE);
			setState(255);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SECTION_DESCRIPTION) {
				{
				setState(252);
				section_description();
				setState(253);
				match(LINE);
				}
			}

			setState(257);
			section_obligatoriness();
			setState(258);
			match(LINE);
			setState(259);
			section_repeatability();
			setState(260);
			match(LINE);
			setState(271);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(261);
				section_content_answered();
				}
				break;
			case 2:
				{
				setState(265); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(262);
						section_content_answered();
						setState(263);
						match(LINE);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(267); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(269);
				section_content_answered();
				}
				break;
			}
			setState(273);
			match(ELEM_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Section_idContext extends ParserRuleContext {
		public Token sect_id;
		public TerminalNode SECTION_ID() { return getToken(questionnairesParser.SECTION_ID, 0); }
		public TerminalNode SEPARATOR() { return getToken(questionnairesParser.SEPARATOR, 0); }
		public TerminalNode INT() { return getToken(questionnairesParser.INT, 0); }
		public Section_idContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterSection_id(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitSection_id(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitSection_id(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Section_idContext section_id() throws RecognitionException {
		Section_idContext _localctx = new Section_idContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_section_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			match(SECTION_ID);
			setState(276);
			match(SEPARATOR);
			setState(277);
			((Section_idContext)_localctx).sect_id = match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Section_titleContext extends ParserRuleContext {
		public TerminalNode SECTION_TITLE() { return getToken(questionnairesParser.SECTION_TITLE, 0); }
		public TerminalNode SEPARATOR() { return getToken(questionnairesParser.SEPARATOR, 0); }
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Section_titleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section_title; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterSection_title(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitSection_title(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitSection_title(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Section_titleContext section_title() throws RecognitionException {
		Section_titleContext _localctx = new Section_titleContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_section_title);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			match(SECTION_TITLE);
			setState(280);
			match(SEPARATOR);
			setState(281);
			text();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Section_descriptionContext extends ParserRuleContext {
		public TerminalNode SECTION_DESCRIPTION() { return getToken(questionnairesParser.SECTION_DESCRIPTION, 0); }
		public TerminalNode SEPARATOR() { return getToken(questionnairesParser.SEPARATOR, 0); }
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Section_descriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section_description; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterSection_description(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitSection_description(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitSection_description(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Section_descriptionContext section_description() throws RecognitionException {
		Section_descriptionContext _localctx = new Section_descriptionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_section_description);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			match(SECTION_DESCRIPTION);
			setState(284);
			match(SEPARATOR);
			setState(285);
			text();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Section_obligatorinessContext extends ParserRuleContext {
		public Token type;
		public TerminalNode OBLIGATORINESS() { return getToken(questionnairesParser.OBLIGATORINESS, 0); }
		public TerminalNode SEPARATOR() { return getToken(questionnairesParser.SEPARATOR, 0); }
		public TerminalNode LINE() { return getToken(questionnairesParser.LINE, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode OPTIONAL() { return getToken(questionnairesParser.OPTIONAL, 0); }
		public TerminalNode MANDATORY() { return getToken(questionnairesParser.MANDATORY, 0); }
		public TerminalNode CONDITIONAL() { return getToken(questionnairesParser.CONDITIONAL, 0); }
		public Section_obligatorinessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section_obligatoriness; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterSection_obligatoriness(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitSection_obligatoriness(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitSection_obligatoriness(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Section_obligatorinessContext section_obligatoriness() throws RecognitionException {
		Section_obligatorinessContext _localctx = new Section_obligatorinessContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_section_obligatoriness);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			match(OBLIGATORINESS);
			setState(288);
			match(SEPARATOR);
			setState(294);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPTIONAL:
				{
				setState(289);
				((Section_obligatorinessContext)_localctx).type = match(OPTIONAL);
				}
				break;
			case MANDATORY:
				{
				setState(290);
				((Section_obligatorinessContext)_localctx).type = match(MANDATORY);
				}
				break;
			case CONDITIONAL:
				{
				setState(291);
				((Section_obligatorinessContext)_localctx).type = match(CONDITIONAL);
				setState(292);
				match(LINE);
				setState(293);
				condition();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public TerminalNode CONDITION() { return getToken(questionnairesParser.CONDITION, 0); }
		public List<TerminalNode> SEPARATOR() { return getTokens(questionnairesParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(questionnairesParser.SEPARATOR, i);
		}
		public TerminalNode ELEM_START() { return getToken(questionnairesParser.ELEM_START, 0); }
		public Section_idContext section_id() {
			return getRuleContext(Section_idContext.class,0);
		}
		public List<TerminalNode> LINE() { return getTokens(questionnairesParser.LINE); }
		public TerminalNode LINE(int i) {
			return getToken(questionnairesParser.LINE, i);
		}
		public Question_idContext question_id() {
			return getRuleContext(Question_idContext.class,0);
		}
		public TerminalNode ELEM_END() { return getToken(questionnairesParser.ELEM_END, 0); }
		public TerminalNode ANSWER() { return getToken(questionnairesParser.ANSWER, 0); }
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			match(CONDITION);
			setState(297);
			match(SEPARATOR);
			setState(298);
			match(ELEM_START);
			setState(299);
			section_id();
			setState(300);
			match(LINE);
			setState(301);
			question_id();
			setState(306);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LINE) {
				{
				setState(302);
				match(LINE);
				setState(303);
				match(ANSWER);
				setState(304);
				match(SEPARATOR);
				setState(305);
				text();
				}
			}

			setState(308);
			match(ELEM_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Section_repeatabilityContext extends ParserRuleContext {
		public TerminalNode REPEATABILITY() { return getToken(questionnairesParser.REPEATABILITY, 0); }
		public TerminalNode SEPARATOR() { return getToken(questionnairesParser.SEPARATOR, 0); }
		public TerminalNode TRUE() { return getToken(questionnairesParser.TRUE, 0); }
		public TerminalNode LINE() { return getToken(questionnairesParser.LINE, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode FALSE() { return getToken(questionnairesParser.FALSE, 0); }
		public Section_repeatabilityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section_repeatability; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterSection_repeatability(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitSection_repeatability(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitSection_repeatability(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Section_repeatabilityContext section_repeatability() throws RecognitionException {
		Section_repeatabilityContext _localctx = new Section_repeatabilityContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_section_repeatability);
		try {
			setState(318);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(310);
				match(REPEATABILITY);
				setState(311);
				match(SEPARATOR);
				setState(312);
				match(TRUE);
				setState(313);
				match(LINE);
				setState(314);
				condition();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(315);
				match(REPEATABILITY);
				setState(316);
				match(SEPARATOR);
				setState(317);
				match(FALSE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Section_contentContext extends ParserRuleContext {
		public TerminalNode CONTENT() { return getToken(questionnairesParser.CONTENT, 0); }
		public TerminalNode SEPARATOR() { return getToken(questionnairesParser.SEPARATOR, 0); }
		public QuestionsContext questions() {
			return getRuleContext(QuestionsContext.class,0);
		}
		public Section_contentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section_content; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterSection_content(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitSection_content(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitSection_content(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Section_contentContext section_content() throws RecognitionException {
		Section_contentContext _localctx = new Section_contentContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_section_content);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			match(CONTENT);
			setState(321);
			match(SEPARATOR);
			setState(322);
			questions();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Section_content_answeredContext extends ParserRuleContext {
		public TerminalNode CONTENT() { return getToken(questionnairesParser.CONTENT, 0); }
		public TerminalNode SEPARATOR() { return getToken(questionnairesParser.SEPARATOR, 0); }
		public Questions_answeredContext questions_answered() {
			return getRuleContext(Questions_answeredContext.class,0);
		}
		public Section_content_answeredContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section_content_answered; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterSection_content_answered(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitSection_content_answered(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitSection_content_answered(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Section_content_answeredContext section_content_answered() throws RecognitionException {
		Section_content_answeredContext _localctx = new Section_content_answeredContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_section_content_answered);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			match(CONTENT);
			setState(325);
			match(SEPARATOR);
			setState(326);
			questions_answered();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuestionsContext extends ParserRuleContext {
		public TerminalNode LIST_START() { return getToken(questionnairesParser.LIST_START, 0); }
		public TerminalNode LIST_END() { return getToken(questionnairesParser.LIST_END, 0); }
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public List<TerminalNode> LINE() { return getTokens(questionnairesParser.LINE); }
		public TerminalNode LINE(int i) {
			return getToken(questionnairesParser.LINE, i);
		}
		public QuestionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterQuestions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitQuestions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitQuestions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionsContext questions() throws RecognitionException {
		QuestionsContext _localctx = new QuestionsContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_questions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(328);
			match(LIST_START);
			setState(333); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(329);
				question();
				setState(331);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LINE) {
					{
					setState(330);
					match(LINE);
					}
				}

				}
				}
				setState(335); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ELEM_START );
			setState(337);
			match(LIST_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Questions_answeredContext extends ParserRuleContext {
		public TerminalNode LIST_START() { return getToken(questionnairesParser.LIST_START, 0); }
		public TerminalNode LIST_END() { return getToken(questionnairesParser.LIST_END, 0); }
		public List<Question_answeredContext> question_answered() {
			return getRuleContexts(Question_answeredContext.class);
		}
		public Question_answeredContext question_answered(int i) {
			return getRuleContext(Question_answeredContext.class,i);
		}
		public List<TerminalNode> LINE() { return getTokens(questionnairesParser.LINE); }
		public TerminalNode LINE(int i) {
			return getToken(questionnairesParser.LINE, i);
		}
		public Questions_answeredContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questions_answered; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterQuestions_answered(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitQuestions_answered(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitQuestions_answered(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Questions_answeredContext questions_answered() throws RecognitionException {
		Questions_answeredContext _localctx = new Questions_answeredContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_questions_answered);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339);
			match(LIST_START);
			setState(344); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(340);
				question_answered();
				setState(342);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LINE) {
					{
					setState(341);
					match(LINE);
					}
				}

				}
				}
				setState(346); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ELEM_START );
			setState(348);
			match(LIST_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuestionContext extends ParserRuleContext {
		public TerminalNode ELEM_START() { return getToken(questionnairesParser.ELEM_START, 0); }
		public Question_idContext question_id() {
			return getRuleContext(Question_idContext.class,0);
		}
		public List<TerminalNode> LINE() { return getTokens(questionnairesParser.LINE); }
		public TerminalNode LINE(int i) {
			return getToken(questionnairesParser.LINE, i);
		}
		public Question_textContext question_text() {
			return getRuleContext(Question_textContext.class,0);
		}
		public Question_typeContext question_type() {
			return getRuleContext(Question_typeContext.class,0);
		}
		public ObligatorinessContext obligatoriness() {
			return getRuleContext(ObligatorinessContext.class,0);
		}
		public TerminalNode ELEM_END() { return getToken(questionnairesParser.ELEM_END, 0); }
		public Question_instructionContext question_instruction() {
			return getRuleContext(Question_instructionContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_question);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			match(ELEM_START);
			setState(351);
			question_id();
			setState(352);
			match(LINE);
			setState(353);
			question_text();
			setState(354);
			match(LINE);
			setState(358);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUESTION_INSTRUCTION) {
				{
				setState(355);
				question_instruction();
				setState(356);
				match(LINE);
				}
			}

			setState(360);
			question_type();
			setState(361);
			match(LINE);
			setState(362);
			obligatoriness();
			setState(363);
			match(ELEM_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Question_answeredContext extends ParserRuleContext {
		public TerminalNode ELEM_START() { return getToken(questionnairesParser.ELEM_START, 0); }
		public Question_idContext question_id() {
			return getRuleContext(Question_idContext.class,0);
		}
		public List<TerminalNode> LINE() { return getTokens(questionnairesParser.LINE); }
		public TerminalNode LINE(int i) {
			return getToken(questionnairesParser.LINE, i);
		}
		public Question_textContext question_text() {
			return getRuleContext(Question_textContext.class,0);
		}
		public Question_typeContext question_type() {
			return getRuleContext(Question_typeContext.class,0);
		}
		public Obligatoriness_answeredContext obligatoriness_answered() {
			return getRuleContext(Obligatoriness_answeredContext.class,0);
		}
		public TerminalNode ELEM_END() { return getToken(questionnairesParser.ELEM_END, 0); }
		public Question_instructionContext question_instruction() {
			return getRuleContext(Question_instructionContext.class,0);
		}
		public Question_answeredContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_answered; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterQuestion_answered(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitQuestion_answered(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitQuestion_answered(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Question_answeredContext question_answered() throws RecognitionException {
		Question_answeredContext _localctx = new Question_answeredContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_question_answered);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(365);
			match(ELEM_START);
			setState(366);
			question_id();
			setState(367);
			match(LINE);
			setState(368);
			question_text();
			setState(369);
			match(LINE);
			setState(373);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUESTION_INSTRUCTION) {
				{
				setState(370);
				question_instruction();
				setState(371);
				match(LINE);
				}
			}

			setState(375);
			question_type();
			setState(376);
			match(LINE);
			setState(377);
			obligatoriness_answered();
			setState(378);
			match(ELEM_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Question_idContext extends ParserRuleContext {
		public Token quest_id;
		public TerminalNode QUESTION_ID() { return getToken(questionnairesParser.QUESTION_ID, 0); }
		public TerminalNode SEPARATOR() { return getToken(questionnairesParser.SEPARATOR, 0); }
		public TerminalNode INT() { return getToken(questionnairesParser.INT, 0); }
		public Question_idContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterQuestion_id(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitQuestion_id(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitQuestion_id(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Question_idContext question_id() throws RecognitionException {
		Question_idContext _localctx = new Question_idContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_question_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(380);
			match(QUESTION_ID);
			setState(381);
			match(SEPARATOR);
			setState(382);
			((Question_idContext)_localctx).quest_id = match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Question_textContext extends ParserRuleContext {
		public TerminalNode QUESTION() { return getToken(questionnairesParser.QUESTION, 0); }
		public TerminalNode SEPARATOR() { return getToken(questionnairesParser.SEPARATOR, 0); }
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Question_textContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_text; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterQuestion_text(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitQuestion_text(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitQuestion_text(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Question_textContext question_text() throws RecognitionException {
		Question_textContext _localctx = new Question_textContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_question_text);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(384);
			match(QUESTION);
			setState(385);
			match(SEPARATOR);
			setState(386);
			text();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Question_optionsContext extends ParserRuleContext {
		public OptionContext list;
		public TerminalNode LIST_START() { return getToken(questionnairesParser.LIST_START, 0); }
		public TerminalNode LIST_END() { return getToken(questionnairesParser.LIST_END, 0); }
		public OptionContext option() {
			return getRuleContext(OptionContext.class,0);
		}
		public Question_optionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_options; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterQuestion_options(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitQuestion_options(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitQuestion_options(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Question_optionsContext question_options() throws RecognitionException {
		Question_optionsContext _localctx = new Question_optionsContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_question_options);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(388);
			match(LIST_START);
			setState(389);
			((Question_optionsContext)_localctx).list = option();
			setState(390);
			match(LIST_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionContext extends ParserRuleContext {
		public List<TextContext> text() {
			return getRuleContexts(TextContext.class);
		}
		public TextContext text(int i) {
			return getRuleContext(TextContext.class,i);
		}
		public List<TerminalNode> LINE() { return getTokens(questionnairesParser.LINE); }
		public TerminalNode LINE(int i) {
			return getToken(questionnairesParser.LINE, i);
		}
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_option);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
			text();
			setState(397);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LINE) {
				{
				{
				setState(393);
				match(LINE);
				setState(394);
				text();
				}
				}
				setState(399);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Question_typeContext extends ParserRuleContext {
		public Token type;
		public Token info;
		public Question_optionsContext question_list;
		public Token max_options;
		public Token max_chars;
		public Question_optionsContext question_scale;
		public TerminalNode QUESTION_TYPE() { return getToken(questionnairesParser.QUESTION_TYPE, 0); }
		public List<TerminalNode> SEPARATOR() { return getTokens(questionnairesParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(questionnairesParser.SEPARATOR, i);
		}
		public List<TerminalNode> LINE() { return getTokens(questionnairesParser.LINE); }
		public TerminalNode LINE(int i) {
			return getToken(questionnairesParser.LINE, i);
		}
		public TerminalNode ELEM_START() { return getToken(questionnairesParser.ELEM_START, 0); }
		public TerminalNode QUESTION_OPTIONS() { return getToken(questionnairesParser.QUESTION_OPTIONS, 0); }
		public TerminalNode ELEM_END() { return getToken(questionnairesParser.ELEM_END, 0); }
		public TerminalNode QUESTION_MAX_CHARS() { return getToken(questionnairesParser.QUESTION_MAX_CHARS, 0); }
		public TerminalNode QUESTION_SCALE() { return getToken(questionnairesParser.QUESTION_SCALE, 0); }
		public TerminalNode QUESTION_INFO() { return getToken(questionnairesParser.QUESTION_INFO, 0); }
		public List<Question_optionsContext> question_options() {
			return getRuleContexts(Question_optionsContext.class);
		}
		public Question_optionsContext question_options(int i) {
			return getRuleContext(Question_optionsContext.class,i);
		}
		public TerminalNode FREE_TEXT() { return getToken(questionnairesParser.FREE_TEXT, 0); }
		public TerminalNode INT() { return getToken(questionnairesParser.INT, 0); }
		public TerminalNode NUMERIC() { return getToken(questionnairesParser.NUMERIC, 0); }
		public TerminalNode SCALING_OPTION() { return getToken(questionnairesParser.SCALING_OPTION, 0); }
		public TerminalNode SINGLE_CHOICE() { return getToken(questionnairesParser.SINGLE_CHOICE, 0); }
		public TerminalNode SINGLE_CHOICE_INPUT() { return getToken(questionnairesParser.SINGLE_CHOICE_INPUT, 0); }
		public TerminalNode MULTIPLE_CHOICE() { return getToken(questionnairesParser.MULTIPLE_CHOICE, 0); }
		public TerminalNode MULTIPLE_CHOICE_INPUT() { return getToken(questionnairesParser.MULTIPLE_CHOICE_INPUT, 0); }
		public TerminalNode SORTING_OPTION() { return getToken(questionnairesParser.SORTING_OPTION, 0); }
		public TerminalNode QUESTION_MAX_OPTIONS() { return getToken(questionnairesParser.QUESTION_MAX_OPTIONS, 0); }
		public Question_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterQuestion_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitQuestion_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitQuestion_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Question_typeContext question_type() throws RecognitionException {
		Question_typeContext _localctx = new Question_typeContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_question_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
			match(QUESTION_TYPE);
			setState(401);
			match(SEPARATOR);
			setState(452);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SINGLE_CHOICE:
			case SINGLE_CHOICE_INPUT:
				{
				setState(402);
				((Question_typeContext)_localctx).type = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==SINGLE_CHOICE || _la==SINGLE_CHOICE_INPUT) ) {
					((Question_typeContext)_localctx).type = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(403);
				match(LINE);
				setState(404);
				((Question_typeContext)_localctx).info = match(QUESTION_INFO);
				setState(405);
				match(SEPARATOR);
				setState(406);
				match(ELEM_START);
				setState(407);
				match(QUESTION_OPTIONS);
				setState(408);
				match(SEPARATOR);
				setState(409);
				((Question_typeContext)_localctx).question_list = question_options();
				setState(410);
				match(ELEM_END);
				}
				break;
			case MULTIPLE_CHOICE:
			case MULTIPLE_CHOICE_INPUT:
			case SORTING_OPTION:
				{
				setState(412);
				((Question_typeContext)_localctx).type = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLE_CHOICE) | (1L << MULTIPLE_CHOICE_INPUT) | (1L << SORTING_OPTION))) != 0)) ) {
					((Question_typeContext)_localctx).type = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(413);
				match(LINE);
				setState(414);
				((Question_typeContext)_localctx).info = match(QUESTION_INFO);
				setState(415);
				match(SEPARATOR);
				setState(416);
				match(ELEM_START);
				setState(417);
				match(QUESTION_OPTIONS);
				setState(418);
				match(SEPARATOR);
				setState(419);
				((Question_typeContext)_localctx).question_list = question_options();
				setState(424);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LINE) {
					{
					setState(420);
					match(LINE);
					setState(421);
					match(QUESTION_MAX_OPTIONS);
					setState(422);
					match(SEPARATOR);
					setState(423);
					((Question_typeContext)_localctx).max_options = match(INT);
					}
				}

				setState(426);
				match(ELEM_END);
				}
				break;
			case FREE_TEXT:
				{
				setState(428);
				((Question_typeContext)_localctx).type = match(FREE_TEXT);
				setState(429);
				match(LINE);
				setState(430);
				((Question_typeContext)_localctx).info = match(QUESTION_INFO);
				setState(431);
				match(SEPARATOR);
				setState(432);
				match(ELEM_START);
				setState(433);
				match(QUESTION_MAX_CHARS);
				setState(434);
				match(SEPARATOR);
				setState(435);
				((Question_typeContext)_localctx).max_chars = match(INT);
				setState(436);
				match(ELEM_END);
				}
				break;
			case NUMERIC:
				{
				setState(437);
				((Question_typeContext)_localctx).type = match(NUMERIC);
				}
				break;
			case SCALING_OPTION:
				{
				setState(438);
				((Question_typeContext)_localctx).type = match(SCALING_OPTION);
				setState(439);
				match(LINE);
				setState(440);
				((Question_typeContext)_localctx).info = match(QUESTION_INFO);
				setState(441);
				match(SEPARATOR);
				setState(442);
				match(ELEM_START);
				setState(443);
				match(QUESTION_OPTIONS);
				setState(444);
				match(SEPARATOR);
				setState(445);
				((Question_typeContext)_localctx).question_list = question_options();
				setState(446);
				match(LINE);
				setState(447);
				match(QUESTION_SCALE);
				setState(448);
				match(SEPARATOR);
				setState(449);
				((Question_typeContext)_localctx).question_scale = question_options();
				setState(450);
				match(ELEM_END);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Question_instructionContext extends ParserRuleContext {
		public TerminalNode QUESTION_INSTRUCTION() { return getToken(questionnairesParser.QUESTION_INSTRUCTION, 0); }
		public TerminalNode SEPARATOR() { return getToken(questionnairesParser.SEPARATOR, 0); }
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Question_instructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterQuestion_instruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitQuestion_instruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitQuestion_instruction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Question_instructionContext question_instruction() throws RecognitionException {
		Question_instructionContext _localctx = new Question_instructionContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_question_instruction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(454);
			match(QUESTION_INSTRUCTION);
			setState(455);
			match(SEPARATOR);
			setState(456);
			text();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObligatorinessContext extends ParserRuleContext {
		public Token type;
		public Token answer_section_id;
		public Token answer_question_id;
		public OptionContext answer_list;
		public TextContext answer_option;
		public TerminalNode OBLIGATORINESS() { return getToken(questionnairesParser.OBLIGATORINESS, 0); }
		public List<TerminalNode> SEPARATOR() { return getTokens(questionnairesParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(questionnairesParser.SEPARATOR, i);
		}
		public List<TerminalNode> LINE() { return getTokens(questionnairesParser.LINE); }
		public TerminalNode LINE(int i) {
			return getToken(questionnairesParser.LINE, i);
		}
		public TerminalNode CONDITION() { return getToken(questionnairesParser.CONDITION, 0); }
		public TerminalNode ELEM_START() { return getToken(questionnairesParser.ELEM_START, 0); }
		public TerminalNode SECTION_ID() { return getToken(questionnairesParser.SECTION_ID, 0); }
		public TerminalNode QUESTION_ID() { return getToken(questionnairesParser.QUESTION_ID, 0); }
		public TerminalNode ELEM_END() { return getToken(questionnairesParser.ELEM_END, 0); }
		public TerminalNode OPTIONAL() { return getToken(questionnairesParser.OPTIONAL, 0); }
		public TerminalNode MANDATORY() { return getToken(questionnairesParser.MANDATORY, 0); }
		public TerminalNode CONDITIONAL() { return getToken(questionnairesParser.CONDITIONAL, 0); }
		public List<TerminalNode> INT() { return getTokens(questionnairesParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(questionnairesParser.INT, i);
		}
		public TerminalNode ANSWER() { return getToken(questionnairesParser.ANSWER, 0); }
		public TerminalNode LIST_START() { return getToken(questionnairesParser.LIST_START, 0); }
		public TerminalNode LIST_END() { return getToken(questionnairesParser.LIST_END, 0); }
		public OptionContext option() {
			return getRuleContext(OptionContext.class,0);
		}
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public ObligatorinessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obligatoriness; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterObligatoriness(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitObligatoriness(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitObligatoriness(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObligatorinessContext obligatoriness() throws RecognitionException {
		ObligatorinessContext _localctx = new ObligatorinessContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_obligatoriness);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(458);
			match(OBLIGATORINESS);
			setState(459);
			match(SEPARATOR);
			setState(488);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPTIONAL:
				{
				setState(460);
				((ObligatorinessContext)_localctx).type = match(OPTIONAL);
				}
				break;
			case MANDATORY:
				{
				setState(461);
				((ObligatorinessContext)_localctx).type = match(MANDATORY);
				}
				break;
			case CONDITIONAL:
				{
				setState(462);
				((ObligatorinessContext)_localctx).type = match(CONDITIONAL);
				setState(463);
				match(LINE);
				setState(464);
				match(CONDITION);
				setState(465);
				match(SEPARATOR);
				setState(466);
				match(ELEM_START);
				setState(467);
				match(SECTION_ID);
				setState(468);
				match(SEPARATOR);
				setState(469);
				((ObligatorinessContext)_localctx).answer_section_id = match(INT);
				setState(470);
				match(LINE);
				setState(471);
				match(QUESTION_ID);
				setState(472);
				match(SEPARATOR);
				setState(473);
				((ObligatorinessContext)_localctx).answer_question_id = match(INT);
				setState(485);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
				case 1:
					{
					setState(474);
					match(LINE);
					setState(475);
					match(ANSWER);
					setState(476);
					match(SEPARATOR);
					setState(477);
					match(LIST_START);
					setState(478);
					((ObligatorinessContext)_localctx).answer_list = option();
					setState(479);
					match(LIST_END);
					}
					break;
				case 2:
					{
					setState(481);
					match(LINE);
					setState(482);
					match(ANSWER);
					setState(483);
					match(SEPARATOR);
					setState(484);
					((ObligatorinessContext)_localctx).answer_option = text();
					}
					break;
				}
				setState(487);
				match(ELEM_END);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Obligatoriness_answeredContext extends ParserRuleContext {
		public Token type;
		public Token answer_section_id;
		public Token answer_question_id;
		public OptionContext answer_list;
		public TextContext answer_option;
		public TerminalNode OBLIGATORINESS() { return getToken(questionnairesParser.OBLIGATORINESS, 0); }
		public List<TerminalNode> SEPARATOR() { return getTokens(questionnairesParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(questionnairesParser.SEPARATOR, i);
		}
		public List<TerminalNode> LINE() { return getTokens(questionnairesParser.LINE); }
		public TerminalNode LINE(int i) {
			return getToken(questionnairesParser.LINE, i);
		}
		public AnswerContext answer() {
			return getRuleContext(AnswerContext.class,0);
		}
		public TerminalNode CONDITION() { return getToken(questionnairesParser.CONDITION, 0); }
		public TerminalNode ELEM_START() { return getToken(questionnairesParser.ELEM_START, 0); }
		public TerminalNode SECTION_ID() { return getToken(questionnairesParser.SECTION_ID, 0); }
		public TerminalNode QUESTION_ID() { return getToken(questionnairesParser.QUESTION_ID, 0); }
		public TerminalNode ELEM_END() { return getToken(questionnairesParser.ELEM_END, 0); }
		public TerminalNode OPTIONAL() { return getToken(questionnairesParser.OPTIONAL, 0); }
		public TerminalNode MANDATORY() { return getToken(questionnairesParser.MANDATORY, 0); }
		public TerminalNode CONDITIONAL() { return getToken(questionnairesParser.CONDITIONAL, 0); }
		public List<TerminalNode> INT() { return getTokens(questionnairesParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(questionnairesParser.INT, i);
		}
		public TerminalNode ANSWER() { return getToken(questionnairesParser.ANSWER, 0); }
		public TerminalNode LIST_START() { return getToken(questionnairesParser.LIST_START, 0); }
		public TerminalNode LIST_END() { return getToken(questionnairesParser.LIST_END, 0); }
		public OptionContext option() {
			return getRuleContext(OptionContext.class,0);
		}
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public Obligatoriness_answeredContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obligatoriness_answered; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterObligatoriness_answered(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitObligatoriness_answered(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitObligatoriness_answered(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Obligatoriness_answeredContext obligatoriness_answered() throws RecognitionException {
		Obligatoriness_answeredContext _localctx = new Obligatoriness_answeredContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_obligatoriness_answered);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(490);
			match(OBLIGATORINESS);
			setState(491);
			match(SEPARATOR);
			setState(530);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPTIONAL:
				{
				setState(492);
				((Obligatoriness_answeredContext)_localctx).type = match(OPTIONAL);
				setState(495);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LINE) {
					{
					setState(493);
					match(LINE);
					setState(494);
					answer();
					}
				}

				}
				break;
			case MANDATORY:
				{
				setState(497);
				((Obligatoriness_answeredContext)_localctx).type = match(MANDATORY);
				setState(498);
				match(LINE);
				setState(499);
				answer();
				}
				break;
			case CONDITIONAL:
				{
				setState(500);
				((Obligatoriness_answeredContext)_localctx).type = match(CONDITIONAL);
				setState(501);
				match(LINE);
				setState(502);
				match(CONDITION);
				setState(503);
				match(SEPARATOR);
				setState(504);
				match(ELEM_START);
				setState(505);
				match(SECTION_ID);
				setState(506);
				match(SEPARATOR);
				setState(507);
				((Obligatoriness_answeredContext)_localctx).answer_section_id = match(INT);
				setState(508);
				match(LINE);
				setState(509);
				match(QUESTION_ID);
				setState(510);
				match(SEPARATOR);
				setState(511);
				((Obligatoriness_answeredContext)_localctx).answer_question_id = match(INT);
				setState(523);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
				case 1:
					{
					setState(512);
					match(LINE);
					setState(513);
					match(ANSWER);
					setState(514);
					match(SEPARATOR);
					setState(515);
					match(LIST_START);
					setState(516);
					((Obligatoriness_answeredContext)_localctx).answer_list = option();
					setState(517);
					match(LIST_END);
					}
					break;
				case 2:
					{
					setState(519);
					match(LINE);
					setState(520);
					match(ANSWER);
					setState(521);
					match(SEPARATOR);
					setState(522);
					((Obligatoriness_answeredContext)_localctx).answer_option = text();
					}
					break;
				}
				setState(525);
				match(ELEM_END);
				setState(528);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LINE) {
					{
					setState(526);
					match(LINE);
					setState(527);
					answer();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnswerContext extends ParserRuleContext {
		public Token numeric_answer;
		public OptionContext text_answer;
		public OptionContext list_answer;
		public TerminalNode ANSWER() { return getToken(questionnairesParser.ANSWER, 0); }
		public TerminalNode SEPARATOR() { return getToken(questionnairesParser.SEPARATOR, 0); }
		public TerminalNode INT() { return getToken(questionnairesParser.INT, 0); }
		public OptionContext option() {
			return getRuleContext(OptionContext.class,0);
		}
		public TerminalNode LIST_START() { return getToken(questionnairesParser.LIST_START, 0); }
		public TerminalNode LIST_END() { return getToken(questionnairesParser.LIST_END, 0); }
		public AnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerContext answer() throws RecognitionException {
		AnswerContext _localctx = new AnswerContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_answer);
		try {
			setState(544);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(532);
				match(ANSWER);
				setState(533);
				match(SEPARATOR);
				setState(534);
				((AnswerContext)_localctx).numeric_answer = match(INT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(535);
				match(ANSWER);
				setState(536);
				match(SEPARATOR);
				setState(537);
				((AnswerContext)_localctx).text_answer = option();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(538);
				match(ANSWER);
				setState(539);
				match(SEPARATOR);
				setState(540);
				match(LIST_START);
				setState(541);
				((AnswerContext)_localctx).list_answer = option();
				setState(542);
				match(LIST_END);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharsContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(questionnairesParser.WORD, 0); }
		public TerminalNode CHAR() { return getToken(questionnairesParser.CHAR, 0); }
		public TerminalNode INT() { return getToken(questionnairesParser.INT, 0); }
		public TerminalNode SPECIAL() { return getToken(questionnairesParser.SPECIAL, 0); }
		public TerminalNode LINE() { return getToken(questionnairesParser.LINE, 0); }
		public TerminalNode LIST_START() { return getToken(questionnairesParser.LIST_START, 0); }
		public TerminalNode LIST_END() { return getToken(questionnairesParser.LIST_END, 0); }
		public CharsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chars; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterChars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitChars(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitChars(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharsContext chars() throws RecognitionException {
		CharsContext _localctx = new CharsContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_chars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(546);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CHAR) | (1L << SPECIAL) | (1L << INT) | (1L << WORD) | (1L << LIST_START) | (1L << LIST_END) | (1L << LINE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TextContext extends ParserRuleContext {
		public List<CharsContext> chars() {
			return getRuleContexts(CharsContext.class);
		}
		public CharsContext chars(int i) {
			return getRuleContext(CharsContext.class,i);
		}
		public TextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_text; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).enterText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnairesListener ) ((questionnairesListener)listener).exitText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnairesVisitor ) return ((questionnairesVisitor<? extends T>)visitor).visitText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TextContext text() throws RecognitionException {
		TextContext _localctx = new TextContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_text);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(548);
			match(T__0);
			setState(549);
			chars();
			setState(553);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CHAR) | (1L << SPECIAL) | (1L << INT) | (1L << WORD) | (1L << LIST_START) | (1L << LIST_END) | (1L << LINE))) != 0)) {
				{
				{
				setState(550);
				chars();
				}
				}
				setState(555);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(556);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001?\u022f\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0001"+
		"\u0000\u0001\u0000\u0003\u0000S\b\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003"+
		"\u0001]\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u008c"+
		"\b\u0006\n\u0006\f\u0006\u008f\t\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0005\u0007\u009b\b\u0007\n\u0007\f\u0007\u009e\t\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0003\b\u00bd\b\b\u0003\b\u00bf\b\b\u0001\b"+
		"\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\r\u0003\r\u00d4\b\r\u0004\r\u00d6\b\r\u000b\r\f\r\u00d7\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00df\b\u000e"+
		"\u0004\u000e\u00e1\b\u000e\u000b\u000e\f\u000e\u00e2\u0001\u000e\u0001"+
		"\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u00ef\b\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0003\u0010\u0100\b\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0004"+
		"\u0010\u010a\b\u0010\u000b\u0010\f\u0010\u010b\u0001\u0010\u0001\u0010"+
		"\u0003\u0010\u0110\b\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014"+
		"\u0127\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015"+
		"\u0133\b\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016"+
		"\u013f\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0003\u0019\u014c\b\u0019\u0004\u0019\u014e\b\u0019\u000b\u0019\f\u0019"+
		"\u014f\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0003"+
		"\u001a\u0157\b\u001a\u0004\u001a\u0159\b\u001a\u000b\u001a\f\u001a\u015a"+
		"\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u0167\b\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0003\u001c\u0176\b\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0005 \u018c\b \n \f \u018f"+
		"\t \u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0001!\u0001!\u0001!\u0001!\u0003!\u01a9\b!\u0001!\u0001!\u0001!\u0001"+
		"!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0001!\u0001!\u0003!\u01c5\b!\u0001\"\u0001\"\u0001\"\u0001\"\u0001"+
		"#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001"+
		"#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001"+
		"#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0003#\u01e6\b#\u0001#\u0003"+
		"#\u01e9\b#\u0001$\u0001$\u0001$\u0001$\u0001$\u0003$\u01f0\b$\u0001$\u0001"+
		"$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001"+
		"$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001"+
		"$\u0001$\u0001$\u0001$\u0001$\u0003$\u020c\b$\u0001$\u0001$\u0001$\u0003"+
		"$\u0211\b$\u0003$\u0213\b$\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001"+
		"%\u0001%\u0001%\u0001%\u0001%\u0001%\u0003%\u0221\b%\u0001&\u0001&\u0001"+
		"\'\u0001\'\u0001\'\u0005\'\u0228\b\'\n\'\f\'\u022b\t\'\u0001\'\u0001\'"+
		"\u0001\'\u0000\u0000(\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLN\u0000\u0004"+
		"\u0001\u0000\u000b\u000e\u0001\u0000\'(\u0001\u0000)+\u0003\u0000259:"+
		">>\u0232\u0000R\u0001\u0000\u0000\u0000\u0002T\u0001\u0000\u0000\u0000"+
		"\u0004i\u0001\u0000\u0000\u0000\u0006y\u0001\u0000\u0000\u0000\b}\u0001"+
		"\u0000\u0000\u0000\n\u0081\u0001\u0000\u0000\u0000\f\u0085\u0001\u0000"+
		"\u0000\u0000\u000e\u0093\u0001\u0000\u0000\u0000\u0010\u00a3\u0001\u0000"+
		"\u0000\u0000\u0012\u00c2\u0001\u0000\u0000\u0000\u0014\u00c6\u0001\u0000"+
		"\u0000\u0000\u0016\u00ca\u0001\u0000\u0000\u0000\u0018\u00cc\u0001\u0000"+
		"\u0000\u0000\u001a\u00d0\u0001\u0000\u0000\u0000\u001c\u00db\u0001\u0000"+
		"\u0000\u0000\u001e\u00e6\u0001\u0000\u0000\u0000 \u00f7\u0001\u0000\u0000"+
		"\u0000\"\u0113\u0001\u0000\u0000\u0000$\u0117\u0001\u0000\u0000\u0000"+
		"&\u011b\u0001\u0000\u0000\u0000(\u011f\u0001\u0000\u0000\u0000*\u0128"+
		"\u0001\u0000\u0000\u0000,\u013e\u0001\u0000\u0000\u0000.\u0140\u0001\u0000"+
		"\u0000\u00000\u0144\u0001\u0000\u0000\u00002\u0148\u0001\u0000\u0000\u0000"+
		"4\u0153\u0001\u0000\u0000\u00006\u015e\u0001\u0000\u0000\u00008\u016d"+
		"\u0001\u0000\u0000\u0000:\u017c\u0001\u0000\u0000\u0000<\u0180\u0001\u0000"+
		"\u0000\u0000>\u0184\u0001\u0000\u0000\u0000@\u0188\u0001\u0000\u0000\u0000"+
		"B\u0190\u0001\u0000\u0000\u0000D\u01c6\u0001\u0000\u0000\u0000F\u01ca"+
		"\u0001\u0000\u0000\u0000H\u01ea\u0001\u0000\u0000\u0000J\u0220\u0001\u0000"+
		"\u0000\u0000L\u0222\u0001\u0000\u0000\u0000N\u0224\u0001\u0000\u0000\u0000"+
		"PS\u0003\u0004\u0002\u0000QS\u0003\u0002\u0001\u0000RP\u0001\u0000\u0000"+
		"\u0000RQ\u0001\u0000\u0000\u0000S\u0001\u0001\u0000\u0000\u0000TU\u0005"+
		";\u0000\u0000UV\u0003\u0006\u0003\u0000VW\u0005>\u0000\u0000WX\u0003\n"+
		"\u0005\u0000X\\\u0005>\u0000\u0000YZ\u0003\f\u0006\u0000Z[\u0005>\u0000"+
		"\u0000[]\u0001\u0000\u0000\u0000\\Y\u0001\u0000\u0000\u0000\\]\u0001\u0000"+
		"\u0000\u0000]^\u0001\u0000\u0000\u0000^_\u0003\u0012\t\u0000_`\u0005>"+
		"\u0000\u0000`a\u0003\u0014\n\u0000ab\u0005>\u0000\u0000bc\u0003\u0016"+
		"\u000b\u0000cd\u0005=\u0000\u0000de\u0003\u001a\r\u0000ef\u0005>\u0000"+
		"\u0000fg\u0003\u0018\f\u0000gh\u0005<\u0000\u0000h\u0003\u0001\u0000\u0000"+
		"\u0000ij\u0005;\u0000\u0000jk\u0003\b\u0004\u0000kl\u0005>\u0000\u0000"+
		"lm\u0003\n\u0005\u0000mn\u0005>\u0000\u0000no\u0003\u0012\t\u0000op\u0005"+
		">\u0000\u0000pq\u0003\u0014\n\u0000qr\u0005>\u0000\u0000rs\u0003\u0016"+
		"\u000b\u0000st\u0005=\u0000\u0000tu\u0003\u001c\u000e\u0000uv\u0005>\u0000"+
		"\u0000vw\u0003\u0018\f\u0000wx\u0005<\u0000\u0000x\u0005\u0001\u0000\u0000"+
		"\u0000yz\u0005\u0002\u0000\u0000z{\u0005=\u0000\u0000{|\u0005\t\u0000"+
		"\u0000|\u0007\u0001\u0000\u0000\u0000}~\u0005\u0002\u0000\u0000~\u007f"+
		"\u0005=\u0000\u0000\u007f\u0080\u0005\n\u0000\u0000\u0080\t\u0001\u0000"+
		"\u0000\u0000\u0081\u0082\u0005\u0003\u0000\u0000\u0082\u0083\u0005=\u0000"+
		"\u0000\u0083\u0084\u00056\u0000\u0000\u0084\u000b\u0001\u0000\u0000\u0000"+
		"\u0085\u0086\u0005\u0004\u0000\u0000\u0086\u0087\u0005=\u0000\u0000\u0087"+
		"\u008d\u00059\u0000\u0000\u0088\u0089\u0003\u000e\u0007\u0000\u0089\u008a"+
		"\u0005>\u0000\u0000\u008a\u008c\u0001\u0000\u0000\u0000\u008b\u0088\u0001"+
		"\u0000\u0000\u0000\u008c\u008f\u0001\u0000\u0000\u0000\u008d\u008b\u0001"+
		"\u0000\u0000\u0000\u008d\u008e\u0001\u0000\u0000\u0000\u008e\u0090\u0001"+
		"\u0000\u0000\u0000\u008f\u008d\u0001\u0000\u0000\u0000\u0090\u0091\u0003"+
		"\u000e\u0007\u0000\u0091\u0092\u0005:\u0000\u0000\u0092\r\u0001\u0000"+
		"\u0000\u0000\u0093\u0094\u0005;\u0000\u0000\u0094\u0095\u0005\u0010\u0000"+
		"\u0000\u0095\u0096\u0005=\u0000\u0000\u0096\u009c\u00059\u0000\u0000\u0097"+
		"\u0098\u0003\u0010\b\u0000\u0098\u0099\u0005>\u0000\u0000\u0099\u009b"+
		"\u0001\u0000\u0000\u0000\u009a\u0097\u0001\u0000\u0000\u0000\u009b\u009e"+
		"\u0001\u0000\u0000\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009c\u009d"+
		"\u0001\u0000\u0000\u0000\u009d\u009f\u0001\u0000\u0000\u0000\u009e\u009c"+
		"\u0001\u0000\u0000\u0000\u009f\u00a0\u0003\u0010\b\u0000\u00a0\u00a1\u0005"+
		":\u0000\u0000\u00a1\u00a2\u0005<\u0000\u0000\u00a2\u000f\u0001\u0000\u0000"+
		"\u0000\u00a3\u00be\u0005;\u0000\u0000\u00a4\u00a5\u0005\u0011\u0000\u0000"+
		"\u00a5\u00a6\u0005=\u0000\u0000\u00a6\u00a7\u0007\u0000\u0000\u0000\u00a7"+
		"\u00a8\u0005>\u0000\u0000\u00a8\u00a9\u00050\u0000\u0000\u00a9\u00aa\u0005"+
		"=\u0000\u0000\u00aa\u00bf\u0003N\'\u0000\u00ab\u00ac\u0005\u0011\u0000"+
		"\u0000\u00ac\u00ad\u0005=\u0000\u0000\u00ad\u00ae\u0005\u000f\u0000\u0000"+
		"\u00ae\u00bc\u0005>\u0000\u0000\u00af\u00b0\u0005\u0012\u0000\u0000\u00b0"+
		"\u00b1\u0005=\u0000\u0000\u00b1\u00b2\u00054\u0000\u0000\u00b2\u00b3\u0005"+
		">\u0000\u0000\u00b3\u00b4\u0005\u0013\u0000\u0000\u00b4\u00b5\u0005=\u0000"+
		"\u0000\u00b5\u00bd\u00054\u0000\u0000\u00b6\u00b7\u0005\u0014\u0000\u0000"+
		"\u00b7\u00b8\u0005=\u0000\u0000\u00b8\u00bd\u00054\u0000\u0000\u00b9\u00ba"+
		"\u0005\u0015\u0000\u0000\u00ba\u00bb\u0005=\u0000\u0000\u00bb\u00bd\u0005"+
		"4\u0000\u0000\u00bc\u00af\u0001\u0000\u0000\u0000\u00bc\u00b6\u0001\u0000"+
		"\u0000\u0000\u00bc\u00b9\u0001\u0000\u0000\u0000\u00bd\u00bf\u0001\u0000"+
		"\u0000\u0000\u00be\u00a4\u0001\u0000\u0000\u0000\u00be\u00ab\u0001\u0000"+
		"\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0\u00c1\u0005<\u0000"+
		"\u0000\u00c1\u0011\u0001\u0000\u0000\u0000\u00c2\u00c3\u0005\u0005\u0000"+
		"\u0000\u00c3\u00c4\u0005=\u0000\u0000\u00c4\u00c5\u0003N\'\u0000\u00c5"+
		"\u0013\u0001\u0000\u0000\u0000\u00c6\u00c7\u0005\u0006\u0000\u0000\u00c7"+
		"\u00c8\u0005=\u0000\u0000\u00c8\u00c9\u0003N\'\u0000\u00c9\u0015\u0001"+
		"\u0000\u0000\u0000\u00ca\u00cb\u0005\u0007\u0000\u0000\u00cb\u0017\u0001"+
		"\u0000\u0000\u0000\u00cc\u00cd\u0005\b\u0000\u0000\u00cd\u00ce\u0005="+
		"\u0000\u0000\u00ce\u00cf\u0003N\'\u0000\u00cf\u0019\u0001\u0000\u0000"+
		"\u0000\u00d0\u00d5\u00059\u0000\u0000\u00d1\u00d3\u0003\u001e\u000f\u0000"+
		"\u00d2\u00d4\u0005>\u0000\u0000\u00d3\u00d2\u0001\u0000\u0000\u0000\u00d3"+
		"\u00d4\u0001\u0000\u0000\u0000\u00d4\u00d6\u0001\u0000\u0000\u0000\u00d5"+
		"\u00d1\u0001\u0000\u0000\u0000\u00d6\u00d7\u0001\u0000\u0000\u0000\u00d7"+
		"\u00d5\u0001\u0000\u0000\u0000\u00d7\u00d8\u0001\u0000\u0000\u0000\u00d8"+
		"\u00d9\u0001\u0000\u0000\u0000\u00d9\u00da\u0005:\u0000\u0000\u00da\u001b"+
		"\u0001\u0000\u0000\u0000\u00db\u00e0\u00059\u0000\u0000\u00dc\u00de\u0003"+
		" \u0010\u0000\u00dd\u00df\u0005>\u0000\u0000\u00de\u00dd\u0001\u0000\u0000"+
		"\u0000\u00de\u00df\u0001\u0000\u0000\u0000\u00df\u00e1\u0001\u0000\u0000"+
		"\u0000\u00e0\u00dc\u0001\u0000\u0000\u0000\u00e1\u00e2\u0001\u0000\u0000"+
		"\u0000\u00e2\u00e0\u0001\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000"+
		"\u0000\u00e3\u00e4\u0001\u0000\u0000\u0000\u00e4\u00e5\u0005:\u0000\u0000"+
		"\u00e5\u001d\u0001\u0000\u0000\u0000\u00e6\u00e7\u0005;\u0000\u0000\u00e7"+
		"\u00e8\u0003\"\u0011\u0000\u00e8\u00e9\u0005>\u0000\u0000\u00e9\u00ea"+
		"\u0003$\u0012\u0000\u00ea\u00ee\u0005>\u0000\u0000\u00eb\u00ec\u0003&"+
		"\u0013\u0000\u00ec\u00ed\u0005>\u0000\u0000\u00ed\u00ef\u0001\u0000\u0000"+
		"\u0000\u00ee\u00eb\u0001\u0000\u0000\u0000\u00ee\u00ef\u0001\u0000\u0000"+
		"\u0000\u00ef\u00f0\u0001\u0000\u0000\u0000\u00f0\u00f1\u0003(\u0014\u0000"+
		"\u00f1\u00f2\u0005>\u0000\u0000\u00f2\u00f3\u0003,\u0016\u0000\u00f3\u00f4"+
		"\u0005>\u0000\u0000\u00f4\u00f5\u0003.\u0017\u0000\u00f5\u00f6\u0005<"+
		"\u0000\u0000\u00f6\u001f\u0001\u0000\u0000\u0000\u00f7\u00f8\u0005;\u0000"+
		"\u0000\u00f8\u00f9\u0003\"\u0011\u0000\u00f9\u00fa\u0005>\u0000\u0000"+
		"\u00fa\u00fb\u0003$\u0012\u0000\u00fb\u00ff\u0005>\u0000\u0000\u00fc\u00fd"+
		"\u0003&\u0013\u0000\u00fd\u00fe\u0005>\u0000\u0000\u00fe\u0100\u0001\u0000"+
		"\u0000\u0000\u00ff\u00fc\u0001\u0000\u0000\u0000\u00ff\u0100\u0001\u0000"+
		"\u0000\u0000\u0100\u0101\u0001\u0000\u0000\u0000\u0101\u0102\u0003(\u0014"+
		"\u0000\u0102\u0103\u0005>\u0000\u0000\u0103\u0104\u0003,\u0016\u0000\u0104"+
		"\u010f\u0005>\u0000\u0000\u0105\u0110\u00030\u0018\u0000\u0106\u0107\u0003"+
		"0\u0018\u0000\u0107\u0108\u0005>\u0000\u0000\u0108\u010a\u0001\u0000\u0000"+
		"\u0000\u0109\u0106\u0001\u0000\u0000\u0000\u010a\u010b\u0001\u0000\u0000"+
		"\u0000\u010b\u0109\u0001\u0000\u0000\u0000\u010b\u010c\u0001\u0000\u0000"+
		"\u0000\u010c\u010d\u0001\u0000\u0000\u0000\u010d\u010e\u00030\u0018\u0000"+
		"\u010e\u0110\u0001\u0000\u0000\u0000\u010f\u0105\u0001\u0000\u0000\u0000"+
		"\u010f\u0109\u0001\u0000\u0000\u0000\u0110\u0111\u0001\u0000\u0000\u0000"+
		"\u0111\u0112\u0005<\u0000\u0000\u0112!\u0001\u0000\u0000\u0000\u0113\u0114"+
		"\u0005\u0016\u0000\u0000\u0114\u0115\u0005=\u0000\u0000\u0115\u0116\u0005"+
		"4\u0000\u0000\u0116#\u0001\u0000\u0000\u0000\u0117\u0118\u0005\u0017\u0000"+
		"\u0000\u0118\u0119\u0005=\u0000\u0000\u0119\u011a\u0003N\'\u0000\u011a"+
		"%\u0001\u0000\u0000\u0000\u011b\u011c\u0005\u0018\u0000\u0000\u011c\u011d"+
		"\u0005=\u0000\u0000\u011d\u011e\u0003N\'\u0000\u011e\'\u0001\u0000\u0000"+
		"\u0000\u011f\u0120\u0005\u0019\u0000\u0000\u0120\u0126\u0005=\u0000\u0000"+
		"\u0121\u0127\u0005-\u0000\u0000\u0122\u0127\u0005.\u0000\u0000\u0123\u0124"+
		"\u0005/\u0000\u0000\u0124\u0125\u0005>\u0000\u0000\u0125\u0127\u0003*"+
		"\u0015\u0000\u0126\u0121\u0001\u0000\u0000\u0000\u0126\u0122\u0001\u0000"+
		"\u0000\u0000\u0126\u0123\u0001\u0000\u0000\u0000\u0127)\u0001\u0000\u0000"+
		"\u0000\u0128\u0129\u00050\u0000\u0000\u0129\u012a\u0005=\u0000\u0000\u012a"+
		"\u012b\u0005;\u0000\u0000\u012b\u012c\u0003\"\u0011\u0000\u012c\u012d"+
		"\u0005>\u0000\u0000\u012d\u0132\u0003:\u001d\u0000\u012e\u012f\u0005>"+
		"\u0000\u0000\u012f\u0130\u00051\u0000\u0000\u0130\u0131\u0005=\u0000\u0000"+
		"\u0131\u0133\u0003N\'\u0000\u0132\u012e\u0001\u0000\u0000\u0000\u0132"+
		"\u0133\u0001\u0000\u0000\u0000\u0133\u0134\u0001\u0000\u0000\u0000\u0134"+
		"\u0135\u0005<\u0000\u0000\u0135+\u0001\u0000\u0000\u0000\u0136\u0137\u0005"+
		"\u001a\u0000\u0000\u0137\u0138\u0005=\u0000\u0000\u0138\u0139\u00058\u0000"+
		"\u0000\u0139\u013a\u0005>\u0000\u0000\u013a\u013f\u0003*\u0015\u0000\u013b"+
		"\u013c\u0005\u001a\u0000\u0000\u013c\u013d\u0005=\u0000\u0000\u013d\u013f"+
		"\u00057\u0000\u0000\u013e\u0136\u0001\u0000\u0000\u0000\u013e\u013b\u0001"+
		"\u0000\u0000\u0000\u013f-\u0001\u0000\u0000\u0000\u0140\u0141\u0005\u001b"+
		"\u0000\u0000\u0141\u0142\u0005=\u0000\u0000\u0142\u0143\u00032\u0019\u0000"+
		"\u0143/\u0001\u0000\u0000\u0000\u0144\u0145\u0005\u001b\u0000\u0000\u0145"+
		"\u0146\u0005=\u0000\u0000\u0146\u0147\u00034\u001a\u0000\u01471\u0001"+
		"\u0000\u0000\u0000\u0148\u014d\u00059\u0000\u0000\u0149\u014b\u00036\u001b"+
		"\u0000\u014a\u014c\u0005>\u0000\u0000\u014b\u014a\u0001\u0000\u0000\u0000"+
		"\u014b\u014c\u0001\u0000\u0000\u0000\u014c\u014e\u0001\u0000\u0000\u0000"+
		"\u014d\u0149\u0001\u0000\u0000\u0000\u014e\u014f\u0001\u0000\u0000\u0000"+
		"\u014f\u014d\u0001\u0000\u0000\u0000\u014f\u0150\u0001\u0000\u0000\u0000"+
		"\u0150\u0151\u0001\u0000\u0000\u0000\u0151\u0152\u0005:\u0000\u0000\u0152"+
		"3\u0001\u0000\u0000\u0000\u0153\u0158\u00059\u0000\u0000\u0154\u0156\u0003"+
		"8\u001c\u0000\u0155\u0157\u0005>\u0000\u0000\u0156\u0155\u0001\u0000\u0000"+
		"\u0000\u0156\u0157\u0001\u0000\u0000\u0000\u0157\u0159\u0001\u0000\u0000"+
		"\u0000\u0158\u0154\u0001\u0000\u0000\u0000\u0159\u015a\u0001\u0000\u0000"+
		"\u0000\u015a\u0158\u0001\u0000\u0000\u0000\u015a\u015b\u0001\u0000\u0000"+
		"\u0000\u015b\u015c\u0001\u0000\u0000\u0000\u015c\u015d\u0005:\u0000\u0000"+
		"\u015d5\u0001\u0000\u0000\u0000\u015e\u015f\u0005;\u0000\u0000\u015f\u0160"+
		"\u0003:\u001d\u0000\u0160\u0161\u0005>\u0000\u0000\u0161\u0162\u0003<"+
		"\u001e\u0000\u0162\u0166\u0005>\u0000\u0000\u0163\u0164\u0003D\"\u0000"+
		"\u0164\u0165\u0005>\u0000\u0000\u0165\u0167\u0001\u0000\u0000\u0000\u0166"+
		"\u0163\u0001\u0000\u0000\u0000\u0166\u0167\u0001\u0000\u0000\u0000\u0167"+
		"\u0168\u0001\u0000\u0000\u0000\u0168\u0169\u0003B!\u0000\u0169\u016a\u0005"+
		">\u0000\u0000\u016a\u016b\u0003F#\u0000\u016b\u016c\u0005<\u0000\u0000"+
		"\u016c7\u0001\u0000\u0000\u0000\u016d\u016e\u0005;\u0000\u0000\u016e\u016f"+
		"\u0003:\u001d\u0000\u016f\u0170\u0005>\u0000\u0000\u0170\u0171\u0003<"+
		"\u001e\u0000\u0171\u0175\u0005>\u0000\u0000\u0172\u0173\u0003D\"\u0000"+
		"\u0173\u0174\u0005>\u0000\u0000\u0174\u0176\u0001\u0000\u0000\u0000\u0175"+
		"\u0172\u0001\u0000\u0000\u0000\u0175\u0176\u0001\u0000\u0000\u0000\u0176"+
		"\u0177\u0001\u0000\u0000\u0000\u0177\u0178\u0003B!\u0000\u0178\u0179\u0005"+
		">\u0000\u0000\u0179\u017a\u0003H$\u0000\u017a\u017b\u0005<\u0000\u0000"+
		"\u017b9\u0001\u0000\u0000\u0000\u017c\u017d\u0005\u001c\u0000\u0000\u017d"+
		"\u017e\u0005=\u0000\u0000\u017e\u017f\u00054\u0000\u0000\u017f;\u0001"+
		"\u0000\u0000\u0000\u0180\u0181\u0005\u001d\u0000\u0000\u0181\u0182\u0005"+
		"=\u0000\u0000\u0182\u0183\u0003N\'\u0000\u0183=\u0001\u0000\u0000\u0000"+
		"\u0184\u0185\u00059\u0000\u0000\u0185\u0186\u0003@ \u0000\u0186\u0187"+
		"\u0005:\u0000\u0000\u0187?\u0001\u0000\u0000\u0000\u0188\u018d\u0003N"+
		"\'\u0000\u0189\u018a\u0005>\u0000\u0000\u018a\u018c\u0003N\'\u0000\u018b"+
		"\u0189\u0001\u0000\u0000\u0000\u018c\u018f\u0001\u0000\u0000\u0000\u018d"+
		"\u018b\u0001\u0000\u0000\u0000\u018d\u018e\u0001\u0000\u0000\u0000\u018e"+
		"A\u0001\u0000\u0000\u0000\u018f\u018d\u0001\u0000\u0000\u0000\u0190\u0191"+
		"\u0005\u001f\u0000\u0000\u0191\u01c4\u0005=\u0000\u0000\u0192\u0193\u0007"+
		"\u0001\u0000\u0000\u0193\u0194\u0005>\u0000\u0000\u0194\u0195\u0005 \u0000"+
		"\u0000\u0195\u0196\u0005=\u0000\u0000\u0196\u0197\u0005;\u0000\u0000\u0197"+
		"\u0198\u0005!\u0000\u0000\u0198\u0199\u0005=\u0000\u0000\u0199\u019a\u0003"+
		">\u001f\u0000\u019a\u019b\u0005<\u0000\u0000\u019b\u01c5\u0001\u0000\u0000"+
		"\u0000\u019c\u019d\u0007\u0002\u0000\u0000\u019d\u019e\u0005>\u0000\u0000"+
		"\u019e\u019f\u0005 \u0000\u0000\u019f\u01a0\u0005=\u0000\u0000\u01a0\u01a1"+
		"\u0005;\u0000\u0000\u01a1\u01a2\u0005!\u0000\u0000\u01a2\u01a3\u0005="+
		"\u0000\u0000\u01a3\u01a8\u0003>\u001f\u0000\u01a4\u01a5\u0005>\u0000\u0000"+
		"\u01a5\u01a6\u0005\"\u0000\u0000\u01a6\u01a7\u0005=\u0000\u0000\u01a7"+
		"\u01a9\u00054\u0000\u0000\u01a8\u01a4\u0001\u0000\u0000\u0000\u01a8\u01a9"+
		"\u0001\u0000\u0000\u0000\u01a9\u01aa\u0001\u0000\u0000\u0000\u01aa\u01ab"+
		"\u0005<\u0000\u0000\u01ab\u01c5\u0001\u0000\u0000\u0000\u01ac\u01ad\u0005"+
		"%\u0000\u0000\u01ad\u01ae\u0005>\u0000\u0000\u01ae\u01af\u0005 \u0000"+
		"\u0000\u01af\u01b0\u0005=\u0000\u0000\u01b0\u01b1\u0005;\u0000\u0000\u01b1"+
		"\u01b2\u0005#\u0000\u0000\u01b2\u01b3\u0005=\u0000\u0000\u01b3\u01b4\u0005"+
		"4\u0000\u0000\u01b4\u01c5\u0005<\u0000\u0000\u01b5\u01c5\u0005&\u0000"+
		"\u0000\u01b6\u01b7\u0005,\u0000\u0000\u01b7\u01b8\u0005>\u0000\u0000\u01b8"+
		"\u01b9\u0005 \u0000\u0000\u01b9\u01ba\u0005=\u0000\u0000\u01ba\u01bb\u0005"+
		";\u0000\u0000\u01bb\u01bc\u0005!\u0000\u0000\u01bc\u01bd\u0005=\u0000"+
		"\u0000\u01bd\u01be\u0003>\u001f\u0000\u01be\u01bf\u0005>\u0000\u0000\u01bf"+
		"\u01c0\u0005$\u0000\u0000\u01c0\u01c1\u0005=\u0000\u0000\u01c1\u01c2\u0003"+
		">\u001f\u0000\u01c2\u01c3\u0005<\u0000\u0000\u01c3\u01c5\u0001\u0000\u0000"+
		"\u0000\u01c4\u0192\u0001\u0000\u0000\u0000\u01c4\u019c\u0001\u0000\u0000"+
		"\u0000\u01c4\u01ac\u0001\u0000\u0000\u0000\u01c4\u01b5\u0001\u0000\u0000"+
		"\u0000\u01c4\u01b6\u0001\u0000\u0000\u0000\u01c5C\u0001\u0000\u0000\u0000"+
		"\u01c6\u01c7\u0005\u001e\u0000\u0000\u01c7\u01c8\u0005=\u0000\u0000\u01c8"+
		"\u01c9\u0003N\'\u0000\u01c9E\u0001\u0000\u0000\u0000\u01ca\u01cb\u0005"+
		"\u0019\u0000\u0000\u01cb\u01e8\u0005=\u0000\u0000\u01cc\u01e9\u0005-\u0000"+
		"\u0000\u01cd\u01e9\u0005.\u0000\u0000\u01ce\u01cf\u0005/\u0000\u0000\u01cf"+
		"\u01d0\u0005>\u0000\u0000\u01d0\u01d1\u00050\u0000\u0000\u01d1\u01d2\u0005"+
		"=\u0000\u0000\u01d2\u01d3\u0005;\u0000\u0000\u01d3\u01d4\u0005\u0016\u0000"+
		"\u0000\u01d4\u01d5\u0005=\u0000\u0000\u01d5\u01d6\u00054\u0000\u0000\u01d6"+
		"\u01d7\u0005>\u0000\u0000\u01d7\u01d8\u0005\u001c\u0000\u0000\u01d8\u01d9"+
		"\u0005=\u0000\u0000\u01d9\u01e5\u00054\u0000\u0000\u01da\u01db\u0005>"+
		"\u0000\u0000\u01db\u01dc\u00051\u0000\u0000\u01dc\u01dd\u0005=\u0000\u0000"+
		"\u01dd\u01de\u00059\u0000\u0000\u01de\u01df\u0003@ \u0000\u01df\u01e0"+
		"\u0005:\u0000\u0000\u01e0\u01e6\u0001\u0000\u0000\u0000\u01e1\u01e2\u0005"+
		">\u0000\u0000\u01e2\u01e3\u00051\u0000\u0000\u01e3\u01e4\u0005=\u0000"+
		"\u0000\u01e4\u01e6\u0003N\'\u0000\u01e5\u01da\u0001\u0000\u0000\u0000"+
		"\u01e5\u01e1\u0001\u0000\u0000\u0000\u01e5\u01e6\u0001\u0000\u0000\u0000"+
		"\u01e6\u01e7\u0001\u0000\u0000\u0000\u01e7\u01e9\u0005<\u0000\u0000\u01e8"+
		"\u01cc\u0001\u0000\u0000\u0000\u01e8\u01cd\u0001\u0000\u0000\u0000\u01e8"+
		"\u01ce\u0001\u0000\u0000\u0000\u01e9G\u0001\u0000\u0000\u0000\u01ea\u01eb"+
		"\u0005\u0019\u0000\u0000\u01eb\u0212\u0005=\u0000\u0000\u01ec\u01ef\u0005"+
		"-\u0000\u0000\u01ed\u01ee\u0005>\u0000\u0000\u01ee\u01f0\u0003J%\u0000"+
		"\u01ef\u01ed\u0001\u0000\u0000\u0000\u01ef\u01f0\u0001\u0000\u0000\u0000"+
		"\u01f0\u0213\u0001\u0000\u0000\u0000\u01f1\u01f2\u0005.\u0000\u0000\u01f2"+
		"\u01f3\u0005>\u0000\u0000\u01f3\u0213\u0003J%\u0000\u01f4\u01f5\u0005"+
		"/\u0000\u0000\u01f5\u01f6\u0005>\u0000\u0000\u01f6\u01f7\u00050\u0000"+
		"\u0000\u01f7\u01f8\u0005=\u0000\u0000\u01f8\u01f9\u0005;\u0000\u0000\u01f9"+
		"\u01fa\u0005\u0016\u0000\u0000\u01fa\u01fb\u0005=\u0000\u0000\u01fb\u01fc"+
		"\u00054\u0000\u0000\u01fc\u01fd\u0005>\u0000\u0000\u01fd\u01fe\u0005\u001c"+
		"\u0000\u0000\u01fe\u01ff\u0005=\u0000\u0000\u01ff\u020b\u00054\u0000\u0000"+
		"\u0200\u0201\u0005>\u0000\u0000\u0201\u0202\u00051\u0000\u0000\u0202\u0203"+
		"\u0005=\u0000\u0000\u0203\u0204\u00059\u0000\u0000\u0204\u0205\u0003@"+
		" \u0000\u0205\u0206\u0005:\u0000\u0000\u0206\u020c\u0001\u0000\u0000\u0000"+
		"\u0207\u0208\u0005>\u0000\u0000\u0208\u0209\u00051\u0000\u0000\u0209\u020a"+
		"\u0005=\u0000\u0000\u020a\u020c\u0003N\'\u0000\u020b\u0200\u0001\u0000"+
		"\u0000\u0000\u020b\u0207\u0001\u0000\u0000\u0000\u020b\u020c\u0001\u0000"+
		"\u0000\u0000\u020c\u020d\u0001\u0000\u0000\u0000\u020d\u0210\u0005<\u0000"+
		"\u0000\u020e\u020f\u0005>\u0000\u0000\u020f\u0211\u0003J%\u0000\u0210"+
		"\u020e\u0001\u0000\u0000\u0000\u0210\u0211\u0001\u0000\u0000\u0000\u0211"+
		"\u0213\u0001\u0000\u0000\u0000\u0212\u01ec\u0001\u0000\u0000\u0000\u0212"+
		"\u01f1\u0001\u0000\u0000\u0000\u0212\u01f4\u0001\u0000\u0000\u0000\u0213"+
		"I\u0001\u0000\u0000\u0000\u0214\u0215\u00051\u0000\u0000\u0215\u0216\u0005"+
		"=\u0000\u0000\u0216\u0221\u00054\u0000\u0000\u0217\u0218\u00051\u0000"+
		"\u0000\u0218\u0219\u0005=\u0000\u0000\u0219\u0221\u0003@ \u0000\u021a"+
		"\u021b\u00051\u0000\u0000\u021b\u021c\u0005=\u0000\u0000\u021c\u021d\u0005"+
		"9\u0000\u0000\u021d\u021e\u0003@ \u0000\u021e\u021f\u0005:\u0000\u0000"+
		"\u021f\u0221\u0001\u0000\u0000\u0000\u0220\u0214\u0001\u0000\u0000\u0000"+
		"\u0220\u0217\u0001\u0000\u0000\u0000\u0220\u021a\u0001\u0000\u0000\u0000"+
		"\u0221K\u0001\u0000\u0000\u0000\u0222\u0223\u0007\u0003\u0000\u0000\u0223"+
		"M\u0001\u0000\u0000\u0000\u0224\u0225\u0005\u0001\u0000\u0000\u0225\u0229"+
		"\u0003L&\u0000\u0226\u0228\u0003L&\u0000\u0227\u0226\u0001\u0000\u0000"+
		"\u0000\u0228\u022b\u0001\u0000\u0000\u0000\u0229\u0227\u0001\u0000\u0000"+
		"\u0000\u0229\u022a\u0001\u0000\u0000\u0000\u022a\u022c\u0001\u0000\u0000"+
		"\u0000\u022b\u0229\u0001\u0000\u0000\u0000\u022c\u022d\u0005\u0001\u0000"+
		"\u0000\u022dO\u0001\u0000\u0000\u0000\"R\\\u008d\u009c\u00bc\u00be\u00d3"+
		"\u00d7\u00de\u00e2\u00ee\u00ff\u010b\u010f\u0126\u0132\u013e\u014b\u014f"+
		"\u0156\u015a\u0166\u0175\u018d\u01a8\u01c4\u01e5\u01e8\u01ef\u020b\u0210"+
		"\u0212\u0220\u0229";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}