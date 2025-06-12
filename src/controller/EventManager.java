package controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    // Método para retornar eventos ordenados por data/hora
    public List<Event> getEventsOrderedByDate() {
        List<Event> sortedEvents = new ArrayList<>(events);
        Collections.sort(sortedEvents, Comparator.comparing(Event::getDateTime));
        return sortedEvents;
    }
}
