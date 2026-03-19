package ua.khpi.oop.lab01;

import java.util.Objects;

public class Reservation {

    Guest guest;
    Room room;
    String date;

    public Reservation(Guest guest, Room room, String date) {
        this.guest = guest;
        this.room = room;
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format(
                "Бронювання:\n%s\n%s\n  Дата: %s", guest, room, date
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(guest, room, date);
    }
}