package ua.khpi.oop.lab09.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReservationTest {

    @Test
    void shouldCalculateDaysCorrectly() {
        Customer customer = new Customer("Zakhar Isaiev", "+380991112233");
        Room room = new Room(101, "Standard", 1200);

        Reservation reservation = new Reservation(
                customer,
                room,
                LocalDate.of(2026, 5, 20),
                LocalDate.of(2026, 5, 23),
                true
        );

        assertEquals(3, reservation.getDays());
    }

    @Test
    void shouldCalculateTotalPriceCorrectly() {
        Customer customer = new Customer("Zakhar Isaiev", "+380991112233");
        Room room = new Room(101, "Standard", 1200);

        Reservation reservation = new Reservation(
                customer,
                room,
                LocalDate.of(2026, 5, 20),
                LocalDate.of(2026, 5, 23),
                true
        );

        assertEquals(3600.0, reservation.getTotalPrice(), 0.001);
    }

    @Test
    void shouldDetectDateConflictForSameRoom() {
        Customer customer = new Customer("Zakhar Isaiev", "+380991112233");
        Room room = new Room(101, "Standard", 1200);

        Reservation reservation = new Reservation(
                customer,
                room,
                LocalDate.of(2026, 5, 20),
                LocalDate.of(2026, 5, 23),
                true
        );

        boolean result = reservation.hasConflict(
                room,
                LocalDate.of(2026, 5, 21),
                LocalDate.of(2026, 5, 22)
        );

        assertTrue(result);
    }

    @Test
    void shouldNotDetectConflictForDifferentRoom() {
        Customer customer = new Customer("Zakhar Isaiev", "+380991112233");

        Room room1 = new Room(101, "Standard", 1200);
        Room room2 = new Room(202, "Lux", 2500);

        Reservation reservation = new Reservation(
                customer,
                room1,
                LocalDate.of(2026, 5, 20),
                LocalDate.of(2026, 5, 23),
                true
        );

        boolean result = reservation.hasConflict(
                room2,
                LocalDate.of(2026, 5, 21),
                LocalDate.of(2026, 5, 22)
        );

        assertFalse(result);
    }

    @Test
    void shouldNotDetectConflictWhenDatesDoNotOverlap() {
        Customer customer = new Customer("Zakhar Isaiev", "+380991112233");
        Room room = new Room(101, "Standard", 1200);

        Reservation reservation = new Reservation(
                customer,
                room,
                LocalDate.of(2026, 5, 20),
                LocalDate.of(2026, 5, 23),
                true
        );

        boolean result = reservation.hasConflict(
                room,
                LocalDate.of(2026, 5, 24),
                LocalDate.of(2026, 5, 26)
        );

        assertFalse(result);
    }
}