package models;

import javafx.scene.image.Image;
import javafx.scene.text.Text;

/**
 * This class keeps track of a template for the the plot.
 *
 * @author Aditya Varun Pratap
 * @version 1.0
 */
public class PlotTemplate {

    private PlotModel plotModel;


    private Image imgPlot;
    private Image imgName;
    private Image imgWater;
    private Image imgFertilizer;
    private Image imgPesticide;
    private Text txtWaterValue;
    private Text txtFertilizerValue;

    /***
     * Constructor for the plotmodel.
     * @param plotModel The plot model containing the plot information.
     * @param plotImage The plot's image.
     * @param plotNameImage The plot's image of its name.
     * @param waterValue The water value of the plot.
     * @param fertilizerValue The fertilizer value of the plot.
     */
    public PlotTemplate(PlotModel plotModel, Image plotImage, Image plotNameImage, String waterValue, String fertilizerValue) {
        this.plotModel = plotModel;

        this.imgPlot = plotImage;
        this.imgName = plotNameImage;
        this.imgWater = new Image("....\\dependencies\\images\\water_can.png");
        this.imgFertilizer = new Image("....\\dependencies\\images\\Fertilizer.png");
        this.imgPesticide = new Image("....\\dependencies\\images\\pesticide.png");

        this.txtWaterValue = new Text(waterValue);
        this.txtFertilizerValue = new Text(fertilizerValue);
    }

    public PlotModel getPlotModel() {
        return plotModel;
    }

    public void setPlotModel(PlotModel plotModel) {
        this.plotModel = plotModel;
    }

    public Image getPlotImage() {
        return imgPlot;
    }

    public void setPlotImage(Image imgPlot) {
        this.imgPlot = imgPlot;
    }

    public Image getImageName() {
        return imgName;
    }

    public void setImageName(Image imgName) {
        this.imgName = imgName;
    }

    public Text getWaterValue() {
        return txtWaterValue;
    }

    public void setWaterValue(String waterValue) {
        this.txtWaterValue.setText(waterValue);
    }

    public Text getFertilizerValue() {
        return txtFertilizerValue;
    }

    public void setFertilizerValue(String fertilizerValue) {
        this.txtFertilizerValue.setText(fertilizerValue);
    }

    public Image getImgWater() {
        return imgWater;
    }

    public Image getImgFertilizer() {
        return imgFertilizer;
    }

    public Image getImgPesticide() {
        return imgPesticide;
    }
}

