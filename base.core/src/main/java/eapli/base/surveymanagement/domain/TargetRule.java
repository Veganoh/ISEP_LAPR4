package eapli.base.surveymanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.List;

public class TargetRule implements ValueObject, Comparable<TargetRule> {
    private List<TargetCondition> conditions = new ArrayList<>();

    public TargetRule() {}

    public TargetRule(List<Pair<String, String>> conditions) {
        Preconditions.nonEmpty(conditions, "A Target Rule must have at least one condition defined!");
        int ageGroupCount = 0;
        int genderCount = 0;

        for (Pair<String, String> condition : conditions) {
            if (condition.a.equals("Age Group"))
                ageGroupCount++;

            if (condition.a.equals("Gender"))
                genderCount++;

            Preconditions.ensure(ageGroupCount <= 1, "A Target Rule can't have more than one Age Group Condition!");
            Preconditions.ensure(genderCount <= 1, "A Target Rule can't have more than one Gender Condition!");

            this.conditions.add(TargetCondition.valueOf(condition.a, condition.b));
        }
    }

    public static TargetRule valueOf(List<Pair<String, String>> conditions) { return new TargetRule(conditions);}

    @Override
    public int compareTo(TargetRule o) {
        return 0;
    }

    public List<TargetCondition> conditions() {
        return conditions;
    }
}
