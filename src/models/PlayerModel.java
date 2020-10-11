package models;

/**
 * The PlayerModel class represents the model class of the Player
 * holding the instance variables and associating methods.
 *
 * @author Andrew Novokshanov
 * @version 1.0
 */
public class PlayerModel {
    private double currentMoney;
    private SettingModel playerSettings;
    private StorageModel storageModel;

    /**
     * Constructs a PlayerModel object.
     *
     * @param currentMoney The playerModels current money.
     * @param settingModel The player's current settings.
     * @param storageModel The storage/inventory of the user.
     */
    public PlayerModel(double currentMoney, SettingModel settingModel, StorageModel storageModel) {
        this.currentMoney = currentMoney;
        this.playerSettings = settingModel;
        this.storageModel = storageModel;
    }

    /**
     * @return Gets and returns the currentMoney attribute of a PlayerModel object.
     */
    public double getUserCurrentMoney() {
        return this.currentMoney;
    }

    /**
     * Sets the type of a PlayerModel.
     *
     * @param currentMoney is the new desired currentMoney of a PlayerModel object.
     */
    public void setUserCurrentMoney(double currentMoney) {
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

    /**
     * Sets the player's storage/inventory.
     *
     * @param storage The StorageModel object to be set.
     */
    public void setPlayerStorage(StorageModel storage) {
        this.storageModel = storage;
    }

    /**
     * Gets and returns the StorageModel object of the user.
     *
     * @return The StorageModel object that contains the user's storage.
     */
    public StorageModel getUserStorage() {
        System.out.println("Storage Model " + this.storageModel);
        return this.storageModel;
    }
}

