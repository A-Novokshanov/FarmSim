package viewmodels;

import models.CropModel;
import models.PlayerModel;
import models.PlotModel;
import services.player.PlayerPlotService;
import services.player.PlayerSettingsService;

import java.util.List;
import java.util.Random;

/**
 * This view-model class controls the logic and flow of harvesting from the plot.
 *
 * @author Aditya Varun Pratap, Andrew Novokshanov
 * @version 1.0
 */
public class PlotViewModel {

    private PlayerPlotService playerPlotService = new PlayerPlotService();
    private PlayerSettingsService playerSettingsService = new PlayerSettingsService();
    private PlayerModel playerModel;

    /**
     * Constructor for plot view model/
     *
     * @param playerModel the player model who uses these plots.
     */
    public PlotViewModel(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

    /**
     * Harvest a plot and store the crops in storage/inventory.
     *
     * @param harvestedPlot The plot to be harvested.
     * @param player        The player harvesting the plot.
     */
    public void harvestPlot(PlotModel harvestedPlot, PlayerViewModel player) {
        StorageViewModel storageVM = new StorageViewModel(player);
        int toAdd = 0;
        /*
        if (harvestedPlot.getDaysSinceWater() > 5) {
            harvestedPlot.setCropInPlot(null);
        }
        */
        if ((harvestedPlot.getWaterValue() > 6) || (harvestedPlot.getWaterValue() <= 0)){
            harvestedPlot.setCropInPlot(null);
        } else if (harvestedPlot.getDaysOld() >= 10) {
            while ((toAdd < 3) && (player.getPlayer().getUserStorage().getTotalCropAmount() < 15)) {
                storageVM.addToInventory(harvestedPlot.getCropInPlot(), 1);
                toAdd++;
            }
            playerPlotService.deletePlot(harvestedPlot.getPlotIdentifier(),
                    player.getPlayer().getPlayerSettings().getPlayerName());
            harvestedPlot.setCropInPlot(null);
        }
    }

    /**
     * Populates the plots with a chosen crop.
     *
     * @param cropInPlot The crop to plot.
     * @return The PlotModel object that has the crop.
     */
    public PlotModel populatePlot(CropModel cropInPlot) {
        Random random = new Random();
        int daysOld = random.nextInt(15);
        return new PlotModel(cropInPlot, daysOld);
    }

    /**
     * Waters the plot, incrementing the value of waterLevel by 2.
     *
     * @param plotToWater The plot to water.
     */
    public void waterPlot(PlotModel plotToWater) {
        /*
        if (plotToWater.getDaysSinceWater() <= 5) {
            plotToWater.setDaysSinceWater(0);
        }
        */
        if ((plotToWater.getWaterValue()) > 0 && (plotToWater.getWaterValue() <= 6)) {
            plotToWater.setWaterValue(plotToWater.getWaterValue() + 2);
        }
    }

    /**
     * Increments the daysOld of a PlotModel.
     *
     * @param plotToIncrement The plot whose daysOld to increment, and waterValue to decrement.
     */
    public void incrementPlotDaysOld(PlotModel plotToIncrement) {
        plotToIncrement.setDaysOld(plotToIncrement.getDaysOld() + 1);
        //plotToIncrement.setDaysSinceWater(plotToIncrement.getDaysSinceWater() + 1);
        plotToIncrement.setWaterValue(plotToIncrement.getWaterValue() - 1);
    }

    /**
     * Plants a crop into PlotModel.
     *
     * @param plotToPlant the plot which to plant a crop to.
     * @param cropToPlant the crop to plant in the plot.
     */
    public void plantPlot(PlotModel plotToPlant, CropModel cropToPlant) {
        plotToPlant.setCropInPlot(cropToPlant);
    }

    /**
     * Saves the plots from the users game when they click continue.
     *
     * @param plots      is list of all 8 plots the user has, each having a certain state.
     * @param playerName the user name we are specifically pulling from the database for.
     */
    public void addPlayerPlotsToDatabase(List<PlotModel> plots, String playerName) {
        playerPlotService.addPlayerPlots(plots, playerName);
    }

    /**
     * Updates the plot maturity by the time since planted.
     *
     * @param cropName   is the name of the crop.
     * @param playerName is the name of the player.
     */
    public void updatePlotMaturity(String cropName, String playerName) {
        playerPlotService.adjustPlotDaysOld(cropName, playerName);
        playerSettingsService.updatePlayerDay(1, playerName);
    }
}
