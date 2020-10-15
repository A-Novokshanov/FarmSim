package tests.milestone3;

import models.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Matthew Farias mfarias6@gatech.edu
 * @version 1.0
 */

public class PlotModelMatthewFarias {
    private PlotModel plotModelTest;
    private CropModel corn;
    private CropModel potato;
    private int daysOld;

    @Before
    public void setUp() {
        corn = new CropModel("Corn", 1, 100.0);
        potato = new CropModel("Potato", 7, 92.0);
        daysOld = 3;
        plotModelTest = new PlotModel(corn, daysOld);
    }

    @Test
    public void testGetCropInPlot() {
        assertEquals(plotModelTest.getCropInPlot(), corn);
        assertEquals(plotModelTest.getCropInPlot().getCropName(), "Corn");
        assertEquals(plotModelTest.getCropInPlot().getCropQuantity(), 1);
        assertEquals(plotModelTest.getCropInPlot().getCropValue(),  100.0, 0.0);
    }

    @Test
    public void testSetCropInPlot() {
        plotModelTest.setCropInPlot(potato);
        assertEquals(plotModelTest.getCropInPlot().getCropName(), "Potato");
        assertEquals(plotModelTest.getCropInPlot().getCropQuantity(), 7);
        assertEquals(plotModelTest.getCropInPlot().getCropValue(),  92.0, 0.0);
    }

    @Test
    public void testGetDaysOld() {
        assertEquals(plotModelTest.getDaysOld(), 3);
    }

    @Test
    public void testSetDaysOld() {
        plotModelTest.setDaysOld(6);
        assertEquals(plotModelTest.getDaysOld(), 6);
    }
}
