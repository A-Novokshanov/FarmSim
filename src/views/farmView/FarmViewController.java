package views.farmView;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.List;

/**
 * A view class that controls the UI elements for the main farm screen.
 *
 * @author Matthew Farias, Shaun Jacob
 * @version 1.0
 */
public class FarmViewController {
    @FXML
    private Text budget;
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
    private Pane btnAddPlot;
    @FXML
    private JFXButton btnHome;
    @FXML
    private Text plotPrice;
    @FXML
    private GridPane gridPane;
    @FXML
    private Pane gameOverScreen;

    @FXML
    private JFXButton btnPlant00;
    @FXML
    private JFXButton btnPlant01;
    @FXML
    private JFXButton btnPlant02;

    @FXML
    private Text txtFertilizerCount;
    @FXML
    private Text txtPesticideCount;
    @FXML
    private Text txtDailyWaterCount;
    @FXML
    private Text txtDailyHarvestCount;

    @FXML
    private ObservableList<PlotTemplate> plotsObservableList;

    private ArrayDeque<int[]> gridPositionDeque = new ArrayDeque<>(Arrays.asList(
            new int[]{1, 1}, new int[]{2, 1}, new int[]{3, 1}, new int[]{4, 1}, new int[]{5, 1},
            new int[]{1, 2}, new int[]{2, 2}, new int[]{3, 2}, new int[]{4, 2}, new int[]{5, 2},
            new int[]{0, 1}, new int[]{0, 2}, new int[]{6, 1}, new int[]{6, 2},
            new int[]{1, 3}, new int[]{2, 3}, new int[]{3, 3}, new int[]{4, 3}, new int[]{5, 3}));

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
    private MarketViewModel marketViewModel;
    private PlotViewModel plotViewModel;
    private MaturityView maturityView;
    private PlantView plantView;
    private HarvestView harvestView;
    private WaterView waterView;
    private FertilizeView fertilizeView;
    private PesticideView pesticideView;
    private EventView eventView;
    private int plantingPlotNum;
    private int emptyPlotCounter;

    /**
     * Initializes data from the Initial Configuration screen.
     *
     * @param playerViewModel Setting View Model to access player details.
     */
    public void initConfigData(PlayerViewModel playerViewModel) {
        setUpData(playerViewModel);
        setUpPlots(
                playerViewModel.getPlayer().getPlayerSettings().getStartingCropType());
    }

    public void initSaveData(PlayerViewModel playerViewModel) {
        setUpData(playerViewModel);
        setUpPlots(plotViewModel.getPlotsFromDatabase());
        checkGameOver();
    }

    public void setUpData(PlayerViewModel playerViewModel) {
        this.playerViewModel = playerViewModel;
        this.storageViewModel = new StorageViewModel(playerViewModel);
        this.marketViewModel = new MarketViewModel(playerViewModel);
        this.plotViewModel = new PlotViewModel(playerViewModel.getPlayer());
        this.maturityView = new MaturityView(playerViewModel, plotViewModel);
        this.plantView = new PlantView(playerViewModel, plotViewModel);
        this.harvestView = new HarvestView(playerViewModel, plotViewModel);
        this.waterView = new WaterView(playerViewModel, plotViewModel, maturityView);
        this.fertilizeView = new FertilizeView(playerViewModel, plotViewModel);
        this.pesticideView = new PesticideView(playerViewModel, plotViewModel);
        this.eventView = new EventView(playerViewModel, plotViewModel, maturityView);
        this.budget.setText("$ " + playerViewModel.getPlayer().getUserCurrentMoney());
        this.dayNum.setText("Day " + doubleDigitString(this.playerViewModel.getPlayer().getDays()));
        this.txtFertilizerCount.setText(doubleDigitString(
                this.playerViewModel.getPlayer().getUserStorage().getTotalFertilizer() - 1));
        this.txtPesticideCount.setText(doubleDigitString(
                this.playerViewModel.getPlayer().getUserStorage().getTotalPesticide() - 1));
        this.txtDailyWaterCount.setText(String.valueOf(
                this.playerViewModel.getPlayer().getMaxWateringPerDay()
                        - this.playerViewModel.getPlayer().getCurrentWaterCounter()));
        this.txtDailyHarvestCount.setText(String.valueOf(
                this.playerViewModel.getPlayer().getMaxHarvestsPerDay()
                - this.playerViewModel.getPlayer().getCurrentHarvestCounter()));
        this.btnPlant00.setId("btnPlant00");
        this.btnPlant01.setId("btnPlant01");
        this.btnPlant02.setId("btnPlant02");
    }

