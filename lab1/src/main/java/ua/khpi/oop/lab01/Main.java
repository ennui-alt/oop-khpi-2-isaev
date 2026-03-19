package ua.khpi.oop.lab01;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введіть назву готелю: ");
        String hotelName = sc.nextLine();

        System.out.print("Введіть місто: ");
        String hotelCity = sc.nextLine();

        Hotel hotel = new Hotel(hotelName, hotelCity);

        System.out.print("Введіть ім'я гостя: ");
        String guestName = sc.nextLine();

        System.out.print("Введіть телефон: ");
        String guestPhone = sc.nextLine();

        Guest guest = new Guest(guestName, guestPhone);

        System.out.print("Введіть номер кімнати: ");
        int roomNumber = sc.nextInt();
        sc.nextLine();

        System.out.print("Введіть ціну кімнати: ");
        double roomPrice = sc.nextDouble();
        sc.nextLine();

        Room room = new Room(roomNumber, roomPrice);

        System.out.print("Введіть дату бронювання (дд.мм.рррр): ");
        String date = sc.nextLine();

        Reservation reservation = new Reservation(guest, room, date);

        System.out.println("\n" + hotel);
        System.out.println(reservation);
    }
}