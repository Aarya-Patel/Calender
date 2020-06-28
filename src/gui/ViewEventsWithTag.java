package gui;

import Calender.*;
import system.manager.ViewManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewEventsWithTag extends JFrame{
    private JLabel title;
    private JPanel contentPanel;
    private JButton okButton;
    private JScrollPane scrollPanel;
    private JLabel display;
    private ViewManagement vm;
    private List<Tag> tagList;
    private ChooseObjectNameGUI<Tag> chooseObjectNameGUI;

    public ViewEventsWithTag(ViewManagement vm, List<Tag> tagList){
        super("Viewing Events by Tag!");

        this.setContentPane(contentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setMinimumSize(new Dimension(650, 450));
        this.vm = vm;
        this.tagList = tagList;
        this.chooseObjectNameGUI = new ChooseObjectNameGUI<Tag>(this, tagList);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void setDisplayText(){
        String text = vm.viewTaggedEvents(chooseObjectNameGUI.getInput());
        display.setText(text);
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
        setDisplayText();
    }
}
