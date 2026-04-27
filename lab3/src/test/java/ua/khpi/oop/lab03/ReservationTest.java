package ua.khpi.oop.lab03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    private Guest guest;
    private Room room;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        guest = new Guest("Zahar", "0991234567");
        room = new Room(101, 2500.0);
        reservation = new Reservation(guest, room, "01.04.2026");
    }

    @Test
    void constructorShouldCreateObjectWithCorrectArguments() {
        assertEquals(guest, reservation.getGuest());
        assertEquals(room, reservation.getRoom());
        assertEquals("01.04.2026", reservation.getDate());
    }

    @Test
    void negativeScenarioShouldSetDefaultDate() {
        Reservation testReservation = new Reservation(guest, room);

        assertEquals("не вказано", testReservation.getDate());
    }

    @Test
    void changeDateShouldChangeObjectState() {
        reservation.changeDate("10.04.2026");

        assertEquals("10.04.2026", reservation.getDate());
    }

    @Test
    void equalsShouldReturnFalseForNull() {
        assertNotEquals(null, reservation);
    }
}