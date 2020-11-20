package views.farmView;

import javafx.collections.ObservableList;
import javafx.scene.text.Text;
import models.PlotTemplate;
import viewmodels.PlayerViewModel;
import viewmodels.PlotViewModel;

public class WaterView {
    private PlayerViewModel playerViewModel;
    private PlotViewModel plotViewModel;
    private MaturityView maturityView;

    public WaterView(PlayerViewModel playerViewModel, PlotViewModel plotViewModel, MaturityView maturityView) {
        this.playerViewModel = playerViewModel;
        this.plotViewModel = plotViewModel;
        this.maturityView = maturityView;
    }

    public void waterCrop(ObservableList<PlotTemplate> plotsObservableList, int plotNum, Text waterCounter) {
        if (plotsObservableList.get(plotNum).getPlotModel() != null) {
            int waterValue = plotsObservableList.get(plotNum).getPlotModel().getWaterValue();
            if (waterValue > 0 && waterValue <= 6) {
                this.plotViewModel.waterPlot(plotsObservableList.get(plotNum).getPlotModel());
                plotsObservableList.get(plotNum).setWaterValue(
                        doubleDigitString(plotsObservableList.get(plotNum).getPlotModel().getWaterValue()));
                maturityView.checkMaturity(plotsObservableList, plotNum, waterCounter);
                playerViewModel.increasePlayerWaterCounter();
                System.out.println("The max water is " + playerViewModel.getPlayer().getMaxWateringPerDay());
                System.out.println("The current water counter is " + playerViewModel.getPlayer().getCurrentWaterCounter());
            }
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
