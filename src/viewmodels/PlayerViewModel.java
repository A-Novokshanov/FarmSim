package viewmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.PlayerModel;

import models.UserModel;

public class PlayerViewModel {
    private StringProperty playerName = new SimpleStringProperty();
    private StringProperty startingDifficulty = new SimpleStringProperty();

    PlayerModel player;
    UserModel user;

    /**
     * Gets the player's details.
     *
     * @return the user object.
     */
    public UserModel getPlayerDetails() {
        // TODO: Get money from database
        player = new PlayerModel(100);
        user = new UserModel(player);
        return user;
    }

}
