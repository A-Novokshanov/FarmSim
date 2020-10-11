package views.marketPlace;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class MarketPlace {

    @FXML
    private JFXButton btnSeeds;
    @FXML
    private JFXButton btnAnimals;
    @FXML
    private JFXButton buySellSwap;
    @FXML
    private JFXButton returnFarm;


    @FXML
    private ImageView panel1Img;
    @FXML
    private JFXButton panel1Plus;
    @FXML
    private JFXButton panel1Minus;
    @FXML
    private JFXButton panel1Action;
    @FXML
    private Text panel1Value;

    @FXML
    private ImageView panel2Img;
    @FXML
    private JFXButton panel2Plus;
    @FXML
    private JFXButton panel2Minus;
    @FXML
    private JFXButton panel2Action;
    @FXML
    private Text panel2Value;

    @FXML
    private ImageView panel3Img;
    @FXML
    private JFXButton panel3Plus;
    @FXML
    private JFXButton panel3Minus;
    @FXML
    private JFXButton panel3Action;
    @FXML
    private Text panel3Value;

    @FXML
    private ImageView panel4Img;
    @FXML
    private JFXButton panel4Plus;
    @FXML
    private JFXButton panel4Minus;
    @FXML
    private JFXButton panel4Action;
    @FXML
    private Text panel4Value;

    @FXML
    private ImageView panel5Img;
    @FXML
    private JFXButton panel5Plus;
    @FXML
    private JFXButton panel5Minus;
    @FXML
    private JFXButton panel5Action;
    @FXML
    private Text panel5Value;

    @FXML
    private ImageView panel6Img;
    @FXML
    private JFXButton panel6Plus;
    @FXML
    private JFXButton panel6Minus;
    @FXML
    private JFXButton panel6Action;
    @FXML
    private Text panel6Value;

    @FXML
    private ImageView panel7Img;
    @FXML
    private JFXButton panel7Plus;
    @FXML
    private JFXButton panel7Minus;
    @FXML
    private JFXButton panel7Action;
    @FXML
    private Text panel7Value;

    @FXML
    private ImageView panel8Img;
    @FXML
    private JFXButton panel8Plus;
    @FXML
    private JFXButton panel8Minus;
    @FXML
    private JFXButton panel8Action;
    @FXML
    private Text panel8Value;

    public void upValue1(MouseEvent mouseEvent) {
        int num = Integer.parseInt(panel1Value.getText());
        if (num < 15) {
            num++;
        }
        String str;
        if (num < 10) {
            str = "0" + String.valueOf(num);
        } else {
            str = String.valueOf(num);
        }
        panel1Value.setText(str);
    }

    public void downValue1(MouseEvent mouseEvent) {
        int num = Integer.parseInt(panel1Value.getText());
        if (num > 1) {
            num--;
        }
        String str;
        if (num < 10) {
            str = "0" + String.valueOf(num);
        } else {
            str = String.valueOf(num);
        }
        panel1Value.setText(str);
    }
}


