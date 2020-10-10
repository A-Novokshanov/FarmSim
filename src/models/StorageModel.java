package models;


import java.util.ArrayList;

/**
 * This class stores the data related to the inventory, and also adds and removes crops from inventory.
 *
 * @author Rohan Kashiviswanathan
 * @version 1.0
 */
public class StorageModel {
    private ArrayList<CropModel> inventory;

    /**
     * Constructor for the inventory that creates the structure of the inventory and initializes it with starter crops.
     */
    public StorageModel() {
        inventory = new ArrayList<>(15);
        inventory.add(new CropModel("Corn", 1,  100.00));
        inventory.add(new CropModel("Tomato", 1,  100.00));
        inventory.add(new CropModel("Potato", 1,  100.00));
    }

    /**
     * Gets the inventory that the player currently has.
     *
     * @return the inventory.
     */
    public ArrayList<CropModel> getInventory() {
        return inventory;
    }

    /**
     * Looks to see if we have the crop in the storage and then adds a certain quantity, or it adds it to inventory.
     *
     * @param crop the crop we want to add.
     * @param quantity how much of the crop to add.
     */
    public void addCrop(CropModel crop, int quantity) {
        if (getInventorySize() < 15) {
            boolean added = false;
            for (int i = 0; i < getInventorySize(); i++) {
                if (checkIfNameCorrect(i, crop)) {
                    inventory.get(i).setCropQuantity(inventory.get(i).getCropQuantity() + quantity);
                    added = true;
                    break;
                }
            }
            if (!added) {
                inventory.set(inventory.size(), crop);
                crop.setCropQuantity(crop.getCropQuantity() + quantity);
            }
        }
    }

    /**
     * Looks to see if we have the crop in the storage and then removes a certain quantity, if not all of it.
     *
     * @param crop the crop we want to remove.
     * @param quantity how much of the crop to remove.
     */
    public void sellCrop(CropModel crop, int quantity) {
        if (getInventorySize() > 0 && upForSale(crop)) {
            for (int i = 0; i < getInventorySize(); i++) {
                if (checkIfNameCorrect(i, crop)) {
                    if (inventory.get(i).getCropQuantity() - quantity > 0) {
                        inventory.get(i).setCropQuantity(inventory.get(i).getCropQuantity() - quantity);
                        break;
                    } else if (inventory.get(i).getCropQuantity() - quantity > -1){
                        inventory.remove(i);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Gets the current inventory size.
     *
     * @return inventory size.
     */
    public int getInventorySize() {
        return inventory.size();
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
     * Tells if you want to exit out of inventory.
     *
     * @return true to leave inventory.
     */
    public boolean leaveInventory() {
        return true;
    }

    public boolean checkIfNameCorrect(int index, CropModel crop2) {
        if (inventory.get(index).getCropName().equals(crop2.getCropName())) {
            return true;
        }
        return false;
    }

}
