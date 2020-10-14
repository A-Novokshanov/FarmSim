package models;


import java.util.ArrayList;

/**
 * This class stores the data related to the cropInventory,
 * and also adds and removes crops from cropInventory.
 *
 * @author Rohan Kashiviswanathan
 * @version 1.0
 */
public class StorageModel {
    private ArrayList<CropModel> cropInventory;

    /**
     * Constructor for the cropInventory that creates the
     * structure of the cropInventory and initializes it with starter crops.
     */
    public StorageModel() {
        cropInventory = new ArrayList<>(15);
        cropInventory.add(new CropModel("Corn", 5, 100.00));
        cropInventory.add(new CropModel("Tomato", 7, 80.00));
        cropInventory.add(new CropModel("Potato", 9, 60.00));

    }

    /**
     * Gets the cropInventory that the player currently has.
     *
     * @return the cropInventory.
     */
    public ArrayList<CropModel> getInventory() {
        return cropInventory;
    }

    /**
     * Adds to the current crop amount.
     *
     * @param i        the index our object is in.
     * @param quantity how much of the crop to add.
     */
    public void setNewCropAmount(int quantity, int i) {
        cropInventory.get(i).setCropQuantity(cropInventory.get(i).getCropQuantity() + quantity);
    }

    /**
     * Sets up a new crop in our list if its
     *
     * @param crop     the crop we want to add.
     * @param quantity how much of the crop to add.
     */
    public void setNewCrop(CropModel crop, int quantity) {
        cropInventory.add(cropInventory.size(), crop);
        crop.setCropQuantity(crop.getCropQuantity() + quantity);
    }


    /**
     * Looks to see if we have the crop in the storage and then
     * removes a certain quantity, if not all of it.
     *
     * @param quantity how much of the crop to remove.
     * @param i        is the location of crop in inventory
     */
    public void removeCropAmount(int quantity, int i) {
        cropInventory.get(i).setCropQuantity(cropInventory.get(i).getCropQuantity() - quantity);
    }

    /**
     * Removes the crop from the inventory.
     *
     * @param i where its located in our list.
     */
    public void removeCrop(int i) {
        cropInventory.remove(i);
    }

    /**
     * Checks to see if we have to remove from inventory, or just a certain amount.
     *
     * @param i        location of crop.
     * @param quantity amount to remove.
     * @return what scenario to go with.
     */
    public int getEnoughToRemove(int i, int quantity) {
        if (cropInventory.get(i).getCropQuantity() - quantity > 0) {
            return 1;
        } else if (cropInventory.get(i).getCropQuantity() - quantity == 0) {
            return 2;
        }
        return 3;
    }

    /**
     * Gets the current cropInventory size.
     *
     * @return cropInventory size.
     */
    public int getInventorySize() {
        return cropInventory.size();
    }

    /**
     * Checks to see if the crop passed in is valid to sell.
     *
     * @param crop the crop we want to check is valid to sell.
     * @return if its valid to sell or not.
     */
    public boolean upForSale(CropModel crop) {
        String[] cropArray = new String[]{"Tomato", "Potato", "Corn"};
        for (String i : cropArray) {
            if (i.equals(crop.getCropName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks to see if the crop name matches with the current object in list.
     *
     * @param index the current index in the list.
     * @param crop2 the crop passed in to add to inventory.
     * @return whether the names are equal.
     */
    public boolean checkIfNameCorrect(int index, CropModel crop2) {
        return cropInventory.get(index).getCropName().equals(crop2.getCropName());
    }

    /**
     * The capacity of our inventory.
     *
     * @return the capacity.
     */
    public int getCapacity() {
        return 15;
    }

}
