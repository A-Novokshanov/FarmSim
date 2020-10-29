package viewmodels;

import com.sun.scenario.effect.Crop;
import models.CropModel;
import models.StorageModel;
import services.player.PlayerInventoryService;
import services.player.PlayerSettingsService;

import java.util.ArrayList;
import java.util.List;

/**
 * This view-model class keeps controls the logic flow of adding to a user's inventory.
 *
 * @author Rohan Kashiviswanathan
 * @version 1.0
 */
public class StorageViewModel {
    private StorageModel storageModel;
    private PlayerViewModel player;
    private PlayerSettingsService playerInfoDatabase;
    private PlayerInventoryService playerInventoryService;


    /**
     * This constructor initializes the instance variables.
     *
     * @param player is the player view model object
     **/
    public StorageViewModel(PlayerViewModel player) {
        this.player = player;
        this.storageModel = player.getPlayer().getUserStorage();
        this.playerInfoDatabase = new PlayerSettingsService();
        this.playerInventoryService = new PlayerInventoryService();
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
                List<CropModel> crops = new ArrayList<>();
                crops.add(crop);
                playerInventoryService.addPlayerCrops(player.getPlayer().getPlayerSettings().getPlayerName(), crops);
            }
            playerInventoryService.adjustCropQuantity(crop.getCropName(),
                    quantity, player.getPlayer().getPlayerSettings().getPlayerName());
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

                        double money = amount * (calculateCropPrice(crop.getCropValue(),
                                player.getPlayer().getPlayerSettings()
                                        .getStartingDifficulty()));

                        player.getPlayer().setUserCurrentMoney((int) (
                                player.getPlayer().getUserCurrentMoney() + money));

                        this.playerInfoDatabase.updatePlayerMoney(money, this.player.getPlayer()
                                .getPlayerSettings().getPlayerName());

                        this.playerInventoryService.adjustCropQuantity(crop.getCropName(), -amount,
                                this.player.getPlayer().getPlayerSettings().getPlayerName());
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
