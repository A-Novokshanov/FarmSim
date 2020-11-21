package views.farmView;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.WeakListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
import viewmodels.*;
import views.marketView.MarketViewController;


import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * A view class that controls the UI elements for the main farm screen.
 *
 * @author Matthew Farias, Shaun Jacob
 * @version 1.0
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
    private JFXButton btnAddPlot;
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
    private HashMap<Integer, int[]> gridPositionMap = new HashMap<>();

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
        setUpPlotObservableList(
                playerViewModel.getPlayer().getPlayerSettings().getStartingCropType());
    }

    public void initSaveData(PlayerViewModel playerViewModel) {
        setUpData(playerViewModel);
        setUpPlotObservableList(plotViewModel.getPlotsFromDatabase());
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

    private void setUpPlotObservableList(CropModel cropModel) {
        this.plotsObservableList = FXCollections.observableArrayList();
        for (int i = 0; i < 10; i++) {
            PlotModel plot = plotViewModel.populatePlot(cropModel);
            Image plotImage = maturityView.setInitialMaturity(plot);
            PlotTemplate plotTemplate = new PlotTemplate(plot, plotImage, chooseCropImage(cropModel),
                    "03", "00");
            plotViewModel.addPlotDatabase(plot, playerViewModel.getPlayer());
            plotViewModel.updatePlotStage(plot, playerViewModel.getPlayer());
            plotViewModel.updatePlotFertilizerDatabase(plot, playerViewModel.getPlayer());
            plotsObservableList.add(plotTemplate);
            Pane pane = createPane(plotTemplate, i);
            int[] gridPosition = gridPositionDeque.remove();
            gridPositionMap.put(i, gridPosition);
            gridPane.add(pane, gridPosition[0], gridPosition[1]);
        }
    }

    public void setUpPlotObservableList(List<PlotModel> listPlots) {
        this.plotsObservableList = FXCollections.observableArrayList();
        for (int i = 0; i < listPlots.size(); i++) {
            PlotTemplate plotTemplate = new PlotTemplate(listPlots.get(i),
                    maturityView.getMaturityImage(listPlots.get(i)),
                    chooseCropImage(listPlots.get(i).getCropInPlot()),
                    doubleDigitString(listPlots.get(i).getWaterValue()),
                    doubleDigitString(listPlots.get(i).getFertilizerLevel()));
            plotsObservableList.add(plotTemplate);
            Pane pane = createPane(plotsObservableList.get(i), i);
            int[] gridPosition = gridPositionDeque.remove();
            gridPositionMap.put(i, gridPosition);
            gridPane.add(pane, gridPosition[0], gridPosition[1]);
        }
    }

    private Pane createPane(PlotTemplate plotTemplate, int iD) {
        Pane pane = new Pane();
        pane.getChildren().add(plotTemplate.getPlotImageView());
        ImageView plotNameImage = plotTemplate.getNameImageView();
        plotNameImage.setLayoutY(100);
        pane.getChildren().add(plotNameImage);
        ImageView waterImageView = new ImageView(waterImage);
        waterImageView.setId("Water" + iD);
        waterImageView.setOnMouseClicked(this::waterCrop);
        waterImageView.setLayoutX(7);
        waterImageView.setLayoutY(10);
        pane.getChildren().add(waterImageView);
        ImageView fertilizerImageView = new ImageView(fertilizerImage);
        fertilizerImageView.setId("Fertilizer" + iD);
        fertilizerImageView.setOnMouseClicked(this::fertilizePlot);
        fertilizerImageView.setLayoutX(7);
        fertilizerImageView.setLayoutY(38);
        pane.getChildren().add(fertilizerImageView);
        ImageView pesticideImageView = new ImageView(pesticideImage);
        pesticideImageView.setId("Pesticide" + iD);
        pesticideImageView.setOnMouseClicked(this::pesticidePlot);
        pesticideImageView.setLayoutX(10);
        pesticideImageView.setLayoutY(66);
        pane.getChildren().add(pesticideImageView);
        Text waterValueText = plotTemplate.getWaterValue();
        waterValueText.setStyle("-fx-font: 18 System");
        waterValueText.setLayoutX(124);
        waterValueText.setLayoutY(28);
        pane.getChildren().add(waterValueText);
        Text fertilizerValueText = plotTemplate.getFertilizerValue();
        fertilizerValueText.setStyle("-fx-font: 18 System");
        fertilizerValueText.setLayoutX(124);
        fertilizerValueText.setLayoutY(50);
        pane.getChildren().add(fertilizerValueText);
        return pane;
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
     * Method to increase the total plot amount.
     */
    public void addPlot() {
        if (!gridPositionDeque.isEmpty()) {
            PlotModel plot = new PlotModel(null, 0);
            PlotTemplate plotTemplate = new PlotTemplate(plot, dirtImg, emptyNameImg,
                    "03", "00");
            plotViewModel.addPlotDatabase(plot, playerViewModel.getPlayer());
            plotViewModel.updatePlotStage(plot, playerViewModel.getPlayer());
            plotViewModel.updatePlotFertilizerDatabase(plot, playerViewModel.getPlayer());
            plotsObservableList.add(plotTemplate);
            Pane pane = createPane(plotTemplate, plotsObservableList.size());
            int[] gridPosition = gridPositionDeque.remove();
            gridPane.add(pane, gridPosition[0], gridPosition[1]);
        }
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
            if (plotsObservableList.get(i).getPlotModel().getCropInPlot() != null) {
                plotViewModel.incrementPlotDaysOld(plotsObservableList.get(i).getPlotModel(), playerViewModel);
                plotViewModel.updatePlotMaturity(plotsObservableList.get(i).getPlotModel(), playerViewModel.getPlayer());
                this.plotViewModel.updateWaterValue(plotsObservableList.get(i).getPlotModel().getWaterValue(),
                        plotsObservableList.get(i).getPlotModel().getPlotIdentifier());
            }
        }
        eventRoll();
    }

//    private void updateWaterValueAll() {
//        for (int i = 0; i < 10; i++) {
//            updateWaterValue(plotsObservableList.get(i).getPlotModel(),
//                    plotsObservableList.get(i).getPlotModel().getWaterValue());
//        }
//    }
//
//    private void updateWaterValue(PlotModel plot, Text waterValue) {
//        if (plot.getCropInPlot() != null) {
//            String str = doubleDigitString(plot.getWaterValue());
//            waterValue.setText(str);
//        }
//    }

//    private void updateFertilizerLevelAll() {
//        for (int i = 0; i < 10; i++) {
//            updateFertilizerLevel(listPlots.get(i), listPlotFertilizerLevels.get(i));
//        }
//    }
//
//    private void updateFertilizerLevel(PlotModel plot, Text fertilizerLevel) {
//        if (plot.getFertilizerLevel() > 0) {
//            String str = doubleDigitString(plot.getFertilizerLevel());
//            fertilizerLevel.setText(str);
//        }
//    }

    public void checkMaturity(int plotNum, Text waterValue) {
        maturityView.checkMaturity(plotsObservableList, plotNum, waterValue);
    }

    public void plantingInventory(int plotNum) {
        toggleInventoryScreenVisibility();
        turnOnPlantBtnVisibility();
        this.plantingPlotNum = plotNum;
    }

//    public void plantCrop(int cropNum, CropModel crop) {
//        if (storageViewModel.userInventory().get(cropNum).getCropQuantity() > 2) {
//            storageViewModel.userInventory().get(cropNum).setCropQuantity(
//                    storageViewModel.userInventory().get(cropNum).getCropQuantity() - 1);
//            toggleInventoryScreenVisibility();
//            plantView.plantCrop(plotsObservableList, cropNum, crop);
//            updateWaterValue(listPlots.get(cropNum), listPlotWaterValues.get(cropNum));
//            listPlotWaterValues.get(cropNum).setVisible(true);
//            switchPlantHarvest(listPlotImages.get(cropNum), cropNum, true);
//        }
//    }

    public void harvestCrop(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        int plotNum = Character.getNumericValue(
                imageView.getId().charAt(imageView.getId().length() - 1));
        if (playerViewModel.getPlayer().getCurrentHarvestCounter()
                < playerViewModel.getPlayer().getMaxHarvestsPerDay()) {
            harvestView.harvestCrop(plotsObservableList, plotNum);
        } else {
            displayMaxHarvestPopUp();
        }
    }

    private void eventRoll() {

    }

    public void waterCrop(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        int plotNum = Character.getNumericValue(
                imageView.getId().charAt(imageView.getId().length() - 1));
        waterView.waterCrop(plotsObservableList, plotNum, plotsObservableList.get(plotNum).getWaterValue());
        txtFertilizerCount.setText(doubleDigitString(
                playerViewModel.getPlayer().getUserStorage().getTotalFertilizer() - 1));
    }

    public void fertilizePlot(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        int plotNum = Character.getNumericValue(
                imageView.getId().charAt(imageView.getId().length() - 1));
        fertilizeView.fertilizePlot(plotsObservableList, plotNum);
        txtFertilizerCount.setText(doubleDigitString(
                playerViewModel.getPlayer().getUserStorage().getTotalFertilizer() - 1));
    }

    public void pesticidePlot(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        int plotNum = Character.getNumericValue(
                imageView.getId().charAt(imageView.getId().length() - 1));
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

//    public void switchPlantHarvest(ImageView plotImg, int i, boolean isPlant) {
//        switch (i) {
//            case 0:
//                if (isPlant) {
//                    plotImg.setOnMouseClicked(this::harvestCropPlot1);
//                } else {
//                    plotImg.setOnMouseClicked(this::plantCropPlot1);
//                }
//                break;
//            case 1:
//                if (isPlant) {
//                    plotImg.setOnMouseClicked(this::harvestCropPlot2);
//                } else {
//                    plotImg.setOnMouseClicked(this::plantCropPlot2);
//                }
//                break;
//            case 2:
//                if (isPlant) {
//                    plotImg.setOnMouseClicked(this::harvestCropPlot3);
//                } else {
//                    plotImg.setOnMouseClicked(this::plantCropPlot3);
//                }
//                break;
//            case 3:
//                if (isPlant) {
//                    plotImg.setOnMouseClicked(this::harvestCropPlot4);
//                } else {
//                    plotImg.setOnMouseClicked(this::plantCropPlot4);
//                }
//                break;
//            case 4:
//                if (isPlant) {
//                    plotImg.setOnMouseClicked(this::harvestCropPlot5);
//                } else {
//                    plotImg.setOnMouseClicked(this::plantCropPlot5);
//                }
//                break;
//            case 5:
//                if (isPlant) {
//                    plotImg.setOnMouseClicked(this::harvestCropPlot6);
//                } else {
//                    plotImg.setOnMouseClicked(this::plantCropPlot6);
//                }
//                break;
//            case 6:
//                if (isPlant) {
//                    plotImg.setOnMouseClicked(this::harvestCropPlot7);
//                } else {
//                    plotImg.setOnMouseClicked(this::plantCropPlot7);
//                }
//                break;
//            case 7:
//                if (isPlant) {
//                    plotImg.setOnMouseClicked(this::harvestCropPlot8);
//                } else {
//                    plotImg.setOnMouseClicked(this::plantCropPlot8);
//                }
//                break;
//            case 8:
//                if (isPlant) {
//                    plotImg.setOnMouseClicked(this::harvestCropPlot9);
//                } else {
//                    plotImg.setOnMouseClicked(this::plantCropPlot9);
//                }
//                break;
//            case 9:
//                if (isPlant) {
//                    plotImg.setOnMouseClicked(this::harvestCropPlot10);
//                } else {
//                    plotImg.setOnMouseClicked(this::plantCropPlot10);
//                }
//                break;
//            default:
//                break;
//        }
//    }

//    public void chooseCorn() {
//        plantCrop(0, storageViewModel.userInventory().get(0));
//    }
//
//    public void choosePotato() {
//        plantCrop(1, storageViewModel.userInventory().get(1));
//    }
//
//    public void chooseTomato() {
//        plantCrop(2, storageViewModel.userInventory().get(2));
//    }


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
                    getClass().getResource("../marketView/MarketView.fxml"));
            stage.setScene(new Scene(loader.load()));
            MarketViewController marketViewController = loader.getController();
            marketViewController.initData(mouseEvent, playerViewModel, storageViewModel);
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
