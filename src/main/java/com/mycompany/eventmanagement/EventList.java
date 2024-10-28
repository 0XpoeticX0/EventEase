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
        events.add(new Event("Event Tree", "C:\\Users\\MaHir0\\Pictures\\New folder\\1.jfif"));
        events.add(new Event("Event Sea", "C:\\Users\\MaHir0\\Pictures\\New folder\\2.jfif"));
        events.add(new Event("Event Evening", "C:\\Users\\MaHir0\\Pictures\\New folder\\3.jfif"));
        events.add(new Event("Event Animal", "C:\\Users\\MaHir0\\Pictures\\New folder\\4.jfif"));
        events.add(new Event("Event Tree2", "C:\\Users\\MaHir0\\Pictures\\New folder\\5.jfif"));
        events.add(new Event("Event Tree6", "C:\\Users\\MaHir0\\Pictures\\New folder\\6.jfif"));
        events.add(new Event("Event Tree10", "C:\\Users\\MaHir0\\Pictures\\New folder\\1.jfif")); // Additional event
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
