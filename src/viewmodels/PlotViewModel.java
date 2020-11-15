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
    private WorkerViewModel workerViewModel = new WorkerViewModel(playerModel);

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
            playerPlotService.harvestPlot(0,
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
            playerPlotService.harvestPlot(0,
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
            updateWaterValue(plotToWater.getWaterValue(), plotToWater.getPlotIdentifier());
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
     * @param plotToFertilize the plot which to plant a crop to.
     */
    public void fertilizePlot(PlotModel plotToFertilize) {
        if (plotToFertilize.getFertilizerLevel() < 9) {
            plotToFertilize.setFertilizerLevel(plotToFertilize.getFertilizerLevel() + 2);
            updatePlotFertilizerDatabase(plotToFertilize, playerModel);
        }
    }

    /**
     * Uses pesticide on a plot.
     *
     * @param plotToPesticide the plot which to plant a crop to.
     */
    public void pesticidePlot(PlotModel plotToPesticide) {
        if (plotToPesticide.getCropInPlot() == null) {
            return;
        }
        StorageModel storage = playerModel.getUserStorage();
        switch (plotToPesticide.getCropInPlot().getCropName()) {
        case ("Corn"):
            plotToPesticide.setCropInPlot(storage.getInventory().get(3));
            updateCropInPlotDatabase(plotToPesticide, playerModel);
            break;
        case ("Potato"):
            plotToPesticide.setCropInPlot(storage.getInventory().get(4));
            updateCropInPlotDatabase(plotToPesticide, playerModel);
            break;
        case ("Tomato"):
            plotToPesticide.setCropInPlot(storage.getInventory().get(5));
            updateCropInPlotDatabase(plotToPesticide, playerModel);
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
     * @param plot        The plotModel that contains the plotIdentifier.
     * @param playerModel The playerModel containing the player information.
     */
    public void updatePlotMaturity(PlotModel plot, PlayerModel playerModel) {
        playerPlotService.adjustPlotMaturity(plot.getPlotIdentifier(),
                playerModel.getPlayerSettings().getPlayerName());
    }

    /**
     * Method that sets the plot to a new stage.
     *
     * @param playerModel The playerModel containing the player information.
     * @param plot        The plot model that contains the updated information.
     */
    public void updatePlotStage(PlotModel plot, PlayerModel playerModel) {
        playerPlotService.updatePlotStage(plot.getPlotIdentifier(),
                plot.getPlotStage(), playerModel.getPlayerSettings().getPlayerName());
    }

    /**
     * Updates the days of the plot.
     *
     * @param plot        The plot that contains the updated information.
     * @param playerModel The playerModel containing the player information.
     */
    public void updatePlotDaysDatabase(PlotModel plot, PlayerModel playerModel) {
        playerPlotService.adjustPlotDays(plot.getDaysOld(), plot.getPlotIdentifier(),
                playerModel.getPlayerSettings().getPlayerName());
    }

    /**
     * Updates the fertilizer level of the plot.
     *
     * @param plot        The plot that contains the updated information.
     * @param playerModel The playerModel containing the player information.
     */
    public void updatePlotFertilizerDatabase(PlotModel plot, PlayerModel playerModel) {
        playerPlotService.adjustPlotFertilizer(plot.getFertilizerLevel(),
                plot.getPlotIdentifier(), playerModel.getPlayerSettings().getPlayerName());

    }

    /**
     * Gets the fertilizer level of the plot.
     *
     * @param plot        The plot that contains the updated information.
     * @param playerModel The playerModel containing the player information.
     */
    public void getPlotFertilizerDatabase(PlotModel plot, PlayerModel playerModel) {
        playerPlotService.queryPlotFertilizer(plot.getPlotIdentifier(),
                playerModel.getPlayerSettings().getPlayerName());
    }

    /**
     * Updates the crop in the plot database.
     *
     * @param playerModel The player model that contains the player information.
     * @param plotModel   The plotmodel that contains the new crop.
     */
    public void updateCropInPlotDatabase(PlotModel plotModel, PlayerModel playerModel) {
        playerPlotService.adjustCropInPlot(plotModel.getCropInPlot().getCropName(),
                plotModel.getPlotIdentifier(),
                playerModel.getPlayerSettings().getPlayerName());
    }
}