package models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * This class keeps track of a template for the the plot.
 *
 * @author Aditya Varun Pratap
 * @version 1.0
 */
public class PlotTemplate {
    private PlotModel plotModel;
    private ImageView imgViewPlot;
    private ImageView imgViewName;
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
        this.imgViewPlot = new ImageView(plotImage);
        this.imgViewName = new ImageView(plotNameImage);
        this.txtWaterValue = new Text(waterValue);
        this.txtFertilizerValue = new Text(fertilizerValue);
    }

    public PlotModel getPlotModel() {
        return plotModel;
    }

    public void setPlotModel(PlotModel plotModel) {
        this.plotModel = plotModel;
    }

    public ImageView getPlotImageView() {
        return imgViewPlot;
    }

    public Image getPlotImage() {
        return  imgViewPlot.getImage();
    }

    public void setPlotImageView(Image imgPlot) {
        this.imgViewPlot.setImage(imgPlot);
    }

    public ImageView getNameImageView() {
        return imgViewName;
    }

    public Image getNameImage() {
        return imgViewName.getImage();
    }

    public void setNameImageView(Image imgName) {
        this.imgViewName.setImage(imgName);
    }

    public Text getWaterValueText() {
        return txtWaterValue;
    }

    public int getWaterValue() {
        return Integer.parseInt(txtWaterValue.getText());
    }

    public void setWaterValue(String waterValue) {
        this.txtWaterValue.setText(waterValue);
    }

    public void setWaterValueVisibility(Boolean bool) {
        this.txtWaterValue.setVisible(bool);
    }

    public Text getFertilizerValueText() {
        return txtFertilizerValue;
    }

    public void setFertilizerValue(String fertilizerValue) {
        this.txtFertilizerValue.setText(fertilizerValue);
    }
}

