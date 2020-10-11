package viewmodels;

import models.CropModel;
import models.StorageModel;

import java.util.ArrayList;

/**
 * This view-model class keeps controls the logic flow of adding to a user's inventory.
 *
 * @author Rohan Kashiviswanathan
 * @version 1.0
 */
public class StorageViewModel {
    private StorageModel storageModel;
    private PlayerViewModel player;


    /**
     * This constructor initializes the instance variables.
     *
     * @param storageModel The storage model object.
     */
    public StorageViewModel(StorageModel storageModel, PlayerViewModel player) {
        this.storageModel = storageModel;
        this.player = player;
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
                    storageModel.setNewCropAmount(quantity, i);
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
     * @param crop   The crop the user is trying to sell.
     * @param amount The amount of crop the user is trying to sell.
     */
    public void sellItemFromInventory(CropModel crop, int amount) {
        if (storageModel.getInventorySize() > 0 && storageModel.upForSale(crop)) {
            for (int i = 0; i < storageModel.getInventorySize(); i++) {
                if (storageModel.checkIfNameCorrect(i, crop)) {
                    if (storageModel.getEnoughToRemove(i, amount) == 1) {
                        if (storageModel.getEnoughToRemove(i, amount) == 1) {
                            storageModel.removeCropAmount(amount, i);
                            player.getPlayer().setUserCurrentMoney((int) (player.getPlayer().getUserCurrentMoney()
                                    + amount * (crop.getCropValue())));
                        } else {
                            storageModel.removeCrop(i);
                            player.getPlayer().setUserCurrentMoney((int) (player.getPlayer().getUserCurrentMoney()
                                    + amount * (crop.getCropValue())));
                        }

                    }
                }
            }
        }
    }

    /**
     * Gets and returns the user's inventory.
     *
     * @return The inventory arrayList of the user.
     */
    public ArrayList<CropModel> userInventory() {
        return storageModel.getInventory();
    }


}
