grammar questionnaires;

import sections;

questionnaires: questionnaires_answered | questionnaires_unanswered;

questionnaires_unanswered: ELEM_START
                    questionnaires_document LINE
                    questionnaires_id LINE
                    (questionnaires_target LINE)?
                    questionnaires_title LINE
                    questionnaires_welcome_message LINE
                    questionnaires_sections SEPARATOR sections LINE
                    questionnaires_ending
                 ELEM_END;

questionnaires_answered: ELEM_START
                            questionnaires_document_answer LINE
                            questionnaires_id LINE
                            questionnaires_title LINE
                            questionnaires_welcome_message LINE
                            questionnaires_sections SEPARATOR sections_answered LINE
                            questionnaires_ending
                         ELEM_END;

questionnaires_document:
    SURVEY_DOCUMENT SEPARATOR SURVEY_UNANSWERED;

questionnaires_document_answer:
    SURVEY_DOCUMENT SEPARATOR SURVEY_ANSWERED;

questionnaires_id:
    SURVEY_ID SEPARATOR id=ID;

questionnaires_target:
    SURVEY_TARGET SEPARATOR LIST_START (target_rule LINE)* target_rule  LIST_END;

target_rule:
    ELEM_START RULE_CONDITION SEPARATOR LIST_START (rule_condition LINE)* rule_condition LIST_END ELEM_END;

rule_condition:
    ELEM_START
            ((CONDITION_TYPE SEPARATOR type=(CONDITION_CATEGORY | CONDITION_PRODUCT | CONDITION_BRAND | CONDITION_GENDER) LINE
            CONDITION SEPARATOR target=text)
            | (CONDITION_TYPE SEPARATOR type=CONDITION_AGE LINE (
                    (MINIMUM_AGE SEPARATOR minimum=INT LINE
                    MAXIMUM_AGE SEPARATOR maximum=INT)
                    | (OLDER SEPARATOR older=INT)
                    | (YOUNGER SEPARATOR younger=INT))))
    ELEM_END;

questionnaires_title:
    SURVEY_TITLE SEPARATOR text;

questionnaires_welcome_message:
    SURVEY_WELCOME SEPARATOR text;

questionnaires_sections:
    SURVEY_SECTIONS;

questionnaires_ending:
    SURVEY_END SEPARATOR text;