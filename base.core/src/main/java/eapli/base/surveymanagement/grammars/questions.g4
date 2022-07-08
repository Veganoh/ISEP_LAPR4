grammar questions;

import questionnaireTokens;

questions : LIST_START (question LINE?)+ LIST_END;
questions_answered : LIST_START (question_answered LINE?)+ LIST_END;

question :
            ELEM_START
              question_id LINE
              question_text LINE
              (question_instruction LINE)?
              question_type LINE
              obligatoriness
           ELEM_END;

question_answered :
            ELEM_START
              question_id LINE
              question_text LINE
              (question_instruction LINE)?
              question_type LINE
              obligatoriness_answered
           ELEM_END;

question_id:
    QUESTION_ID SEPARATOR quest_id=INT;

question_text:
    QUESTION SEPARATOR text;


question_options: LIST_START list=option LIST_END;

option: text (LINE text)*;

question_type:
    QUESTION_TYPE SEPARATOR (type=(SINGLE_CHOICE | SINGLE_CHOICE_INPUT) LINE
    info = QUESTION_INFO SEPARATOR ELEM_START
                    QUESTION_OPTIONS SEPARATOR question_list=question_options
                    ELEM_END |
                             type=(MULTIPLE_CHOICE | MULTIPLE_CHOICE_INPUT | SORTING_OPTION) LINE
    info = QUESTION_INFO SEPARATOR ELEM_START
                    QUESTION_OPTIONS SEPARATOR question_list=question_options (LINE
                    QUESTION_MAX_OPTIONS SEPARATOR max_options=INT)?
                    ELEM_END |
                             type=FREE_TEXT LINE
    info = QUESTION_INFO SEPARATOR ELEM_START
                    QUESTION_MAX_CHARS SEPARATOR max_chars=INT
                    ELEM_END |
                             type=NUMERIC
                             |
                             type=SCALING_OPTION LINE
    info = QUESTION_INFO SEPARATOR ELEM_START
                    QUESTION_OPTIONS SEPARATOR question_list=question_options LINE
                    QUESTION_SCALE SEPARATOR question_scale=question_options
                    ELEM_END);

question_instruction:
    QUESTION_INSTRUCTION SEPARATOR text;

obligatoriness:
    OBLIGATORINESS SEPARATOR (type=OPTIONAL
                            | type=MANDATORY
                            | type=CONDITIONAL LINE
                                CONDITION SEPARATOR ELEM_START
                                    SECTION_ID SEPARATOR answer_section_id=INT LINE
                                    QUESTION_ID SEPARATOR answer_question_id=INT (LINE
                                    ANSWER SEPARATOR LIST_START answer_list=option LIST_END
                                    | LINE ANSWER SEPARATOR answer_option = text)?
                                    ELEM_END);

obligatoriness_answered:
    OBLIGATORINESS SEPARATOR (type=OPTIONAL (LINE answer)?
                            | type=MANDATORY LINE answer
                            | type=CONDITIONAL LINE
                                CONDITION SEPARATOR ELEM_START
                                    SECTION_ID SEPARATOR answer_section_id=INT LINE
                                    QUESTION_ID SEPARATOR answer_question_id=INT (LINE
                                    ANSWER SEPARATOR LIST_START answer_list=option LIST_END
                                    | LINE ANSWER SEPARATOR answer_option = text)?
                                    ELEM_END (LINE answer)?);

answer:
    ANSWER SEPARATOR numeric_answer=INT   //numericAnswer
    | ANSWER SEPARATOR text_answer=option  //freeTextOrSingleChoiceAnswer
    | ANSWER SEPARATOR LIST_START list_answer=option LIST_END //multipleChoiceAnswer, ScallingChoiceAnswer or SortingChoiceAnswer
    ;




