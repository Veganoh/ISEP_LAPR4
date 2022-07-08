package eapli.base.surveymanagement.domain;

import eapli.framework.validations.Preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Question {
    private final QuestionID questionID;
    private final QuestionType questionType;
    private final List<String> options;

    public enum QuestionType {
        FREE_TEXT ("\"Free Text\""),
        NUMERIC ("\"Numeric\""),
        SINGLE_CHOICE ("\"Single Choice\""),
        SINGLE_CHOICE_INPUT ("\"Single Choice With Input Value\""),
        MULTIPLE_CHOICE ("\"Multiple Choice\""),
        MULTIPLE_CHOICE_INPUT ("\"Multiple Choice With Input Value\""),
        SORTING_OPTION ("\"Sorting Options\""),
        SCALING_OPTION ("\"Scaling Options\"");

        private String value;

        QuestionType(String value) {
            this.value = value;
        }

        static QuestionType get(String value) {
            for (QuestionType type : QuestionType.values())
                if (type.value.equals(value))
                    return type;
            return null;
        }

        String value() {
            return value;
        }
    }

    private Question(QuestionID questionID, QuestionType questionType, List<String> options) {
        Preconditions.noneNull(questionID, questionType, options);

        this.questionID = questionID;
        this.questionType = questionType;
        this.options = options;
    }

    public static Question valueOf(String sectionID, String questionID, String questionType, List<String> options) {
        return new Question(QuestionID.valueOf(sectionID, questionID), QuestionType.get(questionType), options);
    }

    public static Question valueOf(String sectionID, String questionID, String questionType) {
        return new Question(QuestionID.valueOf(sectionID, questionID), QuestionType.get(questionType), new ArrayList<>());
    }
    public QuestionID id() {return questionID;}

    public QuestionType questionType() {
        return questionType;
    }

    public List<String> options() {
        return options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return questionID.equals(question.questionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionID);
    }
}
