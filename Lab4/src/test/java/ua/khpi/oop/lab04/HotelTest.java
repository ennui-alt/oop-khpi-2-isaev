package ua.khpi.oop.lab04;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class HotelTest {

    private Hotel hotel;
    private Guest guest;
    private Room room;

    @BeforeMethod
    public void setUp() {
        hotel = new Hotel("Sunrise", "Kyiv");
        guest = new Guest("Zahar", "0991234567");
        room = new Room(101, 1500.0);
    }

    @Test(groups = "fast")
    public void testConstructorAndCollections() {
        assertEquals(hotel.getName(), "Sunrise");
        assertEquals(hotel.getCity(), "Kyiv");
        assertNotNull(hotel.getRooms());
        assertNotNull(hotel.getReservations());
    }

    @Test(groups = "domain")
    public void testAddRoom() {
        hotel.addRoom(room);
        assertEquals(hotel.getRooms().size(), 1);
        assertTrue(hotel.getRooms().contains(room));
    }

    @Test(groups = "domain")
    public void testCreateReservation() {
        hotel.createReservation(guest, room, "2026-04-23");
        assertEquals(hotel.getReservations().size(), 1);
        assertEquals(hotel.getReservations().get(0).getGuest(), guest);
    }
}