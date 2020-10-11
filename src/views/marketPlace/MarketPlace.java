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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MarketPlace {

    private boolean buyState = true;

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
    private ImageView p2Img;
    @FXML
    private JFXButton p2Action;
    @FXML
    private Text p2Value;
    @FXML
    private Text p2Price;

    @FXML
    private ImageView p3Img;
    @FXML
    private JFXButton p3Action;
    @FXML
    private Text p3Value;
    @FXML
    private Text p3Price;

    @FXML
    private ImageView p4Img;
    @FXML
    private JFXButton p4Action;
    @FXML
    private Text p4Value;
    @FXML
    private Text p4Price;

    @FXML
    private ImageView p5Img;
    @FXML
    private JFXButton p5Action;
    @FXML
    private Text p5Value;
    @FXML
    private Text p5Price;

    @FXML
    private ImageView p6Img;
    @FXML
    private JFXButton p6Action;
    @FXML
    private Text p6Value;
    @FXML
    private Text p6Price;

    @FXML
    private ImageView p7Img;
    @FXML
    private JFXButton p7Action;
    @FXML
    private Text p7Value;
    @FXML
    private Text p7Price;

    @FXML
    private ImageView p8Img;
    @FXML
    private JFXButton p8Action;
    @FXML
    private Text p8Value;
    @FXML
    private Text p8Price;

    public void InitData(MouseEvent mouseEvent) {
        sellSwap(mouseEvent);
    }

    private void upValue(Text value) {
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
    }

    private void downValue(Text value) {
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
    }

    private void buyValue(Text value) {
        int num = Integer.parseInt(value.getText());
        int budgetCheck = Integer.parseInt(this.budget.getText());
        if (num > budgetCheck) {

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
        upValue(p1Value);
    }

    public void downValue1() {
        downValue(p1Value);
    }

    public void action1() {
        if(buyState) {
            buyValue(p1Value);
        } else {
            sellValue(p1Value);
        }
    }

    public void upValue2() {
        upValue(p2Value);
    }

    public void downValue2() {
        downValue(p2Value);
    }

    public void action2() {
        if(buyState) {
            buyValue(p2Value);
        } else {
            sellValue(p2Value);
        }
    }

    public void upValue3() {
        upValue(p3Value);
    }

    public void downValue3() {
        downValue(p3Value);
    }

    public void action3() {
        if(buyState) {
            buyValue(p3Value);
        } else {
            sellValue(p3Value);
        }
    }

    public void upValue4() {
        upValue(p4Value);
    }

    public void downValue4() {
        downValue(p4Value);
    }

    public void action4() {
        if(buyState) {
            buyValue(p4Value);
        } else {
            sellValue(p4Value);
        }
    }

    public void upValue5() {
        upValue(p5Value);
    }

    public void downValue5() {
        downValue(p5Value);
    }

    public void action5() {
        if(buyState) {
            buyValue(p5Value);
        } else {
            sellValue(p5Value);
        }
    }

    public void upValue6() {
        upValue(p6Value);
    }

    public void downValue6() {
        downValue(p6Value);
    }

    public void action6() {
        if(buyState) {
            buyValue(p6Value);
        } else {
            sellValue(p6Value);
        }
    }

    public void upValue7() {
        upValue(p7Value);
    }

    public void downValue7() { downValue(p7Value); }

    public void action7() {
        if(buyState) {
            buyValue(p7Value);
        } else {
            sellValue(p7Value);
        }
    }

    public void upValue8() {
        upValue(p8Value);
    }

    public void downValue8() { downValue(p8Value); }

    public void action8() {
        if(buyState) {
            buyValue(p8Value);
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

    private void setPrices(String str) {
        p1Price.setText(str);
        p2Price.setText(str);
        p3Price.setText(str);
        p4Price.setText(str);
        p5Price.setText(str);
        p6Price.setText(str);
        p7Price.setText(str);
        p8Price.setText(str);
    }
}


