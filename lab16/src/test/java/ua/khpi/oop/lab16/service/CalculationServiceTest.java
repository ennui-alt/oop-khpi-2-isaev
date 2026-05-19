package ua.khpi.oop.lab16.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculationServiceTest {
    private final CalculationService service = new CalculationService();

    @Test
    void javaAndNativeResultsShouldBeEqualForPositiveNumbers() {
        int[] values = {10, 20, 30, 40, 50};

        double javaResult = service.calculateJavaAverage(values);
        double nativeResult = service.calculateNativeAverage(values);

        assertEquals(javaResult, nativeResult, 0.000001, "Java та native результати мають збігатися");
        assertTrue(service.hasSameResult(values), "Метод hasSameResult має повернути true");
    }

    @Test
    void javaAndNativeResultsShouldBeEqualForNegativeNumbers() {
        int[] values = {-5, -10, -15, -20};

        double javaResult = service.calculateJavaAverage(values);
        double nativeResult = service.calculateNativeAverage(values);

        assertEquals(javaResult, nativeResult, 0.000001, "Результати для від'ємних чисел мають збігатися");
        assertTrue(service.hasSameResult(values), "Результати мають бути однаковими");
    }

    @Test
    void javaAndNativeResultsShouldBeEqualForDifferentCases() {
        int[][] cases = {
                {1, 2, 3},
                {100, 200, 300},
                {-10, 0, 10},
                {7},
                {0, 0, 0, 0}
        };

        for (int[] values : cases) {
            double javaResult = service.calculateJavaAverage(values);
            double nativeResult = service.calculateNativeAverage(values);

            assertEquals(javaResult, nativeResult, 0.000001, "Java та native результати мають збігатися для кожного набору");
        }
    }
}