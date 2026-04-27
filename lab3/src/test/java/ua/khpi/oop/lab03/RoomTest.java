package ua.khpi.oop.lab03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room(101, 2500.0);
    }

    @Test
    void constructorShouldCreateObjectWithCorrectArguments() {
        assertEquals(101, room.getNumber());
        assertEquals(2500.0, room.getPrice(), 0.001);
    }

    @Test
    void negativeScenarioShouldCreateRoomWithDefaultPrice() {
        Room testRoom = new Room(102);

        assertEquals(102, testRoom.getNumber());
        assertEquals(0.0, testRoom.getPrice(), 0.001);
    }

    @Test
    void changePriceShouldChangeObjectState() {
        room.changePrice(2200.0);

        assertEquals(2200.0, room.getPrice(), 0.001);
    }

    @Test
    void isExpensiveShouldReturnFalseForBoundaryValue() {
        Room testRoom = new Room(101, 2000.0);

        assertFalse(testRoom.isExpensive());
    }
}