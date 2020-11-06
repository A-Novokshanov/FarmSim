package models;

/**
 * This class keeps track of a crop's attributes.
 *
 * @author Aditya Varun Pratap, Andrew Novokshanov
 * @version 1.0
 */
public class CropModel {

    private String cropName;
    private int cropQuantity;
    private double cropValue;
    private boolean hasPesticide;

    /**
     * This 3-parameter constructor initializes the instance variables to the parameters.
     *
     * @param cropName     The name of the crop.
     * @param cropQuantity The quantity of the specific crop.
     * @param cropValue    The value of each crop.
     */
    public CropModel(String cropName, int cropQuantity, double cropValue) {
        this.cropName = cropName;
        this.cropQuantity = cropQuantity;
        this.cropValue = cropValue;
        this.hasPesticide = false;
    }

    /**
     * Gets the name of the crop.
     *
     * @return The name attribute of the CropModel object.
     */
    public String getCropName() {
        return cropName;
    }

    /**
     * Sets the name of the crop.
     *
     * @param cropName The name to be set to the crop name attribute.
     */
    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    /**
     * Gets the quantity of the crop.
     *
     * @return The quantity attribute of the CropModel object.
     */
    public int getCropQuantity() {
        return cropQuantity;
    }

    /**
     * Sets the quantity of the crop.
     *
     * @param cropQuantity The quantity to be set to the crop name attribute.
     */
    public void setCropQuantity(int cropQuantity) {
        this.cropQuantity = cropQuantity;
    }

    /**
     * Gets the value of the crop.
     *
     * @return The value attribute of the CropModel object.
     */
    public double getCropValue() {
        return cropValue;
    }

    /**
     * Sets the value of the crop.
     *
     * @param cropValue The value to be set to the crop name attribute.
     */
    public void setCropValue(double cropValue) {
        this.cropValue = cropValue;
    }

    /**
     * Gets the hasPesticide boolean of the crop.
     *
     * @return The hasPesticide attribute of the CropModel object.
     */
    public boolean getHasPesticide() {
        return hasPesticide;
    }

    /**
     * Sets the hasPesticide boolean of the crop.
     *
     * @param hasPesticide The hasPesticide boolean to be set to the hasPesticide attribute.
     */
    public void setHasPesticide(boolean hasPesticide) {
        this.hasPesticide = hasPesticide;
    }
}
