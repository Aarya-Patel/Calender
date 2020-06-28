package Calender;

import gui.NotificationGUI;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FrequencyAlert extends Alert implements Serializable {
    //Private Attributes
    protected int frequency;
    protected ChronoUnit freqUnit;
    protected int amount;

    /**
     * Constructor for the Entities.FrequencyAlert
     *
     * @param name      The name of the Entities.Alert
     * @param startDate The start date of the alert
     * @param freqUnit  The frequency of the alert in ChronoUnit(i.e. DAYS, WEEKS, MONTHS, etc.)
     * @param amount    The number of times this alert will fire
     */
    public FrequencyAlert(String name, LocalDateTime startDate, int frequency, ChronoUnit freqUnit, int amount) {
        super(name, startDate);
        this.frequency = frequency;
        this.freqUnit = freqUnit;
        this.amount = amount;
    }

    /**
     * Gets the frequency type of this alert
     *
     * @return The ChronoUnit describing the frequency type
     */
    public ChronoUnit getFreqType() {
        return freqUnit;
    }

    /**
     * Gets the number of times this alert will fire again
     *
     * @return The number of times the alert will fire
     */
    public int freqAmount() {
        return amount;
    }


    /**
     * This notifies the observers and also updates its own date according to the number
     * of days(amount) left
     */
    @Override
    public void fireAlert(LocalDateTime currentTime) {
        // Decrease the number of alerts left
        this.amount -= 1;
        super.fireAlert(currentTime);

        // Once the amount hits zero then notify observer which is the calendermanager to delete this alert
        if (this.amount != 0) {
            this.setStartDate(this.getStartDate().plus(this.frequency, this.freqUnit));

            //Recursively fire the alert until the dates is after the current time
            if (this.getStartDate().isBefore(currentTime)) {
                this.fireAlert(currentTime);
            }
        }
    }

    /**
     * The toString method for this class
     *
     * @return The string representation of the frequency alert
     */
    public String toString() {
        return super.toString() + String.format(", Freq. Every %d %s, Amount: %d",
                frequency, freqUnit.toString(), amount);
    }
}