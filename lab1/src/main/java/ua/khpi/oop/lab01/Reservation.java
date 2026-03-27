package ua.khpi.oop.lab01;

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

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void changeDate(String newDate) {
        this.date = newDate;
    }

    public void changeRoom(Room newRoom) {
        this.room = newRoom;
    }

    @Override
    public String toString() {
        return String.format(
                "Reservation:\n%s\n%s\n  date: %s",
                guest, room, date
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
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