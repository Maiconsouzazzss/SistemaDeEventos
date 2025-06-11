package controller;

import java.util.List;

import model1.Event;
import util.FileHandler;

public class EventManager {

    private List<Event> events;

    public EventManager() {
        this.events = FileHandler.loadEvents(); // carregar eventos ao iniciar
    }

    public void addEvent(Event event) {
        events.add(event);
        FileHandler.saveEvents(events); // salvar sempre que adicionar
    }

    public List<Event> getAllEvents() {
        return events;
    }
}
