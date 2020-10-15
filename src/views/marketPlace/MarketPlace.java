package views.marketPlace;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import viewmodels.MarketViewModel;
import viewmodels.PlayerViewModel;
import viewmodels.StorageViewModel;
import views.farmUI.FarmUIController;

import java.io.IOException;

public class MarketPlace {

    private boolean buyState = true;
    private MarketViewModel marketViewModel;
    private StorageViewModel storageViewModel;
    private PlayerViewModel playerViewModel;
    private String playerName;

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

    @FXML
    private ImageView panel7Img;
    @FXML
    private JFXButton panel7Action;
    @FXML
    private Text panel7Value;
    @FXML
    private Text panel7Price;
    @FXML
    private Text panel7Quantity;

    @FXML
    private ImageView panel8Img;
    @FXML
    private JFXButton panel8Action;
    @FXML
    private Text panel8Value;
    @FXML
    private Text panel8Price;
    @FXML
    private Text panel8Quantity;

    public void initData(MouseEvent mouseEvent,
                         PlayerViewModel player, StorageViewModel storage, String name) {
        this.marketViewModel = new MarketViewModel(player);
        this.storageViewModel = storage;
        this.playerViewModel = player;
        this.playerName = name;
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

    private void upValue(Text quantity, int crop, Text price) {
        int num = Integer.parseInt(quantity.getText());
        if (num < 15) {
            num++;
        }
        String str = doubleDigitString(num);
        quantity.setText(str);
        setPrice(quantity, crop, price);
    }

    private void downValue(Text quantity, int crop, Text price) {
        int num = Integer.parseInt(quantity.getText());
        if (num > 1) {
            num--;
        }
        String str = doubleDigitString(num);
        quantity.setText(str);
        setPrice(quantity, crop, price);
    }

    private void setPrice(Text quantity, int crop, Text price) {
        double basePrice = storageViewModel.userInventory().get(crop).getCropValue();
        String curDifficulty =
                playerViewModel.getPlayer().getPlayerSettings().getStartingDifficulty();
        double calPrice = marketViewModel.calculateCropPrice(basePrice, curDifficulty);
        price.setText("$" + (calPrice * Integer.parseInt(quantity.getText())));
    }

    private void buyValue(Text quantity, int crop, Text iQuantity) {
        int num = Integer.parseInt(quantity.getText());
        marketViewModel.purchaseItems(
                storageViewModel.userInventory().get(crop), num);
        iQuantity.setText(doubleDigitString(
                storageViewModel.userInventory().get(crop).getCropQuantity()));
        this.txtBudget.setText("$" + (playerViewModel.getPlayer().getUserCurrentMoney()));
    }

    private void sellValue(Text quantity, int crop, Text iQuantity) {
        int num = Integer.parseInt(quantity.getText());
        storageViewModel.sellItemFromInventory(
                storageViewModel.userInventory().get(crop), num);
        iQuantity.setText(doubleDigitString(
                storageViewModel.userInventory().get(crop).getCropQuantity()));
        this.txtBudget.setText("$" + (playerViewModel.getPlayer().getUserCurrentMoney()));
    }

    public void upQuantity1() {
        upValue(panel1Value, 0, panel1Price);
    }

    public void downQuantity1() {
        downValue(panel1Value, 0, panel1Price);
    }

    public void panel1BuySell() {
        if (buyState) {
            buyValue(panel1Value, 0, panel1Quantity);
        } else {
            sellValue(panel1Value, 0, panel1Quantity);
        }
    }

    public void upQuantity2() {
        upValue(panel2Value, 1, panel2Price);
    }

    public void downQuantity2() {
        downValue(panel2Value, 1, panel2Price);
    }

    public void panel2BuySell() {
        if (buyState) {
            buyValue(panel2Value, 1, panel2Quantity);
        } else {
            sellValue(panel2Value, 1, panel2Quantity);
        }
    }

    public void upQuantity3() {
        upValue(panel3Value, 2, panel3Price);
    }

    public void downQuantity3() {
        downValue(panel3Value, 2, panel3Price);
    }

    public void panel3BuySell() {
        if (buyState) {
            buyValue(panel3Value, 2, panel3Quantity);
        } else {
            sellValue(panel3Value, 2, panel3Quantity);
        }
    }

    public void upQuantity4() {
        upValue(panel4Value, 3, panel4Price);
    }

    public void downQuantity4() {
        downValue(panel4Value, 3, panel4Price);
    }

    public void panel4BuySell() {
        if (buyState) {
            buyValue(panel4Value, 3, panel4Quantity);
        } else {
            sellValue(panel4Value, 3, panel4Quantity);
        }
    }

    public void upQuantity5() {
        upValue(panel5Value, 4, panel5Price);
    }

    public void downQuantity5() {
        downValue(panel5Value, 4, panel5Price);
    }

    public void panel5BuySell() {
        if (buyState) {
            buyValue(panel5Value, 4, panel5Quantity);
        } else {
            sellValue(panel5Value, 4, panel5Quantity);
        }
    }

    public void upQuantity6() {
        upValue(panel6Value, 5, panel6Price);
    }

    public void downQuantity6() {
        downValue(panel6Value, 5, panel6Price);
    }

    public void panel6BuySell() {
        if (buyState) {
            buyValue(panel6Value, 5, panel6Quantity);
        } else {
            sellValue(panel6Value, 5, panel6Quantity);
        }
    }

    public void upQuantity7() {
        upValue(panel7Value, 6, panel7Price);
    }

    public void downQuantity7() {
        downValue(panel7Value, 6, panel7Price);
    }

    public void panel7BuySell() {
        if (buyState) {
            buyValue(panel7Value, 6, panel7Quantity);
        } else {
            sellValue(panel7Value, 6, panel7Quantity);
        }
    }

    public void upQuantity8() {
        upValue(panel8Value, 7, panel8Price);
    }

    public void downQuantity8() {
        downValue(panel8Value, 7, panel8Price);
    }

    public void panel8BuySell() {
        if (buyState) {
            buyValue(panel8Value, 7, panel8Quantity);
        } else {
            sellValue(panel8Value, 7, panel8Quantity);
        }
    }

    private void setActionLabel(String str) {
        panel1Action.setText(str);
        panel2Action.setText(str);
        panel3Action.setText(str);
        panel4Action.setText(str);
        panel5Action.setText(str);
        panel6Action.setText(str);
        panel7Action.setText(str);
        panel8Action.setText(str);
    }

    private void resetValues() {
        panel1Value.setText("01");
        panel2Value.setText("01");
        panel3Value.setText("01");
        panel4Value.setText("01");
        panel5Value.setText("01");
        panel6Value.setText("01");
        panel7Value.setText("01");
        panel8Value.setText("01");
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

    public void returnFarm(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../farmUI/FarmUI.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        currentStage.close();
        try {
            stage.setScene(
                    new Scene(loader.load())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        FarmUIController farmUIController = loader.getController();
        farmUIController.initData(this.playerViewModel, playerName);

        stage.setTitle("Farm");
        stage.show();
    }
}


