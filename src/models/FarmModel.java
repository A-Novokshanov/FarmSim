package models;
/**
 The FarmModel class represents the model class of the Player
 holding the instance variables and associating methods.
 * @author Andrew Novokshanov
 * @version 1.0
 */
public class FarmModel {
    private int date;
    /** Constructs a FarmModel object.
     * @param date The FarmModels current date
     */
    public FarmModel(int date) {
        this.date = date;
    }

    /** @return Gets and returns the date attribute of a FarmModel object. */
    public int getFarmDate() {
        return this.date;
    }

    /** Sets the date of a FarmModel.
     @param date is the new desired date of a FarmModel object.
     */
    public void setFarmDate(int date) {
        this.date = date;
    }
}