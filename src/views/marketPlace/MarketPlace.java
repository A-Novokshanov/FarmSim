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
    private Text budget;


    @FXML
    private ImageView p1Img;
    @FXML
    private JFXButton p1Action;
    @FXML
    private Text p1Value;
    @FXML
    private Text p1Price;
    @FXML
    private Text p1Quantity;

    @FXML
    private ImageView p2Img;
    @FXML
    private JFXButton p2Action;
    @FXML
    private Text p2Value;
    @FXML
    private Text p2Price;
    @FXML
    private Text p2Quantity;

    @FXML
    private ImageView p3Img;
    @FXML
    private JFXButton p3Action;
    @FXML
    private Text p3Value;
    @FXML
    private Text p3Price;
    @FXML
    private Text p3Quantity;

    @FXML
    private ImageView p4Img;
    @FXML
    private JFXButton p4Action;
    @FXML
    private Text p4Value;
    @FXML
    private Text p4Price;
    @FXML
    private Text p4Quantity;

    @FXML
    private ImageView p5Img;
    @FXML
    private JFXButton p5Action;
    @FXML
    private Text p5Value;
    @FXML
    private Text p5Price;
    @FXML
    private Text p5Quantity;

    @FXML
    private ImageView p6Img;
    @FXML
    private JFXButton p6Action;
    @FXML
    private Text p6Value;
    @FXML
    private Text p6Price;
    @FXML
    private Text p6Quantity;

    @FXML
    private ImageView p7Img;
    @FXML
    private JFXButton p7Action;
    @FXML
    private Text p7Value;
    @FXML
    private Text p7Price;
    @FXML
    private Text p7Quantity;

    @FXML
    private ImageView p8Img;
    @FXML
    private JFXButton p8Action;
    @FXML
    private Text p8Value;
    @FXML
    private Text p8Price;
    @FXML
    private Text p8Quantity;

    public void initData(MouseEvent mouseEvent,
                         PlayerViewModel player, StorageViewModel storageViewModel, String name) {
        this.marketViewModel = new MarketViewModel(player);
        this.storageViewModel = storageViewModel;
        this.playerViewModel = player;
        this.playerName = name;
        this.budget.setText("$" + (player.getPlayer().getUserCurrentMoney()));
        if (storageViewModel.userInventory().get(0) != null) {
            setPrice(p1Value, 0, p1Price);
            p1Quantity.setText(doubleDigitString(
                    storageViewModel.userInventory().get(0).getCropQuantity()));
        }
        if (storageViewModel.userInventory().get(1) != null) {
            setPrice(p2Value, 1, p2Price);
            p2Quantity.setText(doubleDigitString(
                    storageViewModel.userInventory().get(1).getCropQuantity()));
        }
        if (storageViewModel.userInventory().get(2) != null) {
            setPrice(p3Value, 2, p3Price);
            p3Quantity.setText(doubleDigitString(
                    storageViewModel.userInventory().get(2).getCropQuantity()));
        }
        sellSwap(mouseEvent);
    }

    private void upValue(Text value, int crop, Text price, Text quantity) {
        int num = Integer.parseInt(value.getText());
        if (num < 15) {
            num++;
        }
        String str = doubleDigitString(num);
        value.setText(str);
        setPrice(value, crop, price);
    }

    private void downValue(Text value, int crop, Text price, Text quantity) {
        int num = Integer.parseInt(value.getText());
        if (num > 1) {
            num--;
        }
        String str = doubleDigitString(num);
        value.setText(str);
        setPrice(value, crop, price);
    }

    private void setPrice(Text value, int crop, Text price) {
        double basePrice = storageViewModel.userInventory().get(crop).getCropValue();
        String curDifficulty =
                playerViewModel.getPlayer().getPlayerSettings().getStartingDifficulty();
        double calPrice = marketViewModel.calculateCropPrice(basePrice, curDifficulty);
        price.setText("$" + (calPrice * Integer.parseInt(value.getText())));
    }

    private void buyValue(Text value, int crop, Text quantity) {
        int num = Integer.parseInt(value.getText());
        int iNum = Integer.parseInt(quantity.getText());
        double basePrice = storageViewModel.userInventory().get(crop).getCropValue();
        double currentMoney = playerViewModel.getPlayer().getUserCurrentMoney();
        // if (crop <= 2) {
        if (num * basePrice <= currentMoney) {
            marketViewModel.purchaseItems(storageViewModel.userInventory().get(crop), num);
            quantity.setText(doubleDigitString(
                    storageViewModel.userInventory().get(crop).getCropQuantity()));
            this.budget.setText("$" + (playerViewModel.getPlayer().getUserCurrentMoney()));
        }
        // }
    }

    private void sellValue(Text value, int crop, Text quantity) {
        int num = Integer.parseInt(value.getText());
        int iNum = Integer.parseInt(quantity.getText());
        if (crop <= 2) {
            if (iNum > 0) {
                storageViewModel.sellItemFromInventory(
                        storageViewModel.userInventory().get(crop), num);
                quantity.setText(doubleDigitString(
                        storageViewModel.userInventory().get(crop).getCropQuantity()));
                this.budget.setText("$" + (playerViewModel.getPlayer().getUserCurrentMoney()));
            }
        }
    }

    public void upValue1() {
        upValue(p1Value, 0, p1Price, p1Quantity);
    }

    public void downValue1() {
        downValue(p1Value, 0, p1Price, p1Quantity);
    }

    public void action1() {
        if (buyState) {
            buyValue(p1Value, 0, p1Quantity);
        } else {
            sellValue(p1Value, 0, p1Quantity);
        }
    }

    public void upValue2() {
        upValue(p2Value, 1, p2Price, p2Quantity);
    }

    public void downValue2() {
        downValue(p2Value, 1, p2Price, p2Quantity);
    }

    public void action2() {
        if (buyState) {
            buyValue(p2Value, 1, p2Quantity);
        } else {
            sellValue(p2Value, 1, p2Quantity);
        }
    }

    public void upValue3() {
        upValue(p3Value, 2, p3Price, p3Quantity);
    }

    public void downValue3() {
        downValue(p3Value, 2, p3Price, p3Quantity);
    }

    public void action3() {
        if (buyState) {
            buyValue(p3Value, 2, p3Quantity);
        } else {
            sellValue(p3Value, 2, p3Quantity);
        }
    }

    public void upValue4() {
        upValue(p4Value, 3, p4Price, p4Quantity);
    }

    public void downValue4() {
        downValue(p4Value, 3, p4Price, p4Quantity);
    }

    public void action4() {
        if (buyState) {
            buyValue(p4Value, 3, p4Quantity);
        } else {
            sellValue(p4Value, 3, p4Quantity);
        }
    }

    public void upValue5() {
        upValue(p5Value, 4, p5Price, p5Quantity);
    }

    public void downValue5() {
        downValue(p5Value, 4, p5Price, p5Quantity);
    }

    public void action5() {
        if (buyState) {
            buyValue(p5Value, 4, p5Quantity);
        } else {
            sellValue(p5Value, 4, p5Quantity);
        }
    }

    public void upValue6() {
        upValue(p6Value, 5, p6Price, p6Quantity);
    }

    public void downValue6() {
        downValue(p6Value, 5, p6Price, p6Quantity);
    }

    public void action6() {
        if (buyState) {
            buyValue(p6Value, 5, p6Quantity);
        } else {
            sellValue(p6Value, 5, p6Quantity);
        }
    }

    public void upValue7() {
        upValue(p7Value, 6, p7Price, p7Quantity);
    }

    public void downValue7() {
        downValue(p7Value, 6, p7Price, p7Quantity);
    }

    public void action7() {
        if (buyState) {
            buyValue(p7Value, 6, p7Quantity);
        } else {
            sellValue(p7Value, 6, p7Quantity);
        }
    }

    public void upValue8() {
        upValue(p8Value, 7, p8Price, p8Quantity);
    }

    public void downValue8() {
        downValue(p8Value, 7, p8Price, p8Quantity);
    }

    public void action8() {
        if (buyState) {
            buyValue(p8Value, 7, p8Quantity);
        } else {
            sellValue(p8Value, 7, p8Quantity);
        }
    }

    private void setActionLabel(String str) {
        p1Action.setText(str);
        p2Action.setText(str);
        p3Action.setText(str);
        p4Action.setText(str);
        p5Action.setText(str);
        p6Action.setText(str);
        p7Action.setText(str);
        p8Action.setText(str);
    }

    private void resetValues() {
        p1Value.setText("01");
        p2Value.setText("01");
        p3Value.setText("01");
        p4Value.setText("01");
        p5Value.setText("01");
        p6Value.setText("01");
        p7Value.setText("01");
        p8Value.setText("01");
    }

    private void resetPrices() {
        setPrice(p1Value, 0, p1Price);
        setPrice(p2Value, 1, p2Price);
        setPrice(p3Value, 2, p3Price);
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


