package Buttons;

import Events.Event;
import java.util.ArrayList;
import java.util.List;

public class Search {
    private List<Event> events; // List of events for searching

    public Search(List<Event> events) {
        this.events = events; // Initialize events through constructor
    }

    public List<Event> searchEvents(String searchTerm) {
        List<Event> matchedEvents = new ArrayList<>();
        for (Event event : events) {
            if (event.name.toLowerCase().contains(searchTerm.toLowerCase())) {
                matchedEvents.add(event);
            }
        }
        return matchedEvents;
    }
}
