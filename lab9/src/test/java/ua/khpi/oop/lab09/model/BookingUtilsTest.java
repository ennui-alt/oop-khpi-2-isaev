package ua.khpi.oop.lab09.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingUtilsTest {

    private List<Reservation> createReservations() {
        Customer customer1 = new Customer("Zakhar Isaiev", "+380991112233");
        Customer customer2 = new Customer("Oleg Vinnik", "+380971112233");
        Customer customer3 = new Customer("Ivan Petrenko", "+380501234567");

        Room room1 = new Room(101, "Standard", 1200);
        Room room2 = new Room(202, "Lux", 2500);

        Reservation reservation1 = new Reservation(
                customer1,
                room1,
                LocalDate.of(2026, 5, 20),
                LocalDate.of(2026, 5, 23),
                true
        );

        Reservation reservation2 = new Reservation(
                customer2,
                room2,
                LocalDate.of(2026, 5, 24),
                LocalDate.of(2026, 5, 26),
                false
        );

        Reservation reservation3 = new Reservation(
                customer3,
                room1,
                LocalDate.of(2026, 5, 28),
                LocalDate.of(2026, 5, 30),
                true
        );

        return Arrays.asList(reservation1, reservation2, reservation3);
    }

    @Test
    void shouldReturnOnlyConfirmedReservations() {
        List<Reservation> reservations = createReservations();

        List<Reservation> confirmed = BookingUtils.getConfirmed(reservations);

        assertEquals(2, confirmed.size());
        assertTrue(confirmed.get(0).isConfirmed());
        assertTrue(confirmed.get(1).isConfirmed());
    }

    @Test
    void shouldDetectThatRoomIsBusy() {
        List<Reservation> reservations = createReservations();

        Room room = reservations.get(0).getRoom();

        boolean result = BookingUtils.isRoomBusy(
                reservations,
                room,
                LocalDate.of(2026, 5, 21),
                LocalDate.of(2026, 5, 22)
        );

        assertTrue(result);
    }

    @Test
    void shouldDetectThatRoomIsFree() {
        List<Reservation> reservations = createReservations();

        Room room = reservations.get(0).getRoom();

        boolean result = BookingUtils.isRoomBusy(
                reservations,
                room,
                LocalDate.of(2026, 5, 24),
                LocalDate.of(2026, 5, 27)
        );

        assertFalse(result);
    }

    @Test
    void shouldFindFirstReservation() {
        List<Reservation> reservations = createReservations();

        Reservation result = BookingUtils.findSmallest(reservations);

        assertEquals(LocalDate.of(2026, 5, 20), result.getFrom());
    }

    @Test
    void shouldFindCheapestRoom() {
        Room room1 = new Room(101, "Standard", 1200);
        Room room2 = new Room(202, "Lux", 2500);

        Room result = BookingUtils.findSmallest(Arrays.asList(room1, room2));

        assertSame(room1, result);
    }

    @Test
    void shouldReturnNullForEmptyList() {
        List<Reservation> emptyList = List.of();

        Reservation result = BookingUtils.findSmallest(emptyList);

        assertNull(result);
    }
}