    private void setUpPlots(CropModel cropModel) {
        this.plotsObservableList = FXCollections.observableArrayList();
        for (int i = 0; i < 10; i++) {
            PlotModel plot = plotViewModel.populatePlot(cropModel);
            Image plotImage = maturityView.setInitialMaturity(plot);
            PlotTemplate plotTemplate = new PlotTemplate(plot, plotImage,
                    chooseCropImage(cropModel),
                    "03", "00");
            plotViewModel.addPlotDatabase(plot, playerViewModel.getPlayer());
            plotViewModel.updatePlotStage(plot, playerViewModel.getPlayer());
            plotViewModel.updatePlotFertilizerDatabase(plot, playerViewModel.getPlayer());
            plotsObservableList.add(plotTemplate);
            Pane pane = createPane(plotTemplate, i);
            int[] gridPosition = gridPositionDeque.remove();
            gridPane.add(pane, gridPosition[0], gridPosition[1]);
        }
        this.plotPrice.setText("$" + marketViewModel.calculatePlotPrice(
                plotsObservableList.size()));
    }

    public void setUpPlots(List<PlotModel> listPlots) {
        this.plotsObservableList = FXCollections.observableArrayList();
        emptyPlotCounter = 0;
        for (int i = 0; i < listPlots.size(); i++) {
            PlotTemplate plotTemplate = new PlotTemplate(listPlots.get(i),
                maturityView.getMaturityImage(listPlots.get(i)),
                chooseCropImage(listPlots.get(i).getCropInPlot()),
                doubleDigitString(listPlots.get(i).getWaterValue()),
                doubleDigitString(listPlots.get(i).getFertilizerLevel()));
            plotsObservableList.add(plotTemplate);
            Pane pane = createPane(plotsObservableList.get(i), i);
            int[] gridPosition = gridPositionDeque.remove();
            gridPane.add(pane, gridPosition[0], gridPosition[1]);
            if (listPlots.get(i).getCropInPlot() == null) {
                emptyPlotCounter++;
            }
        }
        this.plotPrice.setText("$" + marketViewModel.calculatePlotPrice(
                plotsObservableList.size()));
    }

