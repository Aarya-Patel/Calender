package Calender;

import java.io.Serializable;
import java.time.LocalDateTime;

public class IndividualAlert extends Alert implements Serializable {

    /**
     * The constructor of the individual alert
     * @param name The name of the alert
     * @param startDate The start date of the alert
     */
    public IndividualAlert(String name, LocalDateTime startDate){
        super(name, startDate);
    }

}
