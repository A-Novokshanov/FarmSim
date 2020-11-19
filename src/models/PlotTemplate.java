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
    private String plotImagePath;
    private String nameImagePath;
    private String waterValue;
    private String fertilizerValue;


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
     * @param plotImagePath The path to plot image.
     * @param nameImagePath The path to the name image.
     * @param waterValue The water value of the plot.
     * @param fertilizerValue The fertilizer value of the plot.
     */
    public PlotTemplate(PlotModel plotModel, String plotImagePath, String nameImagePath, String waterValue, String fertilizerValue) {
        this.plotModel = plotModel;
        this.plotImagePath = plotImagePath;
        this.nameImagePath = nameImagePath;
        this.waterValue = waterValue;
        this.fertilizerValue = fertilizerValue;

        this.imgPlot = new Image(plotImagePath);
        this.imgName = new Image(nameImagePath);
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

    public Image getImgPlot() {
        return imgPlot;
    }

    public void setImgPlot(Image imgPlot) {
        this.imgPlot = imgPlot;
    }

    public Image getImgName() {
        return imgName;
    }

    public void setImgName(Image imgName) {
        this.imgName = imgName;
    }

    public Text getTxtWaterValue() {
        return txtWaterValue;
    }

    public void setTxtWaterValue(Text txtWaterValue) {
        this.txtWaterValue = txtWaterValue;
    }

    public Text getTxtFertilizerValue() {
        return txtFertilizerValue;
    }

    public void setTxtFertilizerValue(Text txtFertilizerValue) {
        this.txtFertilizerValue = txtFertilizerValue;
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

