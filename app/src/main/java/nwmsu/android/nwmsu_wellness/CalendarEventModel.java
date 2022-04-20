package nwmsu.android.nwmsu_wellness;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CalendarEventModel {

    public static ArrayList<CalendarEventModel> eventList = new ArrayList<>();

    public static ArrayList<CalendarEventModel> eventsOnDate(LocalDate date) {
        ArrayList<CalendarEventModel> events = new ArrayList<>();
        for(CalendarEventModel event : eventList) {
            if( event.getStartDate().equals(date)) {
                events.add(event);
            }
        }
        return events;
    }

    private LocalDate startDate;
    private LocalTime startTime;
    private String eventName;
    private String eventDescription;

    public CalendarEventModel(String name, LocalDate date, LocalTime time) {
        initModel(name, date, time);
        this.eventDescription = "";
    }

    public CalendarEventModel(String name, LocalDate date, LocalTime time, String description) {
        initModel(name, date, time);
        this.eventDescription = description;
    }

    public void initModel(String name, LocalDate date, LocalTime time) {
        this.eventName = name;
        this.startDate = date;
        this.startTime = time;
    }


    public String getEventName() {
        return this.eventName;
    }

    public boolean setEventName(String name) {
        try {
            this.eventName = name;
        } catch (Exception e) {
            // Handle exception
            return false;
        }
        return true;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public boolean setStartDate(LocalDate date) {
        try {
            this.startDate = date;
        } catch (Exception e) {
            // Handle exception
            return false;
        }
        return true;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public boolean setEndDate(LocalTime time) {
        try {
            this.startTime = time;
        } catch (Exception e) {
            // Handle exception
            return false;
        }
        return true;
    }

    public String getDescription() {
        return this.eventDescription;
    }

    public boolean setDescription(String text) {
        try {
            this.eventDescription = text;
        } catch (Exception e) {
            // Handle exception
            return false;
        }
        return true;
    }
}
