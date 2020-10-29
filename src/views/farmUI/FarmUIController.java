package views.farmUI;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
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
import java.util.List;

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
    private Text txtWaterValue1;
    @FXML
    private Text txtWaterValue2;
    @FXML
    private Text txtWaterValue3;
    @FXML
    private Text txtWaterValue4;
    @FXML
    private Text txtWaterValue5;
    @FXML
    private Text txtWaterValue6;
    @FXML
    private Text txtWaterValue7;
    @FXML
    private Text txtWaterValue8;
    @FXML
    private Text txtWaterValue9;
    @FXML
    private Text txtWaterValue10;

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


    private PlayerViewModel playerViewModel;
    private StorageViewModel storageViewModel;
    private PlotViewModel plotViewModel;
    private final ArrayList<PlotModel> plotModels = new ArrayList<>(10);
    private final ArrayList<Image> plotModelImgs = new ArrayList<>(10);
    private String name;
    private PlotModel plantingPlot;
    private ImageView plantedPlotImg;
    private ImageView plantedPlotNameImg;
    private int plantedPlotNum;
    private Text plantedWaterValueText;

    /**
     * Initializes data from the Initial Configuration screen.
     *
     * @param playerViewModel Setting View Model to access player details.
     * @param playerName      The name of the current player.
     */
    public void initData(PlayerViewModel playerViewModel, String playerName) {
        this.money.setText("$ " + playerViewModel.getPlayer().getUserCurrentMoney());
        this.storageViewModel = new StorageViewModel(playerViewModel);
        this.playerViewModel = playerViewModel;
        this.plotViewModel = new PlotViewModel(playerViewModel.getPlayer());
        this.name = playerName;
        this.dayNum.setText("Day " + doubleDigitString(this.playerViewModel.getPlayer().getDays()));
        setUpPlotModels(
                playerViewModel.getPlayer().getPlayerSettings().getStartingCropType());
        createPlotModels();
    }