    /**
     * Method to create a pane to be added to gridPane.
     * @param plotTemplate The plot template used to create a new pane
     * @param iD The pane's given id number, used to when calling a method for a given pane.
     * @return The created pane for gridPane.
     */
    private Pane createPane(PlotTemplate plotTemplate, int iD) {
        ImageView plotImage = plotTemplate.getPlotImageView();
        plotImage.setId("Plot" + doubleDigitString(iD));
        if (plotTemplate.getPlotModel().getCropInPlot() == null) {
            plotImage.setOnMouseClicked(this::plantCrop);
        } else {
            plotImage.setOnMouseClicked(this::harvestCrop);
        }

        ImageView plotNameImage = plotTemplate.getNameImageView();
        plotNameImage.setLayoutY(100);

        ImageView waterImageView = new ImageView(waterImage);
        waterImageView.setId("Water" + doubleDigitString(iD));
        waterImageView.setOnMouseClicked(this::waterCrop);
        waterImageView.setLayoutX(7);
        waterImageView.setLayoutY(10);

        ImageView fertilizerImageView = new ImageView(fertilizerImage);
        fertilizerImageView.setId("Fertilizer" + doubleDigitString(iD));
        fertilizerImageView.setOnMouseClicked(this::fertilizePlot);
        fertilizerImageView.setLayoutX(7);
        fertilizerImageView.setLayoutY(38);

        ImageView pesticideImageView = new ImageView(pesticideImage);
        pesticideImageView.setId("Pesticide" + doubleDigitString(iD));
        pesticideImageView.setOnMouseClicked(this::pesticidePlot);
        pesticideImageView.setLayoutX(10);
        pesticideImageView.setLayoutY(66);

        Text waterValueText = plotTemplate.getWaterValueText();
        waterValueText.setStyle("-fx-font: 18 System");
        waterValueText.setLayoutX(124);
        waterValueText.setLayoutY(28);
        if (plotTemplate.getPlotModel().getCropInPlot() == null
                || plotTemplate.getPlotModel().getStage().equals("Withered")) {
            waterValueText.setVisible(false);
        }

        Text fertilizerValueText = plotTemplate.getFertilizerValueText();
        fertilizerValueText.setStyle("-fx-font: 18 System");
        fertilizerValueText.setLayoutX(124);
        fertilizerValueText.setLayoutY(50);

        Pane pane = new Pane();
        pane.getChildren().add(plotImage);
        pane.getChildren().add(plotNameImage);
        pane.getChildren().add(waterImageView);
        pane.getChildren().add(fertilizerImageView);
        pane.getChildren().add(pesticideImageView);
        pane.getChildren().add(waterValueText);
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
        btnAddPlot.setVisible(!btnAddPlot.isVisible());
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
            boolean bought = marketViewModel.purchasePlot(plot, plotsObservableList.size());
            if (bought) {
                plotViewModel.updatePlotStage(plot, playerViewModel.getPlayer());
                plotViewModel.updatePlotFertilizerDatabase(plot, playerViewModel.getPlayer());
                plotsObservableList.add(plotTemplate);
                Pane pane = createPane(plotTemplate, plotsObservableList.size() - 1);
                int[] gridPosition = gridPositionDeque.remove();
                gridPane.add(pane, gridPosition[0], gridPosition[1]);
                this.budget.setText("$ " + playerViewModel.getPlayer().getUserCurrentMoney());
                this.plotPrice.setText(
                        "$" + marketViewModel.calculatePlotPrice(plotsObservableList.size()));
            }
        }
    }

    /**
     * Makes "Plant" buttons on Inventory screen visible.
     */
    public void turnOnPlantBtnVisibility() {
        btnPlant00.setVisible(true);
        btnPlant01.setVisible(true);
        btnPlant02.setVisible(true);
    }

    /**
     * Makes "Plant" buttons on Inventory screen visible.
     */
    public void turnOffPlantBtnVisibility() {
        btnPlant00.setVisible(false);
        btnPlant01.setVisible(false);
        btnPlant02.setVisible(false);
    }

    public void updateDay() {
        this.playerViewModel.getPlayer().setDays(
                this.playerViewModel.getPlayer().getDays() + 1);
        dayNum.setText("Day " + doubleDigitString(this.playerViewModel.getPlayer().getDays()));
        this.playerViewModel.updatePlayerDay(
                this.playerViewModel.getPlayer().getPlayerSettings().getPlayerName());
        eventRoll();
        emptyPlotCounter = 0;
        incrementAllPlotDays();
        playerViewModel.zeroCurrentHarvestCounter();
        playerViewModel.zeroCurrentWaterCounter();
        this.txtDailyWaterCount.setText(String.valueOf(
                this.playerViewModel.getPlayer().getMaxWateringPerDay()));
        this.txtDailyHarvestCount.setText(String.valueOf(
                this.playerViewModel.getPlayer().getMaxHarvestsPerDay()));
        if (emptyPlotCounter == plotsObservableList.size()) {
            if (playerViewModel.getPlayer().getUserStorage().getTotalCropAmount() <= 0) {
                double basePrice = storageViewModel.userInventory().get(2).getCropValue();
                String curDifficulty =
                        playerViewModel.getPlayer().getPlayerSettings().getStartingDifficulty();
                double calPrice = marketViewModel.calculateCropPrice(basePrice, curDifficulty);
                if (playerViewModel.getPlayer().getUserCurrentMoney() < calPrice) {
                    displayGameOverScreen();
                }
            }
        }
    }

    private void incrementAllPlotDays() {
        for (int i = 0; i < plotsObservableList.size(); i++) {
            if (plotsObservableList.get(i).getPlotModel().getCropInPlot() != null) {
                plotViewModel.incrementPlotDaysOld(
                        plotsObservableList.get(i).getPlotModel(), playerViewModel);
                plotsObservableList.get(i).setWaterValue(doubleDigitString(
                        plotsObservableList.get(i).getPlotModel().getWaterValue()));
                plotsObservableList.get(i).setFertilizerValue(doubleDigitString(
                        plotsObservableList.get(i).getPlotModel().getFertilizerLevel()));
                maturityView.checkMaturity(plotsObservableList, i);
                this.plotViewModel.updatePlotMaturity(plotsObservableList.get(i).getPlotModel(),
                        playerViewModel.getPlayer());
                this.plotViewModel.updatePlotDaysDatabase(plotsObservableList.get(i).getPlotModel(),
                        playerViewModel.getPlayer());
                this.plotViewModel.updateWaterValue(
                        plotsObservableList.get(i).getPlotModel().getWaterValue(),
                        plotsObservableList.get(i).getPlotModel().getPlotIdentifier());
                Pane pane = (Pane) gridPane.getChildren().get(i);
                ImageView plotImageView = (ImageView) pane.getChildren().get(0);
                plotImageView.setImage(plotsObservableList.get(i).getPlotImage());
                pane.getChildren().set(5, plotsObservableList.get(i).getWaterValueText());
                pane.getChildren().set(6, plotsObservableList.get(i).getFertilizerValueText());
            } else {
                emptyPlotCounter++;
            }
        }
    }

    public void plantCrop(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        this.plantingPlotNum = getIdString(imageView.getId());
        toggleInventoryScreenVisibility();
        turnOnPlantBtnVisibility();
    }

    public void plantCropFromInventory(MouseEvent mouseEvent) {
        JFXButton button = (JFXButton) mouseEvent.getSource();
        int cropNum = getIdString(button.getId());
        boolean changed = plantView.plantCrop(plotsObservableList, plantingPlotNum,
                playerViewModel.getPlayer().getUserStorage().getInventory().get(cropNum));
        if (changed) {
            Pane pane = (Pane) gridPane.getChildren().get(plantingPlotNum);
            pane.getChildren().get(0).setOnMouseClicked(this::harvestCrop);
            pane.getChildren().set(5, plotsObservableList.get(plantingPlotNum).getWaterValueText());
            toggleInventoryScreenVisibility();
        }
    }

    public void harvestCrop(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        int plotNum = getIdString(imageView.getId());
        if (playerViewModel.getPlayer().getCurrentHarvestCounter()
                < playerViewModel.getPlayer().getMaxHarvestsPerDay()) {
            PlotModel harvestedPlot = plotsObservableList.get(plotNum).getPlotModel();
            if (harvestedPlot.getDaysOld() >= 10
                    || harvestedPlot.getWaterValue() > 6 || harvestedPlot.getWaterValue() <= 0) {
                harvestView.harvestCrop(plotsObservableList, plotNum);
                Pane pane = (Pane) gridPane.getChildren().get(plotNum);
                ImageView plotImageView = (ImageView) pane.getChildren().get(0);
                plotImageView.setImage(plotsObservableList.get(plotNum).getPlotImage());
                plotImageView.setOnMouseClicked(this::plantCrop);
                ImageView plotNameImageView = (ImageView) pane.getChildren().get(1);
                plotNameImageView.setImage(plotsObservableList.get(plotNum).getNameImage());
                pane.getChildren().set(5, plotsObservableList.get(plotNum).getWaterValueText());
                txtDailyHarvestCount.setText(
                        String.valueOf(playerViewModel.getPlayer().getMaxHarvestsPerDay()
                                        - playerViewModel.getPlayer().getCurrentHarvestCounter()));
            }
        } else {
            displayMaxHarvestPopUp();
        }
    }

    public void waterCrop(MouseEvent mouseEvent) {
        if (playerViewModel.getPlayer().getCurrentWaterCounter()
                < playerViewModel.getPlayer().getMaxWateringPerDay()) {
            ImageView imageView = (ImageView) mouseEvent.getSource();
            int plotNum = getIdString(imageView.getId());
            boolean changed = waterView.waterCrop(plotsObservableList, plotNum);
            if (changed) {
                Pane pane = (Pane) gridPane.getChildren().get(plotNum);
                pane.getChildren().set(5, plotsObservableList.get(plotNum).getWaterValueText());
                txtDailyWaterCount.setText(
                        String.valueOf(playerViewModel.getPlayer().getMaxWateringPerDay()
                                - playerViewModel.getPlayer().getCurrentWaterCounter()));
            }
        } else {
            displayMaxWaterPopUp();
        }
    }

    public void fertilizePlot(MouseEvent mouseEvent) {
        if ((playerViewModel.getPlayer().getUserStorage().getTotalFertilizer() - 1) > 0) {
            ImageView imageView = (ImageView) mouseEvent.getSource();
            int plotNum = getIdString(imageView.getId());
            boolean changed = fertilizeView.fertilizePlot(plotsObservableList, plotNum);
            if (changed) {
                txtFertilizerCount.setText(doubleDigitString(
                        playerViewModel.getPlayer().getUserStorage().getTotalFertilizer() - 1));
                Pane pane = (Pane) gridPane.getChildren().get(plotNum);
                pane.getChildren().set(6,
                        plotsObservableList.get(plotNum).getFertilizerValueText());
            }
        }
    }

    public void pesticidePlot(MouseEvent mouseEvent) {
        if ((playerViewModel.getPlayer().getUserStorage().getTotalPesticide() - 1) > 0) {
            ImageView imageView = (ImageView) mouseEvent.getSource();
            int plotNum = getIdString(imageView.getId());
            boolean changed = pesticideView.pesticidePlot(plotsObservableList, plotNum);
            if (changed) {
                txtPesticideCount.setText(doubleDigitString(
                        playerViewModel.getPlayer().getUserStorage().getTotalPesticide() - 1));
                Pane pane = (Pane) gridPane.getChildren().get(plotNum);
                pane.getChildren().set(1, plotsObservableList.get(plotNum).getNameImageView());
            }
        }
    }

    private void eventRoll() {
        String event = eventView.eventRoll(plotsObservableList);
        if (event.equals("Rain") || event.equals("Drought")) {
            for (int i = 0; i < plotsObservableList.size(); i++) {
                Pane pane = (Pane) gridPane.getChildren().get(i);
                ImageView plotImageView = (ImageView) pane.getChildren().get(0);
                plotImageView.setImage(plotsObservableList.get(i).getPlotImage());
                pane.getChildren().set(5, plotsObservableList.get(i).getWaterValueText());
            }
        } else if (event.equals("Locusts")) {
            for (int i = 0; i < plotsObservableList.size(); i++) {
                if (plotsObservableList.get(i).getPlotModel().getCropInPlot() == null) {
                    Pane pane = (Pane) gridPane.getChildren().get(i);
                    ImageView plotImageView = (ImageView) pane.getChildren().get(0);
                    plotImageView.setImage(plotsObservableList.get(i).getPlotImage());
                    plotImageView.setOnMouseClicked(this::plantCrop);
                    ImageView nameImageView = (ImageView) pane.getChildren().get(1);
                    nameImageView.setImage(plotsObservableList.get(i).getNameImage());
                    pane.getChildren().set(5, plotsObservableList.get(i).getWaterValueText());
                }
            }
        }
        textEvent.setText(event);
    }

    private Image chooseCropImage(CropModel crop) {
        if (crop == null) {
            return this.emptyNameImg;
        } else {
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

    private int getIdString(String iD) {
        String firstDigit = String.valueOf(iD.charAt(iD.length() - 2));
        String secondDigit = String.valueOf(iD.charAt(iD.length() - 1));
        String digits = firstDigit + secondDigit;
        return Integer.parseInt(digits);
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

    private void displayGameOverScreen() {
        gameOverScreen.setVisible(true);
    }

    public void returnHomeScreen(MouseEvent mouseEvent) {
        try {
            JFXButton button = (JFXButton) mouseEvent.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("../homeScreenView/HomeScreenView.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Home Screen");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkGameOver() {
        if (emptyPlotCounter == plotViewModel.getPlotsFromDatabase().size()) {
            if (playerViewModel.getPlayer().getUserStorage().getTotalCropAmount() <= 0) {
                double basePrice = storageViewModel.userInventory().get(2).getCropValue();
                String curDifficulty =
                        playerViewModel.getPlayer().getPlayerSettings().getStartingDifficulty();
                double calPrice = marketViewModel.calculateCropPrice(basePrice, curDifficulty);
                if (playerViewModel.getPlayer().getUserCurrentMoney() < calPrice) {
                    displayGameOverScreen();
                }
            }
        }
    }
}
