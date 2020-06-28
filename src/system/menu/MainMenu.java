package system.menu;

import Calender.*;
import gui.MainMenuGUI;
import gui.SetTimeGUI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The main menu is the facade class responsible for calling other methods in UseCases.ViewManagement and UseCases.CalenderManagement
 */
public class MainMenu extends TimerTask {
    User currentUser;
    TimeThread timeThread;
    volatile LocalDateTime systemTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    ViewFactory viewFactory;
    ViewFactoryBuilder viewBuilder;
    boolean go = true;
    MainMenuGUI mainMenuGUI;
    Timer updateTimeThread;
    Timer updateMenuThread;

    /**
     * The constructor for the main menu
     *
     * @param currentUser the user that is currently logged in
     */
    public MainMenu(User currentUser) {
        this.currentUser = currentUser;
        this.timeThread = new TimeThread();
        this.viewBuilder = new ViewFactoryBuilder(currentUser.getCurrentCalender());

        //Build the view
        this.viewBuilder.buildCalenderManagement();
        this.viewBuilder.buildViewMangement();
        this.viewBuilder.buildView();
        this.viewFactory = this.viewBuilder.getView();
        this.mainMenuGUI = new MainMenuGUI(this);
        this.mainMenuGUI.setVisible(true);

        //Set the timer threads
        this.updateMenuThread = new Timer();
        this.updateTimeThread = new Timer();
        this.updateTimeThread.schedule(timeThread, 0, 5000);
        this.updateMenuThread.schedule(this, 0, 5000);
    }

    /**
     * Ends the threads that update the time
     */
    public void endThreads(){
        this.updateMenuThread.cancel();
        this.updateTimeThread.cancel();
    }

    public synchronized void setSystemTimeUser() {
        SetTimeGUI setTimeGUI = new SetTimeGUI(this.timeThread, formatter);
    }

    public void updateAll() {
        this.viewFactory.updateAll(this.systemTime);
    }

    public boolean keepGoing() {
        return this.go;
    }

    public ViewFactory getView(){
        return this.viewFactory;
    }

    public User getCurrentUser(){
        return this.currentUser;
    }
    @Override
    public void run() {
        this.systemTime = this.timeThread.getTime();
        this.updateAll();
        this.go = !mainMenuGUI.getLogoutStatus();
    }
}
