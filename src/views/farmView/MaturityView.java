package views.farmView;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import models.PlotTemplate;
import viewmodels.PlayerViewModel;
import viewmodels.PlotViewModel;

public class MaturityView {
    private final Image dirtImg = new Image("@../../dependencies/images/Dirt.png",
            400.0, 300.0, true, false);
    private final Image seedImg = new Image("@../../dependencies/images/Seed.png",
            400.0, 300.0, true, false);
    private final Image immature1Img = new Image("@../../dependencies/images/Immature_1.png",
            400.0, 300.0, true, false);
    private final Image immature2Img = new Image("@../../dependencies/images/Immature_2.png",
            400.0, 300.0, true, false);
    private final Image matureImg = new Image("@../../dependencies/images/Mature.png",
            400.0, 300.0, true, false);
    private final Image witheredImg = new Image("@../../dependencies/images/Withered.png",
            400.0, 300.0, true, false);

    private PlayerViewModel playerViewModel;
    private PlotViewModel plotViewModel;

    public MaturityView(PlayerViewModel playerViewModel, PlotViewModel plotViewModel) {
        this.playerViewModel = playerViewModel;
        this.plotViewModel = plotViewModel;
    }

    public void checkMaturity(ObservableList<PlotTemplate> plotsObservableList, int plotNum, Text waterCounter) {
        int waterValue = plotsObservableList.get(plotNum).getPlotModel().getWaterValue();
        if (plotsObservableList.get(plotNum).getPlotModel().getCropInPlot() != null) {
            if (waterValue > 6 || waterValue <= 0) {
                plotsObservableList.get(plotNum).setPlotImage(witheredImg);
                plotsObservableList.get(plotNum).getPlotModel().setStage("Withered");
                plotViewModel.updatePlotStage(
                        plotsObservableList.get(plotNum).getPlotModel(), playerViewModel.getPlayer());
                waterCounter.setVisible(false);
            } else if (plotsObservableList.get(plotNum).getPlotModel().getDaysOld() < 2) {
                plotsObservableList.get(plotNum).setPlotImage(seedImg);
                plotsObservableList.get(plotNum).getPlotModel().setStage("Seed");
                plotViewModel.updatePlotStage(
                        plotsObservableList.get(plotNum).getPlotModel(), playerViewModel.getPlayer());
            } else if (plotsObservableList.get(plotNum).getPlotModel().getDaysOld() < 6) {
                plotsObservableList.get(plotNum).setPlotImage(immature1Img);
                plotsObservableList.get(plotNum).getPlotModel().setStage("Immature 1");
                plotViewModel.updatePlotStage(
                        plotsObservableList.get(plotNum).getPlotModel(), playerViewModel.getPlayer());
            } else if (plotsObservableList.get(plotNum).getPlotModel().getDaysOld() < 10) {
                plotsObservableList.get(plotNum).setPlotImage(immature2Img);
                plotsObservableList.get(plotNum).getPlotModel().setStage("Immature 2");
                plotViewModel.updatePlotStage(
                        plotsObservableList.get(plotNum).getPlotModel(), playerViewModel.getPlayer());
            } else {
                plotsObservableList.get(plotNum).setPlotImage(matureImg);
                plotsObservableList.get(plotNum).getPlotModel().setStage("Mature");
                plotViewModel.updatePlotStage(
                        plotsObservableList.get(plotNum).getPlotModel(), playerViewModel.getPlayer());
            }
        } else {
            plotsObservableList.get(plotNum).setPlotImage(dirtImg);
            plotsObservableList.get(plotNum).getPlotModel().setStage(null);
            waterCounter.setVisible(false);
            //switchPlantHarvest(plotImg, plotNum, false);
        }
    }
}
