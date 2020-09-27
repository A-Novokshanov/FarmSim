package views.initialConfig;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.AnimalModel;
import models.CropModel;
import models.SeasonModel;
import models.SeedModel;
import viewmodels.SettingViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InitialConfiguration {
    private Image springImage = new Image("@../../dependencies/images/SpringBig.png", 400.0, 300.0, true, false);
    private Image summerImage = new Image("@../../dependencies/images/SummerBig.jpg", 400.0, 300.0, true, true);
    private Image autumnImage = new Image("@../../dependencies/images/Fall.png", 400.0, 300.0, true, true);
    private Image winterImage = new Image("@../../dependencies/images/Winter.png", 400.0, 300.0, true, true);

    @FXML
    private JFXTextField txtFldName;
    @FXML
    private JFXButton btnCreateGame;
    private SettingViewModel settings;
    @FXML
    private JFXRadioButton btnCasual;
    @FXML
    private JFXRadioButton btnNormal;
    @FXML
    private JFXRadioButton btnVeteran;
    @FXML
    private JFXRadioButton btnCorn;
    @FXML
    private JFXRadioButton btnPotato;
    @FXML
    private JFXRadioButton btnTomato;
    @FXML
    private JFXButton btnSpring;
    @FXML
    private JFXButton btnSummer;
    @FXML
    private JFXButton btnAutumn;
    @FXML
    private JFXButton btnWinter;
    @FXML
    private ImageView imgSeasonSelected;
    @FXML
    private Text txtNameError;
    private boolean validNameEntered = false;


    public void setSeasonAndSeed() {
        SeedModel seed = new SeedModel("tomato");
        List<AnimalModel> animals = new ArrayList<>();
        List<CropModel> crops = new ArrayList<>();
        SeasonModel season = new SeasonModel(3, "spring", animals, crops);
        settings = new SettingViewModel(seed, season);
    }

    public void setNewName() {
        txtFldName.textProperty().bind(settings.getPlayerName());
    }

    public void setDifficulty() {
        txtFldName.textProperty().bind(settings.getStartingDifficulty());
    }

    public void createGame(MouseEvent mouseEvent) {

        if (validateName()) {
            setSeasonAndSeed();
            setNewName();
            setDifficulty();
            openHomeScreen();
        }

    }

    private void openHomeScreen() {

        Stage stage = (Stage) btnCreateGame.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../farmUI/FarmUI.fxml"));
        } catch (IOException e) {
            System.out.println("Loader error");
            e.printStackTrace();
        }
        stage.setTitle("Home Screen");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }

    private boolean validateName() {
        if (txtFldName.getText().trim().isEmpty()) {
            txtFldName.textProperty().addListener((observable, oldValue, newValue) -> {
                txtFldName.setStyle("-fx-prompt-text-fill: BLACK");
                txtFldName.setUnFocusColor(Color.BLACK);
                txtFldName.setFocusColor(Color.BLACK);
                txtNameError.setVisible(false);

            });
            txtNameError.setVisible(true);
            txtFldName.setUnFocusColor(Color.RED);
            txtFldName.setStyle("-fx-prompt-text-fill: RED");

            return false;
        }

        return true;
    }

    public void setCasual(MouseEvent mouseEvent) {
        btnCasual.setSelected(true);
        btnNormal.setSelected(false);
        btnVeteran.setSelected(false);
    }

    public void setNormal(MouseEvent mouseEvent) {
        btnCasual.setSelected(false);
        btnNormal.setSelected(true);
        btnVeteran.setSelected(false);
    }

    public void setVeteran(MouseEvent mouseEvent) {
        btnCasual.setSelected(false);
        btnNormal.setSelected(false);
        btnVeteran.setSelected(true);
    }

    public void setCorn(MouseEvent mouseEvent) {
        btnCorn.setSelected(true);
        btnPotato.setSelected(false);
        btnTomato.setSelected(false);
    }

    public void setPotato(MouseEvent mouseEvent) {
        btnCorn.setSelected(false);
        btnPotato.setSelected(true);
        btnTomato.setSelected(false);
    }

    public void setTomato(MouseEvent mouseEvent) {
        btnCorn.setSelected(false);
        btnPotato.setSelected(false);
        btnTomato.setSelected(true);
    }

    public void setSpring(MouseEvent mouseEvent) {
        imgSeasonSelected.setImage(springImage);
    }

    public void setSummer(MouseEvent mouseEvent) {
        imgSeasonSelected.setImage(summerImage);
    }

    public void setAutumn(MouseEvent mouseEvent) {
        imgSeasonSelected.setImage(autumnImage);
    }

    public void setWinter(MouseEvent mouseEvent) {
        imgSeasonSelected.setImage(winterImage);
    }
}
