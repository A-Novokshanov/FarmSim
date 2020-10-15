package views.farmUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.CropModel;
import models.PlotModel;
import models.StorageModel;
import viewmodels.PlayerViewModel;
import viewmodels.PlotViewModel;
import viewmodels.StorageViewModel;
import views.marketPlace.MarketPlace;

import javax.swing.text.html.ImageView;
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

    @FXML
    private ImageView plot1Img;
    @FXML
    private ImageView plot2Img;
    @FXML
    private ImageView plot3Img;
    @FXML
    private ImageView plot4Img;
    @FXML
    private ImageView plot5Img;
    @FXML
    private ImageView plot6Img;
    @FXML
    private ImageView plot7Img;
    @FXML
    private ImageView plot8Img;
    @FXML
    private ImageView plot9Img;
    @FXML
    private ImageView plot10Img;

    @FXML
    private ImageView plotName1Img;
    @FXML
    private ImageView plotName2Img;
    @FXML
    private ImageView plotName3Img;
    @FXML
    private ImageView plotName4Img;
    @FXML
    private ImageView plotName5Img;
    @FXML
    private ImageView plotName6Img;
    @FXML
    private ImageView plotName7Img;
    @FXML
    private ImageView plotName8Img;
    @FXML
    private ImageView plotName9Img;
    @FXML
    private ImageView plotName10Img;

    private PlotModel plot1;
    private PlotModel plot2;
    private PlotModel plot3;
    private PlotModel plot4;
    private PlotModel plot5;
    private PlotModel plot6;
    private PlotModel plot7;
    private PlotModel plot8;
    private PlotModel plot9;
    private PlotModel plot10;

    private int num = 1;

    private PlayerViewModel playerViewModel;
    private StorageViewModel storageViewModel;
    private StorageModel storageModel;
    private PlotViewModel plotViewModel;
    private String name;

    /**
     * Initializes data with the parameter
     *
     * @param playerViewModel Setting View Model to access player details.
     * @param playerName      The name of the current player.
     */
    public void initData(PlayerViewModel playerViewModel, String playerName) {
        //playerViewModel.getPlayerInformationFromDatabase(playerName);
        this.money.setText("$ " + playerViewModel.getPlayer().getUserCurrentMoney());
        this.storageViewModel = new StorageViewModel(playerViewModel);
        this.plotViewModel = new PlotViewModel();
        this.playerViewModel = playerViewModel;
        this.name = playerName;
        setUpPlotModels(
                playerViewModel.getPlayer().getPlayerSettings().getStartingCropType());
        numCorn.setText(
                String.valueOf(storageViewModel.userInventory().get(0).getCropQuantity()));
        numPotatoes.setText(
                String.valueOf(storageViewModel.userInventory().get(1).getCropQuantity())
        );
        numTomatoes.setText(
                String.valueOf(storageViewModel.userInventory().get(2).getCropQuantity())
        );
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
        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        currentStage.close();
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(
                    new Scene(loader.load())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        MarketPlace marketPlace = loader.getController();
        marketPlace.initData(mouseEvent, playerViewModel, storageViewModel, name);

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
        incrementAllPlotDays();
    }

    public void harvestCrop(PlotModel harvestedPlot) {
        this.plotViewModel.harvestPlot(harvestedPlot, this.playerViewModel);
    }

    public void setUpPlotModels(CropModel cropModel) {
        plot1 = plotViewModel.populatePlot(cropModel);
        plot2 = plotViewModel.populatePlot(cropModel);
        plot3 = plotViewModel.populatePlot(cropModel);
        plot4 = plotViewModel.populatePlot(cropModel);
        plot5 = plotViewModel.populatePlot(cropModel);
        plot6 = plotViewModel.populatePlot(cropModel);
        plot7 = plotViewModel.populatePlot(cropModel);
        plot8 = plotViewModel.populatePlot(cropModel);
        plot9 = plotViewModel.populatePlot(cropModel);
        plot10 = plotViewModel.populatePlot(cropModel);
    }

    public void incrementAllPlotDays() {
        plotViewModel.incrementPlotDaysOld(plot1);
        plotViewModel.incrementPlotDaysOld(plot2);
        plotViewModel.incrementPlotDaysOld(plot3);
        plotViewModel.incrementPlotDaysOld(plot4);
        plotViewModel.incrementPlotDaysOld(plot5);
        plotViewModel.incrementPlotDaysOld(plot6);
        plotViewModel.incrementPlotDaysOld(plot7);
        plotViewModel.incrementPlotDaysOld(plot8);
        plotViewModel.incrementPlotDaysOld(plot9);
        plotViewModel.incrementPlotDaysOld(plot10);
    }
}
