package gui;

import system.manager.CalenderManagement;
import Calender.Event;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class editEventGUI extends JFrame{
    private JTextField newName;
    private JTextField newStart;
    private JTextField newEnd;
    private JButton backButton;
    private JButton doneButton;
    private JPanel contentPanel;
    private JLabel title;
    private JLabel nameText;
    private JLabel startText;
    private JLabel endText;
    private CalenderManagement cm;
    private ChooseSingleObjectGUI chooseObj;
    private List<Event> events;
    private DateTimeFormatter formatter;

    public editEventGUI (CalenderManagement cm, DateTimeFormatter formatter){
        this.cm = cm;
        this.events = new ArrayList<Event>();
        this.formatter = formatter;

        events.addAll(this.cm.calender.getEvents());
        events.addAll(this.cm.calender.getPastEvent());

        this.setContentPane(contentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        this.chooseObj = new ChooseSingleObjectGUI<Event>(this, events);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = newName.getText();
                    String start = newStart.getText();
                    String end = newEnd.getText();
                    Event target = (Event) chooseObj.getObjectChoice();

                    LocalDateTime startDate = parseDate(start);
                    LocalDateTime endDate = parseDate(end);
                    cm.setEventName(target, name);
                    cm.setEventDate(target, startDate, endDate);
                } catch (DateTimeParseException i){
                    ErrorGUI errorGUI = new ErrorGUI("Please put correct input in the text fields.");
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
}
