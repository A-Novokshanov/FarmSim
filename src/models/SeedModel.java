package models;

public class SeedModel {

    private String seedType;

    /**
     * This constructor initializes the instance variables to the parameter.
     *
     * @param seedType The type/kind of seed.
     */
    public SeedModel(String seedType) {
        this.seedType = seedType;
    }

    /**
     * Gets and returns the type of seed.
     *
     * @return The seed type attribute of the SeedModel object.
     */
    public String getSeedType() {
        return seedType;
    }

    /**
     * Sets the type of seed.
     *
     * @param seedType The type of seed to be set.
     */
    public void setSeedType(String seedType) {
        this.seedType = seedType;
    }
}
