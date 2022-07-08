package eapli.base.surveymanagement.domain;

import eapli.framework.domain.model.ValueObject;

public enum TargetConditionType implements ValueObject  {
    Category("Category"),
    Product("Product"),
    AgeGroup("Age Group"),
    Brand("Brand"),
    Gender("Gender");


    private final String type;

    TargetConditionType(String status) {
        this.type = status;
    }

    public static TargetConditionType valueOfType(String type) {
        for (TargetConditionType e : values())
            if (e.type.equals(type))
                return e;

        throw new IllegalArgumentException("No Such Target type!");
    }
}
