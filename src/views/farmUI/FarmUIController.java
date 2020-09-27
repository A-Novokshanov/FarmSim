package views.farmUI;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.awt.event.MouseEvent;

public class FarmUIController {
    @FXML
    private Text money;
    @FXML
    private Text dayNum;
    private int num = 1;

    public void setMoney() {

    }

    public void updateDay(MouseEvent mouseEvent) {
        num++;
        dayNum.setText("Day " + num);
    }
}
