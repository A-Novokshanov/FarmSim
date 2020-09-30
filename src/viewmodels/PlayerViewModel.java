package viewmodels;

import models.PlayerModel;
import models.SeasonModel;
import models.SeedModel;
import models.SettingModel;
import services.create.CreatePlayer;

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
    private int currentMoney;


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
     * Sets the players details/ initial configurations.
     *
     * @param seedModel          The seed type the player chooses.
     * @param seasonModel        The season player chooses.
     * @param playerName         The name of the player.
     * @param startingDifficulty The starting difficulty the player chooses.
     * @param currentMoney       The current money of the player.
     */
    public void setPlayerDetails(SeedModel seedModel, SeasonModel seasonModel,
                                 String playerName, String startingDifficulty, int currentMoney) {

        this.settingModel = new SettingModel(seasonModel, seedModel,
                startingDifficulty, playerName);
        this.playerModel = new PlayerModel(currentMoney, this.settingModel);
        this.addSettingsToDatabase(this.playerModel);

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
