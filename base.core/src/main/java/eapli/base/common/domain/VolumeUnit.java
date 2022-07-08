package eapli.base.common.domain;

public enum VolumeUnit {
    MM(1),CM(1000),DM(1000000),M(1000000000);

    private long code;

    private VolumeUnit(long code) {
        this.code = code;
    }

    public static Volume convert(Volume volume, VolumeUnit unit){
        return Volume.valueOf(volume, ((double)volume.Unit().code)/unit.code, unit);
    }
}
