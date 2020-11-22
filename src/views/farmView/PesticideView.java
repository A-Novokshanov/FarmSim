package views.farmView;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import models.CropModel;
import models.PlotTemplate;
import viewmodels.PlayerViewModel;
import viewmodels.PlotViewModel;

public class PesticideView {
    private final Image cornPesticideNameImg = new Image(
            "@../../dependencies/images/Crop_Bar_Corn_Pesticide.png",
            150.0, 50.0, true, false);
    private final Image potatoPesticideNameImg = new Image(
            "@../../dependencies/images/Crop_Bar_Potato_Pesticide.png",
            150.0, 50.0, true, false);
    private final Image tomatoPesticideNameImg = new Image(
            "@../../dependencies/images/Crop_Bar_Tomato_Pesticide.png",
            150.0, 50.0, true, false);
    private final Image emptyNameImg = new Image("@../../dependencies/images/Crop_Bar_Empty.png",
            150.0, 50.0, true, false);

    private PlayerViewModel playerViewModel;
    private PlotViewModel plotViewModel;

    public PesticideView(PlayerViewModel playerViewModel, PlotViewModel plotViewModel) {
        this.playerViewModel = playerViewModel;
        this.plotViewModel = plotViewModel;
    }

    public boolean pesticidePlot(ObservableList<PlotTemplate> plotsObservableList, int plotNum) {
        if (plotsObservableList.get(plotNum) != null) {
            if (plotsObservableList.get(plotNum).getPlotModel().getCropInPlot() != null
                    && !plotsObservableList.get(plotNum).getPlotModel().getCropInPlot().getHasPesticide()
                    && playerViewModel.getPlayer().getUserStorage().getTotalPesticide() > 1) {
                this.plotViewModel.pesticidePlot(plotsObservableList.get(plotNum).getPlotModel());
                playerViewModel.getPlayer().getUserStorage().setTotalPesticide(
                        playerViewModel.getPlayer().getUserStorage().getTotalPesticide() - 1);
                plotsObservableList.get(plotNum).setNameImageView(
                        chooseCropImage(plotsObservableList.get(plotNum).getPlotModel().getCropInPlot()));
                plotViewModel.updateCropInPlotDatabase(plotsObservableList.get(plotNum).getPlotModel(),
                        playerViewModel.getPlayer());
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private Image chooseCropImage(CropModel crop) {
        switch (crop.getCropName()) {
            case "Corn with Pesticide":
                return this.cornPesticideNameImg;
            case "Potato with Pesticide":
                return this.potatoPesticideNameImg;
            case "Tomato with Pesticide":
                return this.tomatoPesticideNameImg;
            default:
                return this.emptyNameImg;
        }
    }
}
