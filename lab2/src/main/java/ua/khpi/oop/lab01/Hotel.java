package ua.khpi.oop.lab01;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hotel {
    private String name;
    private String city;

    private List<Room> rooms = new ArrayList<>();

    private List<Reservation> reservations = new ArrayList<>();

    public Hotel(String name, String city) {
        this.name = name;
        this.city = city;
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

    public List<Room> getRooms() {
        return rooms;
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

    public void addRoom(Room room) {
        if (room != null) {
            rooms.add(room);
        }
    }

    public void createReservation(Guest guest, Room room, String date) {
        if (guest != null && room != null) {
            reservations.add(new Reservation(guest, room, date));
        }
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    @Override
    public String toString() {
        return String.format(
                "Hotel:\n name: %s\n city: %s\n rooms: %d\n reservations: %d",
                name, city, rooms.size(), reservations.size()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(name, hotel.name) &&
                Objects.equals(city, hotel.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city);
    }
}