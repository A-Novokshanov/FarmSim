package views.farmUI;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import viewmodels.SettingViewModel;

/**
 * @author Matthew Farias Email: mfarias6@gatech.edu
 * @author Shaun Jacob Email: sjacob31@gatech.edu
 * @version 1.0
 */
public class FarmUIController {
    @FXML
    private Text money;
    @FXML
    private Text dayNum;
    private int num = 1;

    /**
     *Starting money amount based on difficulty.
     *
     * @param curDifficulty Inputted difficulty in initial configuration screen.
     */
    public void setMoney(String curDifficulty) {
        SettingViewModel settings = new SettingViewModel(null, null);
        switch (settings.getDifficulty().toString()) {
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
     *Updates day number.
     *
     * @param mouseEvent (Prototype) Clicking on a day switches the current day number.
     */
    public void updateDay(MouseEvent mouseEvent) {
        num++;
        dayNum.setText("Day " + num);
    }
}
