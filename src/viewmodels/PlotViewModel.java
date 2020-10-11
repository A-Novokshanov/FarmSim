package viewmodels;

import models.CropModel;
import models.PlotModel;

import java.util.Random;

/**
 * This view-model class controls the logic and flow of harvesting from the plot.
 *
 * @author Aditya Varun Pratap
 * @version 1.0
 */
public class PlotViewModel {

    /**
     * Harvest a plot and store the crops in storage/inventory.
     *
     * @param harvestedPlot The plot to be harvested.
     * @param player        The player harvesting the plot.
     */
    public void harvestPlot(PlotModel harvestedPlot, PlayerViewModel player) {
        StorageViewModel storageViewModel = new StorageViewModel(player);
        if (harvestedPlot == null) {
            throw new IllegalArgumentException("The plot is null");
        }
        if (harvestedPlot.getDaysOld() >= 10 && player.getPlayer().getUserStorage().getInventorySize()
                >= harvestedPlot.getCropInPlot().getCropQuantity()) {

            storageViewModel.addToInventory(harvestedPlot.getCropInPlot(),
                    harvestedPlot.getCropInPlot().getCropQuantity());
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
        PlotModel plot = new PlotModel(cropInPlot, daysOld);
        return plot;
    }
}
