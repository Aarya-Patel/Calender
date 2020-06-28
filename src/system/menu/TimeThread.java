package system.menu;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.TimerTask;

public class TimeThread extends TimerTask {
    protected LocalDateTime time;

    public TimeThread(){
        this.time = LocalDateTime.now();
    }

    public synchronized void updateTime(){
        this.setTime(this.time.plus(5, ChronoUnit.SECONDS));
    }

    public LocalDateTime getTime(){
        return this.time;
    }

    public synchronized void setTime(LocalDateTime time){
        this.time = time;
    }

    @Override
    public void run() {
        this.updateTime();
    }
}
