package views.farmUI;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import viewmodels.PlayerViewModel;

public class FarmUIController {
    @FXML
    private Text money;
    @FXML
    private Text dayNum;
    private int num = 1;

    private PlayerViewModel playerViewModel;


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
