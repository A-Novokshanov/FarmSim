package viewmodels;

import models.PlayerModel;
import models.SeasonModel;
import models.CropModel;
import models.SettingModel;
import models.StorageModel;
import services.authentication.CreatePlayer;
import services.authentication.LoginPlayer;

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


    /**
     * Packs the data into the SettingModel object and passes to the services package.
     * Then, deserializes the data and adds to the database.
     *
     * @param playerModel The model class that contains all player details.
     */
    public void addSettingsToDatabase(PlayerModel playerModel) {
        CreatePlayer createPlayer = new CreatePlayer();
        createPlayer.setPlayerDetails(playerModel);
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
                                 int currentMoney) {

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
        this.playerModel = loginPlayer.getPlayerDetails(playerName);
    }

    /**
     * Returns the populated setting model object.
     *
     * @return The setting model object.
     */
    public PlayerModel getPlayer() {
        return this.playerModel;
    }

}
