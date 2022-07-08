package eapli.base.surveymanagement.domain;

import eapli.framework.validations.Preconditions;

import java.util.Objects;

public class QuestionID {
    private final String questionID;
    private final String sectionID;

    private QuestionID(String sectionID, String questionID) {
        Preconditions.noneNull(sectionID, questionID);

        this.sectionID = sectionID;
        this.questionID = questionID;
    }

    public static QuestionID valueOf (final String sectionID, final String questionID) {
        return new QuestionID(sectionID, questionID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionID that = (QuestionID) o;
        return questionID.equals(that.questionID) && sectionID.equals(that.sectionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionID, sectionID);
    }

    @Override
    public String toString() {
        return String.format("Section %s - Question %s\n", sectionID, questionID);
    }
}
