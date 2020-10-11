package views.farmUI;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import viewmodels.SettingViewModel;

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

    private int num = 1;

    private SettingViewModel settingViewModel;

    /**
     * Initializes data with the parameter
     *
     * @param settingViewModel Setting View Model to access player details.
     */
    public void initData(SettingViewModel settingViewModel) {
        this.settingViewModel = settingViewModel;
        setMoney();
    }

    /**
     * Makes Inventory Screen Invisible if exit button is clicked
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
     * Starting money amount based on difficulty.
     */
    public void setMoney() {
        switch (settingViewModel.getPlayerSettings().getStartingDifficulty()) {
        case "Casual":
            money.setText("$" + 10000);
            break;
        case "Normal":
            money.setText("$" + 1000);
            break;
        case "Veteran":
            money.setText("$" + 100);
            break;
        default:
            money.setText("$" + 0);
        }
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
