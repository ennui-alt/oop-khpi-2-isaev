package ua.khpi.oop.lab15.service;

import ua.khpi.oop.lab15.model.BookingRequest;
import ua.khpi.oop.lab15.model.HotelRoom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BookingService {
    private final List<HotelRoom> rooms;

    public BookingService(List<HotelRoom> rooms) {
        if (rooms == null || rooms.isEmpty()) {
            throw new IllegalArgumentException("У готелі має бути хоча б одна кімната");
        }

        this.rooms = new ArrayList<>(rooms);
    }

    public Integer bookUnsafe(BookingRequest request) {
        Objects.requireNonNull(request, "Заявка на бронювання не може бути null");

        for (HotelRoom room : rooms) {
            if (room.bookUnsafe(request.getPlaces())) {
                return room.getRoomNumber();
            }
        }

        return null;
    }

    public Integer bookSafe(BookingRequest request) {
        Objects.requireNonNull(request, "Заявка на бронювання не може бути null");

        for (HotelRoom room : rooms) {
            if (room.bookSafe(request.getPlaces())) {
                return room.getRoomNumber();
            }
        }

        return null;
    }

    public int getTotalCapacity() {
        return rooms.stream()
                .mapToInt(HotelRoom::getCapacity)
                .sum();
    }

    public int getTotalBookedPlaces() {
        return rooms.stream()
                .mapToInt(HotelRoom::getBookedPlaces)
                .sum();
    }

    public int getTotalFreePlaces() {
        return rooms.stream()
                .mapToInt(HotelRoom::getFreePlaces)
                .sum();
    }

    public List<HotelRoom> getRooms() {
        return Collections.unmodifiableList(rooms);
    }

    public void clearBookings() {
        for (HotelRoom room : rooms) {
            room.clearBookings();
        }
    }

    public static BookingService createHotel(int roomCount, int placesPerRoom) {
        if (roomCount <= 0 || placesPerRoom <= 0) {
            throw new IllegalArgumentException("Кількість кімнат і місць має бути додатною");
        }

        List<HotelRoom> hotelRooms = new ArrayList<>();

        for (int i = 1; i <= roomCount; i++) {
            hotelRooms.add(new HotelRoom(i, placesPerRoom));
        }

        return new BookingService(hotelRooms);
    }
}