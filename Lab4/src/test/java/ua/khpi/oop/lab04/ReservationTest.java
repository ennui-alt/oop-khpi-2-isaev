package ua.khpi.oop.lab04;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ReservationTest {

    private Reservation reservation;
    private Guest guest;
    private Room room;

    @BeforeMethod
    public void setUp() {
        guest = new Guest("Zahar", "0991234567");
        room = new Room(101, 1500.0);
        reservation = new Reservation(guest, room, "2026-04-23");
    }

    @Test(groups = "fast")
    public void testConstructorAndGetters() {
        assertEquals(reservation.getGuest(), guest);
        assertEquals(reservation.getRoom(), room);
        assertEquals(reservation.getDate(), "2026-04-23");
    }

    @Test(groups = "domain")
    public void testChangeDate() {
        reservation.changeDate("2026-05-01");
        assertEquals(reservation.getDate(), "2026-05-01");
    }

    @Test(groups = "fast")
    public void testToString() {
        String expected = "Reservation:\n guest: Zahar\n room: 101\n date: 2026-04-23";
        assertEquals(reservation.toString(), expected);
    }
}