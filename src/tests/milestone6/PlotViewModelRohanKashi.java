package tests.milestone6;

import models.*;
import org.junit.Before;
import org.junit.Test;
import viewmodels.PlayerViewModel;
import viewmodels.PlotViewModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class PlotViewModelRohanKashi {
    private PlotModel harvestedPlotAlive;
    private PlotModel harvestedPlotDead;
    private CropModel cropInPlot;
    private PlotViewModel plotViewModel;
    private PlayerViewModel playerViewModel;
    private PlotModel plotModel;
    private CropModel cropModel = new CropModel("corn", 2,  4);
    private CropModel cropModel2 = new CropModel("tomato", 1,  3);

    @Before
    public void setUp() {
        plotModel = new PlotModel(cropModel, 0);
        cropInPlot = new CropModel("Potato", 2, 1.50);
        harvestedPlotAlive = new PlotModel(cropInPlot, 4);
        harvestedPlotDead = new PlotModel(cropInPlot, 10);
        List<CropModel> crops = new ArrayList<CropModel>();
        crops.add(cropInPlot);
        List<AnimalModel> desAnim = new ArrayList<AnimalModel>();
        SeasonModel season = new SeasonModel(1, "Spring", desAnim, crops);
        SettingModel playerSetting = new SettingModel(season, cropInPlot, "Casual", "Andrew");
        StorageModel playerStorage = new StorageModel();
        PlayerModel player = new PlayerModel(100.00, playerSetting, playerStorage);
        playerViewModel = new PlayerViewModel();
        playerViewModel.setPlayerDetails(
                playerSetting.getStartingCropType(), season, playerSetting.getPlayerName(),
                playerStorage, playerSetting.getStartingDifficulty(), player.getUserCurrentMoney());
        plotViewModel = new PlotViewModel(playerViewModel.getPlayer());
    }

    /**
     * Tests if crops are getting set in plot
     */
    @Test
    public void testSetCropInPlot() {
        plotModel.setCropInPlot(cropModel2);
        assertEquals(plotModel.getCropInPlot(), cropModel2);
    }

    /**
     * Testing the plot fertilizer level
     */
    @Test
    public void testCropFertilizer() {
        assertEquals(plotModel.getFertilizerLevel(), 0);
    }

    /**
     * Testing the plot fertilizer level setting
     */
    @Test
    public void testSetCropFertilizer() {
        plotModel.setFertilizerLevel(10);
        assertEquals(plotModel.getFertilizerLevel(), 10);
    }

    /**
     * Test the get water value
     */
    @Test
    public void testGetWater() {
        assertEquals(plotModel.getWaterValue(), 3);
    }

    /**
     * Test the set water value
     */
    @Test
    public void testSetWater() {
        plotModel.setWaterValue(19);
        assertEquals(plotModel.getWaterValue(), 19);
    }

    /**
     * Test the days old of plot.
     */
    @Test
    public void testPlotDaysOld() {
        assertEquals(3, harvestedPlotAlive.getWaterValue());
        plotViewModel.incrementPlotDaysOld(harvestedPlotAlive, playerViewModel);
        assertEquals(5, harvestedPlotAlive.getDaysOld());
        assertEquals(2, harvestedPlotAlive.getWaterValue());
        assertEquals(3, harvestedPlotDead.getWaterValue());
        plotViewModel.incrementPlotDaysOld(harvestedPlotDead, playerViewModel);
        assertEquals(11, harvestedPlotDead.getDaysOld());
        assertEquals(2, harvestedPlotDead.getWaterValue());
    }

    /**
     * Test the water level
     */
    @Test
    public void testWaterLevel() {
        plotViewModel.waterPlot(harvestedPlotAlive);
        assertEquals(5, harvestedPlotAlive.getWaterValue());
        harvestedPlotDead.setWaterValue(-3);
        plotViewModel.waterPlot(harvestedPlotDead);
        assertEquals(-3, harvestedPlotDead.getWaterValue());
    }

    /**
     * Test which plant in crop
     */
    @Test
    public void testPlantInPlot() {
        CropModel newCrop = new CropModel("Corn", 10, 15.0);
        plotViewModel.harvestPlot(harvestedPlotDead, playerViewModel);
        plotViewModel.plantPlot(harvestedPlotDead, newCrop);
        assertEquals(harvestedPlotDead.getCropInPlot(), newCrop);
    }

    /**
     * Test if it harvests
     */
    @Test
    public void testHarvest() {
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


}
