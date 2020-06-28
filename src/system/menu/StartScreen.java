package system.menu;


import gui.StartMenuGUI;
import system.ConnectDB;

public class StartScreen {
    private ConnectDB connectDB = new ConnectDB();
    private StartMenuGUI startMenuGUI;
    private UserCalenderMenu userCalenderMenu = new UserCalenderMenu(connectDB);

    public StartScreen(){
        // Create the startmenu gui here and pass the connectdb into the gui as an arg
        startMenuGUI = new StartMenuGUI(connectDB, userCalenderMenu);

    }

}
