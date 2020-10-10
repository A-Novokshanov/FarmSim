package viewmodels;

import models.CropModel;
import models.StorageModel;

/**
 *This view-model class keeps controls the logic flow of adding to a user's inventory.
 *
 * @author Aditya Varun Pratap
 * @version 1.0
 */
public class StorageViewModel {
    private StorageModel storageModel;

    public StorageViewModel() {
        storageModel = new StorageModel();
    }

    /**
     * Add the item purchased to the inventory.
     */
    public void addToInventory(CropModel crop, int quantity) {
        if (storageModel.getInventorySize() < 15) {
            int count = 0;
            for (int i = 0; i < storageModel.getInventorySize(); i++) {
                count += 1;
                if (storageModel.checkIfNameCorrect(i, crop)) {
                    storageModel.setNewCropAmount(crop, quantity, i);
                }
            }
            if (count == storageModel.getInventorySize()) {
                storageModel.setNewCrop(crop, quantity);
            }
        }
    }

        /**
         * Removes item from inventory and returns the removed item.
         *
         * @param crop The crop the user is trying to sell.
         * @param amount The amount of crop the user is trying to sell.l
         * @return The removed item.
         */
        public void sellItemFromInventory (CropModel crop,int amount){
            if (storageModel.getInventorySize() > 0 && storageModel.upForSale(crop)) {
                for (int i = 0; i < storageModel.getInventorySize(); i++) {
                    if (storageModel.checkIfNameCorrect(i, crop)) {
                        if (storageModel.getEnoughToRemove(i, crop, amount) == 1) {
                            if (storageModel.getEnoughToRemove(i, crop, amount) == 1) {
                                storageModel.removeCropAmount(crop, amount, i);
                            } else {
                                storageModel.removeCrop(crop, i);
                            }

                        }
                    }
                }
            }
        }






}
