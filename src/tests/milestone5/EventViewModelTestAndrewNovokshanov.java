package tests.milestone5;

import models.*;
import org.junit.Before;
import org.junit.Test;
import viewmodels.EventViewModel;
import viewmodels.PlayerViewModel;
import viewmodels.PlotViewModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author Andrew Novokshanov anovokshanov3@gatech.edu
 * @version 1.0
 */
public class EventViewModelTestAndrewNovokshanov {
    private PlotModel harvestedPlot;
    private EventViewModel eventViewModel;
    private PlotViewModel plotViewModel;

    @Before
    public void setUp() {
        CropModel cropInPlot = new CropModel("Potato", 2, 1.50);
        AnimalModel animal = new AnimalModel(1, 1, 1, "Cow");
        harvestedPlot = new PlotModel(cropInPlot, 4);
        List<CropModel> desCrop = new ArrayList<CropModel>();
        desCrop.add(cropInPlot);
        List<AnimalModel> desAnim = new ArrayList<AnimalModel>();
        desAnim.add(animal);
        SeasonModel season = new SeasonModel(1, "Spring", desAnim, desCrop);
        SettingModel playerSetting = new SettingModel(season, cropInPlot, "Casual", "Andrew");
        StorageModel playerStorage = new StorageModel();
        PlayerModel player = new PlayerModel(100.00, playerSetting, playerStorage);
        eventViewModel = new EventViewModel(player);
        PlayerViewModel playerViewModel = new PlayerViewModel();
        playerViewModel.setPlayerDetails(
                playerSetting.getStartingCropType(), season, playerSetting.getPlayerName(),
                playerStorage, playerSetting.getStartingDifficulty(), player.getUserCurrentMoney());
        plotViewModel = new PlotViewModel(playerViewModel.getPlayer());
    }

    @Test
    //Test chooseEvent() method to make sure an event, or lack of an event,
    // is being returned each time the method is called
    public void testChooseEvent() {
        int eventCode = eventViewModel.chooseEvent();
        boolean inRange = ((eventCode >= -1) && (eventCode <= 2));
        assertTrue(inRange);
    }

    @Test
    //Test performRainEvent method, ensuring that random value being returned is in range
    public void testPerformRainEvent() {
        int rainValue = eventViewModel.performRainEvent();
        boolean inRange = ((rainValue >= 1) && (rainValue <= 2));
        assertTrue(inRange);
    }

    @Test
    //Test performDroughtEvent method, ensuring that random value being returned is in range
    public void testPerformDroughtEvent() {
        int droughtValue = eventViewModel.performRainEvent();
        boolean inRange = ((droughtValue >= 1) && (droughtValue <= 2));
        assertTrue(inRange);
    }

    @Test
    //Test performLocustEvent method,
    // ensuring that crops without pesticide were occasionally being eaten
    //during the event
    public void testPerformLocustEventNormal() {
        int wasCropEaten = eventViewModel.performLocustEvent(harvestedPlot);
        boolean inRange = ((wasCropEaten >= 0) && (wasCropEaten <= 1));
        assertTrue(inRange);
    }

    @Test
    //Test performLocustEvent method, ensuring that crops with pesticide are never eaten
    public void testPerformLocustEventWithPesticide() {
        plotViewModel.fertilizePlot(harvestedPlot);
        int wasCropEaten = eventViewModel.performLocustEvent(harvestedPlot);
        boolean inRange = (wasCropEaten == 0);
        assertTrue(inRange);
    }
}
