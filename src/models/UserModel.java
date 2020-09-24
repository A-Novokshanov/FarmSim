package models;
/**
 The userModel class represents the model class of the User
 holding the instance variables and associating methods.
 * @author Andrew Novokshanov
 * @version 1.0
 */
public class UserModel {
    /** @param Represents the type of the user. */
    private String type;

    /** Constructs a User object with specified Type.
     @param type The type of user
     */
    public UserModel(String type) {
        this.type = type;
    }

    /** @return Gets and returns the Type attribute of a userModel object. */
    public String getUserType() {
        return this.type;
    }

    /** Sets the type of a userModel.
     @param type is the new desired type of a userModel object.
     */
    public void setUserType(String type) {
        this.type = type;
    }
}

