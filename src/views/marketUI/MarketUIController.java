package views.marketUI;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import viewmodels.MarketViewModel;
import viewmodels.PlayerViewModel;
import viewmodels.StorageViewModel;
import views.farmUI.FarmUIController;

import java.io.IOException;


public class MarketUIController {
    @FXML
    private JFXButton btnSeeds;
    @FXML
    private JFXButton btnAnimals;
    @FXML
    private JFXButton btnSwap;
    @FXML
    private JFXButton btnFarm;
    @FXML
    private Text txtBudget;


    @FXML
    private ImageView panel1Img;
    @FXML
    private JFXButton panel1Action;
    @FXML
    private Text panel1Value;
    @FXML
    private Text panel1Price;
    @FXML
    private Text panel1Quantity;

    @FXML
    private ImageView panel2Img;
    @FXML
    private JFXButton panel2Action;
    @FXML
    private Text panel2Value;
    @FXML
    private Text panel2Price;
    @FXML
    private Text panel2Quantity;

    @FXML
    private ImageView panel3Img;
    @FXML
    private JFXButton panel3Action;
    @FXML
    private Text panel3Value;
    @FXML
    private Text panel3Price;
    @FXML
    private Text panel3Quantity;


    @FXML
    private ImageView panel4Img;
    @FXML
    private JFXButton panel4Action;
    @FXML
    private Text panel4Value;
    @FXML
    private Text panel4Price;
    @FXML
    private Text panel4Quantity;

    @FXML
    private ImageView panel5Img;
    @FXML
    private JFXButton panel5Action;
    @FXML
    private Text panel5Value;
    @FXML
    private Text panel5Price;
    @FXML
    private Text panel5Quantity;

    @FXML
    private ImageView panel6Img;
    @FXML
    private JFXButton panel6Action;
    @FXML
    private Text panel6Value;
    @FXML
    private Text panel6Price;
    @FXML
    private Text panel6Quantity;


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
            setPrice(panel1Value, 0, panel1Price);
            panel1Quantity.setText(doubleDigitString(
                    storage.userInventory().get(0).getCropQuantity()));
        }
        if (storage.userInventory().get(1) != null) {
            setPrice(panel2Value, 1, panel2Price);
            panel2Quantity.setText(doubleDigitString(
                    storage.userInventory().get(1).getCropQuantity()));
        }
        if (storage.userInventory().get(2) != null) {
            setPrice(panel3Value, 2, panel3Price);
            panel3Quantity.setText(doubleDigitString(
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
        upQuantity(panel1Value, 0, panel1Price);
    }

    public void downQuantity1() {
        downQuantity(panel1Value, 0, panel1Price);
    }

    public void panel1BuySell() {
        if (buyState) {
            buyQuantity(panel1Value, 0, panel1Quantity);
        } else {
            sellQuantity(panel1Value, 0, panel1Quantity);
        }
    }

    public void upQuantity2() {
        upQuantity(panel2Value, 1, panel2Price);
    }

    public void downQuantity2() {
        downQuantity(panel2Value, 1, panel2Price);
    }

    public void panel2BuySell() {
        if (buyState) {
            buyQuantity(panel2Value, 1, panel2Quantity);
        } else {
            sellQuantity(panel2Value, 1, panel2Quantity);
        }
    }

    public void upQuantity3() {
        upQuantity(panel3Value, 2, panel3Price);
    }

    public void downQuantity3() {
        downQuantity(panel3Value, 2, panel3Price);
    }

    public void panel3BuySell() {
        if (buyState) {
            buyQuantity(panel3Value, 2, panel3Quantity);
        } else {
            sellQuantity(panel3Value, 2, panel3Quantity);
        }
    }

    public void upQuantity5() {
        // upQuantity(panel5Value, 4, panel5Price);
    }

    public void downQuantity5() {
        downQuantity(panel4Value, 4, panel4Price);
    }

    public void panel5BuySell() {
        if (buyState) {
            buyQuantity(panel4Value, 4, panel4Quantity);
        } else {
            sellQuantity(panel4Value, 4, panel4Quantity);
        }
    }

    public void upQuantity6() {
        upQuantity(panel5Value, 5, panel5Price);
    }

    public void downQuantity6() {
        downQuantity(panel5Value, 5, panel5Price);
    }

    public void panel6BuySell() {
        if (buyState) {
            buyQuantity(panel5Value, 5, panel5Quantity);
        } else {
            sellQuantity(panel5Value, 5, panel5Quantity);
        }
    }

    public void upQuantity7() {
        upQuantity(panel6Value, 6, panel6Price);
    }

    public void downQuantity7() {
        downQuantity(panel6Value, 6, panel6Price);
    }

    public void panel7BuySell() {
        if (buyState) {
            buyQuantity(panel6Value, 6, panel6Quantity);
        } else {
            sellQuantity(panel6Value, 6, panel6Quantity);
        }
    }


    private void setActionLabel(String str) {
        panel1Action.setText(str);
        panel2Action.setText(str);
        panel3Action.setText(str);
        panel4Action.setText(str);
        panel5Action.setText(str);
        panel6Action.setText(str);
    }

    private void resetValues() {
        panel1Value.setText("01");
        panel2Value.setText("01");
        panel3Value.setText("01");
        panel4Value.setText("01");
        panel5Value.setText("01");
        panel6Value.setText("01");
    }

    private void resetPrices() {
        setPrice(panel1Value, 0, panel1Price);
        setPrice(panel2Value, 1, panel2Price);
        setPrice(panel3Value, 2, panel3Price);
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
        btnAnimals.setVisible(false);
        btnSeeds.setVisible(false);
        setActionLabel("Sell");
        resetValues();
        btnSwap.setText("Buy Crops");
        resetPrices();
        btnSwap.setOnMouseClicked(this::sellSwap);
    }

    public void sellSwap(MouseEvent mouseEvent) {
        buyState = true;
        btnAnimals.setVisible(true);
        btnSeeds.setVisible(true);
        setActionLabel("Buy");
        resetValues();
        btnSwap.setText("Sell Crops");
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


