package viewmodels;

import models.SeasonModel;
import models.SeedModel;
import models.SettingModel;
import services.create.CreatePlayer;

/**
 * This view-model class acts as an intermediary for the settings view, settings model,
 * and the settings service classes.
 *
 * @author Aditya Varun Pratap
 * @version 1.1
 */
public class SettingViewModel {

    private String playerName;
    private String startingDifficulty;
    private SeedModel startingSeedType;
    private SeasonModel startingSeasonType;
    private SettingModel settingModel;


    /**
     * Packs the data into the SettingModel object and passes to the services package.
     * Then, deserializes the data and adds to the database.
     *
     * @param settingModel The model class that contains all player details.
     */
    public void addSettingsToDatabase(SettingModel settingModel) {
        settingModel = new SettingModel(startingSeasonType,
                startingSeedType, startingDifficulty.toString(), playerName.toString());
        CreatePlayer createPlayer = new CreatePlayer();
        createPlayer.setPlayerDetails(settingModel);
    }

    /**
     * Sets the players details/ initial configurations.
     *
     * @param seedModel          The seed type the player chooses.
     * @param seasonModel        The season player chooses.
     * @param playerName         The name of the player.
     * @param startingDifficulty The starting difficulty the player chooses.
     */
    public void setPlayerDetails(SeedModel seedModel, SeasonModel seasonModel,
                                 String playerName, String startingDifficulty) {

        this.startingSeedType = seedModel;
        this.startingSeasonType = seasonModel;
        this.playerName = playerName;
        this.startingDifficulty = startingDifficulty;

        this.settingModel = new SettingModel(seasonModel, seedModel,
                startingDifficulty, playerName);

        this.addSettingsToDatabase(this.settingModel);

    }

    /**
     * Returns the populated setting model object.
     *
     * @return The setting model object.
     */
    public SettingModel getPlayerSettings() {
        return this.settingModel;
    }

}
