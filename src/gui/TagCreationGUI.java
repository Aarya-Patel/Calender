package gui;

import Calender.*;
import system.manager.CalenderManagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TagCreationGUI extends JFrame{
    private JPanel contentPanel;
    private JTextField tagNameInput;
    private JLabel title;
    private JLabel tagNameText;
    private JButton backButton;
    private JButton doneButton;
    private CalenderManagement cm;
    private List<Event> eventList;
    private ChooseGUI<Event> chooseGUI;

    /**
     * Constructor for this class
     * @param cm the calender manager needed to create the tag
     * @param eventList the event list that this tag can be added to
     */
    public TagCreationGUI(CalenderManagement cm, List<Event> eventList){
        super("Creating Tags!");
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
                //Create the tag
                String tagName = tagNameInput.getText();

                Tag tag = cm.createTag(tagName);

                //Get the events from the chooseGUI and then add the tag to those events
                List<Event> events = chooseGUI.getObjectChoices();
                for(Event event: events){
                    cm.addTag(event, tag);
                }

                //Finally dispose of the frame
                dispose();
            }
        });
    }
}