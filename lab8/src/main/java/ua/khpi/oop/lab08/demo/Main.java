package ua.khpi.oop.lab08.demo;

import ua.khpi.oop.lab08.exception.InvalidCampusServiceException;
import ua.khpi.oop.lab08.model.ConsultationSlot;
import ua.khpi.oop.lab08.model.ExamReminder;
import ua.khpi.oop.lab08.model.RoomBooking;
import ua.khpi.oop.lab08.persistence.CampusServiceFileManager;
import ua.khpi.oop.lab08.service.CampusServiceRegistry;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {
        CampusServiceRegistry registry = new CampusServiceRegistry();

        try {
            registry.addService(new ExamReminder(
                    "E001",
                    "Zakhar Isaiev",
                    LocalDate.of(2026, 5, 20),
                    LocalTime.of(10, 30),
                    "Object-Oriented Programming",
                    "301"
            ));

            registry.addService(new RoomBooking(
                    "R001",
                    "Zakhar Isaiev",
                    LocalDate.of(2026, 5, 21),
                    LocalTime.of(14, 0),
                    "204",
                    "Team project meeting"
            ));

            registry.addService(new ConsultationSlot(
                    "C001",
                    "Zakhar Isaiev",
                    LocalDate.of(2026, 5, 22),
                    LocalTime.of(12, 15),
                    "Valentina V.",
                    "Java serialization"
            ));

            System.out.println("Initial campus services:");
            registry.getServices().forEach(System.out::println);

            CampusServiceFileManager fileManager = new CampusServiceFileManager();

            Path textPath = Path.of("build", "lab08", "campus-services.txt");
            Path binaryPath = Path.of("build", "lab08", "campus-services.bin");

            fileManager.saveAsText(registry, textPath);

            CampusServiceRegistry restoredFromText = fileManager.loadFromText(textPath);

            System.out.println("\nRestored from text file:");
            restoredFromText.getServices().forEach(System.out::println);

            fileManager.serialize(restoredFromText, binaryPath);

            CampusServiceRegistry restoredFromBinary = fileManager.deserialize(binaryPath);

            System.out.println("\nRestored from binary file:");
            restoredFromBinary.getServices().forEach(System.out::println);

            System.out.println("\nSearch by student name:");
            restoredFromBinary.findByStudentName("Zakhar Isaiev").forEach(System.out::println);

            showErrorExample(fileManager);

        } catch (InvalidCampusServiceException exception) {
            System.err.println("Campus service error: " + exception.getMessage());
        } catch (IOException exception) {
            System.err.println("File error: " + exception.getMessage());
        } catch (ClassNotFoundException exception) {
            System.err.println("Binary file contains unknown class: " + exception.getMessage());
        }
    }

    private static void showErrorExample(CampusServiceFileManager fileManager) {
        Path brokenPath = Path.of("build", "lab08", "broken-campus-services.txt");

        try {
            Files.createDirectories(brokenPath.getParent());
            Files.writeString(
                    brokenPath,
                    "EXAM;BAD_LINE;Zakhar Isaiev",
                    StandardCharsets.UTF_8
            );

            fileManager.loadFromText(brokenPath);
        } catch (InvalidCampusServiceException exception) {
            System.out.println("\nError example was handled:");
            System.out.println(exception.getMessage());
        } catch (IOException exception) {
            System.err.println("Could not create broken example file: " + exception.getMessage());
        }
    }
}