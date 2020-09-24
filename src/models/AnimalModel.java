package models;

import java.sql.Time;

/**
 * This model class keeps track of an animal's attributes.
 *
 * @author Aditya Varun Pratap
 * @version 1.0
 */
public class AnimalModel {

    private double purchasePrice;
    private double sellPrice;
    private Time timeToGrow;
    private String animalType;

    /**
     * This 4-parameter constructor initializes the instance variables to the parameters.
     *
     * @param purchasePrice The price of the animal to purchase it.
     * @param sellPrice     The price of the animal to sell it.
     * @param timeToGrow    The time it takes for the animal to grow.
     * @param animalType    The type of animal.
     */
    public AnimalModel(double purchasePrice, double sellPrice, Time timeToGrow, String animalType) {
        this.purchasePrice = purchasePrice;
        this.sellPrice = sellPrice;
        this.timeToGrow = timeToGrow;
        this.animalType = animalType;
    }

    /**
     * Gets and returns the purchase price of the animal.
     *
     * @return The purchase price attribute of the AnimalModel object.
     */
    public double getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * Sets the purchase price to the parameter.
     *
     * @param purchasePrice The price to purchase the animal at.
     */
    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /**
     * Gets and returns the selling price of the animal.
     *
     * @return The sell price attribute of the AnimalModel object.
     */
    public double getSellPrice() {
        return sellPrice;
    }

    /**
     * Sets the sell price to the parameter.
     *
     * @param sellPrice The price to sell the animal at.
     */
    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    /**
     * Gets and returns the time it takes for the animal to grow.
     *
     * @return The time to grow attribute of the AnimalModel object.
     */
    public Time getTimeToGrow() {
        return timeToGrow;
    }

    /**
     * Sets the time it takes for the animal to grow to the parameter.
     *
     * @param timeToGrow The time it takes for the animal to grow.
     */
    public void setTimeToGrow(Time timeToGrow) {
        this.timeToGrow = timeToGrow;
    }

    /**
     * Gets and returns the type of the animal.
     *
     * @return The animal type attribute of the AnimalModel object.
     */
    public String getAnimalType() {
        return animalType;
    }

    /**
     * Sets the animal type to the parameter.
     *
     * @param animalType The kind/type of animal an animal is.
     */
    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }


}
