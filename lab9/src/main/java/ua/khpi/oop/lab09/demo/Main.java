package ua.khpi.oop.lab09.demo;

import ua.khpi.oop.lab09.model.Customer;
import ua.khpi.oop.lab09.model.Reservation;
import ua.khpi.oop.lab09.model.Room;
import ua.khpi.oop.lab09.model.BookingLink;
import ua.khpi.oop.lab09.model.BookingUtils;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Customer customer1 = new Customer("Zakhar Isaiev", "+380991112233");
        Customer customer2 = new Customer("Oleg Vinnik", "+380971112233");
        Customer customer3 = new Customer("Dzidzio", "+380501234567");

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

        List<Reservation> reservations = Arrays.asList(
                reservation1,
                reservation2,
                reservation3
        );

        BookingLink<Customer, Room> link1 = new BookingLink<>(customer1, room1);
        BookingLink<Customer, Reservation> link2 = new BookingLink<>(customer1, reservation1);

        System.out.println("Generic link with room:");
        System.out.println(link1);

        System.out.println();

        System.out.println("Generic link with reservation:");
        System.out.println(link2);

        System.out.println();

        System.out.println("All reservations:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }

        System.out.println();

        System.out.println("Confirmed reservations:");
        for (Reservation reservation : BookingUtils.getConfirmed(reservations)) {
            System.out.println(reservation);
        }

        System.out.println();

        boolean busy = BookingUtils.isRoomBusy(
                reservations,
                room1,
                LocalDate.of(2026, 5, 21),
                LocalDate.of(2026, 5, 22)
        );

        System.out.println("Is room 101 busy?");
        System.out.println(busy);

        System.out.println();

        Reservation firstReservation = BookingUtils.findSmallest(reservations);
        Room cheapestRoom = BookingUtils.findSmallest(Arrays.asList(room1, room2));

        System.out.println("First reservation:");
        System.out.println(firstReservation);

        System.out.println();

        System.out.println("Cheapest room:");
        System.out.println(cheapestRoom);
    }
}