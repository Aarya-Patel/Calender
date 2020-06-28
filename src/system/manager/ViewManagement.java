package system.manager;

import Calender.*;
import gui.NotificationGUI;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ViewManagement implements Observer {
    protected Calender calender;
    protected List<Event> eventsList;
    protected List<Tag> tagsList;
    protected List<Memo> memoList;
    protected List<Alert> alertList;
    protected List<Event> pastEvents;


    /**
     * Constructor for the UseCases.ViewManagement system
     *
     * @param calender the Entities.User's personal calender
     */
    public ViewManagement(Calender calender) {
        this.calender = calender;
        this.eventsList = calender.getEvents();
        this.memoList = calender.getMemos();
        this.tagsList = calender.getTags();
        this.alertList = calender.getAlerts();
        this.pastEvents = calender.getPastEvent();

        addingObserversAfterSerializing();
    }

    private void addingObserversAfterSerializing(){
        for(Event e: eventsList){
            e.addObserver(this);
        }
    }

    public List<Event> getPastEvents() {
        return this.pastEvents;
    }

    public List<Alert> getAlertList() {
        return this.alertList;
    }

    public List<Event> getEventsList() {
        return this.eventsList;
    }

    public List<Tag> getTagsList() {
        return this.tagsList;
    }

    public List<Memo> getMemoList() {
        return this.memoList;
    }

    /**
     * Prints out all the events in the calender with this event name
     *
     * @return the string representation of all the events in this calender
     */
    public String viewAllEventsWithName(String eventName) {
        String str = "";
        for (Event e : eventsList) {
            if (e.getEventName().equals(eventName)) {
                str += e+"<br/>";
            }
        }
        return "<html>"+str+"</html>";
    }


    /**
     * Print out all the events in the calender with this tag
     *
     * @param tag the tag required for search
     */
    public String viewTaggedEvents(String tag) {
        String str = "";
        for (Event e : eventsList) {
            for (Tag t : e.getTagList()) {
                if (t.getName().equals(tag)) {
                    str+=e + "<br/>";
                }
            }
        }
        return "<html>"+str+"</html>";
    }


    /**
     * Print out all the events that have this memo
     *
     * @param memo the memo you want to search with
     */
    public String viewMemoEvents(String memo) {
        String str ="";

        for (Event e : eventsList) {
            Memo temp = e.getMemo();
            //Check both the memo name and content
            if (temp != null && temp.getName().equals(memo) ) {
                str += e+"<br/>"+"<pre>\t"+e.getMemo()+"</pre>";
            }
        }
        return "<html>"+str+"</html>";
    }


    /**
     * Presenter.View all events in series
     *
     * @param seriesName The name of the series you wish to view
     */
    public String viewEventsInSeries(String seriesName) {
        String str = "";
        for (Event e : eventsList) {
            if (e.isInSeries() && e.getSeriesName().equals(seriesName)) {
                str += e+"<br/>";
            }
        }
        return "<html>"+str+"</html>";
    }

    /**
     * Prints out all the events in the calender
     *
     * @return the string representation of all the events
     */
    public String viewFutureEvents() {
        String str = "";
        for (Event e : eventsList) {
            str += e + "<br/>";
        }
        return "<html>" + str + "</html>";
    }

    public String viewPastEvents() {
        String str = "";
        for (Event e : pastEvents) {
            str += e + "<br/>";
        }
        return "<html>" + str + "</html>";
    }

    /**
     * Prints out all the alerts that are stored within the events
     */
    public String viewAllAlerts() {
        String str = "";
        for (Alert a : alertList) {
            str += a + "<br/>";
        }
        return "<html>" + str + "</html>";
    }

    /**
     * The update method that fires when its observable notifies this class
     *
     * @param o   the observable
     * @param arg currently not in use, only for the purpose of implementing Observer interface
     */
    @Override
    public void update(Observable o, Object arg) {
        NotificationGUI notificationGUI = new NotificationGUI(o);
        //Remove the event and add it to the past events
        this.eventsList.remove(o);
        this.pastEvents.add((Event) o);
    }
}
