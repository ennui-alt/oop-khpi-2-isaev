package ua.khpi.oop.lab16.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AverageCalculatorTest {
    private final AverageCalculator calculator = new AverageCalculator();

    @Test
    void averageShouldCalculatePositiveNumbers() {
        int[] values = {10, 20, 30, 40, 50};

        double result = calculator.average(values);

        assertEquals(30.0, result, 0.000001, "Середнє значення має бути 30.0");
    }

    @Test
    void averageShouldCalculateNegativeNumbers() {
        int[] values = {-10, -20, -30};

        double result = calculator.average(values);

        assertEquals(-20.0, result, 0.000001, "Середнє значення від'ємних чисел має бути правильним");
    }

    @Test
    void averageShouldCalculateMixedNumbers() {
        int[] values = {-10, 0, 10, 20};

        double result = calculator.average(values);

        assertEquals(5.0, result, 0.000001, "Середнє значення змішаних чисел має бути правильним");
    }

    @Test
    void averageShouldRejectNullArray() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.average(null),
                "Масив null має викликати виняток"
        );
    }

    @Test
    void averageShouldRejectEmptyArray() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.average(new int[]{}),
                "Порожній масив має викликати виняток"
        );
    }
}