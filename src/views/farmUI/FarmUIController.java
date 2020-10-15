package views.farmUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.CropModel;
import models.PlotModel;
import viewmodels.PlayerViewModel;
import viewmodels.PlotViewModel;
import viewmodels.StorageViewModel;
import views.marketPlace.MarketPlace;

import java.io.IOException;
import java.util.ArrayList;

public class FarmUIController {
    @FXML
    private Text money;
    @FXML
    private Text dayNum;
    @FXML
    private Pane inventoryScreen;
    @FXML
    private Circle dayCounter;
    @FXML
    private Circle sun;
    @FXML
    private Line sunProgressBar;
    @FXML
    private Text numTomatoes;
    @FXML
    private Text numPotatoes;
    @FXML
    private Text numCorn;

    @FXML
    private ImageView plot1Img;
    @FXML
    private ImageView plot2Img;
    @FXML
    private ImageView plot3Img;
    @FXML
    private ImageView plot4Img;
    @FXML
    private ImageView plot5Img;
    @FXML
    private ImageView plot6Img;
    @FXML
    private ImageView plot7Img;
    @FXML
    private ImageView plot8Img;
    @FXML
    private ImageView plot9Img;
    @FXML
    private ImageView plot10Img;

    @FXML
    private ImageView plotName1Img;
    @FXML
    private ImageView plotName2Img;
    @FXML
    private ImageView plotName3Img;
    @FXML
    private ImageView plotName4Img;
    @FXML
    private ImageView plotName5Img;
    @FXML
    private ImageView plotName6Img;
    @FXML
    private ImageView plotName7Img;
    @FXML
    private ImageView plotName8Img;
    @FXML
    private ImageView plotName9Img;
    @FXML
    private ImageView plotName10Img;

    private PlotModel plot1;
    private PlotModel plot2;
    private PlotModel plot3;
    private PlotModel plot4;
    private PlotModel plot5;
    private PlotModel plot6;
    private PlotModel plot7;
    private PlotModel plot8;
    private PlotModel plot9;
    private PlotModel plot10;

    private final Image dirtImg = new Image("@../../dependencies/images/Dirt.png",
            400.0, 300.0, true, false);
    private final Image seedImg = new Image("@../../dependencies/images/Seed.png",
            400.0, 300.0, true, false);
    private final Image immature1Img = new Image("@../../dependencies/images/Immature_1.png",
            400.0, 300.0, true, false);
    private final Image immature2Img = new Image("@../../dependencies/images/Immature_2.png",
            400.0, 300.0, true, false);
    private final Image matureImg = new Image("@../../dependencies/images/Mature.png",
            400.0, 300.0, true, false);
    private final Image emptyNameImg = new Image("@../../dependencies/images/Crop_Bar_Empty.png",
            400.0, 300.0, true, false);
    private final Image cornNameImg = new Image("@../../dependencies/images/Crop_Bar_Corn.png",
            400.0, 300.0, true, false);
    private final Image potatoNameImg = new Image("@../../dependencies/images/Crop_Bar_Potato.png",
            400.0, 300.0, true, false);
    private final Image tomatoNameImg = new Image("@../../dependencies/images/Crop_Bar_Tomato.png",
            400.0, 300.0, true, false);

    private int num = 1;

    private PlayerViewModel playerViewModel;
    private StorageViewModel storageViewModel;
    private PlotViewModel plotViewModel;
    private ArrayList<PlotModel> plotModels = new ArrayList<PlotModel>(10);
    private ArrayList<ImageView> plotModelNames = new ArrayList<ImageView>(10);
    private String name;

    /**
     * Initializes data with the parameter
     *
     * @param playerViewModel Setting View Model to access player details.
     * @param playerName      The name of the current player.
     */
    public void initData(PlayerViewModel playerViewModel, String playerName) {
        //playerViewModel.getPlayerInformationFromDatabase(playerName);
        this.money.setText("$ " + playerViewModel.getPlayer().getUserCurrentMoney());
        this.storageViewModel = new StorageViewModel(playerViewModel);
        this.plotViewModel = new PlotViewModel();
        this.playerViewModel = playerViewModel;
        this.name = playerName;
        setUpPlotModels(
                playerViewModel.getPlayer().getPlayerSettings().getStartingCropType());
        createPlotModels();
        createPlotModelNames();
    }

    public void initData2(PlayerViewModel playerViewModel, String playerName,
                          ArrayList<PlotModel> plotModels, ArrayList<ImageView> plotModelNames, Text dayNum) {
        //playerViewModel.getPlayerInformationFromDatabase(playerName);
        this.money.setText("$ " + playerViewModel.getPlayer().getUserCurrentMoney());
        this.storageViewModel = new StorageViewModel(playerViewModel);
        this.plotViewModel = new PlotViewModel();
        this.playerViewModel = playerViewModel;
        this.dayNum = dayNum;
        this.name = playerName;
        setUpPlotModels(plotModels, plotModelNames,
                playerViewModel.getPlayer().getPlayerSettings().getStartingCropType());
        createPlotModels();
        createPlotModelNames();
    }

