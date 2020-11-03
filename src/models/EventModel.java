package models;

import java.util.ArrayList;

/**
 * The EventModel class represents the Event class of the Farm
 * holding the instance variables and associating methods.
 *
 * @author Andrew Novokshanov
 * @version 1.0
 */
public class EventModel {
    private ArrayList<String> eventList;

  /**
     * Constructs an EventModel object.
     */
    public EventModel() {
        this.eventList = new ArrayList<>();
        eventList.add("Rain");
        eventList.add("Drought");
        eventList.add("Locusts");
    }

    /**
     * @return Gets and returns the list of events in a EventModel object.
     */
    public ArrayList<String> getEventList() {
        return this.eventList;
    }

    /**
     * Sets the type of crop in a PlotModel object.
     *
     * @param eventList is the new desired list of events of an EventModel object.
     */
    public void setEventList(ArrayList<String> eventList) {
        this.eventList = eventList;
    }

}
