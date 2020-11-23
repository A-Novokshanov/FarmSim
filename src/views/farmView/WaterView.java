package views.farmView;

import javafx.collections.ObservableList;
import models.PlotTemplate;
import viewmodels.PlayerViewModel;
import viewmodels.PlotViewModel;

public class WaterView {
    private PlayerViewModel playerViewModel;
    private PlotViewModel plotViewModel;
    private MaturityView maturityView;

    public WaterView(PlayerViewModel playerViewModel, PlotViewModel plotViewModel,
                     MaturityView maturityView) {
        this.playerViewModel = playerViewModel;
        this.plotViewModel = plotViewModel;
        this.maturityView = maturityView;
    }

    public boolean waterCrop(ObservableList<PlotTemplate> plotsObservableList, int plotNum) {
        if (plotsObservableList.get(plotNum).getPlotModel() != null) {
            int waterValue = plotsObservableList.get(plotNum).getPlotModel().getWaterValue();
            if (waterValue > 0 && waterValue <= 6) {
                this.plotViewModel.waterPlot(plotsObservableList.get(plotNum).getPlotModel());
                plotsObservableList.get(plotNum).setWaterValue(doubleDigitString(
                        plotsObservableList.get(plotNum).getPlotModel().getWaterValue()));
                maturityView.checkMaturity(plotsObservableList, plotNum);
                plotViewModel.updateWaterValue(plotsObservableList.get(plotNum).getWaterValue(),
                        plotsObservableList.get(plotNum).getPlotModel().getPlotIdentifier());
                playerViewModel.increasePlayerWaterCounter();
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
