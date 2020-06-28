package Calender;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Entities.User class representing a Entities.User in the system
 */
public class User implements Serializable {
    protected String userId;
    protected String password;
    protected String userName;
    protected List<Calender> calenders;
    protected Calender currentCalender;
    protected static final long serialVersionUID = 5998445582702318745L;
    protected MsgBox msgBox;

    /**
     * This is the basic constructor for the user
     * @param userId the user id
     * @param name the name of the user
     * @param password the password
     */
    public User(String userId, String name, String password){
        this.userId = userId;
        this.userName = name;
        this.password = password;
        this.calenders = new ArrayList<Calender>();
        this.msgBox = new MsgBox();
    }

    /**
     *
     * @param calender the active calender to be set and add to calenders
     */
    public void setCurrentCalender(Calender calender){
        currentCalender = calender;
        addCalender(calender);
    }

    public String getPassword(){
        return password;
    }

    /**
     * Returns the user id of this user
     * @return the user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Returns the name of this user
     * @return the name
     */
    public String getName() {
        return userName;
    }

    /**
     * Return the current calender of this user
     * @return
     */
    public Calender getCurrentCalender(){
        return currentCalender;
    }

    /**
     * Set the current calender that this user is using
     * @param calender the calender to be add to this user's calender list
     */
    public void addCalender(Calender calender){
        if(!calenders.contains(calender)) {
            calenders.add(calender);
        }
    }

    /**
     * Returns all the calenders in this user
     * @return a list of all the calenders
     */
    public List<Calender> getAllCalender() {
        return calenders;
    }

    /**
     * Returns all the messages sent to this user's message box
     * @return
     */
    public List<Message> getMessageList(){
        return msgBox.getMsgList();
    }

    /**
     * Add the message to this user
     * @param message the message to be sent
     */
    public void addMsg(Message message){
        msgBox.addMessage(message);
    }

    /**
     * Delete the message from this user
     * @param message
     */
    public void deleteMsg(Message message){
        msgBox.deleteMessage(message);
    }


    /**
     * String representation of the user
     * @return the string representation
     */
    public String toString(){
        return "Username: "+userName+" Id:"+userId;
    }

}
