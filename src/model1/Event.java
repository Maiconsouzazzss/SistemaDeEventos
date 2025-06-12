package model1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Event {
    private String name;
    private String address;
    private String category;
    private LocalDateTime dateTime;
    private String description;
    private List<User> participants;

    public Event(String name, String address, String category, LocalDateTime dateTime, String description) {
        this.name = name;
        this.address = address;
        this.category = category;
        this.dateTime = dateTime;
        this.description = description;
        this.participants = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getCategory() { return category; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getDescription() { return description; }
    public List<User> getParticipants() { return participants; }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(name, event.name) &&
               Objects.equals(dateTime, event.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateTime);
    }
}