    /**
     * Makes Inventory Screen Invisible if exit button is clicked
     *
     * @param mouseEvent a mouse click on the exit button
     */
    public void toggleInventoryScreenVisibility(MouseEvent mouseEvent) {
        inventoryScreen.setVisible(!inventoryScreen.isVisible());
        dayCounter.setVisible(!dayCounter.isVisible());
        dayNum.setVisible(!dayNum.isVisible());
        sun.setVisible(!sun.isVisible());
        sunProgressBar.setVisible(!sunProgressBar.isVisible());
        numCorn.setText(
                String.valueOf(storageViewModel.userInventory().get(0).getCropQuantity()));
        numTomatoes.setText(
                String.valueOf(storageViewModel.userInventory().get(2).getCropQuantity())
        );
        numPotatoes.setText(
                String.valueOf(storageViewModel.userInventory().get(1).getCropQuantity())
        );
    }

    /**
     * @param mouseEvent is the mouse trigger event
     */
    public void goToMarket(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().
                getResource("../marketPlace/MarketPlace.fxml"));
        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        currentStage.close();
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(
                    new Scene(loader.load())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        MarketPlace marketPlace = loader.getController();
        marketPlace.initData(mouseEvent, playerViewModel, storageViewModel, plotModels, plotModelNames, name, dayNum);

        stage.setTitle("Market");
        stage.show();
    }

    /**
     * Updates day number.
     *
     * @param mouseEvent (Prototype) Clicking on a day switches the current day number.
     */
    public void updateDay(MouseEvent mouseEvent) {
        num++;
        dayNum.setText("Day " + num);
        incrementAllPlotDays();
    }

    public void setUpPlotModels(CropModel cropModel) {
        plot1 = plotViewModel.populatePlot(cropModel);
        checkMaturity(plot1, plot1Img);
        plot2 = plotViewModel.populatePlot(cropModel);
        checkMaturity(plot2, plot2Img);
        plot3 = plotViewModel.populatePlot(cropModel);
        checkMaturity(plot3, plot3Img);
        plot4 = plotViewModel.populatePlot(cropModel);
        checkMaturity(plot4, plot4Img);
        plot5 = plotViewModel.populatePlot(cropModel);
        checkMaturity(plot5, plot5Img);
        plot6 = plotViewModel.populatePlot(cropModel);
        checkMaturity(plot6, plot6Img);
        plot7 = plotViewModel.populatePlot(cropModel);
        checkMaturity(plot7, plot7Img);
        plot8 = plotViewModel.populatePlot(cropModel);
        checkMaturity(plot8, plot8Img);
        plot9 = plotViewModel.populatePlot(cropModel);
        checkMaturity(plot9, plot9Img);
        plot10 = plotViewModel.populatePlot(cropModel);
        checkMaturity(plot10, plot10Img);
        setUpPlotName(plotName1Img, cropModel.getCropName());
        setUpPlotName(plotName2Img, cropModel.getCropName());
        setUpPlotName(plotName3Img, cropModel.getCropName());
        setUpPlotName(plotName4Img, cropModel.getCropName());
        setUpPlotName(plotName5Img, cropModel.getCropName());
        setUpPlotName(plotName6Img, cropModel.getCropName());
        setUpPlotName(plotName7Img, cropModel.getCropName());
        setUpPlotName(plotName8Img, cropModel.getCropName());
        setUpPlotName(plotName9Img, cropModel.getCropName());
        setUpPlotName(plotName10Img, cropModel.getCropName());
    }

    public void setUpPlotModels(ArrayList<PlotModel> plotModels, ArrayList<ImageView> plotModelNames, CropModel cropModel) {
        plot1 = plotModels.get(0);
        plot2 = plotModels.get(1);
        plot3 = plotModels.get(2);
        plot4 = plotModels.get(3);
        plot5 = plotModels.get(4);
        plot6 = plotModels.get(5);
        plot7 = plotModels.get(6);
        plot8 = plotModels.get(7);
        plot9 = plotModels.get(8);
        plot10 = plotModels.get(9);
        plotName1Img = plotModelNames.get(0);
        plotName2Img = plotModelNames.get(1);
        plotName3Img = plotModelNames.get(2);
        plotName4Img = plotModelNames.get(3);
        plotName5Img = plotModelNames.get(4);
        plotName6Img = plotModelNames.get(5);
        plotName7Img = plotModelNames.get(6);
        plotName8Img = plotModelNames.get(7);
        plotName9Img = plotModelNames.get(8);
        plotName10Img = plotModelNames.get(9);
        checkMaturity(plot1, plot1Img);
        checkMaturity(plot2, plot2Img);
        checkMaturity(plot3, plot3Img);
        checkMaturity(plot4, plot4Img);
        checkMaturity(plot5, plot5Img);
        checkMaturity(plot6, plot6Img);
        checkMaturity(plot7, plot7Img);
        checkMaturity(plot8, plot8Img);
        checkMaturity(plot9, plot9Img);
        checkMaturity(plot10, plot10Img);
    }

