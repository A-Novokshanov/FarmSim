package tests.milestone3;


import models.*;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.*;


public class StorageModelTestRohanKashi {
    private StorageModel storageModelTest;
    private ArrayList<CropModel> cropInventory;
    private CropModel orange;
    private CropModel corn;
    private CropModel melon;
    private CropModel squash;
    private CropModel strawberries;
    private CropModel tomato;


    @Before
    public void setup() {
        storageModelTest = new StorageModel();
    }

    @Test
    public void testGetInventory() {
        cropInventory = new ArrayList<>(15);
        cropInventory.add(new CropModel("Corn", 1, 100.00));
        cropInventory.add(new CropModel("Potato", 1, 80.00));
        cropInventory.add(new CropModel("Tomato", 1, 60.00));
        assertEquals(storageModelTest.getInventory().get(0).getCropName(),
                cropInventory.get(0).getCropName());
        assertEquals(storageModelTest.getInventory().get(1).getCropName(),
                cropInventory.get(1).getCropName());
        assertEquals(storageModelTest.getInventory().get(2).getCropName(),
                cropInventory.get(2).getCropName());
        assertEquals(storageModelTest.getInventory().get(0).getCropQuantity(),
                cropInventory.get(0).getCropQuantity());
        assertEquals(storageModelTest.getInventory().get(1).getCropQuantity(),
                cropInventory.get(1).getCropQuantity());
        assertEquals(storageModelTest.getInventory().get(2).getCropQuantity(),
                cropInventory.get(2).getCropQuantity());

    }

    @Test
    public void testSetNewCropAmount() {
        storageModelTest.setNewCropAmount(5, 0);
        assertEquals(storageModelTest.getInventory().get(0).getCropQuantity(), 6);
        storageModelTest.setNewCropAmount(2, 1);
        assertEquals(storageModelTest.getInventory().get(1).getCropQuantity(), 3);
        storageModelTest.setNewCropAmount(5, 2);
        assertEquals(storageModelTest.getInventory().get(2).getCropQuantity(), 6);
    }

    @Test
    public void testSetNewCrop() {
        orange = new CropModel("Orange", 0, 1500.3);
        storageModelTest.setNewCrop(orange, 3);
        assertEquals(storageModelTest.getInventory().get(3).getCropQuantity(), 3);
        assertEquals(storageModelTest.getInventory().get(3), orange);
        strawberries = new CropModel("Strawberries", 0, 10.0);
        storageModelTest.setNewCrop(strawberries, 9);
        assertEquals(storageModelTest.getInventory().get(4).getCropQuantity(), 9);
        assertEquals(storageModelTest.getInventory().get(4), strawberries);
        squash = new CropModel("Squash", 0, 400.0);
        storageModelTest.setNewCrop(squash, 12);
        assertEquals(storageModelTest.getInventory().get(5).getCropQuantity(), 12);
        assertEquals(storageModelTest.getInventory().get(5), squash);
        melon = new CropModel("Melon", 0, 29.29);
        storageModelTest.setNewCrop(melon, 100);
        assertEquals(storageModelTest.getInventory().get(6).getCropQuantity(), 100);
        assertEquals(storageModelTest.getInventory().get(6), melon);
    }

    @Test
    public void testRemoveCropAmount() {
        storageModelTest.removeCropAmount(1, 0);
        assertEquals(storageModelTest.getInventory().get(0).getCropQuantity(), 0);
        storageModelTest.removeCropAmount(1, 1);
        assertEquals(storageModelTest.getInventory().get(1).getCropQuantity(), 0);
        storageModelTest.removeCropAmount(1, 2);
        assertEquals(storageModelTest.getInventory().get(2).getCropQuantity(), 0);
    }

    @Test
    public void testRemoveCrop() {
        storageModelTest.removeCrop(2);
        assertEquals(storageModelTest.getTotalCropAmount(), 2);
        storageModelTest.removeCrop(1);
        assertEquals(storageModelTest.getTotalCropAmount(), 1);
        storageModelTest.removeCrop(0);
        assertEquals(storageModelTest.getTotalCropAmount(), 0);

    }

    @Test
    public void testGetEnoughRemove() {
        assertEquals(storageModelTest.getEnoughToRemove(0, 1), 2);
        assertEquals(storageModelTest.getEnoughToRemove(1, 1), 2);
        assertEquals(storageModelTest.getEnoughToRemove(2, 12), 3);
    }

    @Test
    public void testUpForSale() {
        tomato = new CropModel("Tomato", 131, 55.0);
        orange = new CropModel("Orange", 0, 1500.3);
        assertTrue(storageModelTest.upForSale(tomato));
        assertFalse(storageModelTest.upForSale(orange));
    }

    @Test
    public void testGetInventorySize() {
        assertEquals(storageModelTest.getTotalCropAmount(), 3);
        orange = new CropModel("Orange", 0, 1500.3);
        storageModelTest.setNewCrop(orange, 4);
        assertEquals(storageModelTest.getTotalCropAmount(), 7);

    }

    @Test
    public void testCheckIfNameCorrect() {
        corn = new CropModel("Corn", 3440, 45323.3);
        tomato = new CropModel("Orange", 0, 1500.3);
        assertTrue(storageModelTest.checkIfNameCorrect(0, corn));
        assertFalse(storageModelTest.checkIfNameCorrect(1, tomato));
    }

    @Test
    public void testGetInventoryCapacity() {
        assertEquals(storageModelTest.getCapacity(), 15);
    }
}

