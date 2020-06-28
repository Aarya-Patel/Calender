package gui;

import Calender.Event;
import system.manager.ViewManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewEventsBySeries extends JFrame{
    private JLabel title;
    private JButton okButton;
    private JLabel display;
    private JScrollPane scrollPanel;
    private JPanel contentPanel;
    private ViewManagement vm;
    private List<Event> eventList;
    private ChooseObjectNameGUI<Event> chooseObjectNameGUI;


    public ViewEventsBySeries(ViewManagement vm, List<Event> eventList){
        super("Viewing Events by Series!");

        this.setContentPane(contentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.vm = vm;
        this.eventList = eventList;

        this.chooseObjectNameGUI = new ChooseObjectNameGUI<Event>(this, eventList);
        this.setMinimumSize(new Dimension(650, 450));
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void setDisplayText(){
        String temp = this.vm.viewEventsInSeries(chooseObjectNameGUI.getInput());
        display.setText(temp);
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
        setDisplayText();
    }
}
