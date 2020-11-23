package views.farmView;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import models.PlotTemplate;
import viewmodels.PlayerViewModel;
import viewmodels.PlotViewModel;

public class HarvestView {
    private final Image dirtImg = new Image("@../../dependencies/images/Dirt.png",
            150.0, 150.0, true, false);
    private final Image emptyNameImg = new Image("@../../dependencies/images/Crop_Bar_Empty.png",
            150.0, 50.0, true, false);

    private PlayerViewModel playerViewModel;
    private PlotViewModel plotViewModel;

    public HarvestView(PlayerViewModel playerViewModel, PlotViewModel plotViewModel) {
        this.playerViewModel = playerViewModel;
        this.plotViewModel = plotViewModel;
    }

    public void harvestCrop(ObservableList<PlotTemplate> plotsObservableList, int plotNum) {
        this.plotViewModel.harvestPlot(plotsObservableList.get(plotNum).
                getPlotModel(), this.playerViewModel);
        plotsObservableList.get(plotNum).getPlotModel().setWaterValue(0);
        plotsObservableList.get(plotNum).getPlotModel().setDaysOld(0);
        plotsObservableList.get(plotNum).setPlotImageView(dirtImg);
        plotsObservableList.get(plotNum).setNameImageView(emptyNameImg);
        plotsObservableList.get(plotNum).getPlotModel().setStage(null);
        plotsObservableList.get(plotNum).getPlotModel().setCropInPlot(null);
        this.plotViewModel.updatePlotStage(plotsObservableList.get(plotNum).getPlotModel(),
                playerViewModel.getPlayer());
        this.plotViewModel.updatePlotDaysDatabase(plotsObservableList.get(plotNum).getPlotModel(),
                playerViewModel.getPlayer());
        this.plotViewModel.updateCropInPlotDatabase(plotsObservableList.get(plotNum).getPlotModel(),
                playerViewModel.getPlayer());
        plotsObservableList.get(plotNum).setWaterValueVisibility(false);
        playerViewModel.increasePlayerHarvestCounter();
    }
}