    public void incrementAllPlotDays() {
        plotViewModel.incrementPlotDaysOld(plot1);
        checkMaturity(plot1, plot1Img);
        plotViewModel.incrementPlotDaysOld(plot2);
        checkMaturity(plot2, plot2Img);
        plotViewModel.incrementPlotDaysOld(plot3);
        checkMaturity(plot3, plot3Img);
        plotViewModel.incrementPlotDaysOld(plot4);
        checkMaturity(plot4, plot4Img);
        plotViewModel.incrementPlotDaysOld(plot5);
        checkMaturity(plot5, plot5Img);
        plotViewModel.incrementPlotDaysOld(plot6);
        checkMaturity(plot6, plot6Img);
        plotViewModel.incrementPlotDaysOld(plot7);
        checkMaturity(plot7, plot7Img);
        plotViewModel.incrementPlotDaysOld(plot8);
        checkMaturity(plot8, plot8Img);
        plotViewModel.incrementPlotDaysOld(plot9);
        checkMaturity(plot9, plot9Img);
        plotViewModel.incrementPlotDaysOld(plot10);
        checkMaturity(plot10, plot10Img);
    }

    public void createPlotModels() {
        plotModels.add(plot1);
        plotModels.add(plot2);
        plotModels.add(plot3);
        plotModels.add(plot4);
        plotModels.add(plot5);
        plotModels.add(plot6);
        plotModels.add(plot7);
        plotModels.add(plot8);
        plotModels.add(plot9);
        plotModels.add(plot10);
    }

    public void createPlotModelNames() {
        plotModelNames.add(plotName1Img);
        plotModelNames.add(plotName2Img);
        plotModelNames.add(plotName3Img);
        plotModelNames.add(plotName4Img);
        plotModelNames.add(plotName5Img);
        plotModelNames.add(plotName6Img);
        plotModelNames.add(plotName7Img);
        plotModelNames.add(plotName8Img);
        plotModelNames.add(plotName9Img);
        plotModelNames.add(plotName10Img);
    }

    public void checkMaturity(PlotModel plotModel, ImageView plotImg) {
        if (plotModel.getCropInPlot() != null) {
            if (plotModel.getDaysOld() < 2) {
                plotImg.setImage(seedImg);
            } else if (plotModel.getDaysOld() < 6) {
                plotImg.setImage(immature1Img);
            } else if (plotModel.getDaysOld() < 10) {
                plotImg.setImage(immature2Img);
            } else {
                plotImg.setImage(matureImg);
            }
        }
    }

    public void harvestCrop(PlotModel harvestedPlot,
                            ImageView harvestedPlotImg, ImageView harvestedPlotNameImg) {
        if (harvestedPlot.getDaysOld() >= 10) {
            this.plotViewModel.harvestPlot(harvestedPlot, this.playerViewModel);
            harvestedPlotImg.setImage(dirtImg);
            harvestedPlotNameImg.setImage(emptyNameImg);
        }
    }

    public void harvestCropPlot1() {
        harvestCrop(plot1, plot1Img, plotName1Img);
    }

    public void harvestCropPlot2() {
        harvestCrop(plot2, plot2Img, plotName2Img);
    }

    public void harvestCropPlot3() {
        harvestCrop(plot3, plot3Img, plotName3Img);
    }

    public void harvestCropPlot4() {
        harvestCrop(plot4, plot4Img, plotName4Img);
    }

    public void harvestCropPlot5() {
        harvestCrop(plot5, plot5Img, plotName5Img);
    }

    public void harvestCropPlot6() {
        harvestCrop(plot6, plot6Img, plotName6Img);
    }

    public void harvestCropPlot7() {
        harvestCrop(plot7, plot7Img, plotName7Img);
    }

    public void harvestCropPlot8() {
        harvestCrop(plot8, plot8Img, plotName8Img);
    }

    public void harvestCropPlot9() {
        harvestCrop(plot9, plot9Img, plotName9Img);
    }

    public void harvestCropPlot10() {
        harvestCrop(plot10, plot10Img, plotName10Img);
    }

    public void setUpPlotName(ImageView plotName, String str) {
        if (str.equals("Corn")) {
            plotName.setImage(cornNameImg);
        } else if (str.equals("Potato")) {
            plotName.setImage(potatoNameImg);
        } else {
            plotName.setImage(tomatoNameImg);
        }
    }
}
