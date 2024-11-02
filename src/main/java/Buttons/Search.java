/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Buttons;

import Events.Event;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MaHir0
 */
public class Search {

    private Iterable<Event> events;
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
