package ua.khpi.oop.lab04;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RoomTest {

    private Room room;

    @BeforeMethod
    public void setUp() {
        room = new Room(101, 1500.0);
    }

    @DataProvider(name = "roomPrices")
    public Object[][] roomPrices() {
        return new Object[][]{
                {1500.0, false},
                {2000.0, false},
                {2500.0, true}
        };
    }

    @Test(groups = "fast")
    public void testConstructorAndGetters() {
        assertEquals(room.getNumber(), 101);
        assertEquals(room.getPrice(), 1500.0);
    }

    @Test(dataProvider = "roomPrices", groups = "domain")
    public void testIsExpensive(double price, boolean expected) {
        Room testRoom = new Room(200, price);
        assertEquals(testRoom.isExpensive(), expected);
    }

    @Test(groups = "domain")
    public void testChangePrice() {
        room.changePrice(2100.0);

        assertEquals(room.getPrice(), 2100.0);
        assertTrue(room.isExpensive());
    }

    @Test(expectedExceptions = IllegalArgumentException.class, groups = "fast")
    public void testConstructorShouldThrowExceptionForNegativePrice() {
        new Room(300, -100.0);
    }
}