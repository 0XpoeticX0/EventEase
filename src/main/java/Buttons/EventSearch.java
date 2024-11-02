package Buttons;

import Events.Event;
import java.util.List;
import java.util.stream.Collectors;

public class EventSearch {

    // Method to search for events based on a query string
    public static List<Event> searchEvents(List<Event> events, String query) {
        return events.stream()
                .filter(event -> event.name.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
