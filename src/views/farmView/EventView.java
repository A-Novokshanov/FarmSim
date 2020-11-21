package views.farmView;

import viewmodels.PlayerViewModel;
import viewmodels.PlotViewModel;

public class EventView {
    private PlayerViewModel playerViewModel;
    private PlotViewModel plotViewModel;

    public EventView(PlayerViewModel playerViewModel, PlotViewModel plotViewModel) {
        this.playerViewModel = playerViewModel;
        this.plotViewModel = plotViewModel;
    }
}
