package viewmodels;

import com.sun.scenario.effect.Crop;
import models.CropModel;
import models.MarketModel;
import models.PlayerModel;
import models.StorageModel;

/**
 * This view-model class controls operations for the market
 *
 * @author Aditya Varun Pratap, Andrew Novokshanov
 * @version 1.0
 */
public class MarketViewModel {
    private PlayerViewModel player;
    private StorageModel storage;
    private StorageViewModel storageViewModel;
    private MarketModel marketModel;

    /**
     * This methods constructs a new instance of MarketViewModel
     * @param player a PlayerModel
     * @param storage a StorageModel
     */
    public MarketViewModel(PlayerViewModel player, StorageModel storage, MarketModel market){
        this.player = player;
        this.storage = storage;
        this.storageViewModel = new StorageViewModel(storage, player);
        this.marketModel = market;
    }
    /**
     * This methods checks if the user can purchase an item for the market
     * <p>
     * 1. Check if the user has enough money.
     * 2. Check if user has enough enough storage space.
     * </p>
     *
     * @return A boolean representing if a user can purchase an item.l
     */
    public boolean checkPurchasable(double cropBasePrice, int quantity) {
        String difficulty = marketModel.getSettingModel().getStartingDifficulty();
        double currentPriceIndividual = calculateCropPrice(cropBasePrice, difficulty);
        double currentPriceTotal = currentPriceIndividual * quantity;
        return (!(player.getPlayer().getUserCurrentMoney() < currentPriceTotal))
                && ((quantity + storage.getInventorySize()) <= storage.getCapacity());
    }

    /**
     * Add crop to market after checking it is eligible to be added.
     * @param crop The crop to be added to the market.
     */
    public void purchaseItems(CropModel crop, int quantity) {
        if (checkPurchasable(crop.getCropValue(), quantity)) {
            storageViewModel.addToInventory(crop, quantity);
            PlayerModel curPlayer = player.getPlayer();
            curPlayer.setUserCurrentMoney(curPlayer.getUserCurrentMoney() -
                    calculateCropPrice(crop.getCropValue(), curPlayer.getPlayerSettings().getStartingDifficulty()));
        }
    }

    /**
     * Get price of crop after taking account difficulty
     * @param cropBasePrice Base price of a crop without taking into account the difficulty
     * @param difficulty Current difficulty a player has set
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
