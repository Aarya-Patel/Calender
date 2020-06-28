package system;

import Calender.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConnectDB {
    private ArrayList<User> allUsers;

    public ConnectDB(){
        try {
            allUsers = getUser();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateAllUsers(){
        try {
            this.allUsers = getUser();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<User> getAllUsers(){
        return allUsers;
    }

    public static Connection connection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        //static String databaseName = "Register";
//        String url  = "jdbc:mysql://localhost:3306/Register";
        String url = "jdbc:mysql://localhost:3306/Register?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
//        String password = "Shalini@2000";
        String password = "password";
        String driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public static User menu() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        //String driver = "com.mysql.cj.jdbc.Driver";
        //Class.forName(driver);
        ///connection = DriverManager.getConnection(url, username, password);
        //Connection connection = connection();
        Scanner count = new Scanner(System.in);
        System.out.println("Press 1 to login and 2 to Signup");
        String temp = count.nextLine();
        User x = null;
        if (temp.equals("1")) {
//            x = login();
//            Login login = new Login();
        } else if (temp.equals("2")) {
//            register();
//            x = login();
            //print();
        } else {
            System.out.println("invalid, try again");
            x = menu();
        }
        return x;


    }

    public static void register(String userName, String userID, String Password) throws SQLException, ClassNotFoundException, IOException {
        Connection connection = connection();
        createTable();
        String sq = "INSERT INTO user"
                + "(UserID, UserName, Password, Calender)"
                + "values(?,?,?,?)";
        PreparedStatement pst = connection.prepareStatement(sq);
        pst.setString(1, userID);
        pst.setString(2, userName);
        pst.setString(3, Password);

        User user = new User(userID, userName, Password);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oout = new ObjectOutputStream(baos);
        oout.writeObject(user);
        oout.close();

        pst.setBytes(4, baos.toByteArray());
        pst.executeUpdate();
    }
    public static ArrayList<User> getUser() throws SQLException, ClassNotFoundException, IOException {
        Connection connection = connection();
        Statement st = connection.createStatement();
        ResultSet myRs = st.executeQuery("select * from user");
        ArrayList<User> lst = new ArrayList<User>();
        User x;
        while (myRs.next()){
            byte[] buf = myRs.getBytes("Calender");
            ObjectInputStream objectIn = null;
            if (buf != null)
                objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));

            assert objectIn != null;
            //Entities.Calender objj = (Entities.Calender) objectIn.readObject();
            Object deSerializedObject = objectIn.readObject();
            x = (User) deSerializedObject;
            lst.add(x);


        }

        return lst;

    }


    public User login(String userName, String Password) throws ClassNotFoundException {
        User user = null;
        for(User u: allUsers){
            if(u.getName().equals(userName) && u.getPassword().equals(Password)) {
                user = u;
            }
        }

        if(user == null){
            throw new ClassNotFoundException();
        }
        return user;
    }


    public static void createTable() throws SQLException, ClassNotFoundException {
        Connection connection = connection();
        PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS user(UserID varchar(40) NOT NULL UNIQUE, UserName varchar(50) NOT NULL UNIQUE, Password varchar(50) NOT NULL, Calender blob)");
        create.executeUpdate();
    }

    public void print() {
        try {
            Connection connection = connection();
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from user");
            while (myRs.next()) {
                System.out.println(myRs.getObject("Calender") + "," + myRs.getString("Password"));
            }
        } catch (Exception exc) {
            System.out.println("ansh");
            exc.printStackTrace();
        }
    }

    public void logoutAllUsers() {
        //this.allUsers = getUser();
        List<User> usr = getAllUsers();
        for(int i = 0; i < usr.size(); i++)
            logout(usr.get(i));


    }

    public static void logout(User user) {
        try {
            Connection connection = connection();
            String sql = "UPDATE User SET Calender = ? WHERE UserID =" + user.getUserId();
            PreparedStatement pst = connection.prepareStatement(sql);
            //pst.setString(2, user.getUserId());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(baos);
            oout.writeObject(user);
            oout.close();
            // serializing java object to mysql database
            //serialized_id = serializeJavaObjectToDB(connection, obj);
            pst.setBytes(1, baos.toByteArray());
            pst.executeUpdate();
            //ResultSet rs = pst.executeQuery();

        } catch (Exception e) {
            System.out.println("Logout failed, please try again");
        }


    }
}
