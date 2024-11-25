package Events;

// Class to represent an event
public class Event {
    public String e_id;
    public String name;
    public String location;
    public String description;
    public double price;
    public String imagePath;

    public Event(String e_id, String name, String imagePath, String description, String location, double price) {
        this.e_id = e_id;
        this.name = name;
        this.imagePath = imagePath;
        this.location= location;
        this.description = description;
        this.price = price;
    }
}
