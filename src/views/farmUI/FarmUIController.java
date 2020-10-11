package views.farmUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import models.StorageModel;
import viewmodels.PlayerViewModel;
import viewmodels.StorageViewModel;

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
        this.playerViewModel = playerViewModel;
        playerViewModel.getPlayerInformationFromDatabase(playerName);
        money.setText("$ " + this.playerViewModel.getPlayer().getUserCurrentMoney());
        storageModel = new StorageModel();
        storageViewModel = new StorageViewModel(playerViewModel);
        numTomatoes.setText(
                String.valueOf(storageViewModel.userInventory().get(0).getCropQuantity())
        );
        numPotatoes.setText(
                String.valueOf(storageViewModel.userInventory().get(1).getCropQuantity())
        );
        numCorn.setText(String.valueOf(storageViewModel.userInventory().get(2).getCropQuantity()));
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
     * Updates day number.
     *
     * @param mouseEvent (Prototype) Clicking on a day switches the current day number.
     */
    public void updateDay(MouseEvent mouseEvent) {
        num++;
        dayNum.setText("Day " + num);
    }
}
