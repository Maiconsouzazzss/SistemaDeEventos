package controller;

import model1.Event;
import model1.User;
import util.FileHandler;
import java.util.*;
import java.util.stream.Collectors;


public class EventManager {

    private List<Event> events;

    public EventManager() {
        this.events = FileHandler.loadEvents();
    }

    public void addEvent(Event event) {
        events.add(event);
        FileHandler.saveEvents(events); // salva ao adicionar
    }

    public List<Event> getAllEvents() {
        return events;
    }

    public List<Event> getEventsOrderedByDate() {
        return events.stream()
                .sorted(Comparator.comparing(Event::getDateTime))
                .collect(Collectors.toList());
    }

    public void participateInEvent(int index, User user) {
        Event event = events.get(index);
        event.addParticipant(user);
        FileHandler.saveEvents(events); // salva após alteração
    }

    public void cancelParticipation(int index, User user) {
        Event event = events.get(index);
        event.removeParticipant(user);
        FileHandler.saveEvents(events);
    }

    public List<Event> getEventsUserIsParticipating(User user) {
        return events.stream()
                .filter(event -> event.isParticipating(user))
                .collect(Collectors.toList());
    }
}

