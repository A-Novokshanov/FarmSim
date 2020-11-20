package views.farmView;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class PopUpViewController {
    @FXML
    private Text txtPopUp;

    public void initData(String popUpText) {
        this.txtPopUp.setText(popUpText);
    }
}
