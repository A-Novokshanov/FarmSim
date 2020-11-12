package tests.milestone5;

import models.CropModel;
import models.StorageModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * JUnits for storageModel
 *
 * @author Aditya Varun Pratap
 * @version 1.0
 */
public class StorageModelTestAdityaPratap {

    private StorageModel storageModel;
    private ArrayList<CropModel> cropInventory = new ArrayList<>(15);

    @Before
    public void setup() {
        storageModel = new StorageModel();
        cropInventory.add(new CropModel("Corn", 3, 100.00));
        cropInventory.add(new CropModel("Potato", 3, 80.00));
        cropInventory.add(new CropModel("Tomato", 3, 60.00));
        CropModel cornPest = new CropModel("Corn with Pesticide", 0, 70.00);
        cornPest.setHasPesticide(true);
        CropModel potatoPest = new CropModel("Potato with Pesticide", 0, 50.00);
        potatoPest.setHasPesticide(true);
        CropModel tomatoPest = new CropModel("Tomato with Pesticide", 0, 30.00);
        tomatoPest.setHasPesticide(true);
        cropInventory.add(cornPest);
        cropInventory.add(potatoPest);
        cropInventory.add(tomatoPest);
    }

    @Test
    public void testGetCropInventory() {


        for (int i = 0; i < cropInventory.size(); i++) {
            assertEquals(cropInventory.get(i).getCropName(),
                    storageModel.getInventory().get(i).getCropName());
        }

    }

    @Test
    public void testSetNewCropAmount() {
        int expected = 23;
        storageModel.setNewCropAmount(20, 0);

        assertEquals(expected, storageModel.getInventory().get(0).getCropQuantity());
    }

    @Test
    public void testRemoveCropAmount() {
        int expected = 0;
        storageModel.removeCropAmount(3, 0);

        assertEquals(expected, storageModel.getInventory().get(0).getCropQuantity());
    }

    @Test
    public void testGetTotalFertilizer() {
        int expected = 1;

        assertEquals(expected, storageModel.getTotalFertilizer());

    }
}
