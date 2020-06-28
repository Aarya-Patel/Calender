package system.menu;

import Calender.*;
import gui.UserCalenderMenuGUI;
import system.ConnectDB;

import java.util.ArrayList;
import java.util.List;

/**
 * This class allows the user to create/select calenders. An intermediate step before progressing towards
 * the MainMenu where the user can manage their calender.
 */
public class UserCalenderMenu {

    // The current user in this menu.
    private User currentUser;
    private ConnectDB connectDB;
    private List<User> userList;

    public UserCalenderMenu(ConnectDB connectDB){
        this.connectDB = connectDB;
    }

    /**
     * Create the menu for this user
     * @param currentUser the current user in the menu
     */
    public void createMenu(User currentUser){
        this.currentUser = currentUser;
        //Get the list of users except this one
        try {
            this.userList = (ArrayList<User>)connectDB.getAllUsers().clone();
            userList.remove(currentUser);
        } catch (Exception e){
            e.printStackTrace();
        }
        UserCalenderMenuGUI userCalenderMenuGUI = new UserCalenderMenuGUI(this);

    }

    public List<User> getUserList(){
        return userList;
    }

    /**
     * Create the calender
     * @param name the name of the calender
     * @return a calender
     */
    public Calender createCalender(String name){
        Calender calender = new Calender(name);
        return calender;
    }

    /**
     * Adds the calender to the list of the user's calenders
     * @param calender the calender to be added
     */
    public void addCalender(Calender calender){
        currentUser.addCalender(calender);
    }

    /**
     * Set the calender to the current calender
     * @param calender the current calender
     */
    public void selectCalender(Calender calender){
        currentUser.setCurrentCalender(calender);
    }

    /**
     * Gets the current user in the system
     * @return the current user
     */
    public User getCurrentUser(){
        return currentUser;
    }

    /**
     * Proceeds to the main menu with this user. Creates the MainMenu obj.
     */
    public void proceedToMainMenu(){
        MainMenu mainMenu = new MainMenu(currentUser);
    }

    public void logoutCurrentUser(){
//        connectDB.logout(currentUser);
        try {
            connectDB.logoutAllUsers();
            connectDB.updateAllUsers();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in Logging out Users");
        }
    }

}
