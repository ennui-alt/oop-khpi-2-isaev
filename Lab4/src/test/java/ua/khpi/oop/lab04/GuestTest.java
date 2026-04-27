package ua.khpi.oop.lab04;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GuestTest {

    private Guest guest;

    @BeforeMethod
    public void setUp() {
        guest = new Guest("Zahar", "0991234567");
    }

    @Test(groups = "fast")
    public void testConstructorAndGetters() {
        assertEquals(guest.getName(), "Zahar");
        assertEquals(guest.getPhone(), "0991234567");
    }

    @Test(groups = "domain")
    public void testChangePhone() {
        guest.changePhone("0671112233");
        assertEquals(guest.getPhone(), "0671112233");
    }

    @Test(groups = "fast")
    public void testConstructorWithOnlyName() {
        Guest testGuest = new Guest("Ivan");
        assertEquals(testGuest.getName(), "Ivan");
        assertEquals(testGuest.getPhone(), "не вказано");
    }
}