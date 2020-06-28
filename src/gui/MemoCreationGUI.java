package gui;

import Calender.*;
import system.manager.CalenderManagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MemoCreationGUI extends JFrame {

    private JPanel contentPanel;
    private JLabel title;
    private JLabel memoTitleText;
    private JTextField memoNameInput;
    private JTextField memoContentInput;
    private JButton backButton;
    private JButton doneButton;
    private JLabel contentText;
    private ChooseGUI<Event> chooseGUI;
    private List<Event> eventList;

    //Constructor for the memo gui
    public MemoCreationGUI(CalenderManagement cm, List<Event> eventList){
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

        // Create the memo
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String memoName = memoNameInput.getText();
                String memoContent = memoContentInput.getText();

                List<Event> eventList = chooseGUI.getObjectChoices();
                Memo memo = cm.createMemo(memoName, memoContent);

                for(Event event: eventList){
                    cm.addMemo(event, memo);
                }
                dispose();
            }
        });
    }

}
