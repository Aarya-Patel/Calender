package Calender;


import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Calender implements Serializable {
    // List of Events
    protected List<Event> events;
    protected List<Memo> memos;
    protected List<Event> pastEvent;
    protected List<Tag> tags;
    protected List<Alert> alerts;
    protected String name = "";
    protected static final long serialVersionUID = 5998445582702318745L;



    /**
     * The constructor for the calender class
     */
    public Calender(String name) {
        this.name = name;
        events = new ArrayList<Event>();
        memos = new ArrayList<Memo>();
        tags = new ArrayList<Tag>();
        pastEvent = new ArrayList<Event>();
        alerts = new ArrayList<Alert>();
    }

    public String getName() {
        return name;
    }

    public List<Event> getPastEvent() {
        return this.pastEvent;
    }

    public void addAlert(Alert alert) {
        this.alerts.add(alert);
    }

    public List<Alert> getAlerts() {
        return this.alerts;
    }

    /**
     * Adds the event to the list
     *
     * @param event the event to be added
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Add the memo to the big list of memos
     *
     * @param m
     */
    public void addMemo(Memo m) {
        this.memos.add(m);
    }

    /**
     * Add the tag to the big list of tags
     *
     * @param t
     */
    public void addTag(Tag t) {
        this.tags.add(t);
    }

    /**
     * Gets the list of events
     *
     * @return the list of events
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Returns the list of memos
     *
     * @return
     */
    public List<Memo> getMemos() {
        return this.memos;
    }

    /**
     * Returns the list of tags
     *
     * @return the list of tags
     */
    public List<Tag> getTags() {
        return this.tags;
    }

    public void removeMemo(Memo memo){
        if(this.memos.remove(memo)){
            for(Event e: memo.getEventList()){
                e.removeMemo();
            }
        }
    }

    public String toString(){
        return "Calender Name: "+ name;
    }

}
