package gui;

import Calender.*;
import system.menu.UserCalenderMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Pattern;

public class CalenderSelectionGUI extends JFrame {
    private JPanel contentPanel;
    private JLabel title;
    private JLabel welcomeText;
    private JScrollPane scrollPanel;
    private JLabel display;
    private JTextField textField1;
    private JButton doneButton;
    private JButton backButton;
    private JLabel instructionText;
    private UserCalenderMenu userCalenderMenu;
    private Pattern pattern;
    private User currentUser;

    public CalenderSelectionGUI(UserCalenderMenu userCalenderMenu) {
        super("Selecting Calender!");
        this.setContentPane(contentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        this.userCalenderMenu = userCalenderMenu;
        this.currentUser = userCalenderMenu.getCurrentUser();
        this.pattern = Pattern.compile("^[0-9]+");
        this.setMinimumSize(new Dimension(650, 450));

        this.welcomeText.setText("Welcome " + currentUser.getName());
        setDisplay();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(pattern.matcher(textField1.getText()).matches()){
                        List<Calender> calenders = currentUser.getAllCalender();
                        System.out.println(calenders);
                        userCalenderMenu.selectCalender(calenders.get(Integer.valueOf(textField1.getText())));
                        userCalenderMenu.proceedToMainMenu();
                        dispose();
                    }
                } catch (Exception err){
                    ErrorGUI errorGUI = new ErrorGUI("Error with input! Use valid integers. Thank you. (Invalid User Input in CalenderSelectionGUI)");
                }
            }
        });
    }

    public void setDisplay() {
        String str = "";
        List<Calender> calenders = currentUser.getAllCalender();
        for (int i = 0; i < calenders.size(); i++) {
            str += i + ") " + calenders.get(i) + "<br/>";
        }
        display.setText("<html>" + str + "</html>");
    }
}
