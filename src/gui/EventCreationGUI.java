package gui;

import Calender.*;
import system.manager.CalenderManagement;
import system.manager.ViewManagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class EventCreationGUI extends JFrame{
    private JPanel eventCreationPanel;
    private JButton backButton;
    private JButton doneButton;
    //Redundant text labels
    private JLabel eventNameText;
    private JLabel title;
    private JLabel seriesNameText;
    private JLabel frequencyText;
    private JLabel repeatText;
    private JLabel eventSeriesText;
    private JLabel eventEndText;
    private JLabel eventStartText;
    //Input fields
    private JTextField eventNameInput;
    private JTextField eventStartInput;
    private JTextField eventEndInput;
    private JTextField seriesInput;
    private JTextField seriesNameInput;
    private JTextField frequencyInput;
    private JTextField repeatInput;
    private CalenderManagement cm;
    private ViewManagement vm;
    private DateTimeFormatter formatter;

    /**
     * Constructor for this GUI
     * @param calenderManagement the calender system response for creating the event
     * @param vm the system that will be the observer for the events created
     * @param formatter the date formatter
     */
    public EventCreationGUI(CalenderManagement calenderManagement, ViewManagement vm, DateTimeFormatter formatter){
        super("Creating Events!");
        //These are recieved from View class - things needed to create events and add observers, etc.
        this.cm = calenderManagement;
        this.formatter = formatter;
        this.vm = vm;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(eventCreationPanel);
        this.pack();
        this.setVisible(true);

        //When they click done then run this code!
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get the required information
                    String eventName = eventNameInput.getText();
                    LocalDateTime startDate = parseDate(eventStartInput.getText());
                    LocalDateTime endDate = parseDate(eventEndInput.getText());
                    String isInSeries = seriesInput.getText().toUpperCase();

                    //Check if the event is in series
                    if (isInSeries.equals("N") || isInSeries.equals("")) {
                        Event event = cm.createEvent(eventName, startDate, endDate);
                        event.addObserver(vm);
                        cm.addEvent(event);
                    } else {
                        String seriesName = seriesNameInput.getText();
                        //Get the frequency units and convert them into a chronounit and integer
                        String[] frequency = frequencyInput.getText().split(" ");
                        ChronoUnit unit = parseUnit(frequency[1]);
                        int freq = Integer.valueOf(frequency[0]);
                        int amount = Integer.valueOf(repeatInput.getText());

                        // Create the list of repeating events
                        List<Event> events = cm.createEvent(eventName, seriesName, startDate, endDate, freq,
                                unit, amount);
                        //Add observers
                        for (Event event : events) {
                            event.addObserver(vm);
                            System.out.println(event.countObservers());
                            cm.addEvent(event);
                        }
                    }
                } catch (Exception err){
                    ErrorGUI errorGUI = new ErrorGUI("Please use correct input in the text fields." +
                            " Please adhere to the date format and choose events with proper integers.");
                }
                //Dispose the JFrame
                dispose();
            }
        });

        //Back Button action listener - just dispose the current gui
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    /**
     * Parses the string representation of the date into a LocalDateTime object
     *
     * @param timeStr The string representation
     * @return A LocalDateTime object with the date and time of timeStr
     * @throws DateTimeParseException if the string format is incorrect
     */
    private LocalDateTime parseDate(String timeStr) throws DateTimeParseException {
        return LocalDateTime.parse(timeStr, formatter);
    }

    /**
     * Returns the corresponding ChronoUnit based on input
     *
     * @param unitStr The base unit of time
     * @return Returns a ChronoUnit corresponding to unitStr
     */
    private ChronoUnit parseUnit(String unitStr) {
        unitStr = unitStr.toUpperCase();
        // If the unitStr ends with a 'S' then do nothing otherwise add a 'S' to the end
        unitStr = unitStr.charAt(unitStr.length() - 1) == 'S' ? unitStr : unitStr + "S";
        // Return the corresponding ChronoUnit
        switch (unitStr) {
            case "DAYS":
                return ChronoUnit.DAYS;
            case "WEEKS":
                return ChronoUnit.WEEKS;
            case "HOURS":
                return ChronoUnit.HOURS;
            case "SECONDS":
                return ChronoUnit.SECONDS;
            case "MINUTES":
                return ChronoUnit.MINUTES;
        }
        return ChronoUnit.DAYS;
    }

}
