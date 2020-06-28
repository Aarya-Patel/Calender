package Calender;

import Calender.Calender;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Observable;

public abstract class Alert extends Observable implements Serializable {
    protected String name;
    protected LocalDateTime startDate;

    /**
     * The constructor for the Entities.Alert class
     *
     * @param name      The name of the Entities.Alert
     * @param startDate The startDate for the Entities.Alert
     */
    public Alert(String name, LocalDateTime startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    /**
     * Get this alert's name
     *
     * @return The name of this alert
     */
    public String getName() {
        return name;
    }


    /**
     * Get the start date of this alert
     *
     * @return The start date of this alert
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Setter for startDate
     *
     * @param newStartDate The new startDate of the alert
     */
    public void setStartDate(LocalDateTime newStartDate) {
        this.startDate = newStartDate;
    }

    /**
     * This fires the alert and notifies its observer
     */
    public void fireAlert(LocalDateTime time) {
        // Notify observers
        setChanged();
        notifyObservers();
    }

    /**
     * Return a string version of this alert
     *
     * @return The string representation of this alert
     */
    public String toString() {
        return "Alert Name: " + this.name + ", Start Date: " + this.startDate;
    }


    /**
     * Edit the info of this Alert
     *
     * @param newName new name of this alert
     * @param newDate new trigger date of this alert
     */
    public void editAlert(String newName, LocalDateTime newDate) {
        if (!newName.equals("")) {
            this.name = newName;
        }
        this.startDate = newDate;
    }

    public void editAlert(String newName) {
        if (!newName.equals("")) {
            this.name = newName;
        }
    }
}
