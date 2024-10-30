package com.mycompany.eventmanagement;

import java.util.ArrayList;
import java.util.List;

// Class to manage the list of events
public class EventList {
    private final List<Event> events;

    public EventList() {
        events = new ArrayList<>();
        initializeEvents(); // Populate the events list
    }    

    // Method to initialize event data
    private void initializeEvents() {
        events.add(new Event("Event Tree", "src/main/java/Resorces/Images/1.jfif"));
        events.add(new Event("Event Sea", "src/main/java/Resorces/Images/2.jfif"));
        events.add(new Event("Event Evening", "src/main/java/Resorces/Images/3.jfif"));
        events.add(new Event("Event Animal", "src/main/java/Resorces/Images/4.jfif"));
        events.add(new Event("Event Tree2", "src/main/java/Resorces/Images/5.jfif"));
        events.add(new Event("Event Tree6", "src/main/java/Resorces/Images/6.jfif"));
        events.add(new Event("Event Tree", "src/main/java/Resorces/Images/1.jfif"));
        events.add(new Event("Event Sea", "src/main/java/Resorces/Images/2.jfif"));
        events.add(new Event("Event Evening", "src/main/java/Resorces/Images/3.jfif"));
        events.add(new Event("Event Animal", "src/main/java/Resorces/Images/4.jfif"));
        events.add(new Event("Event Tree2", "src/main/java/Resorces/Images/5.jfif"));
        events.add(new Event("Event Tree6", "src/main/java/Resorces/Images/6.jfif"));
        events.add(new Event("Event Tree10", "src/main/java/Resorces/Images/4.jfif"));// Additional event
        // Add more events as needed
    }

    // Method to get a subset of events (limited to 6)
    public List<Event> getEvents(int count) {
        return events.subList(0, Math.min(count, events.size()));
    }

    // Method to get all events
    public List<Event> getAllEvents() {
        return events;
    }
}
