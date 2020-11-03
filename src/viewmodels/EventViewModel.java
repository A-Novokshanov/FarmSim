package viewmodels;

import models.CropModel;
import models.EventModel;
import models.PlayerModel;
import models.PlotModel;
import services.player.PlayerPlotService;
import services.player.PlayerSettingsService;

import java.util.List;
import java.util.Random;

/**
 * This view-model class controls the logic and flow of events occuring in the farm.
 *
 * @author Andrew Novokshanov
 * @version 1.0
 */
public class EventViewModel {

    private PlayerModel player;
    private PlotViewModel plotView;
    private EventModel eventList = new EventModel();

    /**
     * Constructor for plot view model/
     */
    public EventViewModel(PlayerModel player) {
        this.player = player;
        this.plotView = new PlotViewModel(player);
    }

    /**
     * Method to choose a random event
     * @return the index of the chosen event
     */
    public int chooseEvent() {
        Random rand = new Random();
        int toReturn = rand.nextInt(eventList.getEventList().size());
        return toReturn;
    }

    /** Performs a rain Event
     * @return value to increase crop's waterLevel by
     */
    public int performRainEvent() { //if chooseEvent returns 0
        Random rand = new Random();
        int waterIncrease = rand.nextInt(2) + 1;
        return waterIncrease; // perform PlotViewModel's setWaterValue with (plot.getWaterValue + waterIncrease)
        }

    /** Performs a drought Event
     * @return value to decrease crop's waterLevel by
     */
    public int performDroughtEvent() { //if chooseEvent returns 1
        Random rand = new Random();
        int waterDecrease = rand.nextInt(2) + 1;
        return waterDecrease; // perform PlotViewModel's setWaterValue with (plot.getWaterValue - waterIncrease)
    }

    /** Performs a drought Event
     * @return binary boolean where 1 means crop was eaten, 0 where crop is fine
     */
    public int performLocustEvent(PlotModel plot) { //if chooseEvent returns 2
        Random rand = new Random();
        int locustHunger = rand.nextInt(11);
        String difficulty = player.getPlayerSettings().getStartingDifficulty();
        switch (difficulty) {
            case "Casual":
                if (locustHunger > 8) {
                    plot.setCropInPlot(null);
                    return 1;
                }
                break;
            case "Normal":
                if (locustHunger > 6) {
                    plot.setCropInPlot(null);
                    return 1;
                }
                break;
            case "Veteran":
                if (locustHunger > 4) {
                    plot.setCropInPlot(null);
                    return 1;
                }
                break;
        }
        return 0; // If chooseEvent returns 2, have a variable set to 0 and call this method on each plot,
                  // adding the return value to variable.
    }
}