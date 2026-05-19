package ua.khpi.oop.lab15.threads;

import org.junit.jupiter.api.Test;
import ua.khpi.oop.lab15.service.BookingService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookingTaskTest {

    @Test
    void taskShouldBookRequestsInSafeMode() {
        BookingService service = BookingService.createHotel(1, 5);
        BookingTask task = new BookingTask(service, BookingTask.Mode.SAFE, 3, "Тестовий гість");

        task.run();

        assertEquals(3, task.getSuccessfulBookings(), "Завдання має виконати три успішні бронювання");
        assertEquals(0, task.getFailedBookings(), "Невдалих бронювань не повинно бути");
        assertEquals(3, service.getTotalBookedPlaces(), "У готелі має бути три зайняті місця");
    }

    @Test
    void taskShouldCountFailedRequestsWhenHotelIsFull() {
        BookingService service = BookingService.createHotel(1, 2);
        BookingTask task = new BookingTask(service, BookingTask.Mode.SAFE, 5, "Тестовий гість");

        task.run();

        assertEquals(2, task.getSuccessfulBookings(), "Успішних бронювань має бути тільки два");
        assertEquals(3, task.getFailedBookings(), "Решта заявок має бути невдалою");
        assertEquals(2, service.getTotalBookedPlaces(), "Зайнятих місць не може бути більше за місткість");
        assertEquals(0, service.getTotalFreePlaces(), "Вільних місць не повинно залишитися");
    }

    @Test
    void severalThreadsShouldNotBookMoreThanCapacityInSafeMode() throws InterruptedException {
        BookingService service = BookingService.createHotel(3, 10);

        int threadCount = 4;
        int requestsPerThread = 1000;

        BookingTask[] tasks = new BookingTask[threadCount];
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            tasks[i] = new BookingTask(
                    service,
                    BookingTask.Mode.SAFE,
                    requestsPerThread,
                    "Потоковий гість-" + (i + 1)
            );

            threads[i] = new Thread(tasks[i]);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int successfulBookings = 0;

        for (BookingTask task : tasks) {
            successfulBookings += task.getSuccessfulBookings();
        }

        assertEquals(30, service.getTotalCapacity(), "Загальна місткість має бути 30 місць");
        assertEquals(30, service.getTotalBookedPlaces(), "Синхронізований варіант не повинен перевищувати місткість");
        assertEquals(30, successfulBookings, "Успішних бронювань має бути рівно стільки, скільки місць у готелі");
        assertEquals(0, service.getTotalFreePlaces(), "Після заповнення готелю вільних місць не має бути");
    }

    @Test
    void constructorShouldRejectInvalidRequestsCount() {
        BookingService service = BookingService.createHotel(1, 5);

        assertThrows(
                IllegalArgumentException.class,
                () -> new BookingTask(service, BookingTask.Mode.SAFE, 0, "Гість"),
                "Кількість заявок має бути додатною"
        );
    }
}