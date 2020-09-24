package models;

import java.util.List;

/**
 * Model class for the season attribute of the game.
 * This class keeps track of the season's attributes and the corresponding methods.
 *
 * @author Aditya Varun Pratap
 * @version 1.0
 */
public class SeasonModel {

    private String plantGrowthModifier;
    private String seasonType;
    private List<AnimalModel> desirableAnimals;
    private List<CropModel> desirableCrops;

    /**
     * This 4-parameter constructor initializes the instance variables to the parameters.
     *
     * @param plantGrowthModifier The modifier to for plant growth.
     * @param seasonType          The type of season; Spring, Winter, Fall etc.
     * @param desirableAnimals    The desirable animals to be added to a particular season.
     * @param desirableCrops      The desirable crops to be added to a particular season.
     */
    public SeasonModel(String plantGrowthModifier, String seasonType,
                       List<AnimalModel> desirableAnimals, List<CropModel> desirableCrops) {
        this.plantGrowthModifier = plantGrowthModifier;
        this.seasonType = seasonType;
        this.desirableAnimals = desirableAnimals;
        this.desirableCrops = desirableCrops;
    }

    /**
     * Sets the plant growth modifier to the String parameter.
     *
     * @param plantGrowthModifier The plant growth modifier to be set.
     */
    public void setPlantGrowthModifier(String plantGrowthModifier) {
        this.plantGrowthModifier = plantGrowthModifier;
    }

    /**
     * Gets the plant growth modifier.
     *
     * @return The plant growth modifier of the season.
     */
    public String getPlantGrowthModifier() {
        return this.plantGrowthModifier;
    }

    /**
     * Sets the season type to the parameter.
     *
     * @param season The desired type of season.
     */
    public void setSeasonType(String season) {
        this.seasonType = season;
    }

    /**
     * Gets and returns the the season.
     *
     * @return The season type attribute of the SeasonModel object.
     */
    public String getSeasonType() {
        return this.seasonType;
    }

    /**
     * Sets the list desired animals of the season.
     *
     * @param animals The list of desired animals to be set for a particular season.
     */
    public void setDesirableAnimals(List<AnimalModel> animals) {
        this.desirableAnimals = animals;
    }

    /**
     * Gets and returns the list of desired animals
     *
     * @return The list of desired animals attribute of the SeasonModel object.
     */
    public List<AnimalModel> getDesirableAnimals() {
        return this.desirableAnimals;
    }

    /**
     * Sets the list desired crops of the season.
     *
     * @param crops The list of desired crops to be set for a particular season.
     */
    public void setDesirableCrops(List<CropModel> crops) {
        this.desirableCrops = crops;
    }

    /**
     * Gets and returns the list of desired crops.
     *
     * @return The list of desired crops attribute of the SeasonModel object.
     */
    public List<CropModel> getDesirableCrops() {
        return this.desirableCrops;
    }
}