//    /**
//     *
//     * @param playerViewModel
//     * @param plotModelImgs
//     * @param name
//     */
//    public void initDataFromMarket(PlayerViewModel playerViewModel,
//                                   ArrayList<Image> plotModelImgs, String name) {
//        this.money.setText("$ " + playerViewModel.getPlayer().getUserCurrentMoney());
//        this.storageViewModel = new StorageViewModel(playerViewModel);
//        this.playerViewModel = playerViewModel;
//        this.plotViewModel = new PlotViewModel(playerViewModel.getPlayer());
//        this.name = name;
//        this.dayNum.setText("Day " + doubleDigitString(this.playerViewModel.getPlayer().getDays()));
//        setUpPlotModels(this.plotViewModel.getPlotsFromDatabase(), plotModelImgs);
//        checkAllMaturity();
//        createPlotModels();
//    }

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
        turnOffPlantBtnVisibility();
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
     * Makes "Plant" buttons on Inventory screen visible.
     */
    public void turnOffPlantBtnVisibility() {
        btnCornPlant.setVisible(false);
        btnPotatoPlant.setVisible(false);
        btnTomatoPlant.setVisible(false);
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
        //currentStage.close();
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(
                    new Scene(loader.load())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        createPlotModelImgs();
        MarketPlace marketPlace = loader.getController();
        marketPlace.initData(mouseEvent, playerViewModel, storageViewModel,
                plotModelImgs, name);

        stage.setTitle("Market");
        stage.show();
    }

    /**
     * Updates day number.
     */
    public void updateDay() {
        this.playerViewModel.getPlayer().setDays(
                this.playerViewModel.getPlayer().getDays() + 1);
        dayNum.setText("Day " + doubleDigitString(this.playerViewModel.getPlayer().getDays()));
        incrementAllPlotDays();
    }

    /**
     * Sets up all the plot models with the initial config data.
     *
     * @param cropModel Placeholder
     */
    public void setUpPlotModels(CropModel cropModel) {
        List<PlotModel> plotModelList = new ArrayList<>();
        plot1 = plotViewModel.populatePlot(cropModel);
        plotModelList.add(plot1);
        plot2 = plotViewModel.populatePlot(cropModel);
        plotModelList.add(plot2);
        plot3 = plotViewModel.populatePlot(cropModel);
        plotModelList.add(plot3);
        plot4 = plotViewModel.populatePlot(cropModel);
        plotModelList.add(plot4);
        plot5 = plotViewModel.populatePlot(cropModel);
        plotModelList.add(plot5);
        plot6 = plotViewModel.populatePlot(cropModel);
        plotModelList.add(plot6);
        plot7 = plotViewModel.populatePlot(cropModel);
        plotModelList.add(plot7);
        plot8 = plotViewModel.populatePlot(cropModel);
        plotModelList.add(plot8);
        plot9 = plotViewModel.populatePlot(cropModel);
        plotModelList.add(plot9);
        plot10 = plotViewModel.populatePlot(cropModel);
        plotModelList.add(plot10);
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

        plotViewModel.addPlayerPlotsToDatabase(plotModelList,
                playerViewModel.getPlayer().getPlayerSettings().getPlayerName());
    }

    /**
     * @param plotModels    Placeholder
     * @param plotModelImgs Placeholder
     */
    public void setUpPlotModels(List<PlotModel> plotModels, ArrayList<Image> plotModelImgs) {
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
        plotViewModel.updatePlotMaturity(plot1.getPlotIdentifier(),
                playerViewModel.getPlayer().getPlayerSettings().getPlayerName());
        plotViewModel.updatePlotMaturity(plot2.getPlotIdentifier(),
                playerViewModel.getPlayer().getPlayerSettings().getPlayerName());

        plotViewModel.updatePlotMaturity(plot3.getPlotIdentifier(),
                playerViewModel.getPlayer().getPlayerSettings().getPlayerName());

        plotViewModel.updatePlotMaturity(plot4.getPlotIdentifier(),
                playerViewModel.getPlayer().getPlayerSettings().getPlayerName());

        plotViewModel.updatePlotMaturity(plot5.getPlotIdentifier(),
                playerViewModel.getPlayer().getPlayerSettings().getPlayerName());

        plotViewModel.updatePlotMaturity(plot6.getPlotIdentifier(),
                playerViewModel.getPlayer().getPlayerSettings().getPlayerName());

        plotViewModel.updatePlotMaturity(plot7.getPlotIdentifier(),
                playerViewModel.getPlayer().getPlayerSettings().getPlayerName());

        plotViewModel.updatePlotMaturity(plot8.getPlotIdentifier(),
                playerViewModel.getPlayer().getPlayerSettings().getPlayerName());

        plotViewModel.updatePlotMaturity(plot9.getPlotIdentifier(),
                playerViewModel.getPlayer().getPlayerSettings().getPlayerName());

        plotViewModel.updatePlotMaturity(plot10.getPlotIdentifier(),
                playerViewModel.getPlayer().getPlayerSettings().getPlayerName());


        checkAllMaturity();
        updateWaterValueAll();
    }

    /**
     * @param plot       Placeholder
     * @param waterValue Placeholder
     */
    private void updateWaterValue(PlotModel plot, Text waterValue) {
        String str = doubleDigitString(plot.getWaterValue());
        waterValue.setText(str);
    }

    /**
     *
     */
    private void updateWaterValueAll() {
        updateWaterValue(plot1, txtWaterValue1);
        updateWaterValue(plot2, txtWaterValue2);
        updateWaterValue(plot3, txtWaterValue3);
        updateWaterValue(plot4, txtWaterValue4);
        updateWaterValue(plot5, txtWaterValue5);
        updateWaterValue(plot6, txtWaterValue6);
        updateWaterValue(plot7, txtWaterValue7);
        updateWaterValue(plot8, txtWaterValue8);
        updateWaterValue(plot9, txtWaterValue9);
        updateWaterValue(plot10, txtWaterValue10);
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
     * Placeholder
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
     * Placeholder
     *
     * @param plotModel  Placeholder
     * @param plotImg    Placeholder
     * @param waterValue Placeholder
     */
    public void checkMaturity(PlotModel plotModel, ImageView plotImg, Text waterValue) {
        if (plotModel.getCropInPlot() != null) {
            if (plotModel.getWaterValue() > 6 || plotModel.getWaterValue() <= 0) {
                plotImg.setImage(witheredImg);
                waterValue.setVisible(false);
            } else if (plotModel.getDaysOld() < 2) {
                plotImg.setImage(seedImg);
            } else if (plotModel.getDaysOld() < 6) {
                plotImg.setImage(immature1Img);
            } else if (plotModel.getDaysOld() < 10) {
                plotImg.setImage(immature2Img);
            } else {
                plotImg.setImage(matureImg);
            }
        } else {
            plotImg.setImage(dirtImg);
            waterValue.setVisible(false);
        }
    }

    /**
     * Placeholder
     */
    public void checkAllMaturity() {
        checkMaturity(plot1, plot1Img, txtWaterValue1);
        checkMaturity(plot2, plot2Img, txtWaterValue2);
        checkMaturity(plot3, plot3Img, txtWaterValue3);
        checkMaturity(plot4, plot4Img, txtWaterValue4);
        checkMaturity(plot5, plot5Img, txtWaterValue5);
        checkMaturity(plot6, plot6Img, txtWaterValue6);
        checkMaturity(plot7, plot7Img, txtWaterValue7);
        checkMaturity(plot8, plot8Img, txtWaterValue8);
        checkMaturity(plot9, plot9Img, txtWaterValue9);
        checkMaturity(plot10, plot10Img, txtWaterValue10);
    }

    /**
     * Placeholder
     *
     * @param plantingPlot         Placeholder
     * @param plantedPlotImg       Placeholder
     * @param plantedPlotNameImg   Placeholder
     * @param plantedPlotNum       Placeholder
     * @param plantedWaterDaysText Placeholder
     */
    public void plantingInventory(PlotModel plantingPlot, ImageView plantedPlotImg,
                                  ImageView plantedPlotNameImg,
                                  int plantedPlotNum, Text plantedWaterDaysText) {
        toggleInventoryScreenVisibility();
        turnOnPlantBtnVisibility();
        this.plantingPlot = plantingPlot;
        this.plantedPlotImg = plantedPlotImg;
        this.plantedPlotNameImg = plantedPlotNameImg;
        this.plantedPlotNum = plantedPlotNum;
        this.plantedWaterValueText = plantedWaterDaysText;
    }

    public void plantCrop(int cropNum, CropModel crop) {
        if (storageViewModel.userInventory().get(cropNum).getCropQuantity() > 0) {
            storageViewModel.userInventory().get(cropNum).setCropQuantity(
                    storageViewModel.userInventory().get(cropNum).getCropQuantity() - 1);
            toggleInventoryScreenVisibility();
            this.plantingPlot.setWaterValue(3);
            this.plantingPlot.setDaysOld(0);
            this.plotViewModel.plantPlot(this.plantingPlot, crop);
            this.plantedPlotImg.setImage(seedImg);
            this.plantedPlotNameImg.setImage(chooseCropImage(crop));
            updateWaterValue(this.plantingPlot, this.plantedWaterValueText);
            this.plantedWaterValueText.setVisible(true);
            switchPlantHarvest(this.plantedPlotImg, this.plantedPlotNum, true);
        }
    }

    public Image chooseCropImage(CropModel crop) {
        switch (crop.getCropName()) {
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
     * @param harvestedPlot        Placeholder
     * @param harvestedPlotImg     Placeholder
     * @param harvestedPlotNameImg Placeholder
     * @param harvestedPlotNum     Placeholder
     * @param waterDays            Placeholder
     */
    public void harvestCrop(PlotModel harvestedPlot, ImageView harvestedPlotImg,
                            ImageView harvestedPlotNameImg, int harvestedPlotNum, Text waterDays) {
        if (harvestedPlot.getDaysOld() >= 10
                || harvestedPlot.getWaterValue() > 6 || harvestedPlot.getWaterValue() <= 0) {
            this.plotViewModel.harvestPlot(harvestedPlot, this.playerViewModel);
            harvestedPlotImg.setImage(dirtImg);
            harvestedPlotNameImg.setImage(emptyNameImg);
//            this.plotViewModel.zeroWaterValue(harvestedPlot.getWaterValue(),
//                    harvestedPlot.getPlotIdentifier());
//            harvestedPlot.setWaterValue(0);
            switchPlantHarvest(harvestedPlotImg, harvestedPlotNum, false);
            waterDays.setVisible(false);
        }
    }

    /**
     * @param plotModel    Placeholder
     * @param plotModelImg Placeholder
     * @param waterDays    Placeholder
     */
    public void waterCrop(PlotModel plotModel, ImageView plotModelImg, Text waterDays) {
        if (plotModel != null) {
            if (plotModel.getWaterValue() > 0 && plotModel.getWaterValue() <= 6) {
                this.plotViewModel.waterPlot(plotModel);
                waterDays.setText(doubleDigitString(plotModel.getWaterValue()));
                checkMaturity(plotModel, plotModelImg, waterDays);
            }
        }
    }

    /**
     * Placeholder
     *
     * @param plotImg Placeholder
     * @param i       Placeholder
     * @param isPlant Placeholder
     */
    public void switchPlantHarvest(ImageView plotImg, int i, boolean isPlant) {
        switch (i) {
            case 1:
                if (isPlant) {
                    plotImg.setOnMouseClicked(this::harvestCropPlot1);
                } else {
                    plotImg.setOnMouseClicked(this::plantCropPlot1);
                }
                break;
            case 2:
                if (isPlant) {
                    plotImg.setOnMouseClicked(this::harvestCropPlot2);
                } else {
                    plotImg.setOnMouseClicked(this::plantCropPlot2);
                }
                break;
            case 3:
                if (isPlant) {
                    plotImg.setOnMouseClicked(this::harvestCropPlot3);
                } else {
                    plotImg.setOnMouseClicked(this::plantCropPlot3);
                }
                break;
            case 4:
                if (isPlant) {
                    plotImg.setOnMouseClicked(this::harvestCropPlot4);
                } else {
                    plotImg.setOnMouseClicked(this::plantCropPlot4);
                }
                break;
            case 5:
                if (isPlant) {
                    plotImg.setOnMouseClicked(this::harvestCropPlot5);
                } else {
                    plotImg.setOnMouseClicked(this::plantCropPlot5);
                }
                break;
            case 6:
                if (isPlant) {
                    plotImg.setOnMouseClicked(this::harvestCropPlot6);
                } else {
                    plotImg.setOnMouseClicked(this::plantCropPlot6);
                }
                break;
            case 7:
                if (isPlant) {
                    plotImg.setOnMouseClicked(this::harvestCropPlot7);
                } else {
                    plotImg.setOnMouseClicked(this::plantCropPlot7);
                }
                break;
            case 8:
                if (isPlant) {
                    plotImg.setOnMouseClicked(this::harvestCropPlot8);
                } else {
                    plotImg.setOnMouseClicked(this::plantCropPlot8);
                }
                break;
            case 9:
                if (isPlant) {
                    plotImg.setOnMouseClicked(this::harvestCropPlot9);
                } else {
                    plotImg.setOnMouseClicked(this::plantCropPlot9);
                }
                break;
            case 10:
                if (isPlant) {
                    plotImg.setOnMouseClicked(this::harvestCropPlot10);
                } else {
                    plotImg.setOnMouseClicked(this::plantCropPlot10);
                }
                break;
            default:
                break;
        }
    }

    public void plantCropPlot1(MouseEvent mouseEvent) {
        plantingInventory(plot1, plot1Img, plotName1Img, 2, txtWaterValue1);
    }

    public void plantCropPlot2(MouseEvent mouseEvent) {
        plantingInventory(plot2, plot2Img, plotName2Img, 2, txtWaterValue2);
    }

    public void plantCropPlot3(MouseEvent mouseEvent) {
        plantingInventory(plot3, plot3Img, plotName3Img, 3, txtWaterValue3);
    }

    public void plantCropPlot4(MouseEvent mouseEvent) {
        plantingInventory(plot4, plot4Img, plotName4Img, 4, txtWaterValue4);
    }

    public void plantCropPlot5(MouseEvent mouseEvent) {
        plantingInventory(plot5, plot5Img, plotName5Img, 5, txtWaterValue5);
    }

    public void plantCropPlot6(MouseEvent mouseEvent) {
        plantingInventory(plot6, plot6Img, plotName6Img, 6, txtWaterValue6);
    }

    public void plantCropPlot7(MouseEvent mouseEvent) {
        plantingInventory(plot7, plot7Img, plotName7Img, 7, txtWaterValue7);
    }

    public void plantCropPlot8(MouseEvent mouseEvent) {
        plantingInventory(plot8, plot8Img, plotName8Img, 8, txtWaterValue8);
    }

    public void plantCropPlot9(MouseEvent mouseEvent) {
        plantingInventory(plot9, plot9Img, plotName9Img, 9, txtWaterValue9);
    }

    public void plantCropPlot10(MouseEvent mouseEvent) {
        plantingInventory(plot10, plot10Img, plotName10Img, 10, txtWaterValue10);
    }

    public void chooseCorn() {
        plantCrop(0, storageViewModel.userInventory().get(0));
    }

    public void choosePotato() {
        plantCrop(1, storageViewModel.userInventory().get(1));
    }

    public void chooseTomato() {
        plantCrop(2, storageViewModel.userInventory().get(2));
    }

    public void harvestCropPlot1(MouseEvent mouseEvent) {
        harvestCrop(plot1, plot1Img, plotName1Img, 1, txtWaterValue1);
    }

    public void harvestCropPlot2(MouseEvent mouseEvent) {
        harvestCrop(plot2, plot2Img, plotName2Img, 2, txtWaterValue2);
    }

    public void harvestCropPlot3(MouseEvent mouseEvent) {
        harvestCrop(plot3, plot3Img, plotName3Img, 3, txtWaterValue3);
    }

    public void harvestCropPlot4(MouseEvent mouseEvent) {
        harvestCrop(plot4, plot4Img, plotName4Img, 4, txtWaterValue4);
    }

    public void harvestCropPlot5(MouseEvent mouseEvent) {
        harvestCrop(plot5, plot5Img, plotName5Img, 5, txtWaterValue5);
    }

    public void harvestCropPlot6(MouseEvent mouseEvent) {
        harvestCrop(plot6, plot6Img, plotName6Img, 6, txtWaterValue6);
    }

    public void harvestCropPlot7(MouseEvent mouseEvent) {
        harvestCrop(plot7, plot7Img, plotName7Img, 7, txtWaterValue7);
    }

    public void harvestCropPlot8(MouseEvent mouseEvent) {
        harvestCrop(plot8, plot8Img, plotName8Img, 8, txtWaterValue8);
    }

    public void harvestCropPlot9(MouseEvent mouseEvent) {
        harvestCrop(plot9, plot9Img, plotName9Img, 9, txtWaterValue8);
    }

    public void harvestCropPlot10(MouseEvent mouseEvent) {
        harvestCrop(plot10, plot10Img, plotName10Img, 10, txtWaterValue10);
    }

    public void waterPlot1() {
        waterCrop(plot1, plot1Img, txtWaterValue1);
    }

    public void waterPlot2() {
        waterCrop(plot2, plot2Img, txtWaterValue2);
    }

    public void waterPlot3() {
        waterCrop(plot3, plot3Img, txtWaterValue3);
    }

    public void waterPlot4() {
        waterCrop(plot4, plot4Img, txtWaterValue4);
    }

    public void waterPlot5() {
        waterCrop(plot5, plot5Img, txtWaterValue5);
    }

    public void waterPlot6() {
        waterCrop(plot6, plot6Img, txtWaterValue6);
    }

    public void waterPlot7() {
        waterCrop(plot7, plot7Img, txtWaterValue7);
    }

    public void waterPlot8() {
        waterCrop(plot8, plot8Img, txtWaterValue8);
    }

    public void waterPlot9() {
        waterCrop(plot9, plot9Img, txtWaterValue9);
    }

    public void waterPlot10() {
        waterCrop(plot10, plot10Img, txtWaterValue10);
    }

    /**
     * Placeholder
     *
     * @param plotName Placeholder
     * @param str      Placeholder
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
     * Placeholder
     *
     * @param num Placeholder
     * @return Placeholder
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
