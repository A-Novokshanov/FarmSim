package models;
/**
 The MarketModel class represents the model class of the Market
 holding the instance variables and associating methods.
 * @author Andrew Novokshanov
 * @version 1.0
 */
public class MarketModel {
    private CropModel[] cropsInMarket;

    /** Constructs a MarketModel object.
     * @param cropsInMarket The list of Crops available in the market
     */
    public MarketModel(CropModel[] cropsInMarket) {
        this.cropsInMarket = cropsInMarket;
    }

    /** @return Gets and returns the list of crops in a MarketModel object. */
    public CropModel[] getCropsInMarket() {
        return this.cropsInMarket;
    }

    /** Sets the list of crops in a MarketModel object.
     @param cropsInMarket is the new desired list of crops of a MarketModel object.
     */
    public void setCropsInMarket(CropModel[] cropsInMarket) {
        this.cropsInMarket = cropsInMarket;
    }
}