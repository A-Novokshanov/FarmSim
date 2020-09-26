package views.homeScreen;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class homeScreenController {
    @FXML
    private JFXButton btnNewGame;

    /*
     *Starts up initial configuration windows to create new game.
     *
     * @param mouseEvent On click, moves player to initial configuration screen.
     */
    public void newGame(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnNewGame.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../initialConfig/InitialConfiguration.fxml"));
        } catch (IOException e) {
            System.out.println("Loader error.");
            e.printStackTrace();
        }
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }
}