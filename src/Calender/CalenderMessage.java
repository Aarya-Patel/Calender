package Calender;

import java.io.Serializable;
import java.util.List;

public class CalenderMessage extends Message<Calender> implements Serializable {

    public CalenderMessage(User user, User recipient, List<Calender> calenderList){
        super(user, recipient, calenderList);


    }

    //Add the calenders into the receipient
    @Override
    public void accept(List<Calender> calenderList){
        User recipient = this.getRecipients();
        List<Calender> calenders = this.getObjectSent();
        for(Calender c: calenders){
            recipient.addCalender(c);
        }
        this.getRecipients().deleteMsg(this);
    }
}
