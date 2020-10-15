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
     * @param player is the player view model object
     **/
    public StorageViewModel(PlayerViewModel player) {
        this.player = player;
        this.storageModel = player.getPlayer().getUserStorage();
    }

    /**
     * Add the item purchased to the inventory.
     *
     * @param crop     is the crop model object
     * @param quantity is the amount of crops to add
     */
    public void addToInventory(CropModel crop, int quantity) {
        if (storageModel.getTotalCropAmount() < 15) {
            int count = 0;
            for (int i = 0; i < storageModel.getInventorySize(); i++) {
                count += 1;
                if (storageModel.checkIfNameCorrect(i, crop)) {
                    storageModel.setNewCropAmount(quantity, i);
                }
            }
            if (count == storageModel.getTotalCropAmount()) {
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
        if (storageModel.getTotalCropAmount() > 0 && storageModel.upForSale(crop)) {
            for (int i = 0; i < storageModel.getInventorySize(); i++) {
                if (storageModel.checkIfNameCorrect(i, crop)) {
                    if (storageModel.getEnoughToRemove(i, amount) == 1) {
                        storageModel.removeCropAmount(amount, i);
                        player.getPlayer().setUserCurrentMoney((int) (
                                player.getPlayer().getUserCurrentMoney()
                                        + amount * (calculateCropPrice(crop.getCropValue(),
                                        player.getPlayer().getPlayerSettings()
                                                .getStartingDifficulty()))));
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

    /**
     * Get price of crop after taking account difficulty
     *
     * @param cropBasePrice Base price of a crop without taking into account the difficulty
     * @param difficulty    Current difficulty a player has set
     * @return current price of crop.
     */
    public double calculateCropPrice(double cropBasePrice, String difficulty) {
        double difficultyMod;
        switch (difficulty) {
        case "Casual":
            difficultyMod = 0.8;
            break;
        case "Normal":
            difficultyMod = 1.0;
            break;
        case "Veteran":
            difficultyMod = 1.2;
            break;
        default:
            difficultyMod = 0.0;
            break;
        }
        double currentPrice = difficultyMod * cropBasePrice;
        return currentPrice;
    }
}
