package Events;

// Class to represent an event
public class Event {
    public String name;
    public String location;
    public String description;
    public int price;
    public String imagePath;

    public Event(String name, String imagePath,String description,String location,int price) {
        this.name = name;
        this.imagePath = imagePath;
        this.location= location;
        this.description = description;
        this.price = price;
    }
}
