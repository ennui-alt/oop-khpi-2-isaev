package ua.khpi.oop.lab07.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExamReminderTest {

    @Test
    void shouldCreateExamReminder() {
        ExamReminder reminder = new ExamReminder("OOP", "25.05.2026");

        assertEquals("OOP", reminder.getSubject());
        assertEquals("25.05.2026", reminder.getExamDate());
    }

    @Test
    void shouldReturnNotificationText() {
        ExamReminder reminder = new ExamReminder("OOP", "25.05.2026");

        String result = reminder.notifyStudent("Zakhar");

        assertTrue(result.contains("Zakhar"));
        assertTrue(result.contains("OOP"));
        assertTrue(result.contains("25.05.2026"));
    }
}