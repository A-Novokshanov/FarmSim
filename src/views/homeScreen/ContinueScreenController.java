package views.homeScreen;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import viewmodels.PlayerViewModel;

public class ContinueScreenController {

    @FXML
    private JFXTextField txtFldUserName;

    private PlayerViewModel playerViewModel = new PlayerViewModel();

    /**
     * This method checks if the user exists, gets their information,
     * and continues their previously saved game.
     *
     * @param actionEvent The action event is the user pressing the continue button.
     */
    public void continueGame(ActionEvent actionEvent) {
    }
}
