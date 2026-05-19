package ua.khpi.oop.lab15.model;

import java.util.concurrent.locks.LockSupport;

public class HotelRoom {
    private final int roomNumber;
    private final int capacity;
    private int bookedPlaces;

    public HotelRoom(int roomNumber, int capacity) {
        if (roomNumber <= 0) {
            throw new IllegalArgumentException("Номер кімнати має бути додатним");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Місткість кімнати має бути додатною");
        }

        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.bookedPlaces = 0;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public synchronized int getBookedPlaces() {
        return bookedPlaces;
    }

    public synchronized int getFreePlaces() {
        return capacity - bookedPlaces;
    }

    public boolean bookUnsafe(int places) {
        checkPlaces(places);

        if (bookedPlaces + places <= capacity) {
            int currentBooked = bookedPlaces;
            LockSupport.parkNanos(1L);
            bookedPlaces = currentBooked + places;
            return true;
        }

        return false;
    }

    public synchronized boolean bookSafe(int places) {
        checkPlaces(places);

        if (bookedPlaces + places > capacity) {
            return false;
        }

        bookedPlaces += places;
        return true;
    }

    public synchronized void clearBookings() {
        bookedPlaces = 0;
    }

    private void checkPlaces(int places) {
        if (places <= 0) {
            throw new IllegalArgumentException("Кількість місць має бути додатною");
        }
    }

    @Override
    public synchronized String toString() {
        return "Кімната готелю{" +
                "номер=" + roomNumber +
                ", місткість=" + capacity +
                ", заброньовано=" + bookedPlaces +
                ", вільно=" + getFreePlaces() +
                '}';
    }
}