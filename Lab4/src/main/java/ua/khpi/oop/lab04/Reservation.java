package ua.khpi.oop.lab04;

import java.util.Objects;

public class Reservation {
    private Guest guest;
    private Room room;
    private String date;

    public Reservation(Guest guest, Room room, String date) {
        this.guest = guest;
        this.room = room;
        this.date = date;
    }

    public Reservation(Guest guest, Room room) {
        this(guest, room, "не вказано");
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public String getDate() {
        return date;
    }

    public void changeDate(String newDate) {
        this.date = newDate;
    }

    @Override
    public String toString() {
        return String.format(
                "Reservation:\n guest: %s\n room: %s\n date: %s",
                guest.getName(), room.getNumber(), date
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(guest, that.guest) &&
                Objects.equals(room, that.room) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guest, room, date);
    }
}