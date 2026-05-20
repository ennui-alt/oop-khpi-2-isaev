package ua.khpi.oop.lab08.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ua.khpi.oop.lab08.exception.InvalidCampusServiceException;
import ua.khpi.oop.lab08.model.ConsultationSlot;
import ua.khpi.oop.lab08.model.ExamReminder;
import ua.khpi.oop.lab08.model.RoomBooking;
import ua.khpi.oop.lab08.service.CampusServiceRegistry;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class CampusServiceFileManagerTest {

    @TempDir
    Path tempDir;

    @Test
    void saveAsTextAndLoadFromTextShouldRestoreRegistry() throws Exception {
        CampusServiceRegistry registry = createRegistry();
        CampusServiceFileManager fileManager = new CampusServiceFileManager();

        Path textPath = tempDir.resolve("campus-services.txt");

        fileManager.saveAsText(registry, textPath);
        CampusServiceRegistry restoredRegistry = fileManager.loadFromText(textPath);

        assertTrue(Files.exists(textPath));
        assertEquals(3, restoredRegistry.size());
        assertTrue(restoredRegistry.findById("E001").isPresent());
        assertTrue(restoredRegistry.findById("R001").isPresent());
        assertTrue(restoredRegistry.findById("C001").isPresent());
    }

    @Test
    void serializeAndDeserializeShouldRestoreRegistry() throws Exception {
        CampusServiceRegistry registry = createRegistry();
        CampusServiceFileManager fileManager = new CampusServiceFileManager();

        Path binaryPath = tempDir.resolve("campus-services.bin");

        fileManager.serialize(registry, binaryPath);
        CampusServiceRegistry restoredRegistry = fileManager.deserialize(binaryPath);

        assertTrue(Files.exists(binaryPath));
        assertEquals(3, restoredRegistry.size());
        assertEquals(3, restoredRegistry.findByStudentName("Zakhar Isaiev").size());
    }

    @Test
    void loadFromTextShouldThrowExceptionForBrokenLine() throws Exception {
        CampusServiceFileManager fileManager = new CampusServiceFileManager();

        Path brokenPath = tempDir.resolve("broken-campus-services.txt");

        Files.writeString(
                brokenPath,
                "EXAM;BAD_LINE;Zakhar Isaiev",
                StandardCharsets.UTF_8
        );

        assertThrows(InvalidCampusServiceException.class, () -> fileManager.loadFromText(brokenPath));
    }

    @Test
    void loadFromTextShouldThrowExceptionForUnknownServiceType() throws Exception {
        CampusServiceFileManager fileManager = new CampusServiceFileManager();

        Path wrongTypePath = tempDir.resolve("wrong-type.txt");

        Files.writeString(
                wrongTypePath,
                "UNKNOWN;X001;Zakhar Isaiev;2026-05-20;10:30;Some field;Some field",
                StandardCharsets.UTF_8
        );

        assertThrows(InvalidCampusServiceException.class, () -> fileManager.loadFromText(wrongTypePath));
    }

    private CampusServiceRegistry createRegistry() throws InvalidCampusServiceException {
        CampusServiceRegistry registry = new CampusServiceRegistry();

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

        return registry;
    }
}