package Calender;

import Calender.Calender;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Message<T> implements Serializable {
    private List<T> objectSent = new ArrayList<T>();
    private User recipients;
    private String sender;

    public Message(User user, User recipients, List<T> objectSent) {
        sender = user.userName;
        this.recipients = recipients;
        this.objectSent = objectSent;

    }

    /**
     * Gets the recipients of this message
     * @return the user who is the recipient
     */
    public User getRecipients() {
        return recipients;
    }

    /**
     * Gets the attachments sent with this message
     * @return
     */
    public List<T> getObjectSent() {
        return objectSent;
    }

    /**
     * Abstract method that each subclass will implement.
     */
    public abstract void accept(List<Calender> calenderList);

    /**
     * String representation of this object
     * @return the string representation
     */
    public String toString() {
        String temp = "";
        for (T t : objectSent) {
            temp += t + "\n";
        }
        return "From: " + sender + " To: " + recipients.userName + "\n Attachments: " + temp;
    }
}
