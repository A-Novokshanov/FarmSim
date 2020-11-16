package viewmodels;

import models.CropModel;
import models.PlayerModel;
import models.SeasonModel;
import models.SettingModel;
import models.StorageModel;
import services.authentication.CreatePlayer;
import services.authentication.LoginPlayer;
import services.player.PlayerInventoryService;
import services.player.PlayerSettingsService;

/**
 * This view-model class will aid accessing certain types of players.
 * This view-model will act as an intermediary for Player and Game Admin.
 *
 * @author Aditya Varun Pratap, Rohan K
 * @version 1.0
 */
public class PlayerViewModel {

    private SettingModel settingModel;
    private PlayerModel playerModel;
    private PlayerSettingsService playerSettingsService = new PlayerSettingsService();


    /**
     * Packs the data into the SettingModel object and passes to the services package.
     * Then, deserializes the data and adds to the database.
     *
     * @param playerModel The model class that contains all player details.
     */
    public void addSettingsToDatabase(PlayerModel playerModel) {
        CreatePlayer createPlayer = new CreatePlayer();
        createPlayer.setPlayerDetails(playerModel);

        PlayerInventoryService playerInventoryService = new PlayerInventoryService();
        playerInventoryService.addPlayerCrops(playerModel.getPlayerSettings().getPlayerName(),
                playerModel.getUserStorage().getInventory());
    }

    /**
     * This method checks if the user exists.
     *
     * @param name The name of the user.
     * @return A boolean representing if the user already exists.
     */
    public boolean playerExists(String name) {
        LoginPlayer loginPlayer = new LoginPlayer();
        return loginPlayer.checkUserExists(name);
    }

    /**
     * Sets the players details/ initial configurations.
     *
     * @param cropModel          The crop type the player chooses.
     * @param seasonModel        The season player chooses.
     * @param playerName         The name of the player.
     * @param startingDifficulty The starting difficulty the player chooses.
     * @param currentMoney       The current money of the player.
     * @param storageModel       The storage/inventory of the user
     */
    public void setPlayerDetails(CropModel cropModel, SeasonModel seasonModel,
                                 String playerName,
                                 StorageModel storageModel, String startingDifficulty,
                                 double currentMoney) {

        this.settingModel = new SettingModel(seasonModel, cropModel,
                startingDifficulty, playerName);
        this.playerModel = new PlayerModel(currentMoney, this.settingModel, storageModel);
        this.addSettingsToDatabase(this.playerModel);

    }

    /**
     * Gets the player information from the service class.
     *
     * @param playerName The name of the player to get the settings of.
     */
    public void getPlayerInformationFromDatabase(String playerName) {
        LoginPlayer loginPlayer = new LoginPlayer();
        this.playerModel = loginPlayer.queryPlayerMoneyAndSettings(playerName);
    }

    /**
     * Returns the populated setting model object.
     *
     * @return The setting model object.
     */
    public PlayerModel getPlayer() {
        return this.playerModel;
    }

    /**
     * Updates player day by 1.
     *
     * @param playerName Name of the current user.
     */
    public void updatePlayerDay(String playerName) {
        playerSettingsService.updatePlayerDay(1, playerName);
    }

    /**
     * Increases the current harvest counter of the player by 1.
     */
    public void increasePlayerHarvestCounter() {
        playerModel.setCurrentHarvestCounter(playerModel.getCurrentHarvestCounter() + 1);
        playerSettingsService.updatePlayerHarvest(playerModel.getCurrentHarvestCounter(),
                playerModel.getPlayerSettings().getPlayerName());
    }

    /**
     * Increases the current water counter of the player by 1.
     */
    public void increasePlayerWaterCounter() {
        playerModel.setCurrentWaterCounter(playerModel.getCurrentWaterCounter() + 1);
        playerSettingsService.updatePlayerWater(playerModel.getCurrentWaterCounter(),
                playerModel.getPlayerSettings().getPlayerName());
    }

    /**
     * Zeros the current harvest counter of the player.
     */
    public void zeroCurrentHarvestCounter() {
        playerModel.setCurrentHarvestCounter(0);
        playerSettingsService.updatePlayerHarvest(0,
                playerModel.getPlayerSettings().getPlayerName());
    }

    /**
     * Zeros the current water counter of the player.
     */
    public void zeroCurrentWaterCounter() {
        playerModel.setCurrentWaterCounter(0);
        playerSettingsService.updatePlayerWater(0,
                playerModel.getPlayerSettings().getPlayerName());

    }

}
