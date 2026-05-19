package ua.khpi.oop.lab16.service;

public class CalculationService {
    private static final double EPSILON = 0.000001;

    private final AverageCalculator javaCalculator;
    private final NativeAverageCalculator nativeCalculator;

    public CalculationService() {
        this.javaCalculator = new AverageCalculator();
        this.nativeCalculator = new NativeAverageCalculator();
    }

    public double calculateJavaAverage(int[] values) {
        return javaCalculator.average(values);
    }

    public double calculateNativeAverage(int[] values) {
        return nativeCalculator.average(values);
    }

    public boolean hasSameResult(int[] values) {
        double javaResult = calculateJavaAverage(values);
        double nativeResult = calculateNativeAverage(values);

        return Math.abs(javaResult - nativeResult) < EPSILON;
    }
}