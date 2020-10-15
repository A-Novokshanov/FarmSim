package junits.milestone3;

import models.CropModel;
import models.PlotModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import viewmodels.PlotViewModel;

/**
 * @author Shaun Jacob sjacob31@gatech.edu
 * @version 1.0
 */
public class PlotViewModelTest {
    private PlotModel harvestedPlot;
    private CropModel cropInPlot;
    private PlotViewModel plotViewModel;

    @Before
    public void setUp() {
        cropInPlot = new CropModel("Potato", 2, 1.50);
        harvestedPlot = new PlotModel(cropInPlot, 4);
        plotViewModel = new PlotViewModel();
    }

    @Test
    public void testPopulatePlot() {
        PlotModel postPop = plotViewModel.populatePlot(cropInPlot);
        assertEquals(postPop.getCropInPlot(), cropInPlot);
    }

    @Test
    public void testIncrementPlotDaysOld() {
        plotViewModel.incrementPlotDaysOld(harvestedPlot);
        assertEquals(5, harvestedPlot.getDaysOld());
    }
}
