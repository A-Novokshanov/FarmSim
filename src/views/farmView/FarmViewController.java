package views.farmView;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.CropModel;
import models.PlotModel;
import models.PlotTemplate;
import models.WorkerModel;
import viewmodels.*;
import views.marketUI.MarketUIController;


import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A view class that controls the UI elements for the main farm screen.
 *
 * @author Matthew Farias, Shaun Jacob
 * @version 0.4
 */
public class FarmViewController {
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
    private GridPane gridPane;


    @FXML
    private JFXButton btnCornPlant;
    @FXML
    private JFXButton btnPotatoPlant;
    @FXML
    private JFXButton btnTomatoPlant;

    @FXML
    private Text txtFertilizerCount;
    @FXML
    private Text txtPesticideCount;

    @FXML
    private Text txtPopUp;

    @FXML
    private ObservableList<PlotTemplate> plotsObservableList;

    private ArrayDeque<int[]> gridPositionDeque = new ArrayDeque<>(Arrays.asList(
            new int[]{1, 1}, new int[]{2, 1}, new int[]{3, 1}, new int[]{4, 1}, new int[]{5, 1},
            new int[]{1, 2}, new int[]{2, 2}, new int[]{3, 2}, new int[]{4, 2}, new int[]{5, 2},
            new int[]{0, 1}, new int[]{0, 2}, new int[]{6, 1}, new int[]{6, 2},
            new int[]{1, 3}, new int[]{2, 3}, new int[]{3, 3}, new int[]{4, 3}, new int[]{5, 3}));

    private ArrayList<PlotModel> listPlots = new ArrayList<>();
    private ArrayList<ImageView> listPlotImages = new ArrayList<>();
    private ArrayList<ImageView> listPlotNameImages = new ArrayList<>();
    private ArrayList<Text> listPlotWaterValues = new ArrayList<>();
    private ArrayList<Text> listPlotFertilizerLevels = new ArrayList<>();

    private final Image dirtImg = new Image("@../../dependencies/images/Dirt.png",
            150.0, 150.0, true, false);
    private final Image emptyNameImg = new Image("@../../dependencies/images/Crop_Bar_Empty.png",
            150.0, 50.0, true, false);
    private final Image cornNameImg = new Image("@../../dependencies/images/Crop_Bar_Corn.png",
            150.0, 50.0, true, false);
    private final Image potatoNameImg = new Image("@../../dependencies/images/Crop_Bar_Potato.png",
            150.0, 50.0, true, false);
    private final Image tomatoNameImg = new Image("@../../dependencies/images/Crop_Bar_Tomato.png",
            150.0, 50.0, true, false);
    private final Image cornPesticideNameImg = new Image(
            "@../../dependencies/images/Crop_Bar_Corn_Pesticide.png",
            150.0, 50.0, true, false);
    private final Image potatoPesticideNameImg = new Image(
            "@../../dependencies/images/Crop_Bar_Potato_Pesticide.png",
            150.0, 50.0, true, false);
    private final Image tomatoPesticideNameImg = new Image(
            "@../../dependencies/images/Crop_Bar_Tomato_Pesticide.png",
            150.0, 50.0, true, false);
    private final Image waterImage = new Image(
            "@../../dependencies/images/water_can.png",
            25.0, 25.0, true, false);
    private final Image fertilizerImage = new Image(
            "@../../dependencies/images/Fertilizer.png",
            25.0, 25.0, true, false);
    private final Image pesticideImage = new Image(
            "@../../dependencies/images/pesticide.png",
            25.0, 25.0, true, false);

