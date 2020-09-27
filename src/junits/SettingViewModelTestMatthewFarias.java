package junits;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.SeasonModel;
import models.SeedModel;
import org.junit.Before;
import org.junit.Test;
import viewmodels.SettingViewModel;

import static org.junit.Assert.assertEquals;

/**
 * @author Matthew Farias Email:mfarias6@gatech.edu
 * @version 1.0
 */
public class SettingViewModelTestMatthewFarias {
    private SettingViewModel settingViewModel;
    private SeedModel seedModel;
    private SeasonModel seasonModel;

    @Before
    public void setup() {
        seedModel  = new SeedModel("Corn");
        seasonModel = new SeasonModel(1,"Spring", null, null);
        settingViewModel = new SettingViewModel(seedModel, seasonModel);
    }

    /**
     * Test for the player name getter for the SettingViewModel class.
     */
    @Test
    public void testGetPlayerName() {
        assertEquals(settingViewModel.getPlayerName(), null);
    }

    /**
     * Test for the difficulty getter for the SettingViewModel class.
     */
    @Test
    public void testGetDifficulty() {
        assertEquals(settingViewModel.getDifficulty(), null);
    }
}
