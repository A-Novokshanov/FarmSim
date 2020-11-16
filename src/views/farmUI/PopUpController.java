package views.farmUI;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class PopUpController {
    @FXML
    private Text txtPopUp;

    public void initData(String popUpText) {
        this.txtPopUp.setText(popUpText);
    }
}
