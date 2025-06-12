package model1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Event {

    private String name;
    private String address;
    private String category;
    private LocalDateTime dateTime;
    private String description;
    private List<User> participants;  // novo atributo

    public Event(String name, String address, String category, LocalDateTime dateTime, String description) {
        this.name = name;
        this.address = address;
        this.category = category;
        this.dateTime = dateTime;
        this.description = description;
        this.participants = new ArrayList<>();  // inicializa a lista
    }

    // getters e setters aqui...

    // métodos para participação
    public void addParticipant(User user) {
        if (!participants.contains(user)) {
            participants.add(user);
        }
    }

    public void removeParticipant(User user) {
        participants.remove(user);
    }

    public boolean isParticipating(User user) {
        return participants.contains(user);
    }

    public List<User> getParticipants() {
        return participants;
    }
}

