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
    private int days;
    private final int maxHarvestsPerDay;
    private final int maxWateringPerDay;
    private int currentHarvestCounter;
    private int currentWaterCounter;

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
        this.days = 1;
        this.maxHarvestsPerDay = 5;
        this.maxWateringPerDay = 10;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getDays() {
        return this.days;
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
        return this.storageModel;
    }

    /**
     * Gets the max harvest number per day for the farmer.
     *
     * @return The maximum number of harvests.
     */
    public int getMaxHarvestsPerDay() {
        return this.maxHarvestsPerDay;
    }

    /**
     * Gets the max watering cycles per day for the framer.
     *
     * @return The maximum number of watering cycles.
     */
    public int getMaxWateringPerDay() {
        return this.maxWateringPerDay;
    }

    /**
     * Sets the current harvest counter of the player.
     *
     * @param harvest The amount to increase the harvest counter by.
     */
    public void setCurrentHarvestCounter(int harvest) {
        this.currentHarvestCounter = harvest;
    }

    /**
     * Sets the current water counter of the player.
     *
     * @param water The amount to increase the water counter by.
     */
    public void setCurrentWaterCounter(int water) {
        this.currentWaterCounter = water;
    }

    /**
     * Gets the current harvest counter of the player.
     *
     * @return The current harvest counter.
     */
    public int getCurrentHarvestCounter() {
        return this.currentHarvestCounter;
    }

    /**
     * Gets the current water counter of the player.
     *
     * @return The current water counter.
     */
    public int getCurrentWaterCounter() {
        return this.currentWaterCounter;
    }
}

