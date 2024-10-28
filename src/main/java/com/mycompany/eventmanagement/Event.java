package com.mycompany.eventmanagement;

// Class to represent an event
public class Event {
    private String name;
    private String imagePath;

    public Event(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }
}
