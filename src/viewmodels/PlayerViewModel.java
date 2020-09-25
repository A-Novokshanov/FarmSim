package viewmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.PlayerModel;
import models.SeasonModel;

import models.UserModel;

public class PlayerViewModel {
    private StringProperty playerName = new SimpleStringProperty();
    private StringProperty startingDifficulty = new SimpleStringProperty();
    private UserModel startingSeedType;
    private SeasonModel startingSeasonType;

    /**
     * This constructor takes in the seedModel and seasonModel for the particular user.
     *
     * @param userMoney   The seed model object of the user.
     * @param userName The season model object of the user.
     */

    /**
     * Gets the player name string property to be binded in the view class.
     *
     * @return The player name string property.
     */
    public StringProperty getUserMoneyFromDatabase() {
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
    }

    public PlayerModel getPlayerDetails() {
        // TODO: Get money from database
        PlayerModel player = new PlayerModel();
        return player;
    }

}
