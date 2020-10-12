package views.farmUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.StorageModel;
import viewmodels.PlayerViewModel;
import viewmodels.StorageViewModel;
import views.marketPlace.MarketPlace;

import java.io.IOException;

public class FarmUIController {
    @FXML
    private Text money;
    @FXML
    private Text dayNum;
    @FXML
    private Pane inventoryScreen;
    @FXML
    private Circle dayCounter;
    @FXML
    private Circle sun;
    @FXML
    private Line sunProgressBar;
    @FXML
    private Text numTomatoes;
    @FXML
    private Text numPotatoes;
    @FXML
    private Text numCorn;

    private int num = 1;

    private PlayerViewModel playerViewModel;
    private StorageViewModel storageViewModel;
    private StorageModel storageModel;

    /**
     * Initializes data with the parameter
     *
     * @param playerViewModel Setting View Model to access player details.
     * @param playerName      The name of the current player.
     */
    public void initData(PlayerViewModel playerViewModel, String playerName) {
        //playerViewModel.getPlayerInformationFromDatabase(playerName);
        money.setText("$ " + playerViewModel.getPlayer().getUserCurrentMoney());
        storageViewModel = new StorageViewModel(playerViewModel);
        System.out.println("User Storage" + playerViewModel.getPlayer().getUserStorage());
        numTomatoes.setText(
                String.valueOf(storageViewModel.userInventory().get(0).getCropQuantity())
        );
        numPotatoes.setText(
                String.valueOf(storageViewModel.userInventory().get(1).getCropQuantity())
        );
        numCorn.setText(String.valueOf(storageViewModel.userInventory().get(2).getCropQuantity()));
        this.playerViewModel = playerViewModel;
    }

    /**
     * Makes Inventory Screen Invisible if exit button is clicked
     *
     * @param mouseEvent a mouse click on the exit button
     */
    public void toggleInventoryScreenVisibility(MouseEvent mouseEvent) {
        inventoryScreen.setVisible(!inventoryScreen.isVisible());
        dayCounter.setVisible(!dayCounter.isVisible());
        dayNum.setVisible(!dayNum.isVisible());
        sun.setVisible(!sun.isVisible());
        sunProgressBar.setVisible(!sunProgressBar.isVisible());
    }

    /**
     * @param mouseEvent is the mouse trigger event
     */
    public void goToMarket(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().
                getResource("../marketPlace/MarketPlace.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(
                    new Scene(loader.load())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        MarketPlace marketPlace = loader.getController();
        marketPlace.initData(mouseEvent, playerViewModel, storageViewModel);

        stage.setTitle("Market");
        stage.show();
    }

    /**
     * Updates day number.
     *
     * @param mouseEvent (Prototype) Clicking on a day switches the current day number.
     */
    public void updateDay(MouseEvent mouseEvent) {
        num++;
        dayNum.setText("Day " + num);
    }
}
