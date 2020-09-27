package viewmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.SeasonModel;
import models.SeedModel;
import models.SettingModel;

/**
 * This view-model class acts as an intermediary for the settings view, settings model,
 * and the settings service classes.
 *
 * @author Aditya Varun Pratap
 * @version 1.0l
 */
public class SettingViewModel {

    private StringProperty playerName = new SimpleStringProperty();
    private StringProperty startingDifficulty = new SimpleStringProperty();
    private SeedModel startingSeedType;
    private SeasonModel startingSeasonType;

    /**
     * This constructor is a default constructor.
     */
    public SettingViewModel() {
    }

    /**
     * This constructor takes in the seedModel and seasonModel for the particular user.
     *
     * @param seedModel   The seed model object of the user.
     * @param seasonModel The season model object of the user.
     */
    public SettingViewModel(SeedModel seedModel, SeasonModel seasonModel) {
        this.startingSeedType = seedModel;
        this.startingSeasonType = seasonModel;
    }

    /**
     * Gets the player name string property to be binded in the view class.
     *
     * @return The player name string property.
     */
    public StringProperty getPlayerName() {
        return this.playerName;
    }

    /**
     * Gets the player's starting difficulty string property to be binded in the view class.
     *
     * @return The player's starting difficulty string property.
     */
    public StringProperty getStartingDifficulty() {
        return this.startingDifficulty;
    }

    /**
     * Packs the data into the SettingModel object and passes to the services package.
     * Then, deserializes the data and adds to the database.
     */
    public void addSettingsToDatabase() {
        // TODO Add user settings to the database.
        SettingModel settingModel = new SettingModel(startingSeasonType,
                startingSeedType, startingDifficulty.toString(), playerName.toString());
    }
}
