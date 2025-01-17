import java.util.Date;

public class Event {
    private int id;
    private String name;
    private Date date;
    private String location;

    // Constructor
    public Event(int id, String name, Date date, String location) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Event [ID=" + id + ", Name=" + name + ", Date=" + date + ", Location=" + location + "]";
    }
}
