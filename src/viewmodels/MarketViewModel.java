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
    private PlayerModel player;
    private StorageModel storage;
    private CropModel corn;
    private CropModel potato;
    private CropModel tomato;
    private CropModel[] marketList;
    private MarketModel marketModel;

    /**
     * This methods constructs a new instance of MarketViewModel
     * @param player a PlayerModel
     * @param storage a StorageModel
     * @return A boolean representing if a user can purchase an item.l
     */
    public MarketViewModel(PlayerModel player , StorageModel storage) {
        this.player = player;
        this.storage = storage;
        this.corn = new CropModel("Corn", 0, 10.0);
        this.potato = new CropModel("Potato", 0, 8.0);
        this.tomato = new CropModel("Tomato", 0, 6.0);
        this.marketList = new CropModel[]{corn, potato, tomato};
        this.marketModel = new MarketModel(marketList, player.getPlayerSettings());
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
        double currentPrice = difficultyMod * cropBasePrice * quantity;
        if ((player.getUserCurrentMoney() < currentPrice) || ((quantity + storage.getInventorySize()) > storage.getCapacity())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Add crop to market after checking it is eligible to be added.
     * @param crop The crop to be added to the market.
     */
    public void addCropsToStorage(CropModel crop) {
//        if (checkPurchasable(crop.getCropValue())) {
//
//        }
    }

    /**
     * Add crop to market after checking it is eligible to be added.
     * @param crop The crop to be added to the market.
     */
    public void addCropsToMarket(CropModel crop) {

    }
}
