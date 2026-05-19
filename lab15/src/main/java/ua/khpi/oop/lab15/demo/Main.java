package ua.khpi.oop.lab15.demo;

import ua.khpi.oop.lab15.model.BookingRequest;
import ua.khpi.oop.lab15.service.BookingService;
import ua.khpi.oop.lab15.threads.BookingTask;

public class Main {
    private static final int THREAD_COUNT = 4;
    private static final int ITERATIONS = 100_000;
    private static final int ROOM_COUNT = 20;
    private static final int PLACES_PER_ROOM = 5;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Лабораторна робота №15");
        System.out.println("Варіант 3: бронювання номерів");
        System.out.println("Тема: синхронізація потоків у Java");
        System.out.println();

        runScenario(BookingTask.Mode.UNSAFE);
        runScenario(BookingTask.Mode.SAFE);
        compareSequentialAndParallel();
    }

    private static void runScenario(BookingTask.Mode mode) throws InterruptedException {
        BookingService service = BookingService.createHotel(ROOM_COUNT, PLACES_PER_ROOM);
        ScenarioResult result = runParallelTasks(service, mode, ITERATIONS);

        String title = mode == BookingTask.Mode.SAFE
                ? "СИНХРОНІЗОВАНИЙ ВАРІАНТ"
                : "ВАРІАНТ БЕЗ SYNCHRONIZED";

        System.out.println("=== " + title + " ===");
        System.out.println("Кількість потоків: " + THREAD_COUNT);
        System.out.println("Ітерацій на потік: " + ITERATIONS);
        System.out.println("Місткість готелю: " + result.capacity());
        System.out.println("Успішних бронювань: " + result.successfulBookings());
        System.out.println("Фактично зайнятих місць: " + result.bookedPlaces());
        System.out.println("Вільних місць: " + result.freePlaces());
        System.out.println("Час виконання: " + result.timeMillis() + " мс");
        System.out.println();
    }

    private static ScenarioResult runParallelTasks(
            BookingService service,
            BookingTask.Mode mode,
            int iterations
    ) throws InterruptedException {
        BookingTask[] tasks = new BookingTask[THREAD_COUNT];
        Thread[] threads = new Thread[THREAD_COUNT];

        long start = System.nanoTime();

        for (int i = 0; i < THREAD_COUNT; i++) {
            tasks[i] = new BookingTask(service, mode, iterations, "Гість-" + (i + 1));
            threads[i] = new Thread(tasks[i], "Потік-" + (i + 1));
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long timeMillis = (System.nanoTime() - start) / 1_000_000;

        int successfulBookings = 0;

        for (BookingTask task : tasks) {
            successfulBookings += task.getSuccessfulBookings();
        }

        return new ScenarioResult(
                successfulBookings,
                service.getTotalBookedPlaces(),
                service.getTotalFreePlaces(),
                service.getTotalCapacity(),
                timeMillis
        );
    }

    private static void compareSequentialAndParallel() throws InterruptedException {
        int totalRequests = THREAD_COUNT * ITERATIONS;

        BookingService sequentialService = BookingService.createHotel(100, 4_000);

        long sequentialStart = System.nanoTime();

        for (int i = 1; i <= totalRequests; i++) {
            BookingRequest request = new BookingRequest(i, "Послідовний гість-" + i, 1);
            sequentialService.bookSafe(request);
        }

        long sequentialTime = (System.nanoTime() - sequentialStart) / 1_000_000;

        BookingService parallelService = BookingService.createHotel(100, 4_000);
        ScenarioResult parallelResult = runParallelTasks(
                parallelService,
                BookingTask.Mode.SAFE,
                ITERATIONS
        );

        System.out.println("=== ПОРІВНЯННЯ ЧАСУ ===");
        System.out.println("Однаковий обсяг роботи: " + totalRequests + " бронювань");
        System.out.println("Послідовний варіант: " + sequentialTime + " мс");
        System.out.println("Паралельний варіант: " + parallelResult.timeMillis() + " мс");
        System.out.println("Фактично заброньовано у паралельному варіанті: " + parallelResult.bookedPlaces());

        if (parallelResult.timeMillis() > sequentialTime) {
            System.out.println("Паралельний варіант може бути повільнішим через накладні витрати на синхронізацію.");
        } else {
            System.out.println("Паралельний варіант у цьому запуску виконався швидше.");
        }
    }

    private record ScenarioResult(
            int successfulBookings,
            int bookedPlaces,
            int freePlaces,
            int capacity,
            long timeMillis
    ) {
    }
}