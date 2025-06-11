package model1;

import java.time.LocalDateTime;

public class Event {

    private String name;
    private String address;
    private String category;
    private LocalDateTime dateTime;
    private String description;

    public Event(String name, String address, String category, LocalDateTime dateTime, String description) {
        this.name = name;
        this.address = address;
        this.category = category;
        this.dateTime = dateTime;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCategory() {
        return category;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Evento: " + name + "\nCategoria: " + category + "\nEndereço: " + address +
               "\nData e Hora: " + dateTime + "\nDescrição: " + description;
    }
}
