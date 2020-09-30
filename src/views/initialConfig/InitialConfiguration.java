package views.initialConfig;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.AnimalModel;
import models.CropModel;
import models.SeasonModel;
import models.SeedModel;
import viewmodels.PlayerViewModel;
import views.farmUI.FarmUIController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    private SeedModel seed;
    private PlayerViewModel playerViewModel = new PlayerViewModel();
    private SeasonModel season;
    private String curDifficulty = "Normal";
    private String curSeed = "Corn";
    private String curSeason = "Spring";
    private int currentMoney;

    /**
     * Sets Season and Seed in a Setting view model.
     */
    public void setSeasonAndSeed() {
        seed = new SeedModel(curSeed.toString());
        List<AnimalModel> animals = new ArrayList<>();
        List<CropModel> crops = new ArrayList<>();
        season = new SeasonModel(3, curSeason.toString(), animals, crops);
    }


    /**
     * Validates or checks if name is entered correctly.
     *
     * @param name The name of the player trying to login.
     * @return A boolean representing if the name is entered correctly.
     */
    private boolean validateName(String name) {

        if (txtFldName.getText().isBlank()) {
            txtNameError.setVisible(true);
            txtFldName.setUnFocusColor(Color.RED);
            txtFldName.setFocusColor(Color.RED);
            txtFldName.setStyle("-fx-prompt-text-fill: RED");
            txtFldName.textProperty().addListener((observable, oldValue, newValue) -> {
                txtFldName.setStyle("-fx-prompt-text-fill: black");
                txtFldName.setUnFocusColor(Color.BLACK);
            });

            return false;

        } else if (playerViewModel.playerExists(name)) {
            txtNameError.setText("The player already exists, please login");
            txtNameError.setVisible(true);
            txtFldName.setUnFocusColor(Color.RED);
            txtFldName.setFocusColor(Color.RED);
            txtFldName.setStyle("-fx-prompt-text-fill: RED");
            txtFldName.textProperty().addListener((observable, oldValue, newValue) -> {
                txtFldName.setStyle("-fx-prompt-text-fill: black");
                txtFldName.setUnFocusColor(Color.BLACK);
            });
            return false;
        }

        return true;
    }

    /**
     * Creates new game based on selected settings
     *
     * @param mouseEvent Game created on mouse click.
     */
    public void createGame(MouseEvent mouseEvent) {
        setSeasonAndSeed();
        // btn1 = create button id
        if (validateName(txtFldName.textProperty().getValue())) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../farmUI/FarmUI.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            try {
                stage.setScene(
                        new Scene(loader.load())
                );
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.setMoney();
            playerViewModel.setPlayerDetails(seed, season,
                    txtFldName.textProperty().getValue(), curDifficulty, currentMoney);
            FarmUIController farmUIController = loader.getController();
            farmUIController.initData(playerViewModel);

            Stage currentStage = (Stage) txtFldName.getScene().getWindow();
            currentStage.close();

            stage.setTitle("Farm");
            stage.show();
        }
    }

    /**
     * Starting money amount based on difficulty.
     */
    public void setMoney() {
        switch (curDifficulty) {
            case "Casual":
                currentMoney = 10000;
                break;
            case "Normal":
                currentMoney = 1000;
                break;
            case "Veteran":
                currentMoney = 100;
                break;
            default:
                currentMoney = 0;
        }
    }

    /**
     * Sets difficulty to Casual.
     *
     * @param mouseEvent Button selected on mouse click.
     */
    public void setCasual(MouseEvent mouseEvent) {
        btnCasual.setSelected(true);
        btnNormal.setSelected(false);
        btnVeteran.setSelected(false);
        curDifficulty = "Casual";
    }

    /**
     * Sets difficulty to Normal.
     *
     * @param mouseEvent Button selected on mouse click.
     */
    public void setNormal(MouseEvent mouseEvent) {
        btnCasual.setSelected(false);
        btnNormal.setSelected(true);
        btnVeteran.setSelected(false);
        curDifficulty = "Normal";
    }

    /**
     * Sets difficulty to Veteran.
     *
     * @param mouseEvent Button selected on mouse click.
     */
    public void setVeteran(MouseEvent mouseEvent) {
        btnCasual.setSelected(false);
        btnNormal.setSelected(false);
        btnVeteran.setSelected(true);
        curDifficulty = "Veteran";
    }

    /**
     * Sets starting seed type to Corn.
     *
     * @param mouseEvent Button selected on mouse click.
     */
    public void setCorn(MouseEvent mouseEvent) {
        btnCorn.setSelected(true);
        btnPotato.setSelected(false);
        btnTomato.setSelected(false);
        curSeed = "Corn";
    }

    /**
     * Sets starting seed type to Potato.
     *
     * @param mouseEvent Button selected on mouse click.
     */
    public void setPotato(MouseEvent mouseEvent) {
        btnCorn.setSelected(false);
        btnPotato.setSelected(true);
        btnTomato.setSelected(false);
        curSeed = "Potato";
    }

    /**
     * Sets starting seed type to Tomato.
     *
     * @param mouseEvent Button selected on mouse click.
     */
    public void setTomato(MouseEvent mouseEvent) {
        btnCorn.setSelected(false);
        btnPotato.setSelected(false);
        btnTomato.setSelected(true);
        curSeed = "Tomato";
        System.out.println(curSeed.toString());
    }

    /**
     * Sets season to Spring.
     *
     * @param mouseEvent Button selected on mouse click.
     */
    public void setSpring(MouseEvent mouseEvent) {
        imgSeasonSelected.setImage(springImage);
        curSeason = "Spring";
    }

    /**
     * Sets season to Summer.
     *
     * @param mouseEvent Button selected on mouse click.
     */
    public void setSummer(MouseEvent mouseEvent) {
        imgSeasonSelected.setImage(summerImage);
        curSeason = "Summer";
    }

    /**
     * Sets season to Autumn.
     *
     * @param mouseEvent Button selected on mouse click.
     */
    public void setAutumn(MouseEvent mouseEvent) {
        imgSeasonSelected.setImage(autumnImage);
        curSeason = "Autumn";
    }

    /**
     * Sets season to Winter.
     *
     * @param mouseEvent Button selected on mouse click.
     */
    public void setWinter(MouseEvent mouseEvent) {
        imgSeasonSelected.setImage(winterImage);
        curSeason = "Winter";
    }
}
