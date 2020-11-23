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

    public boolean plantCrop(ObservableList<PlotTemplate> plotsObservableList,
                             int plotNum, CropModel crop) {
        if (playerViewModel.getPlayer().getUserStorage().
                getInventory().get(cropNumber(crop)).getCropQuantity() > 2) {
            this.plotViewModel.plantPlot(plotsObservableList.get(plotNum).getPlotModel(), crop);
            plotsObservableList.get(plotNum).getPlotModel().setWaterValue(3);
            plotsObservableList.get(plotNum).getPlotModel().setDaysOld(0);
            plotsObservableList.get(plotNum).setWaterValue("03");
            plotsObservableList.get(plotNum).getWaterValueText().setVisible(true);
            plotsObservableList.get(plotNum).getPlotModel().setStage("Seed");
            plotViewModel.updatePlotStage(plotsObservableList.get(plotNum).getPlotModel(),
                    playerViewModel.getPlayer());
            plotViewModel.updateCropInPlotDatabase(plotsObservableList.get(plotNum).getPlotModel(),
                    playerViewModel.getPlayer());
            plotViewModel.updateWaterValue(plotsObservableList.get(plotNum).getWaterValue(),
                    plotsObservableList.get(plotNum).getPlotModel().getPlotIdentifier());
            plotViewModel.updatePlotDaysDatabase(plotsObservableList.get(plotNum).getPlotModel(),
                    playerViewModel.getPlayer());
            plotsObservableList.get(plotNum).setPlotImageView(seedImg);
            plotsObservableList.get(plotNum).setNameImageView(chooseCropImage(crop));
            return true;
        } else {
            return false;
        }
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

    private int cropNumber(CropModel cropModel) {
        switch (cropModel.getCropName()) {
        case "Corn":
            return 0;
        case "Potato":
            return 1;
        case "Tomato":
            return 2;
        default:
            return -1;
        }
    }
}
