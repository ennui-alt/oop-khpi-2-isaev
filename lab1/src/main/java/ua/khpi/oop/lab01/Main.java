package ua.khpi.oop.lab01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Введення даних гостя ===");
        System.out.print("Ім'я: ");
        String guestName = sc.nextLine();

        System.out.print("Телефон: ");
        String guestPhone = sc.nextLine();

        Guest guest1 = new Guest(guestName, guestPhone);

        System.out.println("\n=== Введення даних кімнати ===");
        System.out.print("Номер кімнати: ");
        int roomNumber = sc.nextInt();

        System.out.print("Ціна: ");
        double roomPrice = sc.nextDouble();
        sc.nextLine();

        Room room1 = new Room(roomNumber, roomPrice);

        System.out.println("\n=== Введення даних готелю ===");
        System.out.print("Назва готелю: ");
        String hotelName = sc.nextLine();

        System.out.print("Місто: ");
        String hotelCity = sc.nextLine();

        Hotel hotel = new Hotel(hotelName, hotelCity);

        System.out.println("\n=== Введення бронювання ===");
        System.out.print("Дата бронювання: ");
        String date = sc.nextLine();

        Reservation reservation1 = new Reservation(guest1, room1, date);

        hotel.addReservation(reservation1);

        System.out.println("\n=== toString() ===");
        System.out.println(guest1);
        System.out.println();
        System.out.println(room1);
        System.out.println();
        System.out.println(hotel);
        System.out.println();
        System.out.println(reservation1);

        System.out.println("\n=== Бронювання в готелі ===");
        hotel.printReservations();

        System.out.println("=== Створіть ще одного гостя для порівняння ===");
        System.out.print("Ім'я: ");
        String guestName2 = sc.nextLine();

        System.out.print("Телефон: ");
        String guestPhone2 = sc.nextLine();

        Guest guest2 = new Guest(guestName2, guestPhone2);

        System.out.println("\n=== equals() ===");
        if (guest1.equals(guest2)) {
            System.out.println("Гості ОДНАКОВІ");
        } else {
            System.out.println("Гості РІЗНІ");
        }

        System.out.println("\n=== hashCode() ===");
        System.out.println("guest1: " + guest1.hashCode());
        System.out.println("guest2: " + guest2.hashCode());

        sc.close();
    }
}