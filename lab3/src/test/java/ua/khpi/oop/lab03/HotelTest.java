package ua.khpi.oop.lab03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    private Hotel hotel;
    private Guest guest;
    private Room room;

    @BeforeEach
    void setUp() {
        hotel = new Hotel("Sun Hotel", "Kyiv");
        guest = new Guest("Zahar", "0991234567");
        room = new Room(101, 2500.0);
    }

    @Test
    void constructorShouldCreateObjectWithCorrectArguments() {
        assertEquals("Sun Hotel", hotel.getName());
        assertEquals("Kyiv", hotel.getCity());
        assertNotNull(hotel.getRooms());
        assertNotNull(hotel.getReservations());
        assertEquals(0, hotel.getRooms().size());
        assertEquals(0, hotel.getReservations().size());
    }

    @Test
    void negativeScenarioShouldNotAddReservationIfGuestIsNull() {
        hotel.createReservation(null, room, "01.04.2026");

        assertEquals(0, hotel.getReservations().size());
    }

    @Test
    void createReservationShouldChangeObjectState() {
        hotel.createReservation(guest, room, "01.04.2026");

        assertEquals(1, hotel.getReservations().size());
        assertEquals(guest, hotel.getReservations().get(0).getGuest());
        assertEquals(room, hotel.getReservations().get(0).getRoom());
        assertEquals("01.04.2026", hotel.getReservations().get(0).getDate());
    }

    @Test
    void addRoomShouldChangeObjectState() {
        hotel.addRoom(room);

        assertEquals(1, hotel.getRooms().size());
        assertTrue(hotel.getRooms().contains(room));
    }
}