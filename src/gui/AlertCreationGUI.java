package gui;

import Calender.*;
import system.manager.CalenderManagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AlertCreationGUI extends JFrame{

    private JPanel contentPanel;
    private JLabel alertNameText;
    private JTextField alertNameInput;
    private JLabel startTimeText;
    private JTextField startTimeInput;
    private JTextField repeatingInput;
    private JTextField frequencyInput;
    private JTextField amountInput;
    private JLabel repeatingText;
    private JLabel frequencyText;
    private JLabel amountText;
    private JButton backButton;
    private JButton doneButton;
    private ChooseGUI<Event> chooseEventGUI;
    private CalenderManagement cm;
    private DateTimeFormatter formatter;
    private List<Event> events;

    public AlertCreationGUI(CalenderManagement cm, List<Event> events, DateTimeFormatter formatter){
        super("Alert Creation!");
        this.cm = cm;
        this.events = events;
        this.formatter = formatter;

        this.setContentPane(contentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();


        //Create the ChooseEventGUI and wait for that to return
        this.chooseEventGUI = new ChooseGUI<Event>(this, events);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //Create the alert
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    //Basic Information
                    String alertName = alertNameInput.getText();
                    LocalDateTime startDate = parseDate(startTimeInput.getText());
                    String isRepeating = repeatingInput.getText().toUpperCase();

                    // Create the Alert
                    Alert alert;
                    if(isRepeating.equals("N") || isRepeating.equals("")){
                        alert = cm.createAlert(alertName, startDate);
                    } else {
                        String[] frequency = frequencyInput.getText().split(" ");
                        ChronoUnit unit = parseUnit(frequency[1]);
                        int freq = Integer.valueOf(frequency[0]);
                        int amount = Integer.valueOf(amountInput.getText());

                        alert = cm.createAlert(alertName, startDate, freq, unit, amount);
                    }

                    //Add the selected alert to the selected events
                    List<Event> event = chooseEventGUI.getObjectChoices();

                    for(Event eve: event){
                        cm.addAlert(eve, alert);
                    }

                } catch (Exception err){
                    ErrorGUI errorGUI = new ErrorGUI("Please put correct input in the text fields." +
                            " Please adhere to the date format and choose events with proper integers.");
                }
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
