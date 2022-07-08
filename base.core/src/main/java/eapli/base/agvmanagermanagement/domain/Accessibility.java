package eapli.base.agvmanagermanagement.domain;

public enum Accessibility {
    Up("w-"),
    Right("l+"),
    Down("w+"),
    Left("l-");

    private final String label;

    Accessibility(String label) {
        this.label = label;
    }

    public static Accessibility valueOfLabel(String label) {
        for (Accessibility e : values())
            if (e.label.equals(label))
                return e;

        return null;
    }
}
