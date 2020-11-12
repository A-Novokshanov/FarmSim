package tests.milestone4;

import models.AnimalModel;
import models.CropModel;
import models.PlayerModel;
import models.PlotModel;
import models.SeasonModel;
import models.SettingModel;
import models.StorageModel;
import org.junit.Before;
import org.junit.Test;
import viewmodels.PlayerViewModel;
import viewmodels.PlotViewModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Andrew Novokshanov anovokshanov3@gatech.edu
 * @version 1.0
 */
public class PlotViewModelTestAndrewNovokshanov {
    private PlotModel harvestedPlotAlive;
    private PlotModel harvestedPlotDead;
    private CropModel cropInPlot;
    private PlotViewModel plotViewModel;
    private PlayerViewModel playerViewModel;


    @Before
    public void setUp() {
        cropInPlot = new CropModel("Potato", 2, 1.50);
        AnimalModel animal = new AnimalModel(1, 1, 1, "Cow");
        harvestedPlotAlive = new PlotModel(cropInPlot, 4);
        //harvestedPlotAlive.setDaysSinceWater(3);
        harvestedPlotDead = new PlotModel(cropInPlot, 10);
        //harvestedPlotDead.setDaysSinceWater(6);
        List<CropModel> desCrop = new ArrayList<CropModel>();
        desCrop.add(cropInPlot);
        List<AnimalModel> desAnim = new ArrayList<AnimalModel>();
        desAnim.add(animal);
        SeasonModel season = new SeasonModel(1, "Spring", desAnim, desCrop);
        SettingModel playerSetting = new SettingModel(season, cropInPlot, "Casual", "Andrew");
        StorageModel playerStorage = new StorageModel();
        PlayerModel player = new PlayerModel(100.00, playerSetting, playerStorage);
        playerViewModel = new PlayerViewModel();
        playerViewModel.setPlayerDetails(
                playerSetting.getStartingCropType(), season, playerSetting.getPlayerName(),
                playerStorage, playerSetting.getStartingDifficulty(), player.getUserCurrentMoney());
        plotViewModel = new PlotViewModel(playerViewModel.getPlayer());
    }

    @Test
    //Test populatePlot() method to make sure a PlotModel is correctly being populated initially
    public void testPopulatePlot() {
        PlotModel postPop = plotViewModel.populatePlot(cropInPlot);
        assertEquals(postPop.getCropInPlot(), cropInPlot);
    }

    @Test
    //Test incrementPlotDaysOld() method to make sure plots' daysOld
    //and daysSinceWater were being incremented correctly
    public void testIncrementPlotDaysOld() {
        assertEquals(3, harvestedPlotAlive.getWaterValue());
        plotViewModel.incrementPlotDaysOld(harvestedPlotAlive, playerViewModel);
        assertEquals(5, harvestedPlotAlive.getDaysOld());
        assertEquals(2, harvestedPlotAlive.getWaterValue());
        assertEquals(3, harvestedPlotDead.getWaterValue());
        plotViewModel.incrementPlotDaysOld(harvestedPlotDead, playerViewModel);
        assertEquals(11, harvestedPlotDead.getDaysOld());
        assertEquals(2, harvestedPlotDead.getWaterValue());
    }

    @Test
    //Test harvestPlot() method to make sure a PlotModel is correctly being harvested correctly,
    // depending on its stage of growth and days since its been watered.
    public void testHarvestPlot() {
        harvestedPlotAlive.setDaysOld(10);
        assertEquals("Potato", harvestedPlotAlive.getCropInPlot().getCropName());
        plotViewModel.harvestPlot(harvestedPlotAlive, playerViewModel);
        assertEquals(6,
                playerViewModel.getPlayer().getUserStorage().getInventory().get(1).getCropQuantity()
        );
        assertNull(harvestedPlotAlive.getCropInPlot());
        assertEquals("Potato", harvestedPlotDead.getCropInPlot().getCropName());
        harvestedPlotDead.setWaterValue(10);
        plotViewModel.harvestPlot(harvestedPlotDead, playerViewModel);
        assertEquals(6,
                playerViewModel.getPlayer().getUserStorage().getInventory().get(1).getCropQuantity()
        );
        assertNull(harvestedPlotDead.getCropInPlot());
    }

    @Test
    //Test waterPlot() method to make sure PlotModel was correctly having daysSinceWater set to 0
    public void testWaterPlot() {
        plotViewModel.waterPlot(harvestedPlotAlive);
        assertEquals(5, harvestedPlotAlive.getWaterValue());
        harvestedPlotDead.setWaterValue(-3);
        plotViewModel.waterPlot(harvestedPlotDead);
        assertEquals(-3, harvestedPlotDead.getWaterValue());
    }

    @Test
    //Test plantPlot() method to make sure CropModel was being properly added to a PlotModel
    public void testPlantCropInPlot() {
        CropModel newCrop = new CropModel("Corn", 10, 15.0);
        plotViewModel.harvestPlot(harvestedPlotDead, playerViewModel);
        plotViewModel.plantPlot(harvestedPlotDead, newCrop);
        assertEquals(harvestedPlotDead.getCropInPlot(), newCrop);
    }
}
