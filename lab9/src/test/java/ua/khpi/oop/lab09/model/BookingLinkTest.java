package ua.khpi.oop.lab09.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookingLinkTest {

    @Test
    void shouldCreateLinkBetweenCustomerAndRoom() {
        Customer customer = new Customer("Zakhar Isaiev", "+380991112233");
        Room room = new Room(101, "Standard", 1200);

        BookingLink<Customer, Room> link = new BookingLink<>(customer, room);

        assertSame(customer, link.getCustomer());
        assertSame(room, link.getResource());
    }

    @Test
    void shouldCreateLinkBetweenCustomerAndReservation() {
        Customer customer = new Customer("Zakhar Isaiev", "+380991112233");
        Room room = new Room(101, "Standard", 1200);

        Reservation reservation = new Reservation(
                customer,
                room,
                LocalDate.of(2026, 5, 20),
                LocalDate.of(2026, 5, 23),
                true
        );

        BookingLink<Customer, Reservation> link = new BookingLink<>(customer, reservation);

        assertSame(customer, link.getCustomer());
        assertSame(reservation, link.getResource());
    }

    @Test
    void shouldReturnReadableText() {
        Customer customer = new Customer("Zakhar Isaiev", "+380991112233");
        Room room = new Room(101, "Standard", 1200);

        BookingLink<Customer, Room> link = new BookingLink<>(customer, room);

        String text = link.toString();

        assertTrue(text.contains("Zakhar Isaiev"));
        assertTrue(text.contains("Room 101"));
    }
}