package viewmodels;

import models.CropModel;
import models.MarketModel;

/**
 * This view-model class controls operations for the market
 *
 * @author Aditya Varun Pratap
 * @version 1.0
 */
public class MarketViewModel {
    private MarketModel marketModel;

    /**
     * This methods checks if the user can purchase an item for the market
     * <p>
     * 1. Check if the user has enough money.
     * 2. Check if user has enough enough storage space.
     * </p>
     *
     * @return A boolean representing if a user can purchase an item.l
     */
    public boolean checkPurchasable() {
        return false;
    }

    /**
     * Add crop to market after checking it is eligible to be added.
     * @param crop The crop to be added to the market.l
     */
    public void addCropsToMarket(CropModel crop) {

    }
}
