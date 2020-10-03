package views.homeScreen;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * @author Matthew Farias Email: mfarias6@gatech.edu
 * @author Shaun Jacob Email: sjacob31@gatech.edu
 * @version 1.0
 */
public class HomeScreenController {
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
        Stage stage = new Stage();
        Stage currentStage = (Stage) btnNewGame.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../initialConfig/InitialConfiguration2.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Initial Configuration");
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root, 1280, 720);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        currentStage.close();
        stage.show();
    }

    /**
     * Continues game on Farm UI screen.
     *
     * @param mouseEvent On click, moves player to farm UI screen.
     */
    public void continueGame(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnContinue.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../farmUI/FarmUI.fxml"));
        } catch (IOException e) {
            System.out.println("Loader error.");
            e.printStackTrace();
        }
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }
}
