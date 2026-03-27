package ua.khpi.oop.lab01;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hotel {
    private String name;
    private String city;
    private List<Reservation> reservations;

    public Hotel(String name, String city) {
        this.name = name;
        this.city = city;
        this.reservations = new ArrayList<>();
    }

    public Hotel(String name) {
        this(name, "не вказано");
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void renameHotel(String newName) {
        this.name = newName;
    }

    public void changeCity(String newCity) {
        this.city = newCity;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void printReservations() {
        if (reservations.isEmpty()) {
            System.out.println("У готелі немає бронювань.");
            return;
        }

        for (Reservation reservation : reservations) {
            System.out.println(reservation);
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Hotel:\n  name: %s\n  city: %s\n  reservations count: %d",
                name, city, reservations.size()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(name, hotel.name) &&
                Objects.equals(city, hotel.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city);
    }
}