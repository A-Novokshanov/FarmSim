package models;

/**
 * The settingModel class represents the model class of the Setting
 * holding the instance variables and associating methods.
 *
 * @author Andrew Novokshanov
 * @version 1.0
 */
public class SettingModel {
    /**
     *  Represents the type of the setting.
     */
    private SeasonModel startingSeason;
    private CropModel startingCropType;
    private String startingDifficulty;
    private String playerName;

    /**
     * Constructs a settings object with specified Type.
     *
     * @param startingSeason     The starting season of the game
     * @param startingCropType   The starting crop type of the game
     * @param startingDifficulty The starting difficulty of the game
     * @param playerName         The player's starting name
     */
    public SettingModel(SeasonModel startingSeason, CropModel startingCropType,
                        String startingDifficulty, String playerName) {
        this.startingSeason = startingSeason;
        this.startingCropType = startingCropType;
        this.startingDifficulty = startingDifficulty;
        this.playerName = playerName;
    }

    /**
     * @return Gets and returns the startingSeason attribute of a settingModel object.
     */
    public SeasonModel getStartingSeason() {
        return this.startingSeason;
    }

    /**
     * Sets the startingSeason attribute of a settingModel object.
     *
     * @param startingSeason is the new desired startingSeason attribute of a settingModel object.
     */
    public void setStartingSeason(SeasonModel startingSeason) {
        this.startingSeason = startingSeason;
    }

    /**
     * @return Gets and returns the startingCropType attribute of a settingModel object.
     */
    public CropModel getStartingCropType() {
        return this.startingCropType;
    }

    /**
     * Sets the startingCropType attribute of a settingModel object.
     *
     * @param startingCropType is the new desired startingCropType attribute of a
     *                         settingModel object.
     */
    public void setStartingCropType(CropModel startingCropType) {
        this.startingCropType = startingCropType;
    }

    /**
     * @return Gets and returns the startingDifficulty attribute of a settingModel object.
     */
    public String getStartingDifficulty() {
        return this.startingDifficulty;
    }

    /**
     * Sets the startingDifficulty attribute of a settingModel object.
     *
     * @param startingDifficulty is the new desired startingDifficulty attribute
     *                           of a settingModel object.
     */
    public void setStartingDifficulty(String startingDifficulty) {
        this.startingDifficulty = startingDifficulty;
    }

    /**
     * @return Gets and returns the playerName attribute of a settingModel object.
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * Sets the playerName attribute of a settingModel object.
     *
     * @param playerName is the new desired playerName attribute of a settingModel object.
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}

