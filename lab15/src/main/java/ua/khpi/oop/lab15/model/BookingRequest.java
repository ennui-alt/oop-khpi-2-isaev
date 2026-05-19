package ua.khpi.oop.lab15.model;

import java.util.Objects;

public class BookingRequest {
    private final int requestId;
    private final String guestName;
    private final int places;

    public BookingRequest(int requestId, String guestName, int places) {
        if (requestId <= 0) {
            throw new IllegalArgumentException("Номер заявки має бути додатним");
        }
        if (places <= 0) {
            throw new IllegalArgumentException("Кількість місць має бути додатною");
        }

        this.requestId = requestId;
        this.guestName = Objects.requireNonNull(guestName, "Ім'я гостя не може бути null");
        this.places = places;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getPlaces() {
        return places;
    }

    @Override
    public String toString() {
        return "Заявка на бронювання{" +
                "номер заявки=" + requestId +
                ", гість='" + guestName + '\'' +
                ", місць=" + places +
                '}';
    }
}