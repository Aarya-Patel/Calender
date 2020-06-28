package Calender;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

/**
 *  Entities.Tag Class
 */
public class Tag implements Serializable {
    protected String tagName;
    // List of Entities.Event tagged by this tag
    protected  List<Event> eventList;

    /**
     * Constructor of the Entities.Tag Class
     * @param name Name of this Entities.Tag
     */
    public Tag(String name){
        this.tagName = name;
        this.eventList = new ArrayList<Event>();
    }

    /**
     * Add a single event to the eventList to build up the relationship
     * @param event The event that this is tagged by this Entities.Tag
     */
    public void addEvent(Event event){
        this.eventList.add(event);
    }

    /**
     * Add a list of events to the eventList to build up the relationship
     * @param events The list of event that is tagged by this Entities.Tag
     */
    public void addEvent(Collection<Event> events){
        this.eventList.addAll(events);
    }

    /**
     * Return the name of this tag
     * @return The name of this tag
     */
    public String getName(){
        return this.tagName;
    }

    /**
     * Edit the name of this tag
     * @param name New name of this tag
     */
    public void setName(String name){
        this.tagName = name;
    }

    /**
     * Return all the Events that is tagged by this Entities.Memo
     * @return The eventList that stores all the events tagged by this tag
     */
    public List<Event> getEventList(){
        return this.eventList;
    }

    public String toString(){
        return "Tag Name: " + this.tagName;
    }

}
