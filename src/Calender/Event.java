package Calender;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Event Class
 */
public class Event extends Observable implements Serializable {
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;
    protected String eventName;
    protected String seriesName;
    protected boolean inSeries;
    // The reason for storing alerts wihtin the Event is because when you delete
    // an event all the alerts in the event will also be deleted! <- Quick and logical
    protected List<Alert> alerts;
    // The Tags are stored as a list as a single event can be tagged by multiple tags
    // also it is easier to loop through it and do all kinds of searching
    protected List<Tag> tagList;
    // Entities.Memo is not in a list since project phase 1 specified that each event can only have
    // have at most 1 Entities.Memo attached to each Entities.Event. Multiple Memos can be a possible extension
    protected Memo memo;

    /**
     * Constructor for the Event class when created individually
     */
    public Event(String eventName, LocalDateTime startDate, LocalDateTime endDate) {
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.alerts = new ArrayList<Alert>();
        this.inSeries = false;
        this.tagList = new ArrayList<Tag>();
        this.memo = null;
    }

    /**
     * Constructor for the Event class when created is a series
     */
    public Event(String eventName, String seriesName, LocalDateTime startDate, LocalDateTime endDate) {
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.inSeries = true;
        this.alerts = new ArrayList<Alert>();
        this.tagList = new ArrayList<Tag>();
        this.seriesName = seriesName;
    }

    /**
     * This method will add this an individual event to a series
     */
    public void addEventToSeries(String seriesName) {
        this.seriesName = seriesName;
        this.inSeries = true;
    }

    /**
     * Return the list of alerts associated with this event
     */
    public List<Alert> getAlerts() {
        return this.alerts;
    }

    /**
     * Check if this event is in a series
     */
    public boolean isInSeries() {
        return this.inSeries;
    }

    /**
     * Get the name of the event
     */
    public String getEventName() {
        return this.eventName;
    }

    /**
     * Get the start date for this event
     */
    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    /**
     * Get the name of the series this Event is in
     */
    public String getSeriesName() {
        return this.seriesName;
    }

    /**
     * Add a single new Entities.Tag to this event
     *
     * @param newTag The new tag to be added
     */
    public void addTag(Tag newTag) {
        this.tagList.add(newTag);
    }

    /**
     * Gets the tag list of this event
     *
     * @return gets the tag list
     */
    public List<Tag> getTagList() {
        return this.tagList;
    }

    /**
     * Set the Entities.Memo of this event
     *
     * @param memo The memo to be associated to this event
     */
    public void addMemo(Memo memo) {
        this.memo = memo;
    }

    /**
     * Adds the alert to this event's list of alerts
     * @param alert The alert to be added
     */
    public void addAlert(Alert alert) {
        alerts.add(alert);
    }

    /**
     * Return the memo of this event
     */
    public Memo getMemo() {
        return this.memo;
    }

    /**
     * String representation for this event
     *
     * @return String representation of this Event
     */
    public String toString() {
        if (!this.inSeries) {
            return "Event Name: " + this.eventName + ", Starts: " + startDate + ", Ends: " + endDate;
        } else {
            return "Event Name: " + this.eventName + ", Series Name: " + this.seriesName + ", Starts: " + startDate + ", Ends: " + endDate;
        }
    }

    /**
     * Notify the observers
     */
    public void fireEvent() {
        setChanged();
        notifyObservers();
    }

    public void removeMemo(){
        this.memo = null;
    }

    public void removeAlert(Alert alert){ this.alerts.remove(alert);}

    public void setEventName(String newName){
        if(!newName.equals("")){
            this.eventName = newName;
        }
    }

    public void setDate(LocalDateTime startDate, LocalDateTime endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }




}
