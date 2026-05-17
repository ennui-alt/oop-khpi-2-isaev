package ua.khpi.oop.lab08.persistence;

import ua.khpi.oop.lab08.exception.InvalidCampusServiceException;
import ua.khpi.oop.lab08.model.CampusService;
import ua.khpi.oop.lab08.model.ConsultationSlot;
import ua.khpi.oop.lab08.model.ExamReminder;
import ua.khpi.oop.lab08.model.RoomBooking;
import ua.khpi.oop.lab08.service.CampusServiceRegistry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CampusServiceFileManager {

    public void saveAsText(CampusServiceRegistry registry, Path path) throws IOException {
        createParentDirectory(path);

        List<String> lines = new ArrayList<>();

        for (CampusService service : registry.getServices()) {
            lines.add(service.toTextLine());
        }

        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    public CampusServiceRegistry loadFromText(Path path) throws IOException, InvalidCampusServiceException {
        CampusServiceRegistry registry = new CampusServiceRegistry();

        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        int lineNumber = 1;

        for (String line : lines) {
            if (line.isBlank()) {
                lineNumber++;
                continue;
            }

            try {
                CampusService service = parseLine(line);
                registry.addService(service);
            } catch (RuntimeException exception) {
                throw new InvalidCampusServiceException(
                        "Invalid data in line " + lineNumber + ": " + line,
                        exception
                );
            }

            lineNumber++;
        }

        return registry;
    }

    public void serialize(CampusServiceRegistry registry, Path path) throws IOException {
        createParentDirectory(path);

        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path))) {
            outputStream.writeObject(registry);
        }
    }

    public CampusServiceRegistry deserialize(Path path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(path))) {
            return (CampusServiceRegistry) inputStream.readObject();
        }
    }

    private CampusService parseLine(String line) {
        String[] parts = line.split(";", -1);

        if (parts.length != 7) {
            throw new IllegalArgumentException("Line must have 7 fields");
        }

        String type = parts[0];
        String id = parts[1];
        String studentName = parts[2];
        LocalDate date = LocalDate.parse(parts[3]);
        LocalTime time = LocalTime.parse(parts[4]);

        if (type.equals("EXAM")) {
            return new ExamReminder(
                    id,
                    studentName,
                    date,
                    time,
                    parts[5],
                    parts[6]
            );
        }

        if (type.equals("ROOM")) {
            return new RoomBooking(
                    id,
                    studentName,
                    date,
                    time,
                    parts[5],
                    parts[6]
            );
        }

        if (type.equals("CONSULTATION")) {
            return new ConsultationSlot(
                    id,
                    studentName,
                    date,
                    time,
                    parts[5],
                    parts[6]
            );
        }

        throw new IllegalArgumentException("Unknown service type: " + type);
    }

    private void createParentDirectory(Path path) throws IOException {
        Path parent = path.getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }
    }
}