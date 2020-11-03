package views.homeScreenUI;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.stage.StageStyle;
import viewmodels.PlayerViewModel;
import views.farmUI.FarmUIController;

import java.io.IOException;

/**
 * @author Aditya Varun Pratap
 */
public class ContinueScreenController {

    @FXML
    private JFXTextField txtFldUserName;

    @FXML
    private Text txtError;

    private PlayerViewModel playerViewModel = new PlayerViewModel();

    /**
     * This method runs before any other on this controller and initializes
     * and values as the page is loading.
     */
    @FXML
    public void initialize() {
        this.txtFldUserName.setStyle("-fx-text-fill: white");
    }

    /**
     * This method checks if the user exists, gets their information,
     * and continues their previously saved game.
     *
     * @param actionEvent The action event is the user pressing the continue button.
     */
    public void continueGame(ActionEvent actionEvent) {
        if (this.playerViewModel.playerExists(txtFldUserName.textProperty().getValue())) {
            playerViewModel.getPlayerInformationFromDatabase(
                    txtFldUserName.textProperty().getValue());
            Parent root;
            try {
                root = FXMLLoader.load(
                        getClass().getResource("../farmUI/FarmUI.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(new Scene(root, 1280, 720));
            } catch (IOException e) {
                e.printStackTrace();
            }

            FarmUIController farmUIController = loader.getController();
            farmUIController.initSaveData(playerViewModel);

            stage.setTitle("Farm");
            stage.show();

        } else {
            txtFldUserName.setStyle("-fx-text-fill: white");
            txtFldUserName.setUnFocusColor(Color.RED);
            txtError.setVisible(true);

            txtFldUserName.textProperty().addListener((observable, oldValue, newValue) -> {
                txtFldUserName.setStyle("-fx-prompt-text-fill: white");
                txtFldUserName.setStyle("-fx-text-fill: white");
                txtError.setVisible(false);
                txtFldUserName.setUnFocusColor(Color.WHITE);
                txtFldUserName.setFocusColor(Color.WHITE);
            });
        }
    }
}
