package nwmsu.android.nwmsu_wellness;

public class CalendarEventModel {
    private String startDate;
    private String endDate;
    private String eventName;
    private String eventDescription;

    public CalendarEventModel() {
        // Nothing
    }

    public CalendarEventModel(String name, String start, String end) {
        initModel(name, start, end);
        this.eventDescription = "";
    }

    public CalendarEventModel(String name, String start, String end, String description) {
        initModel(name, start, end);
        this.eventDescription = description;
    }

    public void initModel(String name, String start, String end) {
        this.eventName = name;
        this.startDate = start;
        this.endDate = end;
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

    public String getStartDate() {
        return this.startDate;
    }

    public boolean setStartDate(String date) {
        try {
            this.startDate = date;
        } catch (Exception e) {
            // Handle exception
            return false;
        }
        return true;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public boolean setEndDate(String date) {
        try {
            this.endDate = date;
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
