package views.marketUI;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import viewmodels.MarketViewModel;
import viewmodels.PlayerViewModel;
import viewmodels.StorageViewModel;
import views.farmUI.FarmUIController;

import java.io.IOException;


public class MarketUIController {
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
    private Pane pane6;
    @FXML
    private Pane tierPane;
    @FXML
    private Pane pane6QuantityPane;
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

    public void initData(MouseEvent mouseEvent, PlayerViewModel player, StorageViewModel storage) {
        this.marketViewModel = new MarketViewModel(player);
        this.storageViewModel = storage;
        this.playerViewModel = player;
        this.txtBudget.setText("$" + (player.getPlayer().getUserCurrentMoney()));
        if (storage.userInventory().get(0) != null) {
            setPrice(pane1Value, 0, pane1Price);
            pane1Quantity.setText(doubleDigitString(
                    storage.userInventory().get(0).getCropQuantity()));
        }
        if (storage.userInventory().get(1) != null) {
            setPrice(pane2Value, 1, pane2Price);
            pane2Quantity.setText(doubleDigitString(
                    storage.userInventory().get(1).getCropQuantity()));
        }
        if (storage.userInventory().get(2) != null) {
            setPrice(pane3Value, 2, pane3Price);
            pane3Quantity.setText(doubleDigitString(
                    storage.userInventory().get(2).getCropQuantity()));
        }
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

    private void setPrice(Text quantity, int crop, Text price) {
        if (crop < 3) {
            double basePrice = storageViewModel.userInventory().get(crop).getCropValue();
            String curDifficulty =
                    playerViewModel.getPlayer().getPlayerSettings().getStartingDifficulty();
            double calPrice = marketViewModel.calculateCropPrice(basePrice, curDifficulty);
            price.setText("$" + (calPrice * Integer.parseInt(quantity.getText())));
        }
    }

    private void buyQuantity(Text quantity, int crop, Text iQuantity) {
        int num = Integer.parseInt(quantity.getText());
        if (crop < 3) {
            marketViewModel.purchaseItems(
                    storageViewModel.userInventory().get(crop), num);
            iQuantity.setText(doubleDigitString(
                    storageViewModel.userInventory().get(crop).getCropQuantity()));
            this.txtBudget.setText("$" + (playerViewModel.getPlayer().getUserCurrentMoney()));
        }
    }

    private void sellQuantity(Text quantity, int crop, Text iQuantity) {
        if (crop < 3) {
            int num = Integer.parseInt(quantity.getText());
            storageViewModel.sellItemFromInventory(
                    storageViewModel.userInventory().get(crop), num);
            iQuantity.setText(doubleDigitString(
                    storageViewModel.userInventory().get(crop).getCropQuantity()));
            this.txtBudget.setText("$" + (playerViewModel.getPlayer().getUserCurrentMoney()));
        }
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
        upQuantity(pane4Value, 3, pane4Quantity);
    }

    public void downQuantity4() {
        downQuantity(pane4Value, 3, pane4Price);
    }

    public void panel4BuySell() {
        if (buyState) {
            buyQuantity(pane4Value, 3, pane4Quantity);
            buyFertilizer(Integer.parseInt(pane4Quantity.getText()));
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
            buyPesticide(Integer.parseInt(pane5Quantity.getText()));
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

    /**
     * Updating the total fertilizer count after buying
     *
     * @param count new total fertilizer
     */
    private void buyFertilizer(int count) {
        this.playerViewModel.getPlayer().getUserStorage().updateTotalFertilizer(count);
    }

    /**
     * Updating the total pesticide count after buying
     *
     * @param count new total pesticide
     */
    private void buyPesticide(int count) {
        this.playerViewModel.getPlayer().getUserStorage().updateTotalPesticide(count);
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
        setPrice(pane1Value, 0, pane1Price);
        setPrice(pane2Value, 1, pane2Price);
        setPrice(pane3Value, 2, pane3Price);
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
        btnSwap.setText("Buy Crops");
        pane4Img.setImage(cornPesticideImg);
        pane5Img.setImage(potatoPesticideImg);
        pane6.setVisible(true);
        resetPrices();
        btnSwap.setOnMouseClicked(this::sellSwap);
    }

    public void sellSwap(MouseEvent mouseEvent) {
        buyState = true;
        setActionLabel("Buy");
        resetValues();
        btnSwap.setText("Sell Crops");
        pane4Img.setImage(fertilizerImg);
        pane5Img.setImage(pesticideImg);
        pane6.setVisible(false);
        resetPrices();
        btnSwap.setOnMouseClicked(this::buySwap);
    }

    public void returnFarm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../farmUI/FarmUI.fxml"));
            Stage stage = (Stage) btnFarm.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            FarmUIController farmUIController = loader.getController();
            farmUIController.initDataFromMarket(this.playerViewModel);
            stage.setTitle("Farm");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


