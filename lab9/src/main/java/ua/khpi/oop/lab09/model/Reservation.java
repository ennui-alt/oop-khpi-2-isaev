package ua.khpi.oop.lab09.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation implements Comparable<Reservation> {
    private Customer customer;
    private Room room;
    private LocalDate from;
    private LocalDate to;
    private boolean confirmed;

    public Reservation(Customer customer, Room room, LocalDate from, LocalDate to, boolean confirmed) {
        this.customer = customer;
        this.room = room;
        this.from = from;
        this.to = to;
        this.confirmed = confirmed;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public long getDays() {
        return ChronoUnit.DAYS.between(from, to);
    }

    public double getTotalPrice() {
        return getDays() * room.getPrice();
    }

    public boolean hasConflict(Room checkedRoom, LocalDate checkedFrom, LocalDate checkedTo) {
        boolean sameRoom = room.getNumber() == checkedRoom.getNumber();

        boolean dateConflict = from.isBefore(checkedTo) && checkedFrom.isBefore(to);

        return sameRoom && dateConflict;
    }

    @Override
    public int compareTo(Reservation other) {
        return this.from.compareTo(other.from);
    }

    @Override
    public String toString() {
        return customer + " booked " + room +
                " from " + from +
                " to " + to +
                ", confirmed: " + confirmed +
                ", total: " + getTotalPrice();
    }
}