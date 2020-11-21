package views.marketView;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.WorkerModel;
import viewmodels.MarketViewModel;
import viewmodels.PlayerViewModel;
import viewmodels.StorageViewModel;
import viewmodels.WorkerViewModel;
import views.farmView.FarmViewController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class MarketViewController {
    @FXML
    private JFXButton btnSwap;
    @FXML
    private JFXButton btnFarm;
    @FXML
    private Text txtBudget;


    @FXML
    private JFXButton pane1Action;
    @FXML
    private Text pane1Value;
    @FXML
    private Text pane1Price;
    @FXML
    private Text pane1Quantity;

    @FXML
    private JFXButton pane2Action;
    @FXML
    private Text pane2Value;
    @FXML
    private Text pane2Price;
    @FXML
    private Text pane2Quantity;

    @FXML
    private JFXButton pane3Action;
    @FXML
    private Text pane3Value;
    @FXML
    private Text pane3Price;
    @FXML
    private Text pane3Quantity;


    @FXML
    private ImageView pane4Img;
    @FXML
    private JFXButton pane4Action;
    @FXML
    private Text pane4Value;
    @FXML
    private Text pane4Price;
    @FXML
    private Text pane4Quantity;

    @FXML
    private ImageView pane5Img;
    @FXML
    private JFXButton pane5Action;
    @FXML
    private Text pane5Value;
    @FXML
    private Text pane5Price;
    @FXML
    private Text pane5Quantity;

    @FXML
    private ImageView pane6Img;
    @FXML
    private Pane tierPane;
    @FXML
    private Text tierNum;
    @FXML
    private Pane pane6QuantityPane;
    @FXML
    private HBox pane6HBox;
    @FXML
    private JFXButton pane6Action;
    @FXML
    private JFXButton fireButton;
    @FXML
    private JFXButton upgradeButton;
    @FXML
    private Text pane6Value;
    @FXML
    private Text pane6Price;
    @FXML
    private Text pane6Quantity;

    private final Image fertilizerImg = new
            Image("@../../dependencies/images/Fertilizer_Market.png",
            198.0, 198.0, true, false);
    private final Image pesticideImg = new
            Image("@../../dependencies/images/Pesticide_Market.png",
            198.0, 198.0, true, false);
    private final Image cornPesticideImg = new
            Image("@../../dependencies/images/market_corn_pesticide.png",
            198.0, 198.0, true, false);
    private final Image potatoPesticideImg = new
            Image("@../../dependencies/images/market_potato_pesticide.png",
            198.0, 198.0, true, false);

    private boolean buyState = true;
    private MarketViewModel marketViewModel;
    private StorageViewModel storageViewModel;
    private PlayerViewModel playerViewModel;
    private WorkerViewModel workerViewModel;
    private WorkerModel workerModel = new WorkerModel();
    private ArrayList<Text> listPaneValues;
    private ArrayList<Text> listPanePrices;
    private ArrayList<Text> listPaneQuantities;

    public void initData(MouseEvent mouseEvent, PlayerViewModel player, StorageViewModel storage) {
        this.marketViewModel = new MarketViewModel(player);
        this.storageViewModel = storage;
        this.playerViewModel = player;
        this.workerViewModel = new WorkerViewModel(player.getPlayer());
        this.txtBudget.setText("$" + (player.getPlayer().getUserCurrentMoney()));
        listPaneValues = new ArrayList<>(Arrays.asList(pane1Value, pane2Value, pane3Value,
                pane4Value, pane5Value, pane6Value));
        listPanePrices = new ArrayList<>(Arrays.asList(pane1Price, pane2Price, pane3Price,
                pane4Price, pane5Price, pane6Price));
        listPaneQuantities = new ArrayList<>(
                Arrays.asList(pane1Quantity, pane2Quantity, pane3Quantity,
                pane4Quantity, pane5Quantity, pane6Quantity));
        for (int i = 0; i < 6; i++) {
            if (storage.userInventory().get(i) != null) {
                setPrice(listPaneValues.get(i), i, listPanePrices.get(i));
                if (i < 3) {
                    listPaneQuantities.get(i).setText(doubleDigitString(
                            storage.userInventory().get(i).getCropQuantity()));
                } else if (i == 4) {
                    listPaneQuantities.get(i).setText(doubleDigitString(
                            player.getPlayer().getUserStorage().getTotalFertilizer() - 1));
                } else if (i == 5) {
                    listPaneQuantities.get(i).setText(doubleDigitString(
                            player.getPlayer().getUserStorage().getTotalPesticide() - 1));
                }
            }
        }
        pane6Action.setVisible(false);
        sellSwap(mouseEvent);
    }

    private void upQuantity(Text quantity, int crop, Text price) {
        int num = Integer.parseInt(quantity.getText());
        if (num < 15) {
            num++;
        }
        String str = doubleDigitString(num);
        quantity.setText(str);
        setPrice(quantity, crop, price);
    }

    private void downQuantity(Text quantity, int crop, Text price) {
        int num = Integer.parseInt(quantity.getText());
        if (num > 1) {
            num--;
        }
        String str = doubleDigitString(num);
        quantity.setText(str);
        setPrice(quantity, crop, price);
    }

    private void setPrice(Text quantity, int index, Text price) {
        if (index < 3) {
            double basePrice = storageViewModel.userInventory().get(index).getCropValue();
            String curDifficulty =
                    playerViewModel.getPlayer().getPlayerSettings().getStartingDifficulty();
            double calPrice = marketViewModel.calculateCropPrice(basePrice, curDifficulty);
            price.setText("$" + (calPrice * Integer.parseInt(quantity.getText())));
        } else if (!buyState && index < 6) {
            double basePrice = storageViewModel.userInventory().get(index).getCropValue();
            String curDifficulty =
                    playerViewModel.getPlayer().getPlayerSettings().getStartingDifficulty();
            double calPrice = marketViewModel.calculateCropPrice(basePrice, curDifficulty);
            price.setText("$" + (calPrice * Integer.parseInt(quantity.getText())));
        } else if (buyState && index < 5) {
            double basePrice = 100;
            String curDifficulty =
                    playerViewModel.getPlayer().getPlayerSettings().getStartingDifficulty();
            double calPrice = marketViewModel.calculateCropPrice(basePrice, curDifficulty);
            price.setText("$" + (calPrice * Integer.parseInt(quantity.getText())));
        } else {
            double basePrice = workerViewModel.upgradePrice(workerModel);
            String curDifficulty =
                    playerViewModel.getPlayer().getPlayerSettings().getStartingDifficulty();
            double calPrice = marketViewModel.calculateCropPrice(basePrice, curDifficulty);
            price.setText("$" + (calPrice));
        }
    }

    private void buyQuantity(Text value, int index, Text quantity) {
        int valueNum = Integer.parseInt(value.getText());
        if (index < 3) {
            marketViewModel.purchaseCrops(
                    storageViewModel.userInventory().get(index), valueNum);
            quantity.setText(doubleDigitString(
                    storageViewModel.userInventory().get(index).getCropQuantity()));
            this.txtBudget.setText("$" + (playerViewModel.getPlayer().getUserCurrentMoney()));
        } else if (index == 3) {
            this.marketViewModel.purchaseItems("Fertilizer", valueNum);
            quantity.setText(doubleDigitString(
                    playerViewModel.getPlayer().getUserStorage().getTotalFertilizer() - 1));
            this.txtBudget.setText("$" + (playerViewModel.getPlayer().getUserCurrentMoney()));
        } else if (index == 4) {
            this.marketViewModel.purchaseItems("Pesticide", valueNum);
            quantity.setText(doubleDigitString(
                    playerViewModel.getPlayer().getUserStorage().getTotalPesticide() - 1));
            this.txtBudget.setText("$" + (playerViewModel.getPlayer().getUserCurrentMoney()));
        }
    }

    private void sellQuantity(Text value, int index, Text quantity) {
        if (index < 6) {
            if (Integer.parseInt(quantity.getText()) > 2) {
                int num = Integer.parseInt(value.getText());
                storageViewModel.sellItemFromInventory(
                        storageViewModel.userInventory().get(index), num);
                quantity.setText(doubleDigitString(
                        storageViewModel.userInventory().get(index).getCropQuantity()));
                this.txtBudget.setText("$" + (playerViewModel.getPlayer().getUserCurrentMoney()));
            }
        }
    }

    public void upgradeWorker() {
        if (workerViewModel.checkPurchasableUpgrade(workerViewModel.upgradePrice(workerModel))) {
            tierNum.setText(String.valueOf(workerModel.getWorkerType()));
            setPrice(pane6Value, 5, pane6Price);
            workerViewModel.updateWorkerTypeDatabase(workerModel);
            workerViewModel.updateWorkerWageDatabase(workerModel);
        }
    }

    public void fireWorker() {
        workerViewModel.workerLeaves(workerModel);
        tierNum.setText(String.valueOf(workerModel.getWorkerType()));
        setPrice(pane6Value, 5, pane6Price);
        workerViewModel.updateWorkerTypeDatabase(workerModel);
        workerViewModel.updateWorkerWageDatabase(workerModel);
    }

    public void upQuantity1() {
        upQuantity(pane1Value, 0, pane1Price);
    }

    public void downQuantity1() {
        downQuantity(pane1Value, 0, pane1Price);
    }

    public void panel1BuySell() {
        if (buyState) {
            buyQuantity(pane1Value, 0, pane1Quantity);
        } else {
            sellQuantity(pane1Value, 0, pane1Quantity);
        }
    }

    public void upQuantity2() {
        upQuantity(pane2Value, 1, pane2Price);
    }

    public void downQuantity2() {
        downQuantity(pane2Value, 1, pane2Price);
    }

    public void panel2BuySell() {
        if (buyState) {
            buyQuantity(pane2Value, 1, pane2Quantity);
        } else {
            sellQuantity(pane2Value, 1, pane2Quantity);
        }
    }

    public void upQuantity3() {
        upQuantity(pane3Value, 2, pane3Price);
    }

    public void downQuantity3() {
        downQuantity(pane3Value, 2, pane3Price);
    }

    public void panel3BuySell() {
        if (buyState) {
            buyQuantity(pane3Value, 2, pane3Quantity);
        } else {
            sellQuantity(pane3Value, 2, pane3Quantity);
        }
    }

    public void upQuantity4() {
        upQuantity(pane4Value, 3, pane4Price);
    }

    public void downQuantity4() {
        downQuantity(pane4Value, 3, pane4Price);
    }

    public void panel4BuySell() {
        if (buyState) {
            buyQuantity(pane4Value, 3, pane4Quantity);
        } else {
            sellQuantity(pane4Value, 3, pane4Quantity);
        }
    }

    public void upQuantity5() {
        upQuantity(pane5Value, 4, pane5Price);
    }

    public void downQuantity5() {
        downQuantity(pane5Value, 4, pane5Price);
    }

    public void panel5BuySell() {
        if (buyState) {
            buyQuantity(pane5Value, 4, pane5Quantity);
        } else {
            sellQuantity(pane5Value, 4, pane5Quantity);
        }
    }

    public void upQuantity6() {
        upQuantity(pane6Value, 5, pane6Price);
    }

    public void downQuantity6() {
        downQuantity(pane6Value, 5, pane6Price);
    }

    public void panel6BuySell() {
        if (buyState) {
            buyQuantity(pane6Value, 5, pane6Quantity);
        } else {
            sellQuantity(pane6Value, 5, pane6Quantity);
        }
    }

    private void setActionLabel(String str) {
        pane1Action.setText(str);
        pane2Action.setText(str);
        pane3Action.setText(str);
        pane4Action.setText(str);
        pane5Action.setText(str);
        pane6Action.setText(str);
    }

    private void resetValues() {
        pane1Value.setText("01");
        pane2Value.setText("01");
        pane3Value.setText("01");
        pane4Value.setText("01");
        pane5Value.setText("01");
        pane6Value.setText("01");
    }

    private void resetPrices() {
        for (int i = 0; i < 6; i++) {
            setPrice(listPaneValues.get(i), i, listPanePrices.get(i));
        }
    }

    private void resetQuantities() {
        for (int i = 0; i < 6; i++) {
            if (i < 3) {
                listPaneQuantities.get(i).setText(doubleDigitString(
                        storageViewModel.userInventory().get(i).getCropQuantity()));
            } else if (!buyState) {
                listPaneQuantities.get(i).setText(doubleDigitString(
                        storageViewModel.userInventory().get(i).getCropQuantity()));
            } else if (i == 3) {
                listPaneQuantities.get(i).setText(doubleDigitString(
                        playerViewModel.getPlayer().getUserStorage().getTotalFertilizer() - 1));
            } else if (i == 4) {
                listPaneQuantities.get(i).setText(doubleDigitString(
                        playerViewModel.getPlayer().getUserStorage().getTotalPesticide() - 1));
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

    public void buySwap(MouseEvent mouseEvent) {
        buyState = false;
        setActionLabel("Sell");
        resetValues();
        resetPrices();
        resetQuantities();
        btnSwap.setText("Buy Crops");
        pane4Img.setImage(cornPesticideImg);
        pane5Img.setImage(potatoPesticideImg);
        pane6QuantityPane.setVisible(true);
        pane6Action.setVisible(true);
        pane6HBox.setVisible(true);
        tierPane.setVisible(false);
        fireButton.setVisible(false);
        upgradeButton.setVisible(false);
        btnSwap.setOnMouseClicked(this::sellSwap);
    }

    public void sellSwap(MouseEvent mouseEvent) {
        buyState = true;
        setActionLabel("Buy");
        resetValues();
        resetPrices();
        resetQuantities();
        btnSwap.setText("Sell Crops");
        pane4Img.setImage(fertilizerImg);
        pane5Img.setImage(pesticideImg);
        pane6QuantityPane.setVisible(false);
        pane6Action.setVisible(false);
        pane6HBox.setVisible(false);
        tierPane.setVisible(true);
        fireButton.setVisible(true);
        upgradeButton.setVisible(true);
        btnSwap.setOnMouseClicked(this::buySwap);
    }

    public void returnFarm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../farmView/FarmView.fxml"));
            Stage stage = (Stage) btnFarm.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            FarmViewController farmViewController = loader.getController();
            farmViewController.initSaveData(this.playerViewModel);
            stage.setTitle("Farm");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


