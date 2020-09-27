package views.initialConfig;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.AnimalModel;
import models.CropModel;
import models.SeasonModel;
import models.SeedModel;
import viewmodels.SettingViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Matthew Farias Email: mfarias6@gatech.edu
 * @author Shaun Jacob Email: sjacob31@gatech.edu
 * @version 1.0
 */
public class InitialConfiguration {
    private Image springImage = new Image("@../../dependencies/images/SpringBig.png",
            400.0, 300.0, true, false);
    private Image summerImage = new Image("@../../dependencies/images/SummerBig.jpg",
            400.0, 300.0, true, true);
    private Image autumnImage = new Image("@../../dependencies/images/Fall.png",
            400.0, 300.0, true, true);
    private Image winterImage = new Image("@../../dependencies/images/Winter.png",
            400.0, 300.0, true, true);

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
    private StringProperty curDifficulty = new SimpleStringProperty("Normal");
    private StringProperty curSeed = new SimpleStringProperty("Corn");
    private StringProperty curSeason = new SimpleStringProperty("Spring");

    /**
     *Sets Season and Seed in a Setting view model.
     */
    public void setSeasonAndSeed() {
        SeedModel seed = new SeedModel(curSeed.toString());
        List<AnimalModel> animals = new ArrayList<>();
        List<CropModel> crops = new ArrayList<>();
        SeasonModel season = new SeasonModel(3, curSeason.toString(), animals, crops);
        settings = new SettingViewModel(seed, season);
    }

    /**
     *Sets name in a Setting view model.
     */
    public void setNewName() {
        if (txtFldName.getText().isBlank()) {
            txtNameError.setVisible(true);
            txtFldName.setUnFocusColor(Color.RED);
            txtFldName.setFocusColor(Color.RED);
            txtFldName.setStyle("-fx-prompt-text-fill: RED");
            txtFldName.textProperty().addListener((observable, oldValue, newValue) -> {
                txtFldName.setStyle("-fx-prompt-text-fill: black");
                txtFldName.setUnFocusColor(Color.BLACK);
            });
        } else {
            txtFldName.textProperty().bind(settings.getPlayerName());
            validNameEntered = true;
        }
    }

    /**
     *Sets difficulty in a Setting view model.
     */
    public void setDifficulty() {
        curDifficulty.bind(settings.getDifficulty());
    }

    /**
     *Creates new game based on selected settings
     *
     * @param mouseEvent Game created on mouse click.
     */
    public void createGame(MouseEvent mouseEvent) {
        setSeasonAndSeed();
        setNewName();
        setDifficulty();
        // btn1 = create button id
        if (validNameEntered) {
            Stage stage = (Stage) btnCreateGame.getScene().getWindow();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../farmUI/FarmUI.fxml"));
            } catch (IOException e) {
                System.out.println("Loader error");
                e.printStackTrace();
            }
            stage.setTitle("Hello World");
            stage.setScene(new Scene(root, 1280, 720));
            stage.show();
        }
    }

    /**
     *Sets difficulty to Casual.
     *
     * @param mouseEvent Button selected on mouse click.
     */
    public void setCasual(MouseEvent mouseEvent) {
        btnCasual.setSelected(true);
        btnNormal.setSelected(false);
        btnVeteran.setSelected(false);
        curDifficulty.set("Casual");
    }

    /**
     *Sets difficulty to Normal.
     *
     * @param mouseEvent Button selected on mouse click.
     */
    public void setNormal(MouseEvent mouseEvent) {
        btnCasual.setSelected(false);
        btnNormal.setSelected(true);
        btnVeteran.setSelected(false);
        curDifficulty.set("Normal");
    }

    /**
     *Sets difficulty to Veteran.
     *
     * @param mouseEvent Button selected on mouse click.
     */
    public void setVeteran(MouseEvent mouseEvent) {
        btnCasual.setSelected(false);
        btnNormal.setSelected(false);
        btnVeteran.setSelected(true);
        curDifficulty.set("Veteran");
    }

    /**
     *Sets starting seed type to Corn.
     *
     * @param mouseEvent Button selected on mouse click.
     */
    public void setCorn(MouseEvent mouseEvent) {
        btnCorn.setSelected(true);
        btnPotato.setSelected(false);
        btnTomato.setSelected(false);
        curSeed.set("Corn");
    }

    /**
     *Sets starting seed type to Potato.
     *
     * @param mouseEvent Button selected on mouse click.
     */
    public void setPotato(MouseEvent mouseEvent) {
        btnCorn.setSelected(false);
        btnPotato.setSelected(true);
        btnTomato.setSelected(false);
        curSeed.set("Potato");
    }

    /**
     *Sets starting seed type to Tomato.
     *
     * @param mouseEvent Button selected on mouse click.
     */
    public void setTomato(MouseEvent mouseEvent) {
        btnCorn.setSelected(false);
        btnPotato.setSelected(false);
        btnTomato.setSelected(true);
        curSeed.set("Tomato");
    }

    /**
     *Sets season to Spring.
     *
     * @param mouseEvent Button selected on mouse click.
     */
    public void setSpring(MouseEvent mouseEvent) {
        imgSeasonSelected.setImage(springImage);
        curSeason.set("Spring");
    }

    /**
     *Sets season to Summer.
     *
     * @param mouseEvent Button selected on mouse click.
     */
    public void setSummer(MouseEvent mouseEvent) {
        imgSeasonSelected.setImage(summerImage);
        curSeason.set("Summer");
    }

    /**
     *Sets season to Autumn.
     *
     * @param mouseEvent Button selected on mouse click.
     */
    public void setAutumn(MouseEvent mouseEvent) {
        imgSeasonSelected.setImage(autumnImage);
        curSeason.set("Autumn");
    }

    /**
     *Sets season to Winter.
     *
     * @param mouseEvent Button selected on mouse click.
     */
    public void setWinter(MouseEvent mouseEvent) {
        imgSeasonSelected.setImage(winterImage);
        curSeason.set("Winter");
    }
}
