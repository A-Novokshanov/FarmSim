package models;

/**
 * The FarmModel class represents the model class of the Farm
 * holding the instance variables and associating methods.
 *
 * @author Andrew Novokshanov, Aditya Varun Pratap
 * @version 1.0
 */
public class FarmModel {
    private int day;
    private int size;

    /**
     * Constructs a FarmModel object.
     *
     * @param day The FarmModels current day
     */
    public FarmModel(int day) {
        this.day = day;
    }

    /**
     * @return Gets and returns the date attribute of a FarmModel object.
     */
    public int getFarmDay() {
        return this.day;
    }

    /**
     * Sets the date of a FarmModel.
     *
     * @param day is the new desired date of a FarmModel object.
     */
    public void setFarmDay(int day) {
        this.day = day;
    }

    /**
     * Sets the size of the farm;
     *
     * @param size The size to increase the farm by.
     */
    public void increaseSizeBy(int size) {
        this.size += size;
    }

    /**
     * Gets the size of the farm.
     *
     * @return The size of the farm.
     */
    public int getSize() {
        return this.size;
    }
}