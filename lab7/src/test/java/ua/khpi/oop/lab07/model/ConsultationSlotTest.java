package ua.khpi.oop.lab07.model;

import org.junit.jupiter.api.Test;
import ua.khpi.oop.lab07.contracts.Notifiable;
import ua.khpi.oop.lab07.contracts.Schedulable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsultationSlotTest {

    @Test
    void shouldImplementTwoInterfaces() {
        ConsultationSlot slot = new ConsultationSlot("Teacher", "Java interfaces");

        assertTrue(slot instanceof Notifiable);
        assertTrue(slot instanceof Schedulable);
    }

    @Test
    void shouldScheduleConsultation() {
        ConsultationSlot slot = new ConsultationSlot("Teacher", "Java interfaces");

        String result = slot.schedule("21.05.2026 14:00");

        assertEquals("21.05.2026 14:00", slot.getTime());
        assertTrue(result.contains("Consultation with Teacher"));
        assertTrue(result.contains("21.05.2026 14:00"));
    }

    @Test
    void shouldShowMessageIfConsultationIsNotScheduled() {
        ConsultationSlot slot = new ConsultationSlot("Teacher", "Java interfaces");

        String result = slot.notifyStudent("Zakhar");

        assertTrue(result.contains("not scheduled yet"));
    }

    @Test
    void shouldNotifyStudentAfterScheduling() {
        ConsultationSlot slot = new ConsultationSlot("Teacher", "Java interfaces");

        slot.schedule("21.05.2026 14:00");
        String result = slot.notifyStudent("Zakhar");

        assertTrue(result.contains("Zakhar"));
        assertTrue(result.contains("Teacher"));
        assertTrue(result.contains("21.05.2026 14:00"));
    }
}