package gui;

import Calender.User;
import system.menu.MainMenu;
import system.menu.ViewFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The main menu gui that creates other GUIs
 */
public class MainMenuGUI extends JFrame {
    private JPanel mainMenuPanel;
    private JButton createEventsButton;
    private JButton createAddAlertsButton;
    private JButton createMemosButton;
    private ViewFactory view;
    private User currentUser;
    private boolean userLoggedout = false;

    private JLabel mainMenuText;
    private JButton linkEventsIntoSeriesButton;
    private JButton viewEventsWithNameButton;
    private JButton createAddTagsButton;
    private JButton viewEventsWithMemoButton;
    private JButton setTimeButton;
    private JButton backButton;
    private JButton viewFutureEventsButton;
    private JButton viewEventsWithTagButton;
    private JButton viewEventsInSeriesButton;
    private JButton viewAllAlertsButton;
    private JButton viewPastEventsButton;
    private JButton removeMemoButton;
    private JButton editAlertButton;
    private JButton removeAlertButton;
    private JButton editEventButton;
    private JButton removeEventButton;
    private JButton editMemoButton;
    private MainMenu mainMenu;

    public MainMenuGUI(MainMenu mainMenu){
        super("Main Menu");
        this.mainMenu = mainMenu;
        this.view = mainMenu.getView();
        this.currentUser = mainMenu.getCurrentUser();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainMenuPanel);
        this.pack();
        //Add the action listeners for evey button and then just call the respective method in View
        createEventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.userAddEvent();
            }
        });
        createAddAlertsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.userAddAlert();
            }
        });
        createMemosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.userAddMemo();
            }
        });
        createAddTagsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.userAddTag();
            }
        });
        viewFutureEventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.viewFutureEvents();
            }
        });
        viewAllAlertsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.viewAllAlerts();
            }
        });
        viewPastEventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.viewPastEvents();
            }
        });
        linkEventsIntoSeriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.userLinkEventsToSeries();
            }
        });
        viewEventsWithNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.displayUserInputEventsByName();
            }
        });
        viewEventsWithTagButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.displayUserInputEventsWithTag();
            }
        });
        viewEventsWithMemoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.displayUserInputEventsWithMemo();
            }
        });
        viewEventsInSeriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.displayUserInputEventsInSeries();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu.endThreads();
                dispose();
            }
        });

        setTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu.setSystemTimeUser();
            }
        });

        editMemoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.userEditMemo();
            }
        });
        removeMemoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.removeMemo();
            }
        });
        removeAlertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.removeAlert();
            }
        });
        removeEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.userRemoveEvent();
            }
        });
        editEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.userEditEvent();
            }
        });
        editAlertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.userEditAlert();
            }
        });
    }

    public boolean getLogoutStatus(){
        return userLoggedout;
    }
}
