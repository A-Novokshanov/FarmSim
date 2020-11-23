package tests.milestone6;

import models.CropModel;
import models.PlayerModel;
import models.SeasonModel;
import models.SettingModel;
import models.StorageModel;
import org.junit.Before;
import org.junit.Test;
import viewmodels.PlayerViewModel;

import static org.junit.Assert.assertEquals;

/**
 * Junit tests for the PlayerViewModelClass.
 * Tests the new methods added for M6.
 *
 * @author Aditya Varun Pratap
 * @version 1.0
 */
public class PlayerViewModelTestAdityaPratap {

    private PlayerViewModel playerViewModel;
    private PlayerModel playerModel;
    private StorageModel storageModel;
    private SettingModel settingModel;
    private SeasonModel seasonModel;
    private CropModel cropModel;

    @Before
    public void setUp() {
        cropModel = new CropModel("DefaultCrop", 3, 100.0);
        seasonModel = new SeasonModel(1, "Spring", null, null);
        settingModel = new SettingModel(seasonModel, cropModel, "Normal", "Matthew");
        storageModel = new StorageModel();
        playerModel = new PlayerModel(1000.0, settingModel, storageModel);
        playerViewModel = new PlayerViewModel();
        playerViewModel.setPlayerDetails(
                cropModel, seasonModel, "Aditya", storageModel, "Hard", 1000);
    }


    @Test
    public void testIncreasePlayerHarvestCounter() {
        playerViewModel.increasePlayerHarvestCounter();

        int expected = 1;
        int actual = playerViewModel.getPlayer().getCurrentHarvestCounter();

        assertEquals(expected, actual);
    }

    @Test
    public void testIncreasePlayerWaterCounter() {
        playerViewModel.increasePlayerWaterCounter();

        int expected = 1;
        int actual = playerViewModel.getPlayer().getCurrentWaterCounter();

        assertEquals(expected, actual);

    }

    @Test
    public void testZeroCurrentHarvestCounter() {

        playerViewModel.zeroCurrentHarvestCounter();

        int expected = 0;
        int actual = playerViewModel.getPlayer().getCurrentHarvestCounter();

        assertEquals(expected, actual);

    }

    @Test
    public void testZeroCurrentWaterCounter() {

        playerViewModel.zeroCurrentWaterCounter();

        int expected = 0;
        int actual = playerViewModel.getPlayer().getCurrentWaterCounter();

        assertEquals(expected, actual);


    }

    @Test
    public void increaseMaxHarvest() {

        playerViewModel.increaseMaxHarvest();

        int expected = 7;
        int actual = playerViewModel.getPlayer().getMaxHarvestsPerDay();

        assertEquals(expected, actual);

    }

    @Test
    public void increaseMaxWater() {

        playerViewModel.increaseMaxWater();

        int expected = 7;
        int actual = playerViewModel.getPlayer().getMaxWateringPerDay();

        assertEquals(expected, actual);

    }

}
