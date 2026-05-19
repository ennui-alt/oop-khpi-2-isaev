package ua.khpi.oop.lab08.service;

import org.junit.jupiter.api.Test;
import ua.khpi.oop.lab08.exception.InvalidCampusServiceException;
import ua.khpi.oop.lab08.model.ConsultationSlot;
import ua.khpi.oop.lab08.model.ExamReminder;
import ua.khpi.oop.lab08.model.RoomBooking;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class CampusServiceRegistryTest {

    @Test
    void addServiceShouldIncreaseRegistrySize() throws InvalidCampusServiceException {
        CampusServiceRegistry registry = new CampusServiceRegistry();

        ExamReminder exam = new ExamReminder(
                "E001",
                "Zakhar Isaiev",
                LocalDate.of(2026, 5, 20),
                LocalTime.of(10, 30),
                "Object-Oriented Programming",
                "301"
        );

        registry.addService(exam);

        assertEquals(1, registry.size());
    }

    @Test
    void findByIdShouldReturnCorrectService() throws InvalidCampusServiceException {
        CampusServiceRegistry registry = new CampusServiceRegistry();

        RoomBooking booking = new RoomBooking(
                "R001",
                "Zakhar Isaiev",
                LocalDate.of(2026, 5, 21),
                LocalTime.of(14, 0),
                "204",
                "Team project meeting"
        );

        registry.addService(booking);

        assertTrue(registry.findById("R001").isPresent());
        assertEquals("R001", registry.findById("R001").get().getId());
    }

    @Test
    void findByStudentNameShouldReturnAllStudentServices() throws InvalidCampusServiceException {
        CampusServiceRegistry registry = new CampusServiceRegistry();

        registry.addService(new ExamReminder(
                "E001",
                "Zakhar Isaiev",
                LocalDate.of(2026, 5, 20),
                LocalTime.of(10, 30),
                "Object-Oriented Programming",
                "301"
        ));

        registry.addService(new ConsultationSlot(
                "C001",
                "Zakhar Isaiev",
                LocalDate.of(2026, 5, 22),
                LocalTime.of(12, 15),
                "Valentina V.",
                "Java serialization"
        ));

        assertEquals(2, registry.findByStudentName("Zakhar Isaiev").size());
    }

    @Test
    void addServiceShouldThrowExceptionForDuplicateId() throws InvalidCampusServiceException {
        CampusServiceRegistry registry = new CampusServiceRegistry();

        ExamReminder firstExam = new ExamReminder(
                "E001",
                "Zakhar Isaiev",
                LocalDate.of(2026, 5, 20),
                LocalTime.of(10, 30),
                "Object-Oriented Programming",
                "301"
        );

        ExamReminder secondExam = new ExamReminder(
                "E001",
                "Zakhar Isaiev",
                LocalDate.of(2026, 5, 25),
                LocalTime.of(11, 0),
                "Databases",
                "302"
        );

        registry.addService(firstExam);

        assertThrows(InvalidCampusServiceException.class, () -> registry.addService(secondExam));
    }

    @Test
    void addServiceShouldThrowExceptionForTimeConflict() throws InvalidCampusServiceException {
        CampusServiceRegistry registry = new CampusServiceRegistry();

        ExamReminder exam = new ExamReminder(
                "E001",
                "Zakhar Isaiev",
                LocalDate.of(2026, 5, 20),
                LocalTime.of(10, 30),
                "Object-Oriented Programming",
                "301"
        );

        RoomBooking booking = new RoomBooking(
                "R001",
                "Zakhar Isaiev",
                LocalDate.of(2026, 5, 20),
                LocalTime.of(10, 30),
                "204",
                "Team project meeting"
        );

        registry.addService(exam);

        assertThrows(InvalidCampusServiceException.class, () -> registry.addService(booking));
    }
}