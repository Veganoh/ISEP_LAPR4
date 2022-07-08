package eapli.base.common.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeightTest {

    @Test
    public void equalsTest(){
        Weight volume = new Weight(1.5, WeightUnit.KG);
        Weight volumeExpected = new Weight(1500, WeightUnit.G);
        assertEquals(volume, volumeExpected);
    }

    @Test
    public void constructorTest(){
        Weight volume = new Weight(1.5, WeightUnit.G);
        Weight volumeExpected1 = Weight.valueOf("1.5 g");
        Weight volumeExpected2 = Weight.valueOf("1.5 G");

        assertEquals(volume, volumeExpected1);
        assertEquals(volume, volumeExpected2);
    }

}