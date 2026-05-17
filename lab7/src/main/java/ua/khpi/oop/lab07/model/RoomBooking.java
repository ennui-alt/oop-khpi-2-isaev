package ua.khpi.oop.lab07.model;

import ua.khpi.oop.lab07.contracts.Schedulable;

public class RoomBooking implements Schedulable {

    private final String roomNumber;
    private final String purpose;
    private String time;

    public RoomBooking(String roomNumber, String purpose) {
        this.roomNumber = roomNumber;
        this.purpose = purpose;
        this.time = "not scheduled";
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String schedule(String dateTime) {
        this.time = dateTime;
        return "Room " + roomNumber + " is scheduled for "
                + purpose + " at " + time + ".";
    }
}