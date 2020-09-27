package views.farmUI;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import viewmodels.SettingViewModel;

public class FarmUIController {
    @FXML
    private Text money;
    @FXML
    private Text dayNum;
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
