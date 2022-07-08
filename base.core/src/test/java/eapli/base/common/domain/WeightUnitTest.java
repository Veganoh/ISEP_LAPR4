package eapli.base.common.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeightUnitTest {

    @Test
    public void convertTest(){
        Weight volume = new Weight(1.5, WeightUnit.KG);
        Weight volumeExpected = new Weight(1500, WeightUnit.G);
        Weight convertedVolume = WeightUnit.convert(volume, WeightUnit.G);
        assertEquals(convertedVolume, volumeExpected);
    }
}