package ua.khpi.oop.lab16.service;

public class NativeAverageCalculator {

    static {
        System.loadLibrary("nativeaverage");
    }

    public native double average(int[] values);
}