grammar sections;

import questions;

sections: LIST_START (section LINE?)+  LIST_END;
sections_answered: LIST_START (section_answered LINE?)+  LIST_END;

section:
    ELEM_START
        section_id LINE
        section_title LINE
        (section_description LINE)?
        section_obligatoriness LINE
        section_repeatability LINE
        section_content
    ELEM_END
    ;

section_answered:
    ELEM_START
        section_id LINE
        section_title LINE
        (section_description LINE)?
        section_obligatoriness LINE
        section_repeatability LINE
        (section_content_answered | (section_content_answered LINE)+ section_content_answered)
    ELEM_END
    ;

section_id: SECTION_ID SEPARATOR sect_id=INT;

section_title: SECTION_TITLE SEPARATOR text;

section_description: SECTION_DESCRIPTION SEPARATOR text;

section_obligatoriness:
    OBLIGATORINESS SEPARATOR (type = OPTIONAL | type = MANDATORY
                             | type = CONDITIONAL LINE condition)
    ;

condition:
    CONDITION SEPARATOR
    ELEM_START
        section_id LINE
        question_id (LINE
        ANSWER SEPARATOR text)?
    ELEM_END
    ;

section_repeatability:
    REPEATABILITY SEPARATOR TRUE LINE condition
    | REPEATABILITY SEPARATOR FALSE;

section_content: CONTENT SEPARATOR questions;

section_content_answered: CONTENT SEPARATOR questions_answered;