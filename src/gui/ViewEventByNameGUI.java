package gui;

import Calender.Event;
import system.manager.ViewManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewEventByNameGUI extends JFrame{
    private JPanel contentPanel;
    private JLabel title;
    private JButton okButton;
    private JScrollPane scrollPanel;
    private JLabel display;
    private ChooseObjectNameGUI<Event> chooseObjectNameGUI;
    private List<Event> eventList;
    private ViewManagement vm;

    public ViewEventByNameGUI(ViewManagement vm, List<Event> eventList){
        super("Viewing Events!");
        this.vm = vm;
        this.eventList = eventList;
        this.setContentPane(contentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        //Set the display text
        this.setMinimumSize(new Dimension(650, 450));
        chooseObjectNameGUI = new ChooseObjectNameGUI<Event>(this, eventList);


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void setDisplayText(){
        String displayText = vm.viewAllEventsWithName(chooseObjectNameGUI.getInput());
        display.setText(displayText);
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
        setDisplayText();
    }
}
