package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event implements Serializable {
    private String eventDesc;
    private int eventDay;
    private int eventHour;
    private String eventPublicOrPrivate;
    private String userName;

    public Event(){}

    public Event(String eventDesc, int eventDay, int eventHour, String eventPublicOrPrivate, String userName) {
        this.eventDesc = eventDesc;
        this.eventDay = eventDay;
        this.eventHour = eventHour;
        this.eventPublicOrPrivate = eventPublicOrPrivate;
        this.userName = userName;
    }

    public int getHiddenID() {
        return hiddenID;
    }

    public void setHiddenID(int hiddenID) {
        this.hiddenID = hiddenID;
    }

    private int hiddenID;

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public int getEventDay() {
        return eventDay;
    }

    public void setEventDay(int eventDay) {
        this.eventDay = eventDay;
    }

    public int getEventHour() {
        return eventHour;
    }

    public void setEventHour(int eventHour) {
        this.eventHour = eventHour;
    }

    public String getEventPublicOrPrivate() {
        return eventPublicOrPrivate;
    }

    public void setEventPublicOrPrivate(String eventPublicOrPrivate) {
        this.eventPublicOrPrivate = eventPublicOrPrivate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Event> searchEvent(int day , int hour)
    {
        List<Event> foundEvents = new ArrayList<>();
        List<Event> allfindEvents = DAO.getEvents();

        for(int i=0;i< allfindEvents.size();i++)
        {
            if(allfindEvents.get(i).getEventDay() == day && allfindEvents.get(i).getEventHour() == hour)
            {
                foundEvents.add(allfindEvents.get(i));
            }
        }
        return foundEvents;
    }
}
