package ua.khpi.oop.lab16.demo;

import ua.khpi.oop.lab16.service.AverageCalculator;
import ua.khpi.oop.lab16.service.CalculationService;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] values = {10, 20, 30, 40, 50};

        System.out.println("Лабораторна робота №16");
        System.out.println("Варіант 3: середнє значення елементів масиву");
        System.out.println("Операція реалізована мовою Java та через JNI");
        System.out.println();
        System.out.println("Вхідний масив: " + Arrays.toString(values));

        AverageCalculator javaCalculator = new AverageCalculator();
        double javaResult = javaCalculator.average(values);

        System.out.println("Результат Java-реалізації: " + javaResult);

        try {
            CalculationService service = new CalculationService();

            double nativeResult = service.calculateNativeAverage(values);

            System.out.println("Результат native-реалізації: " + nativeResult);
            System.out.println("Результати збігаються: " + service.hasSameResult(values));
        } catch (UnsatisfiedLinkError error) {
            System.out.println("Native-бібліотеку не знайдено або не завантажено");
            System.out.println("Потрібно зібрати nativeaverage.dll і запустити програму з java.library.path");
        }
    }
}