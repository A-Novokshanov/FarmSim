package views.farmView;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import models.PlotModel;
import models.PlotTemplate;
import viewmodels.PlayerViewModel;
import viewmodels.PlotViewModel;

public class HarvestView {
    private final Image dirtImg = new Image("@../../dependencies/images/Dirt.png",
            400.0, 300.0, true, false);
    private final Image emptyNameImg = new Image("@../../dependencies/images/Crop_Bar_Empty.png",
            400.0, 300.0, true, false);

    private PlayerViewModel playerViewModel;
    private PlotViewModel plotViewModel;

    public HarvestView(PlayerViewModel playerViewModel, PlotViewModel plotViewModel) {
        this.playerViewModel = playerViewModel;
        this.plotViewModel = plotViewModel;
    }

    public void harvestCrop(ObservableList<PlotTemplate> plotsObservableList, int plotNum, Text waterCounter) {
        PlotModel harvestedPlot = plotsObservableList.get(plotNum).getPlotModel();
        if (harvestedPlot.getDaysOld() >= 10
                || harvestedPlot.getWaterValue() > 6 || harvestedPlot.getWaterValue() <= 0) {
            this.plotViewModel.harvestPlot(harvestedPlot, this.playerViewModel);
            harvestedPlot.setWaterValue(0);
            harvestedPlot.setDaysOld(0);
            plotsObservableList.get(plotNum).setPlotModel(harvestedPlot);
            plotsObservableList.get(plotNum).setPlotImage(dirtImg);
            plotsObservableList.get(plotNum).setImageName(emptyNameImg);
            plotsObservableList.get(plotNum).getPlotModel().setStage(null);
            this.plotViewModel.updatePlotStage(plotsObservableList.get(plotNum).getPlotModel(),
                    playerViewModel.getPlayer());
            this.plotViewModel.updatePlotDaysDatabase(plotsObservableList.get(plotNum).getPlotModel(),
                    playerViewModel.getPlayer());
            //switchPlantHarvest(harvestedPlotImage, plotNum, false);
            waterCounter.setVisible(false);
            playerViewModel.increasePlayerHarvestCounter();
            System.out.println("The current player max harvest is " + playerViewModel.getPlayer().getMaxHarvestsPerDay());
            System.out.println("The current player harvest counter is " + playerViewModel.getPlayer().getCurrentHarvestCounter());
        }
    }
}
