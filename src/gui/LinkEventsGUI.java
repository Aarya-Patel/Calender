package gui;

import Calender.*;
import system.manager.CalenderManagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LinkEventsGUI extends JFrame{
    private JPanel contentPanel;
    private JLabel title;
    private JLabel linkText;
    private JTextField seriesNameInput;
    private JButton doneButton;
    private JButton backButton;
    private ChooseGUI<Event> chooseGUI;
    private List<Event> eventList;
    private CalenderManagement cm;


    public LinkEventsGUI(CalenderManagement cm, List<Event> eventList){
        super("Linking Events into a Series!");

        this.cm = cm;
        this.eventList = eventList;
        this.setContentPane(contentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        this.chooseGUI = new ChooseGUI<Event>(this, eventList);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seriesName = seriesNameInput.getText();
                if(!seriesName.equals("")){
                    List<Event> events = chooseGUI.getObjectChoices();
                    cm.linkEventsToSeries(events, seriesName);
                }
                dispose();
            }
        });


    }
}
