package models;
/**
 The PlotModel class represents the model class of the Plot
 holding the instance variables and associating methods.
 * @author Andrew Novokshanov
 * @version 1.0
 */
public class PlotModel {
    private CropModel cropInPlot;
    private int daysOld;
    private int daysSinceWater;

    /** Constructs a PlotModel object.
     * @param cropInPlot The type of crop in the PlotModel
     * @param daysOld how long its been since crop planted
     */
    public PlotModel(CropModel cropInPlot, int daysOld) {
        this.cropInPlot = cropInPlot;
        this.daysOld = daysOld;
    }

    /** @return Gets and returns the type of crop in a PlotModel object. */
    public CropModel getCropInPlot() {
        return this.cropInPlot;
    }

    /** Sets the type of crop in a PlotModel object.
     @param cropInPlot is the new desired type of crop of a PlotModel object.
     */
    public void setCropInPlot(CropModel cropInPlot) {
        this.cropInPlot = cropInPlot;
    }

    /** @return Gets and returns the daysOld of a PlotModel object. */
    public int getDaysOld() {
        return daysOld;
    }

    /** Sets the daysOld of a PlotModel object.
     @param daysOld is the new desired daysOld of a PlotModel object.
     */
    public void setDaysOld(int daysOld) {
        this.daysOld = daysOld;
    }

    /** Gets and returns the daysSinceWater of a PlotModel object.
     */
    public int getDaysSinceWater() {
        return this.daysSinceWater;
    }

    /** Sets the daysSinceWater of a PlotModel object.
     @param daysSinceWater is the new desired daysSinceWater of a PlotModel object.
     */
    public void setDaysSinceWater(int daysSinceWater) {
        this.daysSinceWater = daysSinceWater;
    }
}
