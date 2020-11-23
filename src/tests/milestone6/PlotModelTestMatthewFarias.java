package tests.milestone6;

import models.CropModel;
import models.PlotModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlotModelTestMatthewFarias {
    private PlotModel plotModel;
    private CropModel newCropInPlot;
    private int daysOld;

    @Before
    public void setUp() {
        newCropInPlot = new CropModel("Corn", 13, 1.4);
        daysOld = 10;
        plotModel = new PlotModel(newCropInPlot, daysOld);
    }

    /**
     * Test for the getStage method
     */
    @Test
    public void testSetStage() {
        plotModel.setStage("Stage");
        assertEquals("Stage", plotModel.getStage());
    }

    /**
     * Test for the setCropInPlot method
     */
    @Test
    public void testGetCropInPlot() {
        assertEquals(newCropInPlot, plotModel.getCropInPlot());
    }

    /**
     * Test for the getCropInPlot method
     */
    @Test
    public void testSetCropInPlot() {
        newCropInPlot = new CropModel("New CropName", 24, 3.5);
        plotModel.setCropInPlot(newCropInPlot);
    }

    /**
     * Test for the getFertilizerLevel method
     */
    @Test
    public void testGetPlotFertilizerLevel() {
        assertEquals(plotModel.getFertilizerLevel(), 0);
    }

    /**
     * Test for the setCropFertilizerLevel method
     */
    @Test
    public void testSetPlotFertilizerLevel() {
        plotModel.setFertilizerLevel(10);
        assertEquals(plotModel.getFertilizerLevel(), 10);
    }

    /**
     * Test for the getWaterValue method
     */
    @Test
    public void testGetWater() {
        assertEquals(plotModel.getWaterValue(), 3);
    }

    /**
     * Test for the setWaterValue method
     */
    @Test
    public void testSetWater() {
        plotModel.setWaterValue(19);
        assertEquals(plotModel.getWaterValue(), 19);
    }

    /**
     * Test for the getDaysSinceWater method
     */
    @Test
    public void testGetDaysSinceWater() {
        assertEquals(0, plotModel.getDaysSinceWater());
    }

    /**
     * Test for the setDaysSinceWater method
     */
    @Test
    public void testSetDaysSinceWater() {
        int days = 34;
        plotModel.setDaysSinceWater(days);
        assertEquals(34, plotModel.getDaysSinceWater());
    }

    /**
     * Test for the getWaterValue method
     */
    @Test
    public void testGetWaterValue() {
        assertEquals(3, plotModel.getWaterValue());
    }

    /**
     * Test for the setWaterValue method
     */
    @Test
    public void testSetWaterValue() {
        plotModel.setWaterValue(34);
        assertEquals(34, plotModel.getWaterValue());
    }

    /**
     * Test for the getDaysOld method
     */
    @Test
    public void testGetDaysOld() {
        assertEquals(daysOld, plotModel.getDaysOld());
    }

    /**
     * Test for the setDaysOld method
     */
    @Test
    public void testSetDaysOld() {
        plotModel.setDaysOld(23);
        assertEquals(23, plotModel.getDaysOld());
    }

    /**
     * Test for the setPrice method
     */
    @Test
    public void testSetPlotPrice() {
        plotModel.setPrice(34);
        assertEquals(34, plotModel.getPrice());
    }

    /**
     * Test for the getPrice method
     */
    @Test
    public void testGetPlotPrice() {
        assertEquals(0, plotModel.getPrice());
    }
}
