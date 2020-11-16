package views.farmUI;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.CropModel;
import models.PlotModel;
import models.WorkerModel;
import viewmodels.EventViewModel;
import viewmodels.PlayerViewModel;
import viewmodels.PlotViewModel;
import viewmodels.StorageViewModel;
import viewmodels.WorkerViewModel;
import views.marketUI.MarketUIController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A view class that controls the UI elements for the main farm screen.
 *
 * @author Matthew Farias, Shaun Jacob
 * @version 0.4
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
    private Text numCorn;
    @FXML
    private Text numPotatoes;
    @FXML
    private Text numTomatoes;
    @FXML
    private Text numPesticideCorn;
    @FXML
    private Text numPesticidePotatoes;
    @FXML
    private Text numPesticideTomatoes;
    @FXML
    private Text textEvent;
    @FXML
    private JFXButton btnMarket;

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

    @FXML
    private Text textFertilizerLevel1;
    @FXML
    private Text textFertilizerLevel2;
    @FXML
    private Text textFertilizerLevel3;
    @FXML
    private Text textFertilizerLevel4;
    @FXML
    private Text textFertilizerLevel5;
    @FXML
    private Text textFertilizerLevel6;
    @FXML
    private Text textFertilizerLevel7;
    @FXML
    private Text textFertilizerLevel8;
    @FXML
    private Text textFertilizerLevel9;
    @FXML
    private Text textFertilizerLevel10;

    @FXML
    private Text txtFertilizerCount;
    @FXML
    private Text txtPesticideCount;

    @FXML
    private Text txtPopUp;


    private ArrayList<PlotModel> listPlots = new ArrayList<>();
    private ArrayList<ImageView> listPlotImages = new ArrayList<>();
    private ArrayList<ImageView> listPlotNameImages = new ArrayList<>();
    private ArrayList<Text> listPlotWaterValues = new ArrayList<>();
    private ArrayList<Text> listPlotFertilizerLevels = new ArrayList<>();

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
    private final Image cornPesticideNameImg = new Image(
            "@../../dependencies/images/Crop_Bar_Corn_Pesticide.png",
            400.0, 300.0, true, false);
    private final Image potatoPesticideNameImg = new Image(
            "@../../dependencies/images/Crop_Bar_Potato_Pesticide.png",
            400.0, 300.0, true, false);
    private final Image tomatoPesticideNameImg = new Image(
            "@../../dependencies/images/Crop_Bar_Tomato_Pesticide.png",
            400.0, 300.0, true, false);


    private PlayerViewModel playerViewModel;
    private StorageViewModel storageViewModel;
    private PlotViewModel plotViewModel;
    private EventViewModel eventViewModel;
    private int plantingPlotNum;

    /**
     * Initializes data from the Initial Configuration screen.
     *
     * @param playerViewModel Setting View Model to access player details.
     */
    public void initConfigData(PlayerViewModel playerViewModel) {
        setUpData(playerViewModel);
        setUpPlotModels(
                playerViewModel.getPlayer().getPlayerSettings().getStartingCropType());
        WorkerModel workerModel = new WorkerModel();
        WorkerViewModel workerViewModel = new WorkerViewModel(playerViewModel.getPlayer());
        workerViewModel.addWorkerDatabase(workerModel);
    }

    public void initSaveData(PlayerViewModel playerViewModel) {
        setUpData(playerViewModel);
        setUpPlotModels(plotViewModel.getPlotsFromDatabase());
    }

    public void initDataFromMarket(PlayerViewModel playerViewModel) {
        setUpData(playerViewModel);
        setUpPlotModels(plotViewModel.getPlotsFromDatabase());
    }

    public void setUpData(PlayerViewModel playerViewModel) {
        this.money.setText("$ " + playerViewModel.getPlayer().getUserCurrentMoney());
        this.playerViewModel = playerViewModel;
        this.storageViewModel = new StorageViewModel(playerViewModel);
        this.eventViewModel = new EventViewModel(playerViewModel.getPlayer());
        this.plotViewModel = new PlotViewModel(playerViewModel.getPlayer());
        this.dayNum.setText("Day " + doubleDigitString(this.playerViewModel.getPlayer().getDays()));
        this.txtFertilizerCount.setText(doubleDigitString(
                this.playerViewModel.getPlayer().getUserStorage().getTotalFertilizer() - 1));
        this.txtPesticideCount.setText(doubleDigitString(
                this.playerViewModel.getPlayer().getUserStorage().getTotalPesticide() - 1));
        setUpPlotImages();
        setUpPlotNameImages();
        setUpPlotWaterValues();
        setUpPlotFertilizerLevels();
    }

    public void setUpPlotImages() {
        this.listPlotImages = new ArrayList<>(
                Arrays.asList(plot1Img, plot2Img, plot3Img,
                        plot4Img, plot5Img, plot6Img, plot7Img,
                        plot8Img, plot9Img, plot10Img)
        );
    }

    public void setUpPlotNameImages() {
        this.listPlotNameImages = new ArrayList<>(
                Arrays.asList(plotName1Img, plotName2Img, plotName3Img,
                        plotName4Img, plotName5Img, plotName6Img, plotName7Img,
                        plotName8Img, plotName9Img, plotName10Img)
        );
    }

    public void setUpPlotWaterValues() {
        this.listPlotWaterValues = new ArrayList<>(
                Arrays.asList(txtWaterValue1, txtWaterValue2, txtWaterValue3,
                        txtWaterValue4, txtWaterValue5, txtWaterValue6, txtWaterValue7,
                        txtWaterValue8, txtWaterValue9, txtWaterValue10)
        );
    }

    public void setUpPlotFertilizerLevels() {
        this.listPlotFertilizerLevels = new ArrayList<>(
                Arrays.asList(textFertilizerLevel1, textFertilizerLevel2, textFertilizerLevel3,
                        textFertilizerLevel4, textFertilizerLevel5, textFertilizerLevel6,
                        textFertilizerLevel7, textFertilizerLevel8, textFertilizerLevel9,
                        textFertilizerLevel10)
        );
    }

    public void setUpPlotModels(CropModel cropModel) {
        for (int i = 0; i < 10; i++) {
            listPlots.add(plotViewModel.populatePlot(cropModel));
            setUpPlotName(i, cropModel.getCropName());
        }
        setAllInitialMaturity();
        checkAllMaturity();
        plotViewModel.addPlayerPlotsToDatabase(listPlots,
                playerViewModel.getPlayer().getPlayerSettings().getPlayerName());
    }

    public void setUpPlotModels(List<PlotModel> plotModels) {
        for (int i = 0; i < 10; i++) {
            listPlots.add(plotModels.get(i));
            if (plotModels.get(i).getCropInPlot() != null) {
                listPlotNameImages.get(i).setImage(
                        chooseCropImage(plotModels.get(i).getCropInPlot()));
            }
            listPlotWaterValues.get(i).setText(
                    doubleDigitString(plotModels.get(i).getWaterValue()));
            listPlotFertilizerLevels.get(i).setText(
                    doubleDigitString(plotModels.get(i).getFertilizerLevel()));
        }
        checkAllMaturity();
    }

    /**
     * Makes Inventory Screen Invisible if exit button is clicked.
     */
    public void toggleInventoryScreenVisibility() {
        inventoryScreen.setVisible(!inventoryScreen.isVisible());
        dayCounter.setVisible(!dayCounter.isVisible());
        dayNum.setVisible(!dayNum.isVisible());
        btnMarket.setVisible(!btnMarket.isVisible());
        numCorn.setText(
                String.valueOf(storageViewModel.userInventory().get(0).getCropQuantity()));
        numPotatoes.setText(
                String.valueOf(storageViewModel.userInventory().get(1).getCropQuantity()));
        numTomatoes.setText(
                String.valueOf(storageViewModel.userInventory().get(2).getCropQuantity()));
        numPesticideCorn.setText(
                String.valueOf(storageViewModel.userInventory().get(3).getCropQuantity()));
        numPesticidePotatoes.setText(
                String.valueOf(storageViewModel.userInventory().get(4).getCropQuantity()));
        numPesticideTomatoes.setText(
                String.valueOf(storageViewModel.userInventory().get(5).getCropQuantity()));
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

    public void updateDay() {
        this.playerViewModel.getPlayer().setDays(
                this.playerViewModel.getPlayer().getDays() + 1);
        dayNum.setText("Day " + doubleDigitString(this.playerViewModel.getPlayer().getDays()));
        this.playerViewModel.updatePlayerDay(
                this.playerViewModel.getPlayer().getPlayerSettings().getPlayerName());
        incrementAllPlotDays();
        playerViewModel.zeroCurrentHarvestCounter();
        playerViewModel.zeroCurrentWaterCounter();
    }

    private void incrementAllPlotDays() {
        for (int i = 0; i < 10; i++) {
            if (listPlots.get(i).getCropInPlot() != null) {
                plotViewModel.incrementPlotDaysOld(listPlots.get(i), playerViewModel);
                plotViewModel.updatePlotMaturity(listPlots.get(i), playerViewModel.getPlayer());
                this.plotViewModel.updateWaterValue(listPlots.get(i).getWaterValue(),
                        listPlots.get(i).getPlotIdentifier());
            }
        }
        eventRoll();
        checkAllMaturity();
        updateWaterValueAll();
        updateFertilizerLevelAll();
    }

    private void updateWaterValueAll() {
        for (int i = 0; i < 10; i++) {
            updateWaterValue(listPlots.get(i), listPlotWaterValues.get(i));
        }
    }

    private void updateWaterValue(PlotModel plot, Text waterValue) {
        if (plot.getCropInPlot() != null) {
            String str = doubleDigitString(plot.getWaterValue());
            waterValue.setText(str);
        }
    }

    private void updateFertilizerLevelAll() {
        for (int i = 0; i < 10; i++) {
            updateFertilizerLevel(listPlots.get(i), listPlotFertilizerLevels.get(i));
        }
    }

    private void updateFertilizerLevel(PlotModel plot, Text fertilizerLevel) {
        if (plot.getFertilizerLevel() > 0) {
            String str = doubleDigitString(plot.getFertilizerLevel());
            fertilizerLevel.setText(str);
        }
    }

    public void setAllInitialMaturity() {
        for (int i = 0; i < 10; i++) {
            if (listPlots.get(i).getDaysOld() < 2) {
                listPlots.get(i).setStage("Seed");
            } else if (listPlots.get(i).getDaysOld() < 6) {
                listPlots.get(i).setStage("Immature 1");
            } else if (listPlots.get(i).getDaysOld() < 10) {
                listPlots.get(i).setStage("Immature 2");
            } else {
                listPlots.get(i).setStage("Mature");
            }
        }
    }

    public void checkAllMaturity() {
        for (int i = 0; i < 10; i++) {
            checkMaturity(i, listPlots.get(i), listPlotImages.get(i), listPlotWaterValues.get(i));
        }
    }

    public void checkMaturity(int plotNum, PlotModel plotModel,
                              ImageView plotImg, Text waterValue) {
        if (plotModel.getCropInPlot() != null) {
            String name = playerViewModel.getPlayer().getPlayerSettings().getPlayerName();
            if (plotModel.getWaterValue() > 6 || plotModel.getWaterValue() <= 0) {
                plotImg.setImage(witheredImg);
                plotModel.setStage("Withered");
                plotViewModel.updatePlotStage(plotModel, playerViewModel.getPlayer());
                waterValue.setVisible(false);
            } else if (plotModel.getDaysOld() < 2) {
                plotImg.setImage(seedImg);
                plotModel.setStage("Seed");
                plotViewModel.updatePlotStage(plotModel, playerViewModel.getPlayer());
            } else if (plotModel.getDaysOld() < 6) {
                plotImg.setImage(immature1Img);
                plotModel.setStage("Immature 1");
                plotViewModel.updatePlotStage(plotModel, playerViewModel.getPlayer());
            } else if (plotModel.getDaysOld() < 10) {
                plotModel.setStage("Immature 2");
                plotImg.setImage(immature2Img);
                plotViewModel.updatePlotStage(plotModel, playerViewModel.getPlayer());
            } else {
                plotModel.setStage("Mature");
                plotImg.setImage(matureImg);
                plotViewModel.updatePlotStage(plotModel, playerViewModel.getPlayer());
            }
        } else {
            plotImg.setImage(dirtImg);
            waterValue.setVisible(false);
            switchPlantHarvest(plotImg, plotNum, false);
        }
    }

    public void plantingInventory(int plotNum) {
        toggleInventoryScreenVisibility();
        turnOnPlantBtnVisibility();
        this.plantingPlotNum = plotNum;
    }

    public void plantCrop(int cropNum, CropModel crop) {
        if (storageViewModel.userInventory().get(cropNum).getCropQuantity() > 2) {
            int plotNum = plantingPlotNum;
            storageViewModel.userInventory().get(cropNum).setCropQuantity(
                    storageViewModel.userInventory().get(cropNum).getCropQuantity() - 1);
            toggleInventoryScreenVisibility();
            listPlots.get(plotNum).setWaterValue(3);
            listPlots.get(plotNum).setDaysOld(0);
            this.plotViewModel.plantPlot(listPlots.get(plotNum), crop);
            plotViewModel.updatePlotStage(listPlots.get(plotNum), playerViewModel.getPlayer());
            listPlotImages.get(plotNum).setImage(seedImg);
            listPlotNameImages.get(plotNum).setImage(chooseCropImage(crop));
            updateWaterValue(listPlots.get(plotNum), listPlotWaterValues.get(plotNum));
            listPlotWaterValues.get(plotNum).setVisible(true);
            switchPlantHarvest(listPlotImages.get(plotNum), plotNum, true);
        }
    }

    /**
     * @param plotNum The harvested plot's number.
     */
    public void harvestCrop(int plotNum) {
        if (playerViewModel.getPlayer().getCurrentHarvestCounter()
                < playerViewModel.getPlayer().getMaxHarvestsPerDay()) {
            PlotModel harvestedPlot = listPlots.get(plotNum);
            ImageView harvestedPlotImage = listPlotImages.get(plotNum);
            ImageView harvestedPlotNameImage = listPlotNameImages.get(plotNum);
            Text waterValue = listPlotWaterValues.get(plotNum);
            if (harvestedPlot.getDaysOld() >= 10
                    || harvestedPlot.getWaterValue() > 6 || harvestedPlot.getWaterValue() <= 0) {
                this.plotViewModel.harvestPlot(harvestedPlot, this.playerViewModel);
                harvestedPlot.setWaterValue(0);
                harvestedPlot.setDaysOld(0);
                this.plotViewModel.updatePlotStage(listPlots.get(plotNum),
                        playerViewModel.getPlayer());
                this.plotViewModel.updatePlotDaysDatabase(listPlots.get(plotNum),
                        playerViewModel.getPlayer());
                harvestedPlotImage.setImage(dirtImg);
                harvestedPlotNameImage.setImage(emptyNameImg);
                switchPlantHarvest(harvestedPlotImage, plotNum, false);
                waterValue.setVisible(false);
                playerViewModel.increasePlayerHarvestCounter();
                System.out.println("The current player max harvest is " + playerViewModel.getPlayer().getMaxHarvestsPerDay());
                System.out.println("The current player harvest counter is " + playerViewModel.getPlayer().getCurrentHarvestCounter());
            }
        } else {
            displayMaxHarvestPopUp();
        }
    }

    private void eventRoll() {
        switch (eventViewModel.chooseEvent()) {
            case 0:
                int waterRainChange = eventViewModel.performRainEvent();
                for (int i = 0; i < 10; i++) {
                    if (listPlots.get(i).getCropInPlot() != null) {
                        if (listPlots.get(i).getWaterValue() > 0
                                && listPlots.get(i).getWaterValue() <= 6) {
                            listPlots.get(i).setWaterValue(
                                    listPlots.get(i).getWaterValue() + waterRainChange);
                        }
                    }
                }
                textEvent.setText("Rain");
                break;
            case 1:
                int waterDroughtChange = eventViewModel.performDroughtEvent();
                for (int i = 0; i < 10; i++) {
                    if (listPlots.get(i).getCropInPlot() != null) {
                        if (listPlots.get(i).getWaterValue() > 0
                                && listPlots.get(i).getWaterValue() <= 6) {
                            listPlots.get(i).setWaterValue(
                                    Math.max(listPlots.get(i).getWaterValue() - waterDroughtChange, 0));
                        }
                    }
                }
                textEvent.setText("Drought");
                break;
            case 2:
                for (int i = 0; i < 10; i++) {
                    if (listPlots.get(i).getCropInPlot() != null) {
                        int n = eventViewModel.performLocustEvent(listPlots.get(i));
                        if (n == 1) {
                            listPlots.get(i).setStage(null);
                            listPlotImages.get(i).setImage(dirtImg);
                            listPlotNameImages.get(i).setImage(emptyNameImg);
                            switchPlantHarvest(listPlotImages.get(i), i, false);
                            listPlotWaterValues.get(i).setVisible(false);
                            this.plotViewModel.updatePlotStage(listPlots.get(i),
                                    playerViewModel.getPlayer());
                        }
                    }
                }
                textEvent.setText("Locusts");
                break;
            default:
                textEvent.setText("Normal");
        }
    }

    public void waterCrop(int plotNum) {
        if (playerViewModel.getPlayer().getCurrentWaterCounter() < playerViewModel.getPlayer().getMaxWateringPerDay()) {
            if (listPlots.get(plotNum) != null) {
                if (listPlots.get(plotNum).getWaterValue() > 0
                        && listPlots.get(plotNum).getWaterValue() <= 6) {
                    this.plotViewModel.waterPlot(listPlots.get(plotNum));
                    listPlotWaterValues.get(plotNum).setText(
                            doubleDigitString(listPlots.get(plotNum).getWaterValue()));
                    checkMaturity(plotNum, listPlots.get(plotNum), listPlotImages.get(plotNum),
                            listPlotWaterValues.get(plotNum));
                    playerViewModel.increasePlayerWaterCounter();
                    System.out.println("The max water is " + playerViewModel.getPlayer().getMaxWateringPerDay());
                    System.out.println("The current water counter is " + playerViewModel.getPlayer().getCurrentWaterCounter());
                }
            }
        } else {
            displayMaxWaterPopUp();
        }
    }

    public void fertilizePlot(int plotNum) {
        if (listPlots.get(plotNum) != null) {
            if (listPlots.get(plotNum).getFertilizerLevel() < 9
                    && playerViewModel.getPlayer().getUserStorage().getTotalFertilizer() > 1) {
                this.plotViewModel.fertilizePlot(listPlots.get(plotNum));
                playerViewModel.getPlayer().getUserStorage().setTotalFertilizer(
                        playerViewModel.getPlayer().getUserStorage().getTotalFertilizer() - 1);
                txtFertilizerCount.setText(doubleDigitString(
                        playerViewModel.getPlayer().getUserStorage().getTotalFertilizer() - 1));
                listPlotFertilizerLevels.get(plotNum).setText(
                        doubleDigitString(listPlots.get(plotNum).getFertilizerLevel()));
            }
        }
    }

    public void pesticidePlot(int plotNum) {
        if (listPlots.get(plotNum) != null) {
            if (listPlots.get(plotNum).getCropInPlot() != null
                    && !listPlots.get(plotNum).getCropInPlot().getHasPesticide()
                    && playerViewModel.getPlayer().getUserStorage().getTotalPesticide() > 1) {
                this.plotViewModel.pesticidePlot(listPlots.get(plotNum));
                playerViewModel.getPlayer().getUserStorage().setTotalPesticide(
                        playerViewModel.getPlayer().getUserStorage().getTotalPesticide() - 1);
                txtPesticideCount.setText(doubleDigitString(
                        playerViewModel.getPlayer().getUserStorage().getTotalPesticide() - 1));
                listPlotNameImages.get(plotNum).setImage(
                        chooseCropImage(listPlots.get(plotNum).getCropInPlot()));
            }
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
            case "Corn with Pesticide":
                return this.cornPesticideNameImg;
            case "Potato with Pesticide":
                return this.potatoPesticideNameImg;
            case "Tomato with Pesticide":
                return this.tomatoPesticideNameImg;
            default:
                return this.emptyNameImg;
        }
    }

    public void switchPlantHarvest(ImageView plotImg, int i, boolean isPlant) {
        switch (i) {
            case 0:
                if (isPlant) {
                    plotImg.setOnMouseClicked(this::harvestCropPlot1);
                } else {
                    plotImg.setOnMouseClicked(this::plantCropPlot1);
                }
                break;
            case 1:
                if (isPlant) {
                    plotImg.setOnMouseClicked(this::harvestCropPlot2);
                } else {
                    plotImg.setOnMouseClicked(this::plantCropPlot2);
                }
                break;
            case 2:
                if (isPlant) {
                    plotImg.setOnMouseClicked(this::harvestCropPlot3);
                } else {
                    plotImg.setOnMouseClicked(this::plantCropPlot3);
                }
                break;
            case 3:
                if (isPlant) {
                    plotImg.setOnMouseClicked(this::harvestCropPlot4);
                } else {
                    plotImg.setOnMouseClicked(this::plantCropPlot4);
                }
                break;
            case 4:
                if (isPlant) {
                    plotImg.setOnMouseClicked(this::harvestCropPlot5);
                } else {
                    plotImg.setOnMouseClicked(this::plantCropPlot5);
                }
                break;
            case 5:
                if (isPlant) {
                    plotImg.setOnMouseClicked(this::harvestCropPlot6);
                } else {
                    plotImg.setOnMouseClicked(this::plantCropPlot6);
                }
                break;
            case 6:
                if (isPlant) {
                    plotImg.setOnMouseClicked(this::harvestCropPlot7);
                } else {
                    plotImg.setOnMouseClicked(this::plantCropPlot7);
                }
                break;
            case 7:
                if (isPlant) {
                    plotImg.setOnMouseClicked(this::harvestCropPlot8);
                } else {
                    plotImg.setOnMouseClicked(this::plantCropPlot8);
                }
                break;
            case 8:
                if (isPlant) {
                    plotImg.setOnMouseClicked(this::harvestCropPlot9);
                } else {
                    plotImg.setOnMouseClicked(this::plantCropPlot9);
                }
                break;
            case 9:
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
        plantingInventory(0);
    }

    public void plantCropPlot2(MouseEvent mouseEvent) {
        plantingInventory(1);
    }

    public void plantCropPlot3(MouseEvent mouseEvent) {
        plantingInventory(2);
    }

    public void plantCropPlot4(MouseEvent mouseEvent) {
        plantingInventory(3);
    }

    public void plantCropPlot5(MouseEvent mouseEvent) {
        plantingInventory(4);
    }

    public void plantCropPlot6(MouseEvent mouseEvent) {
        plantingInventory(5);
    }

    public void plantCropPlot7(MouseEvent mouseEvent) {
        plantingInventory(6);
    }

    public void plantCropPlot8(MouseEvent mouseEvent) {
        plantingInventory(7);
    }

    public void plantCropPlot9(MouseEvent mouseEvent) {
        plantingInventory(8);
    }

    public void plantCropPlot10(MouseEvent mouseEvent) {
        plantingInventory(9);
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
        harvestCrop(0);
    }

    public void harvestCropPlot2(MouseEvent mouseEvent) {
        harvestCrop(1);
    }

    public void harvestCropPlot3(MouseEvent mouseEvent) {
        harvestCrop(2);
    }

    public void harvestCropPlot4(MouseEvent mouseEvent) {
        harvestCrop(3);
    }

    public void harvestCropPlot5(MouseEvent mouseEvent) {
        harvestCrop(4);
    }

    public void harvestCropPlot6(MouseEvent mouseEvent) {
        harvestCrop(5);
    }

    public void harvestCropPlot7(MouseEvent mouseEvent) {
        harvestCrop(6);
    }

    public void harvestCropPlot8(MouseEvent mouseEvent) {
        harvestCrop(7);
    }

    public void harvestCropPlot9(MouseEvent mouseEvent) {
        harvestCrop(8);
    }

    public void harvestCropPlot10(MouseEvent mouseEvent) {
        harvestCrop(9);
    }

    public void waterPlot1() {
        waterCrop(0);
    }

    public void waterPlot2() {
        waterCrop(1);
    }

    public void waterPlot3() {
        waterCrop(2);
    }

    public void waterPlot4() {
        waterCrop(3);
    }

    public void waterPlot5() {
        waterCrop(4);
    }

    public void waterPlot6() {
        waterCrop(5);
    }

    public void waterPlot7() {
        waterCrop(6);
    }

    public void waterPlot8() {
        waterCrop(7);
    }

    public void waterPlot9() {
        waterCrop(8);
    }

    public void waterPlot10() {
        waterCrop(9);
    }

    public void fertilizePlot1() {
        fertilizePlot(0);
    }

    public void fertilizePlot2() {
        fertilizePlot(1);
    }

    public void fertilizePlot3() {
        fertilizePlot(2);
    }

    public void fertilizePlot4() {
        fertilizePlot(3);
    }

    public void fertilizePlot5() {
        fertilizePlot(4);
    }

    public void fertilizePlot6() {
        fertilizePlot(5);
    }

    public void fertilizePlot7() {
        fertilizePlot(6);
    }

    public void fertilizePlot8() {
        fertilizePlot(7);
    }

    public void fertilizePlot9() {
        fertilizePlot(8);
    }

    public void fertilizePlot10() {
        fertilizePlot(9);
    }

    public void pesticidePlot1() {
        pesticidePlot(0);
    }

    public void pesticidePlot2() {
        pesticidePlot(1);
    }

    public void pesticidePlot3() {
        pesticidePlot(2);
    }

    public void pesticidePlot4() {
        pesticidePlot(3);
    }

    public void pesticidePlot5() {
        pesticidePlot(4);
    }

    public void pesticidePlot6() {
        pesticidePlot(5);
    }

    public void pesticidePlot7() {
        pesticidePlot(6);
    }

    public void pesticidePlot8() {
        pesticidePlot(7);
    }

    public void pesticidePlot9() {
        pesticidePlot(8);
    }

    public void pesticidePlot10() {
        pesticidePlot(9);
    }

    public void setUpPlotName(int plotNum, String str) {
        if (str.equals("Corn")) {
            listPlotNameImages.get(plotNum).setImage(cornNameImg);
        } else if (str.equals("Potato")) {
            listPlotNameImages.get(plotNum).setImage(potatoNameImg);
        } else {
            listPlotNameImages.get(plotNum).setImage(tomatoNameImg);
        }
    }

    private String doubleDigitString(int num) {
        String str;
        if (num < 10) {
            str = "0" + num;
        } else {
            str = String.valueOf(num);
        }
        return str;
    }

    /**
     * Switches screen to the Market UI screen.
     *
     * @param mouseEvent is the mouse trigger event
     */
    public void goToMarket(MouseEvent mouseEvent) {
        try {
            Stage stage = (Stage) btnMarket.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("../marketUI/MarketUI.fxml"));
            stage.setScene(new Scene(loader.load()));
            MarketUIController marketUIController = loader.getController();
            marketUIController.initData(mouseEvent, playerViewModel, storageViewModel);
            stage.setTitle("Market");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayMaxHarvestPopUp() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("PopUp.fxml"));
            stage.setScene(new Scene(loader.load()));
            PopUpController popUpController = loader.getController();
            popUpController.initData("You are out of harvests for the day."
                    + " Please wait until the next day or purchase tractors.");
            stage.setTitle("Alert");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayMaxWaterPopUp() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("PopUp.fxml"));
            stage.setScene(new Scene(loader.load()));
            PopUpController popUpController = loader.getController();
            popUpController.initData("You are out of watering for the day."
                    + " Please wait until the next day or purchase irrigation.");
            stage.setTitle("Alert");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
