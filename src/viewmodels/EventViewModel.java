package viewmodels;

import models.EventModel;
import models.PlayerModel;
import models.PlotModel;

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
     * Constructor for EventViewModel/
     * @param player the player
     */
    public EventViewModel(PlayerModel player) {
        this.player = player;
        this.plotView = new PlotViewModel(player);
    }

    /**
     * Method to choose a random event
     *
     * @return the index of the chosen event
     */
    public int chooseEvent() {
        Random rand = new Random();
        String difficulty = player.getPlayerSettings().getStartingDifficulty();
        int isThereEvent = rand.nextInt(100);
        int toReturn = rand.nextInt(eventList.getEventList().size());
        switch (difficulty) {
        case "Casual":
            if (isThereEvent > 80) {
                return toReturn;
            }
            break;
        case "Normal":
            if (isThereEvent > 70) {
                return toReturn;
            }
            break;
        case "Veteran":
            if (isThereEvent > 60) {
                return toReturn;
            }
            break;
        default:
            return -1;
        }
        return -1;
    }

    /**
     * Performs a rain Event
     *
     * @return value to increase crop's waterLevel by
     */
    public int performRainEvent() { //if chooseEvent returns 0
        Random rand = new Random();
        return rand.nextInt(2) + 1;
    }

    /**
     * Performs a drought Event
     *
     * @return value to decrease crop's waterLevel by
     */
    public int performDroughtEvent() { //if chooseEvent returns 1
        Random rand = new Random();
        return rand.nextInt(2) + 1;
    }

    /**
     * Performs a locust Event
     *
     * @return binary boolean where 1 means crop was eaten, 0 where crop is fine
     * @param plot the plot
     * @pa
     */
    public int performLocustEvent(PlotModel plot) { //if chooseEvent returns 2
        if (plot.getCropInPlot().getHasPesticide()) {
            return 0;
        }
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
        default:
            return 0;
        }
        return 0;
        // If chooseEvent returns 2,
        // have a variable set to 0 and call this method on each plot,
        // adding the return value to variable.
    }
}