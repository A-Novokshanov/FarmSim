package viewmodels;

import models.CropModel;
import models.PlayerModel;
import models.StorageModel;
import services.player.PlayerInventoryService;
import services.player.PlayerSettingsService;

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
    private PlayerSettingsService playerInfoDatabase;
    private PlayerInventoryService playerInventoryService;

    /**
     * This methods constructs a new instance of MarketViewModel
     *
     * @param player a PlayerModel
     */
    public MarketViewModel(PlayerViewModel player) {
        this.player = player;
        this.storage = player.getPlayer().getUserStorage();
        this.storageViewModel = new StorageViewModel(player);
        this.playerInfoDatabase = new PlayerSettingsService();
        this.playerInventoryService = new PlayerInventoryService();
    }

    /**
     * This methods checks if the user can purchase an item for the market
     * <p>
     * 1. Check if the user has enough money.
     * 2. Check if user has enough enough storage space.
     * </p>
     *
     * @param cropBasePrice price of crop
     * @param quantity      amount of crop
     * @return A boolean representing if a user can purchase an item.
     */
    public boolean checkPurchasable(double cropBasePrice, int quantity) {
        String difficulty = player.getPlayer().getPlayerSettings()
                .getStartingDifficulty();
        double currentPriceIndividual = calculateCropPrice(cropBasePrice, difficulty);
        double currentPriceTotal = currentPriceIndividual * quantity;
        return (!(player.getPlayer().getUserCurrentMoney() < currentPriceTotal))
                && ((quantity + storage.getTotalCropAmount()) <= storage.getCapacity());
    }

    /**
     * Add crop to market after checking it is eligible to be added.
     *
     * @param crop     The crop to be added to the market.
     * @param quantity amount of crop.
     */
    public void purchaseCrops(CropModel crop, int quantity) {
        if (checkPurchasable(crop.getCropValue(), quantity)) {
            storageViewModel.addToInventory(crop, quantity);
            PlayerModel curPlayer = player.getPlayer();
            double money = calculateCropPrice(crop.getCropValue(),
                    player.getPlayer().getPlayerSettings().getStartingDifficulty()) * quantity;
            player.getPlayer().setUserCurrentMoney(player.getPlayer().getUserCurrentMoney()
                    - money);

            this.playerInfoDatabase.updatePlayerMoney(-money, this.player.getPlayer()
                    .getPlayerSettings().getPlayerName());

        }
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

    /**
     * Add equipment to inventory after checking it is eligible to be added.
     *
     * @param equipment     Name of the equipment
     * @param quantity      amount of equipment.
     */
    public void purchaseItems(String equipment, int quantity) {
        if (!equipment.equals("Fertilizer") && !equipment.equals("Pesticide")) {
            return;
        }
        if (equipment.equals("Fertilizer")) {
            if (checkPurchasable(100, quantity)) {
                player.getPlayer().getUserStorage().setTotalFertilizer(
                        player.getPlayer().getUserStorage().getTotalFertilizer() + quantity);
                PlayerModel curPlayer = player.getPlayer();
                double money = calculateCropPrice(100,
                        player.getPlayer().getPlayerSettings().getStartingDifficulty()) * quantity;
                player.getPlayer().setUserCurrentMoney(player.getPlayer().getUserCurrentMoney()
                        - money);
                this.playerInfoDatabase.updatePlayerMoney(-money, this.player.getPlayer()
                        .getPlayerSettings().getPlayerName());
            }
        } else if (equipment.equals("Pesticide")) {
            if (checkPurchasable(100, quantity)) {
                player.getPlayer().getUserStorage().setTotalPesticide(
                        player.getPlayer().getUserStorage().getTotalPesticide() + quantity);
                PlayerModel curPlayer = player.getPlayer();
                double money = calculateCropPrice(100,
                        player.getPlayer().getPlayerSettings().getStartingDifficulty()) * quantity;
                player.getPlayer().setUserCurrentMoney(player.getPlayer().getUserCurrentMoney()
                        - money);
                this.playerInfoDatabase.updatePlayerMoney(-money, this.player.getPlayer()
                        .getPlayerSettings().getPlayerName());
            }
        }
    }
}
