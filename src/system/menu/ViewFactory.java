package system.menu;

import Calender.*;
import gui.*;
import system.manager.CalenderManagement;
import system.manager.ViewManagement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ViewFactory {
    private CalenderManagement cm;
    private ViewManagement vm;
    private Calender calender;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


    // To be created by Presenter.ViewBuilder
    public ViewFactory() {
    }

    public void setCalender(Calender calender) {
        this.calender = calender;
    }

    public void setCalenderManagement(CalenderManagement cm) {
        this.cm = cm;
    }

    public void setViewManagement(ViewManagement vm) {
        this.vm = vm;
    }

    //cli interface for creating both single event and repeating event in series
    public void userAddEvent() {
        EventCreationGUI eventCreationGUI = new EventCreationGUI(this.cm, this.vm, this.formatter);
    }

    //cli interface for creating both Entities.IndividualAlert and Entities.FrequencyAlert
    public void userAddAlert() {
        AlertCreationGUI alertCreationGUI = new AlertCreationGUI(cm, calender.getEvents(), formatter);
    }


    //cli interface for linking adding event to series
    public void userLinkEventsToSeries() {
        LinkEventsGUI linkEventsGUI = new LinkEventsGUI(cm, calender.getEvents());
    }

    /**
     * Gets the user to add tags to events
     */
    public void userAddTag() {
        TagCreationGUI tagCreationGUI = new TagCreationGUI(cm, calender.getEvents());
    }


    /**
     * The user interface to get user to input memos
     */
    public void userAddMemo() {
        MemoCreationGUI memoCreationGUI = new MemoCreationGUI(cm, calender.getEvents());
    }

    /**
     * Creates the GUI to print out all the future events in the calender
     */
    public void viewFutureEvents() {
        ViewAllGUI viewAllGUI = new ViewAllGUI("Viewing all Future Events!", vm, vm.viewFutureEvents());
    }


    public void viewPastEvents() {
        ViewAllGUI viewAllGUI = new ViewAllGUI("Viewing all Past Events!", vm, vm.viewPastEvents());
    }

    /**
     * Prints out all the alerts that are stored within the events
     */
    public void viewAllAlerts() {
        ViewAllGUI viewAllGUI = new ViewAllGUI("Viewing all Alerts!", vm, vm.viewAllAlerts());
    }

    /**
     * Get the user input to display events using the events' name
     */
    public void displayUserInputEventsByName() {
        ViewEventByNameGUI viewEventByNameGUI = new ViewEventByNameGUI(vm, calender.getEvents());
    }

    /**
     * Gets the user input to find the tag and have then display the events with that ag
     */
    public void displayUserInputEventsWithTag() {
        ViewEventsWithTag viewEventsWithTag = new ViewEventsWithTag(vm, calender.getTags());
    }


    /**
     * Gets the user input to find the memos and have then display the events with that memo
     */
    public void displayUserInputEventsWithMemo() {
        ViewEventsByMemo viewEventsByMemo = new ViewEventsByMemo(vm, calender.getMemos());
    }

    /**
     * Gets user input to display the series
     */
    public void displayUserInputEventsInSeries() {
        ViewEventsBySeries viewEventsBySeries = new ViewEventsBySeries(vm, calender.getEvents());
    }

    /**
     * This method will update all the events and alerts that need to be fired
     *
     * @param currentTime The current time of the system
     */
    public void updateAll(LocalDateTime currentTime) {
        List<Event> events = (List<Event>) ((ArrayList) this.vm.getEventsList()).clone();
        List<Alert> alerts = (List<Alert>) ((ArrayList) this.vm.getAlertList()).clone();
        for (Event e : events) {
            if (e.getStartDate().isBefore(currentTime)) {
                e.fireEvent();
            }
        }

        for (Alert a : alerts) {
            if (a.getStartDate().isBefore(currentTime)) {
                a.fireAlert(currentTime);
            }
        }
    }

    /**
     * Edit the info(Name, Content) of the memo
     */

    public void userEditMemo() {
            editMemoGUI editMemo = new editMemoGUI(cm);
        }


    /**
     * Remove the memo selected by the user
     */
    public void removeMemo() {
        removeItemGUI<Memo> removeMemo = new removeItemGUI<Memo>(this.cm, "Memo");
    }

    //A helper method used to display all the memo
    private void displayMemo() {
        for (int i = 0; i < this.vm.getMemoList().size(); i++) {
            System.out.println(i + ") " + this.vm.getMemoList().get(i));
        }
    }

    /**
     * Edit the info of the selected Alert
     */
    public void userEditAlert() {
        editAlertGUI editAlert = new editAlertGUI(this.cm, formatter);

    }

    /**
     * Remove the selected Alert
     */
    public void removeAlert() {
        removeItemGUI<Alert> removeAlert = new removeItemGUI<Alert>(this.cm, "Alert");
    }

    //A helper method that display all the Alert options
    private void displayAlert() {
        for (int i = 0; i < this.vm.getAlertList().size(); i++) {
            System.out.println(i + ") " + this.vm.getAlertList().get(i));
        }
    }


    public void userEditEvent() {
        editEventGUI editEvent = new editEventGUI(this.cm, formatter);
    }

    public void userRemoveEvent() {
        removeItemGUI<Event> removeEvent = new removeItemGUI<Event>(this.cm, "Event");
    }



}
