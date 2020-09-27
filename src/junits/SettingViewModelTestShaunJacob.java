package junits;

import models.AnimalModel;
import models.CropModel;
import models.SeasonModel;
import models.SeedModel;
import org.junit.Before;
import org.junit.Test;
import viewmodels.SettingViewModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SettingViewModelTestShaunJacob {
    SettingViewModel settingViewModel;
    SeedModel seedModel;
    SeasonModel seasonModel;
    private List<AnimalModel> desirableAnimals;
    private List<CropModel> desirableCrops;

    @Before
    public void setUp() {
        desirableAnimals = new ArrayList<>();
        desirableCrops = new ArrayList<>();
        seedModel = new SeedModel("corn");
        seasonModel = new SeasonModel(2, "winter", desirableAnimals, desirableCrops);
        settingViewModel = new SettingViewModel(seedModel, seasonModel);
    }

    @Test
    public void testGetPlayerName() {

    }
}
