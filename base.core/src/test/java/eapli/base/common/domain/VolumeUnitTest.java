package eapli.base.common.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VolumeUnitTest {

    @Test
    public void convertTest(){
        Volume volume = new Volume(1, VolumeUnit.CM);
        Volume volumeExpected = new Volume(1000, VolumeUnit.MM);
        Volume convertedVolume = VolumeUnit.convert(volume, VolumeUnit.MM);
        assertEquals(convertedVolume, volumeExpected);
    }
}