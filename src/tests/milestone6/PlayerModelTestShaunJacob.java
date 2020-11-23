package tests.milestone6;

import models.AnimalModel;
import models.CropModel;
import models.PlayerModel;
import models.SeasonModel;
import models.SettingModel;
import models.StorageModel;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PlayerModelTestShaunJacob {
    private PlayerModel playerModel;
    private StorageModel storageModel;
    private SettingModel settingModel;
    private CropModel cropModel;
    private double currentMoney;

    private SeasonModel seasonModel;
    private AnimalModel goat;
    private AnimalModel chicken;
    private AnimalModel cow;
    private CropModel tomatoCrop;
    private CropModel cornCrop;
    private List<AnimalModel> animalModelList;
    private List<CropModel> cropModelList;


    @Before
    public void setUp() {
        goat = new AnimalModel(120, 150, 50, "Goat");
        chicken = new AnimalModel(50, 63, 28, "Chicken");
        cow = new AnimalModel(560, 846, 150, "Cow");

        tomatoCrop = new CropModel("Tomato", 50, 23.45);
        cornCrop = new CropModel("Corn", 65, 12.76);

        animalModelList = new ArrayList<>();
        animalModelList.add(goat);
        animalModelList.add(chicken);
        animalModelList.add(cow);

        cropModelList = new ArrayList<>();
        cropModelList.add(tomatoCrop);
        cropModelList.add(cornCrop);

        cropModel = new CropModel("Corn", 20, 4.5);
        seasonModel = new SeasonModel(2, "Spring", animalModelList, cropModelList);

        storageModel = new StorageModel();
        settingModel = new SettingModel(seasonModel, cropModel, "Veteran", "Hello World");
        currentMoney = 200;

        playerModel = new PlayerModel(currentMoney, settingModel, storageModel);
    }

    /**
     * Testing getDays method
     */
    @Test
    public void testGetDays() {
        assertEquals(1, playerModel.getDays());
    }

    /**
     * Testing setDays method
     */
    @Test
    public void testSetDays() {
        int days = 60;
        playerModel.setDays(days);
        assertEquals(days, playerModel.getDays());
    }

    /**
     * Testing getUserCurrentMoney method
     */
    @Test
    public void testGetUserCurrentMoney() {
        assertEquals(currentMoney, playerModel.getUserCurrentMoney(), 0);
    }

    /**
     * Testing setUserCurrentMoney method
     */
    @Test
    public void testSetUserCurrentMoney() {
        double newCurrMoney = 62.3;
        playerModel.setUserCurrentMoney(newCurrMoney);
        assertEquals(newCurrMoney, playerModel.getUserCurrentMoney(), 0);
    }

    /**
     * Testing getPlayerSettings method
     */
    @Test
    public void testGetUserCurrentSettings() {
        assertEquals(settingModel, playerModel.getPlayerSettings());
    }

    /**
     * Testing setPlayerSettings method
     */
    @Test
    public void testSetPlayerSettings() {
        SettingModel newSettings = new SettingModel(seasonModel, cropModel, "Easy", "Shaun");
        playerModel.setPlayerSettings(newSettings);
        assertEquals(newSettings, playerModel.getPlayerSettings());
    }

    /**
     * Testing getUserStorage method
     */
    @Test
    public void testGetUserCurrentStorage() {
        assertEquals(storageModel, playerModel.getUserStorage());
    }

    /**
     * Testing setPlayerStorage method
     */
    @Test
    public void testSetUserCurrentStorage() {
        StorageModel newStorageModel = new StorageModel();
        newStorageModel.setTotalFertilizer(756);
        playerModel.setPlayerStorage(newStorageModel);
        assertEquals(newStorageModel, playerModel.getUserStorage());
    }

    /**
     * Testing getMaxHarvestsPerDay method
     */
    @Test
    public void testGetMaxHarvestsPerDay() {
        assertEquals(5, playerModel.getMaxHarvestsPerDay());
    }

    /**
     * Testing setMaxHarvestsPerDay method
     */
    @Test
    public void testSetMaxHarvestsPerDay() {
        playerModel.setMaxHarvestsPerDay(69);
        assertEquals(69, playerModel.getMaxHarvestsPerDay());
    }

    /**
     * Testing getMaxWateringPerDay method
     */
    @Test
    public void testGetMaxWaterPerDay() {
        assertEquals(5, playerModel.getMaxWateringPerDay());
    }

    /**
     * Testing setMaxWateringPerDay method
     */
    @Test
    public void testSetMaxWateringPerDay() {
        playerModel.setMaxWateringPerDay(69);
        assertEquals(69, playerModel.getMaxWateringPerDay());
    }

    /**
     * Testing getCurrentHarvestCounter method
     */
    @Test
    public void testGetCurrentHarvestCounter() {
        assertEquals(0, playerModel.getCurrentHarvestCounter());
    }

    /**
     * Testing setCurrentHarvestCounter method
     */
    @Test
    public void testSetCurrentHarvestCounter() {
        playerModel.setCurrentHarvestCounter(42);
        assertEquals(42, playerModel.getCurrentHarvestCounter());
    }

    /**
     * Testing getCurrentWaterCounter method
     */
    @Test
    public void testGetCurrentWaterCounter() {
        assertEquals(0, playerModel.getCurrentWaterCounter());
    }

    /**
     * Testing setCurrentWaterCounter method
     */
    @Test
    public void testSetCurrentWaterCounter() {
        playerModel.setCurrentWaterCounter(96);
        assertEquals(96, playerModel.getCurrentWaterCounter());
    }
}
