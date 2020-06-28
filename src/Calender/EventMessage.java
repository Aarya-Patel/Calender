package Calender;

import java.util.List;

public class EventMessage extends Message<Event>{

    public EventMessage(User user, User recipient, List<Event> eventList){
        super(user, recipient, eventList);
    }

    @Override
    public void accept(List<Calender> calenderList){
        for(Calender c : calenderList){
            for(Event e: getObjectSent()){
                c.addEvent(e);
            }
        }
        this.getRecipients().deleteMsg(this);

    }

}
