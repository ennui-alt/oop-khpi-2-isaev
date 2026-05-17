package ua.khpi.oop.lab07.contracts;

import org.junit.jupiter.api.Test;
import ua.khpi.oop.lab07.model.ConsultationSlot;
import ua.khpi.oop.lab07.model.RoomBooking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SchedulableContractTest {

    @Test
    void shouldScheduleDifferentObjects() {
        Schedulable[] items = {
                new RoomBooking("924", "Java laboratory work"),
                new ConsultationSlot("Teacher", "Java interfaces")
        };

        for (Schedulable item : items) {
            String result = item.schedule("20.05.2026 12:30");

            assertFalse(result.isBlank());
            assertTrue(result.contains("20.05.2026 12:30"));
        }
    }
}