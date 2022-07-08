package eapli.base.common.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VolumeTest {

    @Test
    public void equalsTest(){
        Volume volume = new Volume(1.5, VolumeUnit.CM);
        Volume volumeExpected = new Volume(1500, VolumeUnit.MM);
        assertEquals(volume, volumeExpected);
    }

    @Test
    public void constructorTest(){
        Volume volume = new Volume(1.5, VolumeUnit.CM);
        Volume volumeExpected1 = Volume.valueOf("1.5 cm");
        Volume volumeExpected2 = Volume.valueOf("1.5 CM");

        assertEquals(volume, volumeExpected1);
        assertEquals(volume, volumeExpected2);
    }
}