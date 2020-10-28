package tests.milestone4;

import models.CropModel;
import models.PlotModel;
import org.junit.Before;
import org.junit.Test;

import java.beans.BeanProperty;

import static org.junit.Assert.assertEquals;

/**
 * @author Shaun Jacob sjacob31@gatech.edu
 * @version 1.1
 */

@SuppressWarnings("FieldCanBeLocal")
public class PlotModelTest {
    private PlotModel plotModel;
    private CropModel corn;
    private CropModel potato;
    private int daysOld;

    @Before
    public void setUp() {
        corn = new CropModel("Corn", 2, 20);
        potato = new CropModel("Potato", 12, 67);
        daysOld = 3;
        plotModel = new PlotModel(corn, daysOld);
    }

    /**
     * Testing the PlotModel.getCropInPlot() method
     */
    @Test
    public void testGetCropInPlot() {
        assertEquals(plotModel.getCropInPlot(), corn);
        assertEquals("Corn", plotModel.getCropInPlot().getCropName());
        assertEquals(2, plotModel.getCropInPlot().getCropQuantity());
        assertEquals(20, plotModel.getCropInPlot().getCropValue(), 0.0);
    }

    /**
     * Testing the PlotModel.setCropInPlot() method
     */
    @Test
    public void testSetCropInPlot() {
        plotModel.setCropInPlot(potato);
        assertEquals("Potato", plotModel.getCropInPlot().getCropName());
        assertEquals(12, plotModel.getCropInPlot().getCropQuantity());
        assertEquals(67, plotModel.getCropInPlot().getCropValue(), 0.0);
    }

    /**
     * Testing the PlotModel.getDaysOld() method
     */
    @Test
    public void testGetDaysOld() {
        assertEquals(3, plotModel.getDaysOld());
    }

    /**
     * Testing the PlotModel.setDaysOld() method
     */
    @Test
    public void testSetDaysOld() {
        plotModel.setDaysOld(6);
        assertEquals(6, plotModel.getDaysOld());
    }

    /**
     * Testing the PlotModel.getDaysSinceWater() method
     */
    @Test
    public void testDaysSinceWater() {
        assertEquals(0, plotModel.getDaysSinceWater());
    }

    /**
     * Testing the PlotModel.setDaysSinceWater() method
     */
    @Test
    public void testSetDaysSinceWater() {
        plotModel.setDaysSinceWater(3);
        assertEquals(3, plotModel.getDaysSinceWater());
    }
}
