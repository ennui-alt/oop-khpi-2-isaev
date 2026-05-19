package ua.khpi.oop.lab15.threads;

import ua.khpi.oop.lab15.model.BookingRequest;
import ua.khpi.oop.lab15.service.BookingService;

public class BookingTask implements Runnable {
    public enum Mode {
        UNSAFE,
        SAFE
    }

    private final BookingService bookingService;
    private final Mode mode;
    private final int requestsCount;
    private final String guestPrefix;

    private int successfulBookings;
    private int failedBookings;

    public BookingTask(BookingService bookingService, Mode mode, int requestsCount, String guestPrefix) {
        if (requestsCount <= 0) {
            throw new IllegalArgumentException("Кількість заявок має бути додатною");
        }

        this.bookingService = bookingService;
        this.mode = mode;
        this.requestsCount = requestsCount;
        this.guestPrefix = guestPrefix;
    }

    @Override
    public void run() {
        for (int i = 1; i <= requestsCount; i++) {
            BookingRequest request = new BookingRequest(
                    i,
                    guestPrefix + "-" + i,
                    1
            );

            Integer roomNumber;

            if (mode == Mode.SAFE) {
                roomNumber = bookingService.bookSafe(request);
            } else {
                roomNumber = bookingService.bookUnsafe(request);
            }

            if (roomNumber != null) {
                successfulBookings++;
            } else {
                failedBookings++;
            }
        }
    }

    public int getSuccessfulBookings() {
        return successfulBookings;
    }

    public int getFailedBookings() {
        return failedBookings;
    }
}