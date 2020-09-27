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
public class SettingModelTestAndrewNovokshanov {
    private List<AnimalModel> desirableAnimals;
    private List<CropModel> desirableCrop;
    private SeasonModel spring;
    private SeedModel tomato;
    private SettingModel settingModelTest;
    @Before
    public void setup() {
        desirableAnimals = new ArrayList<AnimalModel>();
        desirableCrop = new ArrayList<CropModel>();
        spring = new SeasonModel(100, "Spring", desirableAnimals, desirableCrop);
        tomato = new SeedModel("Tomato Seed");
        settingModelTest = new SettingModel(spring, tomato, "Normal", "Andrew N");
    }

    //Test getter method for SeasonModel
    @Test
    public void testGetterSeasonModel() {
        assertEquals(settingModelTest.getStartingSeason(), spring);
    }
    //Test setter method for SeasonModel's plantGrowthModifier
    @Test
    public void testSetterSeasonModelPlantGrowthModifier() {
        spring.setPlantGrowthModifier(150);
        assertEquals(spring.getPlantGrowthModifier(), 150);
    }
    //Test getter method for SeedModel
    @Test
    public void testGetterSeedModel() {
        assertEquals(settingModelTest.getStartingSeedType(), tomato);
    }
    //Test setter method for SeedModel's seedType
    @Test
    public void testSetterSeedModelSeedType() {
        tomato.setSeedType("Corn Seed");
        assertEquals(tomato.getSeedType(), "Corn Seed");
    }
    //Test getter method for startingDifficulty
    @Test
    public void testGetterStartingDifficulty() {
        assertEquals(settingModelTest.getStartingDifficulty(), "Normal");
    }
    //Test setter method for startingDifficulty
    @Test
    public void testSetterStartingDifficulty() {
        settingModelTest.setStartingDifficulty("Veteran");
        assertEquals(settingModelTest.getStartingDifficulty(), "Veteran");
    }
    //Test getter method for playerName
    @Test
    public void testGetterPlayerName() {
        assertEquals(settingModelTest.getPlayerName(), "Andrew N");
    }
    //Test setter method for playerName
    @Test
    public void testSetterPlayerName() {
        settingModelTest.setPlayerName("Aibek Musaev");
        assertEquals(settingModelTest.getPlayerName(), "Aibek Musaev");
    }
}
