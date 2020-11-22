package views.farmView;

import javafx.collections.ObservableList;
import models.PlotTemplate;
import viewmodels.PlayerViewModel;
import viewmodels.PlotViewModel;

public class FertilizeView {
    private PlayerViewModel playerViewModel;
    private PlotViewModel plotViewModel;

    public FertilizeView(PlayerViewModel playerViewModel, PlotViewModel plotViewModel) {
        this.playerViewModel = playerViewModel;
        this.plotViewModel = plotViewModel;
    }

    public boolean fertilizePlot(ObservableList<PlotTemplate> plotsObservableList, int plotNum) {
        if (plotsObservableList.get(plotNum) != null) {
            if (plotsObservableList.get(plotNum).getPlotModel().getFertilizerLevel() < 9
                    && playerViewModel.getPlayer().getUserStorage().getTotalFertilizer() > 1) {
                this.plotViewModel.fertilizePlot(plotsObservableList.get(plotNum).getPlotModel());
                playerViewModel.getPlayer().getUserStorage().setTotalFertilizer(
                        playerViewModel.getPlayer().getUserStorage().getTotalFertilizer() - 1);
                plotsObservableList.get(plotNum).setFertilizerValue(doubleDigitString(
                        plotsObservableList.get(plotNum).getPlotModel().getFertilizerLevel()));
                plotViewModel.updatePlotFertilizerDatabase(plotsObservableList.get(plotNum).getPlotModel(),
                        playerViewModel.getPlayer());
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private String doubleDigitString(int num) {
        String str;
        if (num < 10) {
            str = "0" + num;
        } else {
            str = String.valueOf(num);
        }
        return str;
    }
}
