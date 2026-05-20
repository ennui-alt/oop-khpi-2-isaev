package ua.khpi.oop.lab04;

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

        hotel.addRoom(room1);

        System.out.println("\n=== Введення бронювання ===");
        System.out.print("Дата бронювання: ");
        String date = sc.nextLine();

        hotel.createReservation(guest1, room1, date);

        System.out.println("\n=== toString() ===");
        System.out.println(guest1);
        System.out.println(room1);
        System.out.println(hotel);

        System.out.println("\n=== Бронювання готелю ===");
        for (Reservation reservation : hotel.getReservations()) {
            System.out.println(reservation);
        }

        sc.close();
    }
}