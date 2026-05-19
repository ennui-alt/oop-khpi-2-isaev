package ua.khpi.oop.lab15.service;

import org.junit.jupiter.api.Test;
import ua.khpi.oop.lab15.model.BookingRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookingServiceTest {

    @Test
    void createHotelShouldHaveCorrectCapacity() {
        BookingService service = BookingService.createHotel(3, 2);

        assertEquals(6, service.getTotalCapacity(), "Загальна місткість готелю має бути правильною");
        assertEquals(0, service.getTotalBookedPlaces(), "На початку не має бути зайнятих місць");
        assertEquals(6, service.getTotalFreePlaces(), "На початку всі місця мають бути вільними");
    }

    @Test
    void safeBookingShouldNotBookMoreThanHotelCapacity() {
        BookingService service = BookingService.createHotel(2, 2);

        assertNotNull(service.bookSafe(new BookingRequest(1, "Гість 1", 1)));
        assertNotNull(service.bookSafe(new BookingRequest(2, "Гість 2", 1)));
        assertNotNull(service.bookSafe(new BookingRequest(3, "Гість 3", 1)));
        assertNotNull(service.bookSafe(new BookingRequest(4, "Гість 4", 1)));

        assertNull(service.bookSafe(new BookingRequest(5, "Гість 5", 1)));

        assertEquals(4, service.getTotalCapacity(), "Місткість готелю не повинна змінюватися");
        assertEquals(4, service.getTotalBookedPlaces(), "Зайнятих місць не може бути більше за місткість");
        assertEquals(0, service.getTotalFreePlaces(), "Після заповнення готелю вільних місць не має бути");
    }

    @Test
    void clearBookingsShouldResetBookedPlaces() {
        BookingService service = BookingService.createHotel(2, 3);

        service.bookSafe(new BookingRequest(1, "Гість 1", 1));
        service.bookSafe(new BookingRequest(2, "Гість 2", 1));

        assertEquals(2, service.getTotalBookedPlaces(), "Перед очищенням має бути два бронювання");

        service.clearBookings();

        assertEquals(0, service.getTotalBookedPlaces(), "Після очищення не має бути зайнятих місць");
        assertEquals(6, service.getTotalFreePlaces(), "Після очищення всі місця мають бути вільними");
    }

    @Test
    void serviceShouldRejectEmptyRoomList() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new BookingService(List.of()),
                "Сервіс не повинен створюватися без кімнат"
        );
    }

    @Test
    void createHotelShouldRejectInvalidValues() {
        assertThrows(
                IllegalArgumentException.class,
                () -> BookingService.createHotel(0, 2),
                "Кількість кімнат має бути додатною"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> BookingService.createHotel(2, 0),
                "Кількість місць має бути додатною"
        );
    }
}