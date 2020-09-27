package junits;

import models.AnimalModel;
import models.CropModel;
import models.SeasonModel;
import models.SeedModel;
import org.junit.Before;
import org.junit.Test;
import viewmodels.SettingViewModel;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author Matthew Farias Email:mfarias6@gatech.edu
 * @version 1.0
 */
public class SettingViewModelTestMatthewFarias {
    private SettingViewModel settingViewModel;
    private AnimalModel testAnimal;
    private List<AnimalModel> listAnimals;
    private CropModel testCrop;
    private List<CropModel> listCrops;
    private SeedModel seedModel;
    private SeasonModel seasonModel;

    @Before
    public void setup() {
        testAnimal = new AnimalModel(100, 100, 50, "TestAnimal");
        listAnimals = new ArrayList<>();
        listAnimals.add(testAnimal);
        testCrop = new CropModel("testCrop", 2, 4);
        listCrops  = new ArrayList<>();
        listCrops.add(testCrop);
        seedModel  = new SeedModel("Corn");
        seasonModel = new SeasonModel(1, "Spring", listAnimals, listCrops);
        settingViewModel = new SettingViewModel();
        settingViewModel.setPlayerDetails(seedModel, seasonModel, "TestName", "Normal");
    }

    /**
     * Test for the player settings getter for the SettingViewModel class.
     */
    @Test
    public void testGetDifficulty() {
        assertEquals(settingViewModel.getPlayerSettings().getStartingSeedType(), seedModel);
        assertEquals(settingViewModel.getPlayerSettings().getStartingSeason(), seasonModel);
        assertEquals(settingViewModel.getPlayerSettings().getPlayerName(), "TestName");
        assertEquals(settingViewModel.getPlayerSettings().getStartingDifficulty(), "Normal");
    }

    /**
     * Test for the player details setter for the SettingViewModel class.
     */
    @Test
    public void testSetPlayerDetails() {
        seedModel = new SeedModel("Tomato");
        seasonModel = new SeasonModel(1, "Summer", listAnimals, listCrops);
        settingViewModel.setPlayerDetails(seedModel, seasonModel, "NameTest", "Veteran");
        assertEquals(settingViewModel.getPlayerSettings().getStartingSeedType(), seedModel);
        assertEquals(settingViewModel.getPlayerSettings().getStartingSeason(), seasonModel);
        assertEquals(settingViewModel.getPlayerSettings().getPlayerName(), "NameTest");
        assertEquals(settingViewModel.getPlayerSettings().getStartingDifficulty(), "Veteran");
    }
}
