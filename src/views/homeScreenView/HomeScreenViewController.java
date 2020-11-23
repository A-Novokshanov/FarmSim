package views.homeScreenView;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Matthew Farias Email: mfarias6@gatech.edu
 * @author Shaun Jacob Email: sjacob31@gatech.edu
 * @version 1.0
 */
public class HomeScreenViewController {
    @FXML
    private JFXButton btnNewGame;
    @FXML
    private JFXButton btnContinue;

    private double x;
    private double y;

    /**
     * This method enables the dragging of a window pane.
     *
     * @param event The event that is clicking of the mouse.
     */
    public void dragHomeScreen(MouseEvent event) {

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
    public void pressedHomeScreen(MouseEvent event) {

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
     * Starts up initial configuration windows to create new game.
     *
     * @param mouseEvent On click, moves player to initial configuration screen.
     */
    public void newGame(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnNewGame.getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(
                    getClass().getResource("../initialConfigView/InitialConfigView.fxml"));
            stage.setTitle("Initial Configuration");
            stage.setScene(new Scene(root, 1280, 720));
            stage.show();
        } catch (IOException e) {
            System.out.println("New game loader error.");
            e.printStackTrace();
        }
    }

    /**
     * Continues game on Farm UI screen.
     *
     * @param mouseEvent On click, moves player to farm UI screen.
     */
    public void continueGame(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnContinue.getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(
                    getClass().getResource("ContinueScreenView.fxml"));
            stage.setTitle("Login");
            stage.setScene(new Scene(root, 600, 344));
            stage.show();
        } catch (IOException e) {
            System.out.println("Loader error.");
            e.printStackTrace();
        }
    }
}
