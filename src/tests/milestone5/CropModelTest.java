package tests.milestone5;

import models.CropModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CropModelTest {
    private CropModel cropModel;

    @Before
    public void setUp() {
        cropModel = new CropModel("corn", 2,  4);
    }

    /**
     * Testing the crop name getter
     */
    @Test
    public void testGetCropName() {
        assertEquals("corn", cropModel.getCropName());
    }

    /**
     * Testing the crop name setter
     */
    @Test
    public void testSetCropName() {
        cropModel.setCropName("tomato");
        assertEquals("tomato", cropModel.getCropName());
    }

    /**
     * Testing the crop quantity getter
     */
    @Test
    public void testGetCropQuantity() {
        assertEquals(2, cropModel.getCropQuantity());
    }

    /**
     * Testing the crop quantity setter
     */
    @Test
    public void testSetCropQuantity() {
        cropModel.setCropQuantity(4);
        assertEquals(4, cropModel.getCropQuantity());
    }

    /**
     * Testing the crop value getter
     */
    @Test
    public void testGetCropValue() {
        assertEquals(4, cropModel.getCropValue(), 0);
    }

    /**
     * Testing the crop value setter
     */
    @Test
    public void testSetCropValue() {
        cropModel.setCropValue(65);
        assertEquals(65, cropModel.getCropValue(), 0);
    }

    /**
     * Testing the crop hasPesticide getter
     */
    @Test
    public void testGetHasPesticide() {
        assertFalse(cropModel.getHasPesticide());
    }

    /**
     * Testing the crop hasPesticide setter
     */
    @Test
    public void testSetHasPesticide() {
        cropModel.setHasPesticide(true);
        assertTrue(cropModel.getHasPesticide());
    }
}
