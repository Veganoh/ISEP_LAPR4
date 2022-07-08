package eapli.base.common.domain;

public enum WeightUnit {
    MG(1),G(1000),KG(1000000);

    private long code;

    private WeightUnit(long code) {
        this.code = code;
    }

    public static Weight convert(Weight volume, WeightUnit unit){
        return Weight.valueOf(volume, ((double)volume.Unit().code)/unit.code, unit);
    }
}
