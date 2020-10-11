package views.marketPlace;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.CropModel;
import viewmodels.MarketViewModel;
import viewmodels.PlayerViewModel;
import viewmodels.StorageViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MarketPlace {

    private boolean buyState = true;
    private MarketViewModel marketViewModel;
    private StorageViewModel storageViewModel;

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
                         PlayerViewModel player, StorageViewModel storageViewModel) {
        marketViewModel = new MarketViewModel(player);
        budget.setText(String.valueOf(player.getPlayer().getUserCurrentMoney()));
        if (storageViewModel.userInventory().get(0) != null) {
            p1Quantity.setText(
                    String.valueOf(storageViewModel.userInventory().get(0).getCropQuantity()));
        }
        if (storageViewModel.userInventory().get(1) != null) {
            p2Quantity.setText(
                    String.valueOf(storageViewModel.userInventory().get(1).getCropQuantity()));
        }
        if (storageViewModel.userInventory().get(2) != null) {
            p3Quantity.setText(
                    String.valueOf(storageViewModel.userInventory().get(2).getCropQuantity()));
        }
        sellSwap(mouseEvent);
    }

    private void upValue(Text value, Text price, int crop) {
        int num = Integer.parseInt(value.getText());
        if (num < 15) {
            num++;
        }
        String str;
        if (num < 10) {
            str = "0" + num;
        } else {
            str = String.valueOf(num);
        }
        value.setText(str);
        setPrice(price, crop ,value);
    }

    private void downValue(Text value, Text price, int crop) {
        int num = Integer.parseInt(value.getText());
        if (num > 1) {
            num--;
        }
        String str;
        if (num < 10) {
            str = "0" + num;
        } else {
            str = String.valueOf(num);
        }
        value.setText(str);
        setPrice(price, crop ,value);
    }

    private void setPrice(Text price, int crop, Text value) {
        double basePrice = storageViewModel.userInventory().get(crop).getCropValue();
        price.setText(String.valueOf(basePrice * Integer.parseInt(value.getText())));
    }

    private void buyValue(Text value, int crop, Text quantity) {
        int num = Integer.parseInt(value.getText());
        double basePrice = storageViewModel.userInventory().get(crop).getCropValue();
        double budgetCheck = Integer.parseInt(this.budget.getText());
        if (num * basePrice  > budgetCheck && crop <= 2) {
            marketViewModel.purchaseItems(storageViewModel.userInventory().get(crop), num);
            quantity.setText(String.valueOf(num));
        }
    }

    private void sellValue(Text value) {

    }

    public void buySwap(MouseEvent mouseEvent) {
        buyState = false;
        btnAnimals.setVisible(false);
        btnSeeds.setVisible(false);
        setActionLabel("Sell");
        resetValues();
        btnSwap.setText("Buy Crops");
        btnSwap.setOnMouseClicked(this::sellSwap);
    }

    public void sellSwap(MouseEvent mouseEvent) {
        buyState = true;
        btnAnimals.setVisible(true);
        btnSeeds.setVisible(true);
        setActionLabel("Buy");
        resetValues();
        btnSwap.setText("Sell Crops");
        btnSwap.setOnMouseClicked(this::buySwap);
    }

    public void returnFarm() {

    }

    public void upValue1() {
        upValue(p1Value, p1Price, 0);
    }

    public void downValue1() {
        downValue(p1Value, p1Price, 0);
    }

    public void action1() {
        if(buyState) {
            buyValue(p1Value, 0, p1Quantity);
        } else {
            sellValue(p1Value);
        }
    }

    public void upValue2() {
        upValue(p2Value, p2Price, 1);
    }

    public void downValue2() {
        downValue(p2Value, p2Price, 1);
    }

    public void action2() {
        if(buyState) {
            buyValue(p2Value, 1, p2Quantity);
        } else {
            sellValue(p2Value);
        }
    }

    public void upValue3() {
        upValue(p3Value, p3Price, 2);
    }

    public void downValue3() {
        downValue(p3Value, p3Price, 2);
    }

    public void action3() {
        if(buyState) {
            buyValue(p3Value, 2, p3Quantity);
        } else {
            sellValue(p3Value);
        }
    }

    public void upValue4() {
        upValue(p4Value, p4Price, 3);
    }

    public void downValue4() {
        downValue(p4Value, p4Price, 3);
    }

    public void action4() {
        if(buyState) {
            buyValue(p4Value, 3, p4Quantity);
        } else {
            sellValue(p4Value);
        }
    }

    public void upValue5() {
        upValue(p5Value, p5Price, 4);
    }

    public void downValue5() {
        downValue(p5Value, p5Price, 4);
    }

    public void action5() {
        if(buyState) {
            buyValue(p5Value, 4, p5Quantity);
        } else {
            sellValue(p5Value);
        }
    }

    public void upValue6() {
        upValue(p6Value, p6Price, 5);
    }

    public void downValue6() {
        downValue(p6Value, p6Price, 5);
    }

    public void action6() {
        if(buyState) {
            buyValue(p6Value, 5, p6Quantity);
        } else {
            sellValue(p6Value);
        }
    }

    public void upValue7() {
        upValue(p7Value, p7Price, 6);
    }

    public void downValue7() { downValue(p7Value, p7Price, 6); }

    public void action7() {
        if(buyState) {
            buyValue(p7Value, 6, p7Quantity);
        } else {
            sellValue(p7Value);
        }
    }

    public void upValue8() {
        upValue(p8Value, p8Price, 7);
    }

    public void downValue8() { downValue(p8Value, p8Price, 7); }

    public void action8() {
        if(buyState) {
            buyValue(p8Value, 7, p8Quantity);
        } else {
            sellValue(p8Value);
        }
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
}


