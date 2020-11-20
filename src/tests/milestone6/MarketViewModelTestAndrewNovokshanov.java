package tests.milestone6;

import models.AnimalModel;
import models.CropModel;
import models.PlayerModel;
import models.SeasonModel;
import models.SettingModel;
import models.StorageModel;
import org.junit.Before;
import org.junit.Test;
import viewmodels.MarketViewModel;
import viewmodels.PlayerViewModel;
import viewmodels.StorageViewModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Junit tests for MarketViewModel
 *
 * @author Andrew Novokshanov
 * @version 1.0
 */
public class MarketViewModelTestAndrewNovokshanov {

    private MarketViewModel marketViewModel;
    private PlayerViewModel playerViewModel;
    private StorageViewModel storageViewModel;
    private StorageModel storageModel;
    private SettingModel settingModel;
    private PlayerModel playerModel;
    private SeasonModel seasonModel;
    private CropModel crop;

    @Before
    public void setUp() {
        List<CropModel> crops = new ArrayList<>();
        crops.add(new CropModel("Corn", 1, 100.00));
        crops.add(new CropModel("Potato", 1, 80.00));
        crops.add(new CropModel("Tomato", 1, 60.00));
        List<AnimalModel> animals = new ArrayList<>();
        animals.add(new AnimalModel(200, 200, 10, "Goat"));

        this.settingModel = new SettingModel(seasonModel, crop,
                "Casual", "Shaun");
        this.playerModel = new PlayerModel(400, settingModel, storageModel);
        this.playerViewModel = new PlayerViewModel();

        this.storageModel = new StorageModel();
        this.crop = new CropModel("Tomato", 2, 20);
        this.seasonModel = new SeasonModel(1, "Spring", animals, crops);

        this.playerViewModel.setPlayerDetails(crop, seasonModel, "Shaun", storageModel,
                "Casual", (int) playerModel.getUserCurrentMoney());

        this.playerViewModel.getPlayer().setPlayerStorage(storageModel);
        this.storageViewModel = new StorageViewModel(this.playerViewModel);
        this.marketViewModel = new MarketViewModel(this.playerViewModel);
    }

    @Test
    public void testCheckPurchasable() {
        int cropBasePrice = 2;
        int quantity = 4;
        assertTrue(this.marketViewModel.checkPurchasable(cropBasePrice, quantity, true));

    }

    @Test
    public void testPurchaseCropItems() {

        this.marketViewModel.purchaseCrops(this.storageViewModel.userInventory().get(0), 4);
        int expected = 7;
        int actual = this.storageViewModel.userInventory().get(0).getCropQuantity();

        assertEquals(expected, actual);
    }

    @Test
    public void calculateCropPrice() {
        double expected = 16.0;
        double actual = this.marketViewModel.calculateCropPrice(20,
                this.playerViewModel.getPlayer().getPlayerSettings().getStartingDifficulty());

        assertEquals(expected, actual, 0);

    }

    @Test
    public void testPurchaseEquipmentItems() {
        playerViewModel.getPlayer().setUserCurrentMoney(10000);
        int expected = 8;

        int pre1 = this.storageViewModel.userFertilizer();
        assertEquals(3, pre1);
        this.marketViewModel.purchaseItems("Fertilizer", 5);
        int actual = this.storageViewModel.userFertilizer();
        assertEquals(expected, actual);

        int pre2 = this.storageViewModel.userPesticide();
        assertEquals(3, pre2);
        this.marketViewModel.purchaseItems("Pesticide", 5);
        int actual2 = this.storageViewModel.userPesticide();
        assertEquals(expected, actual2);
    }
}