    private PlayerViewModel playerViewModel;
    private StorageViewModel storageViewModel;
    private PlotViewModel plotViewModel;
    private EventViewModel eventViewModel;
    private MaturityView maturityView;
    private PlantView plantView;
    private HarvestView harvestView;
    private WaterView waterView;
    private FertilizeView fertilizeView;
    private PesticideView pesticideView;
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
        this.playerViewModel = playerViewModel;
        this.storageViewModel = new StorageViewModel(playerViewModel);
        this.eventViewModel = new EventViewModel(playerViewModel.getPlayer());
        this.plotViewModel = new PlotViewModel(playerViewModel.getPlayer());
        this.maturityView = new MaturityView(playerViewModel, plotViewModel);
        this.plantView = new PlantView(playerViewModel, plotViewModel);
        this.harvestView = new HarvestView(playerViewModel, plotViewModel);
        this.waterView = new WaterView(playerViewModel, plotViewModel, maturityView);
        this.fertilizeView = new FertilizeView(playerViewModel, plotViewModel);
        this.pesticideView = new PesticideView(playerViewModel, plotViewModel);
        this.money.setText("$ " + playerViewModel.getPlayer().getUserCurrentMoney());
        this.dayNum.setText("Day " + doubleDigitString(this.playerViewModel.getPlayer().getDays()));
        this.txtFertilizerCount.setText(doubleDigitString(
                this.playerViewModel.getPlayer().getUserStorage().getTotalFertilizer() - 1));
        this.txtPesticideCount.setText(doubleDigitString(
                this.playerViewModel.getPlayer().getUserStorage().getTotalPesticide() - 1));
    }

    private void setUpPlotModels(CropModel cropModel) {
        this.plotsObservableList = FXCollections.observableArrayList();
        for (int i = 0; i < 10; i++) {
            PlotModel plot = plotViewModel.populatePlot(cropModel);
            Image plotImage = maturityView.setInitialMaturity(plot);
            PlotTemplate plotTemplate = new PlotTemplate(plot, plotImage, chooseCropImage(cropModel), "03", "00");
            plotsObservableList.add(plotTemplate);
            Pane pane = createPane(i);
            int[] gridPosition = gridPositionDeque.remove();
            gridPane.add(pane, gridPosition[0], gridPosition[1]);
        }
    }

    private Pane createPane(int i) {
        Pane pane = new Pane();
        pane.getChildren().add(plotsObservableList.get(i).getPlotImageView());
        ImageView plotNameImage = plotsObservableList.get(i).getNameImageView();
        plotNameImage.setLayoutY(100);
        pane.getChildren().add(plotNameImage);
        ImageView waterImageView = new ImageView(waterImage);
        waterImageView.setLayoutX(7);
        waterImageView.setLayoutY(10);
        pane.getChildren().add(waterImageView);
        ImageView fertilizerImageView = new ImageView(fertilizerImage);
        fertilizerImageView.setLayoutX(7);
        fertilizerImageView.setLayoutY(38);
        pane.getChildren().add(fertilizerImageView);
        ImageView pesticideImageView = new ImageView(pesticideImage);
        pesticideImageView.setLayoutX(10);
        pesticideImageView.setLayoutY(66);
        pane.getChildren().add(pesticideImageView);
        Text waterValueText = plotsObservableList.get(i).getWaterValue();
        waterValueText.setStyle("-fx-font: 18 System");
        waterValueText.setLayoutX(124);
        waterValueText.setLayoutY(28);
        pane.getChildren().add(waterValueText);
        Text fertilizerValueText = plotsObservableList.get(i).getFertilizerValue();
        fertilizerValueText.setStyle("-fx-font: 18 System");
        fertilizerValueText.setLayoutX(124);
        fertilizerValueText.setLayoutY(50);
        pane.getChildren().add(fertilizerValueText);
        return pane;
    }

    public void setUpPlotModels(List<PlotModel> plotModels) {
        this.plotsObservableList = FXCollections.observableArrayList();
        for (int i = 0; i < 10; i++) {
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
            checkMaturity(i, listPlotWaterValues.get(i));
        }
    }

    public void checkMaturity(int plotNum, Text waterValue) {
        maturityView.checkMaturity(plotsObservableList, plotNum, waterValue);
    }

    public void plantingInventory(int plotNum) {
        toggleInventoryScreenVisibility();
        turnOnPlantBtnVisibility();
        this.plantingPlotNum = plotNum;
    }

    public void plantCrop(int cropNum, CropModel crop) {
        if (storageViewModel.userInventory().get(cropNum).getCropQuantity() > 2) {
            storageViewModel.userInventory().get(cropNum).setCropQuantity(
                    storageViewModel.userInventory().get(cropNum).getCropQuantity() - 1);
            toggleInventoryScreenVisibility();
            plantView.plantCrop(plotsObservableList, cropNum, crop);
            updateWaterValue(listPlots.get(cropNum), listPlotWaterValues.get(cropNum));
            listPlotWaterValues.get(cropNum).setVisible(true);
            switchPlantHarvest(listPlotImages.get(cropNum), cropNum, true);
        }
    }

    /**
     * @param plotNum The harvested plot's number.
     */
    public void harvestCrop(int plotNum) {
        if (playerViewModel.getPlayer().getCurrentHarvestCounter()
                < playerViewModel.getPlayer().getMaxHarvestsPerDay()) {
            harvestView.harvestCrop(plotsObservableList, plotNum, listPlotWaterValues.get(plotNum));
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
            waterView.waterCrop(plotsObservableList, plotNum, listPlotWaterValues.get(plotNum));
        } else {
            displayMaxWaterPopUp();
        }
    }

    public void fertilizePlot(int plotNum) {
        fertilizeView.fertilizePlot(plotsObservableList, plotNum);
        txtFertilizerCount.setText(doubleDigitString(
                playerViewModel.getPlayer().getUserStorage().getTotalFertilizer() - 1));
    }

    public void pesticidePlot(int plotNum) {
        pesticideView.pesticidePlot(plotsObservableList, plotNum);
        txtPesticideCount.setText(doubleDigitString(
                playerViewModel.getPlayer().getUserStorage().getTotalPesticide() - 1));
    }

    private Image chooseCropImage(CropModel crop) {
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
                    getClass().getResource("PopUpView.fxml"));
            stage.setScene(new Scene(loader.load()));
            PopUpViewController popUpViewController = loader.getController();
            popUpViewController.initData("You are out of harvests for the day."
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
                    getClass().getResource("PopUpView.fxml"));
            stage.setScene(new Scene(loader.load()));
            PopUpViewController popUpViewController = loader.getController();
            popUpViewController.initData("You are out of watering for the day."
                    + " Please wait until the next day or purchase irrigation.");
            stage.setTitle("Alert");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
