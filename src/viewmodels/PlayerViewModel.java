package viewmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.PlayerModel;
import models.UserModel;

/**
 * This view-model class will aid accessing certain types of players.
 * This view-model will act as an intermediary for Player and Game Admin.
 *
 * @author Rohan Kashiviswanathan
 * @version 1.0
 */
public class PlayerViewModel {

    private PlayerModel player;
    private UserModel user;

    /**
     * Gets the player's details.
     *
     * @return the user object.
     */
    public UserModel getPlayerDetails() {

        player = new PlayerModel(120);
        user = new UserModel(player);
        return user;
    }

    public void setPlayerDetails() {

    }

}
