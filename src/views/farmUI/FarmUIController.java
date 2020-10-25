package views.farmUI;

import com.jfoenix.controls.JFXButton;
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

/**
 * A view class that controls the UI elements for the main farm screen.
 *
 * @author Matthew Farias, Shaun Jacob
 * @version 0.3
 */
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
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private Pane pane5;
    @FXML
    private Pane pane6;
    @FXML
    private Pane pane7;
    @FXML
    private Pane pane8;
    @FXML
    private Pane pane9;
    @FXML
    private Pane pane10;

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

    @FXML
    private JFXButton btnCornPlant;
    @FXML
    private JFXButton btnPotatoPlant;
    @FXML
    private JFXButton btnTomatoPlant;

    @FXML
    private Text txtDaysSinceWater1;
    @FXML
    private Text txtDaysSinceWater2;
    @FXML
    private Text txtDaysSinceWater3;
    @FXML
    private Text txtDaysSinceWater4;
    @FXML
    private Text txtDaysSinceWater5;
    @FXML
    private Text txtDaysSinceWater6;
    @FXML
    private Text txtDaysSinceWater7;
    @FXML
    private Text txtDaysSinceWater8;
    @FXML
    private Text txtDaysSinceWater9;
    @FXML
    private Text txtDaysSinceWater10;

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
    private final Image witheredImg = new Image("@../../dependencies/images/Withered.png",
            400.0, 300.0, true, false);
    private final Image emptyNameImg = new Image("@../../dependencies/images/Crop_Bar_Empty.png",
            400.0, 300.0, true, false);
    private final Image cornNameImg = new Image("@../../dependencies/images/Crop_Bar_Corn.png",
            400.0, 300.0, true, false);
    private final Image potatoNameImg = new Image("@../../dependencies/images/Crop_Bar_Potato.png",
            400.0, 300.0, true, false);
    private final Image tomatoNameImg = new Image("@../../dependencies/images/Crop_Bar_Tomato.png",
            400.0, 300.0, true, false);

    private int daysPassed = 1;

    private PlayerViewModel playerViewModel;
    private StorageViewModel storageViewModel;
    private PlotViewModel plotViewModel;
    private final ArrayList<PlotModel> plotModels = new ArrayList<>(10);
    private final ArrayList<Image> plotModelImgs = new ArrayList<>(10);
    private final ArrayList<String> transferString = new ArrayList<>(2);
    private String name;
    private PlotModel plantingPlot;
    private ImageView plantedPlotImg;
    private ImageView plantedPlotNameImg;
    private Pane plantingPane;
    private int plantedPlotNum;
    private Text plantedWaterDaysText;

    /**
     * Initializes data from the Initial Configuration screen.
     *
     * @param playerViewModel Setting View Model to access player details.
     * @param playerName      The name of the current player.
     */
    public void initData(PlayerViewModel playerViewModel, String playerName) {
        this.money.setText("$ " + playerViewModel.getPlayer().getUserCurrentMoney());
        this.storageViewModel = new StorageViewModel(playerViewModel);
        this.plotViewModel = new PlotViewModel();
        this.playerViewModel = playerViewModel;
        this.name = playerName;
        setUpPlotModels(
                playerViewModel.getPlayer().getPlayerSettings().getStartingCropType());
        createPlotModels();
    }

    /**
     * Initializes data from the Market screen transfer.
     *
     * @param playerViewModel
     * @param plotModels
     * @param plotModelImgs
     * @param transferString
     * @param daysPassed
     */
    public void initData2(PlayerViewModel playerViewModel, ArrayList<PlotModel> plotModels,
                          ArrayList<Image> plotModelImgs, ArrayList<String> transferString,
                          int daysPassed) {
        this.money.setText("$ " + playerViewModel.getPlayer().getUserCurrentMoney());
        this.storageViewModel = new StorageViewModel(playerViewModel);
        this.plotViewModel = new PlotViewModel();
        this.playerViewModel = playerViewModel;
        this.name = transferString.get(0);
        this.dayNum.setText(transferString.get(1));
        this.daysPassed = daysPassed;
        setUpPlotModels(plotModels, plotModelImgs);
        createPlotModels();
    }

    /**
     * Makes Inventory Screen Invisible if exit button is clicked.
     */
    public void toggleInventoryScreenVisibility() {
        inventoryScreen.setVisible(!inventoryScreen.isVisible());
        dayCounter.setVisible(!dayCounter.isVisible());
        dayNum.setVisible(!dayNum.isVisible());
        sun.setVisible(!sun.isVisible());
        sunProgressBar.setVisible(!sunProgressBar.isVisible());
        numCorn.setText(
                String.valueOf(storageViewModel.userInventory().get(0).getCropQuantity()));
        numPotatoes.setText(
                String.valueOf(storageViewModel.userInventory().get(1).getCropQuantity()));
        numTomatoes.setText(
                String.valueOf(storageViewModel.userInventory().get(2).getCropQuantity()));
        btnCornPlant.setVisible(false);
        btnPotatoPlant.setVisible(false);
        btnTomatoPlant.setVisible(false);
    }

    /**
     * Makes "Plant" buttons on Inventory screen visible.
     */
    public void turnOnPlantBtnVisibility() {
        btnCornPlant.setVisible(true);
        btnPotatoPlant.setVisible(true);
        btnTomatoPlant.setVisible(true);
    }

    /**
     * Switches screen to the Market UI screen.
     *
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

        createPlotModelImgs();
        transferString.add(name);
        transferString.add(dayNum.getText());
        MarketPlace marketPlace = loader.getController();
        marketPlace.initData(mouseEvent, playerViewModel, storageViewModel,
                plotModels, plotModelImgs, transferString, daysPassed);

        stage.setTitle("Market");
        stage.show();
    }

    /**
     * Updates day number.
     */
    public void updateDay() {
        daysPassed++;
        dayNum.setText("Day " + doubleDigitString(daysPassed));
        incrementAllPlotDays();
    }

    /**
     * Sets up all the plot models with the initial config data.
     *
     * @param cropModel
     */
    public void setUpPlotModels(CropModel cropModel) {
        plot1 = plotViewModel.populatePlot(cropModel);
        plot2 = plotViewModel.populatePlot(cropModel);
        plot3 = plotViewModel.populatePlot(cropModel);
        plot4 = plotViewModel.populatePlot(cropModel);
        plot5 = plotViewModel.populatePlot(cropModel);
        plot6 = plotViewModel.populatePlot(cropModel);
        plot7 = plotViewModel.populatePlot(cropModel);
        plot8 = plotViewModel.populatePlot(cropModel);
        plot9 = plotViewModel.populatePlot(cropModel);
        plot10 = plotViewModel.populatePlot(cropModel);
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
        checkAllMaturity();
    }

    /**
     *
     *
     * @param plotModels
     * @param plotModelImgs
     */
    public void setUpPlotModels(ArrayList<PlotModel> plotModels, ArrayList<Image> plotModelImgs) {
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
        plotName1Img.setImage(plotModelImgs.get(0));
        plotName2Img.setImage(plotModelImgs.get(1));
        plotName3Img.setImage(plotModelImgs.get(2));
        plotName4Img.setImage(plotModelImgs.get(3));
        plotName5Img.setImage(plotModelImgs.get(4));
        plotName6Img.setImage(plotModelImgs.get(5));
        plotName7Img.setImage(plotModelImgs.get(6));
        plotName8Img.setImage(plotModelImgs.get(7));
        plotName9Img.setImage(plotModelImgs.get(8));
        plotName10Img.setImage(plotModelImgs.get(9));
        checkAllMaturity();
    }

    /**
     *
     */
    private void incrementAllPlotDays() {
        plotViewModel.incrementPlotDaysOld(plot1);
        plotViewModel.incrementPlotDaysOld(plot2);
        plotViewModel.incrementPlotDaysOld(plot3);
        plotViewModel.incrementPlotDaysOld(plot4);
        plotViewModel.incrementPlotDaysOld(plot5);
        plotViewModel.incrementPlotDaysOld(plot6);
        plotViewModel.incrementPlotDaysOld(plot7);
        plotViewModel.incrementPlotDaysOld(plot8);
        plotViewModel.incrementPlotDaysOld(plot9);
        plotViewModel.incrementPlotDaysOld(plot10);
        checkAllMaturity();
        updateDaysSinceWaterAll();
    }

    /**
     *
     */
    private void updateDaysSinceWater(Text days) {
        int num = Integer.parseInt(days.getText());
        num++;
        String str = doubleDigitString(num);
        days.setText(str);
    }

    /**
     *
     */
    private void updateDaysSinceWaterAll() {
        updateDaysSinceWater(txtDaysSinceWater1);
        updateDaysSinceWater(txtDaysSinceWater2);
        updateDaysSinceWater(txtDaysSinceWater3);
        updateDaysSinceWater(txtDaysSinceWater4);
        updateDaysSinceWater(txtDaysSinceWater5);
        updateDaysSinceWater(txtDaysSinceWater6);
        updateDaysSinceWater(txtDaysSinceWater7);
        updateDaysSinceWater(txtDaysSinceWater8);
        updateDaysSinceWater(txtDaysSinceWater9);
        updateDaysSinceWater(txtDaysSinceWater10);
    }

    /**
     *
     */
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

    /**
     *
     */
    public void createPlotModelImgs() {
        plotModelImgs.add(plotName1Img.getImage());
        plotModelImgs.add(plotName2Img.getImage());
        plotModelImgs.add(plotName3Img.getImage());
        plotModelImgs.add(plotName4Img.getImage());
        plotModelImgs.add(plotName5Img.getImage());
        plotModelImgs.add(plotName6Img.getImage());
        plotModelImgs.add(plotName7Img.getImage());
        plotModelImgs.add(plotName8Img.getImage());
        plotModelImgs.add(plotName9Img.getImage());
        plotModelImgs.add(plotName10Img.getImage());
    }

    /**
     *
     * @param plotModel
     * @param plotImg
     */
    public void checkMaturity(PlotModel plotModel, ImageView plotImg, Text waterDays) {
        if (plotModel.getDaysSinceWater() > 5) {
            plotImg.setImage(witheredImg);
            waterDays.setVisible(false);
        } else if (plotModel.getCropInPlot() != null) {
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

    /**
     *
     */
    public void checkAllMaturity() {
        checkMaturity(plot1, plot1Img, txtDaysSinceWater1);
        checkMaturity(plot2, plot2Img, txtDaysSinceWater2);
        checkMaturity(plot3, plot3Img, txtDaysSinceWater3);
        checkMaturity(plot4, plot4Img, txtDaysSinceWater4);
        checkMaturity(plot5, plot5Img, txtDaysSinceWater5);
        checkMaturity(plot6, plot6Img, txtDaysSinceWater6);
        checkMaturity(plot7, plot7Img, txtDaysSinceWater7);
        checkMaturity(plot8, plot8Img, txtDaysSinceWater8);
        checkMaturity(plot9, plot9Img, txtDaysSinceWater9);
        checkMaturity(plot10, plot10Img, txtDaysSinceWater10);
    }

    /**
     *
     * @param plantingPlot
     * @param plantedPlotImg
     * @param plantedPlotNameImg
     * @param pane
     * @param plantedPlotNum
     * @param plantedWaterDaysText
     */
    public void plantingInventory(PlotModel plantingPlot, ImageView plantedPlotImg, ImageView plantedPlotNameImg,
                                  Pane pane, int plantedPlotNum, Text plantedWaterDaysText) {
        toggleInventoryScreenVisibility();
        turnOnPlantBtnVisibility();
        this.plantingPlot = plantingPlot;
        this.plantedPlotImg = plantedPlotImg;
        this.plantedPlotNameImg = plantedPlotNameImg;
        this.plantingPane = pane;
        this.plantedPlotNum = plantedPlotNum;
        this.plantedWaterDaysText = plantedWaterDaysText;
    }

    public void plantCrop(CropModel crop) {
        this.plotViewModel.plantPlot(this.plantingPlot, crop);
        this.plantedPlotImg.setImage(seedImg);
        this.plantedPlotNameImg.setImage(chooseCropImage(crop));
        updateDaysSinceWater(this.plantedWaterDaysText);
        this.plantedWaterDaysText.setVisible(true);
        switchPlantHarvest(this.plantingPane, this.plantedPlotNum, true);
    }

    public Image chooseCropImage(CropModel crop) {
        switch(crop.getCropName()) {
            case "Corn":
                return this.cornNameImg;
            case "Potato":
                return this.potatoNameImg;
            case "Tomato":
                return this.tomatoNameImg;
            default:
                return this.emptyNameImg;
        }
    }

    /**
     *
     * @param harvestedPlot
     * @param harvestedPlotImg
     * @param harvestedPlotNameImg
     * @param pane
     * @param harvestedPlotNum
     */
    public void harvestCrop(PlotModel harvestedPlot, ImageView harvestedPlotImg,
                            ImageView harvestedPlotNameImg, Pane pane, int harvestedPlotNum) {
        if (harvestedPlot.getDaysOld() >= 10) {
            this.plotViewModel.harvestPlot(harvestedPlot, this.playerViewModel);
            harvestedPlotImg.setImage(dirtImg);
            harvestedPlotNameImg.setImage(emptyNameImg);
            switchPlantHarvest(pane, harvestedPlotNum, false);
        }
    }

    /**
     *
     * @param plotModel
     */
    public void waterCrop(PlotModel plotModel) {
        if (plotModel != null) {
            if (plotModel.getDaysSinceWater() <= 5) {
                this.plotViewModel.waterPlot(plotModel);
            }
        }
    }

    /**
     *
     * @param pane
     * @param i
     * @param isPlant
     */
    public void switchPlantHarvest(Pane pane, int i, boolean isPlant) {
        switch(i) {
        case 1:
            if (isPlant) {
                pane.setOnMouseClicked(this::plantCropPlot1);
            } else {
                pane.setOnMouseClicked(this::harvestCropPlot1);
            }
            break;
        case 2:
            if (isPlant) {
                pane.setOnMouseClicked(this::plantCropPlot2);
            } else {
                pane.setOnMouseClicked(this::harvestCropPlot2);
            }
            break;
        case 3:
            if (isPlant) {
                pane.setOnMouseClicked(this::plantCropPlot3);
            } else {
                pane.setOnMouseClicked(this::harvestCropPlot3);
            }
            break;
        case 4:
            if (isPlant) {
                pane.setOnMouseClicked(this::plantCropPlot4);
            } else {
                pane.setOnMouseClicked(this::harvestCropPlot4);
            }
            break;
        case 5:
            if (isPlant) {
                pane.setOnMouseClicked(this::plantCropPlot5);
            } else {
                pane.setOnMouseClicked(this::harvestCropPlot5);
            }
            break;
        case 6:
            if (isPlant) {
                pane.setOnMouseClicked(this::plantCropPlot6);
            } else {
                pane.setOnMouseClicked(this::harvestCropPlot6);
            }
            break;
        case 7:
            if (isPlant) {
                pane.setOnMouseClicked(this::plantCropPlot7);
            } else {
                pane.setOnMouseClicked(this::harvestCropPlot7);
            }
            break;
        case 8:
            if (isPlant) {
                pane.setOnMouseClicked(this::plantCropPlot8);
            } else {
                pane.setOnMouseClicked(this::harvestCropPlot8);
            }
            break;
        case 9:
            if (isPlant) {
                pane.setOnMouseClicked(this::plantCropPlot9);
            } else {
                pane.setOnMouseClicked(this::harvestCropPlot9);
            }
            break;
        case 10:
            if (isPlant) {
                pane.setOnMouseClicked(this::plantCropPlot10);
            } else {
                pane.setOnMouseClicked(this::harvestCropPlot10);
            }
            break;
        }
    }

    public void plantCropPlot1(MouseEvent mouseEvent) {
        plantingInventory(plot1, plot1Img, plotName1Img, pane1, 2, txtDaysSinceWater1);
    }

    public void plantCropPlot2(MouseEvent mouseEvent) {
        plantingInventory(plot2, plot2Img, plotName2Img, pane2, 2, txtDaysSinceWater2);
    }

    public void plantCropPlot3(MouseEvent mouseEvent) {
        plantingInventory(plot3, plot3Img, plotName3Img, pane3, 3, txtDaysSinceWater3);
    }

    public void plantCropPlot4(MouseEvent mouseEvent) {
        plantingInventory(plot4, plot4Img, plotName4Img, pane4, 4, txtDaysSinceWater4);
    }

    public void plantCropPlot5(MouseEvent mouseEvent) {
        plantingInventory(plot5, plot5Img, plotName5Img, pane5, 5, txtDaysSinceWater5);
    }

    public void plantCropPlot6(MouseEvent mouseEvent) {
        plantingInventory(plot6, plot6Img, plotName6Img, pane6, 6, txtDaysSinceWater6);
    }

    public void plantCropPlot7(MouseEvent mouseEvent) {
        plantingInventory(plot7, plot7Img, plotName7Img, pane7, 7, txtDaysSinceWater7);
    }

    public void plantCropPlot8(MouseEvent mouseEvent) {
        plantingInventory(plot8, plot8Img, plotName8Img, pane8, 8, txtDaysSinceWater8);
    }

    public void plantCropPlot9(MouseEvent mouseEvent) {
        plantingInventory(plot9, plot9Img, plotName9Img, pane9, 9, txtDaysSinceWater9);
    }

    public void plantCropPlot10(MouseEvent mouseEvent) {
        plantingInventory(plot10, plot10Img, plotName10Img, pane10, 10, txtDaysSinceWater10);
    }

    public void chooseCorn() {
        plantCrop(storageViewModel.userInventory().get(0));
    }

    public void choosePotato() {
        plantCrop(storageViewModel.userInventory().get(1));
    }

    public void chooseTomato() {
        plantCrop(storageViewModel.userInventory().get(2));
    }

    public void harvestCropPlot1(MouseEvent mouseEvent) {
        harvestCrop(plot1, plot1Img, plotName1Img, pane1, 1);
    }

    public void harvestCropPlot2(MouseEvent mouseEvent) {
        harvestCrop(plot2, plot2Img, plotName2Img, pane2, 2);
    }

    public void harvestCropPlot3(MouseEvent mouseEvent) {
        harvestCrop(plot3, plot3Img, plotName3Img, pane3, 3);
    }

    public void harvestCropPlot4(MouseEvent mouseEvent) {
        harvestCrop(plot4, plot4Img, plotName4Img, pane4, 4);
    }

    public void harvestCropPlot5(MouseEvent mouseEvent) {
        harvestCrop(plot5, plot5Img, plotName5Img, pane5, 5);
    }

    public void harvestCropPlot6(MouseEvent mouseEvent) {
        harvestCrop(plot6, plot6Img, plotName6Img, pane6, 6);
    }

    public void harvestCropPlot7(MouseEvent mouseEvent) {
        harvestCrop(plot7, plot7Img, plotName7Img, pane7, 7);
    }

    public void harvestCropPlot8(MouseEvent mouseEvent) {
        harvestCrop(plot8, plot8Img, plotName8Img, pane8, 8);
    }

    public void harvestCropPlot9(MouseEvent mouseEvent) {
        harvestCrop(plot9, plot9Img, plotName9Img, pane9, 9);
    }

    public void harvestCropPlot10(MouseEvent mouseEvent) {
        harvestCrop(plot10, plot10Img, plotName10Img, pane10, 10);
    }

    public void waterPlot1() {
        waterCrop(plot1);
    }

    public void waterPlot2() {
        waterCrop(plot2);
    }

    public void waterPlot3() {
        waterCrop(plot3);
    }

    public void waterPlot4() {
        waterCrop(plot4);
    }

    public void waterPlot5() {
        waterCrop(plot5);
    }

    public void waterPlot6() {
        waterCrop(plot6);
    }

    public void waterPlot7() {
        waterCrop(plot7);
    }

    public void waterPlot8() {
        waterCrop(plot8);
    }

    public void waterPlot9() {
        waterCrop(plot9);
    }

    public void waterPlot10() {
        waterCrop(plot10);
    }

    /**
     *
     * @param plotName
     * @param str
     */
    public void setUpPlotName(ImageView plotName, String str) {
        if (str.equals("Corn")) {
            plotName.setImage(cornNameImg);
        } else if (str.equals("Potato")) {
            plotName.setImage(potatoNameImg);
        } else {
            plotName.setImage(tomatoNameImg);
        }
    }

    /**
     *
     * @param num
     * @return
     */
    private String doubleDigitString(int num) {
        String str;
        if (num < 10) {
            str = "0" + num;
        } else {
            str = String.valueOf(num);
        }
        return str;
    }
}
