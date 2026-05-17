package ua.khpi.oop.lab07.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RoomBookingTest {

    @Test
    void shouldCreateRoomBooking() {
        RoomBooking booking = new RoomBooking("924", "Java laboratory work");

        assertEquals("924", booking.getRoomNumber());
        assertEquals("Java laboratory work", booking.getPurpose());
        assertEquals("not scheduled", booking.getTime());
    }

    @Test
    void shouldScheduleRoom() {
        RoomBooking booking = new RoomBooking("924", "Java laboratory work");

        String result = booking.schedule("20.05.2026 12:30");

        assertEquals("20.05.2026 12:30", booking.getTime());
        assertTrue(result.contains("Room 924"));
        assertTrue(result.contains("20.05.2026 12:30"));
    }
}