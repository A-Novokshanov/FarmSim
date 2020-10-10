package junits.milestone2;

import models.CropModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CropModelTestShaunJacob {
    private CropModel cropModel;

    @Before
    public void setUp() {
        cropModel = new CropModel("corn", 2, 0, 4);
    }

    // Testing the crop name getter
    @Test
    public void testGetCropName() {
        assertEquals("corn", cropModel.getCropName());
    }

    // Testing the crop name setter
    @Test
    public void testSetCropName() {
        cropModel.setCropName("tomato");
        assertEquals("tomato", cropModel.getCropName());
    }

    // Testing the crop quantity getter
    @Test
    public void testGetCropQuantity() {
        assertEquals(2, cropModel.getCropQuantity());
    }

    // Testing the crop quantity setter
    @Test
    public void testSetCropQuantity() {
        cropModel.setCropQuantity(4);
        assertEquals(4, cropModel.getCropQuantity());
    }

    // Testing the crop value getter
    @Test
    public void testGetCropValue() {
        assertEquals(4, (int) cropModel.getCropValue());
    }

    // Testing the crop value setter
    @Test
    public void testSetCropValue() {
        cropModel.setCropValue(56);
        assertEquals(56, (long) cropModel.getCropValue());
    }
}
