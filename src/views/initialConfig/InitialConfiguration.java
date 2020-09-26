package views.initialConfig;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class InitialConfiguration {
    @FXML
    private JFXTextField txtFldName;
    @FXML
    private JFXButton btnCreateGame;
    private SettingViewModel settings;


    public void setSeasonAndSeed() {
        SeedModel seed = new SeedModel("tomato");
        List<AnimalModel> animals = new ArrayList<>();
        List<CropModel> crops = new ArrayList<>();
        SeasonModel season = new SeasonModel(3, "spring", animals, crops);
        settings = new SettingViewModel(seed, season);
    }

    public void setNewName() {
        try {
            txtFldName.textProperty().bind(settings.getPlayerName());
        } catch (NullPointerException e) {
            // return message on screen

            // turn textfield color to red
        }
    }

    public void setDifficulty() {
        txtFldName.textProperty().bind(settings.getStartingDifficulty());
    }

    public void createGame(MouseEvent mouseEvent) {
        setSeasonAndSeed();
        setNewName();
        setDifficulty();
        // btn1 = create button id
        Stage stage = (Stage) btnCreateGame.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../farmUI/farmUI.fxml"));
        } catch (IOException e) {
            System.out.println("Loader error");
            e.printStackTrace();
        }
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }
}
