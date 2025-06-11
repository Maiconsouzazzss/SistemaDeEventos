package controller;

import java.util.ArrayList;
import java.util.List;

import model1.Event;

public class EventManager {

    private List<Event> events;

    public EventManager() {
        this.events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getAllEvents() {
        return events;
    }
}
