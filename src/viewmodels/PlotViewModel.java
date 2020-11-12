package viewmodels;


import models.CropModel;
import models.PlayerModel;
import models.PlotModel;
import models.StorageModel;
import models.WorkerModel;
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

    private WorkerModel worker = new WorkerModel();
    private WorkerViewModel workerViewModel = new WorkerViewModel(worker, playerModel);

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
        if ((harvestedPlot.getWaterValue() > 6) || (harvestedPlot.getWaterValue() <= 0)) {
            harvestedPlot.setCropInPlot(null);
            playerPlotService.harvestPlot(-harvestedPlot.getWaterValue(),
                    harvestedPlot.getPlotIdentifier(), player.getPlayer()
                            .getPlayerSettings().getPlayerName());
        } else if (harvestedPlot.getDaysOld() >= 10) {
            if (harvestedPlot.getFertilizerLevel() > 0) {
                Random rand = new Random();
                int chanceIncrease = rand.nextInt(2);
                int newYield = 3;
                if (chanceIncrease > 0) {
                    newYield = 5;
                }
                while ((toAdd < newYield) && (player.getPlayer()
                        .getUserStorage().getTotalCropAmount() < 15)) {
                    storageVM.addToInventory(harvestedPlot.getCropInPlot(), 1);
                    toAdd++;
                }
            } else {
                while ((toAdd < 3) && (player.getPlayer()
                        .getUserStorage().getTotalCropAmount() < 15)) {
                    storageVM.addToInventory(harvestedPlot.getCropInPlot(), 1);
                    toAdd++;
                }
            }
            //playerPlotService.deletePlot(harvestedPlot.getPlotIdentifier(),
            //        player.getPlayer().getPlayerSettings().getPlayerName());
            harvestedPlot.setCropInPlot(null);
            playerPlotService.harvestPlot(-harvestedPlot.getWaterValue(),
                    harvestedPlot.getPlotIdentifier(),
                    player.getPlayer().getPlayerSettings().getPlayerName());
        }
    }

    /**
     * Responsibilities for a worker
     *
     * @param harvestedPlot The plot to be harvested.
     * @param player        The player harvesting the plot.
     * @param worker        The worker that will be responsible for doing work
     */
    public void workerWork(PlotModel harvestedPlot, PlayerViewModel player, WorkerModel worker) {
        StorageViewModel storageVM = new StorageViewModel(player);
        CropModel crop = harvestedPlot.getCropInPlot();
        int workerType = worker.getWorkerType();
        if (workerType == 0) {
            return;
        } else if (workerType == 1) {
            harvestPlot(harvestedPlot, player);
        } else if (workerType == 2) {
            harvestPlot(harvestedPlot, player);
            if (crop.getCropQuantity() > 2) {
                storageVM.sellItemFromInventory(crop, 2);
            }
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
        if ((plotToWater.getWaterValue()) > 0 && (plotToWater.getWaterValue() <= 6)) {
            plotToWater.setWaterValue(plotToWater.getWaterValue() + 2);
            playerPlotService.updateWaterValue(2, playerModel.getPlayerSettings().getPlayerName(),
                    plotToWater.getPlotIdentifier());
        }
    }

    /**
     * Used to update water value in database.
     *
     * @param difWaterValue The water value to change value by.
     * @param plotId        The plot's identification number.
     */
    public void updateWaterValue(int difWaterValue, int plotId) {
        playerPlotService.updateWaterValue(difWaterValue,
                playerModel.getPlayerSettings().getPlayerName(), plotId);
    }

    /**
     * Gets the players plots from the database.
     *
     * @return The list of players plots.
     */
    public List<PlotModel> getPlotsFromDatabase() {
        return playerPlotService.queryPlayerPlots(playerModel.getPlayerSettings().getPlayerName());
    }

    /**
     * Increments the daysOld of a PlotModel.
     *
     * @param plotToIncrement The plot whose daysOld to increment, and waterValue to decrement.
     * @param player          the player.
     */
    public void incrementPlotDaysOld(PlotModel plotToIncrement, PlayerViewModel player) {
        if (plotToIncrement.getFertilizerLevel() > 0) {
            plotToIncrement.setDaysOld(plotToIncrement.getDaysOld() + 2);
        } else {
            plotToIncrement.setDaysOld(plotToIncrement.getDaysOld() + 1);
        }
        //plotToIncrement.setDaysSinceWater(plotToIncrement.getDaysSinceWater() + 1);
        if ((plotToIncrement.getWaterValue() > 0) && (plotToIncrement.getWaterValue() <= 6)) {
            plotToIncrement.setWaterValue(plotToIncrement.getWaterValue() - 1);
        }
        if (plotToIncrement.getFertilizerLevel() > 0) {
            plotToIncrement.setFertilizerLevel(plotToIncrement.getFertilizerLevel() - 1);
        }
        workerViewModel.payWorker(worker);
        workerWork(plotToIncrement, player, worker);
    }

    /**
     * Plants a crop into PlotModel.
     *
     * @param plotToPlant the plot which to plant a crop to.
     * @param cropToPlant the crop to plant in the plot.
     */
    public void plantPlot(PlotModel plotToPlant, CropModel cropToPlant) {
        plotToPlant.setCropInPlot(cropToPlant);
        playerPlotService.plantCrop(plotToPlant, playerModel.getPlayerSettings().getPlayerName());
    }

    /**
     * Uses fertilizer on a plot.
     *
     * @param plotToPlant the plot which to plant a crop to.
     */
    public void fertilizePlot(PlotModel plotToPlant) {
        if (plotToPlant.getFertilizerLevel() < 9) {
            plotToPlant.setFertilizerLevel(plotToPlant.getFertilizerLevel() + 2);
        }
    }

    /**
     * Uses pesticide on a plot.
     *
     * @param plotToPlant the plot which to plant a crop to.
     */
    public void pesticidePlot(PlotModel plotToPlant) {
        if (plotToPlant.getCropInPlot() == null) {
            return;
        }
        StorageModel storage = playerModel.getUserStorage();
        switch (plotToPlant.getCropInPlot().getCropName()) {
            case ("Corn"):
                plotToPlant.setCropInPlot(storage.getInventory().get(3));
                break;
            case ("Potato"):
                plotToPlant.setCropInPlot(storage.getInventory().get(4));
                break;
            case ("Tomato"):
                plotToPlant.setCropInPlot(storage.getInventory().get(5));
                break;
            default:
                break;
        }
    }

    /**
     * Saves the plots from the users game when they click continue.
     *
     * @param plots      is list of all 8 plots the user has, each having a certain state.
     * @param playerName the user name we are specifically pulling from the database for.git
     */
    public void addPlayerPlotsToDatabase(List<PlotModel> plots, String playerName) {
        playerPlotService.addPlayerPlots(plots, playerName);
    }

    /**
     * Updates the plot maturity by the time since planted.
     *
     * @param plotIdentifier is the identifier of the plot.
     * @param playerName     is the name of the player.
     */
    public void updatePlotMaturity(int plotIdentifier, String playerName) {
        playerPlotService.adjustPlotMaturity(plotIdentifier, playerName);
    }

    /**
     * Method that sets the plot to a new stage.
     *
     * @param playerName     the player who owns it.
     * @param plotStage      the stage the plot is at.
     * @param plotIdentifier what plot we want.
     */
    public void updatePlotStage(String playerName, String plotStage, int plotIdentifier) {
        playerPlotService.updatePlotStage(plotIdentifier, plotStage, playerName);
    }

    /**
     * Updates the days of the plot.
     *
     * @param days       The number of days to increment by.
     * @param identifier The identifier of the plot.
     * @param playerName The name of the player.
     */
    public void updatePlotDaysDatabase(int days, int identifier, String playerName) {
        playerPlotService.adjustPlotDays(days, identifier, playerName);
    }


    public void updatePlotFertilizerDatabase(int fertAmount, int plotIdentifier, String playerName) {
        playerPlotService.adjustPlotFertilizer(fertAmount, plotIdentifier, playerName);

    }

    public void getPlotFertilizerDatabase(int plotIdentifier, String playerName) {
        playerPlotService.queryPlotFertilizer(plotIdentifier, playerName);
    }


}