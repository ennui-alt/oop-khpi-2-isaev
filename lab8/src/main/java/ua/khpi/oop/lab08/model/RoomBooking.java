package ua.khpi.oop.lab08.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class RoomBooking extends CampusService {
    private static final long serialVersionUID = 1L;

    private String roomNumber;
    private String purpose;

    public RoomBooking(String id,
                       String studentName,
                       LocalDate date,
                       LocalTime time,
                       String roomNumber,
                       String purpose) {
        super(id, studentName, date, time);

        if (roomNumber == null || roomNumber.isBlank()) {
            throw new IllegalArgumentException("Room number must not be empty");
        }

        if (purpose == null || purpose.isBlank()) {
            throw new IllegalArgumentException("Purpose must not be empty");
        }

        this.roomNumber = roomNumber;
        this.purpose = purpose;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getPurpose() {
        return purpose;
    }

    @Override
    public String getType() {
        return "ROOM";
    }

    @Override
    public String toTextLine() {
        return getType() + ";" + baseTextLine() + ";" + roomNumber + ";" + purpose;
    }

    @Override
    public String toString() {
        return "RoomBooking {id='" + getId()
                + "', studentName='" + getStudentName()
                + "', date=" + getDate()
                + ", time=" + getTime()
                + ", roomNumber='" + roomNumber
                + "', purpose='" + purpose
                + "'}";
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) {
            return false;
        }

        if (!(other instanceof RoomBooking booking)) {
            return false;
        }

        return Objects.equals(roomNumber, booking.roomNumber)
                && Objects.equals(purpose, booking.purpose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), roomNumber, purpose);
    }
}