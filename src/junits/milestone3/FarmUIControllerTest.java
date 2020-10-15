package junits.milestone3;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.junit.Assert.assertEquals;
import views.farmUI.FarmUIController;

/**
 * @author Shaun Jacob sjacob31@gatech.edu
 * @version 1.0
 */
public class FarmUIControllerTest extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FarmUIController farmUIController = new FarmUIController();

        Parent root = FXMLLoader.load(getClass().getResource("../../views/farmUI/FarmUI.fxml"));
        primaryStage.setTitle("Farm UI");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    @Test
    public void testInventoryPopup() {
        clickOn("btnBarn");
        verifyThat()
    }
}
