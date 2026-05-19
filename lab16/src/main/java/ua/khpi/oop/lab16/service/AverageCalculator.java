package ua.khpi.oop.lab16.service;

public class AverageCalculator {

    public double average(int[] values) {
        checkArray(values);

        long sum = 0;

        for (int value : values) {
            sum += value;
        }

        return (double) sum / values.length;
    }

    private void checkArray(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException("Масив не може бути null");
        }

        if (values.length == 0) {
            throw new IllegalArgumentException("Масив не може бути порожнім");
        }
    }
}