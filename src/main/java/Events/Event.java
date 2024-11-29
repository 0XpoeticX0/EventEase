package Events;

// Class to represent an event
public class Event {
    public String e_id;
    public String name;
    public String location;
    public String description;
    public double price;
    public String imagePath;
    public String status;  // Add status field

    // Updated constructor to include status
    public Event(String e_id, String name, String imagePath, String description, String location, double price, String status) {
        this.e_id = e_id;
        this.name = name;
        this.imagePath = imagePath;
        this.location = location;
        this.description = description;
        this.price = price;
        this.status = status;  // Set status during initialization
    }

    // Getters and Setters
    public String getE_id() {
        return e_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
