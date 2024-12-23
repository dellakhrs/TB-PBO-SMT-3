import java.util.Date;

public class Client {
    private int id;
    private String name;
    private String contact;
    private String email;
    private String eventLocation;
    private String status;
    private Date eventTime;
    private double cost;

    // Konstruktor untuk menginisialisasi semua field
    public Client(int id, String name, String contact, String email, String string, String string2, Date eventTime2, double d) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.eventLocation = eventLocation;
        this.status = status;
        this.eventTime = eventTime;
        this.cost = cost;
    }

    // Getter dan setter untuk semua field
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getStatus() {
        return status;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public double getCost() {
        return cost;
    }
}
