package Calender;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

/**
 * Entities.Memo Class
 */
public class Memo implements Serializable {

    protected String name;
    protected String memoContent;
    //This eventList is used to store the association between this Entities.Memo and Entities.Event objects
    protected List<Event> eventList;


    /**
     * Constructor of the Entities.Memo Class
     * @param name Name of this Entities.Memo, used in searching
     */
    public Memo(String name, String memoContent){
        this.memoContent = memoContent;
        this.name =  name;
        this.eventList = new ArrayList<Event>();
    }

    /**
     * Add a single event to the eventList to build up the association
     * @param event The event that this memo associate to
     */
    public void addEvent(Event event){
        this.eventList.add(event);
    }

    /**
     * Add a list of events to the eventList to build up the association
     * @param events The list of event that this memo associate to
     */
    public void addEvent(Collection<Event> events){
        this.eventList.addAll(events);
    }

    /**
     * Get the name of this Entities.Memo
     * @return The name of this Entities.Memo
     */
    public String getName(){
        return this.name;
    }

    /**
     * Get the content of this Entities.Memo
     * @return The content of this Entities.Memo
     */
    public String getContent(){
        return this.memoContent;
    }

    /**
     * Edit the content of this Entities.Memo
     * @param content New content of this Entities.Memo
     */
    public void setContent(String content){
        this.memoContent = content;
    }

    /**
     * Edit the name of this Entities.Memo
     * @param name New name of this Entities.Memo
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Return the string representation of the content of the Entities.Memo
     * @return The string representation of the Entities.Memo
     */
    public String toString(){
        return "Content on " + this.name + ": " + this.memoContent;
    }

    /**
     * Return all the Events that is associated to this memo
     * @return The eventList that stores all the events associated to this memo
     */
    public List<Event> getEventList(){
        return this.eventList;
    }

    /**
     * Edit info of this Memo
     * @param newName new name of this memo
     * @param newContent new content of this memo
     */
    public void editMemo(String newName, String newContent) {
        if (!newName.equals("")) {
            this.name = newName;
        }
        if (!newContent.equals("")) {
            this.memoContent = newContent;
        }
    }
}
