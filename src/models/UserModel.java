package models;
/**
 The UserModel class represents the model class of the User
 holding the instance variables and associating methods.
 * @author Andrew Novokshanov
 * @version 1.0
 */
public class UserModel {
    private PlayerModel type;

    /** Constructs a UserModel object with specified Type.
     @param type The type of user
     */
    public UserModel(PlayerModel type) {
        this.type = type;
    }

    /** @return Gets and returns the Type attribute of a UserModel object. */
    public PlayerModel getPlayerModel() {
        return this.type;
    }

    /** Sets the type of a UserModel.
     @param type is the new desired type of a UerModel object.
     */
    public void setPlayerModel(PlayerModel type) {
        this.type = type;
    }
}

