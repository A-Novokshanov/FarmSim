package tests.milestone4;

import models.*;
import org.junit.Before;
import org.junit.Test;
import viewmodels.PlayerViewModel;

import static org.junit.Assert.assertEquals;

/**
 * @author Matthew Farias mfarias6@gatech.edu
 * @version 1.0
 */
public class PlayerViewModelTestMatthewFarias {
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
    }

    @Test
    public void testPlayerExists() {
        playerViewModel.addSettingsToDatabase(playerModel);
        Boolean name1 = playerViewModel.playerExists("Matthew");
        Boolean name2 = playerViewModel.playerExists("Default");
        Boolean name3 = playerViewModel.playerExists(null);
        assertEquals(name1, true);
        assertEquals(name2, false);
        assertEquals(name3, false);
    }

    @Test
    public void testSetPlayerDetails() {
        playerViewModel.setPlayerDetails(cropModel, seasonModel, "Matthew",
                storageModel, "Normal", 1000.0);
        assertEquals(playerViewModel.getPlayer().getPlayerSettings().getStartingSeason(),
                seasonModel);
        assertEquals(playerViewModel.getPlayer().getPlayerSettings().getStartingCropType(),
                cropModel);
        assertEquals(playerViewModel.getPlayer().getUserStorage(), storageModel);
        assertEquals(playerViewModel.getPlayer().getUserCurrentMoney(), 1000.0, 0.0);
        assertEquals(playerViewModel.getPlayer().getPlayerSettings().getPlayerName(), "Matthew");
    }

    @Test
    public void testGetPlayer() {
        playerViewModel.setPlayerDetails(cropModel, seasonModel, "Matthew",
                storageModel, "Normal", 1000.0);
        PlayerModel testPlayerModel = playerViewModel.getPlayer();
        assertEquals(testPlayerModel.getPlayerSettings().getStartingCropType(), cropModel);
        assertEquals(testPlayerModel.getPlayerSettings().getStartingSeason(), seasonModel);
        assertEquals(testPlayerModel.getUserStorage(), storageModel);
        assertEquals(testPlayerModel.getUserCurrentMoney(), 1000.0, 0.0);
        assertEquals(testPlayerModel.getPlayerSettings().getPlayerName(), "Matthew");
    }
}
