package models;

/**
 * The PlayerModel class represents the model class of the Player
 * holding the instance variables and associating methods.
 *
 * @author Andrew Novokshanov
 * @version 1.0
 */
public class PlayerModel {
    private int currentMoney;
    private SettingModel playerSettings;

    /**
     * Constructs a PlayerModel object.
     *
     * @param currentMoney The playerModels current money.
     * @param settingModel The player's current settings.
     */
    public PlayerModel(int currentMoney, SettingModel settingModel) {
        this.currentMoney = currentMoney;
        this.playerSettings = settingModel;
    }

    /**
     * @return Gets and returns the currentMoney attribute of a PlayerModel object.
     */
    public int getUserCurrentMoney() {
        return this.currentMoney;
    }

    /**
     * Sets the type of a PlayerModel.
     *
     * @param currentMoney is the new desired currentMoney of a PlayerModel object.
     */
    public void setUserCurrentMoney(int currentMoney) {
        this.currentMoney = currentMoney;
    }

    /**
     * Sets the player's settings.
     *
     * @param settings The settings/initial configurations of the player.
     */
    public void setPlayerSettings(SettingModel settings) {
        this.playerSettings = settings;
    }

    /**
     * Gets the player's settings.
     *
     * @return A SettingModel object that contains the player's settings.
     */
    public SettingModel getPlayerSettings() {
        return this.playerSettings;
    }
}

