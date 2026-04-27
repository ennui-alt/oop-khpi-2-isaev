package ua.khpi.oop.lab03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {

    private Guest guest;

    @BeforeEach
    void setUp() {
        guest = new Guest("Zahar", "0991234567");
    }

    @Test
    void constructorShouldCreateObjectWithCorrectArguments() {
        assertEquals("Zahar", guest.getName());
        assertEquals("0991234567", guest.getPhone());
    }

    @Test
    void negativeScenarioShouldCreateGuestWithDefaultPhone() {
        Guest testGuest = new Guest("Ivan");

        assertEquals("Ivan", testGuest.getName());
        assertEquals("не вказано", testGuest.getPhone());
    }

    @Test
    void changePhoneShouldChangeObjectState() {
        guest.changePhone("0670000000");

        assertEquals("0670000000", guest.getPhone());
    }

    @Test
    void equalsShouldReturnFalseForNull() {
        assertNotEquals(null, guest);
    }
}