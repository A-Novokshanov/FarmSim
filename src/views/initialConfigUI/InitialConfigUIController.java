package views.initialConfigUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
import models.StorageModel;
import viewmodels.PlayerViewModel;
import views.farmUI.FarmUIController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InitialConfigUIController {
    private final Image springImage = new Image("@../../dependencies/images/SpringBig.png",
            400.0, 300.0, true, false);
    private final Image summerImage = new Image("@../../dependencies/images/SummerBig.jpg",
            400.0, 300.0, true, true);
    private final Image autumnImage = new Image("@../../dependencies/images/Fall.png",
            400.0, 300.0, true, true);
    private final Image winterImage = new Image("@../../dependencies/images/Winter.png",
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
    @FXML
    private JFXComboBox<String> cmbBoxSeason;

    private CropModel crop;
    private PlayerViewModel playerViewModel = new PlayerViewModel();
    private SeasonModel season;
    private String curDifficulty = "Normal";
    private String curCrop = "Corn";
    private String curSeason = "Spring";
    private int currentMoney;
    private double x = 0;
    private double y = 0;


    //    @FXML
    //    public void initialize() {
    //        this.txtFldName.setStyle("-fx-prompt-text-fill: white");
    //        this.txtFldName.setStyle("-fx-text-fill: white");
    //        this.cmbBoxSeason.setStyle("-fx-text-fill: white");
    //        this.cmbBoxSeason.setStyle("-fx-font-size: 18");
    //
    //        ObservableList<String> seasons =
    //                FXCollections.observableArrayList(
    //                        "Spring",
    //                        "Summer",
    //                        "Autumn",
    //                        "Winter"
    //                );
    //        this.cmbBoxSeason.setItems(seasons);
    //        this.cmbBoxSeason.setOnAction(new EventHandler<ActionEvent>() {
    //            @Override
    //            public void handle(ActionEvent actionEvent) {
    //                cmbBoxSeason.setStyle("-fx-text-fill: white");
    //                cmbBoxSeason.setStyle("-fx-highlight-text-fill: white");
    //                cmbBoxSeason.setStyle("-fx-font-size: 18");
    //                curSeason = cmbBoxSeason.getValue().toString();
    //
    //            }
    //        });
    //    }

    /**
     * Sets Season and Seed in a Setting view model.
     */
    public void setSeasonAndCrop() {
        crop = new CropModel(curCrop.toString(), 1, 100.0);
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
                txtFldName.setStyle("-fx-prompt-text-fill:  black");
                txtFldName.setStyle("-fx-text-fill: black");
                txtFldName.setUnFocusColor(Color.BLACK);
            });

            return false;

        } else if (playerViewModel.playerExists(name)) {
            txtNameError.setText("The player already exists, please login");
            txtNameError.setVisible(true);
            txtFldName.setUnFocusColor(Color.RED);
            txtFldName.setFocusColor(Color.RED);
            txtFldName.setStyle("-fx-prompt-text-fill: red");
            txtFldName.setStyle("-fx-text-fill: black");
            txtFldName.textProperty().addListener((observable, oldValue, newValue) -> {
                txtFldName.setStyle("-fx-prompt-text-fill: black");
                txtFldName.setStyle("-fx-text-fill: black");
                txtNameError.setVisible(false);
                txtFldName.setFocusColor(Color.WHITE);
            });
            return false;
        }

        return true;
    }

    /**
     * This method enables the dragging of a window pane.
     *
     * @param event The event that is clicking of the mouse.
     */
    public void dragInitialConfigScreen(MouseEvent event) {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }


    /**
     * This method obtains the x and y co-ordinates of the mouse when pressed.
     * These co-ordinates are then used in the dragged() method to configure
     * the position of the window pane.
     *
     * @param event The event that is clicking of the mouse.
     */
    public void pressedSignUpScreen(MouseEvent event) {

        x = event.getX();
        y = event.getY();
    }

    /**
     * This event handler method corresponds to the minimize button on the screen.
     * The method minimizes the current screen.
     *
     * @param event The event that is clicking of the mouse.
     */
    public void windowMinimize(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);

    }


    /**
     * This event handler method corresponds to the close button on the screen.
     * The method, when called, closes the current window.
     *
     * @param event A mouse event.
     */
    public void windowClose(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }


    /**
     * Creates new game based on selected settings
     */
    public void createGame() {
        setSeasonAndCrop();
        if (validateName(txtFldName.textProperty().getValue())) {
            Stage stage = (Stage) btnCreateGame.getScene().getWindow();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().
                        getResource("../farmUI/FarmUI.fxml"));
                stage.setTitle("Farm Screen");
                stage.setScene(new Scene(loader.load()));
                this.setMoney();
                StorageModel userStorage = new StorageModel();
                playerViewModel.setPlayerDetails(crop, season,
                        txtFldName.textProperty().getValue(), userStorage, curDifficulty, currentMoney);
                playerViewModel.getPlayer().setPlayerStorage(userStorage);
                FarmUIController farmUIController = loader.getController();
                farmUIController.initConfigData(playerViewModel);
                stage.show();
            } catch (IOException e) {
                System.out.println("Create game loader error.");
                e.printStackTrace();
            }
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
     */
    public void setCasual() {
        btnCasual.setSelected(true);
        btnNormal.setSelected(false);
        btnVeteran.setSelected(false);
        curDifficulty = "Casual";
    }

    /**
     * Sets difficulty to Normal.
     */
    public void setNormal() {
        btnCasual.setSelected(false);
        btnNormal.setSelected(true);
        btnVeteran.setSelected(false);
        curDifficulty = "Normal";
    }

    /**
     * Sets difficulty to Veteran.
     */
    public void setVeteran() {
        btnCasual.setSelected(false);
        btnNormal.setSelected(false);
        btnVeteran.setSelected(true);
        curDifficulty = "Veteran";
    }

    /**
     * Sets starting seed type to Corn.
     */
    public void setCorn() {
        btnCorn.setSelected(true);
        btnPotato.setSelected(false);
        btnTomato.setSelected(false);
        curCrop = "Corn";
    }

    /**
     * Sets starting seed type to Potato.
     */
    public void setPotato() {
        btnCorn.setSelected(false);
        btnPotato.setSelected(true);
        btnTomato.setSelected(false);
        curCrop = "Potato";
    }

    /**
     * Sets starting seed type to Tomato.
     */
    public void setTomato() {
        btnCorn.setSelected(false);
        btnPotato.setSelected(false);
        btnTomato.setSelected(true);
        curCrop = "Tomato";
    }

    /**
     * Sets season to Spring.
     */
    public void setSpring() {
        imgSeasonSelected.setImage(springImage);
        curSeason = "Spring";
    }

    /**
     * Sets season to Summer.
     */
    public void setSummer() {
        imgSeasonSelected.setImage(summerImage);
        curSeason = "Summer";
    }

    /**
     * Sets season to Autumn.
     */
    public void setAutumn() {
        imgSeasonSelected.setImage(autumnImage);
        curSeason = "Autumn";
    }

    /**
     * Sets season to Winter.
     */
    public void setWinter() {
        imgSeasonSelected.setImage(winterImage);
        curSeason = "Winter";
    }
}
