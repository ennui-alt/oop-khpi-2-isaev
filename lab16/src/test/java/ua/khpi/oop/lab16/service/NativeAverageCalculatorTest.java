package ua.khpi.oop.lab16.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NativeAverageCalculatorTest {
    private final NativeAverageCalculator calculator = new NativeAverageCalculator();

    @Test
    void nativeAverageShouldCalculatePositiveNumbers() {
        int[] values = {10, 20, 30, 40, 50};

        double result = calculator.average(values);

        assertEquals(30.0, result, 0.000001, "Native-реалізація має повернути 30.0");
    }

    @Test
    void nativeAverageShouldCalculateNegativeNumbers() {
        int[] values = {-10, -20, -30};

        double result = calculator.average(values);

        assertEquals(-20.0, result, 0.000001, "Native-реалізація має правильно працювати з від'ємними числами");
    }

    @Test
    void nativeAverageShouldCalculateMixedNumbers() {
        int[] values = {-10, 0, 10, 20};

        double result = calculator.average(values);

        assertEquals(5.0, result, 0.000001, "Native-реалізація має правильно працювати зі змішаними числами");
    }

    @Test
    void nativeAverageShouldRejectNullArray() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.average(null),
                "Native-реалізація має відхиляти null"
        );
    }

    @Test
    void nativeAverageShouldRejectEmptyArray() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.average(new int[]{}),
                "Native-реалізація має відхиляти порожній масив"
        );
    }
}