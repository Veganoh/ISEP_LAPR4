package eapli.base.surveymanagement.domain;

import eapli.framework.validations.Preconditions;

import java.util.*;
import java.util.stream.Collectors;

public class QuestionStatisticsService {
    private final Question question;
    private final List<Answer> answers;

    private final int size;

    private final String report;

    public QuestionStatisticsService(Question question, List<Answer> answers) {
        Preconditions.noneNull(question, answers);

        this.question = question;
        this.answers = answers;
        this.size = answers.size();

        this.report = buildStatistics();
    }

    private String buildStatistics() {
        switch (question.questionType()) {
            case FREE_TEXT:
            case NUMERIC:
            case SINGLE_CHOICE:
            case SINGLE_CHOICE_INPUT:
                return printAlternative(distributionForEachAlternative());
            case MULTIPLE_CHOICE:
            case MULTIPLE_CHOICE_INPUT:
                return printAlternative(distributionForEachAlternative()) +
                        printCombined(distributionOfCombinedResponses());
            case SORTING_OPTION:
            case SCALING_OPTION:
                return printPosition(distributionForEachPosition());
            default:
                return null;
        }
    }

    private Map<String, Double> distributionForEachAlternative() {
        Map<String, Double> distribution = new HashMap<>();

        for (Answer answer : answers) {
            for (String option : answer.values()) {

                if (distribution.containsKey(option))
                    distribution.put(option, distribution.get(option) + (1.0 / (double) size));
                else
                    distribution.put(option, 1.0 / (double) size);
            }
        }

        return distribution;
    }

    private Map<HashSet<String>, Double> distributionOfCombinedResponses() {
        Map<HashSet<String>, Double> distribution = new HashMap<>();

        //Calculate all possible answer intersections
        for (Answer answer : answers) {
            for (Answer otherAnswer : answers) {
                if (answer.equals(otherAnswer)) continue;

                HashSet<String> intersection = new HashSet<>(answer.values());
                intersection.retainAll(otherAnswer.values());
                if (intersection.size() <= 1) continue;

                if (distribution.containsKey(intersection))
                    distribution.put(intersection, distribution.get(intersection) + (1.0 / (double) size));
                else
                    distribution.put(intersection, 1.0 / (double) size);
            }
        }

        return distribution;
    }

    private HashMap<String, HashMap<String, Double>> distributionForEachPosition() {
        HashMap<String, HashMap<String, Double>> distribution = new HashMap<>();

        for (Answer answer : answers) {
            for (int i = 1; i < question.options().size(); i++) {
                String positionAnswer = answer.values().get(i - 1);
                String option = question.options().get(i - 1);

                if (distribution.containsKey(option)) {
                    if (distribution.get(option).containsKey(positionAnswer))
                        distribution.get(option).put(positionAnswer, distribution.get(option).get(positionAnswer) + (1.0 / (double) size));
                    else
                        distribution.get(option).put(positionAnswer, 1.0 / (double) size);
                } else {
                    distribution.put(option, new HashMap<>());
                    distribution.get(option).put(positionAnswer, 1.0 / (double) size);
                }
            }
        }
        return distribution;
    }

    private String printAlternative(Map<String, Double> result) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Double> stat : result.entrySet())
            sb.append(String.format(
                    "%s (%.0f%%)\n", stat.getKey(), stat.getValue() * 100
            ));

        return sb.toString();
    }

    private String printCombined(Map<HashSet<String>, Double> result) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nCombined:\n");

        if (result.isEmpty()) sb.append("No combined choices were selected.\n");
        else {
            for (Map.Entry<HashSet<String>, Double> stat : result.entrySet()) {
                sb.append(String.format(
                        "%s (%.0f%%)\n",
                        stat.getKey().stream().map(Object::toString).collect(Collectors.joining("-")),
                        stat.getValue() * 100
                ));
            }
        }
        return sb.toString();
    }

    private String printPosition(HashMap<String, HashMap<String, Double>> result) {
        StringBuilder sb = new StringBuilder();
        for (String pos : result.keySet()) {
            sb.append(String.format("%s - ", pos));
            for (Map.Entry<String, Double> stat : result.get(pos).entrySet())
                sb.append(String.format(
                        "%s (%.0f%%)  ", stat.getKey(), stat.getValue() * 100
                ));
            sb.append("\n");
        }
        return sb.toString();
    }

    public String print() {
        return String.format("No. of answers: %d\n%s", size, report);
    }

}
