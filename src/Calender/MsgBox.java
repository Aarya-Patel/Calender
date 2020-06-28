package Calender;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A container for all the messages
 */
public class MsgBox implements Serializable {
    private List<Message> msgList = new ArrayList<Message>();

    /**
     * Adds the message to this list
     * @param message the message to send
     */
    public void addMessage(Message message){
        msgList.add(message);
    }

    /**
     * Deletes the message from this list
     * @param message
     */
    public void deleteMessage(Message message){
        msgList.remove(message);
    }

    /**
     * Returns the message list
     * @return
     */
    public List<Message> getMsgList(){
        return msgList;
    }


}
