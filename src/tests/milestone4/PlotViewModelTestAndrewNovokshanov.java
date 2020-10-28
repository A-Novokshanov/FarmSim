package tests.milestone4;

import models.*;
import org.junit.Before;
import org.junit.Test;
import viewmodels.PlayerViewModel;
import viewmodels.PlotViewModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
        harvestedPlotAlive.setDaysSinceWater(3);
        harvestedPlotDead = new PlotModel(cropInPlot, 10);
        harvestedPlotDead.setDaysSinceWater(6);
        List<CropModel> desCrop = new ArrayList<CropModel>();
        desCrop.add(cropInPlot);
        List<AnimalModel> desAnim = new ArrayList<AnimalModel>();
        desAnim.add(animal);
        SeasonModel season = new SeasonModel(1, "Spring", desAnim, desCrop);
        plotViewModel = new PlotViewModel();
        SettingModel playerSetting = new SettingModel(season, cropInPlot, "Casual", "Andrew");
        StorageModel playerStorage = new StorageModel();
        PlayerModel player = new PlayerModel(100.00, playerSetting, playerStorage);
        playerViewModel.setPlayerDetails(cropInPlot, season, playerSetting.getPlayerName(),
                playerStorage, playerSetting.getStartingDifficulty(), player.getUserCurrentMoney());
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
        plotViewModel.incrementPlotDaysOld(harvestedPlotAlive);
        assertEquals(5, harvestedPlotAlive.getDaysOld());
        assertEquals(4, harvestedPlotAlive.getDaysSinceWater());
        plotViewModel.incrementPlotDaysOld(harvestedPlotDead);
        assertEquals(11, harvestedPlotAlive.getDaysOld());
        assertEquals(7, harvestedPlotDead.getDaysSinceWater());
    }

    @Test
    //Test harvestPlot() method to make sure a PlotModel is correctly being harvested correctly,
    // depending on its stage of growth and days since its been watered.
    public void testHarvestPlot() {
        plotViewModel.harvestPlot(harvestedPlotAlive, playerViewModel);
        assertEquals(harvestedPlotAlive.getCropInPlot(), "Potato");
        assertEquals(playerViewModel.getPlayer().getUserStorage().getInventory().get(1), 4);
        assertEquals(harvestedPlotAlive.getCropInPlot(), null);
        assertEquals(harvestedPlotDead.getCropInPlot(), "Potato");
        plotViewModel.harvestPlot(harvestedPlotDead, playerViewModel);
        assertEquals(playerViewModel.getPlayer().getUserStorage().getInventory().get(1), 4);
        assertEquals(harvestedPlotDead.getCropInPlot(), null);
    }

    @Test
    //Test waterPlot() method to make sure PlotModel was correctly having daysSinceWater set to 0
    public void testWaterPlot() {
        plotViewModel.waterPlot(harvestedPlotAlive);
        assertEquals(harvestedPlotAlive.getDaysSinceWater(), 0);
        plotViewModel.waterPlot(harvestedPlotDead);
        assertEquals(harvestedPlotDead.getDaysSinceWater(), 0);
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
