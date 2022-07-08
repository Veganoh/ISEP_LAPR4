grammar questionnaireTokens;

SURVEY_DOCUMENT : '"Document"';
SURVEY_ID : '"ID"';
SURVEY_TARGET : '"Target Audience"';
SURVEY_TITLE : '"Title"';
SURVEY_WELCOME : '"WelcomeMessage"';
SURVEY_SECTIONS : '"Sections"';
SURVEY_END : '"FinalMessage"';
SURVEY_UNANSWERED : '"Questionnaire"';
SURVEY_ANSWERED : '"Answered Questionnaire"';

CONDITION_CATEGORY : '"Category"';
CONDITION_PRODUCT : '"Product"';
CONDITION_BRAND : '"Brand"';
CONDITION_GENDER : '"Gender"';
CONDITION_AGE : '"Age Group"';
RULE_CONDITION : '"Rule Conditions"';
CONDITION_TYPE : '"Condition Type"';
MINIMUM_AGE : '"Minimum Age"';
MAXIMUM_AGE : '"Maximum Age"';
OLDER : '"Older Than"';
YOUNGER : '"Younger Than"';

SECTION_ID : '"SectionID"';
SECTION_TITLE : '"SectionTitle"';
SECTION_DESCRIPTION : '"SectionDescription"';
OBLIGATORINESS : '"Obligatoriness"';
REPEATABILITY : '"Repeatability"';
CONTENT : '"Content"';

QUESTION_ID : '"QuestionID"';
QUESTION : '"Question"';
QUESTION_INSTRUCTION : '"Instruction"';
QUESTION_TYPE : '"Type"';
QUESTION_INFO : '"ExtraInfo"';
QUESTION_OPTIONS : '"Options"';
QUESTION_MAX_OPTIONS : '"MaximumOptions"';
QUESTION_MAX_CHARS : '"MaximumCharacters"';
QUESTION_SCALE : '"Scale"';

FREE_TEXT : '"Free Text"';
NUMERIC : '"Numeric"';
SINGLE_CHOICE : '"Single Choice"';
SINGLE_CHOICE_INPUT : '"Single Choice With Input Value"';
MULTIPLE_CHOICE : '"Multiple Choice"';
MULTIPLE_CHOICE_INPUT : '"Multiple Choice With Input Value"';
SORTING_OPTION : '"Sorting Options"';
SCALING_OPTION : '"Scaling Options"';

OPTIONAL : '"optional"';
MANDATORY : '"mandatory"';
CONDITIONAL : '"condition dependent"';

CONDITION : '"Condition"';
ANSWER : '"Answer"';

// Utils
CHAR : [a-zA-Z];
SPECIAL : [_@./#&+\-?] | '(' | ')'|'\\';
INT : [0-9]+;
WORD : ([a-zA-Z_@./#&+\-?] | '(' | ')')+;
chars : (WORD|CHAR|INT|SPECIAL|LINE|LIST_START|LIST_END);
text : '"' chars (chars)* '"';

ID: '"'CHAR CHAR CHAR CHAR [0-9] [0-9] '-' [0-9] [0-9]'"';

FALSE : '"'[Ff][Aa][Ll][Ss][Ee]'"';
TRUE : '"'[Tt][Rr][Uu][Ee]'"';

LIST_START : '[';
LIST_END : ']';
ELEM_START : '{';
ELEM_END : '}';
SEPARATOR : ':';
LINE: ',';

WS : [ \t\r\n]+ -> skip ;