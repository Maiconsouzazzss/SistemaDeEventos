package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model1.Event;

public class FileHandler {

    private static final String FILE_NAME = "events.data";

    public static void saveEvents(List<Event> events) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Event e : events) {
                writer.write(e.getName() + ";" +
                             e.getAddress() + ";" +
                             e.getCategory() + ";" +
                             e.getDateTime().toString() + ";" +
                             e.getDescription());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar eventos: " + e.getMessage());
        }
    }

    public static List<Event> loadEvents() {
        List<Event> events = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    String name = parts[0];
                    String address = parts[1];
                    String category = parts[2];
                    LocalDateTime dateTime = LocalDateTime.parse(parts[3]);
                    String description = parts[4];
                    events.add(new Event(name, address, category, dateTime, description));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Será criado um novo ao salvar.");
        } catch (IOException e) {
            System.out.println("Erro ao ler eventos: " + e.getMessage());
        }
        return events;
    }
}
