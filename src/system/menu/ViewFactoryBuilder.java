package system.menu;

import Calender.*;
import system.manager.CalenderManagement;
import system.manager.ViewManagement;

/**
 * The Builder for the Presenter.View class
 */
public class ViewFactoryBuilder {
     ViewFactory view;
     Calender calender;
     CalenderManagement cm;
     ViewManagement vm;

    public ViewFactoryBuilder(Calender calender){
        this.calender = calender;
    }

    public void buildCalenderManagement(){
        this.cm = new CalenderManagement(this.calender);
    }

    public void buildViewMangement(){
        this.vm = new ViewManagement(this.calender);
    }

    public void buildView(){
        ViewFactory view = new ViewFactory();
        this.view = view;
        this.view.setCalender(this.calender);
        this.view.setCalenderManagement(this.cm);
        this.view.setViewManagement(this.vm);
    }

    public ViewFactory getView(){
        return this.view;
    }
}
