package tests.milestone5;


import models.CropModel;
import models.PlotModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlotModelTestRohanKashiviswanathan {
    private PlotModel plotModel;
    CropModel cropModel = new CropModel("corn", 2,  4);
    CropModel cropModel2 = new CropModel("tomato", 1,  3);

    @Before
    public void setUp() {
        plotModel = new PlotModel(cropModel, 0);
    }


    /**
     * Testing the crop in plot getter
     */
    @Test
    public void testCropInPlot() {
        assertEquals(plotModel.getCropInPlot(), cropModel);
    }

    /**
     * Testing the plot crop in plot setter
     */
    @Test
    public void testSetCropInPlot() {
        plotModel.setCropInPlot(cropModel2);
        assertEquals(plotModel.getCropInPlot(), cropModel2);
    }

    /**
     * Testing the plot fertilizer level
     */
    @Test
    public void testCropFertilizer() {
        assertEquals(plotModel.getFertilizerLevel(), 0);
    }

    /**
     * Testing the plot fertilizer level setting
     */
    @Test
    public void testSetCropFertilizer() {
        plotModel.setFertilizerLevel(10);
        assertEquals(plotModel.getFertilizerLevel(), 10);
    }

    /**
     * Test the get water value
     */
    @Test
    public void testGetWater() {
        assertEquals(plotModel.getWaterValue(), 3);
    }

    /**
     * Test the set water value
     */
    @Test
    public void testSetWater() {
        plotModel.setWaterValue(19);
        assertEquals(plotModel.getWaterValue(), 19);
    }
}

