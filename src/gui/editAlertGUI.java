package gui;

import Calender.Alert;
import system.manager.CalenderManagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class editAlertGUI extends JFrame{

    private JTextField newName;
    private JTextField newDate;
    private JButton backButton;
    private JButton doneButton;
    private JPanel contentPanel;
    private JLabel title;
    private JLabel nameText;
    private JLabel dateText;
    private CalenderManagement cm;
    private DateTimeFormatter formatter;
    private ChooseSingleObjectGUI<Alert> chooseObj;

    public editAlertGUI(CalenderManagement cm, DateTimeFormatter formatter){
        this.cm = cm;
        this.formatter = formatter;

        this.setContentPane(contentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        this.chooseObj = new ChooseSingleObjectGUI<Alert>(this, this.cm.calender.getAlerts());


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
                    String trigger = newDate.getText();
                    Alert alert = chooseObj.getObjectChoice();

                    if (trigger.equals("")) {
                        alert.editAlert(name);
                    } else {
                        LocalDateTime date = parseDate(trigger);
                        alert.editAlert(name, date);
                    }


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
