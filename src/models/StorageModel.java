package models;


import java.util.ArrayList;

/**
 * This class stores the data related to the cropInventory, and also adds and removes crops from cropInventory.
 *
 * @author Rohan Kashiviswanathan
 * @version 1.0
 */
public class StorageModel {
    private ArrayList<CropModel> cropInventory;

    /**
     * Constructor for the cropInventory that creates the structure of the cropInventory and initializes it with starter crops.
     */
    public StorageModel() {
        cropInventory = new ArrayList<>(15);
        cropInventory.add(new CropModel("Corn", 1, 100.00));
        cropInventory.add(new CropModel("Tomato", 1, 100.00));
        cropInventory.add(new CropModel("Potato", 1, 100.00));

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
     * Looks to see if we have the crop in the storage and then adds a certain quantity, or it adds it to cropInventory.
     *
     * @param crop     the crop we want to add.
     * @param quantity how much of the crop to add.
     */
    public void setNewCropAmount(CropModel crop, int quantity, int i) {
        cropInventory.get(i).setCropQuantity(cropInventory.get(i).getCropQuantity() + quantity);


    }

    public void setNewCrop(CropModel crop, int quantity) {
        cropInventory.set(cropInventory.size(), crop);
        crop.setCropQuantity(crop.getCropQuantity() + quantity);
    }




    /**
     * Looks to see if we have the crop in the storage and then removes a certain quantity, if not all of it.
     *
     * @param crop     the crop we want to remove.
     * @param quantity how much of the crop to remove.
     */
    public void removeCropAmount(CropModel crop, int quantity, int i) {
        cropInventory.get(i).setCropQuantity(cropInventory.get(i).getCropQuantity() - quantity);
    }

    public void removeCrop(CropModel crop, int i) {
        cropInventory.remove(i);
    }

    public int getEnoughToRemove(int i, CropModel crop, int quantity) {
        if (cropInventory.get(i).getCropQuantity() - quantity> 0) {
            return 1;
        }
        else if (cropInventory.get(i).getCropQuantity() - quantity == 0) {
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
     *
     * @return whether the names are equal.
     */
    public boolean checkIfNameCorrect(int index, CropModel crop2) {
        return cropInventory.get(index).getCropName().equals(crop2.getCropName());
    }

    public int getCapacity() {
        return 15;
    }

}
