package views.farmView;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import models.CropModel;
import models.PlotTemplate;
import viewmodels.PlayerViewModel;
import viewmodels.PlotViewModel;

public class PlantView {
    private final Image seedImg = new Image("@../../dependencies/images/Seed.png",
            150.0, 150.0, true, false);
    private final Image cornNameImg = new Image("@../../dependencies/images/Crop_Bar_Corn.png",
            150.0, 150.0, true, false);
    private final Image potatoNameImg = new Image("@../../dependencies/images/Crop_Bar_Potato.png",
            150.0, 150.0, true, false);
    private final Image tomatoNameImg = new Image("@../../dependencies/images/Crop_Bar_Tomato.png",
            150.0, 150.0, true, false);
    private final Image emptyNameImg = new Image("@../../dependencies/images/Crop_Bar_Empty.png",
            150.0, 150.0, true, false);

    private PlayerViewModel playerViewModel;
    private PlotViewModel plotViewModel;

    public PlantView(PlayerViewModel playerViewModel, PlotViewModel plotViewModel) {
        this.playerViewModel = playerViewModel;
        this.plotViewModel = plotViewModel;
    }

    public void plantCrop(ObservableList<PlotTemplate> plotsObservableList, int plotNum, CropModel crop) {
        plotsObservableList.get(plotNum).getPlotModel().setWaterValue(3);
        plotsObservableList.get(plotNum).getPlotModel().setDaysOld(0);
        plotsObservableList.get(plotNum).setWaterValue("03");
        plotsObservableList.get(plotNum).getWaterValueText().setVisible(true);
        this.plotViewModel.plantPlot(plotsObservableList.get(plotNum).getPlotModel(), crop);
        plotViewModel.updatePlotStage(plotsObservableList.get(plotNum).getPlotModel(),
                playerViewModel.getPlayer());
        plotsObservableList.get(plotNum).setPlotImageView(seedImg);
        plotsObservableList.get(plotNum).setNameImageView(chooseCropImage(crop));
    }

    private Image chooseCropImage(CropModel crop) {
        switch (crop.getCropName()) {
            case "Corn":
                return this.cornNameImg;
            case "Potato":
                return this.potatoNameImg;
            case "Tomato":
                return this.tomatoNameImg;
            default:
                return this.emptyNameImg;
        }
    }
}
