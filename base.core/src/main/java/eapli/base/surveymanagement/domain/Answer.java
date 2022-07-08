package eapli.base.surveymanagement.domain;

import eapli.framework.validations.Preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Answer {
    private final QuestionID questionID;
    private final List<String> answer;

    private Answer(QuestionID questionID, List<String> answer) {
        Preconditions.noneNull(answer, questionID);
        Preconditions.nonEmpty(answer);

        this.questionID = questionID;
        this.answer = answer;
    }

    public static Answer valueOf(String sectionId, String questionId, List<String> answer) {
        return new Answer(QuestionID.valueOf(sectionId, questionId), answer);
    }

    public static Answer valueOf(String sectionId, String questionId, String answer) {
        return new Answer(QuestionID.valueOf(sectionId, questionId), new ArrayList<>(List.of(answer)));
    }

    public boolean add(Answer answer) {
        return this.answer.addAll(answer.values());
    }

    public List<String> values() {
        return this.answer;
    }

    public QuestionID questionID() {return this.questionID;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(questionID, answer.questionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionID);
    }
}
