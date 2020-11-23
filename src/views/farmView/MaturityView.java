package views.farmView;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import models.PlotModel;
import models.PlotTemplate;
import viewmodels.PlayerViewModel;
import viewmodels.PlotViewModel;

public class MaturityView {
    private final Image dirtImg = new Image("@../../dependencies/images/Dirt.png",
            150.0, 150.0, true, false);
    private final Image seedImg = new Image("@../../dependencies/images/Seed.png",
            150.0, 150.0, true, false);
    private final Image immature1Img = new Image("@../../dependencies/images/Immature_1.png",
            150.0, 150.0, true, false);
    private final Image immature2Img = new Image("@../../dependencies/images/Immature_2.png",
            150.0, 150.0, true, false);
    private final Image matureImg = new Image("@../../dependencies/images/Mature.png",
            150.0, 150.0, true, false);
    private final Image witheredImg = new Image("@../../dependencies/images/Withered.png",
            150.0, 150.0, true, false);

    private PlayerViewModel playerViewModel;
    private PlotViewModel plotViewModel;

    public MaturityView(PlayerViewModel playerViewModel, PlotViewModel plotViewModel) {
        this.playerViewModel = playerViewModel;
        this.plotViewModel = plotViewModel;
    }

    public void checkMaturity(ObservableList<PlotTemplate> plotsObservableList, int plotNum) {
        int waterValue = plotsObservableList.get(plotNum).getPlotModel().getWaterValue();
        if (plotsObservableList.get(plotNum).getPlotModel().getCropInPlot() != null) {
            if (waterValue > 6 || waterValue <= 0) {
                plotsObservableList.get(plotNum).setPlotImageView(witheredImg);
                plotsObservableList.get(plotNum).getPlotModel().setStage("Withered");
                plotViewModel.updatePlotStage(
                        plotsObservableList.get(plotNum).
                                getPlotModel(), playerViewModel.getPlayer());
                plotsObservableList.get(plotNum).getWaterValueText().setVisible(false);
            } else if (plotsObservableList.get(plotNum).
                    getPlotModel().getDaysOld() < 2) {
                plotsObservableList.get(plotNum).setPlotImageView(seedImg);
                plotsObservableList.get(plotNum).getPlotModel().setStage("Seed");
                plotViewModel.updatePlotStage(
                        plotsObservableList.get(plotNum).getPlotModel(),
                        playerViewModel.getPlayer());
            } else if (plotsObservableList.get(plotNum).getPlotModel().getDaysOld() < 6) {
                plotsObservableList.get(plotNum).setPlotImageView(immature1Img);
                plotsObservableList.get(plotNum).
                        getPlotModel().setStage("Immature 1");
                plotViewModel.updatePlotStage(
                        plotsObservableList.get(plotNum).getPlotModel(),
                        playerViewModel.getPlayer());
            } else if (plotsObservableList.get(plotNum).getPlotModel().getDaysOld() < 10) {
                plotsObservableList.get(plotNum).setPlotImageView(immature2Img);
                plotsObservableList.get(plotNum).
                        getPlotModel().setStage("Immature 2");
                plotViewModel.updatePlotStage(
                        plotsObservableList.get(plotNum).
                                getPlotModel(), playerViewModel.getPlayer());
            } else {
                plotsObservableList.get(plotNum).setPlotImageView(matureImg);
                plotsObservableList.get(plotNum).
                        getPlotModel().setStage("Mature");
                plotViewModel.updatePlotStage(
                        plotsObservableList.get(plotNum).
                                getPlotModel(), playerViewModel.getPlayer());
            }
        } else {
            plotsObservableList.get(plotNum).setPlotImageView(dirtImg);
            plotsObservableList.get(plotNum).getPlotModel().setStage(null);
            plotsObservableList.get(plotNum).getWaterValueText().setVisible(false);
            plotViewModel.updatePlotStage(
                    plotsObservableList.get(plotNum).getPlotModel(), playerViewModel.getPlayer());
        }
    }

    public Image setInitialMaturity(PlotModel plotModel) {
        if (plotModel.getDaysOld() < 2) {
            plotModel.setStage("Seed");
            return seedImg;
        } else if (plotModel.getDaysOld() < 6) {
            plotModel.setStage("Immature 1");
            return immature1Img;
        } else if (plotModel.getDaysOld() < 10) {
            plotModel.setStage("Immature 2");
            return immature2Img;
        } else {
            plotModel.setStage("Mature");
            return matureImg;
        }
    }

    public Image getMaturityImage(PlotModel plotModel) {
        if (plotModel.getStage() == null) {
            return dirtImg;
        } else {
            if (plotModel.getStage().equals("Withered")) {
                return witheredImg;
            } else if (plotModel.getStage().equals("Seed")) {
                return seedImg;
            } else if (plotModel.getStage().equals("Immature 1")) {
                return immature1Img;
            } else if (plotModel.getStage().equals("Immature 2")) {
                return immature2Img;
            } else {
                return matureImg;
            }
        }
    }
}
