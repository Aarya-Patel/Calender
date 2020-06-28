package system.manager;

import Calender.*;
import gui.NotificationGUI;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class CalenderManagement implements Observer, Serializable {
    // Private attributes
    public Calender calender;

    /**
     * Constructor for UseCases.CalenderManagement
     *
     * @param calender The personal Entities.Calender of the Entities.User that is currently logged in
     */
    public CalenderManagement(Calender calender) {
        this.calender = calender;
        addingObserversAfterSerializing();
    }


    /**
     * Readd the observer after deserialize
     */
    private void addingObserversAfterSerializing(){
        for(Alert a: calender.getAlerts()){
            a.addObserver(this);
        }
    }

    /**
     * Sets this calender to another calender
     *
     * @param calender The new Entities.Calender object
     */
    public void setCalender(Calender calender) {
        this.calender = calender;
    }

    public Event createEvent(String name, LocalDateTime start, LocalDateTime end) {
        return new Event(name, start, end);
    }

    /**
     *
     * @param name name of this event
     * @param seriesName name of the series these events are in
     * @param start start time of the first event
     * @param end end time of the first event
     * @param frequency the number part of "every 3 days" (3)
     * @param chronoUnit the unit part of "every 3 days" (days)
     * @param amount the number of time does the event repeats
     * @return a list of Event just created
     */
    public List<Event> createEvent(String name, String seriesName, LocalDateTime start, LocalDateTime end,
                                   int frequency, ChronoUnit chronoUnit, int amount) {
        List<Event> e = new ArrayList<Event>();

        for (int i = 0; i < amount; i++) {
            // Create the new event based on the previous one
            LocalDateTime newStart = start.plus(frequency * i, chronoUnit);
            LocalDateTime newEnd = end.plus(frequency * i, chronoUnit);
            e.add(new Event(name, seriesName, newStart, newEnd));

        }
        return e;
    }

    /**
     * Creating a single individual event.
     */
    public void addEvent(Event event) {
        //Add the events
        calender.addEvent(event);
    }

    public Alert createAlert(String name, LocalDateTime time) {
        Alert alert = new IndividualAlert(name, time);
        calender.addAlert(alert);

        return alert;
    }

    /**
     *
     * @param name name of this alert
     * @param time start time of this FrequencyAlert
     * @param frequency the number part of "every 3 days" (3)
     * @param chronoUnit the unit part of "every 3 days" (days)
     * @param amount the number of time does the event repeats
     * @return corresponding FrequencyAlert
     */
    public Alert createAlert(String name, LocalDateTime time, int frequency, ChronoUnit chronoUnit, int amount) {
        FrequencyAlert alert = new FrequencyAlert(name, time, frequency, chronoUnit, amount);
        calender.addAlert(alert);

        return alert;
    }

    /**
     * add an Entities.IndividualAlert
     *
     * @param alert The alert to add
     */
    public void addAlert(Event event, Alert alert) throws DateTimeParseException {
        event.addAlert(alert);
        alert.addObserver(this);
    }

    /**
     * Add a list of events into a series
     *
     * @param events     The list of events to be created into a series
     * @param seriesName The series name
     */
    public void linkEventsToSeries(List<Event> events, String seriesName) {
        for (Event e : events) {
            e.addEventToSeries(seriesName);
        }
    }


    public Tag createTag(String tagName) {
        Tag tag = new Tag(tagName);
        calender.addTag(tag);

        return tag;
    }

    /**
     * Adds a Entities.Tag to the specified Entities.Event
     *
     * @param event The chosen Entities.Event
     * @param tag   The tag to add
     */
    public void addTag(Event event, Tag tag) {
        //Add the tag to the event and the event to the tag
        event.addTag(tag);
        tag.addEvent(event);
    }

    public Memo createMemo(String memoName, String content) {
        Memo memo = new Memo(memoName, content);
        calender.addMemo(memo);
        return memo;
    }

    /**
     * Add a memo
     *
     * @param event the event to add memo
     * @param memo the momo to add Event
     */
    public void addMemo(Event event, Memo memo) {
        //Add the memo to the event and the event to the memo
        event.addMemo(memo);
        memo.addEvent(event);
    }

    /**
     * Delete this alert from ALL events
     *
     * @param alert the alert to be deleted
     */
    public void removeItem(Alert alert) {
        for (Event e : this.calender.getEvents()) {
            e.getAlerts().remove(alert);
        }
        //Remove it from the alert to display
        calender.getAlerts().remove(alert);
    }

    /**
     *
     * @param o the alert when it's fired for the last time, and needs to be removed
     * @param arg currently not in use, only for the purpose of implementing Observer interface
     */
    @Override
    public void update(Observable o, Object arg) {
//        System.out.println("Alert Fired: " + o);
        NotificationGUI notificationGUI = new NotificationGUI(o);
        try{
            FrequencyAlert frequencyAlert = (FrequencyAlert)o;
            if(frequencyAlert.freqAmount() == 0){
                this.removeItem((Alert) o);
            } else {
                return;
            }
        } catch (Exception e){
        }
        this.removeItem((Alert) o);

    }

    /**
     * Edit the info of Memo memo
     * @param memo the memo to be edited
     * @param newName the new name of the memo
     * @param newContent the new content of the memo
     */
    public void editMemo(Memo memo, String newName, String newContent){
        memo.editMemo(newName, newContent);
    }

    public void removeItem(Memo memo){
        this.calender.getMemos().remove(memo);
    }

    public void editAlert(Alert alert, String newName, LocalDateTime newDate){alert.editAlert(newName, newDate);}

    public void setEventDate(Event event, LocalDateTime startDate, LocalDateTime endDate){
        event.setDate(startDate, endDate);
    }

    public void setEventName(Event event, String newName){
        event.setEventName(newName);
    }

    public void removeItem(Event event){
        this.calender.getEvents().remove(event);
        this.calender.getPastEvent().remove(event);
    }

}
