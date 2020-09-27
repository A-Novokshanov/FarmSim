package models;
/**
 The PlayerModel class represents the model class of the Player
 holding the instance variables and associating methods.
 * @author Andrew Novokshanov
 * @version 1.0
 */
public class PlayerModel {
    private int currentMoney;
    /** Constructs a PlayerModel object.
     * @param currentMoney The playerModels current money
     */
    public PlayerModel(int currentMoney) {
        this.currentMoney = currentMoney;
    }

    /** @return Gets and returns the currentMoney attribute of a PlayerModel object. */
    public int getUserCurrentMoney() {
        return this.currentMoney;
    }

    /** Sets the type of a PlayerModel.
     @param currentMoney is the new desired currentMoney of a PlayerModel object.
     */
    public void setUserCurrentMoney(int currentMoney) {
        this.currentMoney = currentMoney;
    }
}

