package views.marketPlace;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class MarketPlace {
    private Image cornImage = new Image("@../../dependencies/images/SpringBig.png",
            400.0, 300.0, true, false);
    private Image potatoImage = new Image("@../../dependencies/images/SummerBig.jpg",
            400.0, 300.0, true, true);
    private Image tomatoImage = new Image("@../../dependencies/images/Fall.png",
            400.0, 300.0, true, true);

    @FXML
    private JFXButton btnSeeds;
    @FXML
    private JFXButton btnAnimals;
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private ImageView image4;
    @FXML
    private ImageView image5;
    @FXML
    private ImageView image6;
    @FXML
    private ImageView image7;
    @FXML
    private ImageView image8;

    public void setSeedSelection(MouseEvent mouseEvent) {

    }

    public void setAnimalSelection(MouseEvent mouseEvent) {

    }

    private void imageSwitch(Image img) {
        image1.setImage(img);
        image2.setImage(img);
        image3.setImage(img);
        image4.setImage(img);
        image5.setImage(img);
        image6.setImage(img);
        image7.setImage(img);
        image8.setImage(img);
    }
}


