package gui;

import Calender.*;
import system.manager.CalenderManagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class removeItemGUI<T> extends JFrame {

    private CalenderManagement cm;
    private JPanel contentPanel;
    private JButton okButton;
    private JLabel message;
    private ChooseSingleObjectGUI chooseObj;
    private String className;

    public removeItemGUI(CalenderManagement cm, String targetClass){
        this.cm = cm;
        this.className = targetClass;

        this.setContentPane(contentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        try {
//            T target = (T) chooseObj.getObjectChoice();
            if (this.className.equals("Memo")) {
                this.chooseObj = new ChooseSingleObjectGUI<Memo>(this, this.cm.calender.getMemos());
//                this.cm.removeItem((Memo) chooseObj.getObjectChoice());
            } else if (this.className.equals("Alert")) {
                this.chooseObj = new ChooseSingleObjectGUI<Alert>(this, this.cm.calender.getAlerts());
//                this.cm.removeItem((Alert) chooseObj.getObjectChoice());
            } else if (this.className.equals("Event")) {
                List<Event> events = new ArrayList<Event>();
                events.addAll(this.cm.calender.getEvents());
                events.addAll(this.cm.calender.getPastEvent());
                this.chooseObj = new ChooseSingleObjectGUI<Event>(this, events);
//                this.cm.removeItem((Event) chooseObj.getObjectChoice());
            }
        } catch (Exception e){
            ErrorGUI errorGUI = new ErrorGUI("Error with input! Use valid integers. Thank you. (Invalid User Input in ChooseGUI)");
        }


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(className.equals("Memo")){
                    cm.removeItem((Memo) chooseObj.getObjectChoice());}
                else if(className.equals("Alert")){
                    cm.removeItem((Alert) chooseObj.getObjectChoice());
                } else if(className.equals("Event")){
                    cm.removeItem((Event) chooseObj.getObjectChoice());
                }
                dispose();
            }
        });
    }
}
