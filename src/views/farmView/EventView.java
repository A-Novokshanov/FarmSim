package views.farmView;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import models.PlotTemplate;
import viewmodels.EventViewModel;
import viewmodels.PlayerViewModel;
import viewmodels.PlotViewModel;

public class EventView {
    private final Image dirtImg = new Image("@../../dependencies/images/Dirt.png",
            150.0, 150.0, true, false);
    private final Image emptyNameImg = new Image("@../../dependencies/images/Crop_Bar_Empty.png",
            150.0, 50.0, true, false);
    private PlayerViewModel playerViewModel;
    private PlotViewModel plotViewModel;
    private EventViewModel eventViewModel;
    private MaturityView maturityView;

    public EventView(PlayerViewModel playerViewModel, PlotViewModel plotViewModel,
                     EventViewModel eventViewModel, MaturityView maturityView) {
        this.playerViewModel = playerViewModel;
        this.plotViewModel = plotViewModel;
        this.eventViewModel = eventViewModel;
        this.maturityView = maturityView;
    }

    public String eventRoll(ObservableList<PlotTemplate> plotsObservableList) {
        switch (eventViewModel.chooseEvent()) {
            case 0:
                int waterRainChange = eventViewModel.performRainEvent();
                for (int i = 0; i < plotsObservableList.size(); i++) {
                    if (plotsObservableList.get(i).getPlotModel().getCropInPlot() != null) {
                        if (plotsObservableList.get(i).getWaterValue() > 0
                                && plotsObservableList.get(i).getWaterValue() <= 6) {
                            int newWaterValue = plotsObservableList.get(i).getWaterValue() + waterRainChange;
                            plotsObservableList.get(i).getPlotModel().setWaterValue(newWaterValue);
                            plotsObservableList.get(i).setWaterValue(doubleDigitString(
                                    plotsObservableList.get(i).getPlotModel().getWaterValue()));
                            plotViewModel.updateWaterValue(plotsObservableList.get(i).getWaterValue(),
                                    plotsObservableList.get(i).getPlotModel().getPlotIdentifier());
                            if (newWaterValue > 6) {
                                maturityView.checkMaturity(plotsObservableList, i);
                            }
                        }
                    }
                }
                return "Rain";
            case 1:
                int waterDroughtChange = eventViewModel.performDroughtEvent();
                for (int i = 0; i < plotsObservableList.size(); i++) {
                    if (plotsObservableList.get(i).getPlotModel().getCropInPlot() != null) {
                        if (plotsObservableList.get(i).getWaterValue() > 0
                                && plotsObservableList.get(i).getWaterValue() <= 6) {
                            int newWaterValue =
                                    Math.max(plotsObservableList.get(i).getWaterValue() - waterDroughtChange, 0);
                            plotsObservableList.get(i).getPlotModel().setWaterValue(newWaterValue);
                            plotsObservableList.get(i).setWaterValue(doubleDigitString(
                                    plotsObservableList.get(i).getPlotModel().getWaterValue()));
                            plotViewModel.updateWaterValue(plotsObservableList.get(i).getWaterValue(),
                                    plotsObservableList.get(i).getPlotModel().getPlotIdentifier());
                            if (newWaterValue <= 0) {
                                maturityView.checkMaturity(plotsObservableList, i);
                            }
                        }
                    }
                }
                return "Drought";
            case 2:
                for (PlotTemplate plotTemplate : plotsObservableList) {
                    if (plotTemplate.getPlotModel().getCropInPlot() != null) {
                        int n = eventViewModel.performLocustEvent(plotTemplate.getPlotModel());
                        if (n == 1) {
                            plotTemplate.getPlotModel().setStage(null);
                            plotTemplate.getPlotModel().setCropInPlot(null);
                            plotTemplate.getPlotModel().setWaterValue(0);
                            plotTemplate.getPlotModel().setDaysOld(0);
                            plotTemplate.setPlotImageView(dirtImg);
                            plotTemplate.setNameImageView(emptyNameImg);
                            plotTemplate.getWaterValueText().setVisible(false);
                            this.plotViewModel.updatePlotStage(plotTemplate.getPlotModel(),
                                    playerViewModel.getPlayer());
                            this.plotViewModel.updateWaterValue(plotTemplate.getWaterValue(),
                                    plotTemplate.getPlotModel().getPlotIdentifier());
                            this.plotViewModel.updatePlotDaysDatabase(plotTemplate.getPlotModel(),
                                    playerViewModel.getPlayer());
                            this.plotViewModel.updateCropInPlotDatabase(plotTemplate.getPlotModel(),
                                    playerViewModel.getPlayer());
                        }
                    }
                }
                return "Locusts";
            default:
                return "Normal";
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
