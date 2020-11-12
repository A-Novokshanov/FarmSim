package models;

import java.util.Random;

/**
 * The PlotModel class represents the model class of the Plot
 * holding the instance variables and associating methods.
 *
 * @author Andrew Novokshanov
 * @version 1.0
 */
public class PlotModel {
    private CropModel cropInPlot;
    private int daysOld;
    private int daysSinceWater;
    private int waterValue;
    private int fertilizerLevel;
    private int plotIdentifier;
    private String plotStage;

    /**
     * This method gets the plot's current stage.
     *
     * @return the plot stage.
     */
    public String getPlotStage() {
        return plotStage;
    }

    /**
     * This method sets the current plot's stage.
     *
     * @param plotStage is the stage that the plot is at currently.
     */
    public void setPlotStage(String plotStage) {
        this.plotStage = plotStage;
    }

    /**
     * Constructs a PlotModel object.
     *
     * @param cropInPlot The type of crop in the PlotModel
     * @param daysOld    how long its been since crop planted
     */
    public PlotModel(CropModel cropInPlot, int daysOld) {
        this.cropInPlot = cropInPlot;
        this.daysOld = daysOld;
        this.waterValue = 3;
        this.fertilizerLevel = 0;
        Random randomID = new Random();
        this.plotIdentifier = randomID.nextInt(100000);
    }

    /**
     * @return Gets and returns the type of crop in a PlotModel object.
     */
    public CropModel getCropInPlot() {
        return this.cropInPlot;
    }

    /**
     * Sets the type of crop in a PlotModel object.
     *
     * @param cropInPlot is the new desired type of crop of a PlotModel object.
     */
    public void setCropInPlot(CropModel cropInPlot) {
        this.cropInPlot = cropInPlot;
    }

    /**
     * This is the thing that sets the plot identifier.
     *
     * @param identifer the identifier we want to set it as.
     */
    public void setPlotIdentifier(int identifer) {
        this.plotIdentifier = identifer;
    }

    /**
     * @return Gets and returns the daysOld of a PlotModel object.
     */
    public int getDaysOld() {
        return daysOld;
    }

    /**
     * Sets the daysOld of a PlotModel object.
     *
     * @param daysOld is the new desired daysOld of a PlotModel object.
     */
    public void setDaysOld(int daysOld) {
        this.daysOld = daysOld;
    }

    /**
     * Gets and returns the daysSinceWater of a PlotModel object.
     *
     * @return Returns a number based on days watered.
     */
    public int getDaysSinceWater() {
        return this.daysSinceWater;
    }

    /**
     * This method gets the unique, random plot identifier.
     *
     * @return The plot identifier.
     */
    public int getPlotIdentifier() {
        return this.plotIdentifier;
    }

    /**
     * Sets the daysSinceWater of a PlotModel object.
     *
     * @param daysSinceWater is the new desired daysSinceWater of a PlotModel object.
     */
    public void setDaysSinceWater(int daysSinceWater) {
        this.daysSinceWater = daysSinceWater;
    }

    /**
     * Gets and returns the waterValue of a PlotModel object.
     *
     * @return Returns a number based on waterValue of a plot.
     */
    public int getWaterValue() {
        return this.waterValue;
    }

    /**
     * Sets the waterValue of a PlotModel object.
     *
     * @param waterValue is the new desired waterValue of a plot.
     */
    public void setWaterValue(int waterValue) {
        this.waterValue = waterValue;
    }

    /**
     * @return Gets and returns the fertilizer level in a PlotModel object.
     */
    public int getFertilizerLevel() {
        return this.fertilizerLevel;
    }

    /**
     * Sets the type of crop in a PlotModel object.
     *
     * @param fertilizerLevel is the new fertilizer lever of a PlotModel object.
     */
    public void setFertilizerLevel(int fertilizerLevel) {
        this.fertilizerLevel = fertilizerLevel;
    }
}
