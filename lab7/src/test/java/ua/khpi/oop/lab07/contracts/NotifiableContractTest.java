package ua.khpi.oop.lab07.contracts;

import org.junit.jupiter.api.Test;
import ua.khpi.oop.lab07.model.ConsultationSlot;
import ua.khpi.oop.lab07.model.ExamReminder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NotifiableContractTest {

    @Test
    void shouldNotifyStudentForDifferentObjects() {
        Notifiable[] items = {
                new ExamReminder("OOP", "25.05.2026"),
                new ConsultationSlot("Teacher", "Java interfaces")
        };

        for (Notifiable item : items) {
            String result = item.notifyStudent("Zakhar");

            assertFalse(result.isBlank());
            assertTrue(result.contains("Zakhar"));
        }
    }
}