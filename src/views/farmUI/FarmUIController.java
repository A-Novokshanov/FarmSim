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
import java.util.Arrays;
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
    private Text numTomatoes;
    @FXML
    private Text numPotatoes;
    @FXML
    private Text numCorn;

    @FXML
    private ImageView plot1Img = new ImageView();
    @FXML
    private ImageView plot2Img = new ImageView();
    @FXML
    private ImageView plot3Img = new ImageView();
    @FXML
    private ImageView plot4Img = new ImageView();
    @FXML
    private ImageView plot5Img = new ImageView();
    @FXML
    private ImageView plot6Img = new ImageView();
    @FXML
    private ImageView plot7Img = new ImageView();
    @FXML
    private ImageView plot8Img = new ImageView();
    @FXML
    private ImageView plot9Img = new ImageView();
    @FXML
    private ImageView plot10Img = new ImageView();

    @FXML
    private ImageView plotName1Img = new ImageView();
    @FXML
    private ImageView plotName2Img = new ImageView();
    @FXML
    private ImageView plotName3Img = new ImageView();
    @FXML
    private ImageView plotName4Img = new ImageView();
    @FXML
    private ImageView plotName5Img = new ImageView();
    @FXML
    private ImageView plotName6Img = new ImageView();
    @FXML
    private ImageView plotName7Img = new ImageView();
    @FXML
    private ImageView plotName8Img = new ImageView();
    @FXML
    private ImageView plotName9Img = new ImageView();
    @FXML
    private ImageView plotName10Img = new ImageView();

    @FXML
    private JFXButton btnCornPlant;
    @FXML
    private JFXButton btnPotatoPlant;
    @FXML
    private JFXButton btnTomatoPlant;

    @FXML
    private Text txtWaterValue1 = new Text("03");
    @FXML
    private Text txtWaterValue2 = new Text("03");
    @FXML
    private Text txtWaterValue3 = new Text("03");
    @FXML
    private Text txtWaterValue4 = new Text("03");
    @FXML
    private Text txtWaterValue5 = new Text("03");
    @FXML
    private Text txtWaterValue6 = new Text("03");
    @FXML
    private Text txtWaterValue7 = new Text("03");
    @FXML
    private Text txtWaterValue8 = new Text("03");
    @FXML
    private Text txtWaterValue9 = new Text("03");
    @FXML
    private Text txtWaterValue10 = new Text("03");

    private ArrayList<PlotModel> listPlots = new ArrayList<>();
    private ArrayList<ImageView> listPlotImages = new ArrayList<>(
            Arrays.asList(plot1Img, plot2Img, plot3Img, plot4Img, plot5Img,
                    plot6Img, plot7Img, plot8Img, plot9Img, plot10Img));
    private ArrayList<ImageView> listPlotNameImages = new ArrayList<>(
            Arrays.asList(plotName1Img, plotName2Img, plotName3Img, plotName4Img,
                    plotName5Img, plotName6Img, plotName7Img, plotName8Img,
                    plotName9Img, plotName10Img));
    private ArrayList<Text> listPlotWaterValues = new ArrayList<>(
            Arrays.asList(txtWaterValue1, txtWaterValue2, txtWaterValue3,
                    txtWaterValue4, txtWaterValue5, txtWaterValue6, txtWaterValue7,
                    txtWaterValue8, txtWaterValue9, txtWaterValue10));

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
    private String name;
    private int plantingPlotNum;

    /**
     * Initializes data from the Initial Configuration screen.
     *
     * @param playerViewModel Setting View Model to access player details.
     * @param playerName      The name of the current player.
     */
    public void initConfigData(PlayerViewModel playerViewModel, String playerName) {
        this.money.setText("$ " + playerViewModel.getPlayer().getUserCurrentMoney());
        this.playerViewModel = playerViewModel;
        this.storageViewModel = new StorageViewModel(playerViewModel);
        this.plotViewModel = new PlotViewModel(playerViewModel.getPlayer());
        this.name = playerName;
        this.dayNum.setText("Day " + doubleDigitString(this.playerViewModel.getPlayer().getDays()));
        setUpPlotModels(
                playerViewModel.getPlayer().getPlayerSettings().getStartingCropType());
    }

    //    /**
    //     *
    //     * @param playerViewModel
    //     * @param plotModelImgs
    //     * @param name
    //     */
    //    public void initDataFromMarket(PlayerViewModel playerViewModel, String name) {
    //        this.money.setText("$ " + playerViewModel.getPlayer().getUserCurrentMoney());
    //        this.storageViewModel = new StorageViewModel(playerViewModel);
    //        this.playerViewModel = playerViewModel;
    //        this.plotViewModel = new PlotViewModel(playerViewModel.getPlayer());
    //        this.name = name;
    //        this.dayNum.setText("Day " + doubleDigitString(
    //        this.playerViewModel.getPlayer().getDays()));
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

        MarketPlace marketPlace = loader.getController();
        marketPlace.initData(mouseEvent, playerViewModel, storageViewModel, name);

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
        for (int i = 0; i < 10; i++) {
            listPlots.add(plotViewModel.populatePlot(cropModel));
            setUpPlotName(i, cropModel.getCropName());
        }
        checkAllMaturity();
        plotViewModel.addPlayerPlotsToDatabase(listPlots,
                playerViewModel.getPlayer().getPlayerSettings().getPlayerName());
    }

    /**
     * @param plotModels    Placeholder
     * @param plotModelNameImages Placeholder
     */
    public void setUpPlotModels(List<PlotModel> plotModels, ArrayList<Image> plotModelNameImages) {
        for (int i = 0; i < 10; i++) {
            listPlots.set(i, plotModels.get(i));
            listPlotNameImages.get(i).setImage(plotModelNameImages.get(i));
        }
        checkAllMaturity();
    }

    /**
     *
     */
    private void incrementAllPlotDays() {
        for (int i = 0; i < 10; i++) {
            plotViewModel.incrementPlotDaysOld(listPlots.get(i));
            plotViewModel.updatePlotMaturity(listPlots.get(i).getPlotIdentifier(),
                    playerViewModel.getPlayer().getPlayerSettings().getPlayerName());
        }
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
        for (int i = 0; i < 10; i++) {
            updateWaterValue(listPlots.get(i), listPlotWaterValues.get(i));
        }
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
        for (int i = 0; i < 10; i++) {
            checkMaturity(listPlots.get(i), listPlotImages.get(i), listPlotWaterValues.get(i));
        }
    }

    /**
     * Placeholder.
     * @param plotNum Placeholder
     */
    public void plantingInventory(int plotNum) {
        toggleInventoryScreenVisibility();
        turnOnPlantBtnVisibility();
        this.plantingPlotNum = plotNum;
    }

    public void plantCrop(int cropNum, CropModel crop) {
        if (storageViewModel.userInventory().get(cropNum).getCropQuantity() > 0) {
            int i = plantingPlotNum;
            storageViewModel.userInventory().get(cropNum).setCropQuantity(
                    storageViewModel.userInventory().get(cropNum).getCropQuantity() - 1);
            toggleInventoryScreenVisibility();
            listPlots.get(i).setWaterValue(3);
            listPlots.get(i).setDaysOld(0);
            this.plotViewModel.plantPlot(listPlots.get(i), crop);
            listPlotImages.get(i).setImage(seedImg);
            listPlotNameImages.get(i).setImage(chooseCropImage(crop));
            updateWaterValue(listPlots.get(i), listPlotWaterValues.get(i));
            listPlotWaterValues.get(i).setVisible(true);
            switchPlantHarvest(listPlotImages.get(i), i, true);
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
     *
     * @param harvestedPlotNum     Placeholder
     */
    public void harvestCrop(int harvestedPlotNum) {
        PlotModel harvestedPlot = listPlots.get(harvestedPlotNum);
        ImageView harvestedPlotImage = listPlotImages.get(harvestedPlotNum);
        ImageView harvestedPlotNameImage = listPlotNameImages.get(harvestedPlotNum);
        Text waterValue = listPlotWaterValues.get(harvestedPlotNum);
        if (harvestedPlot.getDaysOld() >= 10
                || harvestedPlot.getWaterValue() > 6 || harvestedPlot.getWaterValue() <= 0) {
            this.plotViewModel.harvestPlot(harvestedPlot, this.playerViewModel);
            harvestedPlotImage.setImage(dirtImg);
            harvestedPlotNameImage.setImage(emptyNameImg);
            //this.plotViewModel.zeroWaterValue(harvestedPlot.getWaterValue(),
            //harvestedPlot.getPlotIdentifier());
            //harvestedPlot.setWaterValue(0);
            switchPlantHarvest(harvestedPlotImage, harvestedPlotNum, false);
            waterValue.setVisible(false);
        }
    }

    /**
     * @param plotNum    Placeholder
     */
    public void waterCrop(int plotNum) {
        if (listPlots.get(plotNum) != null) {
            if (listPlots.get(plotNum).getWaterValue() > 0 && listPlots.get(plotNum).getWaterValue() <= 6) {
                this.plotViewModel.waterPlot(listPlots.get(plotNum));
                listPlotWaterValues.get(plotNum).setText(
                        doubleDigitString(listPlots.get(plotNum).getWaterValue()));
                checkMaturity(listPlots.get(plotNum), listPlotImages.get(plotNum),
                        listPlotWaterValues.get(plotNum));
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

    /**
     * Placeholder
     *
     * @param plotNum Placeholder
     * @param str      Placeholder
     */
    public void setUpPlotName(int plotNum, String str) {
        System.out.println(listPlotNameImages.toString());
        if (str.equals("Corn")) {
            listPlotNameImages.get(plotNum).setImage(cornNameImg);
        } else if (str.equals("Potato")) {
            listPlotNameImages.get(plotNum).setImage(potatoNameImg);
        } else {
            listPlotNameImages.get(plotNum).setImage(tomatoNameImg);
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
