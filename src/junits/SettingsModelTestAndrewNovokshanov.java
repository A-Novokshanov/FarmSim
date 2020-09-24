package junits;

import models.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Andrew Novokshanov anovokshanov3@gatech.edu
 * @version 1.0
 */
public class SettingsModelTestAndrewNovokshanov {
    List<AnimalModel> desirableAnimals;
    List<CropModel> desirableCrop;
    SeasonModel spring;
    SeedModel tomato;
    SettingModel SettingModelTest;
    @Before
    public void setup() {
        desirableAnimals = new ArrayList<AnimalModel>();
        desirableCrop = new ArrayList<CropModel>();
        spring = new SeasonModel(100,"Spring", desirableAnimals, desirableCrop);
        tomato = new SeedModel("Tomato Seed");
        SettingModelTest = new SettingModel(spring, tomato, "Normal", "Andrew N");
    }

    //Test getter method for SeasonModel
    @Test
    public void addPos123PosAnd456() {

        assertEquals(spring, SettingModelTest.getStartingSeason());
    }
    @Test
    public void addNeg10AndNeg20() {

        assertEquals(builtInAdd, person2Add);
    }
    @Test
    public void addNeg301AndPos50() {

        assertEquals(builtInAdd, person2Add);
    }
    //Test subtraction cases with positive numbers, negative numbers, and positive and negative numbers.
    @Test
    public void subtractPos123PosAnd456() {

        assertEquals(builtInSubtract, person2Subtract);
    }
    @Test
    public void subtractNeg10AndNeg20() {

        assertEquals(builtInSubtract, person2Subtract);
    }
    @Test
    public void subtractNeg301AndPos50() {

        assertEquals(builtInSubtract, person2Subtract);
    }
    //Test multiplication cases with positive numbers, negative numbers, and positive and negative numbers.
    @Test
    public void multiplyPos123PosAnd456() {

        assertEquals(builtInMultiply, person2Multiply);
    }
    @Test
    public void multiplyNeg10AndNeg20() {

        assertEquals(builtInMultiply, person2Multiply);
    }
    @Test
    public void multiplyNeg301AndPos50() {

        assertEquals(builtInMultiply, person2Multiply);
    }
    //Test division cases with positive numbers, negative numbers, positive and negative numbers, and division by 0.
    @Test
    public void dividePos123PosAnd456() {

        assertEquals(builtInDivide, person2Divide);
    }
    @Test
    public void divideNeg10AndNeg20() {

        assertEquals(builtInDivide, person2Divide);
    }
    @Test
    public void divideNeg301AndPos50() {

        assertEquals(builtInDivide, person2Divide);
    }
    @Test(expected = IllegalArgumentException.class)
    public void divideBy0() {

        assertEquals(builtInDivide, person2Divide);
    }
}
